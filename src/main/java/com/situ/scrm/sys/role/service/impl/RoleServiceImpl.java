/**
 * @Company:中享思途   
 * @Title:RoleServiceImpl.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午5:20:52     
 */
package com.situ.scrm.sys.role.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.commons.domain.LayTree;
import com.situ.scrm.sys.role.dao.RoleDao;
import com.situ.scrm.sys.role.dao.RoleResourceDao;
import com.situ.scrm.sys.role.domain.Role;
import com.situ.scrm.sys.role.domain.RoleResource;
import com.situ.scrm.sys.role.param.RoleAuth;
import com.situ.scrm.sys.role.service.RoleService;
import com.situ.scrm.sys.syscount.util.SysCountUtils;
import com.situ.scrm.sys.sysresource.dao.SysResourceDao;
import com.situ.scrm.sys.sysresource.domain.SysResource;
import com.situ.scrm.utils.DAOUtils;

/**
 * @ClassName:RoleServiceImpl
 * @Description:(角色类的逻辑层的实现类)
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private SysResourceDao sysResourceDao;
	@Autowired
	private RoleResourceDao roleResourceDao;
	@Autowired
	private SysCountUtils sysCountUtils;

	/**
	 * @Title: checkRoleName
	 * @Description:(检测名称唯一)
	 * @param roleName
	 * @return
	 */
	@Override
	public Integer checkRoleName(String roleName) {
		// 根据名称去查询出实例。
		Role role = roleDao.getByName(roleName);
		// 判断如果不等于null 则返回1，表示已经别使用了。
		return role != null ? 1 : 0;
	}

	/**
	 * @Title: saveRole
	 * @Description:(新增功能)
	 * @param role
	 * @return
	 */
	@Override
	public Long saveRole(Role role) {
		role.setRoleCode(sysCountUtils.buildRoleCode());
		role.setActiveFlag(1);
		role.setCreateBy("SYS");
		role.setCreateDate(new Date());
		roleDao.save(role);
		return role.getRowId();
	}

	/**
	 * @Title: doDeleteRole
	 * @Description:(删除功能)
	 * @param rowId
	 * @return
	 */
	@Override
	public Integer doDeleteRole(Long rowId) {
		roleDao.delete(rowId);
		return 1;
	}

	/**
	 * @Title: getRoleById
	 * @Description:(根据id查询实例)
	 * @param rowId
	 * @return
	 */
	@Override
	public Role getRoleById(Long rowId) {
		return roleDao.get(rowId);
	}

	/**
	 * @Title: doEditRole
	 * @Description:(执行删除)
	 * @param role
	 * @return
	 */
	@Override
	public Integer doEditRole(Role role) {
		Long rowId = role.getRowId();
		Role editRole = roleDao.get(rowId);
		roleDao.update(DAOUtils.buildEditData(editRole, role));
		return 1;
	}

	/**
	 * @Title: getCount
	 * @Description:(查询出数据的数量)
	 * @return
	 */
	@Override
	public Integer getCount(Role searchRole) {
		return roleDao.getCount(searchRole);
	}

	/**
	 * @Title: findRoleByPage
	 * @Description:(根据分页查询数据)
	 * @param page       页号
	 * @param limit      每页显示的条数
	 * @param searchRole 查询条件的数据
	 * @return
	 */
	@Override
	public LayResult findRoleByPage(Integer page, Integer limit, Role searchRole) {
		// 通过反射机制将类的实例中的""重新赋值为null,方便MyBatis中多条件查询SQL语句的拼写
		Role searchParam = DAOUtils.buildSearchParam(searchRole);
		// 查询出符合条件的一共有多少条记录。
		Integer dataCount = roleDao.getCount(searchParam);
		// 根据分页的请求信息查询出数量列表。
		List<Role> roleList = roleDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "", dataCount, roleList);
	}

	/**
	 * @Title: findAllRole
	 * @Description:(查询所有的角色信息)
	 * @return
	 */
	@Override
	public List<Role> findAllRole() {
		return roleDao.find();
	}

	/**
	 * @Title: findParentResourceIdList
	 * @Description:(查询出所有的父类资源的主键的集合)
	 * @return
	 */
	@Override
	public List<Long> findParentResourceIdList() {
		return sysResourceDao.findIdByParent(SysResource.DEFAULT_PARENT_CODE);
	}

	/**
	 * @Title: setRoleAuth
	 * @Description:(设置权限)
	 * @param roleAuth
	 * @return
	 */
	@Override
	@Transactional
	public Integer setRoleAuth(RoleAuth roleAuth) {
		Long roleId = roleAuth.getRoleId();
		List<String> rescCodeList = roleAuth.getRescCodeList();
		// 查询出所有的原来有的资源的信息集合
		List<RoleResource> havedResourceList = roleResourceDao.findAll(roleId);
		// 需要进行修改的集合
		List<RoleResource> updataList = new ArrayList<RoleResource>();
		// 需要进行新增的集合
		List<RoleResource> saveList = new ArrayList<RoleResource>();
		// 先将原来的数据设置为失效
		roleResourceDao.updateFail(roleId);
		int haveCount = havedResourceList.size();
		Date date = new Date();
		for (int index = 0; index < rescCodeList.size(); index++) {
			String rescCode = rescCodeList.get(index);
			if (index < haveCount) {
				RoleResource editRoleResource = havedResourceList.get(index);
				editRoleResource.setRoleId(roleId);
				editRoleResource.setRescCode(rescCode);
				editRoleResource.setActiveFlag(1);
				editRoleResource.setUpdateBy("sys");
				editRoleResource.setUpdateDate(date);
				updataList.add(editRoleResource);
			} else {
				RoleResource roleResource = new RoleResource();
				roleResource.setRoleId(roleId);
				roleResource.setRescCode(rescCode);
				roleResource.setActiveFlag(1);
				roleResource.setCreateBy("sys");
				roleResource.setCreateDate(date);
				saveList.add(roleResource);
			}
		}
		if (updataList != null && !updataList.isEmpty()) {
			roleResourceDao.updateBatch(updataList);
		}
		if (saveList != null && !saveList.isEmpty()) {
			roleResourceDao.saveBatch(saveList);
		}
		return 1;
	}

	/**
	 * @Title: buildRescCodeList
	 * @Description:(根据layui的写法，需要选中的code只保留最下面的信息)
	 * @param roleId
	 * @return
	 */
	private List<String> buildRescCodeList(Long roleId) {
		// 根据角色ID查询出 已有的权限的资源CODE集合
		List<String> rescCodeAllList = roleResourceDao.findCode(roleId);
		// 查询所有的资源信息
		List<SysResource> resourceList = sysResourceDao.find();
		// Map<rescCode,parentCode>
		Map<String, String> rescCodeMap = new HashMap<String, String>();
		if (resourceList != null) {
			for (SysResource sysResource : resourceList) {
				rescCodeMap.put(sysResource.getRescCode(), sysResource.getParentCode());
			}
		}
		// 尝试将资源的parentCode移除掉
		List<String> removeCodeList = new ArrayList<String>();
		if (rescCodeAllList != null) {
			for (String rescCode : rescCodeAllList) {
				String parentCode = rescCodeMap.get(rescCode);
				if (parentCode != null) {
					removeCodeList.add(parentCode);
				}
			}
			for (Iterator<String> iterator = rescCodeAllList.iterator(); iterator.hasNext();) {
				String checkCode = iterator.next();
				if (removeCodeList.contains(checkCode)) {
					iterator.remove();
				}
			}
		}

		return rescCodeAllList;
	}

	/**
	 * @Title: findRoleAuthDataList
	 * @Description:(根据角色主键查询出系统资源的信息)
	 * @param roleId 角色ID
	 * @return Map<sysResourceId,List<LayTree>>
	 */
	@Override
	public Map<Long, List<LayTree>> findRoleAuthDataList(Long roleId) {
		// 根据角色ID查询出 已有的权限的资源CODE集合#根据layui的写法，需要选中的code只保留最下面的信息
		List<String> rescCodeList = buildRescCodeList(roleId);
		Map<Long, List<LayTree>> layTreeMap = new LinkedHashMap<Long, List<LayTree>>();
		// 将所有的资源信息build成Map<ParentCode,List<SysResource>>
		Map<String, List<LayTree>> resourceMap = buildResourceMap(rescCodeList);
		if (resourceMap != null) {
			// 先从map中将一级资源的集合取出。
			List<LayTree> resourceList = resourceMap.get(SysResource.DEFAULT_PARENT_CODE);
			// 遍历一级资源
			if (resourceList != null) {
				for (LayTree layTree : resourceList) {
					// 判断是否有子数据
					Integer hasChild = layTree.getHasChild();
					// 如果有子数据
					if (hasChild == 1) {
						// 通过递归方法(重复调用本身)将所有资源的子数据处理成功。
						callBackChildList(layTree, resourceMap);
					}
					List<LayTree> list = new ArrayList<LayTree>();
					list.add(layTree);
					layTreeMap.put(layTree.getRowId(), list);
				}
			}
		}
		return layTreeMap;
	}

	/**
	 * @Title: buildResourceMap
	 * @Description:(将资源数据转换成MAP格式的数据)
	 * @param resourceList
	 * @return Map<parentCode,List<LayTree>>
	 */
	private Map<String, List<LayTree>> buildResourceMap(List<String> rescCodeList) {
		// 将系统资源所有的数据都查询出来。
		List<SysResource> resourceList = sysResourceDao.find();
		Map<String, List<LayTree>> resourceMap = new HashMap<String, List<LayTree>>();
		if (resourceList != null) {
			for (SysResource sysResource : resourceList) {
				String parentCode = sysResource.getParentCode();
				List<LayTree> list = resourceMap.get(parentCode);
				if (list == null) {
					list = new ArrayList<LayTree>();
				}
				list.add(buildLayTree(sysResource, rescCodeList));
				resourceMap.put(parentCode, list);
			}
		}
		return resourceMap;
	}

	/**
	 * @Title: buildLayTree
	 * @Description:(将SysResource转换成 LayTree)
	 * @param sysResource
	 * @param rescCodeList
	 * @return
	 */
	private LayTree buildLayTree(SysResource sysResource, List<String> rescCodeList) {
		String rescCode = sysResource.getRescCode();
		// 默认选中是个false
		boolean checked = false;
		// 如果有符合条件的数据, 则将默认的选中设置为true
		if (rescCodeList != null && rescCodeList.contains(rescCode)) {
			checked = true;
		}
		return new LayTree(sysResource.getRescName(), rescCode, true, checked, sysResource.getHasChild(),
				sysResource.getRowId());
	}

	/**
	 * @Title: callBackChildList
	 * @Description:(递归的方式拿到子类数据)
	 * @param parentCode
	 * @param resourceMap
	 * @return
	 */
	private void callBackChildList(LayTree layTree, Map<String, List<LayTree>> resourceMap) {
		String parentCode = layTree.getId();
		List<LayTree> childList = resourceMap.get(parentCode);
		if (childList != null) {
			for (LayTree child : childList) {
				if (child.getHasChild() == 1) {
					callBackChildList(child, resourceMap);
				}
			}
			// 将拿到的子数据设置进去。
			layTree.setChildren(childList);
		}
	}
}

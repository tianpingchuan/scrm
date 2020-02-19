/**
 * @Company:中享思途   
 * @Title:SysResourceServiceImpl.java 
 * @Author:wxinpeng   
 * @Date:2020年2月13日 下午4:20:07     
 */
package com.situ.scrm.sys.sysresource.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.syscount.util.SysCountUtils;
import com.situ.scrm.sys.sysresource.dao.SysActionInfoDao;
import com.situ.scrm.sys.sysresource.dao.SysResourceDao;
import com.situ.scrm.sys.sysresource.domain.SysActionInfo;
import com.situ.scrm.sys.sysresource.domain.SysResource;
import com.situ.scrm.sys.sysresource.service.SysResourceService;
import com.situ.scrm.utils.DAOUtils;

/**
 * @ClassName:SysResourceServiceImpl
 * @Description:(SysResourceServiceImpl)
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {
	@Autowired
	private SysResourceDao sysResourceDao;
	@Autowired
	private SysActionInfoDao sysActionInfoDao;
	@Autowired
	private SysCountUtils sysCountUtils;

	/**
	 * @Title: findAllResource
	 * @Description:(查询所有的资源信息)
	 * @return
	 */
	@Override
	public LayResult findAllResource() {
		List<SysResource> allResourceList = new ArrayList<SysResource>();
		// 将所有的资源信息build成Map<ParentCode,List<SysResource>>
		Map<String, List<SysResource>> resourceMap = buildResourceMap();
		if (resourceMap != null) {
			// 先从map中将一级资源的集合取出。
			List<SysResource> resourceList = resourceMap.get(SysResource.DEFAULT_PARENT_CODE);
			// 遍历一级资源
			if (resourceList != null) {
				for (SysResource sysResource : resourceList) {
					// 判断是否有子数据
					Integer hasChild = sysResource.getHasChild();
					// 如果有子数据
					if (hasChild == 1) {
						// 通过递归方法(重复调用本身)将所有资源的子数据处理成功。
						callBackChildList(sysResource, resourceMap);
					}
					allResourceList.add(sysResource);
				}
			}
		}
		return new LayResult(200, "ok", allResourceList);
	}

	/**
	 * @Title: callBackChildList
	 * @Description:(递归的方式拿到子类数据)
	 * @param parentCode
	 * @param resourceMap
	 * @return
	 */
	private void callBackChildList(SysResource sysResource, Map<String, List<SysResource>> resourceMap) {
		String parentCode = sysResource.getRescCode();
		List<SysResource> childList = resourceMap.get(parentCode);
		if (childList != null) {
			for (SysResource child : childList) {
				if (child.getHasChild() == 1) {
					callBackChildList(child, resourceMap);
				}
			}
			// 将拿到的子数据设置进去。
			sysResource.setChildren(childList);
		}
	}

	/**
	 * @Title: buildResourceMap
	 * @Description:(将资源数据转换成MAP格式的数据)
	 * @param resourceList
	 * @return Map<parentCode,List<SysResource>>
	 */
	private Map<String, List<SysResource>> buildResourceMap() {
		// 将系统资源所有的数据都查询出来。
		List<SysResource> resourceList = sysResourceDao.find();
		Map<String, List<SysResource>> resourceMap = new HashMap<String, List<SysResource>>();
		if (resourceList != null) {
			for (SysResource sysResource : resourceList) {
				String parentCode = sysResource.getParentCode();
				List<SysResource> list = resourceMap.get(parentCode);
				if (list == null) {
					list = new ArrayList<SysResource>();
				}
				list.add(sysResource);
				resourceMap.put(parentCode, list);
			}
		}
		return resourceMap;
	}

	/**
	 * @Title: saveSysResource
	 * @Description:(新增资源信息)
	 * @param sysResource
	 * @return
	 */
	@Override
	@Transactional // 此逻辑牵扯到多次数据库的操作，启用事务处理
	public Long saveSysResource(SysResource sysResource) {
		// 得到资源编号
		String rescCode = sysCountUtils.buildRescCode();
		sysResource.setRescCode(rescCode);
		// 根据父类数据处理排序问题。
		String parentCode = sysResource.getParentCode();
		Integer maxOrder = sysResourceDao.getMaxOrder(parentCode);
		maxOrder = maxOrder == null ? 0 : maxOrder;
		sysResource.setRescOrder(maxOrder + 1);
		// 如果不是默认的ParentCode，则需要对parent类更新有子类数据信息
		if (!SysResource.DEFAULT_PARENT_CODE.equals(parentCode)) {
			sysResourceDao.updateHasChild(parentCode, 1);
		}
		sysResource.setHasChild(0);
		sysResource.setActiveFlag(1);
		sysResource.setCreateBy("sys");
		sysResource.setCreateDate(new Date());
		sysResourceDao.save(sysResource);
		// 处理资源信息对应的 对象数据
		List<SysActionInfo> actionInfoList = removeNullData(sysResource.getActionInfoList());
		// 处理资源信息的动作数据
		handlerActionInfoListData(rescCode, actionInfoList);
		return sysResource.getRowId();
	}

	/**
	 * @Title: updateSysResource
	 * @Description:(修改资源信息)
	 * @param sysResource
	 * @return
	 */
	@Override
	public Long updateSysResource(SysResource sysResource) {
		Long rowId = sysResource.getRowId();
		SysResource editResource = sysResourceDao.get(rowId);
		editResource = DAOUtils.buildEditData(editResource, sysResource);
		editResource.setUpdateBy("sys");
		editResource.setUpdateDate(new Date());
		sysResourceDao.update(editResource);
		// 处理资源信息对应的 对象数据
		List<SysActionInfo> actionInfoList = removeNullData(sysResource.getActionInfoList());
		// 处理资源信息的动作数据
		handlerActionInfoListData(editResource.getRescCode(), actionInfoList);
		return rowId;
	}

	/**
	 * @Title: removeNullData
	 * @Description:(去除空数据,因为前台页面穿过来得数据因为下标的问题有可能出现空数据)
	 * @param actionInfoList
	 * @return
	 */
	private List<SysActionInfo> removeNullData(List<SysActionInfo> actionInfoList) {
		if (actionInfoList != null) {
			for (Iterator<SysActionInfo> iterator = actionInfoList.iterator(); iterator.hasNext();) {
				SysActionInfo sysActionInfo = iterator.next();
				if (sysActionInfo.getMethod() == null || sysActionInfo.getActionUrl() == null) {
					iterator.remove();
				}
			}
			return actionInfoList;
		}
		return null;
	}

	/**
	 * @Title: handlerActionInfoListData
	 * @Description:(处理资源信息的动作数据)
	 * @param rescCode
	 * @param actionInfoList
	 */
	private void handlerActionInfoListData(String rescCode, List<SysActionInfo> actionInfoList) {
		List<SysActionInfo> haveActionInfoList = sysActionInfoDao.findByCode(rescCode);
		// 尝试将原来的Actioninfo数据设置成失效
		sysActionInfoDao.updateFail(rescCode);
		if (actionInfoList != null && !actionInfoList.isEmpty()) {
			Date nowDate = new Date();
			if (haveActionInfoList != null && !haveActionInfoList.isEmpty()) {
				int haveCount = haveActionInfoList.size();
				int newCount = actionInfoList.size();
				for (int i = 0; i < newCount; i++) {
					SysActionInfo newActionInfo = actionInfoList.get(i);
					// 判断原来已经有数据了
					if (i < haveCount) {
						SysActionInfo editActionInfo = haveActionInfoList.get(i);
						editActionInfo = DAOUtils.buildEditData(editActionInfo, newActionInfo);
						editActionInfo.setActiveFlag(1);
						editActionInfo.setUpdateBy("Sys");
						editActionInfo.setUpdateDate(nowDate);
						sysActionInfoDao.update(editActionInfo);
					} else {
						newActionInfo.setRescCode(rescCode);
						newActionInfo.setActiveFlag(1);
						newActionInfo.setCreateBy("Sys");
						newActionInfo.setCreateDate(nowDate);
						sysActionInfoDao.save(newActionInfo);
					}
				}
			} else {
				for (SysActionInfo actionInfo : actionInfoList) {
					actionInfo.setRescCode(rescCode);
					actionInfo.setActiveFlag(1);
					actionInfo.setCreateBy("Sys");
					actionInfo.setCreateDate(nowDate);
					sysActionInfoDao.save(actionInfo);
				}
			}
		}
	}

	/**
	 * @Title: checkRescName
	 * @Description:(检测名称唯一)
	 * @param rescName
	 * @param parentCode
	 * @return
	 */
	@Override
	public Integer checkRescName(String rescName, String parentCode) {
		SysResource sysResource = sysResourceDao.getByNameAndParent(rescName, parentCode);
		return sysResource == null ? 0 : 1;
	}

	/**
	 * @Title: getResourceById
	 * @Description:(根据主键查询Resource对象)
	 * @param rowId
	 * @return
	 */
	@Override
	public SysResource getResourceById(Long rowId) {
		return sysResourceDao.get(rowId);
	}

	/**
	 * @Title: getResourceByCode
	 * @Description:(根据CODE查询Resource对象)
	 * @param rescCode
	 * @return
	 */
	@Override
	public SysResource getAllResourceById(Long rowId) {
		SysResource sysResource = sysResourceDao.get(rowId);
		List<SysActionInfo> actionInfoList = sysActionInfoDao.findByCode(sysResource.getRescCode());
		sysResource.setActionInfoList(actionInfoList);
		String parentCode = sysResource.getParentCode();
		if (!parentCode.equals(SysResource.DEFAULT_PARENT_CODE)) {
			SysResource parentResource = sysResourceDao.getByCode(parentCode);
			sysResource.setParentName(parentResource.getRescName());
		}
		return sysResource;
	}

	/**
	 * @Title: findActionInfoList
	 * @Description:(根据RescCode查询动作集合)
	 * @param rescCode
	 * @return
	 */
	@Override
	public List<SysActionInfo> findActionInfoList(String rescCode) {
		return sysActionInfoDao.findByCode(rescCode);
	}

	/**
	 * @Title: doDeleteSysResource
	 * @Description:(删除)
	 * @param rowId
	 * @return
	 */
	@Override
	public Integer doDeleteSysResource(Long rowId) {
		SysResource deleteResrouce = sysResourceDao.get(rowId);
		String rescCode = deleteResrouce.getRescCode();
		String parentCode = deleteResrouce.getParentCode();
		sysResourceDao.delete(rowId);
		// 回调删除相关的数据
		handlerDeleteResource(deleteResrouce);
		// 如果不是默认的父类CODE
		if (!parentCode.equals(SysResource.DEFAULT_PARENT_CODE)) {
			// 修改父类的资源是否有子元素
			Integer hasChild = 0;
			List<SysResource> childResourceList = sysResourceDao.findByParent(rescCode);
			if (childResourceList != null && !childResourceList.isEmpty()) {
				hasChild = 1;
			}
			sysResourceDao.updateHasChild(parentCode, hasChild);
		}
		return 1;
	}

	/**
	 * @Title: handlerDeleteResource
	 * @Description:(回调删除相关的数据)
	 * @param parentResoruce
	 */
	private void handlerDeleteResource(SysResource parentResoruce) {
		String rescCode = parentResoruce.getRescCode();
		// 删除ActionInfo数据
		sysActionInfoDao.delete(rescCode);
		List<SysResource> childResourceList = sysResourceDao.findByParent(rescCode);
		if (childResourceList != null) {
			for (SysResource sysResource : childResourceList) {
				sysResourceDao.delete(sysResource.getRowId());
				// 删除ActionInfo数据
				sysActionInfoDao.delete(sysResource.getRescCode());
				if (sysResource.getHasChild() == 1) {
					handlerDeleteResource(sysResource);
				}
			}
		}
	}
}

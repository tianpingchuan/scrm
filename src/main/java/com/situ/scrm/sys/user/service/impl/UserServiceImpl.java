package com.situ.scrm.sys.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.role.dao.RoleDao;
import com.situ.scrm.sys.role.dao.RoleResourceDao;
import com.situ.scrm.sys.role.domain.Role;
import com.situ.scrm.sys.set.dao.SetDao;
import com.situ.scrm.sys.sysresource.dao.SysActionInfoDao;
import com.situ.scrm.sys.sysresource.dao.SysResourceDao;
import com.situ.scrm.sys.sysresource.domain.SysActionInfo;
import com.situ.scrm.sys.sysresource.domain.SysResource;
import com.situ.scrm.sys.user.dao.UserDao;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.sys.user.service.UserService;
import com.situ.scrm.utils.ConfigUtils;
import com.situ.scrm.utils.DAOUtils;
import com.situ.scrm.utils.MD5Utils;

@Service
public class UserServiceImpl implements UserService {
	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private SetDao setDao;
	@Autowired
	private SysResourceDao sysResourceDao;
	@Autowired
	private RoleResourceDao roleResourceDao;
	@Autowired
	private SysActionInfoDao sysActionInfoDao;

	/**
	 * @Title: checkUserCode
	 * @Description:(检测名称唯一)
	 * @param userCode
	 * @return
	 */
	@Override
	public Integer checkUserCode(String userCode) {
		User checkUser = userDao.getUserByCode(userCode);
		return checkUser != null ? 1 : 0;
	}

	/**
	 * @Title: saveUser
	 * @Description:(新增功能)
	 * @param User
	 * @return
	 */
	@Override
	public Long saveUser(User user) {
		user.setUserPass(MD5Utils.encode(user.getUserPass()));
		user.setIsLock(User.IS_LOCK_NO);
		user.setLoginCount(0);
		user.setCreateBy("SYS");
		user.setCreateDate(new Date());
		user.setActiveFlag(1);
		userDao.save(user);
		return user.getRowId();
	}

	@Override
	public Integer doDeleteUser(Long rowId) {
		userDao.delete(rowId);
		return 1;
	}

	@Override
	public User getUser(Long rowId) {
		return userDao.get(rowId);
	}

	@Override
	public Integer doEditUser(User user) {
		Long rowId = user.getRowId();
		User editUser = userDao.get(rowId);
		userDao.update(DAOUtils.buildEditData(editUser, user));
		return 1;
	}

	@Override
	public Integer getCount(User searchUser) {
		return userDao.getCount(searchUser);
	}

	@Override
	public LayResult findUserByPage(Integer page, Integer limit, User searchUser) {
		// 通过反射机制将类的实例中的""重新赋值为null,方便MyBatis中多条件查询SQL语句的拼写
		User searchParam = DAOUtils.buildSearchParam(searchUser);
		// 查询出符合条件的一共有多少条记录。
		Integer dataCount = userDao.getCount(searchParam);
		// 根据分页的请求信息查询出数量列表。
		List<User> userList = userDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		for (User user : userList) {// 遍历员工集合
			Long roleId = user.getUserRole();
			Role role = roleDao.get(roleId);
			String userRoleName = role.getRoleName();
			user.setUserRoleName(userRoleName);
		}
		return new LayResult(0, "", dataCount, userList);
	}

	/**
	 * @Title: findAllUser
	 * @Description:(查询全部员工信息)
	 * @return
	 */
	@Override
	public List<User> findAllUser() {
		return null;
	}

	/**
	 * @Title: findAllUserByKind
	 * @Description:(根据级别查找用户)
	 * @param userKind
	 * @return
	 */
	@Override
	public List<User> findAllUserByKind(Long userKind) {
		List<User> userList = new ArrayList<User>();
//		如果传进来的userKind是1，则将用户设置成公司名称
		if (userKind == 0) {
			User user = new User();
			user.setRowId(0L);
			user.setUserName(setDao.find().get(0).getConfig1());
			userList.add(user);
		} else {
			userList = userDao.findAllUserByKind(userKind);
		}
		return userList;
	}

	/**
	 * @Title: login
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param user
	 * @return
	 */
	@Override
	public User login(User user) {
		user.setUserPass(MD5Utils.encode(user.getUserPass()));
		return userDao.login(user);
	}

	/**
	 * @Title: findUserByCode
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param login
	 * @return
	 */
	@Override
	public User findUserByCode(String userCode) {
		return userDao.getUserByCode(userCode);
	}

	/**
	 * @Title: findUserByCodeAndId
	 * @Description:(通过code和id查询用户)
	 * @param userCode
	 * @param rowId
	 * @return
	 */
	@Override
	public User findUserByCodeAndId(String userCode, Long rowId) {
		return userDao.findUserByCodeAndId(userCode, rowId);
	}

	/**
	 * @Title: doUpdateLogin
	 * @Description:(修改登录时间)
	 * @param user
	 */
	@Override
	public void doUpdateLogin(User user) {
		userDao.update(user);
	}

	/**
	 * @Title: findAuthResourceList
	 * @Description:(查询出用户登录的权限菜单相关的数据)
	 * @param session
	 * @return
	 */
	@Override
	public List<SysResource> findAuthResourceList(String userCode, HttpSession session) {
//		根据Code查询出用户实例
		User loginUser = userDao.getUserByCode(userCode);
		Integer userKind = loginUser.getUserKind();
		List<SysResource> resourceList = null;
		if (userKind == 1) {// 一级用户拥有全部权限
			resourceList = sysResourceDao.find();
		} else {// 普通用户，需要根据角色信息build出资源的信息
//			得到角色的id
			Long roleId = loginUser.getUserRole();
			if (roleId != null) {
//				查询出所有的资源Code
				List<String> rescCodeList = roleResourceDao.findCode(roleId);
//				实例化资源集合
				resourceList = new ArrayList<SysResource>();
//				查询出全部资源信息
				List<SysResource> allResourceList = sysResourceDao.find();
				for (SysResource sysResource : allResourceList) {
					if (rescCodeList != null && rescCodeList.contains(sysResource.getRescCode())) {
						resourceList.add(sysResource);
					}
				}
			}
		}

		hanlderUserLoginEdData(loginUser, session);

//		根据用户code查询出角色的相关信息
		return buildMenuResourceList(resourceList);
	}

	/**
	 * @Title: buildMenuResourceList
	 * @Description:(将这些数据编译成菜单的格式)
	 * @param resourceList
	 * @return
	 */
	private List<SysResource> buildMenuResourceList(List<SysResource> resourceList) {
		List<SysResource> find = new ArrayList<SysResource>();
		for (SysResource sysResource : resourceList) {
			String parentCode = sysResource.getParentCode();
			if (parentCode.equals(SysResource.DEFAULT_PARENT_CODE)) {// 先将一级资源放入find集合
				if (sysResource.getHasChild() == 1) {
					callBackChildList(sysResource, resourceList);
				}
				find.add(sysResource);
			}
		}
		return find;
	}

	/**
	 * @Title: callBackChildList
	 * @Description:(递归的方式拿到子类数据)
	 * @param sysResource
	 * @param resourceList
	 */
	private void callBackChildList(SysResource sysResource, List<SysResource> resourceList) {
		String parentCode = sysResource.getRescCode();
		List<SysResource> childList = new ArrayList<SysResource>();
//			将集合遍历赋值给资源类
		for (SysResource child : resourceList) {
//				如果资源的父类code与取得的code相同
			if (child.getParentCode().equals(parentCode)) {
//					将二级资源放入child的集合
				System.out.println(child);
				childList.add(child);
//					递归继续查询数据
//					callBackChildList(child, resourceList);
			}
		}
//			将子数据集合放入资源的变量中
		sysResource.setChildren(childList);

	}

	/**
	 * @Title: hanlderUserLoginEdData
	 * @Description:(一切验证成功，开始处理用户登录的数据处理[用户登录状态+用户的权限相关的数据])
	 * @param loginUser
	 * @param session
	 */
	private void hanlderUserLoginEdData(User loginUser, HttpSession session) {
		Integer userKind = loginUser.getUserKind();
//		用户根据角色对应的动作集合
		List<SysActionInfo> actionInfoList = null;
		if (userKind == 1) {
			actionInfoList = sysActionInfoDao.find();
		} else {// 普通用户查询出动作数据
			String userCode = loginUser.getUserCode();
//			得到对应角色的id
			Long roleId = loginUser.getUserRole();
			if (roleId != null) {
//				查询出所有的资源Code
				List<String> rescCodeList = roleResourceDao.findCode(roleId);
				if (rescCodeList != null) {
					actionInfoList = new ArrayList<SysActionInfo>();
//					将所有的资源动作数据查询出来，在根据用户所具有的资源Code集合进行筛选
					List<SysActionInfo> allActionInfoList = sysActionInfoDao.find();
					if (allActionInfoList != null) {
						for (SysActionInfo sysActionInfo : allActionInfoList) {
//							判断符合用户所具有的，筛选出来
							if(rescCodeList.contains(sysActionInfo.getRescCode())) {
								actionInfoList.add(sysActionInfo);
							}
						}
					}
				}
			}
		}
//		开始整理动作数据
		if(actionInfoList != null) {
			Map<String,Set<String>> actionInfoMap = new HashMap<String,Set<String>>();
			for (SysActionInfo actionInfo : actionInfoList) {
				String method = actionInfo.getMethod();
				String actionUrl = actionInfo.getActionUrl();
				Set<String> actionUrlSet = actionInfoMap.get(method);
				if (actionUrlSet == null) {
					actionUrlSet = new HashSet<String>();
				}
//				将数据库中提供的带着占位符的url数据，通过字符替换的方式，让其转成正则的格式，方便后期匹配
				actionUrlSet.add(actionUrl.replaceAll("\\{\\w*\\}", "\\\\w+"));
				actionInfoMap.put(method, actionUrlSet);
			}
//			将整理出的数据放置到session中
			session.setAttribute(ConfigUtils.SESSION_RESOURCE_MAP_ROLE, actionInfoMap);
		}
	}

//	/**
//	 * @Title: buildMenuResourceList
//	 * @Description:(将这些数据编译成菜单的格式)
//	 * @param resourceList
//	 * @return
//	 */
//	private List<SysResource> buildMenuResourceList(List<SysResource> resourceList) {
//		List<SysResource> find = new ArrayList<SysResource>();
//		for (SysResource sysResource : resourceList) {
//			String parentCode = sysResource.getParentCode();
//			if (parentCode.equals(SysResource.DEFAULT_PARENT_CODE)) {// 先将一级资源放入find集合
//				if (sysResource.getHasChild() == 1) {
//					callBackChildList(sysResource, resourceList);
//				}
//				find.add(sysResource);
//			}
//		}
//		return find;
//	}
//
//	/**
//	 * @Title: callBackChildList
//	 * @Description:(递归的方式拿到子类数据)
//	 * @param sysResource
//	 * @param resourceList
//	 */
//	private void callBackChildList(SysResource sysResource, List<SysResource> resourceList) {
//		String parentCode = sysResource.getRescCode();
//		List<SysResource> childList = new ArrayList<SysResource>();
//		if (sysResource.getHasChild() == 1) {// 判断有子数据
////			将集合遍历赋值给资源类
//			for (SysResource child : resourceList) {
////				如果资源的父类code与取得的code相同
//				if (child.getParentCode().equals(parentCode)) {
////					将资源放入child的集合
//					childList.add(child);
////					递归继续查询数据
//					callBackChildList(child, resourceList);
//				}
//			}
////			将子数据集合放入资源的变量中
//			sysResource.setChildren(childList);
//		}
//	}

}

package com.situ.scrm.sys.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.sysresource.domain.SysResource;
import com.situ.scrm.sys.user.domain.User;

public interface UserService {
	/**
	 * @Title: checkUserCode 
	 * @Description:(检测名称唯一)
	 * @param userCode
	 * @return
	 */
	Integer checkUserCode(String userCode);

	/**
	 * @Title: saveUser 
	 * @Description:(新增功能)
	 * @param user
	 * @return
	 */
	Long saveUser(User user);

	/**
	 * @Title: doDeleteUser 
	 * @Description:(删除功能)
	 * @param rowId
	 * @return
	 */
	Integer doDeleteUser(Long rowId);

	/**
	 * @Title: getUser 
	 * @Description:(根据id查询实例)
	 * @param rowId
	 * @return
	 */
	User getUser(Long rowId);

	/**
	 * @Title: doEditUser 
	 * @Description:(执行删除)
	 * @param user
	 * @return
	 */
	Integer doEditUser(User user);

	/**
	 * @Title: getCount 
	 * @Description:(查询出数据的数量)
	 * @return
	 */
	Integer getCount(User searchUser);

	/**
	 * @Title: findUserByPage 
	 * @Description:(根据分页查询数据)
	 * @param page
	 * @param limit
	 * @param searchUser
	 * @return
	 */
	LayResult findUserByPage(Integer page, Integer limit, User searchUser);

	/** 
	 * @Title: findAllUser 
	 * @Description:(查询全部员工信息)
	 * @return  
	 */ 
	List<User> findAllUser();

	/** 
	 * @Title: findAllUserByKind 
	 * @Description:(根据级别查找用户)
	 * @param l
	 * @return  
	 */ 
	List<User> findAllUserByKind(Long userKind);

	/** 
	 * @Title: login 
	 * @Description:(登录查找用户)
	 * @param user  
	 */ 
	User login(User user);

	/** 
	 * @Title: findUserByCode 
	 * @Description:(通过Code查询用户)
	 * @param login  
	 */ 
	User findUserByCode(String userCode);

	/** 
	 * @Title: findUserByCodeAndId 
	 * @Description:(通过code和id查询用户)
	 * @param userCode
	 * @param rowId
	 * @return  
	 */ 
	User findUserByCodeAndId(String userCode, Long rowId);

	/** 
	 * @Title: doUpdateLogin 
	 * @Description:(修改登录时间)
	 * @param user  
	 */ 
	void doUpdateLogin(User user);

	/** 
	 * @Title: findAuthResourceList 
	 * @Description:(查询出用户登录的权限菜单相关的数据)
	 * @param session
	 * @return  
	 */ 
	List<SysResource> findAuthResourceList(String userCode,HttpSession session);
}

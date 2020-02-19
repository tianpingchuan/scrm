/**
 * @Company:中享思途   
 * @Title:RoleService.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午5:19:38     
 */
package com.situ.scrm.sys.role.service;

import java.util.List;
import java.util.Map;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.commons.domain.LayTree;
import com.situ.scrm.sys.role.domain.Role;
import com.situ.scrm.sys.role.param.RoleAuth;

/**
 * @ClassName:RoleService
 * @Description:(角色管理的逻辑层)
 */
public interface RoleService {
	/**
	 * @Title: checkRoleName
	 * @Description:(检测名称唯一)
	 * @param roleName
	 * @return
	 */
	Integer checkRoleName(String roleName);

	/**
	 * @Title: saveRole
	 * @Description:(新增功能)
	 * @param role
	 * @return
	 */
	Long saveRole(Role role);

	/**
	 * @Title: doDeleteRole
	 * @Description:(删除功能)
	 * @param rowId
	 * @return
	 */
	Integer doDeleteRole(Long rowId);

	/**
	 * @Title: getRoleById
	 * @Description:(根据id查询实例)
	 * @param rowId
	 * @return
	 */
	Role getRoleById(Long rowId);

	/**
	 * @Title: doEditRole
	 * @Description:(执行删除)
	 * @param role
	 * @return
	 */
	Integer doEditRole(Role role);

	/**
	 * @Title: getCount
	 * @Description:(查询出数据的数量)
	 * @return
	 */
	Integer getCount(Role searchRole);

	/**
	 * @Title: findRoleByPage
	 * @Description:(根据分页查询数据)
	 * @param page
	 * @param limit
	 * @param searchRole
	 * @return
	 */
	LayResult findRoleByPage(Integer page, Integer limit, Role searchRole);

	/**
	 * @Title: findAllRole
	 * @Description:(查询所有的角色信息)
	 * @return
	 */
	List<Role> findAllRole();

	/**
	 * @Title: findParentResourceIdList
	 * @Description:(查询出所有的父类资源的主键的集合)
	 * @return
	 */
	List<Long> findParentResourceIdList();

	/**
	 * @Title: findRoleAuthDataList
	 * @Description:(根据角色主键查询出系统资源的信息)
	 * @param rowId
	 * @return Map<sysResourceId,List<LayTree>>
	 */
	Map<Long, List<LayTree>> findRoleAuthDataList(Long rowId);

	/**
	 * @Title: setRoleAuth
	 * @Description:(设置权限)
	 * @param roleAuth
	 * @return
	 */
	Integer setRoleAuth(RoleAuth roleAuth);
}

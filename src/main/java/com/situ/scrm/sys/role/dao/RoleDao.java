/**
 * @Company:中享思途   
 * @Title:RoleDao.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午2:19:34     
 */
package com.situ.scrm.sys.role.dao;

import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.role.domain.Role;

/** 
 * @ClassName:RoleDao 
 * @Description:(角色的Dao层)  
 */
@Repository
public interface RoleDao extends BaseDao<Role> {
	/**
	 * @Title: getByName 
	 * @Description:(根据名称查询实例)
	 * @param roleName
	 * @return
	 */
	Role getByName(String roleName);
}

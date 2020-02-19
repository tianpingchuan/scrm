/**
 * @Company:中享思途   
 * @Title:RoleResourceDao.java 
 * @Author:wxinpeng   
 * @Date:2020年2月15日 下午3:32:40     
 */
package com.situ.scrm.sys.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.sys.role.domain.RoleResource;

/**
 * @ClassName:RoleResourceDao
 * @Description:(RoleResourceDao)
 */
@Repository
public interface RoleResourceDao {
	/**
	 * @Title: findCode
	 * @Description:(根据角色ID查询资源CODE集合)
	 * @param roleId
	 * @return
	 */
	List<String> findCode(Long roleId);

	/**
	 * @Title: findAll
	 * @Description:(查询所有的角色资源信息有效+无效)
	 * @param roleId
	 * @return
	 */
	List<RoleResource> findAll(Long roleId);

	/**
	 * @Title: updateFail
	 * @Description:(设置为失效)
	 * @param roleId
	 */
	void updateFail(Long roleId);

	/**
	 * @Title: updateBatch
	 * @Description:(批量更新)
	 * @param roleResourceList
	 */
	void updateBatch(@Param("roleResourceList") List<RoleResource> roleResourceList);

	/**
	 * @Title: saveBatch
	 * @Description:(批量新增)
	 * @param roleResourceList
	 */
	void saveBatch(@Param("roleResourceList") List<RoleResource> roleResourceList);
}

/**
 * @Company:中享思途   
 * @Title:SysResourceDao.java 
 * @Author:wxinpeng   
 * @Date:2020年2月13日 下午2:33:48     
 */
package com.situ.scrm.sys.sysresource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.sysresource.domain.SysResource;

/**
 * @ClassName:SysResourceDao
 * @Description:(系统资源的DAO层)
 */
@Repository
public interface SysResourceDao extends BaseDao<SysResource> {
	/**
	 * @Title: getByNameAndParent
	 * @Description:(根据Name和parentCode查询实例)
	 * @param rescName
	 * @param parentCode
	 * @return
	 */
	SysResource getByNameAndParent(@Param("rescName") String rescName, @Param("parentCode") String parentCode);

	/**
	 * @Title: getMaxOrder
	 * @Description:(根据ParentCode得到最大的排序)
	 * @param parentCode
	 * @return
	 */
	Integer getMaxOrder(String parentCode);

	/**
	 * @Title: updateHasChild
	 * @Description:(更新是否有子元素)
	 * @param hasChild
	 */
	void updateHasChild(@Param("rescCode") String rescCode, @Param("hasChild") Integer hasChild);

	/**
	 * @Title: getByCode
	 * @Description:(根据CODE查询实例)
	 * @param rescCode
	 * @return
	 */
	SysResource getByCode(String rescCode);

	/**
	 * @Title: findByParent
	 * @Description:(根据parentCode查询子类数据)
	 * @param parentCode
	 * @return
	 */
	List<SysResource> findByParent(String parentCode);
	
	/**
	 * @Title: findIdByParent
	 * @Description:(根据parentCode查询出主键集合)
	 * @param parentCode
	 * @return
	 */
	List<Long> findIdByParent(String parentCode);
}

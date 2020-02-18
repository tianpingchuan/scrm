/**
 * @Company:中享思途   
 * @Title:DdDao.java 
 * @Author:Administrator   
 * @Date:2020年2月18日 上午10:16:36     
 */
package com.situ.scrm.sys.dd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.dd.domain.Dd;

/**
 * @ClassName:DdDao
 * @Description:(数据字典的Dao层)
 */
@Repository
public interface DdDao extends BaseDao<Dd> {

	/**
	 * 
	 * @Title: getMaxOrder
	 * @Description:(根据父类key得到最大的排序)
	 * @param parentKey
	 * @return
	 */
	Integer getMaxOrder(String parentKey);

	/**
	 * 
	 * @Title: getByKey
	 * @Description:(根据key查询实例)
	 * @param ddKey
	 * @return
	 */
	Dd getByKey(String ddKey);

	/**
	 * 
	 * @Title: findByParent
	 * @Description:(根据parentKey查询子类数据)
	 * @param parentKey
	 * @return
	 */
	List<Dd> findByParent(String parentKey);

	/**
	 * @Title: updateHasChild
	 * @Description:(更新是否有子元素)
	 * @param hasChild
	 */
	void updateHasChild(@Param("ddKey") String ddKey, @Param("hasChild") Integer hasChild);

	/**
	 * @Title: getByNameAndParent
	 * @Description:(根据ddValue和parentName检测字典编码唯一)
	 * @param ddValue
	 * @param parentName
	 * @return
	 */
	Dd getByNameAndParent(@Param("ddValue") String ddValue, @Param("parentKey") String parentKey);

}

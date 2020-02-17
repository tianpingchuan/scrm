/**
 * @Company:中享思途   
 * @Title:BaseDao.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午2:11:52     
 */
package com.situ.scrm.commons.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.situ.scrm.commons.domain.Pagination;

/** 
 * @ClassName:BaseDao 
 * @Description:(DAO的基本接口)  
 */
public interface BaseDao<T> {
	/**
	 * @Title: save 
	 * @Description:(保存对象)
	 * @param t
	 */
	void save(T t);

	/**
	 * @Title: update 
	 * @Description:(修改对象)
	 * @param t
	 */
	void update(T t);

	/**
	 * @Title: delete 
	 * @Description:(根据主键删除对象)
	 * @param rowId
	 */
	void delete(Long rowId);

	/**
	 * @Title: get 
	 * @Description:(根据主键查询对象)
	 * @param rowId
	 * @return
	 */
	T get(Long rowId);

	/**
	 * @Title: find 
	 * @Description:(查询所有的记录)
	 * @return
	 */
	List<T> find();

	/**
	 * @Title: getCount 
	 * @Description:(查询出数据的条数)
	 * @param t
	 * @return
	 */
	Integer getCount(@Param("searchParam") T t);

	/**
	 * @Title: findByPage 
	 * @Description:(根据分页查询)
	 * @param firstReslt 页号
	 * @param maxResults 每页显示的条数
	 * @param t 查询的数据#可以为空
	 * @return
	 */
	List<T> findByPage(@Param("pagination") Pagination pagination, @Param("searchParam") T t);

}

/**
 * @Company:中享思途   
 * @Title:SysCountDao.java 
 * @Author:wxinpeng   
 * @Date:2020年2月5日 下午2:07:41     
 */
package com.situ.scrm.sys.syscount.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.sys.syscount.domain.SysCount;

/** 
 * @ClassName:SysCountDao 
 * @Description:(SysCountDao)  
 */
@Repository
public interface SysCountDao {
	/**
	 * @Title: find 
	 * @Description:(查询所有的记录)
	 * @return
	 */
	List<SysCount> find();

	/**
	 * @Title: save 
	 * @Description:(保存记录)
	 * @param sysCount
	 * @return
	 */
	Long save(SysCount sysCount);

	/**
	 * @Title: get 
	 * @Description:(根据columnName查询对象)
	 * @param columnName
	 * @return
	 */
	Integer get(String columnName);

	/**
	 * @Title: updatePlus 
	 * @Description:(根据主键更新加一)
	 * @param rowId
	 */
	void updatePlus(@Param("columnName") String columnName, @Param("index") Integer index);
}

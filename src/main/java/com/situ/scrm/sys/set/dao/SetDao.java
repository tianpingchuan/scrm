/**
 * @Company:中享思途   
 * @Title:SetDao.java 
 * @Author:Administrator   
 * @Date:2020年2月19日 下午4:45:16     
 */
package com.situ.scrm.sys.set.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.sys.set.domain.Set;

/**
 * @ClassName:SetDao
 * @Description:(系统设置的dao层)
 */
@Repository
public interface SetDao {
	/**
	 * @Title: find
	 * @Description:(查询所有的记录)
	 * @return
	 */
	List<Set> find();

	/**
	 * @Title: save
	 * @Description:(保存记录)
	 * @param sysCount
	 * @return
	 */
	Long save(Set set);

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
	void updatePlus(@Param("columnName") String columnName, @Param("config") String config);
}

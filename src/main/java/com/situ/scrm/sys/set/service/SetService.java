/**
 * @Company:中享思途   
 * @Title:SetService.java 
 * @Author:Administrator   
 * @Date:2020年2月19日 下午5:05:38     
 */ 
package com.situ.scrm.sys.set.service;

import com.situ.scrm.sys.set.domain.Set;

/** 
 * @ClassName:SetService 
 * @Description:(系统设置的service层)  
 */
public interface SetService {

	/**
	 * 
	 * @Title: initSetData 
	 * @Description:(初始化数据)
	 */
	void initSetData();
	
	/**
	 * 
	 * @Title: findSet 
	 * @Description:(查找数据)
	 * @return
	 */
	Set findSet();

	/** 
	 * @Title: updateSet 
	 * @Description:(修改数据)
	 * @param set  
	 * @return 
	 */ 
	Integer updateSet(Set set);
}

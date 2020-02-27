/**
 * @Company:中享思途   
 * @Title:DdService.java 
 * @Author:Administrator   
 * @Date:2020年2月18日 上午10:19:39     
 */ 
package com.situ.scrm.sys.dd.service;

import java.util.List;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dd.domain.Dd;

/** 
 * @ClassName:DdService 
 * @Description:(数据字典的逻辑层)  
 */
public interface DdService {

	/** 
	 * @Title: findAllDd 
	 * @Description:(查询全部数据字典信息)
	 * @return  
	 */ 
	LayResult findAllDd();

	/** 
	 * @Title: saveDd 
	 * @Description:(新增数据字典)
	 * @param dd
	 * @return  
	 */ 
	Long saveDd(Dd dd);

	/** 
	 * @Title: getDdById 
	 * @Description:(根据id查询数据字典)
	 * @param parentId
	 * @return  
	 */ 
	Dd getDdById(Long rowId);

	/** 
	 * @Title: checkDdValue 
	 * @Description:(检测字典编码唯一)
	 * @param ddValue
	 * @param parentName
	 * @return  
	 */ 
	Integer checkDdValue(String ddValue, String parentKey);

	/** 
	 * @Title: doDeleteDd 
	 * @Description:(删除数据字典)
	 * @param rowId
	 * @return  
	 */ 
	Integer doDeleteDd(Long rowId);

	/** 
	 * @Title: updateDd 
	 * @Description:(执行修改)
	 * @param dd
	 * @return  
	 */ 
	Long updateDd(Dd dd);

	/** 
	 * @Title: getAllDdById 
	 * @Description:(根据id查询用户字典实例的全部信息)
	 * @param rowId
	 * @return  
	 */ 
	Dd getAllDdById(Long rowId);

	/**
	 * 
	 * @Title: findAllByParentKey 
	 * @Description:(通过父类key查询全部子字典信息)
	 * @param parentKey
	 * @return
	 */
	List<Dd> findAllByParentKey(String parentKey);
}

/**
 * @Company:中享思途   
 * @Title:SysResourceService.java 
 * @Author:wxinpeng   
 * @Date:2020年2月13日 下午4:18:57     
 */
package com.situ.scrm.sys.sysresource.service;

import java.util.List;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.sysresource.domain.SysActionInfo;
import com.situ.scrm.sys.sysresource.domain.SysResource;

/**
 * @ClassName:SysResourceService
 * @Description:(系统资源服务层)
 */
public interface SysResourceService {
	/**
	 * @Title: getResourceById
	 * @Description:(根据主键查询Resource对象)
	 * @param rowId
	 * @return
	 */
	SysResource getResourceById(Long rowId);

	/**
	 * @Title: getAllResourceById
	 * @Description:(根据主键查询Resource对象)
	 * @param rescCode
	 * @return
	 */
	SysResource getAllResourceById(Long rowId);

	/**
	 * @Title: findAllResource
	 * @Description:(查询所有的资源信息)
	 * @return
	 */
	LayResult findAllResource();

	/**
	 * @Title: saveSysResource
	 * @Description:(新增资源信息)
	 * @param sysResource
	 * @return
	 */
	Long saveSysResource(SysResource sysResource);

	/**
	 * @Title: updateSysResource
	 * @Description:(修改资源信息)
	 * @param sysResource
	 * @return
	 */
	Long updateSysResource(SysResource sysResource);

	/**
	 * @Title: checkRescName
	 * @Description:(检测名称唯一)
	 * @param rescName
	 * @param parentCode
	 * @return
	 */
	Integer checkRescName(String rescName, String parentCode);

	/**
	 * @Title: findActionInfoList
	 * @Description:(根据RescCode查询动作集合)
	 * @param rescCode
	 * @return
	 */
	List<SysActionInfo> findActionInfoList(String rescCode);

	/**
	 * @Title: doDeleteSysResource
	 * @Description:(删除)
	 * @param rowId
	 * @return
	 */
	Integer doDeleteSysResource(Long rowId);
}

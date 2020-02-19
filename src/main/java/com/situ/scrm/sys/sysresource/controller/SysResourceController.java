/**
 * @Company:中享思途   
 * @Title:SysResourceController.java 
 * @Author:wxinpeng   
 * @Date:2020年2月13日 下午2:53:25     
 */
package com.situ.scrm.sys.sysresource.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.sysresource.domain.SysResource;
import com.situ.scrm.sys.sysresource.service.SysResourceService;

/**
 * @ClassName:SysResourceController
 * @Description:(系统资源的Controller层)
 */
@RestController
@RequestMapping("/sysresource")
public class SysResourceController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_INDEX_RESOURCE = "sys/sysresource/sysreource_index";
	private static final String PAGE_ADD_EDIT_RESOURCE = "sys/sysresource/sysreource_add_edit";

	@Autowired
	private SysResourceService sysResourceService;

	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX_RESOURCE);
		return modelAndView;
	}

	/**
	 * @Title: /form
	 * @Description:(进新增或修改页面)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView goAddOrEdit(ModelAndView modelAndView) {
		// 默认的父类CODE
		modelAndView.addObject("parentCode", SysResource.DEFAULT_PARENT_CODE);
		modelAndView.setViewName(PAGE_ADD_EDIT_RESOURCE);
		return modelAndView;
	}

	/**
	 * @Title: goAddChildForm
	 * @Description:(进新增自资源)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/child/{parentId}")
	public ModelAndView goAddChildForm(@PathVariable Long parentId, ModelAndView modelAndView) {
		// 默认的父类CODE
		SysResource parentResource = sysResourceService.getResourceById(parentId);
		modelAndView.addObject("parentCode", parentResource.getRescCode());
		modelAndView.addObject("parentName", parentResource.getRescName());
		modelAndView.setViewName(PAGE_ADD_EDIT_RESOURCE);
		return modelAndView;
	}

	/**
	 * @Title: getResourceById
	 * @Description:(进修改)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/{rowId}")
	public SysResource getResourceById(@PathVariable Long rowId) {
		SysResource sysResource = sysResourceService.getAllResourceById(rowId);
		return sysResource;
	}

	/**
	 * @Title: findAllResource
	 * @Description:(查询出所有的资源信息)
	 * @return
	 */
	@GetMapping("/list")
	public LayResult findAllResource() {
		return sysResourceService.findAllResource();
	}

	/**
	 * @Title: saveSysResource
	 * @Description:(新增资源信息)
	 * @param sysResource
	 * @return
	 */
	@PostMapping
	public Long saveSysResource(SysResource sysResource) {
		return sysResourceService.saveSysResource(sysResource);
	}

	/**
	 * @Title: updateSySresource
	 * @Description:(修改资源)
	 * @param sysResource
	 * @return
	 */
	@PutMapping
	public Long updateSysResource(SysResource sysResource) {
		return sysResourceService.updateSysResource(sysResource);
	}

	/**
	 * @Title: doDeleteSysResource
	 * @Description:(删除)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDeleteSysResource(@PathVariable Long rowId) {
		return sysResourceService.doDeleteSysResource(rowId);
	}

	/**
	 * @Title: checkRescName
	 * @Description:(检测名称唯一)
	 * @return
	 */
	@GetMapping("/checkname")
	public Integer checkRescName(String rescName, String parentCode) {
		return sysResourceService.checkRescName(rescName, parentCode);
	}

}

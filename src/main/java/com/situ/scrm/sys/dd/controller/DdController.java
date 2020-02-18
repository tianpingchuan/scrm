/**
 * @Company:中享思途   
 * @Title:DdController.java 
 * @Author:Administrator   
 * @Date:2020年2月18日 上午10:49:33     
 */ 
package com.situ.scrm.sys.dd.controller;

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
import com.situ.scrm.sys.dd.domain.Dd;
import com.situ.scrm.sys.dd.service.DdService;

/** 
 * @ClassName:DdController 
 * @Description:(数据字典的Controller层)  
 */
@RestController
@RequestMapping("/dd")
public class DdController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_INDEX_DD = "sys/dd/dd_index";
	private static final String PAGE_ADD_EDIT_DD = "sys/dd/dd_add_edit";
	
	@Autowired
	private DdService ddService;
	
	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX_DD);
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
		modelAndView.addObject("parentKey", Dd.DEFAULT_PARENT_KEY);
		modelAndView.setViewName(PAGE_ADD_EDIT_DD);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: findAllDd 
	 * @Description:(查询全部数据字典信息)
	 * @return
	 */
	@GetMapping("/list")
	public LayResult findAllDd() {
		return ddService.findAllDd();
	}
	
	/**
	 * 
	 * @Title: saveDd 
	 * @Description:(新增数据字典)
	 * @param dd
	 * @return
	 */
	@PostMapping
	public Long saveDd(Dd dd) {
		return ddService.saveDd(dd);
	}
	
	/**
	 * @Title: goAddChildForm
	 * @Description:(进新增子数据字典)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/child/{parentId}")
	public ModelAndView goAddChildForm(@PathVariable Long parentId, ModelAndView modelAndView) {
		// 默认的父类Key
		Dd parentDd = ddService.getDdById(parentId);
		modelAndView.addObject("parentKey", parentDd.getDdKey());
		modelAndView.addObject("parentName", parentDd.getDdDescribe());
		modelAndView.setViewName(PAGE_ADD_EDIT_DD);
		return modelAndView;
	}
	
	/**
	 * @Title: checkDdValue
	 * @Description:(检测字典编码唯一)
	 * @return
	 */
	@GetMapping("/checkddValue")
	public Integer checkDdValue(String ddValue, String parentKey) {
		return ddService.checkDdValue(ddValue, parentKey);
	}
	
	/**
	 * 
	 * @Title: doDeleteDd 
	 * @Description:(删除数据字典)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDeleteDd(@PathVariable Long rowId) {
		return ddService.doDeleteDd(rowId);
	}
	
	/**
	 * 
	 * @Title: getAllDdById 
	 * @Description:(根据id查询用户字典实例的全部信息)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/{rowId}")
	public Dd getAllDdById(@PathVariable Long rowId) {
		Dd dd = ddService.getAllDdById(rowId);
		return dd;
	}
	
	/**
	 * 
	 * @Title: updateDd 
	 * @Description:(执行修改)
	 * @param dd
	 * @return
	 */
	@PutMapping
	public Long updateDd(Dd dd) {
		return ddService.updateDd(dd);
	}
}

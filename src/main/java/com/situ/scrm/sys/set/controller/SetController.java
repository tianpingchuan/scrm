/**
 * @Company:中享思途   
 * @Title:SetController.java 
 * @Author:Administrator   
 * @Date:2020年2月19日 下午5:18:09     
 */ 
package com.situ.scrm.sys.set.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.sys.set.domain.Set;
import com.situ.scrm.sys.set.service.SetService;

/** 
 * @ClassName:SetController 
 * @Description:(系统设置的controller层)  
 */
@RestController
@RequestMapping("/set")
public class SetController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_INDEX_SET = "sys/set/set_index";
	
	@Autowired
	private SetService setService;
	
	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.addObject("set", setService.findSet());
		modelAndView.setViewName(PAGE_INDEX_SET);
		return modelAndView;
	}
	
	@PutMapping
	public Integer updateSet(Set set) {
		return setService.updateSet(set);
	}
}

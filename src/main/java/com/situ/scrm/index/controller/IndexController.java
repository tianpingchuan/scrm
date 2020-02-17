/**
 * @Company:中享思途   
 * @Title:IndexController.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午3:23:18     
 */
package com.situ.scrm.index.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName:IndexController 
 * @Description:(Index的Controller层)  
 */
@RestController
public class IndexController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_INDEX = "index";

	/**
	 * @Title: goIndex 
	 * @Description:(进系统首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping(path = { "/index", "/" })
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX);
		return modelAndView;
	}
}

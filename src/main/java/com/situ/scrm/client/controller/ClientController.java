/**
 * @Company:中享思途   
 * @Title:ClientController.java 
 * @Author:Administrator   
 * @Date:2020年2月17日 下午2:53:18     
 */ 
package com.situ.scrm.client.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.address.service.TheAddressService;

/** 
 * @ClassName:ClientController 
 * @Description:(客户类的Controller层)  
 */
@RestController
@RequestMapping("/client")
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PAGE_CLIENT_INDEX = "client/client_index";
	private static final String PAGE_ROLE_ADD_EDIT = "client/client__add_edit";
	private static final String PAGE_CITY= "client/address_city";
	private static final String PAGE_AREA= "client/address_area";
	
	@Autowired
	private TheAddressService theAddressService;
	
	/**
	 * @Title: goIndex 
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_CLIENT_INDEX);
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
		modelAndView.addObject("theProvinceList", theAddressService.findAllProvince());
		modelAndView.addObject("theCityList", theAddressService.findBeiJing());
		modelAndView.addObject("theAreaList", theAddressService.findBeiJingArea());
		modelAndView.setViewName(PAGE_ROLE_ADD_EDIT);
		return modelAndView;
	}
	
	/**
	 * 
	 * @param modelAndView
	 * @return
	 */
	@PostMapping("/gochange1/{provinceCode}")
	public ModelAndView goSelectcity(ModelAndView modelAndView,@PathVariable Long provinceCode) {
		modelAndView.addObject("theCityList", theAddressService.findCity(provinceCode));
		modelAndView.setViewName(PAGE_CITY);
		return modelAndView;
	}
	/**
	 * 
	 * @param modelAndView
	 * @return
	 */
	@PostMapping("/gochange2/{cityCode}")
	public ModelAndView goSelectcity2(ModelAndView modelAndView,@PathVariable Long cityCode) {
		modelAndView.addObject("theAreaList", theAddressService.findCity(cityCode));
		modelAndView.setViewName(PAGE_AREA);
		return modelAndView;
	}
}

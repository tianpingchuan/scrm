/**
 * @Company:中享思途   
 * @Title:ClientController.java 
 * @Author:Administrator   
 * @Date:2020年2月17日 下午2:53:18     
 */ 
package com.situ.scrm.bus.client.controller;

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

import com.situ.scrm.address.service.TheAddressService;
import com.situ.scrm.bus.client.domain.Client;
import com.situ.scrm.bus.client.service.ClientService;
import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.commons.domain.UploadFile;
import com.situ.scrm.sys.dd.service.DdService;

/** 
 * @ClassName:ClientController 
 * @Description:(客户类的Controller层)  
 */
@RestController
@RequestMapping("/client")
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PAGE_CLIENT_INDEX = "bus/client/client_index";
	private static final String PAGE_Client_ADD_EDIT = "bus/client/client__add_edit";
	private static final String PAGE_CITY= "bus/client/address_city";
	private static final String PAGE_AREA= "bus/client/address_area";
	
	@Autowired
	private TheAddressService theAddressService;
	@Autowired
	private ClientService clientService;
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
		modelAndView.addObject("clientTypeList",ddService.findAllByParentKey("D001"));
		modelAndView.addObject("clientSourceList",ddService.findAllByParentKey("D002"));
		modelAndView.addObject("toIndustryList",ddService.findAllByParentKey("D003"));
		modelAndView.setViewName(PAGE_Client_ADD_EDIT);
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
	
	/**
	 * @Title: checkClientName
	 * @Description:(检测角色名称的唯一)
	 * @param ClientName
	 * @return
	 */
	@GetMapping("/checkname")
	public Integer checkClientName(String clientName) {
		return clientService.checkClientName(clientName);
	}

	/**
	 * @Title: doAddClient
	 * @Description:(执行新增功能)
	 * @param Client
	 * @return
	 */
	@PostMapping
	public Long doAddClient(Client client) {
		return clientService.saveClient(client);
	}

	/**
	 * @Title: findClientByPage
	 * @Description:(根据分页查询数量)
	 * @param page       页号
	 * @param limit      每页显示的数据数量
	 * @param searchClient 查询的条件
	 * @return
	 */
	// http://localhost:8080/layoa/Client/1/10?ClientKind=&ClientName=
	// restful 匹配 http://localhost:8080/layoa/Client/1/10
	@GetMapping("/{page}/{limit}")
	public LayResult findClientByPage(@PathVariable Integer page, @PathVariable Integer limit, Client searchClient) {
		return clientService.findClientByPage(page, limit, searchClient);
	}

	/**
	 * @Title: doDeleteClient
	 * @Description:(执行删除)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDeleteClient(@PathVariable Long rowId) {
		return clientService.doDeleteClient(rowId);
	}

	/**
	 * @Title: goEditClient
	 * @Description:(进修改)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/{rowId}")
	public Client goEditClient(@PathVariable Long rowId) {
		return clientService.getClientById(rowId);
	}

	/**
	 * @Title: doEditClient
	 * @Description:(执行修改)
	 * @param Client
	 * @return
	 */
	@PutMapping
	public Integer doEditClient(Client client) {
		return clientService.doEditClient(client);
	}
	
	@PostMapping("/upload")
	public LayResult doUploadFile (UploadFile file) {
		String src = clientService.doUploadFile(file);
		return new LayResult(0,"",src);
	}
}

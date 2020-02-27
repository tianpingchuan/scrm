/**
 * @Company:中享思途   
 * @Title:ClientService.java 
 * @Author:Administrator   
 * @Date:2020年2月25日 下午5:23:44     
 */ 
package com.situ.scrm.bus.client.service;

import java.util.List;

import com.situ.scrm.bus.client.domain.Client;
import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.commons.domain.UploadFile;

/** 
 * @ClassName:ClientService 
 * @Description:(客户类的Service层)  
 */
public interface ClientService {
	
	/**
	 * @Title: checkClientName
	 * @Description:(检测名称唯一)
	 * @param ClientName
	 * @return
	 */
	Integer checkClientName(String clientName);

	/**
	 * @Title: saveClient
	 * @Description:(新增功能)
	 * @param Client
	 * @return
	 */
	Long saveClient(Client client);

	/**
	 * @Title: doDeleteClient
	 * @Description:(删除功能)
	 * @param rowId
	 * @return
	 */
	Integer doDeleteClient(Long rowId);

	/**
	 * @Title: getClientById
	 * @Description:(根据id查询实例)
	 * @param rowId
	 * @return
	 */
	Client getClientById(Long rowId);

	/**
	 * @Title: doEditClient
	 * @Description:(执行修改)
	 * @param Client
	 * @return
	 */
	Integer doEditClient(Client client);

	/**
	 * @Title: getCount
	 * @Description:(查询出数据的数量)
	 * @return
	 */
	Integer getCount(Client searchClient);

	/**
	 * @Title: findClientByPage
	 * @Description:(根据分页查询数据)
	 * @param page
	 * @param limit
	 * @param searchClient
	 * @return
	 */
	LayResult findClientByPage(Integer page, Integer limit, Client searchClient);

	/**
	 * @Title: findAllClient
	 * @Description:(查询所有的角色信息)
	 * @return
	 */
	List<Client> findAllClient();

	/** 
	 * @Title: doUpload 
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param file
	 * @return  
	 */ 
	String doUploadFile(UploadFile file);

}

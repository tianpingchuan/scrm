/**
 * @Company:中享思途   
 * @Title:ClientService.java 
 * @Author:Administrator   
 * @Date:2020年2月25日 下午4:24:30     
 */ 
package com.situ.scrm.bus.client.dao;

import org.springframework.stereotype.Repository;

import com.situ.scrm.bus.client.domain.Client;
import com.situ.scrm.commons.dao.BaseDao;

/** 
 * @ClassName:ClientService 
 * @Description:(客户类的Dao层)  
 */
@Repository
public interface ClientDao extends BaseDao<Client> {

	/** 
	 * @Title: getByName 
	 * @Description:(通过名称查询实例)
	 * @param clientName
	 * @return  
	 */ 
	Client getByName(String clientName);
	
}

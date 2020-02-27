/**
 * @Company:中享思途   
 * @Title:ClientServiceImpl.java 
 * @Author:Administrator   
 * @Date:2020年2月25日 下午5:35:53     
 */
package com.situ.scrm.bus.client.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.situ.scrm.bus.client.dao.ClientDao;
import com.situ.scrm.bus.client.domain.Client;
import com.situ.scrm.bus.client.service.ClientService;
import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.commons.domain.UploadFile;
import com.situ.scrm.sys.set.dao.SetDao;
import com.situ.scrm.sys.set.domain.Set;
import com.situ.scrm.sys.syscount.util.SysCountUtils;
import com.situ.scrm.utils.DAOUtils;

/**
 * @ClassName:ClientServiceImpl
 * @Description:(客户类的Service层的实现类)
 */
@Service
public class ClientServiceImpl implements ClientService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Value("${spring.servlet.multipart.location}")
	private String fileLocation;

	@Autowired
	private ClientDao clientDao;
	@Autowired
	private SetDao setDao;
	@Autowired
	private SysCountUtils sysCountUtils;

	/**
	 * @Title: checkClientName
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param clientName
	 * @return
	 */
	@Override
	public Integer checkClientName(String clientName) {
		// 根据名称去查询出实例。
		Client client = clientDao.getByName(clientName);
		// 判断如果不等于null 则返回1，表示已经别使用了。
		return client != null ? 1 : 0;
	}

	/**
	 * @Title: saveClient
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param client
	 * @return
	 */
	@Override
	public Long saveClient(Client client) {
		// 得到客户编号
		String clientCode = sysCountUtils.buildClientCode();
		client.setClientCode(clientCode);;
		client.setPublicDate(setDao.get(Set.CONFIG2).toString());
		client.setIfPublic(1);
		client.setActiveFlag(1);
		client.setCreateBy("sys");
		client.setCreateDate(new Date());
		clientDao.save(client);
		return client.getRowId();
	}

	/**
	 * @Title: doDeleteClient
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param rowId
	 * @return
	 */
	@Override
	public Integer doDeleteClient(Long rowId) {
		clientDao.delete(rowId);
		return 1;
	}

	/**
	 * @Title: getClientById
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param rowId
	 * @return
	 */
	@Override
	public Client getClientById(Long rowId) {
		return clientDao.get(rowId);
	}

	/**
	 * @Title: doEditClient
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param client
	 * @return
	 */
	@Override
	public Integer doEditClient(Client client) {
		Long rowId = client.getRowId();
		Client editClient = clientDao.get(rowId);
		client.setUpdateBy("sys");
		client.setUpdateDate(new Date());
		clientDao.update(DAOUtils.buildEditData(editClient, client));
		return 1;
	}

	/**
	 * @Title: getCount
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param searchClient
	 * @return
	 */
	@Override
	public Integer getCount(Client searchClient) {
		return clientDao.getCount(searchClient);
	}

	/**
	 * @Title: findClientByPage
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param limit
	 * @param searchClient
	 * @return
	 */
	@Override
	public LayResult findClientByPage(Integer page, Integer limit, Client searchClient) {
		// 通过反射机制将类的实例中的""重新赋值为null,方便MyBatis中多条件查询SQL语句的拼写
		Client searchParam = DAOUtils.buildSearchParam(searchClient);
		// 查询出符合条件的一共有多少条记录。
		Integer dataCount = clientDao.getCount(searchParam);
		// 根据分页的请求信息查询出数量列表。
		List<Client> roleList = clientDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "", dataCount, roleList);
	}

	/**
	 * @Title: findAllClient
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @return
	 */
	@Override
	public List<Client> findAllClient() {
		return clientDao.find();
	}

	/** 
	 * @Title: doUpload 
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param file
	 * @return  
	 */  
	@Override
	public String doUploadFile(UploadFile file) {
		String filePath = null; //需要保存到数据库中的文件路径
		MultipartFile uploadFile = file.getUploadFile();
		//判断上传来的文件不为空
		if(!uploadFile.isEmpty()) {
			String folder = UploadFile.UPLOAD_ADDRESS;
			String fileName = uploadFile.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			StringBuffer filePathBuffer = new StringBuffer(folder);
			filePathBuffer.append(Calendar.getInstance().getTimeInMillis()).append(suffix);
			//filePathBuffer 写入到数据库
			filePath = filePathBuffer.toString();
			//写入到本地 构建写出文件的绝对路径
			String fullFilePath = fileLocation + filePath;
			File dest = new File(fullFilePath);
			//判断需要写出的文件夹是否存在，不存在则生成
			File parentFile =dest.getParentFile();
			if(!parentFile.exists()) {
				parentFile.mkdirs();
			}try {//写出文件
				uploadFile.transferTo(dest);
			}catch (IOException e ) {
				e.printStackTrace();
			}
			
		}
		
		return filePath;
	}

}

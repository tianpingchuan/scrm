/**
 * @Company:中享思途   
 * @Title:UploadFile.java 
 * @Author:wxinpeng   
 * @Date:2020年2月7日 上午9:26:18     
 */
package com.situ.scrm.commons.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName:UploadFile
 * @Description:(文件上传的通用类)
 */
public class UploadFile implements Serializable {
	private static final long serialVersionUID = 1L;
	// layui默认传输上了的文件名称就叫file
	private MultipartFile uploadFile;

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

}
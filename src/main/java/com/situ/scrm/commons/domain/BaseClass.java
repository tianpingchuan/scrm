/**
 * @Company:中享思途   
 * @Title:BaseClass.java 
 * @Author:wxinpeng   
 * @Date:2020年2月6日 下午4:43:51     
 */
package com.situ.scrm.commons.domain;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName:BaseClass 
 * @Description:(类的基本数据)  
 */
public class BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long rowId;
	private Integer activeFlag;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}

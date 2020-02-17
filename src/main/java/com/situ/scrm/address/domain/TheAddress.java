package com.situ.scrm.address.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * 全国地区总地址
 * 
 * @author Administrator
 *
 */
@Alias("TheAddress")
public class TheAddress extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long areaCode;// 区域号码
	private String areaName;// 区域名称
	private Long parentCode;// 父级区域号
	private Integer areaRunk;// 地区级别
	private Integer hasChild;// 是否有子地区

	public Long getAreaCode() {
		return areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public Long getParentCode() {
		return parentCode;
	}

	public Integer getAreaRunk() {
		return areaRunk;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public void setAreaRunk(Integer areaRunk) {
		this.areaRunk = areaRunk;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

	@Override
	public String toString() {
		return "TheAddress [areaCode=" + areaCode + ", areaName=" + areaName + ", parentCode=" + parentCode
				+ ", areaRunk=" + areaRunk + ", hasChild=" + hasChild + "]";
	}

}

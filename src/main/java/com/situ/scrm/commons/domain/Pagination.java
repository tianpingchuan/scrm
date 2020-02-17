/**
 * @Company:中享思途   
 * @Title:Pagination.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午6:43:19     
 */
package com.situ.scrm.commons.domain;

import java.io.Serializable;

/** 
 * @ClassName:Pagination 
 * @Description:(分页类)  
 */
public class Pagination implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer firstResult;//分页开始的下标
	private Integer maxResults;//分页查询的数量

	public Pagination(Integer firstResult, Integer maxResults) {
		super();
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
}

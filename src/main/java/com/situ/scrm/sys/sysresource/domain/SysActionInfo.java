/**
 * @Company:中享思途   
 * @Title:SysActionInfo.java 
 * @Author:wxinpeng   
 * @Date:2020年2月14日 上午10:23:09     
 */
package com.situ.scrm.sys.sysresource.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * @ClassName:SysActionInfo
 * @Description:(资源动作)
 */
@Alias("SysActionInfo")
public class SysActionInfo extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rescCode;// 资源的CODE
	private String method;// 资源对应的方法
	private String actionUrl;// 资源所对应的URL

	public String getRescCode() {
		return rescCode;
	}

	public void setRescCode(String rescCode) {
		this.rescCode = rescCode;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
}

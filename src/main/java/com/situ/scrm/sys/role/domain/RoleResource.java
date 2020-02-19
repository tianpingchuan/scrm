/**
 * @Company:中享思途   
 * @Title:RoleResource.java 
 * @Author:wxinpeng   
 * @Date:2020年2月15日 下午3:31:30     
 */
package com.situ.scrm.sys.role.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * @ClassName:RoleResource
 * @Description:(角色资源关联表)
 */
@Alias("RoleResource")
public class RoleResource extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long roleId;// 角色主键
	private String rescCode;// 资源CODE

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRescCode() {
		return rescCode;
	}

	public void setRescCode(String rescCode) {
		this.rescCode = rescCode;
	}
}

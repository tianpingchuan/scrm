/**
 * @Company:中享思途   
 * @Title:Role.java 
 * @Author:wxinpeng   
 * @Date:2020年2月6日 下午4:43:24     
 */
package com.situ.scrm.sys.role.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

/** 
 * @ClassName:Role 
 * @Description:(角色信息)  
 */
@Alias("Role")
public class Role extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	private String roleCode;//角色编号
	private String roleName;//角色名称 #要求唯一
	private Integer roleKind;//角色类型#1:超级角色;0:普通角色;
	private String roleInfo;//角色简介

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleKind() {
		return roleKind;
	}

	public void setRoleKind(Integer roleKind) {
		this.roleKind = roleKind;
	}

	public String getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(String roleInfo) {
		this.roleInfo = roleInfo;
	}
}

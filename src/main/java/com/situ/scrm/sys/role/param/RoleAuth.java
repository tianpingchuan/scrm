/**
 * @Company:中享思途   
 * @Title:RoleSetAuth.java 
 * @Author:wxinpeng   
 * @Date:2020年2月17日 下午1:05:35     
 */
package com.situ.scrm.sys.role.param;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:RoleSetAuth
 * @Description:(角色设置Param)
 */
public class RoleAuth implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long roleId;
	private List<String> rescCodeList;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<String> getRescCodeList() {
		return rescCodeList;
	}

	public void setRescCodeList(List<String> rescCodeList) {
		this.rescCodeList = rescCodeList;
	}
}

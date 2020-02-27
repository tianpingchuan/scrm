package com.situ.scrm.sys.user.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * 
 * @ClassName:User
 * @Description:(员工用户类)
 */
@Alias("User")
public class User extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int IS_LOCK_YES = 1;
	public static final int IS_LOCK_NO = 0;

	private Long userRole;// 员工角色，绑定角色类的Id
	private Long parentId;// 上级员工的ID一级员工上级id是0
	private String userName;// 员工名称
	private String userCode;// 登录账号
	private String userPass;// 登录密码
	private String userPhone;// 手机号码
	private String userAvatar;// 用户头像
	private Integer userKind;// 员工级别：一级：上级是公司；二级：上级是一级员工；三级：上级是二级员工
	private Integer isLock;// 是否锁定#1:是;0:否;
	private Integer loginCount;// 登陆次数
	private String loginIp;// 最后登录IP
	private Date loginDate;// 最后登录时间

//	额外的信息
	private String userRoleName;// 角色名称

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public Long getUserRole() {
		return userRole;
	}

	public void setUserRole(Long userRole) {
		this.userRole = userRole;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public Integer getUserKind() {
		return userKind;
	}

	public void setUserKind(Integer userKind) {
		this.userKind = userKind;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * @Title: toString
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @return
	 */
	@Override
	public String toString() {
		return "User [userRole=" + userRole + ", parentId=" + parentId + ", userName=" + userName + ", userCode="
				+ userCode + ", userPass=" + userPass + ", userPhone=" + userPhone + ", userAvatar=" + userAvatar
				+ ", userKind=" + userKind + ", isLock=" + isLock + ", loginCount=" + loginCount + ", loginIp="
				+ loginIp + ", loginDate=" + loginDate + "]";
	}

}

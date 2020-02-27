package com.situ.scrm.utils;

import java.io.Serializable;

public class ConfigUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	管理员登录用户放在session中的实例名
	public static final String LOGIN_USER = "user";
	
//	管理员cookie名
	public static final String COOKIE_NAME = "WWW.SCRM";
	
	public static final String TO_BASE_FILE = "E:/webfiles/";

	public static final String SESSION_RESOURCE_MAP_ROLE = "actionInfoMap";
	
	// 是否是超级管理员(放置在session中的信息)
	public static final String SESSION_IS_SUPPER = "is_supper";
}

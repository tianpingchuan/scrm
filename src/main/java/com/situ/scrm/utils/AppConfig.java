/**
 * @Company:中享思途   
 * @Title:AppConfig.java 
 * @Author:wxinpeng   
 * @Date:2020年2月13日 下午12:58:31     
 */
package com.situ.scrm.utils;

import java.io.Serializable;

/**
 * @ClassName:AppConfig
 * @Description:(系统配置的信息)
 */
public class AppConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	// 是否是超级管理员(放置在session中的信息)
	public static final String SESSION_IS_SUPPER = "is_supper";
	// 角色相关的资源信息 (放置在session中)
	public static final String SESSION_RESOURCE_MAP_ROLE = "resource_map_role";
}

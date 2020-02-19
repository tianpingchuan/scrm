/**
 * @Company:中享思途   
 * @Title:Set.java 
 * @Author:Administrator   
 * @Date:2020年2月19日 下午3:39:53     
 */
package com.situ.scrm.sys.set.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * @ClassName:Set
 * @Description:(系统设置类)
 */
@Alias("Set")
public class Set extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String CONFIG1 = "CONFIG1";
	public static final String CONFIG2 = "CONFIG2";
	public static final String CONFIG3 = "CONFIG3";
	
	private String config1;// 公司名称
	private String config2;// 公海天数
	private String config3;// 提醒日期
	private String config4;// 预留
	private String config5;// 预留
	private String config6;// 预留

	public String getConfig1() {
		return config1;
	}

	public void setConfig1(String config1) {
		this.config1 = config1;
	}

	public String getConfig2() {
		return config2;
	}

	public void setConfig2(String config2) {
		this.config2 = config2;
	}

	public String getConfig3() {
		return config3;
	}

	public void setConfig3(String config3) {
		this.config3 = config3;
	}

	public String getConfig4() {
		return config4;
	}

	public void setConfig4(String config4) {
		this.config4 = config4;
	}

	public String getConfig5() {
		return config5;
	}

	public void setConfig5(String config5) {
		this.config5 = config5;
	}

	public String getConfig6() {
		return config6;
	}

	public void setConfig6(String config6) {
		this.config6 = config6;
	}

	/**
	 * @Title: toString
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @return
	 */
	@Override
	public String toString() {
		return "Set [config1=" + config1 + ", config2=" + config2 + ", config3=" + config3 + ", config4=" + config4
				+ ", config5=" + config5 + ", config6=" + config6 + "]";
	}

}

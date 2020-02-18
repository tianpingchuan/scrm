/**
 * @Company:中享思途   
 * @Title:Dd.java 
 * @Author:Administrator   
 * @Date:2020年2月18日 上午9:33:47     
 */
package com.situ.scrm.sys.dd.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * @ClassName:Dd
 * @Description:(数据字典类)
 */
@Alias("Dd")
public class Dd extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_PARENT_KEY = "D000";

	private String parentKey;// 父类key
	private String ddKey;// 字典key
	private Integer ddValue;// 字典的值
	private String ddDescribe;// 字典的描述
	private Integer ddOrder;// 排序
	private Integer hasChild;// 是否有子数据

//	额外的数据
	private List<Dd> children;// 子类的集合
	private String parentValue;// 父类的值

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getDdKey() {
		return ddKey;
	}

	public void setDdKey(String ddKey) {
		this.ddKey = ddKey;
	}

	public Integer getDdValue() {
		return ddValue;
	}

	public void setDdValue(Integer ddValue) {
		this.ddValue = ddValue;
	}

	public String getDdDescribe() {
		return ddDescribe;
	}

	public void setDdDescribe(String ddDescribe) {
		this.ddDescribe = ddDescribe;
	}

	public Integer getDdOrder() {
		return ddOrder;
	}

	public void setDdOrder(Integer ddOrder) {
		this.ddOrder = ddOrder;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

	public List<Dd> getChildren() {
		return children;
	}

	public void setChildren(List<Dd> children) {
		this.children = children;
	}

	public String getParentValue() {
		return parentValue;
	}

	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @Title: toString
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @return
	 */
	@Override
	public String toString() {
		return "Dd [parentKey=" + parentKey + ", ddKey=" + ddKey + ", ddValue=" + ddValue + ", ddDescribe=" + ddDescribe
				+ ", ddOrder=" + ddOrder + ", hasChild=" + hasChild + ", children=" + children + ", parentValue="
				+ parentValue + "]";
	}

}

/**
 * @Company:中享思途   
 * @Title:SysResource.java 
 * @Author:wxinpeng   
 * @Date:2020年2月13日 下午2:27:25     
 */
package com.situ.scrm.sys.sysresource.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * @ClassName:SysResource
 * @Description:(系统资源)
 */
@Alias("SysResource")
public class SysResource extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PARENT_CODE = "S000";// 默认的一级资源的CODE
	private String parentCode;// 父类ID
	private String rescCode;// 资源CODE
	private String rescName;// 资源名称
	private Integer rescType;// 资源类型#1:菜单;0:功能;
	private Integer rescOrder;// 排序
	private String menuUrl;// 菜单URL
	private String rescIcon;// 菜单图标
	private Integer hasChild;// 是否有子数据#1:是;0:否;

	// 额外的信息 -开始
	private List<SysResource> children;// 自元素的数据
	private List<SysActionInfo> actionInfoList; // 对应的动作集合
	private String parentName;// 父类资源的名称

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<SysResource> getChildren() {
		return children;
	}

	public void setChildren(List<SysResource> children) {
		this.children = children;
	}

	public List<SysActionInfo> getActionInfoList() {
		return actionInfoList;
	}

	public void setActionInfoList(List<SysActionInfo> actionInfoList) {
		this.actionInfoList = actionInfoList;
	}

	// 额外的信息 -结束
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getRescCode() {
		return rescCode;
	}

	public void setRescCode(String rescCode) {
		this.rescCode = rescCode;
	}

	public String getRescName() {
		return rescName;
	}

	public void setRescName(String rescName) {
		this.rescName = rescName;
	}

	public Integer getRescType() {
		return rescType;
	}

	public void setRescType(Integer rescType) {
		this.rescType = rescType;
	}

	public Integer getRescOrder() {
		return rescOrder;
	}

	public void setRescOrder(Integer rescOrder) {
		this.rescOrder = rescOrder;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getRescIcon() {
		return rescIcon;
	}

	public void setRescIcon(String rescIcon) {
		this.rescIcon = rescIcon;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
}

/**
 * @Company:中享思途   
 * @Title:LayTree.java 
 * @Author:wxinpeng   
 * @Date:2020年2月15日 下午3:21:04     
 */
package com.situ.scrm.commons.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:LayTree
 * @Description:(Layui树形结构的数据)
 */
public class LayTree implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;// 节点标题 String 未命名
	private String id;// 节点唯一索引值，用于对指定节点进行各类操作 String/Number 任意唯一的字符或数字
	private Boolean spread;// 节点是否初始展开，默认 false Boolean true
	private Boolean checked;// 是否默认选中
	private List<LayTree> children;// 子节点。支持设定选项同父节点 Array [{title: '子节点1', id: '111'}]
	private Integer hasChild;
	private Long rowId;

	public LayTree(String title, String id, Boolean spread, Boolean checked, Integer hasChild, Long rowId) {
		super();
		this.title = title;
		this.id = id;
		this.checked = checked;
		this.spread = spread;
		this.hasChild = hasChild;
		this.rowId = rowId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getSpread() {
		return spread;
	}

	public void setSpread(Boolean spread) {
		this.spread = spread;
	}

	public List<LayTree> getChildren() {
		return children;
	}

	public void setChildren(List<LayTree> children) {
		this.children = children;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

}

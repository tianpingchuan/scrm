<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="layui-form" lay-filter="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId" />
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">父级资源</label>
			<div class="layui-input-inline" style="width: 100px;">
				<div class="layui-form-mid layui-word-aux">
					<span class="layui-badge layui-bg-cyan" id="parentName"
						style="width: 100px"><c:out value="${parentName}"
							default="一级数据字典"></c:out></span> <input type="hidden" name="parentKey"
						id="parentKey" value="${parentKey}" />
				</div>
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">字典编码</label>
			<div class="layui-input-inline" style="width: 300px;">
				<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
				<!-- class ='check-unique' 放置在此处用于通用JS脚本修改的时候使用-->
				<input name="ddValue" lay-verify="required|checkddValue" id="ddValue"
					class="layui-input check-unique" placeholder="请输入字典的值"
					autocomplete="off">
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline" id="div_menuUrl">
			<label class="layui-form-label">字典的描述</label>
			<div class="layui-input-inline" style="width: 140px;">
				<input name="ddDescribe" id="ddDescribe" class="layui-input"
					lay-verify="required" placeholder="请输入字典的描述" autocomplete="off">
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_resource_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
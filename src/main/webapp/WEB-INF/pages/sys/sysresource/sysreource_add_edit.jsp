<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="layui-form" lay-filter="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId" />
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">父级资源</label>
			<div class="layui-input-inline" style="width: 100px;">
				<div class="layui-form-mid layui-word-aux">
					<span class="layui-badge layui-bg-cyan" id="parentName"><c:out value="${parentName}" default="一级目录"></c:out></span>
					<input type="hidden" name="parentCode" id="parentCode" value="${parentCode}"/>
				</div>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">资源名称</label>
			<div class="layui-input-inline"  style="width: 300px;">
				<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
				<!-- class ='check-unique' 放置在此处用于通用JS脚本修改的时候使用-->
				<input name="rescName" lay-verify="required|checkrescname" id="rescName" class="layui-input check-unique" placeholder="请输入资源名称" autocomplete="off">
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">资源类型</label>
			<div class="layui-input-inline" style="width: 100px;">
				<select name="rescType" lay-filter="filter_rescType">
					<option value="1">菜单</option>
					<option value="0">功能</option>
				</select>
			</div>
		</div>
		<div class="layui-inline" id="div_rescIcon">
			<label class="layui-form-label">菜单图标</label>
			<div class="layui-input-inline" style="width: 100px;">
				<input name="rescIcon" id="rescIcon" lay-filter="rescIcon" class="layui-hide">
			</div>
		</div>
		<div class="layui-inline" id="div_menuUrl">
			<label class="layui-form-label">菜单URL</label>
			<div class="layui-input-inline" style="width: 140px;">
				<input name="menuUrl" id="menuUrl" class="layui-input" placeholder="请输入资源URL" autocomplete="off">
			</div>
		</div>
	</div>
	<div class="layui-form-item padding-left-30">
		<fieldset class="layui-elem-field">
			<legend>动作信息</legend>
			<button id="btn_action-add" class="layui-btn layui-btn-sm layui-btn-normal" style="position: absolute; right: 40px; top: 130px;">
				<i class="layui-icon layui-icon-addition"></i>
			</button>
			<input type="hidden" id="hide_index" value="0" />
			<div class="layui-field-box" id="action_info_box">
				
			</div>
		</fieldset>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_resource_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<script>
	layui.use(['iconPicker'],function(){
		var iconPicker = layui.iconPicker;
		 iconPicker.render({
	            // 选择器，推荐使用input
	            elem: '#rescIcon',
	            // 数据类型：fontClass/unicode，推荐使用fontClass
	            type: 'fontClass',
	            // 是否开启搜索：true/false，默认true
	            search: true,
	            // 是否开启分页：true/false，默认true
	            page: true,
	            // 每页显示数量，默认12
	            limit: 12,
	            // 点击回调
	            click: function (data) {
	                //console.log(data);
	            },
	            // 渲染成功后的回调
	            success: function(d) {
	               // console.log(d);
	            }
	        });
	});
</script>
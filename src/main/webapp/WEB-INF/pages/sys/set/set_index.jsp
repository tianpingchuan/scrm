<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<fieldset class="layui-elem-field layui-field-title"
	style="margin-top: 20px;">
	<legend>系统设置</legend>
</fieldset>
<form class="layui-form" lay-filter="form_set_add" id="form_set">
	<div class="layui-form-item">
		<label class="layui-form-label">公司名称</label>
		<div class="layui-input-inline">
			<input type="text" name="config1" lay-verify="title" autocomplete="off"
				value="${set.config1}" class="layui-input">
		</div>
		<div class="layui-form-mid layui-word-aux">请输入公司名称</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">公海天数</label>
		<div class="layui-input-inline" style="width: 50px;">
			<input type="text" name="config2" lay-verify="title" autocomplete="off"
				value="${set.config2}" class="layui-input">
		</div>
		<label class="layui-form-label" style="text-align: left;">天</label>
		<div class="layui-form-mid layui-word-aux">最后跟单超过这个时间，客户转成公海状态，进入客户池</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">跟单提醒</label>
		<div class="layui-input-inline" style="width: 50px;">
			<input type="text" name="config3" lay-verify="title" autocomplete="off"
				value="${set.config3}" class="layui-input">
		</div>
		<label class="layui-form-label" style="text-align: left;">天</label>
		<div class="layui-form-mid layui-word-aux">最后一次跟单后，到达这个天数进行提醒</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" type="button" lay-submit lay-filter="but_set_submit">确认保存</button>
		</div>
	</div>
</form>
<!-- 引入自定义的JS脚本 -->
<script type="text/javascript" src="assert/pages/js/sys/set.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="layui-form" lay-filter="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId" />
	<div class="layui-form-item">
		<label class="layui-form-label">客户名称</label>
		<div class="layui-input-block">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<!-- class ='check-unique' 放置在此处用于通用JS脚本修改的时候使用-->
			<input name="roleName" lay-verify="required" id="roleName"
				class="layui-input check-unique" placeholder="请输入角色名称"
				autocomplete="off">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">省份</label>
		<div class="layui-input-block">
			<select name="provinceCode" id="provinceCode"
				lay-filter="search_provinceCode">
				<c:if test="${!empty theProvinceList}">
					<c:forEach items="${theProvinceList}" var="theAddress">
						<option value="${theAddress.areaCode}">${theAddress.areaName}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">市</label>
		<div class="layui-input-block" id="to_city">
			<select name="cityCode" id="cityCode"
				lay-filter="search_cityCode">
				<c:if test="${!empty theCityList}">
					<c:forEach items="${theCityList}" var="theAddress">
						<option value="${theAddress.areaCode}">${theAddress.areaName}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">县区</label>
		<div class="layui-input-block" id="to_area">
			<select name="areaCode" id="areaCode">
				<c:if test="${!empty theAreaList}">
					<c:forEach items="${theAreaList}" var="theAddress">
						<option value="${theAddress.areaCode}">${theAddress.areaName}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">备注</label>
		<div class="layui-input-block">
			<textarea name="roleInfo" lay-verify="required" placeholder="请输入角色信息"
				class="layui-textarea"></textarea>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<script>
	//在页面渲染成功后，使用layui的use方法按需加载需要用的各个模块。
	layui.use([ 'table', 'form' ], function() {
		// layui是基于jQuery的,启用$符号。
		var $ = layui.$;
		var form = layui.form;
		var table = layui.table;

		form.on('select(search_provinceCode)', function(data) {
			var provinceCode = data.value;
			$('#cityCode').html('');
			$.ajax({
				type : 'post',
				url : 'client/gochange1/' + provinceCode,
				success : function(htmlData) {
					if (htmlData) {
						$('#cityCode').empty();
						$('#cityCode').html(htmlData);
						var cityCode = $('#cityCode').val();
						form.render('select');//select是固定写法 不是选择器
						$.ajax({
							type : 'post',
							url : 'client/gochange2/' + cityCode,
							success : function(htmlData) {
								if (htmlData) {
									$('#areaCode').empty();
									$('#areaCode').html(htmlData);
									form.render('select');//select是固定写法 不是选择器
								}
							}
						});
					}
				}
			});
		});
		
		form.on('select(search_cityCode)', function(data) {
			// 要触发的事件
			var cityCode = data.value;
			$.ajax({
				type : 'post',
				url : 'client/gochange2/' + cityCode,
				success : function(htmlData) {
					if (htmlData) {
						$('#areaCode').empty();
						$('#areaCode').html(htmlData);
						form.render('select');//select是固定写法 不是选择器
					}
				}
			});
		});
		
	});
</script>
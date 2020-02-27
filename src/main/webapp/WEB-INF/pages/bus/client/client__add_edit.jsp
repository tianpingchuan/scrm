<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="layui-form" lay-filter="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId" />
	<div class="layui-form-item">
		<label class="layui-form-label">客户名称</label>
		<div class="layui-input-inline">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<!-- class ='check-unique' 放置在此处用于通用JS脚本修改的时候使用-->
			<input name="clientName" lay-verify="required|checkclientname"
				id="clientName" class="layui-input check-unique"
				placeholder="请输入客户名称" autocomplete="off">
		</div>
		<label class="layui-form-label">手机号码</label>
		<div class="layui-input-inline">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<!-- class ='check-unique' 放置在此处用于通用JS脚本修改的时候使用-->
			<input name="clientPhone" lay-verify="required" class="layui-input"
				placeholder="请输入手机号码" autocomplete="off">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">联系人</label>
		<div class="layui-input-inline">
			<input name="linkMan" lay-verify="required" class="layui-input"
				placeholder="请输入联系人" autocomplete="off">
		</div>
		<label class="layui-form-label">联系QQ</label>
		<div class="layui-input-inline">
			<input name="qqNumber" lay-verify="required" class="layui-input"
				placeholder="请输入联系QQ" autocomplete="off">
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">电子邮箱</label>
		<div class="layui-input-block">
			<input name="emailNumber" lay-verify="required" class="layui-input"
				placeholder="请输入联系邮箱" autocomplete="off">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">所在区域</label>
		<div class="layui-input-inline">
			<select name="proviceCode" id="provinceCode"
				lay-filter="search_provinceCode">
				<c:if test="${!empty theProvinceList}">
					<c:forEach items="${theProvinceList}" var="theAddress">
						<option value="${theAddress.areaCode}">${theAddress.areaName}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
		<div class="layui-input-inline" id="to_city">
			<select name="cityCode" id="cityCode" lay-filter="search_cityCode">
				<c:if test="${!empty theCityList}">
					<c:forEach items="${theCityList}" var="theAddress">
						<option value="${theAddress.areaCode}">${theAddress.areaName}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
		<div class="layui-input-inline" id="to_area">
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
		<label class="layui-form-label">详细地址</label>
		<div class="layui-input-block">
			<input name="trueAddress" lay-verify="required" class="layui-input"
				placeholder="请输入详细地址" autocomplete="off">
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">客户类型</label>
		<div class="layui-input-inline">
			<select name="clientType">
				<c:if test="${!empty clientTypeList}">
					<c:forEach items="${clientTypeList}" var="dd">
						<option value="${dd.rowId}">${dd.ddDescribe}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
		<label class="layui-form-label">客户来源</label>
		<div class="layui-input-inline">
			<select name="clientSource">
				<c:if test="${!empty clientSourceList}">
					<c:forEach items="${clientSourceList}" var="dd">
						<option value="${dd.rowId}">${dd.ddDescribe}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">所属行业</label>
		<div class="layui-input-inline">
			<select name="toIndustry">
				<c:if test="${!empty toIndustryList}">
					<c:forEach items="${toIndustryList}" var="dd">
						<option value="${dd.rowId}">${dd.ddDescribe}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">客户级别</label>
		<div class="layui-input-inline">
			<input type="hidden" name="clientKind" id="clientKind" value="2" />
			<div id="test2"></div>
		</div>
		<button type="button" class="layui-btn" id="test11">
			<i class="layui-icon">&#xe67c;</i>上传图片
		</button>
		<div class="layui-inline layui-word-aux" id="cust_upload_word">请上传跟单的附件</div>
		<input type="hidden" name="accessoryBag" id="accessoryBag" />
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">备注信息</label>
		<div class="layui-input-block">
			<textarea name="otherComment" lay-verify="required"
				placeholder="请输入备注信息" class="layui-textarea"></textarea>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="layui-form" lay-filter="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId" />
	<div class="layui-form-item">
		<label class="layui-form-label">员工角色</label>
		<div class="layui-input-block">
			<select name="userRole">
				<c:if test="${!empty roleList}">
					<c:forEach items="${roleList}" var="role">
						<option value="${role.rowId}">${role.roleName}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">员工级别</label>
		<div class="layui-input-inline">
			<select name="userKind" lay-filter="search_parentId">
				<option value="1">一级</option>
				<option value="2">二级</option>
				<option value="3">三级</option>
			</select>
		</div>
		<label class="layui-form-label">上级员工</label>
		<div class="layui-input-inline">
			<select name="parentId" id="parentId">
				<c:if test="${!empty userList}">
					<c:forEach items="${userList}" var="user">
						<option value="${user.rowId}">${user.userName}</option>
					</c:forEach>
				</c:if>
				<c:if test="${!empty setList}">
					<option value="0">${setList.config1}</option>
				</c:if>
			</select>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">员工名称</label>
		<div class="layui-input-block">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<input name="userName" lay-verify="required" placeholder="请输入用户名称"
				autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">登录账号</label>
		<div class="layui-input-block">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<input name="userCode" lay-verify="required|checkusercode"
				id="userCode" placeholder="请输入登录账号" autocomplete="off"
				class="layui-input check-unique">
		</div>
	</div>
	<div class="layui-form-item layui-form-text" id="passpass">
		<label class="layui-form-label">登录密码</label>
		<div class="layui-input-block">
			<input type="password" name="userPass" lay-verify="required"
				placeholder="请输入登录密码" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 引入自定义的权限管理标签 -->
<%@ taglib prefix="auth" uri="/auth-tags"%>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					权限设置--${role.roleName}
					<button type="button" class="layui-btn layui-btn-sm btn-left" onclick="goBackRole();">
						<i class="layui-icon layui-icon-return"></i>返回
					</button>
					<button type="button" class="layui-btn layui-btn-sm btn-left" id="btn_set_auth">
						<i class="layui-icon layui-icon-auz"></i>设置权限
					</button>
				</div>
				<input type="hidden" id="rowId" value="${role.rowId}"/>
				<div class="layui-card-body">
					<div class="layui-row" id="tree_set_auth_div">
						<c:if test="${!empty resourceIdList}">
						<c:forEach items="${resourceIdList}" var="item">
						<div class="layui-col-xs3">
							<div id="auth${item}"></div>
						</div>
						</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="assert/pages/js/sys/role_setauth.js"></script>
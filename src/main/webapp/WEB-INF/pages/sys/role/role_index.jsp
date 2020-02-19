<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入自定义的权限管理标签 -->
<%@ taglib prefix="auth" uri="/auth-tags" %>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					角色管理
					<!-- 使用自定义标签在页面上判断 button或是超链接 是否可以显示 -->
					<%-- <auth:have url="role" method="post"> --%>
					<!-- 新增按钮 开始 -->
					<button type="button" class="layui-btn layui-btn-sm layui-btn-add">
						<i class="layui-icon layui-icon-addition"></i>新增
					</button>
					<%-- </auth:have> --%>
					<!-- 新增按钮 结束 -->
				</div>
				<div class="layui-card-body">
					<!-- 搜索表单 开始  -->
					<form class="layui-form" id="form_search" lay-filter="form_search">
						<div class="layui-search-form">
							<div class="layui-inline">
								<select name="roleKind">
									<option value=>角色类型</option>
									<option value="1">超级角色</option>
									<option value="0">普通角色</option>
								</select>
							</div>
							<div class="layui-inline">
								<input name="roleName" placeholder="角色名称" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<!-- 搜索按钮 -开始 -->
								<button lay-submit lay-filter="btn_search" title="搜索" class="layui-btn layui-btn-primary layui-btn-sm layui-tips" >
									<i class="layui-icon layui-icon-search"></i>
								</button>
								<!-- 搜索按钮 -结束 -->
								<!-- 重置按钮 - 开始 -->
								<!--想自动弹出tip信息 两个条件  class="layui-tips" title="信息" -->
								<button type="reset" title="重置" class="layui-btn layui-btn-primary layui-btn-sm layui-tips">
									<i class="layui-icon layui-icon-refresh"></i>
								</button>
								<!-- 重置按钮 - 结束 -->
							</div>
						</div>
					</form>
					<!-- 搜索表单 结束  -->
					<!-- 页面表格 开始  -->
					<table id="list_table" lay-filter="filter_table"></table>
					<!-- 页面表格 结束  -->
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="hideURL" value="role"/>
<input type="hidden" id="hideTitle" value="角色"/>
<!-- 修改，删除 按钮 -->
<!-- 此处注意：必须有lay-event 才能通过table.on完成事件的绑定 -->
<script type="text/html" id="roleBtnTpl">
{{# if(d.roleKind ==1){ }}
	<a class="layui-btn layui-btn-xs layui-btn-disabled" >设置权限</a>
<%--<auth:have url="role" method="put">--%>
	<a class="layui-btn layui-btn-xs layui-btn-disabled">修改</a>
<%--</auth:have>--%>
	<a class="layui-btn layui-btn-xs layui-btn-disabled">删除</a>
{{# }else{  }}
<%--<auth:have url="role" method="put">--%>
	<a class="layui-btn layui-btn-xs" lay-event="set_resource">设置权限</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
<%--</auth:have>--%>
	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
 {{#  } }}
</script>
<!-- layui 定义的模板数据 -->
<script type="text/html" id="roleKindTpl">
 {{# if(d.roleKind ==1){ }}
    <span class="layui-badge layui-bg-danger">超级角色</span>
  {{#  } else { }}
    <span class="layui-badge layui-bg-cyan">普通角色</span>
  {{#  } }}
</script>
<!-- 引入自定义的JS脚本 -->
<script type="text/javascript" src="assert/pages/js/sys/role.js"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入自定义的权限管理标签 -->
<%@ taglib prefix="auth" uri="/auth-tags" %>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					用户管理
					<!-- 使用自定义标签在页面上判断 button或是超链接 是否可以显示 -->
					<auth:have url="user" method="post">
					<!-- 新增按钮 开始 -->
					<button type="button" class="layui-btn layui-btn-sm layui-btn-add">
						<i class="layui-icon layui-icon-addition"></i>新增
					</button>
					</auth:have>
					<!-- 新增按钮 结束 -->
				</div>
				<div class="layui-card-body">
					<!-- 搜索表单 开始  -->
					<form class="layui-form" id="form_search" lay-filter="form_search">
						<div class="layui-search-form">
							<div class="layui-inline">
								<select name="userType">
									<option value>员工类型</option>
									<option value="1">超级员工</option>
									<option value="0">普通员工</option>
								</select>
							</div>
							<div class="layui-inline">
								<select name="userKind">
									<option value>员工级别</option>
									<option value="1">一级</option>
									<option value="2">二级</option>
									<option value="3">三级</option>
								</select>
							</div>
							<div class="layui-inline">
								<input name="userName" placeholder="员工名称" autocomplete="off"
									class="layui-input">
							</div>
							<div class="layui-inline">
								<!-- 搜索按钮 -开始 -->
								<button class="layui-btn layui-btn-primary layui-btn-sm"
									lay-submit lay-filter="btn_search">
									<i class="layui-icon layui-icon-search"></i>
								</button>
								<!-- 搜索按钮 -结束 -->
								<!-- 重置按钮 - 开始 -->
								<button type="reset"
									class="layui-btn layui-btn-primary layui-btn-sm">
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
<!-- 页面上的隐藏域，提供信息给通用的JS脚本使用 -->
<input type="hidden" id="hideURL" value="user" />
<input type="hidden" id="hideTitle" value="员工" />
<!-- 修改，删除 按钮 -->
<!-- 此处注意：必须有lay-event 才能通过table.on完成事件的绑定 -->
<script type="text/html" id="roleBtnTpl">
<auth:have url="user" method="put">
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
</auth:have>
{{# if(d.userType ==1){ }}
<auth:have url="user" method="delete">
	<button type="button" class="layui-btn layui-btn-xs layui-btn-disabled">删除</button>
</auth:have>
{{# }else{  }}
<auth:have url="user" method="delete">
	<button type="button" class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</button>
</auth:have>
{{#  } }}
</script>
<!-- layui 定义的模板数据 -->
<script type="text/html" id="roleKindTpl">
 {{# if(d.userType ==1){ }}
    <span class="layui-badge layui-bg-danger">超级员工</span>
  {{#  } else { }}
    <span class="layui-badge layui-bg-cyan">普通普通</span>
  {{#  } }}
</script>
<script type="text/html" id="userKindTpl">
{{# if(d.userKind ==1){ }}
    <span class="layui-badge layui-bg-cyan">一级</span>
{{# } else if(d.userKind == 2) { }}
    <span class="layui-badge layui-bg-blue">二级</span>
{{#  } else { }}
    <span class="layui-badge layui-bg-green">三级</span>
{{#  } }}
</script>
<!-- 表格状态列 -->
<script type="text/html" id="demoTreeTableState1">
    <input type="checkbox" lay-filter="ckState" data-rowId="{{d.rowId}}" value="{{d.isLock}}" lay-skin="switch"
           lay-text="正常|锁定" {{d.isLock==0?'checked':''}}/>
</script>
<!-- 引入自定义的JS脚本 -->
<script src="assert/pages/js/sys/user.js"></script>

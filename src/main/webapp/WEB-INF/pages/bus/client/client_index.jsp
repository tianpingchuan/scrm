<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					客户管理
					<!-- 使用自定义标签在页面上判断 button或是超链接 是否可以显示 -->
					<!-- 新增按钮 开始 -->
					<!-- data-callback 定义当form页面弹出层后，进行回调的方法名称-->
					<button type="button" class="layui-btn layui-btn-sm layui-btn-add" data-callback="initForm()">
						<i class="layui-icon layui-icon-addition"></i>新增
					</button>
					<!-- 新增按钮 结束 -->
				</div>
				<div class="layui-card-body">
					<!-- 页面表格 开始  -->
					<table id="list_table" lay-filter="filter_table"></table>
					<!-- 页面表格 结束  -->
				</div>
			</div>
		</div>
	</div>
</div>

<input type="hidden" id="hideURL" value="client"/>
<input type="hidden" id="hideTitle" value="客户"/>
<!-- 表格操作列 -->
<script type="text/html" id="resourceBtnTpl">
    <a class="layui-btn  layui-btn-xs" lay-event="edit" data-callback="initForm()">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>
<script type="text/html" id="roleKindTpl">
 {{# if(d.ifPublic ==1){ }}
    <span class="layui-badge layui-bg-danger">公海客户</span>
  {{#  } else { }}
    <span class="layui-badge layui-bg-cyan">非公海客户</span>
  {{#  } }}
</script>
<!-- 引入自定义的JS脚本 -->
<script type="text/javascript" src="assert/pages/js/bus/client.js"></script>
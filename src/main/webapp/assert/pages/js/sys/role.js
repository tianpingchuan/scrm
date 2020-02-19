//在页面渲染成功后，使用layui的use方法按需加载需要用的各个模块。
layui.use([ 'table', 'form' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	var form = layui.form;
	var table = layui.table;
	//自定义表单校验
	form.verify({
		checkrolename:function(value, item){ //value：表单的值、item：表单的DOM对象
			//调用通用的唯一性校验的方法 #调用这个方法 一定要前面带 return
			return checkUnique(value,item,'role/checkname');
		}
	});
	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'role', // 数据接口 layui会自动封装成  role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols: [[ //表头
		      {field: 'rowId', title: 'ID', width:80, sort: true, fixed: 'left'}
		      ,{field: 'roleKind', title: '角色类型', width:180,templet:'#roleKindTpl'}
		      ,{field: 'roleName', title: '角色名称', width:180}
		      ,{field: 'roleInfo', title: '角色信息'} //此次不设置width 让其自动适应
		      ,{title: '操作', width: 200,templet:'#roleBtnTpl'}
		    ]]
	});
	
	//进入 设置权限
	table.on('tool(filter_table)', function(obj) {
		var event = obj.event;
		//通过data将要修改的数据的主键 取出
		var rowId = obj.data.rowId;
		switch (event) {
			case 'set_resource':{
				$.ajax({
					url:'role/goauth/'+rowId,
					success:function(htmlData){
						$('#layui-body-main').html(htmlData);
					}
				});
			}
			break;
		}
	});
});
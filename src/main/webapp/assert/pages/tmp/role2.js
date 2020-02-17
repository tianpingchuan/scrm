//在页面渲染成功后，使用layui的use方法按需加载需要用的各个模块。
layui.use([ 'table', 'form' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	var form = layui.form;
	var table = layui.table;
	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#table_role', // 要绑定的页面元素
		url : 'role', // 数据接口 layui会自动封装成  role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		//toolbar : '#toolbar_search', // 开启头部工具栏，并为其绑定左侧模板
		page : true,// 开启分页
		cols: [[ //表头
		      {field: 'rowId', title: 'ID', width:80, sort: true, fixed: 'left'}
		      ,{field: 'roleKind', title: '角色类型', width:180,templet:'#roleKindTpl'}
		      ,{field: 'roleName', title: '角色名称', width:180}
		      ,{field: 'roleInfo', title: '角色信息'} //此次不设置width 让其自动适应
		      ,{title: '操作', width: 140,templet:'#roleBtnTpl'}
		    ]]
	});
	//对table里面的按钮进行绑定
	////注：tool 是工具条事件名，filter_table_role =<table lay-filter="filter_table_role">
	table.on('tool(filter_table_role)',function(obj){
		 var data = obj.data; //获得当前行数据
		 var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		 //var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
		 //通过data将要修改的数据的主键 取出
		 var rowId = data.rowId;
		 switch (layEvent) {
		case 'edit':
			//打开修改的表单
			openLayerRole(rowId);
			break;
		case 'delete':
			//让用户再进行一次确认
			layer.confirm('你确定要删除码？',function(index){
				//点击弹出的确认会触发回调函数
				$.ajax({
					type:'delete',
					url:'role/'+rowId,
					success:function(result){
						if(result){
							//请求table重新加载数据 table_role = <table id="table_role"/>
							table.reload('table_role');
							//尝试将弹出层再关闭一下。
							//layer.closeAll(); //疯狂模式，关闭所有层
							layer.close(index);
						}
					}
				});
			});
			break;
		default:
			break;
		}
	});
	// 绑定新增按钮
	$('.layui-btn-add').off('click').on('click', function() {
		////打开新增表单
		openLayerRole();
	});
	//打开新增或修改表单
	function openLayerRole(rowId){
		//弹出层的标题
		var titleVal = rowId==null?'用户新增':'用户修改';
		$.ajax({
			url : 'role/goadd',
			success : function(htmlData) {
				//通过layer的open方法打开弹出层
				layer.open({
					type : 1, //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 
					title : titleVal,
					area : '800px', //设置宽度，高度自适应
					content : htmlData,// 这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
					success : function() { //弹出层打开成功以后
						//判断需要进行修改动作
						if(rowId){
							//用ajax的方式再根据id查询要修改的对象的数据
							$.ajax({
								type:'get',
								url:'role/'+rowId,
								success:function(role){
									//给表单赋值 form_role_add_edit = <form lay-filter="form_role_add_edit">
									form.val("form_role_add_edit",role);
									//为了唯一性的校验，修改的时候设置一个原来的数据
									//$.data('old')  = <input data-old=''/>
									$('#roleName').data('old',role.roleName);
									// 让form表单渲染一下。 form_role_add_edit = <form lay-filter="form_role_add_edit">
									form.render(null, 'form_role_add_edit');
								}
							});
						}else{//进新增动作
							// 让form表单渲染一下。 form_role_add_edit = <form lay-filter="form_role_add_edit">
							form.render(null, 'form_role_add_edit');
						}
					}
				});
			}
		});
	}
	//绑定搜索按钮
	form.on('submit(btn_search)', function(data) {
		//重新渲染table数据
	    table.reload('table_role', {
	        page: {
	          curr: 1 //重新从第 1 页开始
	        }
	        ,where: $('#form_search').serialize() //重新配置查询额外的数据
	      }, 'data');
		return false;
	});
});
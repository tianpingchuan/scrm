layui.use([ 'form', 'table', 'util' ], function() {
	var $ = layui.$;
	var form = layui.form;
	var table = layui.table;
	var util = layui.util;
	// 自定义校验
	form.verify({
		checkusercode : function(value, item) {
			// 调用通用的唯一性校验的方法 #调用这个方法 一定要前面带 return
			return checkUnique(value, item, 'user/checkcode');
		}
	});
	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'user', // 数据接口 layui会自动封装成 role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols : [ [ // 表头
		{
			field : 'userRoleName',
			title : '员工角色',
			width : 150,
			fixed : 'left'
		}, {
			field : 'userKind',
			title : '员工级别',
			width : 100,
			templet : '#userKindTpl'
		}, {
			field : 'userName',
			title : '员工名称',
			width : 100
		}, {
			field : 'userCode',
			title : '员工账号',
			width : 100
		}, {
			field : 'loginCount',
			title : '登录次数'
		}, {
			field : 'loginDate',
			title : '最后登录时间',
			templet : function(d) {
				return util.toDateString(d.loginDate);
			},
			width : 180
		}, {
			templet : '#demoTreeTableState1',
			title : '状态',
			width : 100
		}, {
			title : '操作',
			width : 200,
			templet : '#roleBtnTpl'
		} ] ]
	});

	form.on('select(search_parentId)', function(data) {
		// 要触发的事件
		var userKind = data.value;
		$.ajax({
			type : 'post',
			url : 'user/gochange/' + userKind,
			success : function(htmlData) {
				if (htmlData) {
					$('#parentId').empty();
					$('#parentId').html(htmlData);
					form.render('select');// select是固定写法 不是选择器
				}
			}
		});
	});
	
	table.on('tool(filter_table)', function(obj) {
		var data = obj.data; // 获得当前行数据
		var layEvent = obj.event; // 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		// var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
		// 通过data将要修改的数据的主键 取出
		var rowId = data.rowId;
		switch (layEvent) {
		case 'edit':
			// 打开通用的layer弹层
			var url = $('#hideURL').val() + '/form';
			var title = $('#hideTitle').val() + '修改';
			// 调用通用的弹出层的方法，成功后会回调done方法
			openBaseLayer(url, title).done(function() {
				// 用ajax的方式再根据id查询要修改的对象的数据
				$.ajax({
					type : 'get',
					url : $('#hideURL').val() + '/' + rowId,
					success : function(obj) {
						// 给表单赋值 form_add_edit = <form
						// lay-filter="form_add_edit">
						form.val("form_add_edit", obj);
						// 为了唯一性的校验，修改的时候设置一个原来的数据
						// $.data('old') = <input data-old=''/>
						// 处理如果表单中如果有需要进行唯一性的校验
						$.each($('.check-unique'), function(index, item) {
							var $item = $(item);
							var input_name = $item.attr('name');
							$item.data('old', obj[input_name]);
							var userKind = obj.userKind
							$.ajax({
								type : 'post',
								url : 'user/gochange/' + userKind,
								success : function(htmlData) {
									if (htmlData) {
										$('#parentId').empty();
										$('#parentId').html(htmlData);
										form.render('select');// select是固定写法
																// 不是选择器
									}
								}
							});
							// 将密码框置空
							$('#passpass').html('');

						});
						// 让form表单渲染一下。 form_add_edit = <form
						// lay-filter="form_add_edit">
						form.render(null, 'form_add_edit');
						form.render('select');// select是固定写法 不是选择器
					}
				});
			});
			break;
		case 'delete':
			// 让用户再进行一次确认
			layer.confirm('你确定要删除码？', function(index) {
				// index = 弹出层的index，用于关闭的时候使用
				// 点击弹出的确认会触发回调函数
				$.ajax({
					type : 'delete',
					url : $('#hideURL').val() + '/' + rowId,
					success : function(result) {
						if (result) {
							// 请求table重新加载数据 list_table = <table
							// id="list_table"/>
							table.reload('list_table');
							// 尝试将弹出层再关闭一下。
							// layer.closeAll(); //疯狂模式，关闭所有层
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

	// 监听开关的状态
	form.on('switch(ckState)', function(data) {
		// 获取id值
		//console.log(data.value); // 开关value值，也可以通过data.elem.value得到
		var rowId = $(this).attr('data-rowId');
		var isLock = 1;
		var checked = $(data.elem).prop('checked');
		if (checked) {
			isLock = 0;
		}
		$.ajax({
			type:'get',
			url:'user/lock/'+rowId+'/'+isLock,
		})
	});

});
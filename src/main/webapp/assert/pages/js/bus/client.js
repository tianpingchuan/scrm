var form;
var rate;
var upload;
// 在页面渲染成功后，使用layui的use方法按需加载需要用的各个模块。
layui.use([ 'table', 'form', 'rate', 'upload' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	form = layui.form;
	var table = layui.table;
	rate = layui.rate;
	upload = layui.upload;

	// 自定义表单校验
	form.verify({
		checkclientname : function(value, item) { // value：表单的值、item：表单的DOM对象
			// 调用通用的唯一性校验的方法 #调用这个方法 一定要前面带 return
			return checkUnique(value, item, 'client/checkname');
		}
	});

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
					form.render('select');// select是固定写法 不是选择器
					$.ajax({
						type : 'post',
						url : 'client/gochange2/' + cityCode,
						success : function(htmlData) {
							if (htmlData) {
								$('#areaCode').empty();
								$('#areaCode').html(htmlData);
								form.render('select');// select是固定写法 不是选择器
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
					form.render('select');// select是固定写法 不是选择器
				}
			}
		});
	});

	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'client', // 数据接口 layui会自动封装成 role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols : [ [ // 表头
		{
			field : 'clientCode',
			title : '客户编号',
			width : 150,
			fixed : 'left'
		}, {
			field : 'clientName',
			title : '客户名称',
			width : 150
		}, {
			field : 'clientPhone',
			title : '手机号码',
			width : 150
		}, {
			field : 'linkMan',
			title : '联系人',
			width : 150
		}, {
			field : 'qqNumber',
			title : '联系QQ',
			width : 150
		}, {
			field : 'emailNumber',
			title : '电子邮箱',
			width : 200
		}, {
			field : 'ifPublic',
			title : '客户状态',
			templet : '#roleKindTpl'
		} // 此次不设置width 让其自动适应
		, {
			title : '操作',
			width : 150,
			templet : '#resourceBtnTpl'
		} ] ]
	});

	// 注：tool 是工具条事件名，filter_table =<table lay-filter="filter_table">
	table.on('tool(filter_table)', function(obj) {
		var $tr = $(obj.tr);// 获得当前行 tr 的 DOM 对象（如果有的话）
		var data = obj.data; // 获得当前行数据
		var layEvent = obj.event; // 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		// 通过data将要修改的数据的主键 取出
		var rowId = data.rowId;
		switch (layEvent) {
		case 'edit':
			// 尝试取出修改时需要进行回调的方法名称,不是所有的页面都有。
			var callback4Edit = $tr.find('a[lay-event="edit"]')
					.data('callback');
			// 打开通用的layer弹层
			var url = $('#hideURL').val() + '/form';
			var title = $('#hideTitle').val() + '修改';
			// 调用通用的弹出层的方法，成功后会回调done方法
			openBaseLayer(url, title).done(function() {

				var provinceCode = data.proviceCode;
				// $('#cityCode').html('');
				$.ajax({
					type : 'post',
					url : 'client/gochange1/' + provinceCode,
					success : function(htmlData) {
						if (htmlData) {
							$('#cityCode').empty();
							$('#cityCode').html(htmlData);
							var cityCode = data.cityCode;
							form.render('select');// select是固定写法 不是选择器
							$.ajax({
								type : 'post',
								url : 'client/gochange2/' + cityCode,
								success : function(htmlData) {
									if (htmlData) {
										// $('#areaCode').empty();
										$('#areaCode').html(htmlData);
										form.render('select');// select是固定写法
										// 不是选择器
										form.val("form_add_edit", data);
									}
								}
							});
						}
					}
				});
				console.log(data.cityCode);
				console.log(data.areaCode);
				// 给表单赋值 form_add_edit = <form lay-filter="form_add_edit">
				//form.val("form_add_edit", data);
				// 为了唯一性的校验，修改的时候设置一个原来的数据
				// $.data('old') = <input data-old=''/>
				// 处理如果表单中如果有需要进行唯一性的校验
				$.each($('.check-unique'), function(index, item) {
					var $item = $(item);
					var input_name = $item.attr('name');
					$item.data('old', data[input_name]);
				});

				// 让form表单渲染一下。 form_add_edit = <form
				// lay-filter="form_add_edit">
				form.render(null, 'form_add_edit');

				// 判断如果 修改form页面弹出后，需要回调的方法名称不为空。
				if (callback4Edit) {
					// 尝试调用一下额外配置的为修改使用的回调函数
					eval(callback4Edit);
				}
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

});
// 页面回调方法，一定要写在layui.use外面
function initForm() {
	// 显示文字
	rate.render({
		elem : '#test2',
		value : 2,
		choose:function(value) {
			$('#clientKind').val(value);
		},
		text : true
	});
	upload.render({
		elem : '#test11',
		url : 'client/upload',// 上传接口
		accept : 'file',// 普通文件
		field : 'uploadFile', // 设置文件域的字段名，默认值是file
		done : function(res) {
			$('#accessoryBag').val(res.data);
			$('#cust_upload_word').html('附件上传成功！');
		}
	})
}
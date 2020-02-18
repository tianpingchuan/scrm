/*   layui.config({
        base: 'assert/layui/'//你存放新模块的目录，注意，不是layui的模块目录
    }).extend({
        treeTable: 'treeTable/treeTable'
    })*/
layui.use(['layer', 'util', 'treeTable'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var util = layui.util;
        var form= layui.form;
        var treeTable = layui.treeTable;
        // 渲染表格
        var insTb = treeTable.render({
            elem: '#list_table',
            tree: {
                iconIndex: 0,
                idName: 'rowId',  // 自定义id字段的名称#默认值为id
                haveChildName: 'hasChild',  // 自定义标识是否还有子节点的字段名称#默认值为haveChild
            },
            cols: [
                // {type: 'numbers'},
               // {type: 'checkbox'},
               // {field: 'id', title: 'ID'},
                {field: 'ddDescribe', title: '字典描述'},
                {field: 'createDate', title: '创建时间', templet: function (d) {
                        return util.toDateString(d.createDate);
                    }, width: 180
                },
                {align: 'center', toolbar: '#resourceBtnTpl', title: '操作', width: 250}
            ],
            style: 'margin-top:0;',
            reqData: function(data, callback) {
                    // 在这里写ajax请求，通过callback方法回调数据
                	$.ajax({
                		url:'dd/list',
                		success:function(result){
                			callback(result.data); // 参数是数组类型
                		}
                	});
                }
        });

        treeTable.on('tool(list_table)', function (obj) {
            var event = obj.event;
            //通过data将要修改的数据的主键 取出
   		 	var rowId = obj.data.rowId;
            switch (event) {
			case 'res_child'://进新增子资源
				//打开通用的弹出窗口
				openBaseLayer('dd/child/'+rowId,'子资源新增').done(function(){
					// 让form表单渲染一下。 form_add_edit = <form lay-filter="form_add_edit">
					form.render(null, 'form_add_edit');
				});
				break;
			case 'res_edit':
				openBaseLayer('dd/form','数据字典修改').done(function(){
					$.ajax({
						url:'dd/'+rowId,
						success:function(obj){
							//给表单赋值 form_add_edit = <form lay-filter="form_add_edit">
							form.val("form_add_edit",obj);
							//为了唯一性的校验，修改的时候设置一个原来的数据
							//$.data('old')  = <input data-old=''/>
							//处理如果表单中如果有需要进行唯一性的校验
							$.each($('.check-unique'),function(index,item){
								var $item = $(item);
								var input_name = $item.attr('name');
								$item.data('old',obj[input_name]);
							});
							// 让form表单渲染一下。 form_add_edit = <form lay-filter="form_add_edit">
							form.render(null, 'form_add_edit');
							// 父类名称
							var parentName = obj.parentValue;
							if(parentName){
								$('#parentName').html(parentName);
							}
							//字典编码
							var ddValue = obj.ddValue;
							//字典的描述
							var ddDescribe = obj.ddDescribe;
						}
					});
				});
				break;
			case 'res_del':
				//让用户再进行一次确认
				layer.confirm('你确定要删除码？',function(index){
					//index = 弹出层的index，用于关闭的时候使用
					console.log(rowId);
					//点击弹出的确认会触发回调函数
					$.ajax({
						type:'delete',
						url:$('#hideURL').val()+'/'+rowId,
						success:function(result){
							if(result){
								insTb.refresh();  // 刷新(异步模式)
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
     // 监听提交动作  submit(but_submit) = <button class="layui-btn" lay-submit lay-filter="but_submit">
    	form.on('submit(but_resource_submit)', function(data) {
    		//data.field //当前容器的全部表单字段，名值对形式：{name: value}
    		var rowId = data.field.rowId;
    		//默认为新增
    		var type = 'post';
    		if(rowId){ // 如果rowId有值,则执行修改
    			type='put';
    		}
    		$.ajax({
    			type:type,
    			url:'dd',
    			data:$(data.form).serialize(),
    			success:function(result){
    				if(result){
    					// 关闭弹出层
    					//layer.close(layer.index);
    					layer.closeAll(); //疯狂模式，关闭所有层
    					//请求table重新加载数据 list_table = <table id="list_table"/>
    					insTb.refresh();  // 刷新(异步模式)
    				}
    			}
    		});
    		
    		//不用忘记 return false ,讲按钮原来的功能给屏蔽掉。
    		return false;
    	});
        
        //自定义 校验
        form.verify({
        	checkddValue:function(value,item){
        		return checkUnique(value, item,'dd/checkddValue',{"parentKey":$('#parentKey').val()})
        	}
        });
        
    });
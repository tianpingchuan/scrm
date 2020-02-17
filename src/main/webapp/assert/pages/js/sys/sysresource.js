/*   layui.config({
        base: 'assert/layui/'//你存放新模块的目录，注意，不是layui的模块目录
    }).extend({
        treeTable: 'treeTable/treeTable'
    })*/
layui.use(['layer', 'util', 'treeTable','iconPicker'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var util = layui.util;
        var form= layui.form;
        var treeTable = layui.treeTable;
        var iconPicker = layui.iconPicker;
        // 渲染表格
        var insTb = treeTable.render({
            elem: '#list_table',
            tree: {
                iconIndex: 0,
                idName: 'rowId',  // 自定义id字段的名称#默认值为id
                haveChildName: 'hasChild',  // 自定义标识是否还有子节点的字段名称#默认值为haveChild
                getIcon: function (d) {//自定义图标，区分是菜单还是动作
                    if (d.rescType==1) {
                    	var icon= d.rescIcon
                        return '<i class="layui-icon '+icon+'"></i>';
                    } else {
                        return '<i class="ew-tree-icon ew-tree-icon-file"></i>';
                    }
                }
            },
            cols: [
                // {type: 'numbers'},
               // {type: 'checkbox'},
               // {field: 'id', title: 'ID'},
                {field: 'rescName', title: '资源名称'},
                {field: 'menuUrl', title: '菜单URL'},
                {field: 'createDate', title: '创建时间', templet: function (d) {
                        return util.toDateString(d.createDate);
                    }, width: 180
                },
                {align: 'center', toolbar: '#resourceBtnTpl', title: '操作', width: 200}
            ],
            style: 'margin-top:0;',
            reqData: function(data, callback) {
                    // 在这里写ajax请求，通过callback方法回调数据
                	$.ajax({
                		url:'sysresource/list',
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
				openBaseLayer('sysresource/child/'+rowId,'子资源新增').done(function(){
					// 让form表单渲染一下。 form_add_edit = <form lay-filter="form_add_edit">
					form.render(null, 'form_add_edit');
				});
				break;
			case 'res_edit':
				openBaseLayer('sysresource/form','资源修改').done(function(){
					$.ajax({
						url:'sysresource/'+rowId,
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
							var parentName = obj.parentName;
							if(parentName){
								$('#parentName').html(parentName);
							}
							//菜单 图片
							var rescIcon = obj.rescIcon;
							if(rescIcon){
								iconPicker.checkIcon('rescIcon', rescIcon);
							}
							// 资源类型#1:菜单;0:功能;
							var rescType = obj.rescType;
							if(rescType==0){
								$('#div_rescIcon').hide();
				        		$('#div_menuUrl').hide();
							}
							//动作列表
							var actionInfoList = obj.actionInfoList;
							if(actionInfoList){
								$.each(actionInfoList,function(index,actionInfo){
									initActionForm(index,actionInfo);
								});
							}
						}
					});
				});
				break;
			case 'res_del':
				//让用户再进行一次确认
				layer.confirm('你确定要删除码？',function(index){
					//index = 弹出层的index，用于关闭的时候使用
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
    			url:'sysresource',
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
        //  绑定新增动作表单按钮
        $(document).off('click','#btn_action-add').on('click','#btn_action-add',function(){
        	var $hide_index = $('#hide_index');
        	var index = $hide_index.val();
        	$hide_index.val(parseInt(index)+1);
        	initActionForm(index);
        	return false;
        });
        //初始化动作表单数据
        function initActionForm(index,obj){
        	var get_select='',post_select='',put_select='',delete_select='' ;
        	var actionUrl ='';
        	//如果 obj有值
        	if(obj){
        		actionUrl = obj.actionUrl;
        		var method = obj.method;
        		switch (method) {
				case 'GET':
					get_select ='selected';
					break;
				case 'POST':
					post_select ='selected';
					break;
				case 'PUT':
					put_select ='selected';
					break;
				case 'DELETE':
					delete_select ='selected';
					break;
				default:
					break;
				}
        		
        	}
        	var html='<div class="layui-form-item">\
							<div class="layui-inline">\
						<div class="layui-input-inline" style="width: 100px;">\
							<select name="actionInfoList['+index+'].method" lay-verify="required">\
								<option value="GET" '+get_select+'>GET</option>\
								<option value="POST" '+post_select+'>POST</option>\
								<option value="PUT" '+put_select+'>PUT</option>\
								<option value="DELETE" '+delete_select+'>DELETE</option>\
							</select>\
						</div>\
						<div class="layui-input-inline" style="width: 400px;">\
							<input value="'+actionUrl+'" name="actionInfoList['+index+'].actionUrl" lay-verify="required" placeholder="动作URL" autocomplete="off" class="layui-input">\
						</div>\
						<div class="layui-input-inline" style="width: 40px;">\
							<button class="layui-btn layui-btn-normal btn_action_remove">\
								<i class="layui-icon layui-icon-subtraction"></i>\
							</button>\
						</div>\
					</div>\
				</div>';
        	$('#action_info_box').append(html);
        	// 渲染
        	form.render('select'); //刷新select选择框渲染
        }
        //绑定移除
        $(document).off('click','.btn_action_remove').on('click','.btn_action_remove',function(){
        	var div = $(this).closest('.layui-form-item');
        	div.remove();
        });
        //自定义 校验
        form.verify({
        	checkrescname:function(value,item){
        		return checkUnique(value, item,'sysresource/checkname',{"parentCode":$('#parentCode').val()})
        	}
        });
        //绑定 资源类型 select 
        form.on('select(filter_rescType)', function(data){
        	if(data.value==1){
        		$('#div_rescIcon').show();
        		$('#div_menuUrl').show();
        	}else{
        		$('#div_rescIcon').hide();
        		$('#div_menuUrl').hide();
        	}
    	});  
    });
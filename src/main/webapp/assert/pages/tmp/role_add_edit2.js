layui.use(['layer','form','table'], function() {
	var $ = layui.$;
	var layer = layui.layer;
	var form = layui.form;
	var table = layui.table;
	// 监听提交动作  submit(but_submit) = <button class="layui-btn" lay-submit lay-filter="but_submit">
	form.on('submit(but_submit)', function(data) {
		//data.field //当前容器的全部表单字段，名值对形式：{name: value}
		var rowId = data.field.rowId;
		//默认为新增
		var type='post';
		if(rowId){ // 如果rowId有值,则执行修改
			type='put';
		}
		$.ajax({
			type:type,
			url:'role',
			data:$(data.form).serialize(),
			success:function(result){
				if(result){
					// 关闭弹出层
					//layer.close(layer.index);
					layer.closeAll(); //疯狂模式，关闭所有层
					//请求table重新加载数据 table_role = <table id="table_role"/>
					table.reload('table_role');
				}
			}
		});
		//不用忘记 return false ,讲按钮原来的功能给屏蔽掉。
		return false;
	});
	//自定义表单校验
	form.verify({
		checkrolename:function(value, item){ //value：表单的值、item：表单的DOM对象
			var msg ;
			//判断是否需要进行唯一性的校验
			//$.data  解析的是 <input data-old ="管理员"/>
			var oldVal = $(item).data('old');
			//如果原来的值有数据,并且原值和当前的值一样，则不需再进行唯一性校验。
			if(oldVal!=null && oldVal == value){
				return msg;
			}else{//否则我们需要进行唯一性的校验。
				$.ajax({
					type:'get',
					async:false,//为了让layui可以做唯一性的校验，需要将ajax的异步提交关闭。
					url:'role/checkname',
					data:{"roleName":value},
					success:function(result){
						//判定此名称不可以使用，已经有人用了
						if(result==1){
							//不要在ajax的success中return
							//return '此名称以有人使用';
							msg ='此名称已使用,请重新填写';
						}
					}
				});
				//在ajax的外面return 此次检测的结果。
				//如果return的数据有值的话，则 layui就认为是校验不通过。
				//如果return的结果是个空，则layui认为校验通过。
				return msg;
			}
		}
	});
});
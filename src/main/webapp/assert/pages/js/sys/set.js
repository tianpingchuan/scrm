layui.use(['layer', 'form'], function() {
	var form = layui.form;
	var $ = layui.$;
	var layer = layui.layer;
	// 监听提交
	form.on('submit(but_set_submit)', function(data) {
		$.ajax({
			type : 'put',
			url : 'set',
			data : $('#form_set').serialize(),
			sussess : function(result) {
				if (result) {
					layer.msg("设置成功！！",{icon:1,time:3000});
				}
			}
		});
		return false;
	});
});
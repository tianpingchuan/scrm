layui.use(['form'],function(){
	var $ = layui.$;
	var form = layui.form;
	//自定义校验
	form.verify({
		//至少选中一个
		onechecked:function(value,item){
			//得到需要校验的所有的被选中的checkbox的数量
			var checkedCount = $(item).find(':checkbox:checked').length;
			if(checkedCount==0){
				$(item).addClass('layui-form-danger');
				setTimeout(() => {
					$(item).removeClass('layui-form-danger');
				}, 2000);
				return '请至少选中一个角色信息';
			}
		},
		checkusercode:function(value,item){
			//调用通用的唯一性校验的方法 #调用这个方法 一定要前面带 return
			return checkUnique(value,item,'user/checkcode');
		}
	});
});
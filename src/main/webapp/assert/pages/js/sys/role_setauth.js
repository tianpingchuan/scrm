layui.use('tree', function() {
	var tree = layui.tree;
	var layer = layui.layer;
	
	$.ajax({
		url : 'role/findauth/' + $('#rowId').val(),
		success : function(mapData) {
			//var rescCodeList = roleAuthTree.rescCodeList;// 已经选中的rescCode集合
			//var mapData = roleAuthTree.layTreeMap;
			$.each(mapData, function(key, value) {
				var id = 'tree_role_setauth'+key;
				// 绑定tree
				tree.render({
					elem : '#auth' + key,
					data : value,
					id:id,
					showCheckbox : true, // 是否显示复选框
				});
				// 执行节点勾选
				//tree.setChecked(id, rescCodeList); // 批量勾选 id 为 2、3 的节点
			});
		}
	});
	// 设置权限
	$('#btn_set_auth').on('click',function(){
		// 获得选中的节点
		var checkData=$('#tree_set_auth_div').find(':checked');
		var params = {};
		params['roleId'] = $('#rowId').val();
		if(checkData){
			$.each(checkData,function(index,obj){
				var rescCode = $(obj).val();
				params['rescCodeList['+index+']'] = rescCode;
			});
		}
		$.ajax({
			type:'put',
			url:'role/setauth',
			data:params,
			success:function(result){
				if(result){
					layer.msg('权限设置成功', {icon: 6}); 
					// 回角色管理首页
					goBackRole();
				}
			}
		});
	});
});
// 回角色管理首页
function goBackRole() {
	$.ajax({
		url : 'role',
		success : function(htmlData) {
			$('#layui-body-main').html(htmlData);
		}
	});
}
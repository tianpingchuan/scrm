var canGetCookie = 0;// 是否支持存储Cookie 0 不支持 1 支持
// 默认账号密码

var CodeVal = 0;
Code();
function Code() {
	if (canGetCookie == 1) {
		createCode("AdminCode");
		var AdminCode = getCookieValue("AdminCode");
		showCheck(AdminCode);
	} else {
		showCheck(createCode(""));
	}
}
function showCheck(a) {
	CodeVal = a;
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
	ctx.clearRect(0, 0, 1000, 1000);
	ctx.font = "80px 'Hiragino Sans GB'";
	ctx.fillStyle = "#E8DFE8";
	ctx.fillText(a, 0, 100);
}

// 粒子背景特效
$('body').particleground({
	dotColor : '#E8DFE8',
	lineColor : '#133b88'
});
$('input[name="pwd"]').focus(function() {
	$(this).attr('type', 'password');
});
$('input[type="text"]').focus(function() {
	$(this).prev().animate({
		'opacity' : '1'
	}, 200);
});
$('input[type="text"],input[type="password"]').blur(function() {
	$(this).prev().animate({
		'opacity' : '.5'
	}, 200);
});
$('input[name="login"],input[name="pwd"]').keyup(function() {
	var Len = $(this).val().length;
	if (!$(this).val() == '' && Len >= 5) {
		$(this).next().animate({
			'opacity' : '1',
			'right' : '30'
		}, 200);
	} else {
		$(this).next().animate({
			'opacity' : '0',
			'right' : '20'
		}, 200);
	}
});

// 在页面渲染成功后，使用layui的use方法按需加载需要用的各个模块。
layui.use([ 'form' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	var form = layui.form;
	// 监听提交动作 submit(but_submit) = <button class="layui-btn" lay-submit
	// lay-filter="but_submit">
	form.on('submit(but_submit)', function(data) {
		// data.field //当前容器的全部表单字段，名值对形式：{name: value}
		var rowId = data.field.rowId;

		var login = $('input[name="userCode"]').val();
		var pwd = $('input[name="userPass"]').val();
		var code = $('input[name="code"]').val();
		var remember = $('input[name="remember"]:checked').val();
		if (login == '') {
			ErroAlert('请输入您的账号');
		} else if (pwd == '') {
			ErroAlert('请输入密码');
		} else if (code == '') {
			ErroAlert('输入验证码');
		} else if (data.field.code.toUpperCase() != CodeVal.toUpperCase()
				.toUpperCase()) {
			ErroAlert('验证码错误');
		} else {
			$.ajax({
				type : "get",
				url : "login",
				data : $(data.form).serialize(),
				success : function(result) {
					if (result) {
						location.href = 'success/' + login +"/"+remember;
					} else {
						ErroAlert('用户名密码错误或不存在');
					}
				}
			});
		}

		// 不用忘记 return false ,讲按钮原来的功能给屏蔽掉。
		return false;
	});
});
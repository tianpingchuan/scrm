<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>登录界面</title>
<link href="assert/login/css/default.css" rel="stylesheet"
	type="text/css" />
<!--必要样式-->
<link href="assert/login/css/styles.css" rel="stylesheet"
	type="text/css" />
<link href="assert/login/css/demo.css" rel="stylesheet" type="text/css" />
<link href="assert/login/css/loaders.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<form class="layui-form" lay-filter="form_login">
		<div class='login'>
			<div class='login_title'>
				<span>欢迎登录</span>
			</div>
			<div class='login_fields'>
				<div class='login_fields__user'>
					<div class='icon'>
						<img alt="" src='assert/login/img/user_icon_copy.png'>
					</div>
					<input name="userCode" placeholder='用户名' maxlength="16" type='text'
						autocomplete="off" />
					<div class='validation'>
						<img alt="" src='assert/login/img/tick.png'>
					</div>
				</div>
				<div class='login_fields__password'>
					<div class='icon'>
						<img alt="" src='assert/login/img/lock_icon_copy.png'>
					</div>
					<input name="userPass" placeholder='密码' maxlength="16" type='text'
						autocomplete="off">
					<div class='validation'>
						<img alt="" src='assert/login/img/tick.png'>
					</div>
				</div>
				<div class='login_fields__password'>
					<div class='icon'>
						<img alt="" src='assert/login/img/key.png'>
					</div>
					<input name="code" placeholder='验证码' maxlength="4" type='text'
						name="ValidateNum" autocomplete="off">
					<div class='validation' style="opacity: 1; right: -5px; top: -3px;">
						<canvas class="J_codeimg" id="myCanvas" onclick="Code();"></canvas>
					</div>
				</div>
				<div class='login_title'>
				<div class='login_fields__password'>
					<div class='icon'>
						<input type="checkbox" class="checkbox" name="remember" value="1"
							lay-skin="primary">
						<span>记住密码</span>
					</div>
				</div>
				</div>
				<div class='login_fields__submit'>
					<input type='button' lay-submit lay-filter="but_submit" value='登录'>
				</div>
			</div>
		</div>
	</form>

	<link rel="stylesheet" href="assert/layui/css/layui.css" media="all">

	<script type="text/javascript" src="assert/login/js/jquery.min.js"></script>
	<script type="text/javascript" src="assert/login/js/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src='assert/login/js/stopExecutionOnTimeout.js?t=1'></script>
	<script type="text/javascript" src="assert/layui/layui.js"></script>
	<script type="text/javascript" src="assert/login/js/Particleground.js"></script>
	<script type="text/javascript" src="assert/login/js/Treatment.js"></script>
	<script type="text/javascript" src="assert/login/js/jquery.mockjax.js"></script>

	<script type="text/javascript" src="assert/pages/js/sys/login.js"></script>


</body>
</html>
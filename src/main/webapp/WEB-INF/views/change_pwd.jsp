<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>大数据-修改密码</title>
<script type="text/javascript" src="${ctx }/resources/js/change_pwd.js"></script>
</head>
<body>
	<!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
	<!--main(begin)-->
	<article>
		<nav class="com-nav">
			<div class="w1100">
				<span class="fsize14">当前位置：修改密码</span>
			</div>
		</nav>
		<form class="form-horizontal" id="change-pwd-form">
			<div id="message" class="bd-pwd-content w1100">
				<input type="hidden" name="Id" id="Id" value="${loginuser.id }">
				<div class="form-group pr">
					<label class="control-label">原密码：</label> <input type="password"
						class="form-control" id="old_pwd" name="old_pwd"
						placeholder="2~12个字符，可使用数字、字母">
				</div>
				<div class="form-group pr">
					<label class="control-label">新密码：</label> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="2~12个字符，可使用数字、字母">
				</div>
				<div class="form-group pr">
					<label class="control-label">确认密码：</label> <input type="password"
						class="form-control" id="sure_pwd" name="sure_pwd"
						placeholder="2~12个字符，可使用数字、字母">
				</div>
				<div class="from-group ok-btn">
					<input type="submit" class="btn btn-default" value="确定"> <input
						type="submit" onclick="menus('/home')" class="btn "
						style="margin-left: 8px;" value="返回">
				</div>

			</div>
		</form>
	</article>
	<!--/main(end)-->
	<!-- 公共尾引入 -->
	<%@ include file="/common/foot.jsp"%>
	<!-- /公共尾引入 -->

</body>
</html>
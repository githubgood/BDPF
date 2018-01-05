<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="stylesheet" href="${ctx}/resources/css/tip-yellow.min.css">
<script type="text/javascript" src="${ctx}/resources/js/jquery.poshytip.min.js"></script>
<script  type="text/javascript" src="${ctx}/resources/js/base-validate.js"></script>
<script  type="text/javascript" src="${ctx}/resources/js/login.js"></script>
<title>大数据-登录</title>
</head>
<body style="background-color:#d6e2ee;">
	<!--logo(begin)-->
	<header class="login-header">
		<div class="w1200 login-logo">
			<img src="${ctx}/resources/images/bigData-logo.png" alt="logo" width="371" height="47">
		</div>
	</header>
<!--/logo(end)-->
<!--login-main(begin)-->
	<article>
		<section>
			<div class="login-main">
				<div class="login-bg">
					<span class="login-img">
						<img src="${ctx}/resources/images/bigData-bg.png" alt="" width="1920" height="540">
					</span>
				</div>
				<div class="w1200 pr">
					<div class="login-box pa">
						<form action="">
							<ul class="mlr60">
								<li class="mt50 tac base-color">
									用户登录
								</li>
								<li class="mt40">
									<div class="form-item user-box error-bg">
										<input class="error-bg" type="text" id="username" name="username" placeholder="学号或工号">
									</div>
								</li>
								<li class="mt20">
									<div class="form-item psw-box">
										<input type="password" placeholder="密码" id="password" name="password">
									</div>
								</li>
								<!-- <li class="mt20 ml10">
									<p  class="radio_box mr15">
										<input class="radio" type="radio"  name="type"  value="0">
										<span class="radio">安装维护类</span>
									</p>
									<p  class="radio_box mr15">
										<input class="radio" type="radio"  name="type"   value="1">
										<span class="radio">应用开发类</span>
									</p>
									<p  class="radio_box">
										<input class="radio" type="radio"  name="type"  value="2">
										<span class="radio">数据挖掘分析类</span>
									</p>
								</li> -->
								<li class="mt30">
									<a href="javascript:void(0);" class="common-btn login-btn" onclick="login()" >登<i></i>录</a>
								</li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</section>
	</article>
<!--/login-main(end)-->
<!--footer(begin)-->
<footer class="login-footer">
	<div class="w1100 tac fsize14">Copyright © 2017 - 2020 深圳市讯方技术股份有限公司 粤ICP备12068225号-1</div>
</footer>
<!--/footer(end)-->
<!--loading-->
 <div class="cd-mask">
	<img src="${ctx}/resources/images/loading.gif" alt="">
</div> 
<!--/loading-->
</body>
</html>
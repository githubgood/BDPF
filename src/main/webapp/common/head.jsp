<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>大数据-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
<script src="${ctx}/resources/js/head.js"></script>
</head>
<body class="ly-bg">
	<!-- 公共头引入 -->
	<header id="header">
		<section class="head-wrap fa">
			<div class="w1100 login-info">
				<div class="fr pr mr20">
					<span class="ix-user-name">${loginuser.name}，欢迎你！</span> 
					<span class="ml10">
						<a href="javascript:void(0);" onclick="menus('/user/to_updpassword');">修改密码</a>
					</span>
					<span class="ml10">
						<a href="javascript:void(0);" onclick="menus('/login/loginout');">退出</a>
					</span>
				</div>
			</div>
		</section>
		<section class="head-wrap nav-bc">
			<nav class="hNav w1230 pl20">
				<a href="" title="讯方大数据创新实训系统">
					<img src="${ctx}/resources/images/index/index-logo.png" class="logo-2013" />
				</a>
				<ul class="mr_10 fr">
					<!--header nav begin-->
					<c:forEach items="${menuMap}"  var="model" varStatus="status">
						<li>
						<!-- 如果为空，就取一级菜单的reqMapping -->
						<c:if test="${empty model.value[0].reqMapping}">
							<a href="javascript:void(0);" title="${model.key.name}"  onclick="menus('${model.key.reqMapping}','${loginuser.userType}','${sid}')">${model.key.name}</a>
						</c:if>
						<!-- 如果不为空，就取二级菜单第一个reqMapping -->
						<c:if test="${not empty model.value[0].reqMapping}">
							<a href="javascript:void(0);" title="${model.key.name}"  onclick="menus('${model.value[0].reqMapping}','${loginuser.userType}','${sid}')">${model.key.name}</a>
						</c:if>
			    			<dl class="dropdown_menu">
								 <c:forEach items="${model.value}"  var="menu">
								 <c:if test="${model.key.resCode == menu.parentId && (menu.parentId=='vmstemplates' || menu.parentId=='datasite') }">
									<dd>
										<a href="javascript:void(0);" onclick="menus('${menu.reqMapping}','${loginuser.userType}','${sid}');">${menu.name}</a>
									</dd>
					    		</c:if> 
							</c:forEach>
							</dl>
					    </li>
					</c:forEach>
					
				</ul>
			</nav>
		</section>
	</header>
	<!-- /公共头引入 -->
	<script type="text/javascript">
	$(function() {
		//导航子列表显示
		var $item = $(".hNav li");
		$item.click(function() {
			$item.removeClass("current");
			$(this).hasClass("current") ? $(this).removeClass("current")
					: $(this).addClass("current")
		})
		$item.hover(function() {
			$(this).find(".dropdown_menu").stop().slideDown();
		}, function() {
			$(this).find(".dropdown_menu").stop().slideUp();
		})
	})
	
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ include file="/common/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta HTTP-EQUIV="pragma" CONTENT="no-cache" />
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate" />
<meta HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>进入vnc页面</title>
</head>
<body>
        <!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
	<!--main(begin)-->
<article>
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">虚拟机</a></span>
		</div>
	</nav>
	<section class="w1100 et-main">
		<div class="et-content of" style="margin-top:0px;">
			<div style="height: auto;">
				<div class="et-left-item et-right-list" id="urllist" style="border-top:none;">
				<iframe src="${vncUrl }" width="100%" height="650"></iframe>
				</div>
			</div>
		</div>
	</section>
</article>
<!--/main(end)-->
	<!-- 公共尾引入 -->
	<%@ include file="/common/foot.jsp"%>
	<!-- /公共尾引入 -->
</body>
</html>
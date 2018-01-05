<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>数据站点-列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
	<meta name="renderer" content="webkit">
	

	<link rel="stylesheet" href="${ctx}/resources/js/artDialog/css/ui-dialog.css">
	<script src="${ctx}/resources/js/artDialog/js/dialog-min.js"></script>

	<script src="${ctx}/resources/js/dialog.js"></script>
</head>
<body class="ly-bg">
<div  id="content-api">
<%@ include file="/common/head.jsp"%>
<!-- /公共头引入 -->
<!--main(begin)-->
<article>
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">API接口</a></span>
		</div>
	</nav>
	
	<section class="w1100 teachingPlan-content">
		<div class="et-manage-nav" id="site">
			
		</div>
		<div class="et-manage-content" id="content-r">
			
			<div class="et-manage-operate-datasite" id=main>
			
			</div>
			<br>
			<div id="paginator-tool"  style="margin: auto;"></div>
		</div>
		
		
	</section>
	
</article>

<!--/main(end)-->
<!--footer(begin)-->
<!-- <footer class="index-footer"> -->
<!-- 	<div class="w1100 tac fsize14">Copyright © 2017 - 2020 深圳市讯方技术股份有限公司 粤ICP备12068225号-1</div> -->
<!-- </footer> -->

<!--footer(end)-->
<%@ include file="/common/foot.jsp"%>
</div>
<script>
var siteName='${siteName}';
var id='${id}';
</script>
<script src="${ctx}/resources/js/datasite/api/api.js"></script>
</body>
</html>
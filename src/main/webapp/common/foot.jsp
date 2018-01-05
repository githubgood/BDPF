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
	
</head>
<body class="ly-bg">
<!--footer(begin)-->
<footer class="index-footer">
	<div class="w1100 tac fsize14">Copyright © 2017 - <span id="newyear"></span> 深圳市讯方技术股份有限公司 粤ICP备12068225号-1</div>
</footer>
<!--footer(end)-->
<script type="text/javascript">
	$(function(){
		var now = new Date();  
	    var year = now.getFullYear(); //获取当前年份
	    $('#newyear').html(year);
	});
</script>
</body>
<!-- 弹出框 -->
	<c:import url="/common/dialog.jsp"></c:import>
</html>
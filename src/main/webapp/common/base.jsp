<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="bdpf"  value="讯方大数据创新实训系统" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>${bdpf}</title>
  <link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="${ctx}/resources/css/base.css">
  <link rel="stylesheet" href="${ctx}/resources/css/common.css">
  <link rel="stylesheet" href="${ctx}/resources/css/page-style.css">
  <link rel="stylesheet" href="${ctx}/resources/css/smartpaginator.css"/>
</head>
<body>
<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/echarts.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/smartpaginator.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.validate.common_rules.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.validate.min.js"></script>
 <script type="text/javascript" src="${ctx}/resources/js/dialog.js"></script> 
<script type="text/javascript">
window.ctx = '${ctx}'; //javascript context 项目名称
window.pageNo = '${pageNo+1}';//从第几条开始取数据
window.pageSize ='${pageSize}';//每页显示多少条数据
window.itcAddress = '${itcAddress}';//ITC访问地址
function loadGif(){
	var $mask = $(".cd-mask");
	$mask.height($(document).height()+$(document).scrollTop());
	/*显示loading*/
	$mask.show();
	//loading显示消除右侧滚动条，隐藏恢复
	$mask.is(":hidden")?$("body").css({"height":"auto","overflow":"auto"}):$("body").css({"height":"100%","overflow":"hidden"});
	return false;
}
function closeGif(){
	var $mask = $(".cd-mask");
	/*隐藏loading*/
	$mask.hide();
	//loading显示消除右侧滚动条，隐藏恢复
	$mask.is(":hidden")?$("body").css({"height":"auto","overflow":"auto"}):$("body").css({"height":"100%","overflow":"hidden"});
	return false;
}
//内容区域高度自适应
function autoHeight(a,b){
	var main = document.getElementsByClassName(b)[0];
	var cHeight = document.documentElement.clientHeight;
	$(main).css("min-height",cHeight-a);
	return false;
}
$(function(){
	autoHeight(190,"teachingPlan-content");
	autoHeight(230,"VMware-box");
	autoHeight(250,"fd-dataList-box");
	autoHeight(250,"vm-template-autoHeight");
});
</script>
</body>
</html>
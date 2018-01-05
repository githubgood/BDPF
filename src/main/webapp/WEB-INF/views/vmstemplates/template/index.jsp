<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>虚拟机模板-虚拟机</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<script type="text/javascript" src="${ctx}/resources/js/vmstemplates/template/template_stu.js"></script>
<meta name="renderer" content="webkit">
<style type="text/css">
.VMstudent-item {
    float: left;
    width: 200px;
    padding: 25px 0 0 20px;
    height: 130px;
    border: 1px solid #d6e2ee;
    margin: 0 40px 40px 0;
    cursor: pointer;
}

</style>
</head>
<body>
<!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
<!--main(begin)-->
<article class="of db-min-height">
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">虚拟机模板</a><i>-</i>虚拟机</span>
		</div>
	</nav>
	<section class="VMware-box w1100">
		<div class="VMware-operate clearfix" id="content-r">
			<div class="fr">
				<div class="VMware-search-box fl mt10">
					<input type="text" id="keywords" placeholder="请输入关键字">
				</div>
				<a href="javascript:void(0);"  id="search"  class="btn search-btn">搜索</a>
			</div>
		</div>
		<div class="VMstudent-list mt30">
			<ul id="list-tbody">
				
			</ul>
		</div>
	</section>
</article>
<!--main(end)-->
	<!-- 公共尾引入 -->
	<%@ include file="/common/foot.jsp"%>
	<!-- /公共尾引入 -->
	
			
	<!--loading-->
 <div class="cd-mask">
	<img src="${ctx}/resources/images/loading.gif" alt="">
</div> 
<!--/loading-->
	<script>
	$(function(){
		//导航子列表显示
		var $item = $(".hNav li");
		$item.click(function(){
			$item.removeClass("current");
			$(this).hasClass("current")?$(this).removeClass("current"):$(this).addClass("current")
		})
		$item.hover(function(){
			$(this).find(".dropdown_menu").stop().slideDown();
		},function(){
			$(this).find(".dropdown_menu").stop().slideUp();
		})
		
	})
</script>
</body>
</html>
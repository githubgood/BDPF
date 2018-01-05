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
<meta name="renderer" content="webkit">
<script src="${ctx}/resources/js/vmstemplates/virtual/studentVirtual.js"></script>
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
	<section id="content-r" class="VMware-box w1100">
		<div class="VMware-operate clearfix">
		<div class="fl com-box" style="margin:15px 0 0 15px" id="showVirtual">
				<p class="checkbox_box">
					<input class="checkBox" type="checkbox"  name="virtual" value="hadoop" onclick="bb(this)">
					<span class="checkBox"> hadoop集群 </span>
				</p>
				<p class="checkbox_box">
					<input class="checkBox" type="checkbox"  name="virtual" value="HBase" onclick="bb(this)">
					<span class="checkBox"> HBase集群</span>
				</p>
				<p class="checkbox_box">
					<input class="checkBox" type="checkbox"  name="virtual" value="HBaseHive" onclick="bb(this)">
					<span class="checkBox"> HBase+Hive集群</span>
				</p>
				<p class="checkbox_box">
					<input class="checkBox" type="checkbox"  name="virtual" value="Spark" onclick="bb(this)">
					<span class="checkBox">  Spark集群</span>
				</p>
				<p class="checkbox_box">
					<input class="checkBox" type="checkbox" name="virtual" value="all" onclick="bb(this)">
					<span class="checkBox"> 安装全部集群</span>
				</p>
				</div>
			<div class="fr">
				<!-- <div class="VMware-search-box VM-search-box fl mt10">
					<input class="form-control" type="text" id="keywords" placeholder="请输虚拟机名称">
				<a class="btn create-btn mt10 mr5" id="add" style="background-color: #46c67c;color: #fff;">创建虚拟机</a>
				</div> -->
				<a href="javascript:void(0);" id="install" class="btn search-btn" style="outline: none; ">集群安装</a>
				<a href="javascript:void(0);" id="destroy" class="btn search-btn" style="outline: none; ">集群销毁</a>
				<a href="javascript:void(0);" id="search" class="btn search-btn" style="outline: none; ">刷新</a>
			</div>
		</div>
		<div class="VMstudent-list mt30 VMstudent-hover">
			<ul id="studentvm">

			</ul>
		</div>
	</section>
</article>
<!--/main(end)-->
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
		//搜索框子列表
		$(".search-box").hover(function(){
			$(this).find(".dropdown-list").stop().slideDown();
		},function(){
			$(this).find(".dropdown-list").stop().slideUp();
		})
	})
</script>

</body>

</html>

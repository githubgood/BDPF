<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>资料库-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
 <script src="${ctx}/resources/js/material/material.js"></script> 
</head>
<body>
	<!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
	<!--main(begin)-->
<article class="of db-min-height">
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">资料库</a></span>
		</div>
	</nav>
	<section class="VMware-box w1100">
		<div class="VMware-operate clearfix">
			<div class="fl ml20 sort-operate">
			</div>
			<div class="fr">
				<div class="VMware-search-box fl mt10">
					<div class="pr search-box">
						<span class="search-dropdown fsize14 c-2b3">文档</span>
						<div class="dropdown-list">
							<ul>
								<li class="dropdown-item">
									<a href="javascript:void(0);" id="-1" onclick="changeType(-1)"  >全部</a>
								</li>
								<li class="dropdown-item">
									<a href="javascript:void(0);" id="0" onclick="changeType(0)"  >文档</a>
								</li>
								<li class="dropdown-item">
									<a href="javascript:void(0);" id="1" onclick="changeType(1)">视频</a>
								</li>
								<li class="dropdown-item">
									<a href="javascript:void(0);" id="2" onclick="changeType(2)">图片</a>
								</li>
								<li class="dropdown-item">
									<a href="javascript:void(0);" id="3" onclick="changeType(3)">数据</a>
								</li>
								<li class="dropdown-item">
									<a href="javascript:void(0);" id="4" onclick="changeType(4)">工具</a>
								</li>
								<li class="dropdown-item">
									<a href="javascript:void(0);" id="5" onclick="changeType(5)">源代码</a>
								</li>
							</ul>
						</div>
					</div>
					<input type="text" id="keywords" placeholder="请输入关键字">
				</div>
			 	<a href="javascript:void(0);" id="search" class="btn search-btn">搜索</a>
			</div>
		</div>
		<div class="dataBank-list mt30 of" id="content-r">
			<ul class="content-r">
				
			</ul>
		</div>
		<!--分页-->
			<div id="paginator-tool" style="margin: auto;"></div>
					<!--/paging(begin)-->
	</section>
</article>
<!--main(end)-->
	<!-- 公共尾引入 -->
	<%@ include file="/common/foot.jsp"%>
	<!-- /公共尾引入 -->
</body>
</html>
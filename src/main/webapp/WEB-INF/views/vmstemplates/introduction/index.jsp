<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>虚拟机模板-入门</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
<script type="text/javascript" src="${ctx}/resources/js/ajaxfileupload.js"></script>
<script src="${ctx}/resources/js/vmstemplates/introduction/introduction.js"></script> 
</head>
<body>
<!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
<article>
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">虚拟机模板</a><i>-</i>入门</span>
		</div>
	</nav>
	<section  id="content-r" class="vm-template-box  w1100">
		<div class="vm-template-autoHeight">
			<p class="vm-template-title">虚拟机和模板视图提供虚拟机以及虚拟机模板的创建、操作、管理功能。</p>
			<div  class="clearfix mt30">
				<ul>
					<li class="vm-template-item" id="create-virtual">
						<a href="javascript:void(0);" ><img src="${ctx}/resources/images/vm/vm_line_11.png" alt=""></a>
					</li>
					<!--  
					<li class="vm-template-item">
						<a href="javascript:void(0);"><img src="${ctx}/resources/images/vm/vm_line_12.png" alt=""></a>
					</li>
					-->
					<li class="vm-template-item" onclick="importTemplate()">
						<a href="javascript:void(0);"><img src="${ctx}/resources/images/vm/vm_line_21.png" alt=""></a>
					</li>
					<!-- 
					<li class="vm-template-item">
						<a href="javascript:void(0);"><img src="${ctx}/resources/images/vm/vm_line_22.png" alt=""></a>
					</li>
					-->
				</ul>
			</div>
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
</body>
</html>
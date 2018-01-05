<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>系统管理-资源比例设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
 <script src="${ctx}/resources/js/system/resource/resource.js"></script> 
</head>
<body>
	<!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
	<!--main(begin)-->
	<article>
		<nav class="com-nav">
			<div class="w1100">
				<span class="fsize14">当前位置：<a class="base-color fsize14"
					href="javascript:void(0);" onclick="cancel()">系统管理</a></span>
			</div>
		</nav>
		<section class="w1100 teachingPlan-content of">
		<div class="et-manage-nav">
		<dl>
			<dt class="et-nav-title">
				<a href="javascript:void(0);">系统管理</a>
			</dt>
			<!-- 公共左菜单引入 -->
			<c:import url="/common/menu.jsp">
				<c:param name="modelId" value="system"></c:param>
				<c:param name="menuId"  value="resource"></c:param>
			</c:import>
			</dl>
			</div>
					<form class="form-inline"  id="edit-res-form">
			<div id="content-r" class="et-manage-content">
				<p class="et-manage-title">
					<span>资源比例设置</span>
				</p>
				<div id="message" class="et-teachingPlan-content">
						<div class="form-group">
						<input type="hidden" id="Id" name="Id" value="${resource.id }" />
							<label>课堂比例</label>
							<input type="text" class="form-control classe"  placeholder="请输入1-10"  id="classExperimentProportion" name="classExperimentProportion" value="${resource.classExperimentProportion }"  onkeyup="changeNum(0);">
						</div>
						<div class="form-group">
							<label>开放比例</label>
							<input type="text" class="form-control opene" placeholder="请输入1-10"  id="openExperimentProportion" name="openExperimentProportion" value="${resource.openExperimentProportion }" onkeyup="changeNum(1);">
						</div>
						<div class="tac">
							<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="确定" />
							<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取消" />
						</div>
				</div>
			</div>
		</form>
		</section>
	</article>
	<!--/main(end)-->
	<!-- 公共尾引入 -->
	<%@ include file="/common/foot.jsp"%>
	<!-- /公共尾引入 -->
</body>
</html>
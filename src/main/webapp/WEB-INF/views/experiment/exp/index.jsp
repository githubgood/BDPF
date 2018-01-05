<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>课程实验任务</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
<script src="${ctx}/resources/js/experiment/exp/exp.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/ajaxfileupload.js"></script>
</head>
<body class="ly-bg">
	<!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
	<!--main(begin)-->
	<article>
		<nav class="com-nav">
			<div class="w1100">
				<span class="fsize14">当前位置： <a class="base-color fsize14"
					href="javascript:void(0);">实验管理-课程实验任务</a>
				</span>
			</div>
		</nav>
		<section class="w1100 teachingPlan-content of">
			<div class="et-manage-nav">
				<dl>
					<dt class="et-nav-title">
						<a href="javascript:void(0);">实验管理</a>
					</dt>
					<!-- 公共左菜单引入 -->
					<c:import url="/common/menu.jsp">
						<c:param name="modelId" value="experiment"></c:param>
						<c:param name="menuId" value="exp"></c:param>
					</c:import>
				</dl>
			</div>
			<div id="content-r" class="et-manage-content">
				<p class="et-manage-title">
					<span>课程实验任务</span>
				</p>
				<div class="et-manage-operate">
					<div class="VMware-operate">
						<div id="message">
							<div class="fl ml20 score-bar">
								<form class="form-horizontal">
									<div class="form-group mt10">
										<label class="fl control-label mr5 ml20 fsize14 c-2b3">任务</label>
										<select class="fl form-control score-select" id="taskId"
											name="taskId">
											<c:forEach items="${task}" var="item" step="1">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
									</div>
								</form>
							</div>
							<div class="fr">
								<div class="VMware-search-box fl mt10">
									<input type="text" id="keywords" placeholder="请输入实验名称">
								</div>
								<a href="javascript:void(0);" id="search" class="btn search-btn">搜索</a>
							</div>
						</div>
						<div class="VMware-list mt30">
							<div class="table-responsive com-table">
								<table class="table" border="0">
									<thead>
										<tr>
											<th>实验名称</th>
											<th>实验类型</th>
											<th>任务类型</th>
											<th>任务状态</th>
											<th>报告状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="list-tbody">
										<tr>
											<td colspan="6">对不起，暂时没有您所需要的数据！</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!--分页-->
						<div id="paginator-tool" style="margin: auto;"></div>
					</div>
				</div>
			</div>
		</section>
	</article>
	<%@ include file="/common/foot.jsp"%>
	<!--/main(end)-->
	<!--loading-->
	<div class="cd-mask">
		<img src="${ctx}/resources/images/loading.gif" alt="">
	</div>
	<!--/loading-->
</body>
</html>
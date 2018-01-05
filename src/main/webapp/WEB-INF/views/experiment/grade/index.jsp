<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>大数据-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
<script src="${ctx}/resources/js/experiment/grade/grade.js"></script>
</head>
<body class="ly-bg">
	<!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
	<!--main(begin)-->
	<article>
		<nav class="com-nav">
			<div class="w1100">
				<span class="fsize14">当前位置： 
					<a class="base-color fsize14" href="javascript:void(0);">实验管理-成绩查询</a>
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
						<c:param name="menuId" value="grade"></c:param>
					</c:import>
				</dl>
			</div>
			<div id="content-r" class="et-manage-content">
				<p class="et-manage-title">
					<span>成绩查询</span>
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
											<option value="">--请选择--</option>
											<c:forEach items="${task}" var="item" step="1">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select> 
										<c:if test="${sessionScope.loginuser.userType==0}">
										<label class="fl control-label mr5 ml20 fsize14 c-2b3">班级</label>
										<select class="form-control score-select" id="classId" name="classId">
											<option value="">--请选择--</option>
											<c:forEach items="${clas}" var="item" step="1">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
										</c:if>
									</div>
								</form>
							</div>
							<div class="fl ml40">
								<a href="javascript:void(0);" id="search" class="btn search-btn">搜索</a>
							</div>
						</div>
						<div id="back" class="fl ml40" style="display: none">
							<a href="javascript:void(0);" id="btn-cancel" class="btn search-btn">返回</a>
						</div>
					</div>
					<div id="detail" class="VMware-list mt30">
						<div class="table-responsive com-table">
							<table class="table" border="0">
								<thead>
									<tr>
										<th>班级名称</th>
										<th>学号</th>
										<th>学生姓名</th>
										<th>平均成绩</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="list-tbody">
									<tr>
										<td colspan="8">对不起，暂时没有您所需要的数据！</td>
									</tr>
								</tbody>
							</table>
							<!--分页-->
							<div id="paginator-tool" style="margin: auto;"></div>
							<script type="text/javascript">
								/*表格行间隔色*/
								$('.table-list tbody tr:odd').addClass('odd');
							</script>
						</div>
					</div>
				</div>
			</div>
		</section>
	</article>
	<%@ include file="/common/foot.jsp"%>
	<!--/main(end)-->
</body>
</html>
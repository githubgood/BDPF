<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>系统管理-角色管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
 <script src="${ctx}/resources/js/system/role/role.js"></script> 
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
				<c:param name="menuId"  value="role"></c:param>
			</c:import>
			</dl>
			</div>
			<div id="content-r" class="et-manage-content">
				<p class="et-manage-title">
					<span>角色管理</span>
				</p>
				<div class="et-manage-operate">
					<div class="VMware-operate">
						<div class="fl ml20"> 
							<a href="javascript:void(0);" class="btn open-btn mr5" id="addbtn" >添加</a> <a
								href="javascript:void(0);" class="btn close-btn mr5" id="delebtn" >删除</a>
						</div>
						<div class="fr">
							<div class="VMware-search-box fl mt10">
								<input type="text" id="keywords" name="keywords" placeholder="请输入角色名称">
							</div>
							<a href="javascript:void(0);" class="btn search-btn" id="search">搜索</a>
						</div>
					</div>
					<div class="VMware-list mt30">
						<div class="table-responsive com-table">
							<div class="table-responsive com-table">
							<table class="table" border="0">
							<thead>
								<tr>
									<th>
										<p class="checkbox_box">
											<input class="checkBox" id="check-all-list" type="checkbox"  onchange="toggleCheck(this,'list-tbody')">
											<span class="checkBox"></span>
										</p>
									</th>
									<th>角色名称</th>
									<th>角色描述</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="list-tbody">
								<tr>
									<td colspan="4">对不起，暂时没有您所需要的数据！</td>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
					</div>
					<!--paging(begin)-->
					
					<!--分页-->
			<div id="paginator-tool" style="margin: auto;"></div>
					<!--/paging(begin)-->
				</div>
			</div>
		</section>
	</article>
	<!--/main(end)-->
	<!-- 公共尾引入 -->
	<%@ include file="/common/foot.jsp"%>
	<!-- /公共尾引入 -->
</body>
</html>
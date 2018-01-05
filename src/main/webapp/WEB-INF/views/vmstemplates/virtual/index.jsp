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
<script src="${ctx}/resources/js/vmstemplates/virtual/virtual.js"></script>
</head>
<body>
<!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
<!--main(begin)-->
<article>
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">虚拟机模板</a><i>-</i>虚拟机</span>
		</div>
	</nav>
	<section class="VMware-box w1100">
		<div id="content-r"  class="VMware-operate clearfix">
			<div class="fl ml20">
				<a href="javascript:void(0);" class="btn open-btn mr5" id="batchstart">开机</a>
				<a href="javascript:void(0);" class="btn close-btn mr5" id="batchstop">关机</a>
				<a href="javascript:void(0);" class="btn reset-btn mr5" id="batchRestart">重启</a>
				<a href="javascript:void(0);" class="btn delete-btn mr5" id="batchDel">删除</a>
			</div>
			<div class="fr">
				<div class="VMware-search-box VM-search-box fl mt10">
				<label>状态：</label>
					<select class="form-control" name="" id="status">
						    <option value="" > --请选择--</option>
		                    <option value="运行中" >运行中 </option>
		                    <option value="已停止" >已停止 </option>
					 </select>
					 <label>班级：</label>
						<select class="form-control" name="" id="classId">
						    <option value="" > --请选择--</option>
				         <c:forEach items="${clas}" var="item">
		                    <option value="${item.id}" > ${item.name}</option>
				         </c:forEach>
					 </select>
							<input type="text" id="keywords" class="form-control" placeholder="请输入虚拟机名称">

				</div>
				<a href="javascript:void(0);" id="search" class="btn search-btn">搜索</a>
			</div>
		</div>
					<div class="VMware-list mt30">
						<div class="table-responsive com-table">
							<table class="table" border="0">
							<thead>
								<tr>
									<th>
										<p class="checkbox_box">
											<input class="checkBox" id="check-all-list" type="checkbox" onchange="toggleCheck(this,'list-tbody')">
											<span class="checkBox"></span>
										</p>
									</th>
									<th>名称</th>
									<th>状态</th>
									<th>类型</th>
									<th>IP地址</th>
									<th>VNC地址</th>
									<th style="width:10%;">操作</th>
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
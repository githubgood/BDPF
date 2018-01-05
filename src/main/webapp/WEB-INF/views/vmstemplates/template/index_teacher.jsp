<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>虚拟机模板-模板</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
<script type="text/javascript" src="${ctx}/resources/js/vmstemplates/template/template.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ajaxfileupload.js"></script>
<script type="text/javascript">  
var userType = ${ loginuser.userType};
</script>  
</head>
<body>
<!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
<!--main(begin)-->
<article class="of db-min-height">
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">虚拟机模板</a><i>-</i>模板</span>
		</div>
	</nav>
	<section class="VMware-box w1100">
	<div id="content-r">
		<div class="VMware-operate clearfix">
			<div class="fl ml20">
				<!-- 管理员显示 -->
				<c:if test="${ loginuser.userType == 2}">
				<a href="javascript:void(0);" class="btn open-btn mr5" id="addbtn" style="letter-spacing: inherit;text-indent: 0">创建模板</a>
				<a href="javascript:void(0);" class="btn close-btn mr5" id="delbtn" style="letter-spacing: inherit;text-indent: 0">删除模板</a>
				</c:if>
			</div>
			<div class="fr">
				<div class="VMware-search-box fl mt10">
					<input type="text" id="keywords" placeholder="请输入模板名称">
				</div>
				<a href="javascript:void(0);" id="search" class="btn search-btn">搜索</a>
			</div>
		</div>
		<div class="VMware-list mt30">
			<table class="table table-responsive create-MI-box" border="0">
				<thead>
				<tr>
				<!-- 管理员显示-->
				<c:if test="${ loginuser.userType == 2}">
					<th class="tac">
						<p class="checkbox_box">
							<input class="checkBox" type="checkbox" id="check-all-list" onchange="toggleCheck(this,'list-tbody')" name="" value="">
							<span class="checkBox"></span>
						</p>
					</th>
				</c:if>
					
					<th>
						名称
					</th>
					<th>
						类型
					</th>
					<th>
						描述
					</th>
					<th>
						状态
					</th>
					<th>
						可见性
					</th>
					<th>
						受保护的
					</th>
					<th>
						磁盘格式
					</th>
					<th>
						大小
					</th>
					<!-- 管理员显示-->
				<c:if test="${ loginuser.userType == 2}">
					<th>
						操作
					</th>
				</c:if>
				</tr>
				</thead>
				<tbody id="list-tbody">
					<tr>
					<!-- 管理员显示添加班级 -->
				<c:if test="${ loginuser.userType == 2}">
						<td colspan="8">对不起，暂时没有您所需要的数据！</td>
				</c:if>
				<!-- 管理员显示添加班级 -->
				<c:if test="${ loginuser.userType != 2}">
						<td colspan="6">对不起，暂时没有您所需要的数据！</td>
				</c:if>
				</tr>
				</tbody>
			</table>
		</div>
		<!--paging(begin)-->
		<div id="paginator-tool" style="margin: auto;"></div>
		<!--/paging(begin)-->
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>大数据-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
<script src="${ctx}/resources/js/experiment/check/person.js"></script>
</head>
<body class="ly-bg">
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
	<!--main(begin)-->
	
<article>
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">实验批改</a><i>-</i>实验</span>
		</div>
	</nav>
	<input type="hidden" id="taskId" name="taskId" value="${taskId}">
	<input type="hidden" id="courseResourceId" name="courseResourceId" value="${courseResourceId}">
	<input type="hidden" id="classId" name="classId" value="${classId}">
	<section class="w1100 et-main et-checkBox">
		<div class="et-title of">
			<span id="title" class="fl fsize16">实训一Hadoop完全分布式安装部署</span>
		</div>
		<div  id="content-r" class="et-content of">
			<div class="fl et-left-content">
				<div class="et-left-nav of">
					<a href="javascript:void(0);" class="right-line pr active">未批改</a>
					<a href="javascript:void(0);" class="pr"> 已批改</a>
				</div>
				<div class="et-left-item of">
					<div class="of tab" >
						<ul id="list-Y">
					
						</ul>
						<div id="paginator-tool" style="margin: auto;"></div>
					</div>
					<div id ="correct" class="of tab" style="display: none;">
						<ul id="list-X">
							
						</ul>
						<div id="statuspaginator-tool" style="margin: auto;"></div>
					</div>
				</div>
			</div>
			<div class="fl of et-right-content pr" >
				<div class="et-right-item">
				</div>
				<div class="check-job-box">
					<ul>
						<li class="grade-box fl">
							<p>
								<span class="fsize14 c-2b3 vam">评分</span>
								<input type="text" id="score" name="score">
							</p>
						</li>
						<li class="remark-box fl">
							<p>
								<span class="fsize14 c-2b3">评语</span>
								<textarea name="" id="comment" name="comment"></textarea>
							</p>
						</li>
						<li class="fl mt10">
							<a href="javascript:void(0);" id="submit-btn" class="submit-btn">提交</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
</article>
<%@ include file="/common/foot.jsp"%>
	<!--/main(end)-->
</html>
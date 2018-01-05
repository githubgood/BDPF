<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ include file="/common/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta HTTP-EQUIV="pragma" CONTENT="no-cache" />
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate" />
<meta HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Insert title here</title>
<script >
var course = ${course};
</script>
</head> 
<body>
        <!-- 公共头引入 -->
	<%@ include file="/common/head.jsp"%>
	<!-- /公共头引入 -->
	<!--main(begin)-->
    <article>
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);" onclick="menus('/material')">资料库</a><i>-</i>预览</span>
		</div>
	</nav>
	<section class="w1100 et-main et-checkBox et-checkBox-preview of">
		<div class="et-content of">
			<div class="fl et-left-content">
				<div class="et-left-item of">
					<div class="et-left-title">
						<p class="fsize14 c-656">当前文档</p>
						<input type="hidden" id="cid" value="${course.id }">
						<input type="hidden" id="ctype" value="${course.fileType }">
						<p id="titles" class="fsize16 c-2b3 mt15">${course.title}</p>
					</div>
					<div class="et-left-list">
						<p class="mb20">同标签文档</p>
						<ul id="list-tbody">
						</ul>
						<div class="et-paging of">
							<!--分页-->
						<div id="paginator-tool" style="margin: auto;"></div>
					<!--/paging(begin)-->
				</div>
						</div>
					</div>
				</div>
				<div class="fl of et-right-content pr" >
					<div class="et-right-title fsize16 c-656">
						${course.title}
					</div>
					<div class="et-right-item">
					<iframe src="${course.path }" style="width:100%;height:745px;"></iframe>
					</div>
				</div>
			</div>
	</section>
</article>
<!--main(end)-->
	<!-- 公共尾引入 -->
	<%@ include file="/common/foot.jsp"%>
	<!-- /公共尾引入 -->
	<script type="text/javascript" src="${ctx}/resources/js/material/showpdf.js"></script>  
</body>
</html>
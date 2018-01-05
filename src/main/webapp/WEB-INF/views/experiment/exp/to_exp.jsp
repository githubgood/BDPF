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
<script type="text/javascript" src="${ctx}/resources/js/experiment/exp/to_exp.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ajaxfileupload.js"></script>
<title>进入实验页面</title>
<script type="text/javascript">
var course = ${listCourse};
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
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">数据站点</a><i>-</i>实验</span>
		</div>
	</nav>
	<section class="w1100 et-main">
		<div class="et-operate clearfix" style="z-index:3">
			<ul>
				<!-- <li class="fl et-btns mt15">
					<a href="javascript:void(0);" class="save-btn btn mr5">保存</a>
					<a href="javascript:void(0);" class="submit-btn btn mr5">提交</a>
					<a href="javascript:void(0);" class="down-btn btn">模板下载</a>
					<a href="javascript:void(0);" class="upload-btn btn">上传报告</a>
				</li> -->
				<li class="fl mt15">
					<!-- <i class="dividing_line ml10 mr15"></i> -->
					<div class="et-layout">
						<span class="left-layout">
							<i class="db-icon et-ly-icon"></i>
						</span>
						<span class="both-layout active">
							<i class="db-icon et-by-icon"></i>
						</span>
						<span class="right-layout">
							<i class="db-icon et-ry-icon"></i>
						</span>
					</div>
					<i class="dividing_line ml10 mr20"></i>
				</li>
				<!-- <li class="fl">
					<div class="form-group">
						<label>远程控制VM </label>
						<select  class="form-control ml10" id="iplist">
						</select>
					</div>
				</li> -->
				<li class="fl et-title of" style="height:65px;line-height:65px;width:900px;padding:0;">
					<span class="fl">${grade.title }</span>
					<div class="fr et-time" id="countdown">
						<strong>00</strong><i>:</i><strong>00</strong><i>:</i><strong>00</strong>
					</div>
				</li>
			</ul>
		</div>
		<div class="et-content of" style="margin:-18px 0 60px 0;padding-bottom:0;">
			<div class="fl et-left-content" style="z-index: 100">
				<div class="et-left-nav of">
					<a href="javascript:void(0);" class="right-line active">实验指导书</a>
					<a href="javascript:void(0);" class="right-line pl20">实验工具</a>
					<a href="javascript:void(0);" class="right-line pl20">实验报告</a>
					<a href="javascript:void(0);" class="right-line pl20">我的组员</a>
				</div>
				<div class="et-left-item of">
					<div class="of tab toggle-box">
						<div class="et-width" style="z-index: 1">
							<iframe src="${grade.reportBook }" style="width:100%;height:674px;"></iframe>
						</div>
					</div>
					<div class="of tab"  style="display: none;">
						<ul>
							<c:forEach items="${listCourse }" var="course">
							<li class="tab_con active">
								<dl onclick="dowload('${course.path}')">
									 <dt class="fl">
									 	<c:if test="${course.fileType == 1}">
										<img src="${ctx}/resources/images/dataBank/bank_icon3.png" alt="">
									 	</c:if>
									 	<c:if test="${course.fileType == 2}">
										<img src="${ctx}/resources/images/dataBank/bank_icon2.png" alt="">
									 	</c:if>
									 	<c:if test="${course.fileType == 3}">
										<img src="${ctx}/resources/images/experiment/xftp.png" alt="">
									 	</c:if>
									 	<c:if test="${course.fileType == 4}">
										<img src="${ctx}/resources/images/dataBank/bank_icon5.png" alt="">
									 	</c:if>
									 	<c:if test="${course.fileType == 5}">
										<img src="${ctx}/resources/images/experiment/xftp.png" alt="">
									 	</c:if>
									</dt> 
									<dd class="fl" >
										<span>${course.title }</span>
										 <c:if test="${course.fileType == 1}">
										<p>文件类型:视频</p>
										</c:if>
										<c:if test="${course.fileType == 2}">
										<p>文件类型:图片</p>
										</c:if>
										<c:if test="${course.fileType == 3}">
										<p>文件类型:数据</p>
										</c:if>
										<c:if test="${course.fileType == 4}">
										<p>文件类型:工具</p>
										</c:if>
										<c:if test="${course.fileType == 5}">
										<p>文件类型:源代码</p>
										</c:if>
									</dd>
								</dl>
							</li>
							</c:forEach>
						</ul>
					</div>
					<div class="of tab et-report toggle-box" id="message"  style="display: none;">
						<form id="send-exp-form">
						<input type="hidden" id="id" name="id" value="${grade.id }" >
          				<input type="hidden" id="groupId" name="groupId" value="${grade.groupId }" >
          				<input type="hidden" id="minTime" name="minTime" value="${times.minTime }" >
          				<input type="hidden" id="maxTime" name="maxTime" value="${times.maxTime }" >
          				<input type="hidden" id="addTime" name="addTime" value="${times.addTime }" >
						<input type="hidden" id="time" name="time" value="${grade.time }">
						<input type="hidden" id="num" name="num" value="${grade.num }">
						<input type="hidden" id="vmId" name="vmId" value="${vmId }">
							<ul>
								<li>
									<div class="form-group">
										<label class="com-label">实验名称：</label>
										<span class="ml10 fsize14" >${grade.title }</span>
									</div>
								</li>
								<li class="mb10">
									<label class="com-label">状态：</label>
									<span class="ml10 fsize14"><c:if test="${grade.reportStatus == '0' }">未提交</c:if><c:if test="${grade.reportStatus == '1' }">已提交</c:if></span>
								</li>
								<li class="mb10">
									<label class="com-label">模板：</label>
									<span class="ml10 fsize14">该实验没有添加实验报告模板</span>
								</li>
								<li class="file-box mb40">
									<label class="com-label">实验报告：</label>
									<input type='text' name='textfield' id='textfield' class='txt form-control' placeholder="请选择文件上传!" />
									<input type='button' class='btn' value='选择文件' />
									<input type="file" id="file" name="file" class="file"  accept=".doc" size="28" onchange="document.getElementById('textfield').value=this.value" />
								</li>
								<li>
									<input type="submit" id="btn-save" name="btn-save" class="btn submit-btn" value="提交" onclick="exp.validateForm('send-exp-form')">
									<input type="button" id="btn-cancel" name="btn-cancel" class="btn cancel-btn" value="取消" onclick="exp.prePageHtml">
								</li>
							</ul>
						</form>
					</div>
					<div class="of tab"  style="display: none;">
						<ul>
						<c:forEach items="${listUser }" var="user">
							<li class="tab_con active person-box">
								<dl class="">
									<dt class="fl person-img">
										<i class="et-person-icon"></i>
									</dt>
									<dd class="fl">
										<span>${user.name }</span>
									</dd>
								</dl>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
            <div class="et-drag" style="z-index: 105"></div>
			<div class="fl of et-right-content" style="height: auto;z-index: 100">
				<div class="et-right-nav pr" id="nodelist">
				</div>
				<div class="et-left-item et-right-list" id="urllist">
				</div>
			</div>
			<div id="coverIframe" style="z-index:1;width:100%;position:absolute;top:199px;height:800px"></div>
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
/*初始化*/
var clickX,leftOffset,nextW2;
var dragging = false;
var doc = document;
var labBtn = $(".et-drag");
var wrapWidth = $(".et-content").width();
/*鼠标按下时*/
labBtn.bind("mousedown",function(){
	dragging = true;
	leftOffset = $(".et-content").offset().left;
	doc.getElementById("coverIframe").style.zIndex=103;
	/*	console.log("leftOffset:"+leftOffset);*/
})
/*鼠标移动位置*/
doc.onmousemove = function(e){
	if(dragging){
		//或者鼠标指针的位置
		clickX = e.pageX;
		/*	console.log("clickX:"+clickX);*/
		var myoffset = 80;
		//移动第一个拖动按钮左边不出界
		if(clickX>leftOffset+20){
			labBtn.css("left",myoffset+clickX  - leftOffset + "px");
			labBtn.prev().width(clickX - leftOffset-25 + "px");
			nextW2 = clickX - leftOffset;
			labBtn.next().width(wrapWidth - nextW2-25);
		}else{
			labBtn.css("left",(myoffset + 33) + "px");
		}
		//移动第一个拖动按钮右边不出界
		if(clickX>leftOffset+wrapWidth-20){
			labBtn.css("left",(myoffset+wrapWidth-16) + 'px');
			labBtn.prev().width( wrapWidth - 40 + 'px' );
			labBtn.next().width( '10px' );
		}
	}
}
/*鼠标松开*/
$(doc).mouseup(function(e) {
	dragging = false;
	e.cancelBubble = true;
	doc.getElementById("coverIframe").style.zIndex=1;
})
</script>
</body>
</html>
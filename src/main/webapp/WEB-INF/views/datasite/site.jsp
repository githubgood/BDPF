<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>大数据-数据领域</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
	<meta name="renderer" content="webkit">

	<link rel="stylesheet" href="${ctx}/resources/js/artDialog/css/ui-dialog.css">
	<script src="${ctx}/resources/js/artDialog/js/dialog-min.js"></script>
    <script src="${ctx}/resources/js/dialog.js"></script>
     <script src="${ctx}/resources/js/ajaxfileupload.js"></script>
</head>
<body class="ly-bg">

<%@ include file="/common/head.jsp"%>
<!-- /公共头引入 -->
<!--main(begin)-->

<article>

	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">数据站点</a><i>-</i>数据领域</span>
		</div>
	</nav>
	<div id="content-site">
	<section class="fd-dataList-box w1100 of">
	<div id="main">
		</div>
		
	</section>
	</div>
</article>

<!--main(end)-->
<!--弹窗-->
<div id="titleBox">
<div class="modal modal-update fade modal_align" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">添加数据领域</h4>
			</div>
			<div class="modal-body">
				<form class="form-inline">
					<div class="form-group" style="display: block;">
						<label>数据领域：</label>
						<input type="text" class="form-control"  placeholder="" id="name">
						<span>（不超过6个字）</span>
					</div>
					<div class="form-group">
						<label>描述：</label>
						<input type="text" class="form-control describe-text"  placeholder="" id="abstract">
					</div>
					<div class="form-group  pr  of">
						<label class="fl">图标：</label>
						<div class="default-icon">
							<i class="default-name">默认</i>
							<img src="${ctx}/resources/images/data/upload_icon.png" alt="" width="60" height="52">
						</div>
						<div class="upload-btn fl">
							<input type="file" onchange="uploadSiteImg()" name="btnFile" id="btnFile" accept=".png">
							<input type="hidden" id="imgName" value="field_icon2.png">
							<input type="hidden" id="siteId" >
							<p>请上传 png 格式图标,图片大小应小于200X80</p>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-save mr30" onclick="saveSite()">保存</button>
				<button type="button" class="btn btn-default btn-cancel"  onclick="cancelSite()" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</div>
<!--/弹窗-->
<!--footer(begin)-->
<%@ include file="/common/foot.jsp"%>
<!--footer(end)-->
<script src="${ctx}/resources/js/datasite/site.js"></script>
</body>
</html>
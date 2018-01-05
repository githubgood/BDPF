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
	<section class="fd-dataList-box w1100" >
	<div id="main">
		</div>
		
	</section>
	</div>
</article>

<!--main(end)-->
<!--弹窗-->
<div class="modal fade modal_align" tabindex="-1" role="dialog">
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
					<div class="form-group pr of">
						<label class="fl">图标：</label>
						<div class="default-icon">
							<i class="default-name">默认</i>
							<img src="${ctx}/resources/images/data/upload_icon.png" alt="" width="60" height="52">
						</div>
						</div>
				</form>
			</div>
			</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/弹窗-->
<!--footer(begin)-->
<footer class="index-footer">
	<div class="w1100 tac fsize14">Copyright © 2017 - 2020 深圳市讯方技术股份有限公司 粤ICP备12068225号-1</div>
</footer>
<!--footer(end)-->
<script src="${ctx}/resources/js/datasite/site.js"></script>

<script>
$.ajaxSetup({  
    async : false  
});
var html="";
html+='<ul class="clearfix dataBank-list">';
var url=  window.ctx +'/datasite/getArea';
$.get(url,function(reslut){
for(var i=0;i<reslut.length;i++){

	html+='<li class="fd-dataItem fd-dataField tac" onclick="enter(\''+reslut[i].id+'\')">';
		html+='<dl>';
			html+='<dt class="dataBank-item-operate dataField-item tac">';
			html+='<p>';
			html+='<img src="/res/datasite/titleImg/'+reslut[i].imgName+'" alt="">';
			html+='</p>';
			/* html+='<p>';
		    html+='<i class="dividing_line"></i>';
			html+='</p>'; */
			html+='</dt>';
			html+='<dd class="dataField-infor tac">';
			html+='<h2>'+reslut[i].name+'</h2>';
			html+='<p class="mt5">';
			html+=reslut[i].siteAbstract;
			html+='</p>';
			html+='</dd>';
			html+='</dl>';
			html+='</li>';
}
});
html+='</ul>';

document.getElementById('main').innerHTML=html;

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
		//弹窗
		/* $(".fd-add-box").on("click",function(){
			$('.modal').modal('show');
			$(".modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
		}) */
		
	})
		
	function showBox(){
		isIn=1;
		$('.modal').modal('show');
		$(".modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
	}
    function enter(id){
		
			var url=  window.ctx +'/datasite/area';
			
			var data=[
		                 {name:'id',value:id}
		                 ];
			 $.post(url,data,function(reslut){
				 $('#content-site').html(reslut);
// 				 window.location.href=url;
				 });
			
		}

    
</script>
</body>
</html>
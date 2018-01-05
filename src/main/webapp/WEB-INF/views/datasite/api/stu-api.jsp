<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>数据站点-列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
	<meta name="renderer" content="webkit">

	<link rel="stylesheet" href="${ctx}/resources/js/artDialog/css/ui-dialog.css">
	<script src="${ctx}/resources/js/artDialog/js/dialog-min.js"></script>
	<script src="${ctx}/resources/js/dialog.js"></script>
</head>
<body class="ly-bg">
<div  id="content-api">
<%@ include file="/common/head.jsp"%>
<!-- /公共头引入 -->    
<!--main(begin)-->
<article>
	<nav class="com-nav">
		<div class="w1100">
			<span class="fsize14">当前位置：<a class="base-color fsize14" href="javascript:void(0);">API服务</a></span>
		</div>
	</nav>
	<section class="w1100 teachingPlan-content pb30">
		<div class="et-manage-nav" id="site">
			
		</div>
		<div class="et-manage-content" id="content-r">
			
			<div class="et-manage-operate-datasite" id=main>
			
			</div>
			<br>
			<div id="paginator-tool"  style="margin: auto;"></div>
		</div>
		
		
	</section>
</article>
<!--/main(end)-->
<!--footer(begin)-->
<footer class="index-footer">
	<div class="w1100 tac fsize14">Copyright © 2017 - 2020 深圳市讯方技术股份有限公司 粤ICP备12068225号-1</div>
</footer>
</div>
<!--footer(end)-->

<script>
$.ajaxSetup({  
    async : false  
});
var siteName='${siteName}';
var id='${id}';
var addApi = {};
addApi.prePageHtml = null;
addApi.prePageHtml = $('#content-r').html();
var html1="";
html1+='<dl>';
html1+='<dt class="et-nav-title"><a href="javascript:void(0);">API服务</a></dt>';
var url=  window.ctx +'/datasite/getArea';
$.get(url,function(reslut){
for(var i=0;i<reslut.length;i++){
	html1+='<dd class="et-nav-item" style="position: relative;"><i class="active-line"></i><a href="javascript:void(0);" onclick="choose(\''+reslut[i].id+'\',\''+reslut[i].name+'\')">'+reslut[i].name+'</a></dd>';
}
});
html1+='</dl>';
document.getElementById('site').innerHTML=html1;

list(1, 10);

function list(pageNo, pageSize){
	var html="";
	var url=  window.ctx +'/datasite/getAreaList';
	var data = [
	            {name:'keywords',value:$('#keywords').val()},
	            {name:'pageSize',value:pageSize},
	            {name:'type',value:"1"},
	            {name:'pageNo',value:pageNo},
	            {name:'id',value:id}
	];
	$.get(url,data,function(page){
		
		html+='<p class="et-manage-title">';
		
		if(id==""){
			html+='<span>API接口</span>';
		}else if(page.result.length==0){
			html+='<span>API接口</span>';
		}else{
			html+='<span>'+page.result[0].name+'</span>';
		}
		html+='</p>';
		html+='<div class="et-manage-operate">';
		html+='<div class="VMware-operate">';
		html+='<div class="fl ml20">';
		
		html+='</div>';
		html+='</div>';
		html+='<div class="zoologyCulture-list mt30">';
		html+='<ul>';

			for(var i=0;i<page.result.length;i++){
				html+='<li class="pr zoologyCulture-item" >';
				html+='<a href="javascript:void();"><div class="zc-infor" onclick="enter(\''+page.result[i].id+'\')">';
				html+='<h2>';
				html+='<span class="mr5" >'+page.result[i].title+'</span>';
				html+='<i class="file-format csv-format">CSV</i>&nbsp;';
				html+='<i class="file-format json-format">JSON</i>&nbsp;';
				html+='<i class="file-format xml-format">XML</i>&nbsp;';
				html+='<i class="file-format xls-format">XLS</i>&nbsp;';
				html+='</h2>';
				html+='<p>更新时间：'+page.result[i].updateDate+'</p>';
				html+='<p>数据领域：'+page.result[i].name+'</p>';
				html+='<p>资源摘要： '+page.result[i].areAbstract+'</p>';
				html+='</div>';
				html+='</a>';
				html+='</li>';
			}
			refreshPaginator(page.totalCount, pageSize, pageNo);
		
		
		});
		    html+='</ul>';
			html+='</div>';
			html+='</div>';
			
			document.getElementById('main').innerHTML=html;
};


function refreshPaginator(totalCount, pageSize, pageNo){
	$('#paginator-tool').smartpaginator({
		totalrecords : totalCount,
		recordsperpage : pageSize,
		initval: pageNo,
		length : 1,
		next : '下一页',
		prev : '上一页',
		first : '首页',
		last : '尾页',
		theme : 'red',
		controlsalways : true,
		onchange : function(newPage) {
			list(newPage, pageSize);
		}
	});
	
};


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
		//table
		/* $(document).ready(function(){
			$('#myTable').DataTable({
				paging: false,
				searching: false,
				ordering:  false
			});
		}); */
	})
	
	function choose(id,name){
		
		var url=  window.ctx +'/datasite/api';
		var data=[
	                 {name:'id',value:id},
	                 {name:'siteName',value:name}
	                 ];
		 $.post(url,data,function(reslut){
			 $('#content-api').html(reslut);
// 			 window.location.href=url;
			 });
		 
	}

    function enter(id){
    	var url = window.ctx + '/datasite/to-api-detail';
		var data=[
	                 {name:'id',value:id},
	                 {name:'type',value:"1"}
	                 ];
    	$.get(url,data, function(page){
    		$('#content-r').html(page);
    		
    		addApi.validateForm('detail-area-form');
    		
    	});
	}
</script>
</body>
</html>
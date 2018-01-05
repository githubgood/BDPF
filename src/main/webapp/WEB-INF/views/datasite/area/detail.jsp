<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detail-area-form" class="form-inline">
		
<!-- 			<p class="et-manage-title"> -->
<!-- 				<span>查看数据</span> -->
<!-- 			</p> -->
<!-- 			<div class="et-manage-operate"> -->
<!-- 				<div class="bd-infor-title"> -->
<%-- 				<h2>${area.title}</h2> --%>
<!-- 					<p class="mt10"> -->
<%-- 						<span class="mr40">发布日期：${area.releaseDate} </span> --%>
<%-- 						<span>更新日期：${area.updateDate}</span> --%>
<!-- 					</p> -->
<!-- 				</div> -->
					
					
					
<!-- 				</div> -->
<!-- 				<div class="bd-infor-content of"> -->
<!-- 					<div class="bd-infor-left fl" id="editTable"> -->
			 
<!-- 					</div> -->
<!-- 					<div class="bd-infor-right bd-infor-edit of"> -->
<!-- 						<ul> -->
<!-- 							<li class="bd-infor-list"> -->
<!-- 							<span>数据领域：</span> -->
<%-- 							${area.name} --%>
<!-- 							</li> -->
<!-- 							<li class="bd-infor-list"> -->
							
<!-- 									<span>摘要：</span> -->
<%-- 									${area.areAbstract} --%>
								
<!-- 							</li> -->
<!-- 							<li class="bd-infor-list"> -->
								
<!-- 									<span>关键字：</span> -->
<%-- 									${area.keyword} --%>
								
<!-- 							</li> -->
<!-- 							<li class="bd-infor-list"> -->
								
<!-- 									<span>数据提供方单位：</span> -->
<%-- 									${area.dataProviderEmployer} --%>
							
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								<a href="javascript:void(0);" class="btn bd-down-btn  mt10" onclick="changeType(0)"> -->
<!-- 									<i class="db-icon down-icon"></i> -->
<!-- 									资源下载 -->
<!-- 								</a> -->
<!-- 							</li> -->
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="edit-btns tac"> -->
<!-- 					<button type="button" onclick="ok()" class="btn btn-primary btn-save mr30">确定</button> -->
<!-- 					</div> -->
<!-- 			</div> -->

<!--main(begin)-->

	
		
		
			<p class="et-manage-title">
				<span>${area.title}</span>
			</p>
			<div class="et-manage-content-dataarea">
				<div class="bd-infor-title">
					<h2>${area.title}</h2>
					<p class="mt10">
						<span class="mr40">发布日期：${area.releaseDate} </span>
						<span>更新日期：${area.updateDate}</span>
					</p>
				</div>
				<div class="bd-infor-content of">
					<div class="bd-infor-right of" >
						<ul>
							<li class="bd-infor-list">
								<span>数据领域：${area.name}</span>
								<span class="fr">摘要：${area.areAbstract}</span>
							</li>
							<li class="bd-infor-list">
								<span>关键字：<i class="bd-tag">${area.keyword}</i></span>
								<span class="fr">数据提供方单位：${area.dataProviderEmployer}</span>
							</li>
							
						</ul>
					</div>
					<div class="bd-infor-left" id="editTable">
						
					</div>
					<div class="bd-resource-down">
						<p class="bd-resource-title">资源下载</p>
						<div class="ml20 mr20 pb20" id="res">
						
						</div>
					</div>
					<div class="edit-btns-dataarea tac">
 					<button type="button" onclick="ok()" class="btn btn-primary btn-save mr30">返回</button>
					</div> 
				</div>
			</div>
		
	

<!--/main(end)-->

</form>
<script>
$.ajaxSetup({  
    async : false  
});
var z=0;

var y=0;

editTable();

var current0="current";
var current1="";
var current2="";
var current3="";
var current4="";
changeType(4);
function showDownload(type){
	
	var html="";
	var url=  window.ctx +'/datasite/getRes';
	var data = [
	           {name:'type',value:type},
	           {name:'id',value:'${area.id}'}
	];
	$.get(url,data,function(reslut){
		html+='<div style="height:200px;list-style-type:none;overflow:auto;">';
		html+='<p class="mt10">'
			html+='<a href="javascript:void (0);" onclick="changeType(0)" class="btn down-btn '+current0+' mr5">CSV</a>'
			html+='<a href="javascript:void (0);" onclick="changeType(1)" class="btn down-btn '+current1+' mr5">JSON</a>'
			html+='<a href="javascript:void (0);" onclick="changeType(2)" class="btn down-btn '+current2+' mr5">XML</a>'
			html+='<a href="javascript:void (0);" onclick="changeType(3)" class="btn down-btn '+current3+' mr5">XLS</a>'
			html+='<a href="javascript:void (0);" onclick="changeType(4)" class="btn down-btn '+current4+' mr5">全部</a>'
			html+='</p>'
		for(var i=0;i<reslut.length;i++){
			html+='<p class="pb10">'
			//html+='<a href="'+window.ctx+'/resources/uploadFiles/'+reslut[i].name+'" span class="ml10 fsize14 c-2b3">'+reslut[i].name+'</span></a>'
			//html+='<a  onmouseover= "this.style.color =\'blue\'" onMouseOut= "this.style.color =\'black\'" href="javascript:void (0);"  onclick="getFile(\''+reslut[i].name+'\')" span class="ml10 fsize14 c-2b3">'+reslut[i].name+'</span></a>'
			html+='<a  onmouseover= "this.style.color =\'blue\'" onMouseOut= "this.style.color =\'black\'" href="/res/datasite/'+reslut[i].name+'" download="'+reslut[i].name+'" span class="ml10 fsize14 c-2b3">'+reslut[i].name+'</span></a>'
			html+='</p>'
			}
		
		html+='</div>';
		});
	
	
// 	dialog({
// 		    id: 'download',
// 		    content:html,
// 		    lock: true,
// 		    fixed: true,
// 		    width:590, height:200,
// 		    title:'资源下载',
// 		    ok: function () {
// 		  	 },
// 		    okValue: '确定',
		   
// 		}).show();
	
	
 	document.getElementById('res').innerHTML=html;
// 	$('.modal').modal('show');
// 	$(".modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
}

function getFile(fileName){
	//window.location.href=window.ctx +'/resources/uploadFiles/'+fileName;
	window.location.href='/res/datasite/'+fileName;
	
	
}

function changeType(type){
	dialog({id:'download'}).close();
	dialog({id:'download'}).remove();
	current0="";
	current1="";
	current2="";
	current3="";
	current4="";
	if(type==0){
		current0="current";
	}else if(type==1){
		current1="current";
	}else if(type==2){
		current2="current";
	}else if(type==3){
		current3="current";
	}else if(type==4){
		current4="current";
	}
	showDownload(type);
}


function editTable(){
	
	var description = new Array();
	var name = new Array();  
	
	<c:forEach items="${item}" var="t">  
	description.push("${t.description}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	name.push("${t.name}");
	
	</c:forEach>  
	y=description.length+z;
 	var html="";
 	html+='<table class="table table-bordered">';
 	html+='<thead>';
 	html+='<tr>';
 	html+='<th class="tac w100">';	
 	html+='序号';
 	html+='</th>';
 	html+='<th class="w150">';
 	html+='字段名称';
 	html+='</th>';
 	html+='<th >';
 	html+='字段描述';
 	html+='</th>';
 	html+='</tr>';
 	html+='</thead>';
 	html+='<tbody>';
 	for(var i=0;i<y;i++)  
	{  
 		html+='<tr class="pr edit-box">'
 	 		html+='<td>'
 	 		html+=i+1
 	 		html+='</td>'
 	 		html+='<td>'
 	 			
 	 			if(typeof(name[i])=="undefined"){
	 				html+=''
 	 			}else{
 	 					html+=''+name[i]+''
 	 				} 
 	 		
 	 		html+='</td>'
 	 		html+='<td>'
 	 			if(typeof(description[i])=="undefined"){
 	 		    html+=''
 	 			}else{
 	 				html+=''+description[i]+''
 	 			}
 	 		
 	 		html+='</td>'
 	 		html+='</tr>'
 	 				 	
	}  
 	html+='</tbody>';
 	html+='</table>';
 	
 	
 	document.getElementById('editTable').innerHTML=html;
 	
 	
 }


 
 function ok(){
	 //remind("success-remind-box");
		if(area.prePageHtml!=null){
			$('#content-r').html(area.prePageHtml);
		}
		list(1,10);
  } 
 
 
 
	$(function(){
		//内容区域高度自适应
		/* function autoHeight(a,b){
			var main = document.getElementsByClassName(b)[0];
			var cHeight = document.documentElement.clientHeight;
			main.style.height = cHeight-a+"px";
			return false;
		}
		autoHeight(190,"teachingPlan-content") */
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
		//弹窗
		$(".bd-upload-btn").on("click",function(){
			$('.modal').modal('show');
			$(".modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
		})
		//表格点击编辑
		/* function edit(){
			var $edit_box = $(".edit-box td:gt(0)");
			$edit_box.on("click",function(){
				var $val = $(this).find("span").text();
				$(this).css("padding","5px");
				$(this).find("span").hide();
				$(this).find("input").show().val($val).focus();
				$(this).find("input").on("blur",function(){
					$(this).parent("td").css("padding","11px");
					var $text = $(this).val();
					$(this).prev().show().text($text);
					$(this).hide().val();
				})
			})
		}
		edit(); */
	})
</script>
</body>
</html>
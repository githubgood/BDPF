<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="edit-area-form" class="form-inline">
		
			<p class="et-manage-title">
				<span>修改数据</span>
			</p>
			<div id="message" class="et-teachingPlan-content">
			<div class="et-manage-operate">
				<div class="bd-infor-title">
					<p class="mt10" style="padding-left: 164px;">
						<span class="mr50">发布日期：${area.releaseDate} </span>
						<span>更新日期：${area.updateDate}</span>
					</p>
					</div>
				<div class="bd-infor-content of" style="margin-top:0;">
				<div class="bd-infor-right bd-infor-edit of">
				
					<div class="form-group" id="">
							标<i style="margin-left: 2em;"></i>题：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control"  id="title" name="title" value="${area.title}" placeholder="1~50个字符">
							</div>
				
							
								<div class="form-group" id="">
									数据领域：<i style="margin-left:1em;"></i>
									<select class="form-control" id="siteId" name="siteId">
									<option value="${area.dataSiteId}">${area.name}</option>
										<c:forEach items="${site}" var="cla">
									<option value="${cla.id }">${cla.name}</option>
								</c:forEach>
									</select>
								</div>
								
								<div class="form-group" id="">
									摘<i style="margin-left: 3em;"></i>要：
									<input type="text" class="form-control" id="abstract" value="${area.areAbstract}" name="abstractArea" placeholder="1~100个字符">
								</div>
							
								<div class="form-group" id="">
							关<i style="margin-left: 1em;"></i>键<i style="margin-left: 1em;"></i>字：
							<input type="text" class="form-control" id="keyword" name="keyword" value="${area.keyword}" placeholder="1~20个字符">
							
							</div>
							
							<div class="form-group" id="">
							数据提供方：
							<input type="text" class="form-control" id="data_provider_employer" name="data_provider_employer" value="${area.dataProviderEmployer}" placeholder="1~50个字符">
							</div>
									
							<div class="form-group" id="">
							接口描述：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control" id="api_description" name="api_description" value="${api[0].apiDescription}" placeholder="1~100个字符">
							</div>		
							
							<div class="form-group" id="">
							文件地址：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control" id="api_address" name="api_address" value="${api[0].apiAddress}" placeholder="1~50个字符">
							</div>
							
							<div class="form-group" id="">
							支持格式：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control" id="api_format" name="api_format" value="${api[0].apiFormat}" placeholder="1~20个字符">
							</div>
							
							<div class="form-group" id="">
							请求示例：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control" id="api_example" name="api_example" value="${api[0].apiExample}" placeholder="1~100个字符">
							</div>			
						
					</div>
					
					<div class="bd-infor-left mt20" id="editTable">
			 
					</div>
					
				</div>
					<div class="edit-btns tac">
					 <a href="javascript:void(0);" class="btn bd-down-btn bd-upload-btn" onclick="showBox()">
							<!-- <i class="db-icon down-icon"></i> -->
							生成示例
						</a>
					<input id="btn-save" name="btn-save" type="submit" class="btn btn-primary btn-save mr30" value="修                改" /> 	
					<button type="button" onclick="editAreaCancal()" class="btn btn-default btn-cancel"  data-dismiss="modal">取消</button>
				</div>
			</div>
</div>
	

<!--/main(end)-->
<!--footer(begin)-->

<!--footer(end)-->
<!--弹窗-->
<div class="modal fade modal_align" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">上传资源</h4>
			</div>
			<div class="modal-body upload-resouce-box">
				<form class="form-inline">
					<div class="form-group of">
						<div class="upload-btn">
							<input type="file" class="vam mr10">
							<span class="vam">支持CSV、JSON、XML、XLS格式文件</span>
						</div>
						<div class="file-item mt15 pr">
							<p class="fsize14 c-2b3">区县卫计局计生事务受理机构.csv</p>
							<p class="fsize12 c-67e mt10">文件大小：12M</p>
							<div class="re-integrity-m of pr mt10">
								<div class="re-solid fl tac cpu-precent">
								</div>
								<span class="pa cpu-precent-num">30.0%</span>
							</div>
							<a class="close-icon" href="javascript:void(0);"></a>
						</div>
						<div class="file-item mt15 pr">
							<p class="fsize14 c-2b3">区县卫计局计生事务受理机构.json</p>
							<p class="fsize12 c-67e mt10">文件大小：12M</p>
							<a class="close-icon" href="javascript:void(0);"></a>
						</div>
						<div class="file-item mt15 pr">
							<p class="fsize14 c-2b3">区县卫计局计生事务受理机构.json</p>
							<p class="fsize12 c-67e mt10">文件大小：12M</p>
							<a class="close-icon" href="javascript:void(0);"></a>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button"  class="btn btn-primary btn-save mr30">保存</button>
				<button type="button" class="btn btn-default btn-cancel"  data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</form>
<!--/弹窗-->
<script>
$.ajaxSetup({  
    async : false  
});
var editDescription = new Array();
var editName = new Array(); 

var z=0;

var y=0;
loadTable();
editTable();

function showBox(){
	dialog({
	    id: 'ptn950',              	
	    content: '<form method="POST" name="card" action>'
	    	+'文件所在的服务器ip地址：'
	    	+'<input type="text" class="form-control" id="ip">'
	    	+'用户名：'
	    	+'<input type="text" class="form-control" id="user">'
	    	+'密码：'
	    	+'<input type="text" class="form-control" id="pwd">'
	    	+ '文件名：'
	        +'<input type="text" class="form-control" id="fname">'
	       	+'</form>',
	    	title: '生成示例',
	    lock: true,
	    fixed: true,
	    drag:true,
	    width:400, height: 230,
	    ok: function () {
	    	$("#api_example").val('第一步连接ftp: ftp '+$("#ip").val()+' 第二步输入用户名: '+$("#user").val()+' 第三步输入密码: '+$("#pwd").val()+' 第四步获取文件: get '+$("#fname").val());
	    	
		    },
	    okValue: '确定',
	   
	    cancel: function () {
	    	
	    },
	    cancelValue: '取消',
	}).show();
}


editApi.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{//对指定的字段进行判空或者重复
			debug:false,
			title:{//标题名称
				required:true,
				stringMaxLength:50,//最大只能输入50个字符
				remote:{//根据当前标题主键ID和序号查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/datasite/checkAreaTitle",
		               data:{
		            	title:function(){ return $("#title").val();},
		            	oldTitle:function(){ return "${area.title}";},
		            	type:function(){ return "1";}
		               } 
		         },
			},
			abstractArea:{//摘要
				required:true,
				stringMaxLength:100,//最大只能输入100个字符
				},
		    siteId:{//数据领域id
					required:true,
			},
			keyword:{//关键字
				required:true,
				stringMaxLength:40,//最大只能输入40个字符
				},
			data_provider_employer:{//数据提供方
				required:true,
				stringMaxLength:100,//最大只能输入100个字符
				},
			api_description:{//接口描述
				required:true,
				stringMaxLength:200,//最大只能输入200个字符
				},
			api_address:{//文件地址
				required:true,
				stringMaxLength:100,//最大只能输入100个字符
				},	
			api_format:{//支持格式
				required:true,
				stringMaxLength:40,//最大只能输入40个字符
				},
			api_example:{//请求示例
				required:true,
				stringMaxLength:200,//最大只能输入200个字符
				},	
			},
		messages:{//对指定的字段进行消息提示
		title:{
				required:'请输入标题名称!',
				remote:'该标题已存在!',
			},
		siteId:{
			required:'数据领域必须存在!',
		},
		abstractArea:{
				required:'请输入摘要内容!',
		},
			keyword:{
				required:'请输入关键字!',
		},
		data_provider_employer:{
			    required:'请输入数据提供方!',
	    },	
	    api_description:{
			required:'请输入接口描述信息!',
		},
		api_address:{
			required:'请输入文件地址!',
	    },
	    api_format:{
			required:'请输入文件格式信息!',
	    },
	    api_example:{
		    required:'请输入请求示例!',
        },	
		},
		 submitHandler:function(form){
			  	var item="";
				var ids="";
				
			  	for(var n=0;n<y;n++){
			  		
			  		item+=$("#editName"+n).val()+"]"+$("#editDis"+n).val()+"[";
			  		
			  	}
			  		
			  	var url=  window.ctx +'/datasite/editArea';
			  	var data = [
			                {name:'id',value:"${area.id}"},
			  	            {name:'title',value:$("#title").val()},
			  	            {name:'siteId',value:$("#siteId").val()},
			  	            {name:'areaAbstract',value:$("#abstract").val()},
			  	            {name:'keyword',value:$("#keyword").val()},
			  	            {name:'data_provider_employer',value:$("#data_provider_employer").val()},
			  	            {name:'api_description',value:$("#api_description").val()},
				            {name:'api_address',value:$("#api_address").val()},
				            {name:'api_format',value:$("#api_format").val()},
				            {name:'api_example',value:$("#api_example").val()},
			  	            {name:'item',value:item}
			  	            ];
			
			  	$.post(url,data,function(reslut){
			  		if(reslut==2){
		     			showError("字段名称的长度不能大于20个字符");
		     		}
		     		if(reslut==3){
		     			showError("字段描述的长度不能大于50个字符");
		     		}
			  		if(reslut==0){
			  			remind("success-remind-box");
			  			if(addApi.prePageHtml!=null){
			  				$('#content-r').html(addApi.prePageHtml);
			  			}
			  			list(1,10);
			  				
			  		}else{
			  			if(reslut==1){
			  				showError("添加数据失败");
			  			}
			  		
			  		}
			  		});
			  	
			  	
		     	
		     	}
			
	});
	

};

function loadTable(){
	<c:forEach items="${item}" var="t">  
	editDescription.push("${t.description}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	editName.push("${t.name}");
	
	</c:forEach>  
	y=editDescription.length;
}


function editTable(){
	
	
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
 		html+='<tr class="pr add-item">'
 	 		html+='<td>'
 	 		html+=i+1
 	 		html+='</td>'
 	 		html+='<td>'
 	 			
 	 			if(typeof(editName[i])=="undefined"){
	 				html+='<input type="text" class="form-control W100" id="editName'+i+'">'
 	 			}else{
 	 					html+='<input type="text" class="form-control W100" id="editName'+i+'" value='+editName[i]+'>'
 	 				} 
 	 		
 	 		html+='</td>'
 	 		html+='<td>'
 	 			if(typeof(editDescription[i])=="undefined"){
 	 		    html+='<input type="text" class="form-control W100" id="editDis'+i+'">'
 	 			}else{
 	 				html+='<input type="text" class="form-control W100" id="editDis'+i+'" value='+editDescription[i]+'>'
 	 			}
 	 		
 	 		html+='</td>'
 	 		html+='</tr>'
 	 				 	
	}  
 	html+='</tbody>';
 	html+='</table>';
 	html+='<div class="df-add-operate">';
 	html+='<a href="javascript:void(0);" onclick="addEditTable()" class="df-add-btn mr40 fsize14">新增</a>';	
 	html+='<a href="javascript:void(0);" onclick="delEditTable()" class="df-del-btn fsize14">删除</a>';	
 	html+='</div>';
 	
 	document.getElementById('editTable').innerHTML=html;
 	
 	
 }

function addEditTable(){
	editDescription.splice(0,editDescription.length);
	editName.splice(0,editName.length);
	for(var n=0;n<y;n++){
 		editDescription.push($("#editDis"+n).val());
 		editName.push($("#editName"+n).val());
 	}
	 y=editDescription.length+1;
 	editTable();
 }

 function delEditTable(){
	 editDescription.splice(0,editDescription.length);
	 editName.splice(0,editName.length);
		for(var n=0;n<y;n++){
	 		editDescription.push($("#editDis"+n).val());
	 		editName.push($("#editName"+n).val());
	 	}
 	if(y>0){
 		y=editDescription.length-1;
 	}
 	editTable();
 }

 function editAreaCancal(){
	 
		if(addApi.prePageHtml!=null){
			$('#content-r').html(addApi.prePageHtml);
		}
		list(1,10);
	 }

 
 function editArea(){


  	
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
		/* $(".bd-upload-btn").on("click",function(){
			$('.modal').modal('show');
			$(".modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
		}) */
		//表格点击编辑
		function edit(){
			var $edit_box = $(".edit-box td:gt(0),.bd-infor-title");
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
		edit();
	})
</script>
</body>
</html>
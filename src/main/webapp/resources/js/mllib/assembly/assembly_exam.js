//机械学习首页js文件
$(function(){
	 $("body").css({"position":"fixed","z-index":-99});
	 $(".success-remind-box").css({"position":"fixed","z-index":99});
	 $(".fail-remind-box").css({"position":"fixed","z-index":99});
	//验证提示信息样式
	$("form label.error").css({"width":"200px","margin-left":"10px", "color":"red"});
	//表单提交验证
	$(".copy-test-box form").find(".btn-primary").click(submitForm("title","description","file","copy-test-box"));
	//表单提交取消，清除样式
	$(".copy-test-box form").find(".btn-default").on("click", removelabel);
	$(".copy-test-box form").find(".close").click(removelabel);
	//查询数据库进行首页展示
	$(".pai-nav-list").find(".btn-home-page").unbind().click(resourcelist);
	$(".pai-bpmn-metadata ").find(".pai-dropdown").find(".btn-close").on("click",delexperiment);
	//实验弹出框
	popBox("new-et-btn","copy-experiment-box","400");
	$(".copy-experiment-box form").find(".modal-body").css("height","165px").find('[placeholder="请输入实验名称"]').css("margin-bottom","10px");
	$(".copy-experiment-box form").find(".btn-primary").on("click",simpleVali("name","description","copy-experiment-box"));
	$(".copy-experiment-box form").find(".btn-default").on("click",removelabel);
	$(".copy-experiment-box form").find(".close").unbind().on("click",removelabel);
	$(".pai-nav-list").find(".btn-experiment").on("click",experimentlist);
});

//实验展示
function experimentlist(){
	var url = window.ctx + '/experiment/experimentlist';//查询的URL地址
	$.ajax({
		async:true,
		url : url,
		data: {},
		dataType : "json",
		type : "post",
		success  : function(result){
			if(result.length>0){
				var html="";
				for(var i in result){
					html+='<div class="pai-et-box">';
						html+='<span class="tree-node-indenter"></span>';
						html+='<span class="element-default">';
							html+='<span class="tree-node-icon"></span>';
							html+='<input type="hidden" class="experimentid" value="'+result[i].id+'">';
							html+='<span class="tree-node-label">'+result[i].name+'</span>';
						html+='</span>';
						html+='<span class="pai-del">';
							html+='<span class="icon icon-del-o" style="float:right;"></span>';
						html+='</span>';
					html+='</div>';	
				}
				$("#myexp").html(html);
				$(".pai-bpmn-left .box").find(".text").html(result[0].name);
				$(".pai-bpmn-left .box").find(".expid").val(result[0].id);
				$("#myexp .pai-et-box").find(".tree-node-label").on("click",add2right);				
				$("#myexp .pai-et-box").find(".icon-del-o").on("click",delexperiment);
				$("#experimentDescription").html(result[0].description);
				$("#experimentName").val(result[0].name);				
				$("#experimentDate").html(dateFtt("yyyy-MM-dd hh:mm:ss",new Date(result[0].createDate)));
			}else {//add 2017-12-18
                $("#myexp").html("");
                $("#container").html("");
                sessionStorage.clear();
			}
		}
	})
	
}
//时间格式化
function dateFtt(fmt,date)   
{   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 
//实验名称显示在右边
function add2right(){
	var vid=[//查询的参数
	            {name:'id',value:$(this).parent().find(".experimentid").val()},
	        	];
	var url=window.ctx + '/experiment/searchById';
	$.ajax({	
		url : url, 
		data:vid,
		dataType : "json",
		type : "post",
		success:function(re){	
			
			$(".pai-bpmn-left .box").find(".text").html(re.name);
			$(".pai-bpmn-left .box").find(".expid").val(re.id);
			$("#experimentName").val(re.name);
			$("#experimentDescription").html(re.description);									
			var date=new Date(re.createDate);
			$("#experimentDate").html(dateFtt("yyyy-MM-dd hh:mm:ss",date));

			//add 2017-12-18
            $("#container").html("");
            sessionStorage.clear();
            $("input[name='expid']").val(re.id);
         	updateExperiment();
		 }
	})
}

/**
 * add 2017-12-18
 * 清空工作区
 * 限制：允许创建多个实验，各个实验点击切换时流程组件会被清空，只保留当前在操作实验组件信息
 */
function updateExperiment(){
	var url = window.ctx +'/experiment/updateExperiment';
	$.ajax({
		url:url,
		type:"GET",
		success:function (o) {
            if (o){
                console.log("工作区清空成功？-->"+o);
            }else{
                alert("登录信息错误！");
            }
        }
	});
}

//删除实验
function delexperiment(){
	
	var ctrl=confirm("确定删除实验！");
	if(ctrl==false){
		return false;
	}
	var  url = window.ctx + '/assembly/dels';//查询的URL地址;
	var data = [//查询的参数
	            {name:'expid',value:$(this).parent().parent().find(".experimentid").val()},
	];
			$.ajax({
				async:true,
				url : url, 
				data:data,
				dataType : "json",
				type : "post",
				 beforeSend: function(){ 				
					 $("#myexp").find(".btn-primary").attr("disabled","true");
		        					 
		         },		     
		         success:function(re){		      		        	
		        	 if(re!=null){
		        		 remind("success-remind-box");
		        		experimentlist();
		        	 }else{		        		
		        		 remind("fail-remind-box");		        			       
		        	 }
		         },
				error:function(){
					alert("服务器出错了！");
				},
			});	
			$("."+tagid).find(".btn-primary").removeAttr("disabled");
}
//表单信息验证（没有文件上传）
function simpleVali(a,b,tagid){
	$("."+tagid).find(".form-control:first").attr({"id":a,"name":a});
	$($("."+tagid).find(".form-control")[1]).attr({"id":b,"name":b});
	$("."+tagid).find("form").validate({
		onsubmit:true, 
		rules :{//案例名称验证
			name:{
				required:true,
				maxlength:33,
				remote:{//实验名重复验证
		               type:"POST",
		               dataType: "json",  
		               url:window.ctx + "/experiment/checkName",
		               data:{
		            	   name:function(){ return $("."+tagid).find("#name").val();}		
		               } 
		         },
			},
			description:{//实验描述验证
				required:true,
				maxlength:33,
			}, 
		},
		messages:{//对指定的字段进行消息提示
			name:{
				required:'*请输入实验名称!',
				maxlength:'*不能超过33个字符!',
				remote:'该实验名称已存在!',
			},
			description:{//资源名称
				required:'*请输入实验描述!',
				maxlength:'*不能超过33个字符!',
			},	
		},
		submitHandler:function(form){
			if(!confirm("确定提交填写信息？")){
		 		return false;
		 	}			
						
				var url = window.ctx + '/experiment/save';
				var f = new FormData($("."+tagid).find("form")[0]);	
				 $("."+tagid).hide();
		 			$(".form-control").val("");				
				 $.ajax({
			         url:url,
			         type:"post",
			         traditional:true,
			         data:f,
			         processData:false,
			         contentType:false,
			         beforeSend: function(){
			        	
			        	 $("."+tagid).find(".btn-primary").attr("disabled","true");
			        					 
			         },
			       
			         success:function(re){			        				    
			        	 if(re.successful==true){
			        		 remind("success-remind-box");
			        		 experimentlist();
                             $("input[name='expid']").val(re.data.id);//add 2017-12-18
			        	 }else{			        		 
			        		 remind("fail-remind-box");	
			        	 }
			         },
			         error:function(e){
			        	 alert("服务器出错了！");  
			         }
			     });
				 $("."+tagid).find(".btn-primary").removeAttr("disabled"); 
			}  
	});
}

//移除验证提示信息
function removelabel(){
	$("label.error").remove();
	$(".form-control").val("");
}
//表单提交验证和文件上传
	function submitForm(a,b,c,tagid){
		
		$("."+tagid).find(".form-control:first").attr({"id":a,"name":a});
		$($("."+tagid).find(".form-control")[1]).attr({"id":b,"name":b});
		$($("."+tagid).find(".form-control")[2]).attr({"id":c,"name":c});
		$("."+tagid).find("form").attr("enctype","multipart/form-data");
		jQuery.validator.addMethod("checkFile", function(value, element) {

		    var filepath=$(".copy-test-box form").find("[name='file']").val();

		    //获得上传文件名

		    var fileArr=filepath.split("\\");

		    var fileTArr=fileArr[fileArr.length-1].toLowerCase().split(".");

		    var filetype=fileTArr[fileTArr.length-1];

		    //切割出后缀文件名

		    if(filetype == "doc"||filetype == "txt"||filetype == "xls"||filetype == "pdf"){

		        return true;

		    }else{

		        return false;

		    }

		}, "上传文件格式不适合");
		
		$("."+tagid).find("form").validate({
		
			onsubmit:true, 
			rules :{//案例名称验证
				title:{
					required:true,
					maxlength:33,
					remote:{//根据当前课程资源主键ID和名称查询数据库否是重复
			               type:"POST",
			               dataType: "json",  
			               url:window.ctx + "/resourceAssembly/checkTitle",
			               data:{
			            	   title:function(){ return $("#title").val();}		
			               } 
			         },
				},
				description:{//案例描述验证
					required:true,
					maxlength:33,
				}, 
				 file:{//案例文件名称
						required:true,
						maxlength:50,//最大只能输入50个字符
	                    //accept:"doc",	                    	
						checkFile:true,						
				},
			},
			messages:{//对指定的字段进行消息提示
				title:{//资源序号
					required:'*请输入案例名称!',
					maxlength:'*不能超过33个字符!',
					remote:'该案例名称已存在!',
				},
				description:{//资源名称
					required:'*请输入案例描述!',
					maxlength:'*不能超过33个字符!',
				},	
				file:{//资源分类
					required:'*请选择上传文件!',
					maxlength:'*文件名不能超过50字符!',
					//accept:'*上传文件格式只支持/.doc/.txt/.pdf/.xml',
					checkFile:'*上传文件格式只支持/.doc/.txt/.xls/.pdf',
			 	},
			},
			 submitHandler:function(form){
				 	if(!confirm("确定提交填写信息？")){
				 		return false;
				 	}				 	
					var url = window.ctx + '/resourceAssembly/fileSave';
					var f = new FormData($("."+tagid).find("form")[0]);	
					$("."+tagid).hide();
					$(".form-control").val("");
					 					
					 $.ajax({
						 async:true,
				         url:url,
				         type:"post",
				         data:f,
				         processData:false,
				         contentType:false,
				         beforeSend: function(){ 				      
				        	 $("."+tagid).find(".btn-primary").attr("disabled","true");				        				 
				         },    
				         success:function(re){				        	
				        	 if(re.successful==true){ 				        	
				        		remind("success-remind-box");
				        		 resourcelist();
				        	 }else{	
				        		 remind("fail-remind-box");				        	
				        	 }
				  
				         },
				         error:function(e){
				        	 $("."+tagid).find(".btn-primary").attr("disabled","false");
				        	 alert("服务器出错了！");  
				        	 
				         }
				         
				     });
					 $("."+tagid).find(".btn-primary").removeAttr("disabled");
			 }
		});	
		
	}
//页面案例新建和展示所有的案例
function resourcelist(){
	$(".pai-home").animate({"bottom":0},0);
	var url = window.ctx + '/resourceAssembly/resourcelist';//查询的URL地址
	$.ajax({
		url : url,
		data: {},
		dataType : "json",
		type : "post",
		success  : function(result){
			if(result.length>0){
				var html="";
				$(".template-empty").nextAll().remove();
				for(var i in result){
					html+='<div class="pai-template-item">';
					html+='<div class="template-wrap">';
							html+='<div class="template-image"><img src="/BDPF/resources/images/index/template-img-1.png" alt="加载中.."></div>';
							html+=('<div class="template-id" style="display:none">'+result[i].id+'</div>');
							html+=('<div class="template-title">'+result[i].title+'</div>');
							html+=('<div class="template-desc" title="">'+result[i].description+'</div>');
							html+='<div class="template-social">1067位用户</div>';
							html+='<div class="template-btns">';
							html+='<a class="del-template" href="javascript:void(0)">删除案例</a>';
								html+='<a class="view-document" href="javascript:void(0)">查看文档</a></div>';
						html+='</div>';
					html+='</div>';		
				}
				$('.template-empty').after($(html));//将拼接的结果添加到指定的div之后	
				//alert($($(".template-id")[0]).html())
				$(".del-template").on("click",delexp);
				$(".view-document").on("click",readFile);
			}else{
				//如果没有数据，进行友好提示
					alert("当前没有任何案例")	
			}
		}
	});
};
//删除案例
function delexp(){
	var ctrl=confirm("确定删除案例！");
	if(ctrl==false){
		return false;
	}
	var  url = window.ctx + '/resourceAssembly/resourceDel';//查询的URL地址;
	var v=$(this).parent().parent().children(".template-id").html();
			$.ajax({
				async:true,
				url : url, 
				data:{"id":v},
				dataType : "json",
				type : "post",
				 beforeSend: function(){      	
					 $(".pai-menu-list .pai-et-box").find(".icon-del-o").attr("disabled","true");		 
		         },
		         success:function(re){			        	 
		        	 if(re.successful==true){		    
		        		 remind("success-remind-box");	
		        		 resourcelist();
		        	 }else{
		        		 remind("fail-remind-box");
		        	 }
		         },
				error:function(){
					alert("服务器出错了！");
				},
			});	
			 $(".pai-menu-list .pai-et-box").find(".icon-del-o").removeAttr("disabled");

		
	
}

//查看文档
function readFile(){
	$(this).attr("disabled","true");
	//$(".moment-remind-box").dialog();
	var  url = window.ctx + '/resourceAssembly/readFile';//查询的URL地址;
	var v=$(this).parent().parent().children(".template-id").html();	
	$.ajax({
		async:false,
		url : url, 
		data:{"id":v},
		dataType : "json",
		type : "post",
		success : function(feed){
			//$(".moment-remind-box").dialog("close");
			if(feed.path!=null){
			var html='<div class="fileContext"><div class="et-right-title fsize16 c-656" >';
					html+='<h3 style="text-align:center;font-weight:bold;margin-bottom:-20px">'+feed.title+'</h3>';
					html+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>';
					html+='</button>';
				html+='</div>';
				html+='<div class="et-right-item">';
					html+='<iframe src="'+feed.path+'" style="width:100%;height:650px;"></iframe>';
				html+='</div></div>';
			$(".pai-home-template").hide().after($(html));
			$(".fileContext").find(".close").on("click",function(){
				$(".fileContext").remove();
				$(".pai-home-template").show();	
			});}else{
				alert("找不到资源文件！")
			}
		},
		error:function(){
			alert("服务器出错了！")
		},
	});	
	$(this).removeAttr("disabled");
}

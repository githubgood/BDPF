
$.ajaxSetup({  
    async : false  
});
var html="";
html+='<ul class="clearfix dataBank-list">';
var url=  window.ctx +'/datasite/getArea';
$.get(url,function(reslut){
for(var i=0;i<reslut.length;i++){

	html+='<li class="fd-dataItem fd-dataField tac" onclick="enter(\''+reslut[i].id+'\',\''+reslut[i].name+'\')">';
		html+='<dl>';
			html+='<dt class="dataBank-item-operate dataField-item tac">';
			html+='<p>';
			html+='<img src="/res/datasite/titleImg/'+reslut[i].imgName+'" alt="">';
			html+='</p>';
			html+='<p>';
			html+='<a href="javascript:void();" class="amend_btn current" onclick="updata(\''+reslut[i].id+'\')">修改</a>';
			html+='<i class="dividing_line"></i>';
			html+='<a href="javascript:void(0);" class="del_btn current" onclick="del(\''+reslut[i].id+'\')">删除</a>';
			html+='</p>';
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
html+='<li class="fd-dataItem fd-dataField fd-add-box tac" onclick="showBox()">';
html+='<img src="'+window.ctx+'/resources/images/data/add_icon.png" alt="">';
html+='<p>添加</p>';
html+='</ul>';

document.getElementById('main').innerHTML=html;//载入数据站点主页显示内容

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
//		$(".fd-add-box").on("click",function(){
//			$('.modal').modal('show');
//			$(".modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
//		})
		
	})
		
	function showBox(){
		isIn=1;
		$("#name").val("");		
		$("#abstract").val("");
		$('.modal-update').modal('show');
		$(".modal-update.modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
	}
	
	
	
	var isIn=0;
	/**
	 * 点击进入数据领域方法
	 * @param id
	 * @param name
	 */
	function enter(id,name){
		if(isIn==0){
			var url=  window.ctx +'/datasite/area';
			
			var data=[
		                 {name:'id',value:id},
		                 {name:'siteName',value:name},
		                 ];
			 $.post(url,data,function(reslut){
				 $('#content-site').html(reslut);
				 $('#titleBox').html("");
//				 window.location.href=url;
				 });
			
		}
		if(isIn!=2){
			 isIn=0;
				}
		
	}
	
	
	var oldName="";
	/**
	 * 更新数据领域方法
	 * @param id
	 */
	function updata(id){
		
		isIn=2;

		var url=  window.ctx +'/datasite/getSite';
		var url1=  window.ctx +'/datasite/site';
		var data=[
	                 {name:'id',value:id}
	                 ];
		
 		  	 $.post(url,data,function(reslut){
 		  		
 		  		$("#siteId").val(reslut[0].id);
 		  		$("#name").val(reslut[0].name);			 	
 		  		$("#abstract").val(reslut[0].siteAbstract);
 		  		$("#imgName").val(reslut[0].imgName);
 		  		$('.modal-update').modal('show');
 		  		$(".modal-update.modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
 		  		oldName=reslut[0].name;
 		  		
 				 });
 			
	}
	
	/**
	 * 删除数据领域方法
	 * @param id
	 */
	function del(id){
		isIn=3;
		
		var url=  window.ctx +'/datasite/delSite';
		var url1=  window.ctx +'/datasite/site';
		var data=[
	                 {name:'id',value:id}
	                 ];
		//显示提示框
		showDialog();
		//点击确定按钮
		$(".btn-save").on("click",function(){
			$('.modal').modal('hide');//先隐藏弹出框
			//执行删除功能
			 $.post(url,data,function(reslut){
 				 if(reslut==0){
 					 remind("success-remind-box");
 				}else{
 					showError("删除失败");
 				 }
 				
 				setTimeout("window.location.href='/BDPF/datasite/site'",2000);
 				
 				 });
		})
//		dialog({
// 		    content:'确定删除该项目吗?',
// 		    lock: true,
// 		    fixed: true,
// 		    
// 		    width:200, height: 100,
// 		    ok: function () {
// 		  	 $.post(url,data,function(reslut){
// 				 if(reslut==0){
// 					 dialog({title:'提示', content:"删除成功",width:220, height: 100}).show();
// 					 window.location.href=url1;
// 				 }else{
// 					 dialog({title:'提示', content:"删除失败",width:220, height: 100}).show();
// 					 window.location.href=url1;
// 					 }
// 			 	
// 				
// 				 });
// 	  		 },
// 		    okValue: '确定',
// 		   
// 		    cancel: function () {
// 		    	
// 		    },
// 		    cancelValue: '取消',
// 		    
// 		}).show();
		
	}
	
	function cancelSite(){
			isIn=0;
	}
	
	
	
	/**
	 * 上传图片与保存数据领域模块方法
	 */
	function saveSite(){
		 if(isIn==1){
		 $.ajaxFileUpload({  
		        type: "POST",  
		        url: window.ctx +'/datasite/loadImg',
		        secureuri : false,//是否启用安全提交，默认为false  
		        fileElementId:'btnFile',//文件选择框的id属性  
		        dataType: 'json',//服务器返回的格式  
		        async : false,  
		        error: function (data, status, e){  
		        	showError(e);
		        },
		        success: function(data){
		        	if(data.result=='上传格式不符合要求'){
		               	 showError(data.result);
		            	
		            }else if(data.result=='上传图片大小不符合要求'){
		            	 showError(data.result);
		            	
		            }
		        	else if(data.result=='success'){  
                      if($("#name").val()==""){
                    	 showError("标题不能为空!");
                    	 
                      }else{
		        		var url1=  window.ctx +'/datasite/site';
		        		if(isIn==1){
		                var url=  window.ctx +'/datasite/saveSite';
		                var data=[
		                 {name:'name',value:$("#name").val()},
		                 {name:'siteAbstract',value:$("#abstract").val()},
		                 {name:'imgName',value:data.url}
		        	                 ];
		        		 $.get(url,data,function(reslut){
		        			 if(reslut==2){
		        				 showError("标题长度不能大于6个字!");
		        			 }
		        			 if(reslut==3){
		        				 showError("描述长度不能大于100个字!");
		        			 }
		        			 if(reslut==4){
		        				 showError("保存出错!");
		        			 }
		        			 if(reslut==0){
		        				 showError("存在相同标题的数据领域!");
		        			 }
		        			 if(reslut==1){
		        				 $('.modal-update').modal('hide');
		        				 remind("success-remind-box");
		        				 setTimeout("window.location.href='/BDPF/datasite/site'",2000);
		        				 //window.location.reload(); 
		        			 }
		        			
		        			
		        			 });
		        		}
                      }            
		            }else{  
		            	showError("请选择上传图片");
		            }  
		        	
		        }
		    });  
		 }
	
		 if(isIn==2){
			 
			 $.ajaxFileUpload({  
			        type: "POST",  
			        url: window.ctx +'/datasite/loadImg',
			        secureuri : false,//是否启用安全提交，默认为false  
			        fileElementId:'btnFile',//文件选择框的id属性  
			        dataType: 'json',//服务器返回的格式  
			        async : false,  
			        error: function (data, status, e){  
			        	 showError(e);
			        },
			        success: function(data){  
			        	
			        	 if(data.msg=="上传文件不能为空"){ 
			        		 if($("#name").val()==""){
		                    	 showError("标题不能为空!");
		                      }else{
			        		 var url2=  window.ctx +'/datasite/editSite';
			    				var data2=[
			    				 {name:'id',value:$("#siteId").val()},         
			    		         {name:'name',value:$("#name").val()},
			    		         {name:'siteAbstract',value:$("#abstract").val()},
			    		         {name:'imgName',value:data.url},
			    		         {name:'oldName',value:oldName}
			    			     ];
			    				 $.get(url2,data2,function(reslut){
			    					
			    	 		if(reslut==2){
		        				 showError("标题长度不能大于6个字!");
		        			}
		        			 if(reslut==3){
		        				 showError("描述长度不能大于100个字!");
		        			 }
		        			 if(reslut==4){
		        				 showError("保存出错!");
		        			 }
		        			 if(reslut==0){
		        				 showError("存在相同标题的数据领域!");
		        			 }
		        			 if(reslut==1){
		        				 isIn=0;
		        				 $('.modal-update').modal('hide');
		        				 remind("success-remind-box");
		        				 setTimeout("window.location.href='/BDPF/datasite/site'",2000);
		        				// window.location.reload(); 
		        			 }
			    			 });
		                      }
				            }else if(data.result=='success'){ 
				            	if($("#name").val()==""){
			                    	 showError("标题不能为空!");
			                      }else{
			    		    var url2=  window.ctx +'/datasite/editSite';
			    				var data2=[
			    				 {name:'id',value:$("#siteId").val()},         
			    		         {name:'name',value:$("#name").val()},
			    		         {name:'siteAbstract',value:$("#abstract").val()},
			    		         {name:'imgName',value:data.url},
			    		         {name:'oldName',value:oldName}
			    			     ];
			    				 $.get(url2,data2,function(reslut){
			    	 		 
			    	 		 if(reslut==2){
		        				 showError("标题长度不能大于6个字!");
		        			 }
		        			 if(reslut==3){
		        				 showError("描述长度不能大于100个字!");
		        			 }
		        			 if(reslut==4){
		        				 showError("保存出错!");
		        			 }
		        			 if(reslut==0){
		        				 showError("存在相同标题的数据领域!");
		        			 }
		        			 if(reslut==1){
		        				 isIn=0;
		        				 $('.modal-update').modal('hide');
		        				 remind("success-remind-box");
		        				 setTimeout("window.location.href='/BDPF/datasite/site'",2000);
		        				// window.location.reload(); 
		        			 }
			    			 });
			                      }
			            }else if(data.result=='error'){  
			            	showError("请选择上传图片");
			            }else if(data.result=='上传格式不符合要求'){
			            	showError(data.result);
			            	
			            }else if(data.result=='上传图片大小不符合要求'){
			            	showError(data.result);
			            }
			        }
			    }); 
			 }
	}
	

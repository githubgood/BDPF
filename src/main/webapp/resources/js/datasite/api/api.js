$.ajaxSetup({  
    async : false  
});

var addApi = {};
addApi.prePageHtml = null;
addApi.prePageHtml = $('#content-r').html();

var editApi = {};


var html1="";
html1+='<dl>';
html1+='<dt class="et-nav-title"><a href="javascript:void(0);">API服务</a></dt>';
var url=  window.ctx +'/datasite/getArea';
$.get(url,function(reslut){
for(var i=0;i<reslut.length;i++){
	html1+='<dd class="et-nav-item"><i class="active-line"></i><a href="javascript:void(0);" onclick="choose(\''+reslut[i].id+'\',\''+reslut[i].name+'\')">'+reslut[i].name+'</a></dd>';
}
});
html1+='</dl>';
document.getElementById('site').innerHTML=html1;

list(1, 10);

/**
 * api列表分页
 * @param pageNo
 * @param pageSize
 */
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
			html+='<a href="javascript:void(0);" onclick="add()" class="btn com-btn c-0Cf mr5">添加</a>';
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
				html+='<div class="zc-operate">';
				html+='<a  href="javascript:void(0);" title="" onclick="toEdit(\''+page.result[i].id+'\')">';
				html+='<i class="db-icon et-icon editIcon"></i>&nbsp;';
				html+='<span>修改</span>';
				html+='<em class="et-rLine"></em>';
				html+='</a>';
				html+='<a  href="javascript:void(0);" title="" onclick="del(\''+page.result[i].id+'\')">';
				html+='<i class="db-icon et-icon delIcon"></i>&nbsp;';
				html+='<span>删除</span>';
				html+='</a>';
				html+='</div></a>';
				html+='</li>';
			}
			refreshPaginator(page.totalCount, pageSize, pageNo);
		
		
		});
		    html+='</ul>';
			html+='</div>';
			html+='</div>';
			
			document.getElementById('main').innerHTML=html;
};


/**
 * 分页方法
 * @param totalCount
 * @param pageSize
 * @param pageNo
 */
function refreshPaginator(totalCount, pageSize, pageNo){
	var total = Math.ceil(totalCount/pageSize);
	$('#paginator-tool').smartpaginator({
		dataelement:' 页（共'+totalCount+'条记录）',
		totalrecords : total,
		recordsperpage : 1,
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
//		$(document).ready(function(){
//			$('#myTable').DataTable({
//				paging: false,
//				searching: false,
//				ordering:  false
//			});
//		});
	})
	
	/**
	 * 根据选择的数据领域加载页面
	 * @param id
	 * @param name
	 */
	function choose(id,name){
		
		var url=  window.ctx +'/datasite/api';
		var data=[
	                 {name:'id',value:id},
	                 {name:'siteName',value:name}
	                 ];
		 $.post(url,data,function(reslut){
			 $('#content-api').html(reslut);
//			 window.location.href=url;
			 });
		 
	}
	
     /**
     * 添加数据方法
     */
    function add(){
    	 
    	var url = window.ctx + '/datasite/to-api-add';
    	var data=[
	                 {name:'id',value:id}
	                
	                 ];
    	$.get(url, data,function(page){
    		$('#content-r').html(page);
    		creatTable();
    		addApi.validateForm('add-area-form');
    		
    	});
    	
	}	
	
    addApi.validateForm = function(tagid){
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
			            	oldTitle:function(){ return "";},
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
			     	for(var n=0;n<=x;n++){
			     		
			     		item+=$("#name"+n).val()+"]"+$("#dis"+n).val()+"["
			     	}
			     		
			     	var url=  window.ctx +'/datasite/save';
			     	var data = [
			     	            {name:'title',value:$("#title").val()},
			     	            {name:'siteId',value:$("#siteId").val()},
			     	            {name:'areaAbstract',value:$("#abstract").val()},
			     	            {name:'keyword',value:$("#keyword").val()},
			     	            {name:'data_provider_employer',value:$("#data_provider_employer").val()},
			     	            {name:'type',value:"1"},
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
    
    
    
     
     var x=1;
     var k=0;
     var descriptionTemp = new Array();
 	 var nameTemp = new Array();  
     function creatTable(){
    	
    	 
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
     	for(var n=0;n<x;n++){
     		html+='<tr class="pr add-item">'
     		html+='<td>'
     		html+=n+1
     		html+='</td>'
     		html+='<td>'
     			if(typeof(nameTemp[n])=="undefined"){
     				html+='<input type="text" class="form-control W100" id="name'+n+'">'
     			}else{
     				html+='<input type="text" class="form-control  W100" id="name'+n+'" value='+nameTemp[n]+'>'
     			}
     		
     		html+='</td>'
     		html+='<td>'
     			if(typeof(descriptionTemp[n])=="undefined"){
     				html+='<input type="text" class="form-control  W100" id="dis'+n+'">'
     			}else{
     				html+='<input type="text" class="form-control  W100" id="dis'+n+'" value='+descriptionTemp[n]+'>'
     			}
     		
     		html+='</td>'
     		html+='</tr>'
     	}

     	html+='</tbody>';
     	html+='</table>';
     	html+='<div class="df-add-operate">';
     	html+='<a href="javascript:void(0);" onclick="addTable()" class="df-add-btn mr40 fsize14">新增</a>';	
     	html+='<a href="javascript:void(0);" onclick="delTable()" class="df-del-btn fsize14">删除</a>';	
     	html+='</div>';
     	document.getElementById('addTable').innerHTML=html;
     }


     /**
     * 添加表格方法
     */
    function addTable(){
    	 descriptionTemp.splice(0,descriptionTemp.length);
    	 nameTemp.splice(0,nameTemp.length);
    	
    	 for(var n=0;n<x;n++){
    		 descriptionTemp.push($("#dis"+n).val());
    		 nameTemp.push($("#name"+n).val());
      	}
    	 x=descriptionTemp.length+1;
     	
     	creatTable();
     }

     /**
     * 删除表格方法
     */
    function delTable(){
    	 descriptionTemp.splice(0,descriptionTemp.length);
    	 nameTemp.splice(0,nameTemp.length);
    	 for(var n=0;n<x;n++){
    		 descriptionTemp.push($("#dis"+n).val());
    		 nameTemp.push($("#name"+n).val());
      	}
    	
     	if(x>0){
     		 x=descriptionTemp.length-1;
     	}
     	
     	creatTable();
     }

     /**
     * 返回页面方法
     */
    function addAreaCancal(){
   
    		if(addApi.prePageHtml!=null){
				$('#content-r').html(addApi.prePageHtml);
			}
			list(1,10);
      }
     
     /**
     * 保存api信息
     */
    function save(){
    
     	

     	
     } 
     
     
     
    /**
     * 进入api详细信息
     * @param id
     */
    function enter(id){
    	var url = window.ctx + '/datasite/to-api-detail';
		var data=[
	                 {name:'id',value:id},
	                 {name:'type',value:"1"}
	                 ];
    	$.get(url,data, function(page){
    		$('#content-r').html(page);
    		
    		//area.validateForm('detail-area-form');
    		
    	});
	}
	
	/**
	 * 进入api编辑页面
	 * @param id
	 */
	function toEdit(id){
		
		var url = window.ctx + '/datasite/to-api-edit';
		var data=[
	                 {name:'id',value:id},
	                 {name:'type',value:"1"}
	                 ];
    	$.get(url,data, function(page){
    		$('#content-r').html(page);
    		
    		editApi.validateForm('edit-area-form');
    		
    	});
	}
	
	/**
	 * 删除api信息
	 * @param id
	 */
	function del(id){
		
		var url=  window.ctx +'/datasite/delArea';
		var data=[
	                 {name:'id',value:id}
	                 ];
		
		//显示提示框
		showDialog();
		//点击确定按钮
		$('.btn-save').unbind('click').click(function () {
			$('.modal').modal('hide');//先隐藏弹出框
			//执行删除功能
			$.post(url,data,function(reslut){
			 if(reslut==0){
					remind("success-remind-box");
						if(addApi.prePageHtml!=null){
							$('#content-r').html(addApi.prePageHtml);
						}
						list(1,10);
				 }else{
					
				showError("删除失败");	
				}
			});
		})
		
//		dialog({
//			id:"delApi",
// 		    content:'确定删除该项目吗?',
// 		    lock: true,
// 		    fixed: true,
// 		    
// 		    width:200, height: 100,
// 		    ok: function () {
// 		  	 $.post(url,data,function(reslut){
// 				 if(reslut==0){
// 					dialog({id:'delApi'}).close();
//               	  dialog({id:'delApi'}).remove();
// 					 //dialog({title:'提示', content:"删除成功",width:220, height: 100}).show();
// 					showError("删除成功");	
// 					// remind("success-remind-box");
// 						if(area.prePageHtml!=null){
// 							$('#content-r').html(area.prePageHtml);
// 						}
// 						list(1,10);
// 				 }else{
// 					dialog({id:'delApi'}).close();
//               	  dialog({id:'delApi'}).remove();
// 					// dialog({title:'提示', content:"删除失败",width:220, height: 100}).show();
// 					 showError("删除失败");	
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
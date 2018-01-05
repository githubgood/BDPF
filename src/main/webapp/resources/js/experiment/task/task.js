/**
 * @description 课程发布JS脚本
 * @auth wwq
 */
var task = {};
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
task.prePageHtml = null;
$(function(){//绑定事件
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#addbtn', add);
	$('#content-r').on('click', '#delebtn', del);
	$('#content-r').on('click', '#btn-cancel', back);
	$('#content-r').on('click', '#backbtn', back);
    search();//查询列表
	task.prePageHtml = $('#content-r').html();
});

/**
 * 查询列表，将查询的结果放到指定的tbody中
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function list(pageNo, pageSize){
	var url = window.ctx + '/task/list';//查询的URL地址
	var data = [//查询的参数
	            {name:'keywords',value:$('#keywords').val()},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.totalCount > 0){//判断是否有数据
			$.each(page.result, function(i,value){//进行数据拼接
				html+= '<tr>';
				/*html+= '<td align="center"><input type="checkbox" data-name="check-id" data-id="'+value.id+'" value="'+value.id+'"/> </td>';*/
				html+= '<td ><p class="checkbox_box"><input type="checkbox" class="checkBox" data-name="check-id" data-id="'+value.id+'" name="checkId" value="'+value.id+'"/><span class="checkBox"></span> </td>';
				html+= '<td align="left">'+ value.name +'</td>';
				html+= '<td align="left">'+value.planName +'</td>';
				html+= '<td align="left">'+ className(value.className,value.classId,value.id) +'</td>';
				html+= '<td  id="'+value.id+'" align="left">'+ taskStatus(value.taskStatus) +'</td>';
				html+= '<td align="center"><a onclick="deleteById(\''+value.id+'\')" href="javascript:void(0)" title="删除" class="db-icon et-icon del-icon"></a></td>';
				html+= '</tr>';
			});
		}else{//如果没有数据，进行友好提示
			html += '<tr><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
		$('#list-tbody').html(html);//将拼接的结果放到指定的tbody中
		refreshPaginator(page.totalCount, pageSize, pageNo);//分页
	});
};


/**
 * 自定义任务状态类型
 * @param type 任务状态编码
 * @returns {String}  任务状态名称
 */
function taskStatus(type){
	var value="";
	if(type=="0"){
		value="未开始";
	}else if(type=="1"){
		value="进行中";
	}else if(type=="2"){
		value="已完成";
	}
	return value;
}

function reportStatus(type){
	var value="";
	if(type=="0" ||type==null){
		value="未提交";
	}else if(type=="1"){
		value="已提交";
	}
	return value;
}

function detailtaskStatus(type){
	var value="";
	if(type=="0" ||type==null){
		value="未开始";
	}else if(type=="1"){
		value="已完成";
	}
	return value;
}

/**
 * 分班级查询
 * @param name
 * @returns {String}
 */
function className(name,classid,id){
	var str=name.split(",");
	var classtr = classid.split(",");
	var html = "";
	for(var i=0;i<str.length;i++){
		html+='<a onclick=" detail(\''+classtr[i]+'\',\''+id+'\')" href="javascript:void(0)" >'+str[i]+'</a>'+'&nbsp';
	}
	return html;
}
/**
 * 课程资源批量删除
 */
function del(){
	if($("#list-tbody input[data-name=check-id]:checked").length == 0){
		showError('请选择需要删除的课程资源！');
		return;
	}
	var flag = 0;
	$('#list-tbody input[data-name="check-id"]').each(function(){
		if($(this).prop('checked')){
			var value =$("#"+$(this).attr('data-id')).text();
			if(value=="进行中" || value=="已完成"){
				var message="任务"+value+"不能删除 !";
				showError(message);
				flag = 1;
				return false;
			}
		}
	});
	//显示提示框
	if(flag == 0){
		showDialog();
	}
	//点击确定按钮
	$(".btn-save").on("click",function(){
		$('.modal').modal('hide');//先隐藏弹出框
		loadGif();//加载动画
		//执行删除功能
		var url = window.ctx + '/task/delete';
		var array = [];
		$('#list-tbody input[data-name="check-id"]').each(function(e){
			if($(this).prop('checked')){
				array.push($(this).attr('data-id'));
			}
		});
		var data=[{
			name:'ids',
			value:array.join(',')
		}];
		$.post(url, data,function(feedbak){
			if(feedbak.successful){
				remind("success-remind-box");
			     search();//查询列表
			}else{
				remind("fail-remind-box");
			}
			closeGif();//关闭动画
		});
	});
};

/**
 * 课程资源查询
 */
function search(){
	refresh();
	list(wspageNo, wspageSize);
};

/**
 * 课程资源取消
 */
function back(){
	if(task.prePageHtml!=null){
		$('#content-r').html(task.prePageHtml);
	}
	 search();
};

/**
 * 课程资源单条删除
 * @param id 当前课程资源主键ID
 */
function deleteById(id){
	var value=$("#"+id).text();
	if(value=="进行中" || value=="已完成"){
		var message="任务"+value+"不能删除 !";
		showError(message);
		return;
	}
	//显示提示框
	showDialog();
	//点击确定按钮
//	$(".btn-save").on("click",function(){
	$(".btn-save").unbind('click').click(function(){
		loadGif();//加载动画
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/task/delete';
		var array = [id];
		var data=[{
			name:'ids',
			value:array.join(',')
		}];
		$.post(url, data,function(feedbak){
			if(feedbak.successful){//成功，提示信息
				remind("success-remind-box");
			     search();//查询列表
			}else{
				remind("fail-remind-box");
			}
			closeGif();//关闭动画
		});
	});
};

/**
 * 课程资源修改
 * @param id 当前课程资源主键ID
 */
function detail(classId,id){
	var url = window.ctx + '/task/to-detail';
	var data = [//查询的参数
	            {name:'classId',value:classId},
	            {name:'id',value:id}
	];
	$.post(url,data,function(page){
		$('#detail').html(page);
		detailList(wspageNo, wspageSize);
	});
};

function detailList(pageNo, pageSize){
	var url = window.ctx + '/task/detail';//查询的URL地址
	var data = [//查询的参数
	            {name:'classId',value:$('#classId').val()},
	            {name:'id',value:$('#id').val()},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.totalCount > 0){//判断是否有数据
			$.each(page.result, function(i,value){//进行数据拼接
				html+= '<tr>';
				html+= '<td align="left">'+ value.title +'</td>';
				html+= '<td align="left">'+ value.id +'</td>';
				html+= '<td align="left">'+ value.name +'</td>';
				html+= '<td align="left">'+ reportStatus(value.reportStatus) +'</td>';
				html+= '<td align="left">'+ detailtaskStatus(value.taskStatus) +'</td>';
				html+= '</tr>';
			});
			
		}else{//如果没有数据，进行友好提示
			html += '<tr><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
		$('#list-tbody').html(html);//将拼接的结果放到指定的tbody中
		detailRefreshPaginator(page.totalCount, pageSize, pageNo);//分页
	});
};
/**
 * 课程资源添加
 * @param id 当前课程资源主键ID
 */
function add(){
	var url = window.ctx + '/task/to-add';
	$.get(url, function(page){
		$('#content-r').html(page);
		task.validateForm('add-task-form');
		//手动和自动切换
		$(".type-box:first").on("click",function(){
			$(".type-box").find(".radio").removeClass("active");
			$(this).find(".radio").addClass("active");
			$(".div-show").show();
			$(".div-hidden").hide();
		})
		$(".type-box:last").on("click",function(){
			$(".type-box").find(".radio").removeClass("active");
			$(this).find(".radio").addClass("active");
			$(".div-show").hide();
			$(".div-hidden").show();
		})
		$('#teachingPlanId').change(change);
	});
};
function change() {
	var teachingPlanId= $("#teachingPlanId").val()
	   if(teachingPlanId==""){
		   $("#courseStatu").show();
	    	 return;
	   }
	var url = window.ctx + '/task/queryCourse';
	var data = [
	            {name:'teachingPlanId',value:teachingPlanId},      
	];
	$.post(url, data, function(result){
		if(result.indexOf("0") <0 ){
			$("#courseStatu").hide();
		} else{
			$("#courseStatu").show();
		}
	});
}
//课程资源管理form表单验证
task.validateForm = function(tagid){
	
	validator = $("#" + tagid).validate({
		rules :{//对指定的字段进行判空或者重复
			name:{//任务名称
				required:true,
				stringMaxLength:50,//最大只能输入50个字符
				remote:{//根据当前班级管理名称查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/task/checkTaskName",
		               data:{
		            	   id:function(){ return $("#id").val();},
		            	   name:function(){ return $("#name").val();}
		               } 
		         },
			},
			teachingPlanId:{//课程计划
				required:true,
			},
			classId:{//班级
				required:true,
			},
			groupName:{//请输入分组名称
				required:true,
				stringMaxLength:50,//最大只能输入50个字符
			},
			groupNum:{//小组数
				required:true,
				number:true,
				isclassId:true,
				numberMax4:true,
				stringMaxLength:1,//最大只能输入1个字符
				remote:{//根据当前班级管理名称查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/task/checkGroupNum",
		               data:{
		            	   classId:function(){   
		            		 var spCodesTemp = "";
		            		 $('input:checkbox[name=classId]:checked').each(function(i){ 
		      			     if(0==i){
		    			         spCodesTemp = $(this).val();
		    			        }else{
		    			         spCodesTemp += (","+$(this).val());
		    			        }
		            	   });
		            		 return spCodesTemp;
		            	 },
		            	 groupNum:function(){ return $("#groupNum").val();},
		               } 
		         },
			},
			txt:{//分组模板
				required:true,
			},
			description:{//任务描述
				stringMaxLength:100,//最大只能输入100个字符
			},
		},
		messages:{//对指定的字段进行消息提示
			name:{
				required:'请输入任务名称!',
				remote:'该任务名称已存在!',
			},
			teachingPlanId:{
				required:'请选择课程计划!',
			},
			classId:{
				required:'请选择班级!',
			},
			groupName:{
				required:'请输入分组名称!',
			},
			groupNum:{
				required:'请输入小组数!',
				remote:'小组人数不能大于班级人数!',
			},
			txt:{
				required:'请上传分组模板!',
			},
		},
	
		 submitHandler:function(form){
			 loadGif();
			 //防止重复提交
			$("#btn-save").attr('disabled',"false"); 
			if($("#radio").attr("class")=="radio active"){
				$("#groupType").val("0");
			}else{
				$("#groupType").val("1");
			}
			var url = window.ctx + '/task/save';
			var suf=$('#'+tagid).serialize();
			url=url+"?"+suf;
			var data = $('#'+tagid).serializeArray();
		    jQuery.ajaxSettings.traditional = true;  
		    $.ajaxFileUpload({  
		        url : url,  
		        secureuri : false,//安全协议  
		        fileElementId:'file',//id  
		        type : 'POST',  
		        dataType : 'json',  
		        data:data,  
		        async : false,  
		        error : function(data,status,e) {  
		        },  
		        success : function(feedbak){
		        	closeGif();
		        	$("#btn-save").removeAttr("disabled");
					if(feedbak.successful){
						remind("success-remind-box");
						if(task.prePageHtml!=null){
							$('#content-r').html(task.prePageHtml);
						}
					    search();
					}else{
						remind("fail-remind-box");
					}
				}
		    });  
		}
	});
}; 

/**
 * 分页
 * @param totalCount 总条数
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
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
	$('#list-table tbody tr:odd').addClass('odd');
};

function detailRefreshPaginator(totalCount, pageSize, pageNo){
		var total = Math.ceil(totalCount/pageSize);
		$('#paginator-tool-detail').smartpaginator({
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
			detailList(newPage, pageSize);
		}
	});
	$('#list-table tbody tr:odd').addClass('odd');
};

/**
 * 全选/反选
 * @param v 全选/反选标志
 * @param tagid 指定的标签ID
 */
function toggleCheck(v, tagid){
	if($(v).prop('checked')){
		$('#' + tagid).find('input[data-name="check-id"]').prop('checked',true);
	}else{
		$('#' + tagid).find('input[data-name="check-id"]').attr('checked',false);
	}
};


/**
 * 刷新页面，allcheck的选中状态
 */
function refresh() {
	$('#check-all-list').attr('checked', false);
}
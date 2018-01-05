/**
 * @description 课程实验任务JS脚本
 * @auth wanglf
 */
var exp = {};
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
exp.prePageHtml = null;
$(function(){//绑定事件
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#btn-cancel', back);
	 exp.prePageHtml = $('#content-r').html();
	 list(wspageNo, wspageSize);
});

/**
 * 查询列表，将查询的结果放到指定的tbody中
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function list(pageNo, pageSize){
	var url = window.ctx + '/exp/list';//查询的URL地址
	var data = [//查询的参数
	            {name:'taskId',value:$('#taskId').val()},
	            {name:'keywords',value:$('#keywords').val()},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.totalCount > 0){//判断是否有数据
			$.each(page.result, function(i,value){//进行数据拼接
				html+= '<tr>';
				html+= '<td align="left">'+ value.title +'</td>';
				html+= '<td align="left">'+courseType(value.courseResourceType) +'</td>';
				html+= '<td align="left">'+ groupType(value.courseResourceType) +'</td>';
				html+= '<td align="left">'+ taskStatus(value.taskStatus) +'</td>';
				html+= '<td align="left">'+ reportStatus(value.reportStatus) +'</td>';
				html+= '<td align="center"><a onclick="toEdit()" href="javascript:void(0)" title="预习" class="db-icon et-icon edit-icon preview-icon"></a><a onclick="toExp(\''+value.id+'\',\''+value.lastAttendance+'\','+value.courseResourceType+')" href="javascript:void(0)" title="进入" class="db-icon et-icon edit-icon enter-icon"></a><a onclick="send(\''+value.id+'\','+value.taskStatus+')" href="javascript:void(0)" title="提交" class="db-icon et-icon del-icon upload-icon"></a></td>';
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

/**
 * 自定义课程分类
 * @param type 课程分类编码
 * @returns {String}  课程分类名称
 */
function courseType(type){
	var value="";
	if(type=="0"){
		value="安装维护类";
	}else if(type=="1"){
		value="应用开发类";
	}else if(type=="2"){
		value="数据挖掘类";
	}
	return value;
}

/**
 * 自定义实验分组
 * @param type 实验分组编码
 * @returns {String}  实验分组名称
 */
function groupType(type){
	var value="";
	if(type=="0"){
		value="分组实验";
	}else{
		value="个人实验";
	}
	return value;
}

/**
 * 实验完成状态
 * @param type  0 未完成 1 完成
 * @returns {String} 实验完成状态名称 
 */
function taskStatus(type){
	var value="";
	if(type=="0"){
		value="未完成";
	}else if(type=="1"){
		value="已完成";
	}
	return value;
}
/**
 * 报告状态
 * @param type   0 未提交 1 提交
 * @returns {String} 实验报告提交状态名称 
 */
function reportStatus(type){
	var value="";
	if(type=="0"){
		value="未提交";
	}else if(type=="1"){
		value="已提交";
	}
	return value;
}

/**
 * 时间格式化
 * @param fmt 当前时间 
 * @returns {String} 格式化的时间
 */
function date (fmt) { 
	var d=new Date(fmt);
	var year=d.getFullYear();
	var day=d.getDate();
	if(day<10){
		day="0"+day;
	}
	var month=d.getMonth()+1;
	if(month<10){
		month="0"+month;
	}
	var hour=d.getHours();
	if(hour<10){
		hour="0"+hour;
	}
	var minute=d.getMinutes();
	if(minute<10){
		minute="0"+minute;
	}
	var second=d.getSeconds();
	if(second<10){
		second="0"+second;
	}
	var f=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	return f;  
};


/**
 * 课程实验查询
 */
function search(){
	list(wspageNo, wspageSize);
};

/**
 * 课程实验取消
 */
function back(){
	if(exp.prePageHtml!=null){
		$('#content-r').html(exp.prePageHtml);
	}
	 search();
};

/**
 * 跳转到资料库
 */
function toEdit(id){
	var url = window.ctx + '/material';
	window.open(url);
	
};
/**
 * 发送实验报告
 * @param id
 * @param status
 */
function send(id,status){
	if(status >0){
		showError("老师已经批改，不能提交！");
		return;
	}
	var url = window.ctx + '/exp/to-send/'+id;
	$.get(url, function(page){
		$('#content-r').html(page);
		exp.validateForm('send-exp-form');
	});
};

/**
 * 跳转到实验页面
 * @param id
 * @param status
 */
function toExp(id,lasttims,type){
	if(lasttims != 'null'){
		showError("实验已完成！");
		return;
	}
	window.open(window.ctx +'/exp/to-exp/'+id+'/'+type);
};


//课程实验任务form表单验证
exp.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{//对指定的字段进行判空或者重复
			debug:false,
			txtFoo:{//上传文件
				required:true,
			},			
		},
		messages:{//对指定的字段进行消息提示
		    txtFoo:{//上传文件
				required:'请选择文件上传!',
		    },
		},
		 submitHandler:function(form){
			 loadGif();
			var url = window.ctx + '/exp/save';
			var suf=$('#'+tagid).serialize();
			url=url+"?"+suf;
			var data = $('#'+tagid).serializeArray();
		    var fileName = $("#file").val();//文件名
		    fileName = fileName.split("\\");  
		    jQuery.ajaxSettings.traditional = true;  
		    fileName = fileName[fileName.length-1]; 
		    $.ajaxFileUpload({  
		        url : url,  
		        secureuri : false,//安全协议  
		        fileElementId:'file',//id  
		        type : 'POST',  
		        dataType : 'json',  
		        data:data,  
		        async : false,  
		        error : function(data,status,e) {  
		        	closeGif();
		        },  
		        success : function(feedbak){
		        	closeGif();
					if(feedbak.successful){
						remind("success-remind-box");
						if(exp.prePageHtml!=null){
							$('#content-r').html(exp.prePageHtml);
						}
					    search();
					}else{
						showError(feedbak.message);	
					}
				}
		    });  
		}
	});
}; 


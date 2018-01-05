/**
 * @description 课程资源管理JS脚本
 * @auth wwq
 */
var course = {};
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
course.prePageHtml = null;
$(function(){//绑定事件
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#addbtn', add);
	$('#content-r').on('click', '#delebtn', del);
	$('#content-r').on('click', '#btn-cancel', back);
	search();//查询列表
	course.prePageHtml = $('#content-r').html();
});

/**
 * 查询列表，将查询的结果放到指定的tbody中
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function list(pageNo, pageSize){
	var url = window.ctx + '/course/list';//查询的URL地址
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
				html+= '<td ><p class="checkbox_box"><input type="checkbox" class="checkBox" data-name="check-id" data-id="'+value.id+'" name="checkId" value="'+value.id+'"/><span class="checkBox"></span> </td>';
				if(value.fileType == 0){
					html+= '<td class="tal" style="padding-left:20px;"> <img src="/BDPF/resources/images/experiment/resource.png" alt="">  '+ value.title +'</td>';
				}else{
					html+= '<td class="tal" style="padding-left:20px;"> <img src="/BDPF/resources/images/experiment/tools.png" alt="">  '+ value.title +'</td>';
				}
				html+= '<td class="tal" style="padding-left:20px;">'+courseType(value.courseResourceType) +'</td>';
				html+= '<td class="tal" style="padding-left:20px;">'+ (value.courseMirrorName!= null ? value.courseMirrorName : '无') +'</td>';
				if(value.createUser != null){
					html+= '<td class="tal" style="padding-left:20px;">'+ value.createUser +'</td>';
				}else{
					html+= '<td class="tal" style="padding-left:20px;"></td>';
				}
				html+= '<td align="center"><a onclick="toEdit(\''+value.id+'\')" href="javascript:void(0)" title="编辑" class="db-icon et-icon edit-icon"></a><a onclick="deleteById(\''+value.id+'\')" href="javascript:void(0)" title="删除" class="db-icon et-icon del-icon"></a></td>';
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
 * 课程资源批量删除
 */
function del(){
	if($("#list-tbody input[data-name=check-id]:checked").length == 0){
		showError('请选择需要删除的课程资源！');	
		return;
	}
	//显示提示框
	showDialog();
	//点击确定按钮
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/course/delete';
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
				search();
			}else{
				showError(feedbak.message);	
			}
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
	if(course.prePageHtml!=null){
		$('#content-r').html(course.prePageHtml);
	}
	search();
};

/**
 * 课程资源单条删除
 * @param id 当前课程资源主键ID
 */
function deleteById(id){
	//显示提示框
	showDialog();
	//点击确定按钮
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/course/delete';
		var array = [id];
		var data=[{
			name:'ids',
			value:array.join(',')
		}];
		$.post(url, data,function(feedbak){
			if(feedbak.successful){//成功，提示信息
				remind("success-remind-box");
				search();
			}else{
				showError(feedbak.message);	
			}
		});
	});
};

/**
 * 课程资源修改
 * @param id 当前课程资源主键ID
 */
function toEdit(id){
	var url = window.ctx + '/course/to-edit/' + id;
	$.get(url, function(page){
		$('#content-r').html(page);
		course.validateForm('edit-course-form');
	});
};

/**
 * 课程资源添加
 * @param id 当前课程资源主键ID
 */
function add(){
	var url = window.ctx + '/course/to-add';
	$.get(url, function(page){
		$('#content-r').html(page);
		course.validateForm('add-course-form');
		$('#fileType').change(change);
	});
};

function change() {
  var value=$("#fileType").val();
  if(value==""){
	  $("#courseMirror-Name").show();
	  $("#parent-Id").show();
  }else if(value==0){
	  $("#courseMirror-Name").show();
	  $("#parent-Id").hide();
  }else {
	  $("#parent-Id").show();
	  $("#courseMirror-Name").hide();
  }
  showType(value);
}

function showType(value) {
	$('#type_file').show();
	if(value == ""){
		$('#type_file').html("");
		$('#type_file').hide();
	}
	
	if(value == 0){
		$('#type_file').html("支持上传 /.doc /.xls /.txt /.pdf 文件")
	}else if(value == 1){
		$('#type_file').html("支持上传 /.mp4 文件")
	}else if(value == 2){
		$('#type_file').html("支持上传 /.png /.jpg 文件")
	}else if(value == 3){
		$('#type_file').html("支持上传/.json /.csv /.xml 文件")
	}else if(value == 4){
		$('#type_file').html("支持上传/.exe /.gz /.rpm 文件")
	}else{
		$('#type_file').hide();
	}
	
}

//课程资源管理form表单验证
course.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{//对指定的字段进行判空或者重复
			debug:false,
			xh:{//资源序号
				required:true,
				stringMaxLength:10,//最大只能输入10个字符
				number:true,
				remote:{//根据当前课程资源主键ID和序号查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/course/checkXh",
		               data:{
		            	   id:function(){ return $("#id").val();},
		            	   xh:function(){ return $("#xh").val();}		
		               } 
		         },
			},
			title:{//资源名称
				required:true,
				stringMaxLength:50,//最大只能输入50个字符
				remote:{//根据当前课程资源主键ID和序号查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/course/checkName",
		               data:{
		            	   id:function(){ return $("#id").val();},
		            	   title:function(){ return $("#title").val();}		
		               } 
		         },
			},
			courseResourceType:{//资源分类
				required:true,
			},
			courseMirrorName:{//模板名称
				required:true,
			},
			fileType:{//文件类型
				required:true,
			},
			txtFoo:{//上传文件
				required:true,
				checkFileType:true,
			},
			parentId:{//上传文件
				required:true,
			},
			description:{//班级描述
				stringMaxLength:100,//最大只能输入100个字符
			},
		},
		messages:{//对指定的字段进行消息提示
			xh:{//资源序号
				required:'请输入资源序号!',
				remote:'该资源序号已存在!',
			},
			title:{//资源名称
				required:'请输入资源名称!',
				remote:'该名称已存在!',
			},
			courseResourceType:{//资源分类
				required:'请选择资源分类!',
			},
			courseMirrorName:{//模板名称
				required:'请选择模板名称!',
		    },
		    fileType:{//文件类型
				required:'请选择文件类型!',
		    },
		    txtFoo:{//上传文件
				required:'请上传文件!',
		    },
		    parentId:{//课程资源
				required:'请选择课程资源!',
		    },
		},
		 submitHandler:function(form){
			 loadGif();//加载动画
			var url = window.ctx + '/course/save';
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
		            //alert('Operate Failed!');  
		        },  
		        success : function(feedbak){
					if(feedbak.successful){
						closeGif();//关闭加载动画
						remind("success-remind-box");
						if(course.prePageHtml!=null){
							$('#content-r').html(course.prePageHtml);
						}
						search();
					}else{
						showError(feedbak.message);	
						closeGif();//关闭加载动画
					}
				}
		    });  
		}
	});
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
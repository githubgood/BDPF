/**
 * @description 学生管理管理JS脚本
 * @auth wwq
 */
var student = {};
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
student.prePageHtml = null;
$(function(){//绑定事件
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#addbtn', add);
	$('#content-r').on('click', '#delebtn', del);
	$('#content-r').on('click', '#btn-cancel', back);
	$('#content-r').on('click', '#importbtn', toImport);
	search();//查询列表
	student.prePageHtml = $('#content-r').html();
});

/**
 * 查询列表，将查询的结果放到指定的tbody中
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function list(pageNo, pageSize){
	var url = window.ctx + '/student/list';//查询的URL地址
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
				html+= '<td align="left">'+ value.account +'</td>';
				html+= '<td align="left">'+ value.name +'</td>';
				html+= '<td align="left">'+ value.mobile +'</td>';
				html+= '<td align="left">'+ value.email +'</td>';
				html+= '<td align="left">'+ value.classId +'</td>';
				html += '<td><a class="db-icon et-icon edit-icon" href="javascript:void(0);" onclick="toEdit(\''+ value.id+ '\')"></a><a class="db-icon et-icon del-icon" href="javascript:void(0);" onclick="deleteById(\''+ value.id+ '\')"></a></td>';
				html+= '</tr>';
			});
		}else{//如果没有数据，进行友好提示
			html += '<tr><td colspan="6">对不起，暂时没有您所需要的数据！</td></tr>';
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
 * 学生管理批量删除
 */
function del(){
	if($("#list-tbody input[data-name=check-id]:checked").length == 0){
		showError('请选择需要删除的学生！')
		return;
	}
	//显示提示框
	showDialog();
	//点击确定按钮
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/student/delete';
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
 * 学生管理查询
 */
function search(){
	refresh();
	list(wspageNo, wspageSize);
};

/**
 * 学生管理取消
 */
function back(){
	if(student.prePageHtml!=null){
		$('#content-r').html(student.prePageHtml);
	}
	search();
};

/**
 * 学生信息导入
 */
function toImport(){
	var url = window.ctx + '/student/to-import';
	$.get(url, function(page){
		$('#content-r').html(page);
		clas.validateForm('import-student-form');
	});
};

/**
 * 学生管理单条删除
 * @param id 当前学生管理主键ID
 */
function deleteById(id){
	//显示提示框
	showDialog();
	//点击确定按钮
//	$(".btn-save").on("click",function(){
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/student/delete';
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
 * 学生管理修改
 * @param id 当前学生管理主键ID
 */
function toEdit(id){
	var url = window.ctx + '/student/to-edit/' + id;
	$.get(url, function(page){
		$('#content-r').html(page);
		student.validateForm('edit-student-form');
	});
};

/**
 * 学生管理添加
 * @param id 当前学生管理主键ID
 */
function add(){
	var url = window.ctx + '/student/to-add';
	$.get(url, function(page){
		$('#content-r').html(page);
		student.validateForm('add-student-form');
	});
};

//学生管理管理form表单验证
student.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{
			debug:false,
			account:{
				stringMaxLength:16,//最大只能输入16个字符
				required:true,
				useraccount:true,
				remote:{//根据当前课程资源主键ID和序号查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/student/checkAccount",
		               data:{
		            	   id:function(){ return $("#Id").val();},
		            	   account:function(){ return $("#account").val();}		
		               } 
		         },
			},
			name:{
				stringMaxLength:50,//最大只能输入50个字符
				required:true,
			},
			mobile:{
				required:true,
				isMobile:true,
				stringMaxLength:11,//最大只能输入11个字符
			},
			email:{
				required:true,
				email:true,
				stringMaxLength:32,//最大只能输入32个字符
			},
			classId:{
				required:true,
			},
		},
		messages:{
			account:{
				required:'请输入学号!',
				remote:'该学号已存在!',
			},
			name:{
				required:'请输入用户名称!',
			},
			mobile:{
				required:'请输入手机号码!',
			},
			email:{
				required:'请输入邮箱!',
			},
			classId:{
				required:'请选择班级!',
			},
		},
		 submitHandler:function(form){
			var url = window.ctx + '/student/save';
			var data = $('#'+tagid).serializeArray();
			$.post(url, data, function(feedbak){//如果上面验证通过则进行保存操作
				if(feedbak.successful){
					remind("success-remind-box");
					if(student.prePageHtml!=null){
						$('#content-r').html(student.prePageHtml);
					}
					search();
				}else{
					showError(feedbak.message);
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
 * js自定义消息
 */
function Feedback(successful,message){
	this.successful = successful;
	this.message = message;
};


/**
 * 刷新页面，allcheck的选中状态
 */
function refresh() {
	$('#check-all-list').attr('checked', false);
}
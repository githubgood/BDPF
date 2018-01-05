/**
 * @description 用户管理JS脚本
 * @auth wanglf
 */

var user = {};
user.prePageHtml = null;
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
$(function(){
	//布局存在问题最好是先写好引入  现在使用js加载进去的
		$('#content-r').on('click', '#search', search);
		$('#content-r').on('click', '#addbtn', add);
		$('#content-r').on('click', '#delebtn', del);
		$('#content-r').on('click', '#btn-cancel', back);
		$('#content-r').on('click', '#importbtn', toImport);
		search();//查询列表
		user.prePageHtml = $('#content-r').html();
	});
/**
 * 分页请求数据
 * 
 * @param pageNo
 * @param pageSize
 */
function list(pageNo, pageSize) {
	var url = window.ctx + '/user/list';
	var data = [ 
	    {name : 'keywords',value : $("#keywords").val()},
	    {name : 'pageSize',value : pageSize}, 
	    {name : 'pageNo',value : pageNo} ];
	$.get(url,data,function(page) {
		var html = "";
		if (page.totalCount > 0) {
		$.each(page.result,function(i, user) {
			html += '<tr class="pr">';
			if(user.userType == 2&&user.account=='admin'){//如果 当前 是管理员，不可以选中
				html += '<td><p class="checkbox_box"><input class="checkBox" value="'+ user.id+ '" type="checkbox" name="checkId" value="" disabled><span class="checkBox"></span></p></td>';
			}else{//非管理员，可以选中
				html += '<td><p class="checkbox_box"><input class="checkBox" data-name="check-id" data-id="'+ user.id+ '" value="'+ user.id+ '" type="checkbox" name="checkId" value=""><span class="checkBox"></span></p></td>';
			}
			html += '<td>' + user.account+ '</td>';
			html += '<td>' + user.name+ '</td>';
			html += '<td>' + userType(user.userType)+ '</td>';
			html += '<td>' + empty(user.classId)+ '</td>';
			if(user.userType == 2&&user.account=='admin'){//如果 当前 是管理员，没有删除功能
				html += '<td><a class="db-icon et-icon edit-icon" href="javascript:void(0);" onclick="toEdit(\''+ user.id+ '\','+user.userType+')"></a><a class="db-icon et-icon del-icon no-del-icon" href="javascript:void(0);"></a></td>';
			}else{//非管理员，有删除功能
				html += '<td><a class="db-icon et-icon edit-icon" href="javascript:void(0);" onclick="toEdit(\''+ user.id+ '\','+user.userType+')"></a><a class="db-icon et-icon del-icon" href="javascript:void(0);" onclick="deleteById(\''+ user.id+ '\')"></a></td>';
			}
			html += '</tr>';
		});
		} else {
			html += '<tr class="pr"><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
			$('#list-tbody').html(html);
			refreshPaginator(page.totalCount, pageSize, pageNo);
		});
};
/**
 * 分页
 * 
 * @param totalCount
 *            总条数
 * @param pageSize
 * @param pageNo
 */
function refreshPaginator(totalCount, pageSize, pageNo) {
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
			list(newPage, pageSize)
			refresh();
		}
	});
	$('#list-table tbody tr:odd').addClass('odd');
};
/**
 * 搜索
 */
function search() {
	refresh();
	list(wspageNo, wspageSize);//查询列表
};

/**
 * 教师信息导入
 */
function toImport(){
	var url = window.ctx + '/user/to-import';
	$.get(url, function(page){
		$('#content-r').html(page);
		clas.validateForm('import-user-form');
	});
};

/**
 * 添加
 */
function add() {
	var url = window.ctx + '/user/to-add';
	$.get(url, function(page) {
		$('#content-r').html(page);
		user.validateForm('add-user-form');
		 $("#classId").hide();
		$('#roleId').change(change);
	});
};

/**
 * 修改
 * 
 * @param userId
 */
function toEdit(userId,type) {
	var url = window.ctx + '/user/to-edit/' + userId;
	$.get(url, function(page) {
		$('#content-r').html(page);
		user.validateForm('edit-user-form');
		$('#roleId').change(change);
		if(type== 0){//教师
			  $("#class-view").show();
			  $("#teacher-view").show();
			  $("#classId").hide();
		  }else if(type== 1){//学生
			  $("#class-view").show();
			  $("#classId").show();
			  $("#teacher-view").hide();
			  checkFalse();
		  }else{
			  $("#class-view").hide();
		  }
	});
};


function change() {
	  var value= $('#roleId option:selected').text();//选中的文本
	  value = $.trim(value);
	  if(value.indexOf("教师")>=0||value.indexOf("老师")>=0){
		  $("#class-view").show();
		  $("#teacher-view").show();
		  $("#classId").hide();
	  }else if(value.indexOf("学生")>=0) {
		  $("#class-view").show();
		  $("#classId").show();
		  $("#teacher-view").hide();
		  checkFalse();
	  }else{//管理员
		  $("#class-view").hide();
		  checkFalse();
	  }
}
//取消选择
function checkFalse() {
	var one=document.getElementsByName('tClassId');//获取到复选框的名称
	for(var j=0;j<one.length;j++){
        one[j].checked=false;
    }
}
function userType(type) {
	var types = "";
	if(type == 0){
		types = "老师"
	}else if(type == 1){
		types = "学生"
	}else if(type == 2){
		types = "管理员"
	}
	return types;
}

/**
 * js提交验证
 */
user.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{
			debug:false,
			Id:{
				required:false,
			},
			account:{
				stringMaxLength:16,//最大16个字符
				required:true,
				useraccount:true,
				remote:{//根据当前账号名称查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/user/checkName",
		               data:{
		            	   id:function(){ return $("#Id").val();},
		            	   account:function(){ return $("#account").val();}		
		               } 
		         },
			},
			password:{
				stringMaxLength:12,//最大12个字符
				ispassword:true,
				required:true,
			},
			name:{
				stringMaxLength:50,//最大50个字符
				required:true,
			},
			mobile:{
				required:true,
				isMobile:true,
				stringMaxLength:11,//最大11个字符
			},
			email:{
				required:true,
				stringMaxLength:32,//最大32个字符
			},
			classId:{
				required:true,
			},
			tClassId:{
				required:true,
			},
			roleId:{
				required:true,
			},
			userType:{
				required:true,
			},
		},
		messages:{
			account:{
				required:'请输入账号名!',
				remote:'该账号已存在!',
			},
			password:{
				required:'请输入密码!',
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
				required:'请选择学生所在班级!',
			},
			tClassId:{
				required:'请选择老师所在班级!',
			},
			roleId:{
				required:'请选择角色!',
			},
			userType:{
				required:'请选择角色!',
			},
		},
		 submitHandler:function(form){
			var url = window.ctx + '/user/save';
			var data = $('#'+tagid).serializeArray();
			$.post(url, data, function(feedbak){
				if (feedbak.successful) {				
					// 保存成功				
					remind("success-remind-box");
				$('#content-r').html(user.prePageHtml);
					search();//查询列表
				} else {
					showError(feedbak.message);
				}
			});
		}
	});
}; 

/**
 * 删除
 * 
 * @param claId
 */
function deleteById(claId) {
	//显示提示框
	showDialog();
	//点击确定按钮
//	$(".btn-save").on("click",function(){
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/user/delete';
		var array = [ claId ];
		var data = [ {
			name : 'ids',
			value : array.join(',')
		} ];
		$.post(url, data, function(feedbak) {
			if (feedbak.successful) {
				remind("success-remind-box");
				search();;//查询列表
			} else {
				showError(feedbak.message);
			}
		});
	})
	
};
function back(){
	if(user.prePageHtml!=null){
		$('#content-r').html(user.prePageHtml);
	}
	search();//查询列表
};
/**
 * 多选删除
 */
function del() {

	if ($("#list-tbody input[data-name=check-id]:checked").length == 0) {

		showError("请选择要删除的数据");
		return;
	}
	//显示提示框
	showDialog();
	//点击确定按钮
	$(".btn-save").unbind("click").on("click",function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/user/delete';
		var array = [];
		$('#list-tbody input[data-name="check-id"]').each(function(e) {
			if ($(this).prop('checked')) {
				array.push($(this).attr('data-id'));
			}
		});
		var data = [ {
			name : 'ids',
			value : array.join(',')
		} ];
		$.post(url, data, function(feedbak) {
			if (feedbak.successful) {
				remind("success-remind-box");
				search();//查询列表
			} else {
				showError(feedbak.message);
			}
		});
	})
	
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
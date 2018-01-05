/**
 * @description 用户管理JS脚本
 * @auth wanglf
 */

var role = {};
role.prePageHtml = null;
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
$(function(){
	//布局存在问题最好是先写好引入  现在使用js加载进去的
		$('#content-r').on('click', '#search', search);
		$('#content-r').on('click', '#addbtn', add);
		$('#content-r').on('click', '#delebtn', del);
		$('#content-r').on('click', '#btn-cancel', back);
	     search();//查询列表
		role.prePageHtml = $('#content-r').html();
	});

/**
 * 分页请求数据
 * @param pageNo
 * @param pageSize
 */
function list(pageNo, pageSize){
	var url = window.ctx+'/role/list';
	var data = [
	            {name:'keywords',value:$("#keywords").val()},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){
		var html = "";
		if(page.totalCount > 0){
			$.each(page.result, function(i,role){
				html+= '<tr class="pr">';
				html+= '<td><p class="checkbox_box"><input class="checkBox" data-name="check-id" data-id="'+role.id+'" value="'+role.id+'" type="checkbox" name="checkId" value=""><span class="checkBox"></span></p></td>';
				html+= '<td ">'+role.name +'</td>';
				html+= '<td ">'+role.description +'</td>';
				html+= '<td><a class="db-icon et-icon edit-icon" href="javascript:void(0);" onclick="toEdit(\''+role.id+'\')"></a><a class="db-icon et-icon del-icon" href="javascript:void(0);" onclick="deleteById(\''+role.id+'\')"></a></td>';
				html+= '</tr>';
			});
			
		}else{
			html += '<tr class="pr"><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
		$('#list-tbody').html(html);
		refreshPaginator(page.totalCount, pageSize, pageNo);
	});
	refresh();
};
/**
 * 分页
 * @param totalCount  总条数
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
			refresh();
		}
	});
	$('#list-table tbody tr:odd').addClass('odd');
};
/**
 * 搜索
 */
function search(){
	list(wspageNo, wspageSize)
};
/**
 * 添加
 */
function add(){
	var url = window.ctx + '/role/to-add';
	$.get(url, function(page){
		$('#content-r').html(page);
		role.validateForm('add-role-form');
		DoCheck("system");
		DoCheck("datasite");
		DoCheck("experiment");
		DoCheck("vmstemplates");
		//全部设置为不选中
		$("#message input.checkBox").prop("checked",false);
		//设置第一个选中,并且不能修改
		$(".cd-checkbox").eq(0).find(".checkBox").prop("checked",true).attr("disabled",true);
	});
};

/**
 * 修改
 * @param roleId
 */
function toEdit(roleId){
	var url = window.ctx + '/role/to-edit/' + roleId;
	$.get(url, function(page){
		$('#content-r').html(page);
		role.validateForm('edit-role-form');
		//设置第一个选中,并且不能修改
		$(".cd-checkbox").eq(0).find(".checkBox").prop("checked",true).attr("disabled",true);
	});
};

/**
 * js提交验证
 */
role.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{
			debug:false,
			Id:{
				required:false,
			},
			name:{
				stringMaxLength:50,//最大50个字符
				required:true,
				remote:{//根据当前账号名称查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/role/checkName",
		               data:{
		            	   id:function(){ return $("#Id").val();},
		            	   name:function(){ return $("#name").val();}		
		               } 
		         },
			},
			description:{
				stringMaxLength:100,//最大100个字符
				required:true,
			},
		},
		messages:{
			name:{
				required:'请输入用户名称!',
				remote:'该角色名已存在!',
			},
			description:{
				required:'请输入描述!',
			},
		},
		 submitHandler:function(form){
			 var array = [];
			    $.each($('input:checkbox:checked'),function(){
			        /*alert("你选了："+
			            $('input[type=checkbox]:checked').length+"个，其中有："+$(this).val());*/
			    	array.push($(this).attr('value'));
			    });
			var url = window.ctx + '/role/save/'+array.join("-");
			var data = $('#'+tagid).serializeArray();
			$.post(url, data, function(feedbak){
				if (feedbak.successful) {
					// 保存成功
					remind("success-remind-box");
					$('#content-r').html(role.prePageHtml);
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
 * @param claId
 */
function deleteById(claId){
	//显示提示框
	showDialog();
	//点击确定按钮
//	$(".btn-save").on("click",function(){
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
	var url = window.ctx + '/role/delete';
	var array = [claId];
	var data=[{
		name:'ids',
		value:array.join(',')
	}];
	$.post(url, data,function(feedbak){
		if (feedbak.successful) {
			// 保存成功
			remind("success-remind-box");
			search();//查询列表
		} else {
			showError(feedbak.message);
		}

	});
	});
};
/**
 * 取消
 */
function back(){
	if(role.prePageHtml!=null){
		$('#content-r').html(role.prePageHtml);
	}
	search();//查询列表
}
/**
 * 多选删除
 */
function del(){
	
	if($("#list-tbody input[data-name=check-id]:checked").length == 0){

		showError("请选择要删除的数据");
		return;
	}
	
	//显示提示框
	showDialog();
	//点击确定按钮
	$(".btn-save").on("click",function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
	var url = window.ctx + '/role/delete';
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
		if (feedbak.successful) {
			// 保存成功
			remind("success-remind-box");
			search();//查询列表
		} else {
			showError(feedbak.message);
		}

	});
	});
};

/**
 * 设置全选、反选
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
	$('#check-all-list').attr('checked',false);
}
/**
 * 
 * @param menus
 */
function DoCheck(menus) {
	var checkboxs = document.getElementsByName(menus);
	if(document.getElementsByName(menus)[0].checked==true)
	{
		for(var i=0;i<checkboxs.length;i++)
		{
			checkboxs[i].checked=true;
		}
	}else{
		for(var i=0;i<checkboxs.length;i++)
		{
			checkboxs[i].checked=false;
		}
	}
	//根据复选框个数修改状态
	for(var i=1;i<checkboxs.length;i++){
		checkboxs[i].onclick = function(){
			for(var i=1,n=0;i<checkboxs.length;i++){
				checkboxs[i].checked&&n++;
			}
			document.getElementsByName(menus)[0].checked = n;
		}
	}
}

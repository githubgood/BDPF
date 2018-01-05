/**
 * @description 权限管理JS脚本
 * @auth wanglf
 */

var comp = {};
comp.prePageHtml = null;
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
$(function(){
	//布局存在问题最好是先写好引入  现在使用js加载进去的
		$('#content-r').on('click', '#search', search);
		$('#content-r').on('click', '#addbtn', add);
		$('#content-r').on('click', '#delebtn', del);
		$('#content-r').on('click', '#btn-cancel', back);
	     search();//查询列表
	     comp.prePageHtml = $('#content-r').html();
	});

/**
 * 分页请求数据
 * @param pageNo
 * @param pageSize
 */
function list(pageNo, pageSize){
	var url = window.ctx+'/competence/list';
	var data = [
	            {name:'keywords',value:$("#keywords").val()},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){
		var html = ""; 
		if(page.totalCount > 0){
			$.each(page.result, function(i,competence){
				html+= '<tr class="pr">';
				/*html+= '<td><p class="checkbox_box"><input class="checkBox" data-name="check-id" data-id="'+competence.id+'" value="'+competence.id+'" type="checkbox" name="checkId" value=""><span class="checkBox"></span></p></td>';*/
				html+= '<td ">'+competence.parentId +'</td>';
				html+= '<td ">'+competence.name +'</td>';
				html+= '<td ">'+competence.description +'</td>';
				html+= '<td ">'+competence.resCode +'</td>';
				html+= '<td ">'+competence.reqMapping +'</td>';
				/*html+= '<td><a class="db-icon et-icon edit-icon" href="javascript:void(0);" onclick="toEdit(\''+competence.id+'\')"></a><a class="db-icon et-icon del-icon" href="javascript:void(0);" onclick="deleteById(\''+competence.id+'\')"></a></td>';*/
				html+= '</tr>';
			});
			
		}else{
			html += '<tr class="pr"><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
		$('#list-tbody').html(html);
		refreshPaginator(page.totalCount, pageSize, pageNo);
	});
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
	list(wspageNo, wspageSize);
};
/**
 * 添加
 */
function add(){
	var url = window.ctx + '/competence/to-add';
	$.get(url, function(page){
		$('#content-r').html(page);
		comp.validateForm('add-comp-form');
	});
};

/**
 * 修改
 * @param competenceId
 */
function toEdit(competenceId){
	var url = window.ctx + '/competence/to-edit/' + competenceId;
	$.get(url, function(page){
		$('#content-r').html(page);
		comp.validateForm('edit-comp-form');
	});
};

/**
 * js提交验证
 */
comp.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{
			debug:false,
			Id:{
				required:false,
			},
			name:{
				required:true,
				stringMaxLength:50,//最大50个字符
				remote:{//根据当前账号名称查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/competence/checkName",
		               data:{
		            	   id:function(){ return $("#Id").val();},
		            	   name:function(){ return $("#name").val();}		
		               } 
		         },
			},
			resCode:{
				required:true,
				letter:true,
				stringMaxLength:25,//最大25个字符
			},
			reqMapping:{
				required:true,
				stringMaxLength:200,//最大200个字符
			},
			ParentId:{
				required:false,
				letter:true,
				stringMaxLength:25,//最大25个字符
			},
			description:{
				stringMaxLength:100,//最大100个字符
			},
		},
		messages:{
			name:{
				required:'请输入权限名称!',
				remote:'该权限已存在!',
			},
			resCode:{
				required:'请输入权限代码!',
			},
			reqMapping:{
				required:'请输入URL地址!',
			},
		},
		 submitHandler:function(form){
			var url = window.ctx + '/competence/save';
			var data = $('#'+tagid).serializeArray();
			$.post(url, data, function(feedbak){
				if (feedbak.successful) {
					// 保存成功
					remind("success-remind-box");
					$('#content-r').html(comp.prePageHtml);
					search();//查询列表
				} else {
					remind("fail-remind-box");
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
	var url = window.ctx + '/competence/delete';
	var array = [claId];
	var data=[{
		name:'ids',
		value:array.join(',')
	}];
	$.post(url, data,function(feedbak){
		if (feedbak.successful) {
			remind("success-remind-box");
			search();//查询列表
		} else {
			remind("fail-remind-box");
		}
	});
   });
};
/**
 * 取消
 */
function back(){
	if(comp.prePageHtml!=null){
		$('#content-r').html(comp.prePageHtml);
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
	var url = window.ctx + '/competence/delete';
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
			remind("success-remind-box");
			search();//查询列表
		} else {
			remind("fail-remind-box");
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
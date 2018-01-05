/**
 * @description 班级管理管理JS脚本
 * @auth wwq
 */
var clas = {};
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
clas.prePageHtml = null;
$(function(){//绑定事件
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#addbtn', add);
	$('#content-r').on('click', '#delebtn', del);
	$('#content-r').on('click', '#btn-cancel', back);
	$('#content-r').on('click', '#importbtn', toImport);
	search();//查询列表
	clas.prePageHtml = $('#content-r').html();
});

/**
 * 查询列表，将查询的结果放到指定的tbody中
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function list(pageNo, pageSize){
	var url = window.ctx + '/clas/list';//查询的URL地址
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
				if(userType == 2){
				html+= '<td ><p class="checkbox_box"><input type="checkbox" class="checkBox" data-name="check-id" data-id="'+value.id+'" name="checkId" value="'+value.id+'"/><span class="checkBox"></span> </td>';
				}
				html+= '<td align="left">'+ value.code +'</td>';
				html+= '<td align="left">'+ value.name +'</td>';
				html+= '<td align="left">'+ value.num +'</td>';
				/*html+= '<td align="left">'+ value.description +'</td>';*/
				if(userType == 2){
				html+= '<td align="center"><a onclick="toEdit(\''+value.id+'\')" href="javascript:void(0)" title="编辑" class="db-icon et-icon edit-icon"></a><a onclick="deleteById(\''+value.id+'\')" href="javascript:void(0)" title="删除" class="db-icon et-icon del-icon"></a></td>';
				}
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
 * 班级管理批量删除
 */
function del(){
	if($("#list-tbody input[data-name=check-id]:checked").length == 0){
		showError('请选择需要删除的班级！')
		return;
	}
	//显示提示框
	showDialog();
	//点击确定按钮
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/clas/delete';
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
 * 班级管理查询
 */
function search(){
	refresh();
	list(wspageNo, wspageSize);
};

/**
 * 班级管理取消
 */
function back(){
	if(clas.prePageHtml!=null){
		$('#content-r').html(clas.prePageHtml);
	}
	search();
};

/**
 * 班级信息导入
 */
function toImport(){
	var url = window.ctx + '/clas/to-import';
	$.get(url, function(page){
		$('#content-r').html(page);
		clas.validateForm('import-clas-form');
	});
};

/**
 * 班级管理单条删除
 * @param id 当前班级管理主键ID
 */
function deleteById(id){
	//显示提示框
	showDialog();
	//点击确定按钮
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/clas/delete';
		var array = [id];
		var data=[
		    {name:'ids',value:array.join(',')},
		    {name:'tokens',value:$('#tokens').val()}
		    ];
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
 * 班级管理修改
 * @param id 当前班级管理主键ID
 */
function toEdit(id){
	var url = window.ctx + '/clas/to-edit/' + id;
	$.get(url, function(page){
		$('#content-r').html(page);
		clas.validateForm('edit-clas-form');
	});
};

/**
 * 班级管理添加
 * @param id 当前班级管理主键ID
 */
function add(){
	var url = window.ctx + '/clas/to-add';
	$.get(url, function(page){
		$('#content-r').html(page);
		clas.validateForm('add-clas-form');
	});
};

//班级管理管理form表单验证
clas.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{//对指定的字段进行判空或者重复
			debug:false,
			code:{//班级代码
				required:true,
				stringMaxLength:10,//最大只能输入10个字符
				remote:{//根据当前班级管理名称查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/clas/checkClasCode",
		               data:{
		            	   id:function(){ return $("#id").val();},
		            	   code:function(){ return $("#code").val();}
		               } 
		         },
			},
			name:{//班级名称
				required:true,
				stringMaxLength:50,//最大只能输入50个字符
				remote:{//根据当前班级管理名称查询数据库否是重复
		               type:"POST",
		               url:window.ctx + "/clas/checkClasName",
		               data:{
		            	   id:function(){ return $("#id").val();},
		            	   name:function(){ return $("#name").val();}
		               } 
		         },
			},
			num:{//班级人数
				number:true,
				required:true,
				stringMaxLength:9,//最大只能输入9个字符
			},
			description:{//班级描述
				stringMaxLength:100,//最大只能输入100个字符
			},
		},
		messages:{//对指定的字段进行消息提示
			code:{//班级代码
				required:'请输入班级代码!',
				remote:'该班级代码已存在!',
			},
			name:{//班级名称
				required:'请输入班级名称!',
				remote:'该班级名称已存在!',
			},
			num:{//班级人数
				required:'请输入班级人数!',
			},
		},
		 submitHandler:function(form){
			var url = window.ctx + '/clas/save';
			var data = $('#'+tagid).serializeArray();
			$.post(url, data, function(feedbak){//如果上面验证通过则进行保存操作
				if(feedbak.successful){
					remind("success-remind-box");
					if(clas.prePageHtml!=null){
						$('#content-r').html(clas.prePageHtml);
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
 * 刷新页面，allcheck的选中状态
 */
function refresh() {
	$('#check-all-list').attr('checked', false);
}
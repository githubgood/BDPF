/**
 * 模板-老师js
 */
var template = {};
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
template.prePageHtml = null;

$(function() {
	loadGif();
	//布局存在问题最好是先写好引入  现在使用js加载进去的
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#addbtn', add);
	$('#content-r').on('click', '#delbtn', del);
	$('#content-r').on('click', '#btn-cancel', back);
	template.prePageHtml = $('#content-r').html();
	list();
})

/**
 * 请求数据
 * 
 */
function list() {
	var url = window.ctx + '/template/list';
	var data = [ 
	    {name : 'keywords',value : $("#keywords").val()}]
	$.get(url,data,function(page) {
		var html = "";
		if (page.totalCount > 0) {
		$.each(page.result,function(i, image) {
			html+= '<tr class="pr">';
			if(userType == 2){
				html+= '<td ><p class="checkbox_box"><input type="checkbox" class="checkBox" data-name="check-id" data-id="'+image.id+'" name="checkId" value="'+image.id+'"/><span class="checkBox"></span> </td>';		
			}
			html+= '<td >'+ image.name +'</td>';
			html+= '<td >模板</td>';
			html+= '<td >'+ image.description +'</td>';
			html+= '<td ><span class="c_service">'+ image.status +'</span></td>';
			html+= '<td >公有</td>';
			html+= '<td >否</td>';
			html+= '<td >QCOW2</td>';
			html+= '<td >'+ (image.size/(1024*1024)).toFixed(2) +'M </td>';
			//html+= '<td align="center"><a onclick="toEdit(\''+value.id+'\')" href="javascript:void(0)" title="编辑" class="db-icon et-icon edit-icon"></a><a onclick="deleteById(\''+value.id+'\')" href="javascript:void(0)" title="删除" class="db-icon et-icon del-icon"></a></td>';
			//html+= '<a class="db-icon et-icon edit-icon" href="javascript:void(0);"></a>';
			if(userType == 2){
				html+= '<td>';
				html+= '<a class="db-icon et-icon edit-icon" href="javascript:void(0);" onclick="toEdit(\''+ image.id+ '\')"></a>';
				html+= '<a class="db-icon et-icon del-icon" href="javascript:void(0);" onclick="deleteById(\''+ image.id+ '\')"></a>';
				html+= '</td >';
			}
			html+= '</tr>';
		})
		} else {
			html += '<div style="margin: auto;">对不起，暂时没有您所需要的数据！</div>';
		}
			$('#list-tbody').html(html);
			closeGif();
		});
	
};


/**
 * 添加
 */
function add() {
	var url = window.ctx + '/template/to-add';
	$.get(url, function(page) {
		$('#content-r').html(page);
		template.validateForm('add-template-form');
	});
};

/**
 * 课程资源修改
 * @param id 当前课程资源主键ID
 */
function toEdit(id){
	var url = window.ctx + '/template/to-edit/' + id;
	$.get(url, function(page){
		$('#content-r').html(page);
		template.validateForm('edit-template-form');
	});
};

/**
 * js提交验证
 */
template.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{
			debug:false,
			name:{
				stringMaxLength:20,
				required:true,
			},
			textfield:{//上传文件
				required:(tagid == 'add-template-form'),
			},	
			description:{//描述
				required:false,
				stringMaxLength:20,
			},	
		},
		messages:{
			name:{
				required:'请输入模板名称!',
			},
			textfield:{//上传文件
				required:'请选择文件上传!',
		    },	
		},
		 submitHandler:function(form){
				var url = window.ctx + '/template/save';
				var suf=$('#'+tagid).serialize();
				url=url+"?"+suf;
				var data = $('#'+tagid).serializeArray();
			    var fileName = $("#file").val();//文件名
			    jQuery.ajaxSettings.traditional = true;
			    var size;
			    if(typeof(fileName)!="undefined" && fileName != null && fileName != ""){
			    	fileName = fileName.split("\\");
			    	fileName = fileName[fileName.length-1];
			    	var fileSzie = document.getElementById('file');
			    	size = fileSzie.files[0].size;
			    }
			    size = 0;
			    if(size<1024000000){
			    	 loadGif();
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
							if(feedbak.successful){
								remind("success-remind-box");
								$('#content-r').html(template.prePageHtml);
								closeGif();
								search();
							}else{
								showError(feedbak.message);	
							}
						}
				    });
			    }else{
			    	showError("文件大小不能超过1G,请去云实验平台上传！");	
			    }
		
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
		loadGif();
		//执行删除功能
		var url = window.ctx + '/template/deleteById';
		var data = [ {name : 'id',value : claId} ];
		$.post(url, data, function(feedbak) {
			if (feedbak.successful) {
				remind("success-remind-box");
				closeGif();
				search();//查询列表
			} else {
				remind("fail-remind-box");
			}

		});
	})
	
};

/**
 * 搜索
 */
function search() {
	refresh();
	loadGif();
	list();//查询列表
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
	$(".btn-save").on("click",function(){
		$('.modal').modal('hide');//先隐藏弹出框
		loadGif();
		//执行删除功能
		var url = window.ctx + '/template/delete';
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
				closeGif();
				search();;//查询列表
			} else {
				remind("fail-remind-box");
			}
		});
	})
	
};


/**
 * 返回
 */
function back(){
	if(template.prePageHtml!=null){
		$('#content-r').html(template.prePageHtml);
	}
	search();;//查询列表
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
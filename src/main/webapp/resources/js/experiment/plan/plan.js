/**
 * @description 课程计划管理JS脚本
 * @auth wwq
 */
var plan = {};
plan.prePageHtml = null;
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
$(function(){
	/*事件绑定*/
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#addbtn', add);
	$('#content-r').on('click', '#delebtn', del);
	$('#content-r').on('click', '#btn-cancel', back);
    search();//查询列表
	plan.prePageHtml = $('#content-r').html();
});

/**
 * 查询列表，将查询的结果放到指定的tbody中
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function list(pageNo, pageSize){
	var url = window.ctx + '/plan/list';//查询的URL地址
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
				html+= '<td class="tal" style="padding-left:80px;">'+ value.learningTime +'</td>';
				html+= '<td class="tal" style="padding-left:80px;">'+ value.name +'</td>';
				html+= '<td class="tal" style="padding-left:80px;">'+ courseType(value.courseType)+'</td>';
				/*html+= '<td class="tal" style="padding-left:80px;">'+ value.description +'</td>';*/
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
 * 自定义课程类型分类
 * @param type 课程类型分类编码
 * @returns {String}  课程类型分类名称
 */
function courseType(type){
	var value="";
	if(type=="0"){
		value="课程实验";
	}else if(type=="1"){
		value="公共实验";
	}
	return value;
}
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
 * 课程计划批量删除
 */
function del(){
	if($("#list-tbody input[data-name=check-id]:checked").length == 0){
		showError('请选择需要删除的课程计划！');
		return;
	}
	//显示提示框
	showDialog();
	//点击确定按钮
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/plan/delete';
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
 * 课程计划查询
 */
function search(){
	refresh();
    list(wspageNo, wspageSize);
};

/**
 * 课程计划取消
 */
function back(){
	if(plan.prePageHtml!=null){
		$('#content-r').html(plan.prePageHtml);
	}
    search();
};

/**
 * 课程计划单条删除
 * @param id 当前课程资源主键ID
 */
function deleteById(id){
	//显示提示框
	showDialog();
	//点击确定按钮
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		//执行删除功能
		var url = window.ctx + '/plan/delete';
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
 * 课程计划修改
 * @param id 当前课程计划主键ID
 */
function toEdit(id){
	var url = window.ctx + '/plan/to-edit/' + id;
	$.get(url, function(page){
		$('#content-r').html(page);
		plan.validateForm('edit-plan-form');
	});
};

/**
 * 课程计划添加
 * @param id 当前课程资源主键ID
 */
function add(){
	var url = window.ctx + '/plan/to-add';
	$.get(url, function(page){
		$('#content-r').html(page);
		plan.validateForm('add-plan-form');
		//点击显示
		$(".nice-select").click(function(e){
			$(this).find(".select-box").show();
			e.stopPropagation();
		})
		$(".line-a").click(function(e){
			$("#line").val($(this).attr("name"));
		})

	});
};

//课程资源管理form表单验证
plan.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{//对指定的字段进行判空或者重复
			debug:false,
			learningTime:{
				required:true,
				isNumber:true,
				stringMaxLength:10,//最大只能输入10个字符
			},
			name:{
				stringMaxLength:50,//最大只能输入50个字符
				required:true,
				remote:{
		               type:"POST",
		               url:window.ctx + "/plan/checkName",
		               data:{
		            	   id:function(){ return $("#id").val();},
		            	   name:function(){ return $("#name").val();}		
		               } 
		         },
			},
			courseType:{
				required:true,
			},
			description:{//班级描述
				stringMaxLength:100,//最大只能输入100个字符
			},
		},
		messages:{//对指定的字段进行消息提示
			learningTime:{
				required:'请输入课时!',//计划课时
			},
			name:{
				required:'请输入名称!',//计划名称
				remote:'该资名称已存在!',
			},
			courseType:{
				required:'请选择类型!',//课程类型
		  }
		},
		 submitHandler:function(form){
				var courseId = "";
				var line=$("#line").val();
			$("#"+'content-r input[name='+line+']').each(function(){ //遍历table里的全部checkbox
					if($(this).prop("checked")) //如果被选中
						courseId += $(this).val() + ","; //获取被选中的值
			});
           if(courseId==""){
       		showError('请选择课程资源！');
        	   return
           }
			var url = window.ctx + '/plan/save/' + courseId;
			var data = $('#'+tagid).serializeArray();
			$.post(url, data, function(feedbak){//如果上面验证通过则进行保存操作
				if(feedbak.successful){
					remind("success-remind-box");
					if(plan.prePageHtml!=null){
						$('#content-r').html(plan.prePageHtml);
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
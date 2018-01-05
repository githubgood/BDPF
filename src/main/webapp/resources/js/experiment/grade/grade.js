/**
 * @description 课程资源管理JS脚本
 * @auth wwq
 */
var grade = {};
grade.prePageHtml = null;
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
$(function(){
	/*事件绑定*/
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#btn-cancel', back);
	$('#taskId').change("taskId",change);
	$('#classId').change("classId",change);
});

/**
 * 查询列表，将查询的结果放到指定的tbody中
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function list(pageNo, pageSize){
	var url = window.ctx + '/grade/list';//查询的URL地址
	var data = [//查询的参数
	            {name:'taskId',value:$('#taskId').val()},
	            {name:'classId',value:$('#classId').val()},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.totalCount > 0){//判断是否有数据
			$.each(page.result, function(i,value){//进行数据拼接
				html+= '<tr>';
				html+= '<td align="left">'+ value.className +'</td>';
				html+= '<td align="left">'+ value.studentId +'</td>';
				html+= '<td align="left">'+ value.studentName+'</td>';
				html+= '<td align="left">'+ value.score +'</td>';
				html+= '<td align="center"><a onclick="toDetail(\''+value.studentId+'\')" href="javascript:void(0)" title="预览" class="db-icon et-icon et-lookUp"></a></td>';
				html+= '</tr>';
			});
			
		}else{//如果没有数据，进行友好提示
			html += '<tr><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
		$('#list-tbody').html(html);//将拼接的结果放到指定的tbody中
		grade.totalCount=page.totalCount;
		grade.pageSize=pageSize;
		grade.pageNo=pageNo;
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
 * 课程计划查询
 */
function search(){
	if($("#taskId").val()==""){
		showError("请选择任务！")
		return;
	}
	if($("#classId").val()==""){
		showError("请选择班级！")
		return;
	}
    list(wspageNo, wspageSize);
};

/**
 * 返回菜单
 */
function back(){
	$("#message").show();
	$("#back").hide();
	if(grade.prePageHtml!=null){
		$('#content-r').html(grade.prePageHtml);
		$('#taskId').change("taskId",change);
		$('#classId').change("classId",change);
	}else{
		search();
	}
	refreshPaginator(grade.totalCount, grade.pageSize, grade.pageNo);//刷新分页
};

/**
 * 下拉选择事件
 */
function change() {
 var id= $(this).attr("id");
 var value= $(this).val();
 $("#"+id+' '+'option').attr('selected',false);
 $("#"+id).val(value);
 $("#"+id+' '+'option[value='+value+']').attr("selected",true)

}

/**
 * 查看成绩详情
 * @param studentId
 */
function toDetail(studentId){
	grade.prePageHtml = $('#content-r').html();
	grade.studentId=studentId;
	$("#message").hide();
	$("#back").show();
	var url = window.ctx + '/grade/to-detail';
	var data = [//查询的参数
	];
	$.post(url,data,function(page){
		$('#detail').html(page);
		detailList(wspageNo, wspageSize,studentId);
	});
};

/**
 * 查看成绩详情
 * @param studentId
 */
function detailList(pageNo, pageSize){
	var url = window.ctx + '/grade/detail';//查询的URL地址
	var data = [//查询的参数
	            {name:'taskId',value:$('#taskId').val()},
	            {name:'classId',value:$('#classId').val()},
	            {name:'studentId',value:grade.studentId},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.totalCount > 0){//判断是否有数据
			$.each(page.result, function(i,value){//进行数据拼接
				html+= '<tr>';
				html+= '<td align="left">'+ value.className +'</td>';
				html+= '<td align="left">'+courseType(value.courseResourceType) +'</td>';
				html+= '<td align="left">'+ value.title +'</td>';
				html+= '<td align="left">'+ value.studentId +'</td>';
				html+= '<td align="left">'+ value.studentName+'</td>';
				html+= '<td align="left">'+ value.score +'</td>';
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
 * 详情分页
 * @param totalCount
 * @param pageSize
 * @param pageNo
 */
function detailRefreshPaginator(totalCount, pageSize, pageNo){
	$('#paginator-tool-detail').smartpaginator({
		totalrecords : totalCount,
		recordsperpage : pageSize,
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

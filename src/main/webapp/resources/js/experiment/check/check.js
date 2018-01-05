/**
 * @description 实验批改JS脚本
 * @auth wwq
 */
var check = {};
check.prePageHtml = null;
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
	var url = window.ctx + '/check/list';//查询的URL地址
	var data = [//查询的参数
	            {name:'taskId',value:$('#taskId').val()},
	            {name:'classId',value:$('#classId').val()},
	            {name:'title',value:$('#title').val()},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.totalCount > 0){//判断是否有数据
			$.each(page.result, function(i,value){//进行数据拼接
				html+= '<tr>';
				html+= '<td align="left">'+ value.title +'</td>';
				html+= '<td align="left">'+ courseType(value.courseResourceType) +'</td>';
				html+= '<td align="left">'+ (value.courseResourceType==0 ? "分组" : "单人")+'</td>';
				html+= '<td align="left">'+ value.className+'</td>';
				html+= '<td align="left">'+'<a onclick="toDetail(\''+value.courseResourceId+'\')" href="javascript:void(0)" >'+ value.sumTaskStatus +'/'+value.countStudentId+'</a></td>';			
				html+= '<td align="left">'+ taskStatus(value.taskStatus) +'</td>';
				html+= '<td align="center"><a onclick="toCheck(\''+value.courseResourceId+'\',\''+value.classId+'\',\''+value.courseResourceType+'\')" href="javascript:void(0)" title="批改实验" class="db-icon et-icon et-lookUp"></a></td>';
				html+= '</tr>';
			});
			
		}else{//如果没有数据，进行友好提示
			html += '<tr><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
		$('#list-tbody').html(html);//将拼接的结果放到指定的tbody中
		check.totalCount=page.totalCount;
		check.pageSize=pageSize;
		check.pageNo=pageNo;
		refreshPaginator(page.totalCount, pageSize, pageNo);//分页
	});
};

/**
 * 查看成绩详情
 * @param studentId
 */
function toDetail(courseResourceId){
	check.prePageHtml = $('#content-r').html();
	check.courseResourceId=courseResourceId;
	$("#message").hide();
	$("#back").show();
	var url = window.ctx + '/check/to-detail';
	var data = [//查询的参数
	];
	$.post(url,data,function(page){
		$('#detail').html(page);
		detailList(wspageNo, wspageSize);
	});
};

/**
 * 实现批改明细
 * @param courseResourceId
 * @returns {String}
 */
function detailList(pageNo, pageSize){
	var url = window.ctx + '/check/listDetail';//查询的URL地址
	var data = [//查询的参数
	            {name:'taskId',value:$('#taskId').val()},
	            {name:'classId',value:$('#classId').val()},
	            {name:'courseResourceId',value:check.courseResourceId},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.totalCount > 0){//判断是否有数据
			$.each(page.result, function(i,value){//进行数据拼接
				html+= '<tr>';
				html+= '<td align="left">'+ value.title +'</td>';
				html+= '<td align="left">'+ value.className+'</td>';
				html+= '<td align="left">'+ value.studentId+'</td>';
				html+= '<td align="left">'+ value.studentName+'</td>';
				html+= '<td align="left">'+(value.reportStatus==1 ? "已提交" : "未提交")+'</a></td>';			
				html+= '<td align="left">'+ (value.taskStatus==1 ? "已批改" : "未批改")+'</td>';			
				html+= '</tr>';
			});
			
		}else{//如果没有数据，进行友好提示
			html += '<tr><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
		$('#list-tbody').html(html);//将拼接的结果放到指定的tbody中
		detailRefreshPaginator(page.totalCount, pageSize, pageNo);//分页
	});
}

function taskStatus(type){
	var value="";
	if(type=="0"){
		value="未批改";
	}else if(type=="1"){
		value="批改中";
	}else if(type=="2"){
		value="已批改";
	}
	return value;
}

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
 * 返回菜单
 */
function back(){
	$("#message").show();
	$("#back").hide();
	if(check.prePageHtml!=null){
		$('#content-r').html(check.prePageHtml);
		$('#taskId').change("taskId",change);
		$('#classId').change("classId",change);
	}else{
		search();
	}
	refreshPaginator(check.totalCount, check.pageSize, check.pageNo);//刷新分页
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

/**
 * 课程计划查询
 */
function search(){
	if($('#taskId').val()==""){
		showError("请选择任务名称进行查询");
		return;
	}
	check.taskId=$('#taskId').val();
    list(wspageNo, wspageSize);
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
 * 进入实验批改页面
 * @param courseResourceId 课程资源ID
 * @param classId 班级ID
 * @param type 分组类型
 */
function toCheck(courseResourceId,classId,type){
	var taskId=check.taskId;
	var url;
	if(type==0){//分组实验
		url = window.ctx + '/check/to-group';
	}else{//个人实验
		url = window.ctx + '/check/to-person';
	}
	//弹出对应实验窗口
	window.open(url+"/"+taskId+"/"+courseResourceId+"/"+classId);
};

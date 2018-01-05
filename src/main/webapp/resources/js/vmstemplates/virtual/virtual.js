/**
 * @description 虚拟机管理JS脚本
 * @auth wwq
 */
var virtual = {};
$(function(){//绑定事件
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#batchstart', "START",batchServerAction);
	$('#content-r').on('click', '#batchstop', "STOP",batchServerAction);
	$('#content-r').on('click', '#batchRestart',batchRestart);
	$('#content-r').on('click', '#batchDel',batchDel);
	search();//查询列表
});

/**
 * 查询列表，将查询的结果放到指定的tbody中
 */
function list(){
	var url = window.ctx + '/virtual/list';//查询的URL地址
	var data = [//查询的参数
	            {name:'keywords',value:$('#keywords').val()},
	            {name:'classId',value:$('#classId').val()},
	            {name:'status',value:$('#status').val()},
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if (page.totalCount > 0) {
		var onclick="";
			$.each(page.result, function(i,value){//进行数据拼接
				if(value.runstatus=="运行中"){
					onclick='<a onclick="stop(\''+value.id+'\',event)" href="javascript:void(0)" title="关机" class="db-icon VMware-icon shutdown"></a><a onclick="restart(\''+value.id+'\',event)" href="javascript:void(0)" title="重启" class="db-icon VMware-icon restart"></a><a onclick="deleteById(\''+value.id+'\')" href="javascript:void(0)" title="删除" class="db-icon VMware-icon delete"></a>';
				}else {
					onclick='<a onclick="start(\''+value.id+'\',event)" href="javascript:void(0)" title="开机" class="db-icon VMware-icon starting_up"></a><a onclick="restart(\''+value.id+'\',event)" href="javascript:void(0)" title="重启" class="db-icon VMware-icon restart"></a><a onclick="deleteById(\''+value.id+'\')" href="javascript:void(0)" title="删除" class="db-icon VMware-icon delete"></a>';
				}
				html+= '<tr>';
				html+= '<td ><p class="checkbox_box"><input type="checkbox" class="checkBox" data-name="check-id" data-id="'+value.id+'" name="checkId" value="'+value.id+'"/><span class="checkBox"></span> </td>';
				html+= '<td >'+ value.name +'</td>';
				html+= '<td >'+ value.runstatus +'</td>';
				html+= '<td >普通虚拟机</td>';
				html+= '<td >'+ value.ip +'</td>';
				html+= '<td ><a target="view_window" href='+value.vnc+'> '+ value.vncInfo +'</a></td>';
				html+= '<td >'+onclick+'</td>';
				html+= '</tr>';
			});	
			$('#paginator-tool').show();
			$('#paginator-tool').html('<p>当前总有'+page.totalCount+'条数据</p>');
		}else{
			html += '<div style="margin: auto;">对不起，暂时没有您所需要的数据！</div>';
			$('#paginator-tool').hide();
		}
		$('#list-tbody').html(html);//将拼接的结果放到指定的tbody中
		closeGif();//关闭动画
	});
};

/**
 * 虚拟机批量启动、关闭
 */
function batchServerAction(value){
	if($("#list-tbody input[data-name=check-id]:checked").length == 0){
		showError('请选择需要操作的虚拟机！');
		return;
	}
		var url = window.ctx + '/virtual/batchServerAction';
		var array = [];
		$('#list-tbody input[data-name="check-id"]').each(function(e){
			if($(this).prop('checked')){
				array.push($(this).attr('data-id'));
			}
		});
		var data=[
		          {name:'ids',value:array.join(',')},
		          {name:'type',value:value.data},
		          ];
		loadGif();//加载动画
		$.post(url, data,function(feedbak){
			if(feedbak.successful){
				setTimeout('search()',3000); 
			}else{
				 showError(feedbak.message);
				 closeGif();//关闭动画
			}
		});
};

function batchRestart(){
	if($("#list-tbody input[data-name=check-id]:checked").length == 0){
		showError('请选择需要操作的虚拟机！');
		return;
	}
		var url = window.ctx + '/virtual/batchRestart';
		var array = [];
		$('#list-tbody input[data-name="check-id"]').each(function(e){
			if($(this).prop('checked')){
				array.push($(this).attr('data-id'));
			}
		});
		var data=[
		          {name:'ids',value:array.join(',')},		    
		          ];
		loadGif();//加载动画
		$.post(url, data,function(feedbak){
			if(feedbak.successful){
				setTimeout('search()',3000); 
			}else{
				 showError(feedbak.message);
				 closeGif();//关闭动画
			}
		});
};

/**
 * 批量删除虚拟机
 */
function batchDel(){
	if($("#list-tbody input[data-name=check-id]:checked").length == 0){
		showError('请选择需要操作的虚拟机！');
		return;
	}
	showDialog();
	$(".btn-save").on("click",function(){
		$('.modal').modal('hide');//先隐藏弹出框
		loadGif();//加载动画
		var url = window.ctx + '/virtual/batchServerDel';
		var array = [];
		$('#list-tbody input[data-name="check-id"]').each(function(e){
			if($(this).prop('checked')){
				array.push($(this).attr('data-id'));
			}
		});
		var data=[
		          {name:'ids',value:array.join(',')},		    
		          ];
		$.post(url, data,function(feedbak){
			if(feedbak.successful){
				setTimeout('search()',3000); 
			}else{
				 showError(feedbak.message);
				 closeGif();//关闭动画
			}
		});
	});
};

/**
 * 虚拟机查询
 */
function search(){
	refresh();
	loadGif();//加载动画
	list();
};


/**
 * 虚拟机单条删除
 * @param id 当前班级管理主键ID
 */
function deleteById(id){
	//显示提示框
	showDialog();
	//点击确定按钮
//	$(".btn-save").on("click",function(){
	$(".btn-save").unbind('click').click(function(){
		$('.modal').modal('hide');//先隐藏弹出框
		loadGif();//加载动画
		//执行删除功能
		var url = window.ctx + '/virtual/delete';
		var array = [id];
		var data=[{
			name:'ids',
			value:array.join(',')
		}];
		$.post(url, data,function(feedbak){
			if(feedbak.successful){//成功，提示信息
				setTimeout('search()',3000); 
			}else{
				remind(feedbak.message);
				closeGif();//关闭动画
			}
		});
	});
};

/**
 * 虚拟机关闭
 * @param id 当前班级管理主键ID
 */
function stop(id,event){
	event.stopPropagation();
	var url = window.ctx + '/virtual/stop/' + id;
	loadGif();//加载动画
	$.post(url, function(feedbak){
		if(feedbak.successful){//成功，提示信息
			setTimeout('search()',8000); 
		}else{
			  showError(feedbak.message);
			  closeGif();//关闭动画
		}
	});
};

/**
 * 虚拟机开机
 * @param id 当前班级管理主键ID
 */
function start(id,event){
	event.stopPropagation();
	var url = window.ctx + '/virtual/start/' + id;
	loadGif();//加载动画
	$.post(url, function(feedbak){
		if(feedbak.successful){//成功，提示信息
			setTimeout('search()',3000); 
		}else{
		   showError(feedbak.message);
		   closeGif();//关闭动画
		}
	});
};

/**
 * 虚拟机重启
 * @param id
 */
function restart(id,event){
	event.stopPropagation();
	var url = window.ctx + '/virtual/restart/' + id;
	loadGif();//加载动画
	$.post(url, function(feedbak){
		if(feedbak.successful){//成功，提示信息
			setTimeout('search()',3000); 
		}else{
		   showError(feedbak.message);
		   closeGif();//关闭动画
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

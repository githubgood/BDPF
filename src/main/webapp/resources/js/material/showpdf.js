
/**
 * 预览文件js
 * @author wanglf
 */
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据


//自动加载 
$(function(){
	var id = $('#cid').val();//资料id
	var type = $('#ctype').val();//资料类型
	list(wspageNo, wspageSize,id,type);//查询列表
});
/**
 * 分页请求数据
 * 
 * @param pageNo
 * @param pageSize
 */
function list(pageNo, pageSize,id,type) {
	var url = window.ctx + '/material/typeList';
	var data = [ 
	    {name : 'type',value : type}, 
	    {name : 'id',value : id}, 
	    {name : 'pageSize',value : pageSize}, 
	    {name : 'pageNo',value : pageNo} ];
	$.get(url,data,function(page) {
		var html = "";
		if (page.totalCount > 0) {
		$.each(page.result,function(i, course) {
			var fileExtension = (course.path).split('.').pop().toLowerCase();
			html += '<li class="et-document active" onclick="show(\''+course.path+'\',\''+course.title+'\',\''+course.id+'\',\''+type+'\')">';
			html += '<i class="et-book mr10"></i>';
			html += '<span class="vam fsize14 c-656">'+course.title+'</span>';
			html += '</li>';
		});
		} else {
			html += '<li class="pr">对不起，暂时没有您所需要的数据！</li>';
		}
			$('#list-tbody').html(html);
			refreshPaginator(page.totalCount, pageSize, pageNo,id,type);
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
function refreshPaginator(totalCount, pageSize, pageNo,id,type) {
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
			list(newPage, pageSize,id,type);//查询列表
		}
	});
	$('#list-table tbody tr:odd').addClass('odd');
	/* 隐藏首页和尾页按钮 */
	$("#paginator-tool .btn").eq(0).hide();
	$("#paginator-tool .btn").eq(3).hide();
};



/**
 * 点击 显示 文档信息，设置title
 * @param s
 * @param title
 */
function show(s,title,id,type) {
	$('#titles').html(title);
	$('.et-right-title').html(title);
	$('.et-right-item').html('<iframe src="'+s+'" style="width:100%;height:745px;"></iframe>');
	list(wspageNo, wspageSize,id,type);//查询列表
}
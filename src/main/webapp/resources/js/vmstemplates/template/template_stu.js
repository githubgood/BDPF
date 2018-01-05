/**
 * 模板-学生js
 */
var template = {};
template.prePageHtml = null;
$(function() {
	loadGif();
	//布局存在问题最好是先写好引入  现在使用js加载进去的
	$('#content-r').on('click', '#search', search);
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
			html+= '<li class="VMstudent-item pr">';
			html+= '<i class="state_strip proceed_state"></i>';
			html+= '<h2>'+image.name+'</h2>';
			html+= '<div class="fsize12 c-67e mt20">状态：'+image.status+'</div>';
			html+= '<div class="fsize12 c-67e mt10">大小：'+ (image.size/(1024*1024)).toFixed(2) +' M</div>';
			html+= '</li>';
		})
		} else {
			html += '<div style="margin: auto;">对不起，暂时没有您所需要的数据！</div>';
		}
			$('#list-tbody').html(html);
			closeGif();
		});
	
};

/**
 * 搜索
 */
function search() {
	loadGif();
	list();//查询列表
};

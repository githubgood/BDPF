/**
 * @description 资料库管理JS脚本
 * @auth wanglf
 */

var material = {};
material.prePageHtml = null;
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = 8;//每页显示多少条数据
//默认文件类型为文档
var types = -1;
//JavaScript根据文件名判断文件类型

var imgExt = new Array(".png",".jpg",".jpeg",".bmp",".gif");//图片文件的后缀名
var docExt = new Array(".doc",".docx");//word文件的后缀名
var xlsExt = new Array(".xls",".xlsx");//excel文件的后缀名
var pptExt = new Array(".ppt");//ppt文件的后缀名
var txtExt = new Array(".txt");//txt文件的后缀名
var mp4Ext = new Array(".mp4");//mp4文件的后缀名
//可以预览的文件类型
var allExt = new Array(".png",".jpg",".jpeg",".bmp",".gif",".pdf",".mp4");
//获取文件名后缀名
function extension(name){
    var ext = null;
    var i = name.lastIndexOf(".");
    if(i > -1){
    var ext = name.substring(i);
    }
    return ext;
}

//判断Array中是否包含某个值
function contain(obj,type){
    for(var i=0; i<obj.length; i++){
        if(obj[i] === type)
            return true;
    }
    return false;
};

//判断当前类型是否可以预览
function typeMatch(type, fielname){
    var ext = extension(fielname);
    if(contain(type,ext)){        
        return true;
    }
    return false;
}

$(function(){
	//搜索框子列表
	$(".search-box").hover(function(){
		$(this).find(".dropdown-list").stop().slideDown();
	},function(){
		$(this).find(".dropdown-list").stop().slideUp();
	})
	//布局存在问题最好是先写好引入  现在使用js加载进去的
		$('.fr').on('click', '#search', search);
		changeType(types);
		search();//查询列表
		material.prePageHtml = $('#content-r').html();
	});

var typeText = "";

function changeType(stype) {
	typeText = typeDis(stype);
	types = stype;
	$('.search-dropdown').html(typeText);
	$(".search-box .dropdown-list").hide();
}

/**
 * 分页请求数据
 * 
 * @param pageNo
 * @param pageSize
 */
function list(pageNo, pageSize) {
	var url = window.ctx + '/material/typeList';
	var data = [ 
	    {name : 'keywords',value : $("#keywords").val()},
	    {name : 'type',value : types}, 
	    {name : 'id',value : ""}, 
	    {name : 'pageSize',value : pageSize}, 
	    {name : 'pageNo',value : pageNo} ];
	$.get(url,data,function(page) {
		var html = "";
		if (page.totalCount > 0) {
		$.each(page.result,function(i, course) {
			var stype = course.fileType;
			typeText = typeDis(stype);
			html += '<li class="dataBank-item">';
			html += '</dl>';
			html += '<dt class="dataBank-item-operate tac">';
			html += '<p>';
			html += showImg(stype);
			html += '</p>';
			html += '<p class="mt10">';
			if(typeMatch(allExt, course.path)){
				html += '<a href="javascript:void(0);" class="preview_btn" onclick="toDetailW(\''+course.id+'\')">查看</a>';
				html += '<i class="dividing_line"></i>';
			}
			else{
			}
			var fileName=replaceAll(course.path);
			
     		//html += '<a href="javascript:void(0);" class="down_btn" onclick="dowload(\''+course.path+'\')">下载</a>';
			html += '<a href="'+course.path+'" class="down_btn" download="'+course.title+'">下载</a>';
			html += '</p>';
			html += '</dt>';
			html += '<dd class="dataBank-infor">';
			html += '<h2>'+course.title+'</h2>';
			html += '<p class="mt15">类型：'+typeText+'</p>';
			html += '<p>创建人：'+empty(course.createUser)+'</p>';
			html += '<p>时间：'+formatDateTime(course.createTime)+'</p>';
			html += '</dd>';
			html += '</dl>';
			html += '</li>';
		});
		} else {
			html += '<div style="margin: auto;">对不起，暂时没有您所需要的数据！</div>';
		}
			$('.content-r').html(html);
			refreshPaginator(page.totalCount, pageSize, pageNo);
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
function refreshPaginator(totalCount, pageSize, pageNo) {
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
			list(newPage, pageSize);//查询列表
			refresh();
		}
	});
	$('#list-table tbody tr:odd').addClass('odd');
};

/**
 * 搜索
 */
function search() {
	list(wspageNo, wspageSize);//查询列表
};
/**
 * 非文档格式
 * @param userId
 */
function toDetail(userId){
	var url = window.ctx + '/material/to-detail/' + userId;
	$.get(url, function(page){
		$('#content-r').html(page);
		uedRefresh();
	});
};
/**
 * 文档格式
 * @param id
 */
function toDetailW(id){
	window.open(window.ctx +'/material/to-detailDoc/'+id);
//	window.open(path);
};

/**
 * 文件下载 
 * @param tpath
 */
function dowload(tpath){
	window.open(window.ctx + '/material/download/'+replaceAll(tpath));
}

function replaceAll(tpath){
	//这里把斜杠和点转化为其他字符，然后在controller中转换回来，因为斜杠和点是特殊字符，传参传不过去
   var re=new RegExp("/","g");
   var newstr=tpath.replace(re,"|");
   re=new RegExp("\\.","g");
   newstr=newstr.replace(re,"*");
   return newstr;
}
/**
 * 根据对应type值转化为文字类型 
 * @param stype
 * @returns {String}
 */
function typeDis(stype) {
	if(stype == "0"){
		typeText = "文档";
	}else if(stype == "1"){
		typeText = "视频";
	}else if(stype == "2"){
		typeText = "图片";
	}else if(stype == "3"){
		typeText = "数据";
	}else if(stype == "4"){
		typeText = "工具";
	}else if(stype == "5"){
		typeText = "源代码";
	}else if(stype == "-1"){
		typeText = "全部";
	}
	return typeText;
}

/**
 * 时间格式化
 * @param inputTime  时间戳
 * @returns {String}
 */
function formatDateTime(inputTime) {    
    var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
};  
/**
 * //根据文件类型显示图片
 * @param stype
 * @returns {String}
 */
function showImg(stype) {
	var ss ='';
	if(stype == 0){
		ss = '<img src="resources/images/dataBank/bank_icon1.png" alt="">';
	}else if(stype == 1){
		ss = '<img src="resources/images/dataBank/bank_icon3.png" alt="">';
	}else if(stype == 2){
		ss = '<img src="resources/images/dataBank/bank_icon2.png" alt="">';
	}else if(stype == 3){
		ss = '<img src="resources/images/dataBank/bank_icon4.png" alt="">';
	}else if(stype == 4){
		ss = '<img src="resources/images/dataBank/bank_icon5.png" alt="">';
	}else if(stype == 5){
		ss = '<img src="resources/images/dataBank/bank_icon6.png" alt="">';
	}
	return ss;
}
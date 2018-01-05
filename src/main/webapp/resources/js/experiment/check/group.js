/**
 * @description 实验批改分组JS脚本
 * @auth wwq
 */
var group = {};
group.prePageHtml = null;
var wspageNo = window.pageNo;//从第几条开始取数据
var wspageSize = window.pageSize;//每页显示多少条数据
var totalCount = 0;//当前可批改的实验报告总数据条数
$(function(){
	function tab(a,b){
		var tab = $("."+a);
		tab.find("a").on("click",function(e){
			var $index = $(this).index();
			$(this).addClass("active").siblings("a").removeClass("active");
			$("."+b).find(".tab").hide().eq($index).show();		 
			 if($("#correct").is(":hidden")){
				  list(wspageNo, wspageSize);//查询列表没有批改
			   }else{
				  liststatus(wspageNo, wspageSize);//查询列表已经批改 
			 }
			 e.preventDefault();
			return false;
		})
	}
	tab("et-left-nav","et-left-item");
    list(wspageNo, wspageSize);//查询列表没有批改
});

/**
 * 查询列表，将查询的结果放到指定的tbody中
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function list(pageNo, pageSize){
	var url = window.ctx + '/check/groupList';//查询的URL地址
	var data = [//查询的参数
	            {name:'taskId',value:$('#taskId').val()},
	            {name:'courseResourceId',value:$('#courseResourceId').val()},
	            {name:'classId',value:$('#classId').val()},
	            {name:'taskStatus',value:0},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.totalCount > 0){//判断是否有数据
			var student="";
			$.each(page.result, function(i,value){//进行数据拼接
				var str=value.studentId.split(",");	
				for(var m=0;m<str.length;m++){
					student+='<span>'+str[m]+'</span>';
				}
				html+= '<li><div id= "'+value.reportBook+'" name="'+value.groupId+'" class="check-group-item pr"><p class="fsize14 c-656"><i class="db-icon group-icon"></i>';
				html+= '<span name='+value.title+'>'+ value.groupName +"-"+value.title+'</span>';
				html+= '<p class="group-member pr" style="display: none;"><i>成员：</i>'+student+'</p></p></div></li>';
				student="";
			});
			
		}else{//如果没有数据，进行友好提示
			html += '<tr><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
		$('#list-Y').html(html);//将拼接的结果放到指定的tbody中
		totalCount = page.totalCount;
		//设置点击事件
		$('#content-r').on('click', '#submit-btn', save);
		//初始化数据
		if(page.totalCount > 0){
			$("#title").html($('#list-Y li:first').find("span").attr("name"));
			$('#list-Y li:first').children("div").addClass("on");	
			$('.et-right-item').html('<iframe src="'+$('#list-Y li:first').children("div").attr("id")+'" style="width:100%;height:745px;"></iframe>');		
		}else{//如果没有数据，进行友好提示
			$('.et-right-item').html("当前没有可批改的实验报告！");
		}
		function noCheck(elem){
			var a = $("."+elem);
			a.click(function(){
				a.removeClass("on");
				$(this).toggleClass("on");
				$(this).parent().siblings().find(".group-member").slideUp();
				$(this).find(".group-member").is(":hidden")?$(this).find(".group-member").slideDown():$(this).find(".group-member").slideUp();
				$('.et-right-item').html('<iframe src="'+$(this).attr("id")+'" style="width:100%;height:745px;"></iframe>');
			})
		}
		noCheck("check-group-item");
		noCheck("noCheck-group-item");
		refreshPaginator(page.totalCount, pageSize, pageNo);//分页
	});
};

/**
 * 查询列表，将查询的结果放到指定的tbody中
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function liststatus(pageNo, pageSize){
	var url = window.ctx + '/check/groupList';//查询的URL地址
	var data = [//查询的参数
	            {name:'taskId',value:$('#taskId').val()},
	            {name:'courseResourceId',value:$('#courseResourceId').val()},
	            {name:'classId',value:$('#classId').val()},
	            {name:'taskStatus',value:1},
	            {name:'pageSize',value:pageSize},
	            {name:'pageNo',value:pageNo}
	];
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.totalCount > 0){//判断是否有数据			
			var student="";
			$.each(page.result, function(i,value){//进行数据拼接
				var str=value.studentId.split(",");	
				for(var m=0;m<str.length;m++){
					student+='<span>'+str[m]+'</span>';
				}
				html+= '<li><div id= "'+value.reportBook+'" name="'+value.groupId+'" class="check-group-item pr"><p class="fsize14 c-656"><i class="db-icon group-icon"></i>';
				html+= '<span name='+value.title+'>'+ value.groupName +"-"+value.title+'</span>';
				if(value.score == 100){//评分等于100    5星
					html+= '<p class="check-grade mt10 mb10"><span class="vam mr10 fsize14">评分</span><i class="db-icon no-star star-icon"></i><i class="db-icon no-star star-icon"></i><i class="db-icon no-star star-icon"></i><i class="db-icon no-star star-icon"></i><i class="db-icon no-star  star-icon"></i></p><p class="group-member pr" style="display: none;"><i>成员：</i>'+student+'</p></p></div></li>';
				}else if(value.score>90){//评分大于90  4星
					html+= '<p class="check-grade mt10 mb10"><span class="vam mr10 fsize14">评分</span><i class="db-icon no-star star-icon"></i><i class="db-icon no-star star-icon"></i><i class="db-icon no-star star-icon"></i><i class="db-icon no-star  star-icon"></i><i class="db-icon no-star"></i></p><p class="group-member pr" style="display: none;"><i>成员：</i>'+student+'</p></p></div></li>';
				}else if(value.score>80){//评分大于80  3星
					html+= '<p class="check-grade mt10 mb10"><span class="vam mr10 fsize14">评分</span><i class="db-icon no-star star-icon"></i><i class="db-icon no-star star-icon"></i><i class="db-icon no-star star-icon"></i><i class="db-icon no-star"></i><i class="db-icon  no-star"></i></p><p class="group-member pr" style="display: none;"><i>成员：</i>'+student+'</p></p></div></li>';
				}else if(value.score>70){//评分大于70  2星
					html+= '<p class="check-grade mt10 mb10"><span class="vam mr10 fsize14">评分</span><i class="db-icon no-star star-icon"></i><i class="db-icon no-star star-icon"></i><i class="db-icon no-star"></i><i class="db-icon no-star"></i><i class="db-icon no-star"></i></p><p class="group-member pr" style="display: none;"><i>成员：</i>'+student+'</p></p></div></li>';
				}else if(value.score>60){//评分大于60   1星
					html+= '<p class="check-grade mt10 mb10"><span class="vam mr10 fsize14">评分</span><i class="db-icon no-star star-icon"></i><i class="db-icon no-star"></i><i class="db-icon no-star"></i><i class="db-icon no-star"></i><i class="db-icon no-star"></i></p><p class="group-member pr" style="display: none;"><i>成员：</i>'+student+'</p></p></div></li>';
				}else{//无星
					html+= '<p class="check-grade mt10 mb10"><span class="vam mr10 fsize14">评分</span><i class="db-icon no-star"></i><i class="db-icon no-star"></i><i class="db-icon no-star "></i><i class="db-icon no-star "></i><i class="db-icon no-star"></i></p><p class="group-member pr" style="display: none;"><i>成员：</i>'+student+'</p></p></div></li>';
				}
				
				student="";
			});
		}else{//如果没有数据，进行友好提示
			html += '<tr><td colspan="8">对不起，暂时没有您所需要的数据！</td></tr>';
		}
		$('#list-X').html(html);//将拼接的结果放到指定的tbody中		alert(html)
		//初始化数据
		if(page.totalCount > 0){
			$('#list-X li:first').children("div").addClass("on");
			$('.et-right-item').html('<iframe src="'+$('#list-X li:first').children("div").attr("id")+'" style="width:100%;height:745px;"></iframe>');
		}
		function noCheck(elem){
			var a = $("."+elem);
			a.click(function(){
				a.removeClass("on");
				$(this).toggleClass("on");
				$(this).parent().siblings().find(".group-member").slideUp();
				$(this).find(".group-member").is(":hidden")?$(this).find(".group-member").slideDown():$(this).find(".group-member").slideUp();
				$('.et-right-item').html('<iframe src="'+$(this).attr("id")+'" style="width:100%;height:745px;"></iframe>');
			})
		}
		noCheck("check-group-item");
		noCheck("noCheck-group-item");
		statusrefreshPaginator(page.totalCount, pageSize, pageNo);//分页
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
};

/**
 * 分页
 * @param totalCount 总条数
 * @param pageNo  从第几条开始取数据
 * @param pageSize 每页显示多少条数据
 */
function statusrefreshPaginator(totalCount, pageSize, pageNo){
	$('#statuspaginator-tool').smartpaginator({
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
			liststatus(newPage, pageSize);
		}
	});
};

/**
 * 实验批改保存
 */
function save(){
	if(totalCount > 0){
	var score=$('#score').val();//实验成绩
	var reg=/^(\d{1}|[1-9]\d{1}|100)$/;
	if(score==""){
		showError("请输入成绩！");
		return;
	}else if(!reg.test(score)){
		showError("请输入正确的分数0-100！");
		return;
	}
	var url = window.ctx + '/check/groupGrade';//查询的URL地址
	var data = [//查询的参数
	            {name:'groupId',value:$('.on').attr('name')},
	            {name:'score',value:$('#score').val()},
	            {name:'comment',value:$('#comment').val()},
	];
	$.post(url, data, function(feedbak){//获取返回结果
		if(feedbak.successful){
			$("#score").val("");
			$("#comment").val("");
			$('.et-right-item').html('');
			if($("#correct").is(":hidden")){
		       list(wspageNo, wspageSize);//查询列表没有批改
			}else{
			   liststatus(wspageNo, wspageSize);//查询列表已经批改 
			}
			remind("success-remind-box");
		}else{
			showError(feedbak.message);	
		}
	});
	}else{
		showError("当前没有可批改的实验报告！");
	}
}
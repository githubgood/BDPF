/**
 * @description 机器学习JS脚本
 * @auth jm
 */
$(function(){
    var libraryAssemblyMap = $("#libraryAssemblyMap").val();
	
	$(".pai-dt-list-box").height($(document.body).height()-80);
	
	//选择字段
//			popBox("pai-select-field","createPortGroup-box","880");
	//选择目标列
//			popBox("dms-target-list","targetListBox","880");
	//重命名
//			popBox("btn-rename","rename-box","400");
	//查看数据
//	popBox("btn-view-data","view-data-box","1100");
	// 查看分析报告
	popBox("btn-view-chart", "view-data-chart", "1100");
	var expid = $("input[name='expid']").val();
	
	list(expid);//查询列表
	
});

/**
 * 查询列表，将查询的结果放到指定的div中
 */
function list(expid){
	var url = window.ctx + '/assembly/list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var content = [];
		var mainarr = [];
		var state = 0;
		if(page.experiment!=null){
			content = JSON.parse(page.experiment.content);
			mainarr = JSON.parse(page.experiment.mainarr);
			state = 1;
		}
		saveFlowsheet(state,content,mainarr);
	});
};

/**
 * 实时保存
 */
function saveAssembly(sourceId,targetId,sid,tid,connects,mainArr){
	var url = window.ctx + '/assembly/save';//查询的URL地址
	var connectsString = JSON.stringify(connects);//转换为json类型的字符串
	var mainArrString = JSON.stringify(mainArr);//转换为json类型的字符串
	mainArrString = mainArrString.replace(/\\n/g,"");//过滤回车换行
	mainArrString = mainArrString.replace(/\\t/g,"");//过滤横向跳到下一制表符位置
	var data = [//查询的参数
	            {name:'bdpfMllibExperimentId',value:$("input[name='expid']").val()},
	            {name:'id',value:sid},
	            {name:'bdpfMllibAssemblyLibraryId',value:sourceId.attr("id")},
	            {name:'name',value:sourceId.html()},
            	{name:'tid',value:tid==""?"":tid},
            	{name:'targetId',value:targetId==""?"":targetId.attr("id")},
            	{name:'targetName',value:targetId==""?"":targetId.html()},
	            {name:'connects',value:connectsString},
	            {name:'mainarr',value:mainArrString},
	];
	var html = '';
	$.ajax({
        type: 'post',
        url: url,
        data:data,
        async: false,//把异步改成同步
        success: function(page){
        	if(page == "1"){//判断是否有数据
        		list($("input[name='expid']").val());
			}else if(page == "-1"){
    			alert("错误的连接，请删除该连接");
    		}else {
                alert("保存失败！请重新登录！");
			}
        }
    });
};

/**
 * add ylh 2017-12-14
 * 删除连线
 */
function deleteLabel(sourceId,targetId,sid,tid,connects,mainArr){
	var url = window.ctx + '/assembly/deleteLabel';
    var connectsString = JSON.stringify(connects);//转换为json类型的字符串
    var mainArrString = JSON.stringify(mainArr);//转换为json类型的字符串
    var data = [//查询的参数
        {name:'bdpfMllibExperimentId',value:$("input[name='expid']").val()},
        {name:'id',value:sid},
        {name:'bdpfMllibAssemblyLibraryId',value:sourceId.attr("id")},
        {name:'name',value:sourceId.html()},
        {name:'tid',value:tid==""?"":tid},
        {name:'targetId',value:targetId==""?"":targetId.attr("id")},
        {name:'targetName',value:targetId==""?"":targetId.html()},
        {name:'connects',value:connectsString},
        {name:'mainarr',value:mainArrString}
    ];
	$.ajax({
		type:"POST",
		url:url,
		data:data,
		success:function(result){
			if (result == "success"){
                list($("input[name='expid']").val());
			}else if (result == 1){
				alert("线条两端组件信息有误！");
			}else {
				alert("登录信息有误！");
			}
		}
	});

}


/**
 * 删除单个组件
 */
function delAssembly(id,sid,connects,mainArr){
	var url = window.ctx + '/assembly/del';//查询的URL地址
	var connectsString = JSON.stringify(connects);//转换为json类型的字符串
	var mainArrString = JSON.stringify(mainArr);//转换为json类型的字符串
	mainArrString = mainArrString.replace(/\\n/g,"");//过滤回车换行
	mainArrString = mainArrString.replace(/\\t/g,"");//过滤横向跳到下一制表符位置
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'expid',value:$("input[name='expid']").val()},
	            {name:'sid',value:sid},
	            {name:'connects',value:connectsString},
	            {name:'mainarr',value:mainArrString},
	];
	var html = '';
	$.ajax({
        type: 'post',
        url: url,
        data:data,
        async: false,//把异步改成同步
        success: function(page){
        	if(page != null){//判断是否有数据
        		list($("input[name='expid']").val());
            }
        }
    });
};

/**
 * 删除整个实验
 */
function dels(){
	var url = window.ctx + '/assembly/dels';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:$("input[name='expid']").val()},
	];
	var html = '';
	$.ajax({
        type: 'post',
        url: url,
        data:data,
        async: false,//把异步改成同步
        success: function(page){
        	if(page != null){//判断是否有数据
    			list($("input[name='expid']").val());
    			alert("删除成功！");
    		}
        }
    });
};

/* 弹窗 */
function popBox(a, b, c) {
	$("." + a)
			.unbind("click")
			.on(
					"click",
					function() {
						$('.' + b).modal('show');
						$("." + b).css({
							"display" : "table",
							"height" : "100%",
							"width" : c + "px",
							"left" : "50%",
							"margin-left" : "-" + c / 2 + "px"
						})
						$("." + b + " .modal-w").width(c + "px");
						/* step初始化 */
						// 创建主机
						if (a == "createHost" || a == "nfs-share"
								|| a == "cifs-share" || a == "ftp-share") {
							init();
						}
					})
}

/*设置图表html并显示图*/
function setEchartsHTML(id,sid){
		var data = [// 查询的参数
		{
			name : 'id',
			value : id
		}, {
			name : 'sid',
			value : sid
		}, ];

		// 查询的URL地址
		var urlColumn = window.ctx
				+ '/statisticalanalysis/queryColumn';
		$.ajax({
					url : urlColumn,
					data : data,
					type : "post",
					async : true, // 异步请求
					dataType : "json",
					success : function(result) {
						if (result != null) {
							popDataBox("btn-view-chart","view-data-chart","1100");
							$("#tbodyID").html("");
							for (var i = 0; i < result.length; i++) {
								var column_id = result[i].characteristicColumn +"-"+id;
								$("#tbodyID").append(
									"<tr>"
									+ "<td style='width: 50px; cursor: pointer' id='statusID' align='center' title='"
									+ column_id
									+ "' onclick='tdButton"
									+ result[i].assemblyLibraryId
									+ "(this)'>"
									+ "<br>"
									+ "<br>"
									+ (i + 1)
									+ "</td>"
									+ "<td style='width: 110px;'>"
									+ "<div id='buttonEcharts"
									+ (i + 1)
									+ "'  class='modal-body h450' style='width: 100px; height: 99px; line-height:99px;cursor: pointer;' title='"
									+ id
									+ "' onclick='columnButton"
									+ result[i].assemblyLibraryId
									+ "(this)'>"
									+ result[i].characteristicColumn
									+ "</div>"
									+ "</td>"
									+ "<td>"
									+ "<div id='dy"
									+ i
									+ "' class='modal-body h450' style='width: 320px; height: 160px; cursor: pointer; background: #fff; box-shadow: none;' title='"
									+ column_id
									+ "' onclick='divButton"
									+ result[i].assemblyLibraryId
									+ "(this)'>"
									+ "</div>"
									+ "</td>"
									+ "</tr>");
							}
						}
					}
				});
		if (sid>=32&&sid<=48) {
			eval("setAllAjaxEcharts"+sid+"(\""+id+"\")");
		}
//		else if (id == 33) {
//			setAllAjaxEcharts33(id);
//		} else if (id == 34) {
//			setAllAjaxEcharts34(id);
//		} else if (id == 35) {
//			setAllAjaxEcharts35(id);
//		} else if (id == 36) {
//			setAllAjaxEcharts36(id);
//		} else if (id == 37) {
//			setAllAjaxEcharts37(id);
//		} else if (id == 38) {
//			setAllAjaxEcharts38(id);
//		} else if (id == 39) {
//			setAllAjaxEcharts39(id);
//		} else if (id == 40) {
//			setAllAjaxEcharts40(id);
//		} else if (id == 41) {
//			setAllAjaxEcharts41(id);
//		} else if (id == 42) {
//			setAllAjaxEcharts42(id);
//		} else if (id == 43) {
//			setAllAjaxEcharts43(id);
//		} else if (id == 44) {
//			setAllAjaxEcharts44(id);
//		} else if (id == 45) {
//			setAllAjaxEcharts45(id);
//		}else if (id == 46) {
//			setAllAjaxEcharts46(id);
//		} else if (id == 47) {
//			setAllAjaxEcharts47(id);
//		} else if (id == 48) {
//			setAllAjaxEcharts48(id);
//		}
}


/*弹窗*/
function popDataBox(a, b, c) {
	$('.' + b).modal('show');
	$("." + b).css({
		"display": "table",
		"height": "100%",
		"width": c + "px",
		"left": "50%",
		"margin-left": "-" + c / 2 + "px"
	})
	$("." + b + " .modal-w").width(c + "px");
}

function run(){
    var initPath  = window.ctx + '/assembly/runFlow';
    var reports = assemblylist();
    $.each(reports,function(index,report){
    	var data = [//查询的参数
    	            {name:'id',value:report.id},
    	            {name:'assemblyId',value:report.bdpfMllibAssemblyLibraryId},
    	            {name:'expId',value:report.bdpfMllibExperimentId},
    	            {name:'parentId',value:report.parentId},
    	            {name:'name',value:report.name},
    	];
    	var result = rendering(initPath,data,index);
    	if (result == false){
            stop();
            return false;
		}

    	if(result != 'undefined'){
    		if(reports.length==(index+1)){
    			stop();
    			alert("运行成功！");
    		}
//    		$("#"+result).attr("class","_jsPlumb_connector active");
    	}else{
    		return false;
    	}
    })
}
 
function rendering(url,data,index){
	var result;
	$.ajax({
        type: 'post',
        url: url,
        data:data,
        async: false,//把异步改成同步
        success: function(page){
            // if(page != null){//判断是否有数据
        		// result = page.sourceId;
    		// }
            if (page.successful == true && page.data != null)
                result = page.data.sourceId;
            if (page.successful == false){
            	alert(page.message);
            	result = false;
            	return result;
			}
        }
    });
	return result;
}
 
function stop(){
	var url = window.ctx + '/assembly/stop';//查询的URL地址
	$.ajax({
        type: 'post',
        url: url,
        data:"",
        async: false,//把异步改成同步
        success: function(page){
        	if(page != null){//判断是否有数据
        		
    		}
        }
    });
}

function assemblylist(){
	var result = new Array();
	var url  = window.ctx + '/assembly/assemblylist';
	$.ajax({
        type: 'post',
        url: url,
        data:[{name:'expid',value:$("input[name='expid']").val()}],
        async: false,//把异步改成同步
        success: function(page){
        	$.each(page.assemblies, function(i,value){//进行数据拼接
        		result[i] = value;
        	});
        }
    });
	return result;
}


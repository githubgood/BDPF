/**
 * @description 数据源/目标JS脚本
 * @auth jm
 */

/**
 * 读数据库列表
 */
function model_3(expid,id,state){
	var url = window.ctx + '/sourcetarget/model_3_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	];
	var html = "";
	$.get(url, data, function(page){//获取返回结果
		html+= '<div class="model_20_box">'+
		 '<header class="dms-prop-header has-tab">'+
			'<ul class="dms-prop-tab">'+
				'<li class="selected">表选择</li>'+
				'<li>字段信息</li>'+
			'</ul>'+
		 '</header>'+
		 '<section class="dms-prop-body dms-chart-box">'+
			 	'<div class="dms-prop-page">'+
			 		'<form id="table-form" id="">'+
			 			'<div id="source-error" class="form-group ">'+
			 			 	'<label>表名'+  
			 					'<span class="dms-prop-tooltip">跨项目读表: 项目名.表名</span>'+
			 			 	'</label>';
							if(page.assembly!=null&&page.assembly.name!=null&&page.assembly.name!=''){
								html+='<input type="text" name = "name" id="source-tableName" onkeyup="tableInfoLikeQuery(this.value);" onblur="findTableInfo(this.value);" class="form-control" value="'+page.assembly.name+'">';
							}else{
								html+='<input type="text" name = "name" id="source-tableName" class="form-control" value="">';
							}
			 				html+='<input type="hidden" name="id" value="'+id+'"/>'+
			 				'<input type="hidden" name="path" value="'+expid+'"/>'+
			 				'<i class="icon form-close"></i>'+
			 			'</div>'+						
			 		'</form>'+
			 	'</div>'+
			 	'<div class="dms-prop-page" style="display: none;">'+
			 		'<div class="dms-prop-group">'+
			 			'<div class="dms-prop-item  dms-prop-item-table">'+
			 				'<div class="dms-prop-label">'+
			 					'<label><span class="dms-prop-label-text">源表字段信息</span></label>'+
			 				'</div>'+
			 					'<div class="dms-prop-input"><span class="btn-refresh icon icon-refresh"></span>'+
			 						'<div class="grid-wrap">'+
			 							'<table class="table table-bordered table-hover">'+
			 								'<thead>'+
			 									'<tr>'+
			 										'<th>字段</th>'+
			 										'<th>类型</th>'+
			 										'<th>前 100 条记录范围</th>'+
			 									'</tr>'+
			 								'</thead>'+
			 								'<tbody id="source-table-tbody">'+
				 								/*'<tr>'+
				 									'<td>age</td>'+
				 									'<td>STRING</td>'+
				 									'<td>37.0,41.0,63.0,67.0</td>'+
				 								'</tr>'+*/
			 								'</tbody>'+
				 						'</table>'+
				 					'</div>'+
				 				'</div>'+
				 		'</div>'+
			 		'</div>'+
			 	'</div>'+
			'</section>'+
			'</div>';
		$('.pai-side-right').html(html);
		if(page.assembly!=null&&page.assembly.name!=null&&page.assembly.name!=''){
			findTableInfo(page.assembly.name);
		}
		//表选择
		tab("dms-prop-tab","dms-chart-box");
	})
};

//读取数据库查看数据
function model_3_show(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th title='序号'>序号</th>";//标题
		var body = "<tbody>";//内容
		var heads = new Array();
		$.each(page.tableChilds, function(i,value){//进行数据拼接
			head+= '<th>'+value.name+'</th>';
			heads[i] = value.name;
		});
		head+="</tr></thead>";
		//显示表头
		$(".view-data-box .ui-datatable-head .table").html(head);
		
		$.each(page.resultList, function(i,resultvalue){//进行数据拼接
			if(i<99){//只需要显示前99行
				body+= '<tr><td>'+(i+1)+'</td>';
				$.each(resultvalue,function(key,value){
					$.each(heads, function(item){
						if(key == heads[item]){
							body+= '<td>'+value+'</td>';
						}
					});
				});
				body+= '</tr>';
			}
		});
		body+="</tbody>";
		//显示数据内容
		$(".view-data-box .ui-datatable-body .table").html(body);
	    //查看数据
		popDataBox("btn-view-data","view-data-box","1100");
		$("#data_submit").on("click",function(){
			$('.view-data-box').modal('hide');
		})
	});
}

/**
 * 表字段信息拼接
 */
function appendColumnInfo(obj){
	$('#source-table-tbody').children().remove();
	var html = '';
	$.each(obj,function(n,tc){
		html += '<tr>'+
					'<td>'+tc.name+'</td>'+
					'<td>'+tc.type+'</td>'+
					'<td class="td-overflow" style="border:none;border-bottom:1px solid #ddd">'+tc.columnValue+'</td>'+
				'</tr>';
	});
	$('#source-table-tbody').append(html);
}

var tables =[];
initTables();
/**
 * 加载该用户所有表名信息
 */
function initTables(){
	var url = window.ctx + '/sourcetarget/initTables';
	$.get(url,function(re){
		$.each(re,function(n,obj){
			tables.push(obj.name);
		});
	});
}

/**
 * 表名输入联想
 * @param obj
 */
function tableInfoLikeQuery(obj){
           $('.pai-side-right #source-tableName').autocomplete({
             source: tables
           });
}

function findTableInfo(obj){
	$('#source-error').removeClass('has-error');
	if(obj == null || obj == "" || obj ==undefined){
		$('#source-error').addClass('has-error');
		return ;
	}
	
	var url = window.ctx + '/sourcetarget/tableData';
	$.post(url,$('#table-form').serializeArray(),function(re){
		if(re.data.tcvList!=''&&re.data.tcvList!=null){
			//console.log("message->"+re.message+" data->"+JSON.stringify(re.data));
			$("#"+re.data.assembly.id).find("span.element-text").html(re.data.assembly.name);
			appendColumnInfo(re.data.tcvList);
		}else{
			alert(re.message);
			$('#source-table-tbody').children().remove();
		}
	});
}

/**
 * SQL脚本列表
 */
function model_91(expid,id,state){
	var url = window.ctx + '/sourcetarget/model_91_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	];
	var html = "";
	$.get(url, data, function(page){//获取返回结果
		html+= '<div class="dms-prop-wrap model_2_box" id="">'+
			'<header class="dms-prop-header has-tab">'+
				'<ul class="dms-prop-tab">'+
					'<li class="selected">参数设置</li>'+
				'</ul>'+
			'</header>'+
			'<section class="dms-prop-body">'+
				'<div class="dms-prop-page">'+
					'<div class="dms-prop-item  dms-prop-item-r-dataset">'+
						'<div class="dms-prop-label">'+
							'<label>输入源</label>'+
						'</div>'+
						'<div class="dms-prop-input">'+
						'<ul>';
							if(page.assemblies!=null&&page.assemblies.length>0){
								$.each(page.assemblies,function(i,value){
									html+='<li>'+
									'<span>t'+(i+1)+'</span>'+
									'<em title="fromName">fromName"'+value.name+'"</em>'+
									'</li>';
								});
							}else{
								html+='<li>'+
								'<span>t1</span>'+
								'<em title="fromName">fromName"数据源"</em>'+
								'</li>';
							}
							html+='</ul>';
						'</div>'+
					'</div>'+
					'<div class="dms-prop-item">'+
						'<div class="dms-prop-label has-long-tooltip">'+
							'<label>'+
								'SQL脚本'+
								'<i>最后一句必须为Select语句</i>'+
							'</label>'+
						'</div>'+
						'<div class="dms-prop-text">';
						if(page.sqlAssembly!=null){
							html+='<textarea cols="30" value="" rows="10" onblur="model_91_save(\''+id+'\',\'sqlScript\',this.value)">'+page.sqlAssembly.sqlScript+'</textarea>';
						}else{
							html+='<textarea cols="30" value="" rows="10" onblur="model_91_save(\''+id+'\',\'sqlScript\',this.value)"></textarea>';
						}
						html+='</div>'+
					'</div>'+
				'</div>'+
			'</section>'+
		'</div>';
		
		$('.pai-side-right').html(html);
	})
};

/**
 * SQL脚本保存
 */
function model_91_save(id,columnName,columnValue){
	var url = window.ctx + '/sourcetarget/model_91_save';//查询的URL地址
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:columnName,value:columnValue},
	];
	var expid = $("input[name='expid']").val();
	var html = '';
	$.ajax({
        type: 'post',
        url: url,
        data:data,
        async: false,//把异步改成同步
        success: function(page){
        	$('.model_2_box').modal('hide');
            model_91(expid,page.id,"1");
        }
    });
};

/**
 * SQL脚本查看数据
 * @param id
 * @param sid
 */
function model_91_show(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th title='序号'>序号</th>";//标题
		var body = "<tbody>";//内容
		var heads = new Array();
		$.each(page.tableChilds, function(i,value){//进行数据拼接
			head+= '<th>'+value.name+'</th>';
			heads[i] = value.name;
		});
		head+="</tr></thead>";
		//显示表头
		$(".view-data-box .ui-datatable-head .table").html(head);
		
		$.each(page.resultList, function(i,resultvalue){//进行数据拼接
			if(i<99){//只需要显示前99行
				body+= '<tr><td>'+(i+1)+'</td>';
				$.each(resultvalue,function(key,value){
					$.each(heads, function(item){
						if(key == heads[item]){
							body+= '<td>'+value+'</td>';
						}
					});
				});
				body+= '</tr>';
			}
		});
		body+="</tbody>";
		//显示数据内容
		$(".view-data-box .ui-datatable-body .table").html(body);
	    //查看数据
		popDataBox("btn-view-data","view-data-box","1100");
		$("#data_submit").on("click",function(){
			$('.view-data-box').modal('hide');
		})
	});
}
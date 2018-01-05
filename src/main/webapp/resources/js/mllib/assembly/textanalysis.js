/**
 * @description 文本分析JS脚本
 * @auth jm
 */

//文档主题生成模型查看数据
function model_81_show(id,sid){
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
		var i=0;
		$.each(page.resultList, function(i,resultvalue){//进行数据拼接
			$.each(resultvalue,function(key,value){
				if(!contains(heads,key)){
					heads[i] = key;
					head+= '<th>'+key+'</th>';
				}
				i++;
			});
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
 * 停用词过滤列表
 */
function model_84(expid,id,state){
	if(state == '0'){
		$('#model_84_save').off('click').on('click',model_84_save);
	}
	var url = window.ctx + '/textanalysis/model_84_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form>';
		html+= '<div class="form-group"><label>选择过滤列 <span class="dms-prop-tooltip">可选</span></label>';
		if(page.textAnalysisAssemblies!=null&&page.textAnalysisAssemblies.length!=0){
			html+= '<a class="btn pai-select-field" id="2">已选择<span class="number">'+page.textAnalysisAssemblies.length+'</span>个字段</a>';
		}else{
			html+= '<a class="btn pai-select-field" id="2">选择<span class="number"></span>字段</a>';
		}
		html+= '</div></form></div></section></div>';
	    $('.pai-side-right').html(html);
	    //选择字段
		popBox("pai-select-field","createPortGroup-box","880");
		$(".pai-select-field").on("click",function(){
			
			var thisid=$(this).attr("id");
			var left = "";//左边
			var right = "";//右边
			$.each(page.tableChilds, function(i,value){//进行字段类型拼接
				left+= '<li class="zero-accordion-item">';
				left+= '<div class="ui-checkbox">';
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			$.each(page.textAnalysisAssemblies, function(i,value){//进行数据拼接
				right+= '<tr class="'+value.filterColumn+'_'+value.filterType+'"><td>';
				right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
				right+= '<td class="name">'+value.filterColumn+'</td><td class="dataType">'+value.filterType+'</td>';
				right+= '</tr>';
				$(".left_wrap").find("."+value.filterColumn+'_'+value.filterType).prop("checked",true);
			});
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * 停用词过滤保存
 */
function model_84_save(){
	var url = window.ctx + '/textanalysis/model_84_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+$(this).find(".dataType").text());
	})
	var data = [//查询的参数
	            {name:'id',value:$('.table-body').attr("id")},
	            {name:'conn',value:conn},
	            {name:'convertType',value:$('.check-list').attr("id")},
	];
	var expid = $("input[name='expid']").val();
	var html = '';
	$.ajax({
        type: 'post',
        url: url,
        data:data,
        async: false,//把异步改成同步
        success: function(page){
        	$('.createPortGroup-box').modal('hide');
            model_84(expid,page.id,"1");
        }
    });
};

//停用词过滤查看数据
function model_84_show(id,sid){
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

//词频统计查看数据
function model_86_show(id,sid){
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
		$.each(page.textAnalysisAssemblies, function(i,value){//进行数据拼接
			head+= '<th>'+value.filterColumn+'</th>';
		});
		head+="</tr></thead>";
		//显示表头
		$(".view-data-box .ui-datatable-head .table").html(head);
		var j=0;
		$.each(page.resultList, function(i,resultvalue){//进行数据拼接
				$.each(resultvalue,function(key,value){
					j++;
					if(j<100){//显示前99条
						body+= '<tr><td>'+j+'</td>';
						body+= '<td>'+key+':'+value+'</td>';
						body+= '</tr>';
					}
				});
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
 * Split Word列表
 */
function model_87(expid,id,state){
	if(state == '0'){
		$('#model_87_save').off('click').on('click',model_87_save);
	}
	var url = window.ctx + '/textanalysis/model_87_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form>';
		html+= '<div class="form-group"><label>选择过滤列 <span class="dms-prop-tooltip">可选</span></label>';
		if(page.textAnalysisAssemblies!=null&&page.textAnalysisAssemblies.length!=0){
			html+= '<a class="btn pai-select-field" id="2">已选择<span class="number">'+page.textAnalysisAssemblies.length+'</span>个字段</a>';
		}else{
			html+= '<a class="btn pai-select-field" id="2">选择<span class="number"></span>字段</a>';
		}
		html+= '</div></form></div></section></div>';
	    $('.pai-side-right').html(html);
	    //选择字段
		popBox("pai-select-field","createPortGroup-box","880");
		$(".pai-select-field").on("click",function(){
			
			var thisid=$(this).attr("id");
			var left = "";//左边
			var right = "";//右边
			$.each(page.tableChilds, function(i,value){//进行字段类型拼接
				left+= '<li class="zero-accordion-item">';
				left+= '<div class="ui-checkbox">';
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			$.each(page.textAnalysisAssemblies, function(i,value){//进行数据拼接
				right+= '<tr class="'+value.filterColumn+'_'+value.filterType+'"><td>';
				right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
				right+= '<td class="name">'+value.filterColumn+'</td><td class="dataType">'+value.filterType+'</td>';
				right+= '</tr>';
				$(".left_wrap").find("."+value.filterColumn+'_'+value.filterType).prop("checked",true);
			});
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * Split Word保存
 */
function model_87_save(){
	var url = window.ctx + '/textanalysis/model_87_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+$(this).find(".dataType").text());
	})
	var data = [//查询的参数
	            {name:'id',value:$('.table-body').attr("id")},
	            {name:'conn',value:conn},
	            {name:'convertType',value:$('.check-list').attr("id")},
	];
	var expid = $("input[name='expid']").val();
	var html = '';
	$.ajax({
        type: 'post',
        url: url,
        data:data,
        async: false,//把异步改成同步
        success: function(page){
        	$('.createPortGroup-box').modal('hide');
            model_87(expid,page.id,"1");
        }
    });
};

//Split Word查看数据
function model_87_show(id,sid){
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

function contains(arr, obj) {
    var i = arr.length;  
    while (i--) {  
        if (arr[i] === obj) {  
            return true;  
        }  
    }  
    return false;  
}
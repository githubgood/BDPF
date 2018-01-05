/**
 * @description 数据预处理JS脚本
 * @auth jm
 */
/**
 * 过滤与映射列表
 */
function model_6(expid,id,state){
	if(state == '0'){
		$('#model_6_save').off('click').on('click',model_6_save);
	}
	var url = window.ctx + '/datapretreatment/model_6_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group"><label>映射规则<span class="dms-prop-tooltip">默认为全选列</span></label>';
		if(page.filterMappingChildAssemblies!=null&&page.filterMappingChildAssemblies.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.filterMappingChildAssemblies.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		html+='</div><div class="form-group"><label>过滤条件</label>';
		if(page.filterMappingAssembly!=null&&page.filterMappingAssembly.filtrationCondition!=null){
			html+='<textarea cols="30" value="" rows="10" onblur="model_6_save(\'filtrationCondition\',this.value)">'+page.filterMappingAssembly.filtrationCondition+'</textarea>';
		}else{
			html+='<textarea cols="30" value="" rows="10" onblur="model_6_save(\'filtrationCondition\',this.value)"></textarea>';
		}
		html+='</div></form></div></section></div>';
	    $('.pai-side-right').html(html);
	    //选择字段
		popBox("pai-select-field","createPortGroup-box","880");
		$(".pai-select-field").on("click",function(){
			var left = "";//左边
			var right = "";//右边
			$.each(page.tableChilds, function(i,value){//进行字段类型拼接
				left+= '<li class="zero-accordion-item">';
				left+= '<div class="ui-checkbox">';
				left+= '<input class="checkBox '+value.name+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.filterMappingChildAssemblies!=null&&page.filterMappingChildAssemblies.length!=0){
				$.each(page.filterMappingChildAssemblies, function(i,value){//进行数据拼接
					right+= '<tr class="'+value.name+'"><td>';
					right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
					right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
					right+='</tr>';
					$(".left_wrap").find("."+value.name).prop("checked",true);
				});
				$('.check-list').html(right);
			}
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * 过滤与映射保存
 */
function model_6_save(columnName,columnValue){
	var url = window.ctx + '/datapretreatment/model_6_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+$(this).find(".dataType").text());
	})
	var data = [//查询的参数
	            {name:'id',value:$('.table-body').attr("id")},
	            {name:'conn',value:conn},
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
        	$('.createPortGroup-box').modal('hide');
            model_6(expid,page.id,"1");
        }
    });
};

//过滤与映射查看数据
function model_6_show(id,sid){
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
 * 类型转换列表
 */
function model_11(expid,id,state){
	if(state == '0'){
		$('#model_11_save').off('click').on('click',model_11_save);
	}
	var url = window.ctx + '/datapretreatment/model_11_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group"><label>转换为double类型的列 <span class="dms-prop-tooltip">可选</span></label>';
		if(page.convertAssemblies!=null&&page.convertAssemblies.length!=0&&page.doubleList!=null&&page.doubleList.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.doubleList.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		html+='</div><div class="form-group"><label>转换为double异常时默认填充值 <span class="dms-prop-tooltip">可选</span></label>';
		html+= '<input type="number" step="1" value="0" placeholder="" class="form-control" readonly></div><div class="form-group"><label>转换为int类型的列 <span class="dms-prop-tooltip">可选</span></label>';
		if(page.convertAssemblies!=null&&page.convertAssemblies.length!=0&&page.intList!=null&&page.intList.length!=0){
			html+= '<a class="btn pai-select-field" id="1">已选择<span class="number">'+page.intList.length+'</span>个字段</a>';
		}else{
			html+= '<a class="btn pai-select-field" id="1">选择<span class="number"></span>字段</a>';
		}
		html+= '</div><div class="form-group"><label>转换为int异常时默认填充值 <span class="dms-prop-tooltip">可选</span></label><input type="number" step="1" value="0" readonly placeholder="" class="form-control" ></div>';
		html+= '<div class="form-group"><label>转换为string类型的列 <span class="dms-prop-tooltip">可选</span></label>';
		if(page.convertAssemblies!=null&&page.convertAssemblies.length!=0&&page.stringList!=null&&page.stringList.length!=0){
			html+= '<a class="btn pai-select-field" id="2">已选择<span class="number">'+page.stringList.length+'</span>个字段</a>';
		}else{
			html+= '<a class="btn pai-select-field" id="2">选择<span class="number"></span>字段</a>';
		}
		html+= '</div><div class="form-group"><label>转换为String异常是默认填充值 <span class="dms-prop-tooltip">可选</span></label><input type="text" placeholder="" class="form-control" readonly><i class="icon form-close"></i></div>';
		html+= '</form></div></section></div>';
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
				left+= '<input class="checkBox '+value.name+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.convertAssemblies!=null&&page.convertAssemblies.length!=0){
				if(thisid == 0){
					$.each(page.doubleList, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.name+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.name).prop("checked",true);
					});
				}else if(thisid == 1){
					$.each(page.intList, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.name+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
						right+='</tr>';
						$(".left_wrap").find("."+value.name).prop("checked",true);
					});
				}else{
					$.each(page.stringList, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.name+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
						right+='</tr>';
						$(".left_wrap").find("."+value.name).prop("checked",true);
					});
				}
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * 类型转换保存
 */
function model_11_save(){
	var url = window.ctx + '/datapretreatment/model_11_save';//查询的URL地址
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
            model_11(expid,page.id,"1");
        }
    });
};

//类型转换查看数据
function model_11_show(id,sid){
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
 * 拆分列表
 */
function model_13(expid,id,state){
	var url = window.ctx + '/datapretreatment/model_13_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'expid',value:expid},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
			html+= '<div class="dms-prop-wrap model_5_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab dms-argumentTune-tab"><li class="selected">参数设置</li><li>执行调优</li></ul></header>';
			html+= '<section class="dms-prop-body dms-argumentTune-box"><div class="dms-prop-page dms-prop-input"><form><div class="form-group"><label>拆分方式</label><select class="form-control">';
			html+= '<option>按比例拆分</option>';
			html+= '</select></div><div class="form-group"><label>切分比例<span class="dms-prop-tooltip">表1占原数据的比例,范围 (0, 1)</span></label>';
			if(page.splitAssembly!=null&&page.splitAssembly.segmentationRatio!=null){
				html+='<input type="number" step="1" min="0" max="1" placeholder="" class="form-control" onblur="model_13_save(\'segmentationRatio\',this.value)" value='+page.splitAssembly.segmentationRatio+'>';
			}else{
				html+='<input type="number" step="1" min="0" max="1" placeholder="" class="form-control" onblur="model_13_save(\'segmentationRatio\',this.value)">';
			}
			html+= '</div><div class="form-group"><label>随机数种子<span class="dms-prop-tooltip">正整数</span></label>';
			if(page.splitAssembly!=null&&page.splitAssembly.random!=null){
				html+= '<input type="number" step="1" placeholder="" class="form-control" onblur="model_13_save(\'random\',this.value)" value='+page.splitAssembly.random+'>';
			}else{
				html+= '<input type="number" step="1" placeholder="" class="form-control" onblur="model_13_save(\'random\',this.value)">';
			}
			html+= '</div></form></div><div class="dms-prop-page" style="display: none;"><div class="dms-prop-page dms-prop-input"><form><div class="form-group"><label>计算核心数</label>';
			if(page.splitAssembly!=null&&page.splitAssembly.coreNumber!=null){
				html+='<input type="email" class="form-control" onblur="model_13_save(\'coreNumber\',this.value)" value='+page.splitAssembly.coreNumber+'>';
			}else{
				html+='<input type="email" class="form-control" onblur="model_13_save(\'coreNumber\',this.value)">';
			}
			html+= '</div><div class="form-group"><label>每个核心内存</label>';
			if(page.splitAssembly!=null&&page.splitAssembly.memory!=null){
				html+='<input type="email" class="form-control" onblur="model_13_save(\'memory\',this.value)" value='+page.splitAssembly.memory+'>';
			}else{
				html+='<input type="email" class="form-control" onblur="model_13_save(\'memory\',this.value)" >';
			}
			html+='</div></form></div></div></section></div>';
	    $('.pai-side-right').html(html);
	    tab("dms-argumentTune-tab","dms-argumentTune-box");
	});
};

/**
 * 拆分保存
 */
function model_13_save(columnName,columnValue){
	var url = window.ctx + '/datapretreatment/model_13_save';//查询的URL地址
	if(columnName == "segmentationRatio"){
		if(columnValue > 1){
			alert("切分比例不能大于1");
		}else{
			var data = [//查询的参数
			            {name:'id',value:$('.table-body').attr("id")},
			            {name:'columnName',value:columnName},
			            {name:'columnValue',value:columnValue},
			];
			var expid = $("input[name='expid']").val();
			var html = '';
			$.ajax({
		        type: 'post',
		        url: url,
		        data:data,
		        async: false,//把异步改成同步
		        success: function(page){
		            model_13(expid,page.id,"1");
		        }
		    });
		}
	}
};

//拆分查看数据
function model_13_show(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var left_head = "<thead><tr><th title='序号'>序号</th>";//标题
		var left_body = "<tbody>";//内容
		var right_body = "<tbody>";//内容
		$.each(page.tableChilds, function(i,value){//进行数据拼接
			left_head+= '<th>'+value.name+'</th>';
		});
		left_head+="</tr></thead>";
		//显示表头
		$(".view-more-data-box #show-data-left .ui-datatable-head .table").html(left_head);
		//显示表头
		$(".view-more-data-box #show-data-right .ui-datatable-head .table").html(left_head);
		$.each(page.trainingList, function(i,resultvalue){//进行数据拼接
			if(i<99){//只需要显示前99行
				left_body+= '<tr><td>'+(i+1)+'</td>';
				$.each(resultvalue,function(i,value){
					left_body+= '<td>'+value+'</td>';
				});
				left_body+= '</tr>';
			}
		});
		left_body+="</tbody>";
		//显示数据内容
		$(".view-more-data-box #show-data-left .ui-datatable-body .table").html(left_body);
		$.each(page.testList, function(i,resultvalue){//进行数据拼接
			if(i<99){//只需要显示前99行
				right_body+= '<tr><td>'+(i+1)+'</td>';
				$.each(resultvalue,function(i,value){
					right_body+= '<td>'+value+'</td>';
				});
				right_body+= '</tr>';
			}
		});
		right_body+="</tbody>";
		//显示数据内容
		$(".view-more-data-box #show-data-right .ui-datatable-body .table").html(right_body);
	    //查看数据
		popDataBox("btn-view-data","view-more-data-box","1100");
		$("#more_data_submit").on("click",function(){
			$('.view-more-data-box').modal('hide');
		})
	});
}

/**
 * 随机采样点击查询
 */
function model_5(expid,id,state){
    var url = window.ctx + '/datapretreatment/model_5_list';//查询的URL地址
	var data = [//查询的参数
        {name:'expid',value:expid},
        {name:'id',value:id},
    ];
    $.get(url, data, function(page){//获取返回结果
        var html = "";
        html+= '<div class="dms-prop-wrap model_5_box">'+
						'<header class="dms-prop-header has-tab">'+
							'<ul class="dms-prop-tab dms-argumentTune-tab">' +
								'<li class="selected">参数设置</li>' +
								'<li>执行调优</li>' +
							'</ul>' +
						'</header>'+
		           '<section class="dms-prop-body dms-argumentTune-box">' +
						'<div class="dms-prop-page dms-prop-input">' +
							'<form>' +
								'<input type="hidden" name="id" value='+page.randomSamplingAssembly.id +'>'+
								'<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
								'<div class="form-group">' +
									'<label>采样个数 <span class="dms-prop-tooltip">与采样比例二选一</span></label>'+
									'<input type="number" name="sampleSize" onchange="model_5_save();" step="1" value='+page.randomSamplingAssembly.sampleSize+' placeholder="" class="form-control">'+
								'</div>'+
								'<div class="form-group">' +
									'<label>采样比例<span class="dms-prop-tooltip">范围(0,1) 与采样个数二选一</span></label>'+
									'<input type="number" name="sampleRatio" onchange="model_5_save();" step="1" value='+page.randomSamplingAssembly.sampleRatio+' min="0" max="1" placeholder="" class="form-control" >'+
								'</div>' +
								'<div class="form-group">';
								if (page.randomSamplingAssembly.sampleReplace == 1) {
									html+='<input type="checkbox" checked  name="sampleReplace" onchange="model_5_save();">';
								}else {
                                    html+='<input type="checkbox" name="sampleReplace" onchange="model_5_save();">';
								}
		html+=							'<label>放回采样</label>'+
								'</div>' +

								'<div class="form-group">' +
									'<label>随机数种子<span class="dms-prop-tooltip">正整数</span></label>'+
									'<input type="number" name="randomSeed" onchange="model_5_save();" step="1" value='+page.randomSamplingAssembly.randomSeed+' placeholder="" class="form-control">'+
								'</div>' +
							'</form>' +
						'</div>' +
						'<div class="dms-prop-page" style="display: none;">' +
							'<div class="dms-prop-page dms-prop-input">' +
								'<form>' +
									'<div class="form-group">' +
										'<label>计算核心数</label>'+
										'<input type="number" name="coreNum" onchange="model_5_save();" value='+page.randomSamplingAssembly.coreNum+' class="form-control">'+
									 '</div>' +
									 '<div class="form-group">' +
										 '<label>每个核心内存</label>'+
										 '<input type="number" name="memSizePercore" onchange="model_5_save();" value='+page.randomSamplingAssembly.memSizePercore+' class="form-control">'+
									'</div>' +
							   '</form>' +
							'</div>' +
						'</div>' +
			      '</section>' +
			'</div>';
        $('.pai-side-right').html(html);
        tab("dms-argumentTune-tab","dms-argumentTune-box");
    });
};
/**
 * 随机采样编辑
 */
function model_5_save(){
    var url = window.ctx + '/datapretreatment/model_5_save';//查询的URL地址
    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
		{name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
		{name:'sampleSize',value:$("input[name='sampleSize']").val()},
		{name:'sampleRatio',value:$("input[name='sampleRatio']").val()},
        {name:'sampleReplace',value:$("input[name='sampleReplace']").prop("checked") == true ? 1 : 0},
		{name:'randomSeed',value:$("input[name='randomSeed']").val()},
		{name:'coreNum',value:$("input[name='coreNum']").val()},
		{name:'memSizePercore',value:$("input[name='memSizePercore']").val()},
    ];
    console.log(JSON.stringify(data));

    var html = '';
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){
                $("input[name='id']").val(page.data.randomSamplingAssembly.id);
            }else{
            	alert(page.message);
			}
        }
    });
};

//随机采样查看数据
function model_5_show(id,sid){
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
        $.each(page.tableChilds, function(i,value){//进行数据拼接
            head+= '<th>'+value.name+'</th>';
        });
        head+="</tr></thead>";
        //显示表头
        $(".view-data-box .ui-datatable-head .table").html(head);
        $.each(page.resultList, function(i,resultvalue){//进行数据拼接
            if(i<99){//只需要显示前99行
                body+= '<tr><td>'+(i+1)+'</td>';
                $.each(resultvalue,function(i,value){
                    body+= '<td>'+value+'</td>';
                });
                body+= '</tr>';
            }
        });
        body+="</tbody>";

        //显示数据内容
        $(".view-data-box .ui-datatable-body .table").html(body);
        //查看数据
        popDataBox("btn-view-data","view-data-box","1100");
        $("#more_data_submit").on("click",function(){
            $('.view-data-box').modal('hide');
        })
    });
}

/**
 * 标准化点击查询
 */
function model_16(expid,id,state){
    if(state == '0'){
        $('#model_16_save').off('click').on('click',model_16_save);
    }
    var url = window.ctx + '/datapretreatment/model_16_list';//查询的URL地址
    var data = [//查询的参数
        {name:'expid',value:expid},
        {name:'id',value:id},
    ];
    $.get(url, data, function(page){
        var html = "";
        html+= '<div class="dms-prop-wrap model_4_box">' +
					'<header class="dms-prop-header has-tab">' +
						'<ul class="dms-prop-tab dms-PerformTuning-tab">' +
							'<li class="selected">字段设置</li>' +
							'<li>执行调优</li>' +
						'</ul>' +
					'</header>'+
        		 	'<section class="dms-prop-body dms-PerformTuning-box">' +
						'<input type="hidden" name="id" value='+page.standardizationAssembly.id +'>'+
            			'<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
						'<div class="dms-prop-page dms-prop-input">' +
							'<form>' +
								'<div class="form-group">' +
									'<label>选择一个字段 <span class="dms-prop-tooltip">类型为double或者int等数值型</span></label>';
				if(page.standardizationChildAssemblies !=null && page.standardizationChildAssemblies.length > 0){
					html+=			'<a  class="btn pai-select-field">已选择' +
										'<span class="number">'+page.standardizationChildAssemblies.length+'</span>个字段' +
									'</a>';
				}else {
					html+=
									'<a  class="btn pai-select-field">选择' +
										'<span class="number"></span>字段' +
									'</a>';
				}
				html+=
        						'</div>' +
							'</form>' +
						'</div>' +
						'<div class="dms-prop-page" style="display: none;">' +
							'<div class="dms-prop-page dms-prop-input">' +
								'<form>' +
									'<div class="form-group">' +
										'<label>计算核心数</label>'+
            							'<input type="number" onchange="model_16_save();" name="coreNumber" class="form-control" value='+page.standardizationAssembly.coreNumber+'>'+
        							'</div>' +
									'<div class="form-group">' +
										'<label>每个核心内存</label>'+
										'<input type="number" onchange="model_16_save();" name="memory" class="form-control" value='+page.standardizationAssembly.memory+'>'+
        							'</div>' +
								'</form>' +
							'</div>' +
						'</div>' +
					'</section>' +
				'</div>';
        $('.pai-side-right').html(html);
        tab("dms-PerformTuning-tab","dms-PerformTuning-box");
        popBox("pai-select-field","createPortGroup-box","880");

        $(".pai-select-field").on("click",function(){
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

            if(page.standardizationChildAssemblies!=null&&page.standardizationChildAssemblies.length!=0){
                $.each(page.standardizationChildAssemblies, function(i,value){//进行数据拼接
                    right+= '<tr class="'+value.columnName+'_'+value.dataType+'"><td>';
                    right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
                    right+= '<td class="name">'+value.columnName+'</td><td class="dataType">'+value.dataType+'</td>';
                    right+='<input type="hidden" name="ids" value='+value.id+'>'+
							'<input type="hidden" name="columnName" value='+value.columnName+'>'+
							'<input type="hidden" name="dataType" value='+value.dataType+'>';
                    right+= '</tr>';
                    $(".left_wrap").find("."+value.columnName+'_'+value.dataType).prop("checked",true);
                });

            }
            $('.check-list').html(right);
            checkAll("createPortGroup-box");
        });
    });
};
/**
 * 标准化编辑
 */

function model_16_save(){
    var url = window.ctx + '/datapretreatment/model_16_save';//查询的URL地址
    console.log(url);
    var ids = [];
    var columnNames = [];
    var dataTypes = [];
    var expid = $("input[name='expid']").val();
    var l = $(".right_wrap .table-body tr").length;
    console.log(l);

     var $trs = $(".right_wrap .table-body tr");
    $trs.each(function(){
    	if ($(this).children("input").length > 0){
            ids.push($(this).find("input[name='ids']").val());
            columnNames.push($(this).find("input[name='columnName']").val());
            dataTypes.push($(this).find("input[name='dataType']").val());
		}else{
            ids.push("");
            columnNames.push($(this).find(".name").text());
            dataTypes.push($(this).find(".dataType").text());
		}

    });
    console.log("ids ->"+ids);
    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
        {name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
        {name:'columnName',value:columnNames},
        {name:'columnType',value:dataTypes},
        {name:'standardizationChildAssemblyId',value:ids},
        {name:'coreNumber',value:$("input[name='coreNumber']").val()},
        {name:'memory',value:$("input[name='memory']").val()},
    ];
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){
                $("input[name='id']").val(page.data.standardizationAssembly.id);
                model_16(expid,page.data.standardizationAssembly.id,'1');
            	 console.log( $(".right_wrap .table-body .check-list tr:visible").length)  ;
                $('.createPortGroup-box').modal('hide');
                console.log(JSON.stringify(page));
            }else {
            	alert(page.message);
			}
        }
    });
};


//标准化查看数据
function model_16_show(id,sid){
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
        $.each(page.tableChilds, function(i,value){//进行数据拼接
            head+= '<th>'+value.name+'</th>';
        });
        head+="</tr></thead>";
        //显示表头
        $(".view-data-box .ui-datatable-head .table").html(head);
        $.each(page.resultList, function(i,resultvalue){//进行数据拼接
            if(i<99){//只需要显示前99行
                body+= '<tr><td>'+(i+1)+'</td>';
                $.each(resultvalue,function(i,value){
                    body+= '<td>'+value+'</td>';
                });
                body+= '</tr>';
            }
        });
        body+="</tbody>";

        //显示数据内容
        $(".view-data-box .ui-datatable-body .table").html(body);
        //查看数据
        popDataBox("btn-view-data","view-data-box","1100");
        $("#more_data_submit").on("click",function(){
            $('.view-data-box').modal('hide');
        })
    });
}


/**
 * 缺失值填充点击查询
 */
function model_14(expid,id,state){
    if(state == '0'){
        $('#model_14_save').off('click').on('click',model_14_save);
    }
    var url = window.ctx + '/datapretreatment/model_14_list';//查询的URL地址
    var data = [//查询的参数
        {name:'expid',value:expid},
        {name:'id',value:id},
    ];
    $.get(url, data, function(page){
        var html = "";
        html+= '<div class="dms-prop-wrap model_4_box">' +
					'<header class="dms-prop-header has-tab">' +
							'<ul class="dms-prop-tab dms-PerformTuning-tab">' +
								'<li class="selected">字段设置</li>' +
							'</ul>' +
					'</header>'+
					 '<section class="dms-prop-body dms-PerformTuning-box">' +
						 '<input type="hidden" name="id" value='+page.fillMissingValuesAssembly.id +'>'+
						 '<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
						 '<div class="dms-prop-page dms-prop-input">' +
							 '<form>' +
								 '<div class="form-group">' +
									 '<label>填充的字段</label>';
			if(page.FillMissingValuesChildAssemblies !=null && page.FillMissingValuesChildAssemblies.length > 0){
				html+=			'<a  class="btn pai-select-field">已选择' +
									 '<span class="number">'+page.FillMissingValuesChildAssemblies.length+'</span>个字段' +
								 '</a>';
			}else {
				html+=
								'<a  class="btn pai-select-field">选择' +
										'<span class="number"></span>字段' +
								  '</a>';
			}
			html+=
								 '</div>' +
							'</form>' +
						 '</div>' +
                		 '<div class="dms-prop-page dms-prop-input">' +
							  '<form>' +
								  '<div class="form-group">' +
               						 '<label>原值 </label>';
        html+=					'<select onchange="model_14_save();" class="form-control select-class1">' ;
				if(page.fillMissingValuesAssembly.originValue == 'Null(数值和string)'){
		html+=							'<option value="Null(数值和string)" selected>Null(数值和string)</option>'+
										'<option value="空字符(string)">空字符(string)</option>'+
										'<option value="Null和空字符(string)">Null 和 空字符(string)</option>'+
										'<option value="自定义">自定义(string)</option>';
                }
        		if(page.fillMissingValuesAssembly.originValue == '空字符(string)'){
		html+=       					'<option value="Null(数值和string)">Null(数值和string)</option>'+
										'<option value="空字符(string)" selected>空字符(string)</option>'+
										'<option value="Null和空字符(string)">Null 和 空字符(string)</option>'+
										'<option value="自定义">自定义(string)</option>';
        		}
				if(page.fillMissingValuesAssembly.originValue == 'Null 和 空字符(string)'){
		html+=							'<option value="Null(数值和string)">Null(数值和string)</option>'+
										'<option value="空字符(string)">空字符(string)</option>'+
										'<option value="Null 和 空字符(string)" selected>Null 和 空字符(string)</option>'+
            							'<option value="自定义">自定义(string)</option>';
        		}
        		if(page.fillMissingValuesAssembly.originValue == '自定义(string)'){
		html+=							'<option value="Null(数值和string)">Null(数值和string)</option>'+
										'<option value="空字符(string)">空字符(string)</option>'+
										'<option value="Null和空字符(string)">Null 和 空字符(string)</option>'+
		 								'<option value="自定义(string)" selected>自定义(string)</option>';
        		}
        		if(page.fillMissingValuesAssembly.originValue == null || page.fillMissingValuesAssembly.originValue == ""){
         html+=           				'<option value="Null(数值和string)">Null(数值和string)</option>'+
										'<option value="空字符(string)">空字符(string)</option>'+
										'<option value="Null和空字符(string)">Null 和 空字符(string)</option>'+
										'<option value="自定义">自定义(string)</option>';
				}

		html+=						'</select>';


        html+=        			  '</div>'+
							  '<div class="form-group">' +
								 '<label>替换为 </label>'+
									 '<select onchange="model_14_save();" class="form-control select-class2">';
				if(page.fillMissingValuesAssembly.replaceValue == 'Min(数值型)'){
		html+=							'<option value="Min(数值型)" selected>Min(数值型)</option>'+
										'<option value="Max(数值型)">Max(数值型)</option>'+
										'<option value="Mean(数值型)">Mean(数值型)</option>'+
										'<option value="自定义">自定义(数值型和string)</option>';
				}
				if(page.fillMissingValuesAssembly.replaceValue == 'Max(数值型)'){
		html+=       					'<option value="Min(数值型)">Min(数值型)</option>'+
										'<option value="Max(数值型)" selected>Max(数值型)</option>'+
										'<option value="Mean(数值型)">Mean(数值型)</option>'+
										'<option value="自定义">自定义(数值型和string)</option>';
				}
				if(page.fillMissingValuesAssembly.replaceValue == 'Mean(数值型)'){
		html+=							'<option value="Min(数值型)">Min(数值型)</option>'+
										'<option value="Max(数值型)">Max(数值型)</option>'+
										'<option value="Mean(数值型)" selected>Mean(数值型)</option>'+
										'<option value="自定义">自定义(数值型和string)</option>';
				}
				if(page.fillMissingValuesAssembly.replaceValue == '自定义(数值型和string)'){
		html+=						'<option value="Min(数值型)">Min(数值型)</option>'+
									'<option value="Max(数值型)">Max(数值型)</option>'+
									'<option value="Mean(数值型)">Mean(数值型)</option>'+
									'<option value="自定义" selected>自定义(数值型和string)</option>';
				}
				if(page.fillMissingValuesAssembly.replaceValue == null || page.fillMissingValuesAssembly.replaceValue == ""){
		html+=           				'<option value="Min(数值型)">Min(数值型)</option>'+
										'<option value="Max(数值型)">Max(数值型)</option>'+
										'<option value="Mean(数值型)">Mean(数值型)</option>'+
										'<option value="自定义">自定义(数值型和string)</option>';
				}
		html+=						'</select>'+
								  '</div>'+
							  '</form>'
               			 '</div>' +
					'</section>' +
            	'</div>';
        $('.pai-side-right').html(html);
        tab("dms-PerformTuning-tab","dms-PerformTuning-box");
        popBox("pai-select-field","createPortGroup-box","880");

        $(".pai-select-field").on("click",function(){
            var left = "";//左边
            var right = "";//右边
            $.each(page.tableChilds, function(i,value){//进行字段类型拼接
                left+= '<li class="zero-accordion-item">';
                left+= '<div class="ui-checkbox">';
                left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
                left+= '<span class="checkBox">'+value.name+'</span>';
                left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
            });
            // console.log(left);
            $('.zero-accordion').html(left);
            if(page.FillMissingValuesChildAssemblies!=null&&page.FillMissingValuesChildAssemblies.length!=0){
                $.each(page.FillMissingValuesChildAssemblies, function(i,value){//进行数据拼接
                    right+= '<tr class="'+value.columnName+'_'+value.dataType+'"><td>';
                    right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
                    right+= '<td class="name">'+value.columnName+'</td><td class="dataType">'+value.dataType+'</td>';
                    right+='<input type="hidden" name="ids" value='+value.id+'>'+
                        '<input type="hidden" name="columnName" value='+value.columnName+'>'+
                        '<input type="hidden" name="dataType" value='+value.dataType+'>';
                    right+= '</tr>';
                    $(".left_wrap").find("."+value.columnName+'_'+value.dataType).prop("checked",true);
                });

            }
            $('.check-list').html(right);
            checkAll("createPortGroup-box");
        });
    });
};

/**
 * 缺失值填充编辑
 */

function model_14_save(){
    var url = window.ctx + '/datapretreatment/model_14_save';//查询的URL地址
    var ids = [];
    var columnNames = [];
    var dataTypes = [];
    var expid = $("input[name='expid']").val();
    var originValue = $(".select-class1").find("option:selected").text();
    var replaceValue = $(".select-class2").find("option:selected").text();

    var $trs = $(".right_wrap .table-body tr");
    $trs.each(function(){
        if ($(this).children("input").length > 0){
            ids.push($(this).find("input[name='ids']").val());
            columnNames.push($(this).find("input[name='columnName']").val());
            dataTypes.push($(this).find("input[name='dataType']").val());
        }else{
            ids.push("");
            columnNames.push($(this).find(".name").text());
            dataTypes.push($(this).find(".dataType").text());
        }

    });
    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
        {name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
        {name:'columnName',value:columnNames},
        {name:'columnType',value:dataTypes},
        {name:'fillMissingValuesAssemblyId',value:ids},
        {name:'originValue',value:originValue},
        {name:'replaceValue',value:replaceValue},
    ];
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){
                $("input[name='id']").val(page.data.fillMissingValuesAssembly.id);
                model_14(expid,page.data.fillMissingValuesAssembly.id,'1');
                $('.createPortGroup-box').modal('hide');
                console.log(JSON.stringify(page));
            }else {
                alert(page.message);
            }
        }
    });
};

//缺失值查看数据
function model_14_show(id,sid){
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
        $.each(page.tableChilds, function(i,value){//进行数据拼接
            head+= '<th>'+value.name+'</th>';
        });
        head+="</tr></thead>";
        //显示表头
        $(".view-data-box .ui-datatable-head .table").html(head);
        $.each(page.resultList, function(i,resultvalue){//进行数据拼接
            if(i<99){//只需要显示前99行
                body+= '<tr><td>'+(i+1)+'</td>';
                $.each(resultvalue,function(i,value){
                    body+= '<td>'+value+'</td>';
                });
                body+= '</tr>';
            }
        });
        body+="</tbody>";

        //显示数据内容
        $(".view-data-box .ui-datatable-body .table").html(body);
        //查看数据
        popDataBox("btn-view-data","view-data-box","1100");
        $("#more_data_submit").on("click",function(){
            $('.view-data-box').modal('hide');
        })
    });
}

/**
 * 增加序号列点击查询
 */
function model_12(expid,id,state){
    if(state == '0'){
        $('#model_12_save').off('click').on('click',model_12_save);
    }
    var url = window.ctx + '/datapretreatment/model_12_list';//查询的URL地址
    var data = [//查询的参数
        {name:'expid',value:expid},
        {name:'id',value:id},
    ];
    $.get(url, data, function(page){
        var html = "";
        html+= '<div class="dms-prop-wrap model_4_box">' +
            '<header class="dms-prop-header has-tab">' +
            '<ul class="dms-prop-tab dms-PerformTuning-tab">' +
            '<li class="selected">字段设置</li>' +
            '<li>执行调优</li>' +
            '</ul>' +
            '</header>'+
            '<section class="dms-prop-body dms-PerformTuning-box">' +
            '<input type="hidden" name="id" value='+page.addSerialNumAssembly.id +'>'+
            '<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
            '<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>默认全选 <span class="dms-prop-tooltip">默认为全选字段</span></label>';
        if(page.addSerialNumChildAssemblies !=null && page.addSerialNumChildAssemblies.length > 0){
            html+=			'<a  class="btn pai-select-field">已选择' +
                '<span class="number">'+page.addSerialNumChildAssemblies.length+'</span>个字段' +
                '</a>';
        }else {
            html+=
                '<a  class="btn pai-select-field">选择' +
                '<span class="number"></span>字段' +
                '</a>';
        }
        html+=
            '</div>' +
            '<div class="form-group">' +
            '<label>序号列</label>'+
            '<input class="form-control" onchange="model_12_save();" name="serialNum" value='+page.addSerialNumAssembly.serialNum+' type="text">'+
            '</div>'+
            '</form>' +
            '</div>' +
            '<div class="dms-prop-page" style="display: none;">' +
            '<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>计算核心数</label>'+
            '<input type="number" onchange="model_12_save();" name="coreNumber" class="form-control" value='+page.addSerialNumAssembly.coreNumber+'>'+
            '</div>' +
            '<div class="form-group">' +
            '<label>每个核心内存</label>'+
            '<input type="number" onchange="model_12_save();" name="memory" class="form-control" value='+page.addSerialNumAssembly.memory+'>'+
            '</div>' +
            '</form>' +
            '</div>' +
            '</div>' +
            '</section>' +
            '</div>';
        $('.pai-side-right').html(html);
        tab("dms-PerformTuning-tab","dms-PerformTuning-box");
        popBox("pai-select-field","createPortGroup-box","880");

        $(".pai-select-field").on("click",function(){
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
            if(page.addSerialNumChildAssemblies!=null&&page.addSerialNumChildAssemblies.length!=0){
                $.each(page.addSerialNumChildAssemblies, function(i,value){//进行数据拼接
                    right+= '<tr class="'+value.columnName+'_'+value.dataType+'"><td>';
                    right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
                    right+= '<td class="name">'+value.columnName+'</td><td class="dataType">'+value.dataType+'</td>';
                    right+='<input type="hidden" name="ids" value='+value.id+'>'+
                        '<input type="hidden" name="columnName" value='+value.columnName+'>'+
                        '<input type="hidden" name="dataType" value='+value.dataType+'>';
                    right+= '</tr>';
                    $(".left_wrap").find("."+value.columnName+'_'+value.dataType).prop("checked",true);
                });

            }
            $('.check-list').html(right);
            checkAll("createPortGroup-box");
        });
    });
};


/**
 * 增加序号列编辑
 */

function model_12_save(){
    var url = window.ctx + '/datapretreatment/model_12_save';//查询的URL地址
    var ids = [];
    var columnNames = [];
    var dataTypes = [];
    var expid = $("input[name='expid']").val();

    var $trs = $(".right_wrap .table-body tr");
    $trs.each(function(){
        if ($(this).children("input").length > 0){
            ids.push($(this).find("input[name='ids']").val());
            columnNames.push($(this).find("input[name='columnName']").val());
            dataTypes.push($(this).find("input[name='dataType']").val());
        }else{
            ids.push("");
            columnNames.push($(this).find(".name").text());
            dataTypes.push($(this).find(".dataType").text());
        }

    });
    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
        {name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
        {name:'columnName',value:columnNames},
        {name:'columnType',value:dataTypes},
        {name:'addSerialNumAssemblyId',value:ids},
        {name:'serialNum',value:$("input[name='serialNum']").val()},
        {name:'coreNumber',value:$("input[name='coreNumber']").val()},
        {name:'memory',value:$("input[name='memory']").val()},
    ];
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){

                $("input[name='id']").val(page.data.addSerialNumAssembly.id);
                model_12(expid,page.data.addSerialNumAssembly.id,'1');
                $('.createPortGroup-box').modal('hide');
                console.log(JSON.stringify(page));
            }else {
                alert(page.message);
            }
        }
    });
};

//增加序号列查看数据
function model_12_show(id,sid){
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
        $.each(page.tableChilds, function(i,value){//进行数据拼接
            head+= '<th>'+value.name+'</th>';
        });
        head+="</tr></thead>";
        //显示表头
        $(".view-data-box .ui-datatable-head .table").html(head);
        $.each(page.resultList, function(i,resultvalue){//进行数据拼接
            if(i<99){//只需要显示前99行
                body+= '<tr><td>'+(i+1)+'</td>';
                $.each(resultvalue,function(i,value){
                    body+= '<td>'+value+'</td>';
                });
                body+= '</tr>';
            }
        });
        body+="</tbody>";

        //显示数据内容
        $(".view-data-box .ui-datatable-body .table").html(body);
        //查看数据
        popDataBox("btn-view-data","view-data-box","1100");
        $("#more_data_submit").on("click",function(){
            $('.view-data-box').modal('hide');
        })
    });
}


/**
 * UNION点击查询
 */
function model_17(expid,id,state){
    if(state == '0'){
        $('#model_17_save').off('click').on('click',model_17_save);
    }
    var url = window.ctx + '/datapretreatment/model_17_list';//查询的URL地址
    var data = [//查询的参数
        {name:'expid',value:expid},
        {name:'id',value:id},
    ];
    $.get(url, data, function(page){
        var html = "";
        html+= '<div class="dms-prop-wrap model_4_box">' +
            '<header class="dms-prop-header has-tab">' +
            '<ul class="dms-prop-tab dms-PerformTuning-tab">' +
            '<li class="selected">字段设置</li>' +
            '</ul>' +
            '</header>'+
            '<section class="dms-prop-body dms-PerformTuning-box">' +
            '<input type="hidden" name="id" value='+page.unionAssembly.id +'>'+
            '<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
            '<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>左表输出列</label>';
        if(page.unionChildAssembliesLeft !=null && page.unionChildAssembliesLeft.length > 0){
            html+=			'<a  class="btn pai-select-field">已选择' +
                '<span class="number">'+page.unionChildAssembliesLeft.length+'</span>个字段' +
                '</a>';
        }else {
            html+=
                '<a  class="btn pai-select-field">选择' +
                '<span class="number"></span>字段' +
                '</a>';
        }
        html+=
            '</div>' +
            '<div class="form-group">' +
            '<label>输入左表的where条件</label>'+
            '<textarea class="form-control" onchange="model_17_save();" name="whereConditionLeft"  placeholder="输入左表的where后的条件">'+page.unionAssembly.whereConditionLeft+'</textarea>'+
            '</div>'+
            '</form>' +
            '</div>' +
			'<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>右表输出列</label>';
        if(page.unionChildAssembliesRight !=null && page.unionChildAssembliesRight.length > 0){
            html+=			'<a  class="btn"  id="right-table" data-toggle="modal" data-target="#my-modal">已选择' +
                '<span class="number">'+page.unionChildAssembliesRight.length+'</span>个字段' +
                '</a>';
        }else {
            html+=
                '<a  class="btn " id="right-table" data-toggle="modal" data-target="#my-modal">选择' +
                '<span class="number"></span>字段' +
                '</a>';
        }
        html+=
            '</div>' +
            '<div class="form-group">' +
            '<label>输右表的where条件</label>'+
            '<textarea class="form-control" onchange="model_17_save();" name="whereConditionRight"  placeholder="输入右表的where后的条件">'+page.unionAssembly.whereConditionRight+'</textarea>'+
            '</div>'+
            '<div class="form-group">';
				if (page.unionAssembly.deduplication == 1) {
					html+='<input type="checkbox" checked  name="deduplication" onchange="model_17_save();">';
				}else {
					html+='<input type="checkbox" name="deduplication" onchange="model_17_save();">';
				}
        html+=				'<label>去重</label>'+
            '</div>' +
            '</form>' +
			'</div>' +
            '</section>' +
            '</div>';
        $('.pai-side-right').html(html);
        tab("dms-PerformTuning-tab","dms-PerformTuning-box");
        popBox("pai-select-field","createPortGroup-box","880");

        $(".pai-select-field").on("click",function(){
            var left = "";//左边
            var right = "";//右边
            $.each(page.tableChildsLeft, function(i,value){//进行字段类型拼接
                left+= '<li class="zero-accordion-item">';
                left+= '<div class="ui-checkbox">';
                left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
                left+= '<input type="hidden" name="operationType" id="operationType" value='+0+'>';
                left+= '<span class="checkBox">'+value.name+'</span>';
                left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
            });
            // console.log(left);
            $('.zero-accordion').html(left);

			if(page.unionChildAssembliesLeft!=null&&page.unionChildAssembliesLeft.length!=0){
                $.each(page.unionChildAssembliesLeft, function(i,value){//进行数据拼接
                    right+= '<tr class="'+value.columnIn+'_'+value.dataType+'"><td>';
                    right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
                    right+= '<td class="name">'+value.columnIn+'</td><td class="dataType">'+value.dataType+'</td>';
                    right+='<input type="hidden" name="ids" value='+value.id+'>'+
                        '<input type="hidden" name="operationType" value='+value.operationType+'>'+
                        '<input type="hidden" name="columnName" value='+value.columnIn+'>'+
                        '<input type="hidden" name="dataType" value='+value.dataType+'>';
                    right+= '</tr>';
                    $(".left_wrap").find("."+value.columnIn+'_'+value.dataType).prop("checked",true);
                });

            }
            $('.check-list').html(right);
            checkAll("createPortGroup-box");
        });
        //右表
        $("#right-table").on("click",function(){
            var left = "";//左边
            var right = "";//右边
            $.each(page.tableChildsRight, function(i,value){//进行字段类型拼接
                left+= '<li class="zero-accordion-item">';
                left+= '<div class="ui-checkbox">';
                left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
                left+= '<input type="hidden" name="operationType" id="operationType" value='+1+'>';
                left+= '<span class="checkBox">'+value.name+'</span>';
                left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
            });
            // console.log(left);
            $('.zero-accordion').html(left);

            if(page.unionChildAssembliesRight!=null&&page.unionChildAssembliesRight.length!=0){
                $.each(page.unionChildAssembliesRight, function(i,value){//进行数据拼接
                    right+= '<tr class="'+value.columnIn+'_'+value.dataType+'"><td>';
                    right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
                    right+= '<td class="name">'+value.columnIn+'</td><td class="dataType">'+value.dataType+'</td>';
                    right+='<input type="hidden" name="ids" value='+value.id+'>'+
                        '<input type="hidden" name="operationType" value='+value.operationType+'>'+
                        '<input type="hidden" name="columnName" value='+value.columnIn+'>'+
                        '<input type="hidden" name="dataType" value='+value.dataType+'>';
                    right+= '</tr>';
                    $(".left_wrap").find("."+value.columnIn+'_'+value.dataType).prop("checked",true);
                });

            }
            $('.check-list').html(right);
            checkAll("createPortGroup-box");
        });
        console.log(JSON.stringify(page));
    });
};


/**
 * UNION编辑
 */
function model_17_save(){
    var url = window.ctx + '/datapretreatment/model_17_save';//查询的URL地址
    var ids = [];
    var types = [];
    var columnNames = [];
    var dataTypes = [];
    var expid = $("input[name='expid']").val();
    var $trs = $(".right_wrap .table-body tr");
    $trs.each(function(){
        if ($(this).children("input").length > 0){
            ids.push($(this).find("input[name='ids']").val());
            types.push($(this).find("input[name='operationType']").val());
            columnNames.push($(this).find("input[name='columnName']").val());
            dataTypes.push($(this).find("input[name='dataType']").val());
        }else{
            ids.push("");
            types.push($('#operationType').val());
            columnNames.push($(this).find(".name").text());
            dataTypes.push($(this).find(".dataType").text());
        }

    });

    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
        {name:'expid',value:expid},
        {name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
        {name:'columnName',value:columnNames},
        {name:'columnType',value:dataTypes},
        {name:'unionChildAssemblyId',value:ids},
        {name:'operationType',value:types},
        {name:'whereConditionLeft',value:$("textarea[name='whereConditionLeft']").val()},
        {name:'whereConditionRight',value:$("textarea[name='whereConditionRight']").val()},
        {name:'deduplication',value:$("input[name='deduplication']").prop("checked") == true ? 1 : 0},
    ];
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){

                $("input[name='id']").val(page.data.unionAssembly.id);
                model_17(expid,page.data.unionAssembly.id,'1');
                $('.createPortGroup-box').modal('hide');
                console.log(JSON.stringify(page));
            }else {
                alert(page.message);
            }
        }
    });
};


//UNION查看数据
function model_17_show(id,sid){
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
        $.each(page.unionChildAssemblies, function(i,value){//进行数据拼接
            head+= '<th>'+value.columnIn+'</th>';
        });
        head+="</tr></thead>";
        //显示表头
        $(".view-data-box .ui-datatable-head .table").html(head);
        $.each(page.resultList, function(i,resultvalue){//进行数据拼接
            if(i<99){//只需要显示前99行
                body+= '<tr><td>'+(i+1)+'</td>';
                $.each(resultvalue,function(i,value){
                    body+= '<td>'+value+'</td>';
                });
                body+= '</tr>';
            }
        });
        body+="</tbody>";

        //显示数据内容
        $(".view-data-box .ui-datatable-body .table").html(body);
        //查看数据
        popDataBox("btn-view-data","view-data-box","1100");
        $("#more_data_submit").on("click",function(){
            $('.view-data-box').modal('hide');
        })
    });
}
/**
 * JOIN点击查询
 */
function model_9(expid,id,state){
    if(state == '0'){
        $('#model_9_save').off('click').on('click',model_9_save);
    }
    var url = window.ctx + '/datapretreatment/model_9_list';//查询的URL地址
    var data = [//查询的参数
        {name:'expid',value:expid},
        {name:'id',value:id},
    ];
    $.get(url, data, function(page){
        var html = "";
        html+= '<div class="dms-prop-wrap model_4_box">' +
            '<header class="dms-prop-header has-tab">' +
            '<ul class="dms-prop-tab dms-PerformTuning-tab">' +
            '<li class="selected">字段设置</li>' +
            '</ul>' +
            '</header>'+
            '<section class="dms-prop-body dms-PerformTuning-box">' +
					'<input type="hidden" name="id" value='+page.joinAssembly.id +'>'+
					'<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
            '<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>连接类型 </label>';
        html+=					'<select onchange="model_9_save();" class="form-control select-class1">' ;
        if(page.joinAssembly.joinType == 1){
            html+=           				'<option value="1" selected>左连接</option>'+
                '<option value="2">内连接</option>'+
                '<option value="3">右连接</option>'+
                '<option value="4">全连接</option>';
        }
        if(page.joinAssembly.joinType == 2){
            html+=           				'<option value="1">左连接</option>'+
                '<option value="2" selected>内连接</option>'+
                '<option value="3">右连接</option>'+
                '<option value="4">全连接</option>';
        }
        if(page.joinAssembly.joinType  == 3){
            html+=           				'<option value="1">左连接</option>'+
                '<option value="2">内连接</option>'+
                '<option value="3" selected>右连接</option>'+
                '<option value="4">全连接</option>';
        }
        if(page.joinAssembly.joinType  == 4){
            html+=           				'<option value="1">左连接</option>'+
                '<option value="2">内连接</option>'+
                '<option value="3">右连接</option>'+
                '<option value="4" selected>全连接</option>';
        }
        if(page.joinAssembly.joinType  == null || page.joinAssembly.joinType  == ""){
            html+=           				'<option value="1">左连接</option>'+
                '<option value="2">内连接</option>'+
                '<option value="3">右连接</option>'+
                '<option value="4">全连接</option>';
        }

        html+=						'</select>'+
			'</div>' +
			'</form>';


        html+=        			  '</div>'+

			'<div class="dms-prop-page dms-prop-input">' +
            	'<label>关联条件</label>'+
				'<textarea name="joinCondition" onchange="model_9_save();" class="form-control" placeholder="请输入关联查询条件">'+page.joinAssembly.joinCondition+'</textarea>'+
			'</div>'+
            '<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>左表输出列</label>';
        if(page.joinChildAssembliesLeft !=null && page.joinChildAssembliesLeft.length > 0){
            html+=			'<a  class="btn pai-select-field">已选择' +
                '<span class="number">'+page.joinChildAssembliesLeft.length+'</span>个字段' +
                '</a>';
        }else {
            html+=
                '<a  class="btn pai-select-field">选择' +
                '<span class="number"></span>字段' +
                '</a>';
        }
        html+=
            '</div>' +
            '</form>' +
            '</div>' +
            '<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>右表输出列</label>';
        if(page.joinChildAssembliesRight !=null && page.joinChildAssembliesRight.length > 0){
            html+=			'<a  class="btn"  id="right-table" data-toggle="modal" data-target="#my-modal">已选择' +
                '<span class="number">'+page.joinChildAssembliesRight.length+'</span>个字段' +
                '</a>';
        }else {
            html+=
                '<a  class="btn " id="right-table" data-toggle="modal" data-target="#my-modal">选择' +
                '<span class="number"></span>字段' +
                '</a>';
        }
        html+=
            '</div>' +
            '</form>' +
            '</div>' +
            '</section>' +
            '</div>';
        $('.pai-side-right').html(html);
        tab("dms-PerformTuning-tab","dms-PerformTuning-box");
        popBox("pai-select-field","createPortGroup-box","880");

        $(".pai-select-field").on("click",function(){
            var left = "";//左边
            var right = "";//右边
            $.each(page.tableChildsLeft, function(i,value){//进行字段类型拼接
                left+= '<li class="zero-accordion-item">';
                left+= '<div class="ui-checkbox">';
                left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
                left+= '<input type="hidden" name="operationType" id="operationType" value='+1+'>';
                left+= '<span class="checkBox">'+value.name+'</span>';
                left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
            });
            $('.zero-accordion').html(left);

            if(page.joinChildAssembliesLeft!=null&&page.joinChildAssembliesLeft.length!=0){
                $.each(page.joinChildAssembliesLeft, function(i,value){//进行数据拼接
                    right+= '<tr class="'+value.columnIn+'_'+value.dataType+'"><td>';
                    right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
                    right+= '<td class="name">'+value.columnIn+'</td><td class="dataType">'+value.dataType+'</td>';
                    right+='<input type="hidden" name="ids" value='+value.id+'>'+
                        '<input type="hidden" name="operationType" value='+value.operationType+'>'+
                        '<input type="hidden" name="columnName" value='+value.columnIn+'>'+
                        '<input type="hidden" name="dataType" value='+value.dataType+'>';
                    right+= '</tr>';
                    $(".left_wrap").find("."+value.columnIn+'_'+value.dataType).prop("checked",true);
                });

            }
            $('.check-list').html(right);
            checkAll("createPortGroup-box");
        });
        //右表
        $("#right-table").on("click",function(){
            var left = "";//左边
            var right = "";//右边
            $.each(page.tableChildsRight, function(i,value){//进行字段类型拼接
                left+= '<li class="zero-accordion-item">';
                left+= '<div class="ui-checkbox">';
                left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
                left+= '<input type="hidden" name="operationType" id="operationType" value='+3+'>';
                left+= '<span class="checkBox">'+value.name+'</span>';
                left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
            });
            $('.zero-accordion').html(left);

            if(page.joinChildAssembliesRight!=null&&page.joinChildAssembliesRight.length!=0){
                $.each(page.joinChildAssembliesRight, function(i,value){//进行数据拼接
                    right+= '<tr class="'+value.columnIn+'_'+value.dataType+'"><td>';
                    right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
                    right+= '<td class="name">'+value.columnIn+'</td><td class="dataType">'+value.dataType+'</td>';
                    right+='<input type="hidden" name="ids" value='+value.id+'>'+
                        '<input type="hidden" name="operationType" value='+value.operationType+'>'+
                        '<input type="hidden" name="columnName" value='+value.columnIn+'>'+
                        '<input type="hidden" name="dataType" value='+value.dataType+'>';
                    right+= '</tr>';
                    $(".left_wrap").find("."+value.columnIn+'_'+value.dataType).prop("checked",true);
                });

            }
            $('.check-list').html(right);
            checkAll("createPortGroup-box");
        });
        console.log(JSON.stringify(page));
    });
};


/**
 * JOIN编辑
 */
function model_9_save(){
    var url = window.ctx + '/datapretreatment/model_9_save';//查询的URL地址
    var ids = [];
    var types = [];
    var columnNames = [];
    var dataTypes = [];
    var expid = $("input[name='expid']").val();

    var $trs = $(".right_wrap .table-body tr");
    $trs.each(function(){
        if ($(this).children("input").length > 0){
            ids.push($(this).find("input[name='ids']").val());
            types.push($(this).find("input[name='operationType']").val());
            columnNames.push($(this).find("input[name='columnName']").val());
            dataTypes.push($(this).find("input[name='dataType']").val());
        }else{
            ids.push("");
            types.push($('#operationType').val());
            columnNames.push($(this).find(".name").text());
            dataTypes.push($(this).find(".dataType").text());
        }

    });
    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
        {name:'expid',value:expid},
        {name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
        {name:'columnName',value:columnNames},
        {name:'columnType',value:dataTypes},
        {name:'joinChildAssemblyId',value:ids},
        {name:'operationType',value:types},
        {name:'joinCondition',value:$("textarea[name='joinCondition']").val()},
        {name:'joinType',value:$('.select-class1').find("option:selected").val()},
    ];
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){
                $("input[name='id']").val(page.data.joinAssembly.id);
                model_9(expid,page.data.joinAssembly.id,'1');
                $('.createPortGroup-box').modal('hide');
                console.log(JSON.stringify(page));
            }else {
                alert(page.message);
            }
        }
    });
};

//JOIN查看数据
function model_9_show(id,sid){
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
        $.each(page.joinChildAssemblies, function(i,value){//进行数据拼接
            head+= '<th>'+value.columnIn+'</th>';
        });
        head+="</tr></thead>";
        //显示表头
        $(".view-data-box .ui-datatable-head .table").html(head);
        $.each(page.resultList, function(i,resultvalue){//进行数据拼接
            if(i<99){//只需要显示前99行
                body+= '<tr><td>'+(i+1)+'</td>';
                $.each(resultvalue,function(i,value){
                    body+= '<td>'+value+'</td>';
                });
                body+= '</tr>';
            }
        });
        body+="</tbody>";

        //显示数据内容
        $(".view-data-box .ui-datatable-body .table").html(body);
        //查看数据
        popDataBox("btn-view-data","view-data-box","1100");
        $("#more_data_submit").on("click",function(){
            $('.view-data-box').modal('hide');
        })
    });
}

/**
 * 归一化列表
 */
function model_15(expid,id,state){
	if(state == '0'){
		$('#model_15_save').off('click').on('click',model_15_save);
	}
	var url = window.ctx + '/datapretreatment/model_15_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	];
	$('.table-body').attr("id",id);
	var conn = [];//已选数组
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		if(page.normalizationChildAssemblies!=null&&page.normalizationChildAssemblies.length!=0){
			$.each(page.normalizationChildAssemblies, function(i,value){//进行字段类型拼接
				conn.push(value.name+";"+value.dataType);
			});
		}
		html+= '<div class="dms-prop-wrap model_4_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab dms-PerformTuning-tab"><li class="selected">字段设置</li><li>执行调优</li></ul></header>';
		html+= '<section class="dms-prop-body dms-PerformTuning-box"><div class="dms-prop-page dms-prop-input"><form><div class="form-group"><label>默认全选 <span class="dms-prop-tooltip">默认为全选字段</span></label>';
		if(page.normalizationChildAssemblies!=null&&page.normalizationChildAssemblies.length!=0){
			html+= '<a  class="btn pai-select-field">已选择<span class="number">'+page.normalizationChildAssemblies.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field">选择<span class="number"></span>字段</a>';
		}
		html+= '</div></form></div><div class="dms-prop-page" style="display: none;"><div class="dms-prop-page dms-prop-input"><form><div class="form-group"><label>计算核心数</label>';
		if(page.normalizationAssembly!=null&&page.normalizationAssembly.length!=0){
			html+='<input type="email" class="form-control" id="coreNumber" onblur="model_15_save(\'coreNumber\',this.value,\''+conn+'\')" value="'+page.normalizationAssembly.coreNumber+'">';
		}else{
			html+='<input type="email" class="form-control">';
		}
		html+= '</div><div class="form-group"><label>每个核心内存</label>';
		if(page.normalizationAssembly!=null&&page.normalizationAssembly.length!=0){
			html+='<input type="email" class="form-control" id="memory" onblur="model_15_save(\'memory\',this.value,\''+conn+'\')" value="'+page.normalizationAssembly.memory+'">';
		}else{
			html+='<input type="email" class="form-control">';
		}
		html+='</div></form></div></div></section></div>';
		$('.pai-side-right').html(html);
		tab("dms-PerformTuning-tab","dms-PerformTuning-box");
	    popBox("pai-select-field","createPortGroup-box","880");
	    $(".pai-select-field").on("click",function(){
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
			
			if(page.normalizationChildAssemblies!=null&&page.normalizationChildAssemblies.length!=0){
				$.each(page.normalizationChildAssemblies, function(i,value){//进行数据拼接
					right+= '<tr class="'+value.name+'_'+value.dataType+'"><td>';
					right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
					right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
					right+= '</tr>';
					$(".left_wrap").find("."+value.name+'_'+value.dataType).prop("checked",true);
				});
			}
			$('.check-list').html(right);
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * 归一化保存
 */
function model_15_save(columnName,columnValue,conns){
	var url = window.ctx + '/datapretreatment/model_15_save';//查询的URL地址
	var conn = [];//已选数组
	var state = "0";//状态码
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+$(this).find(".dataType").text());
	})
	if(conn==""){
		conn = conns;
	}
	//如果数组中确实无值，则传一个状态码到后台
	if(conn!=""&&typeof(conn)!="undefined"){
		state = "1";
	}
	var coreNumber;
	if(columnName == "coreNumber"){
		coreNumber = columnValue;
	}else{
		coreNumber = $("#coreNumber").val();
	}
	var memory;
	if(columnName == "memory"){
		memory = columnValue;
	}else{
		memory = $("#memory").val();
	}
	var data = [//查询的参数
	            {name:'id',value:$('.table-body').attr("id")},
	            {name:'conn',value:conn},
	            {name:'coreNumber',value:coreNumber},
	            {name:'memory',value:memory},
	            {name:'state',value:state},
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
        	model_15(expid,page.id,"1");
        }
    });
}

//归一化查看数据
function model_15_show(id,sid){
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

/*全选，已选*/
function checkAll(a){
	var $trs = $("."+a+" .right_wrap .table-body tr");
	var $input = $("."+a+" .left_wrap .ui-checkbox input.checkBox");
	var $right = $("."+a+" .right_wrap tbody");
	var isCheckAll = function ()
	{
		for (var i = 1, n = 0; i < $input.length; i++)
		{
			$input[i].checked && n++
		}
		$input[0].checked = n == $input.length - 1;
	};
	//全选/全不选
	$input[0].onclick = function ()
	{

		for (var i = 1; i < $input.length; i++)
		{
			$input[i].checked = this.checked;
		}
		arr("createPortGroup-box")
		isCheckAll()
	};
	//根据复选个数更新全选框状态
	for (var i = 1; i < $input.length; i++)
	{
		$input[i].onclick = function ()
		{
			isCheckAll()
		}
	}
	//初始化
	var fieldArr = [];
	var dataArr = [];
	var $fieldVal =$(".zero-accordion").find(".zero-accordion-item").find("span.checkBox");
	var $dataVal = $(".zero-accordion").find(".zero-accordion-item").find("span.data-val i");
	var $currentInput = $("."+a+" .left_wrap .zero-accordion-item input.checkBox");
	$fieldVal.each(function(){
		fieldArr.push($(this).text())
	})
	$dataVal.each(function(){
		dataArr.push($(this).text())
	})
	for(var i=0;i<fieldArr.length;i++){
		$($input[i+1]).addClass(fieldArr[i]+"_"+dataArr[i]);
	}
	//多选框单选
	$currentInput.on("click",function(){
		//获取当前多选框字段
		var $currentField = $(this).siblings("span.checkBox").text();
		//获取当前多选框数据类型
		var $currentData = $(this).parents(".zero-accordion-item").find(".data-val i").text()
		//获取右侧tbody
		var $right = $("."+a+" .right_wrap tbody");
		//设置class
		var $class = $currentField;
		var html = "";
		//设置右侧子项
		html+='<tr class="'+$class+'">'
			+ '<td><span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>'
			+ '<td class="name">'+$currentField+'</td>'
			+ '<td class="dataType">'+$currentData+'</td>'
			+ '</tr>'
		if($(this).is(":checked")){
			$right.append(html);
			$(this).addClass($class);
		}else{
			$right.find("."+$class).remove();
		}
	})
	//已选删除
	$("."+a+" .right_wrap .table-header .icon-delete").on("click",function(){
		$(this).parents(".table-header").next(".table-body").find('tbody tr').remove()
		for (var i = 0; i < $input.length; i++)
		{
			$input[i].checked = false;
			
		}
	})
	$("."+a+" .right_wrap .table-body .icon-delete").on("click",function(){
		var $currentClass = $(this).parents("tr").attr("class");
		$(".left_wrap .zero-accordion-item").find("."+$currentClass).prop("checked",false);
		isCheckAll()
	})
}
/*全选已选左右效果*/
function arr(a){
	/*全选，反选*/
	var fieldArr = [];       //字段
	var dataArr = [];        //类型
	var $fieldVal = $("."+a+" .left_wrap  .zero-accordion-item span.checkBox");
	var $dataVal = $("."+a+" .left_wrap  .zero-accordion-item .data-val i");
	var $input = $("."+a+" .left_wrap .ui-checkbox input.checkBox");
	var $right = $("."+a+" .right_wrap tbody");
	var html = "";
	//字段存入数组
	$fieldVal.each(function(){
		fieldArr.push($(this).text())
	})
	//类型存入数组
	$dataVal.each(function () {
		dataArr.push($(this).text())
	})
	//右侧数据初始化
	$right.find("tr").remove();
	if($input[0].checked){
		for(var i=0;i<fieldArr.length;i++){
			html+='<tr class="'+fieldArr[i]+'">'
				+ '<td><span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>'
				+ '<td class="name">'+fieldArr[i]+'</td>'
				+ '<td class="dataType">'+dataArr[i]+'</td>'
				+ '</tr>'
		}
		$right.append(html);
	}else{
		/*反选 */
		$right.find("tr").remove();
	}
}
/*删除已选*/
function del(a){
	//获取当前tr以及class
	var $this = $(a);
	var $currentClass = $this.parents("tr").attr("class");
	//删除当前tr
	$this.parents("tr").remove();
	//取消对应左侧多选框
	$(".left_wrap .zero-accordion-item").find("."+$currentClass).prop("checked",false);
	return;
}

/*判断左右值是否一样*/
/*function arr(a){
	var $left = $("."+a+" .left_wrap  .zero-accordion-item");
	var $right = $("."+a+" .right_wrap tbody tr");
	for(var i=0;i<$left.length;i++){
		var $val = $($left[i]).parent("ul").find("li:first-child span").text();
		$($right[i]).find("td:last-child").text($val);
	}
}*/

function exethispoint(){
	
}
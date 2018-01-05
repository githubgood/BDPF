/**
 * @description 统计分析JS脚本
 * @auth jm
 */

/**
 * 至折线（面积）图列表
 */
function model_32(expid,id,state){

	if(state == '0'){
		$('#model_32_save').off('click').on('click',model_32_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_32_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * 至折线（面积）图列表数据保存
 */
function model_32_save(){
	var url = window.ctx + '/statisticalanalysis/model_32_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+32);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_32(expid,page.id,"1");
    		}
        }
    });
};

//过滤与映射查看数据
function model_show(id,sid){
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
		$.each(page.statisticalanalysisAssemblies, function(i,value){//进行数据拼接
			head+= '<th>'+value.characteristicColumn+'</th>';
			heads[i] = value.characteristicColumn;
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
 * 散点(气泡)图列表
 */
function model_33(expid,id,state){

	if(state == '0'){
		$('#model_33_save').off('click').on('click',model_33_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_33_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 散点(气泡)图列表数据保存
 */
function model_33_save(){
	var url = window.ctx + '/statisticalanalysis/model_33_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+33);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_33(expid,page.id,"1");
    		}
        }
    });
};

/**
 * K线图列表
 */
function model_34(expid,id,state){

	if(state == '0'){
		$('#model_34_save').off('click').on('click',model_34_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_34_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * K线图列表数据保存
 */
function model_34_save(){
	var url = window.ctx + '/statisticalanalysis/model_34_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+34);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_34(expid,page.id,"1");
    		}
        }
    });
};

/**
 * 饼（圆环）图列表
 */
function model_35(expid,id,state){

	if(state == '0'){
		$('#model_35_save').off('click').on('click',model_35_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_35_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 饼（圆环）图列表数据保存
 */
function model_35_save(){
	var url = window.ctx + '/statisticalanalysis/model_35_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+35);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_35(expid,page.id,"1");
    		}
        }
    });
};



/**
 * 雷达（面积）图列表
 */
function model_36(expid,id,state){

	if(state == '0'){
		$('#model_36_save').off('click').on('click',model_36_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_36_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 雷达（面积）图列表数据保存
 */
function model_36_save(){
	var url = window.ctx + '/statisticalanalysis/model_36_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+36);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_36(expid,page.id,"1");
    		}
        }
    });
};




/**
 * 和弦图列表
 */
function model_37(expid,id,state){

	if(state == '0'){
		$('#model_37_save').off('click').on('click',model_37_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_37_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 和弦图列表数据保存
 */
function model_37_save(){
	var url = window.ctx + '/statisticalanalysis/model_37_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+37);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_37(expid,page.id,"1");
    		}
        }
    });
};



/**
 * 力导向布局图列表
 */
function model_38(expid,id,state){

	if(state == '0'){
		$('#model_38_save').off('click').on('click',model_38_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_38_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 力导向布局图列表数据保存
 */
function model_38_save(){
	var url = window.ctx + '/statisticalanalysis/model_38_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+38);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_38(expid,page.id,"1");
    		}
        }
    });
};

/**
 * 图柱状(条形)图列表
 */
function model_39(expid,id,state){

	if(state == '0'){
		$('#model_39_save').off('click').on('click',model_39_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_39_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 图柱状(条形)图列表数据保存
 */
function model_39_save(){
	var url = window.ctx + '/statisticalanalysis/model_39_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+39);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_39(expid,page.id,"1");
    		}
        }
    });
};

/**
 * 仪表盘图列表
 */
function model_40(expid,id,state){

	if(state == '0'){
		$('#model_40_save').off('click').on('click',model_40_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_40_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 仪表盘图列表数据保存
 */
function model_40_save(){
	var url = window.ctx + '/statisticalanalysis/model_40_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+40);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_40(expid,page.id,"1");
    		}
        }
    });
};



/**
 * 漏斗图列表
 */
function model_41(expid,id,state){

	if(state == '0'){
		$('#model_41_save').off('click').on('click',model_41_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_41_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 漏斗图列表数据保存
 */
function model_41_save(){
	var url = window.ctx + '/statisticalanalysis/model_41_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+41);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_41(expid,page.id,"1");
    		}
        }
    });
};



/**
 * 热力图列表
 */
function model_42(expid,id,state){

	if(state == '0'){
		$('#model_42_save').off('click').on('click',model_42_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_42_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 热力图列表数据保存
 */
function model_42_save(){
	var url = window.ctx + '/statisticalanalysis/model_42_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+42);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_42(expid,page.id,"1");
    		}
        }
    });
};


/**
 * 地图列表
 */
function model_43(expid,id,state){
	if(state == '0'){
		$('#model_43_save').off('click').on('click',model_43_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_43_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		/*if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{*/
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
/*		}
*/		html+= '</form></div></section></div>';
	    $('.pai-side-right').html(html);
	    //选择字段
		popBox("pai-select-field","createPortGroup-box","880");
		$(".pai-select-field").on("click",function(){
			var thisid=$(this).attr("id");
			var left = "";//左边
			var right = "";//右边
			/*$.each(page.tableChilds, function(i,value){*///进行字段类型拼接
				left+= '<li class="zero-accordion-item">';
				left+= '<div class="ui-checkbox">';
				left+= '<input class="checkBox" type="checkbox" data-name="check-id" name="select_field_id" value="'+"city_name"+'">';
				left+= '<span class="checkBox">'+"city_name"+'</span>';
				left+= '</div><span>[<i>'+"string"+'</i>]</span></li>';
			/*});*/
			$('.zero-accordion').html(left);
		/*	if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
				$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
					right+= '<tr><td>';
					right+= '<span class="icon icon-delete btn-delete"></span></td>';
					right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">string</td>';
					right+='</tr>';
				});*/
				if(right != ''){
					$('.table-body tbody').removeClass();
					$('.table-body tbody').addClass("check-list sh");
					$('.check-list').html(right);
				}
			/*}*/
			if(right == ''){
				/*$.each(page.tableChilds, function(i,value){*///进行数据拼接
					right+= '<tr><td>';
					right+= '<span class="icon icon-delete btn-delete"></span></td>';
					right+= '<td class="name">'+"city_name"+'</td><td class="dataType">'+"string"+'</td>';
					right+= '</tr>';
				/*});*/
				$('.table-body tbody').removeClass();
				$('.table-body tbody').addClass("check-list hi");
				
				$('.check-list').html(right);
			}		
			$('.check-list').attr("id",thisid);
			
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * 地图列表数据保存
 */
function model_43_save(){
	var url = window.ctx + '/statisticalanalysis/model_43_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+43);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_43(expid,page.id,"1");
    		}
        }
    });
};

/**
 * 韦恩图列表
 */
function model_44(expid,id,state){

	if(state == '0'){
		$('#model_44_save').off('click').on('click',model_44_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_44_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 韦恩图列表数据保存
 */
function model_44_save(){
	var url = window.ctx + '/statisticalanalysis/model_44_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+44);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_44(expid,page.id,"1");
    		}
        }
    });
};


/**
 * 矩形树图列表
 */
function model_45(expid,id,state){

	if(state == '0'){
		$('#model_45_save').off('click').on('click',model_45_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_45_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 矩形树图列表数据保存
 */
function model_45_save(){
	var url = window.ctx + '/statisticalanalysis/model_45_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+45);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_45(expid,page.id,"1");
    		}
        }
    });
};


/**
 * 树图列表
 */
function model_46(expid,id,state){
	if(state == '0'){
		$('#model_46_save').off('click').on('click',model_46_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_46_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';		
		html+= '</form></div></section></div>';
	    $('.pai-side-right').html(html);
	    //选择字段
		popBox("pai-select-field","createPortGroup-box","880");
		$(".pai-select-field").on("click",function(){
			var thisid=$(this).attr("id");
			var left = "";//左边
			var right = "";//右边
				left+= '<li class="zero-accordion-item">';
				left+= '<div class="ui-checkbox">';
				left+= '<input class="checkBox" type="checkbox" data-name="check-id" name="select_field_id" value="'+"name"+'">';
				left+= '<span class="checkBox">'+"name"+'</span>';
				left+= '</div><span>[<i>'+"string"+'</i>]</span></li>';
			$('.zero-accordion').html(left);
				if(right != ''){
					$('.table-body tbody').removeClass();
					$('.table-body tbody').addClass("check-list sh");
					$('.check-list').html(right);
				}
			if(right == ''){
					right+= '<tr><td>';
					right+= '<span class="icon icon-delete btn-delete"></span></td>';
					right+= '<td class="name">'+"name"+'</td><td class="dataType">'+"string"+'</td>';
					right+= '</tr>';
				$('.table-body tbody').removeClass();
				$('.table-body tbody').addClass("check-list hi");
				
				$('.check-list').html(right);
			}		
			$('.check-list').attr("id",thisid);
			
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * 地图列表数据保存
 */
function model_46_save(){
	var url = window.ctx + '/statisticalanalysis/model_46_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+46);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_46(expid,page.id,"1");
    		}
        }
    });
};


/**
 * 混搭图列表
 */
function model_47(expid,id,state){

	if(state == '0'){
		$('#model_47_save').off('click').on('click',model_47_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_47_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 混搭图列表数据保存
 */
function model_47_save(){
	var url = window.ctx + '/statisticalanalysis/model_47_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+47);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_47(expid,page.id,"1");
    		}
        }
    });
};

/**
 * 字符云图列表
 */
function model_48(expid,id,state){

	if(state == '0'){
		$('#model_48_save').off('click').on('click',model_48_save);
	}
	var url = window.ctx + '/statisticalanalysis/model_48_list';//查询的URL地址
	var data = [//查询的参数
	            {name:'expid',value:expid},
	            {name:'id',value:id},
	            {name:'name',value:$("#choose_field_id").val()},
	];
	$('.table-body').attr("id",id);
	$.get(url, data, function(page){//获取返回结果
		var html = "";
		html+= '<div class="dms-prop-wrap model_1_box"><header class="dms-prop-header has-tab"><ul class="dms-prop-tab"><li class="selected">字段设置</li></ul></header>';
		html+= '<section class="dms-prop-body"><div class="dms-prop-page dms-prop-input"><form><div class="form-group">';
		if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.statisticalanalysisAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		
		html+='</div>';
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
				left+= '<input class="checkBox '+value.name+'_'+value.type+'" type="checkbox" data-name="check-id" name="select_field_id" value="'+value.name+'">';
				left+= '<span class="checkBox">'+value.name+'</span>';
				left+= '</div><span class="data-val">[<i>'+value.type+'</i>]</span></li>';
			});
			$('.zero-accordion').html(left);
			if(page.statisticalanalysisAssemblys!=null&&page.statisticalanalysisAssemblys.length!=0){
					$.each(page.statisticalanalysisAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.characteristicColumn+'_'+"string"+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.characteristicColumn+'</td><td class="dataType">'+"string"+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.characteristicColumn+'_'+"string").prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};


/**
 * 字浮云图列表数据保存
 */
function model_48_save(){
	var url = window.ctx + '/statisticalanalysis/model_48_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+48);
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
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_48(expid,page.id,"1");
    		}
        }
    });
};

// 至折线（面积）图
function setAllAjaxEcharts32(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData32(columns,id);
		}
	});
}
// 至折线（面积）图
function getLinesData32(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/getLines';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts32(result, columns[0]);
		}
	});
}
// 至折线（面积）图
function setEcharts32(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
			},
			tooltip : {
				trigger : ''
			},
			legend : {
				data : [ '' ]
			},
			toolbox : {
				show : true,
				feature : {}
			},
			calculable : true,
			xAxis : [ {
				show : false,
				type : 'category',
				boundaryGap : false,
				data : lineLists[i].secondParame
			} ],
			yAxis : [ {
				show : false,
				type : 'value'
			} ],
			series : [ {
				name : '',
				type : 'line',
				smooth : true,
				itemStyle : {
					normal : {
						areaStyle : {
							type : 'default'
						}
					}
				},
				data : lineLists[i].firstParame
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}

	setAjaxEcharts32(column, lineLists[0]);
}

// 至折线（面积）图
function setAjaxEcharts32(column, result) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '个数' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : result.secondParame
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '个数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : result.firstParame
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 至折线（面积）图
function columnButton32(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '个数' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : [ '' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '个数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ 10 ]
		} ]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/line';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					xAxis : [ {
						data : result.secondParame
					} ],
					series : [ {
						data : result.firstParame
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 至折线（面积）图
function tdButton32(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '个数' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : [ '' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '个数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ 10 ]
		} ]
	};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/line';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id 
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
					},
					xAxis : [ {
						data : result.secondParame
					} ],
					series : [ {
						data : result.firstParame
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}
// 至折线（面积）图
function divButton32(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '个数' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : [ '' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '个数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ 10 ]
		} ]
	};

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/line';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
					},
					xAxis : [ {
						data : result.secondParame
					} ],
					series : [ {
						data : result.firstParame
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 散点气泡图
function columnButton33(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis',
			showDelay : 0,

			axisPointer : {
				show : true,
				type : 'cross',
				lineStyle : {
					type : 'dashed',
					width : 1
				}
			}
		},
		legend : {
			data : [ '数量' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataZoom : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		xAxis : [ {
			type : 'value',
			scale : true,
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [ {
			name : '数量',
			type : 'scatter',
			data : [ [ 161.2, 51.6 ] ],
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		},

		]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/scatter';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},

					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 散点（气泡）图
function setAllAjaxEcharts33(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	
	$.ajax({
		url : urlColumn,
		data : {id : id},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData33(columns,id);
		}
	});
}

// 散点（气泡）图
function getLinesData33(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/getScatters';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data ,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts33(result, columns[0]);
		}
	});
}

// 散点（气泡）图
function setEcharts33(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : ''
			},
			tooltip : {
				trigger : 'axis',
				showDelay : 0,

				axisPointer : {
					show : true,
					type : 'cross',
					lineStyle : {
						type : 'dashed',
						width : 1
					}
				}
			},
			legend : {
				data : [ '' ]
			},
			toolbox : {
				show : true,
				feature : {/*
							 * mark : {show: true}, dataZoom : {show: true},
							 * dataView : {show: true, readOnly: false}, restore :
							 * {show: true}, saveAsImage : {show: true}
							 */}
			},
			xAxis : [ {
				show : false,
				type : 'value',
				scale : true,
				axisLabel : {
					formatter : '{value}'
				}
			} ],
			yAxis : [ {
				show : false,
				type : 'value',
				scale : true,
				axisLabel : {
					formatter : '{value}'
				}
			} ],
			series : [ {
				name : '',
				type : 'scatter',
				data : lineLists[i],
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			},

			]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts33(column, lineLists[0]);
}

// 散点（气泡）图
function setAjaxEcharts33(column, result) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'axis',
			showDelay : 0,

			axisPointer : {
				show : true,
				type : 'cross',
				lineStyle : {
					type : 'dashed',
					width : 1
				}
			}
		},
		legend : {
			data : [ '数量' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataZoom : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		xAxis : [ {
			type : 'value',
			scale : true,
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [ {
			name : '数量',
			type : 'scatter',
			data : result,
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		},

		]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 散点（气泡）图
function divButton33(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis',
			showDelay : 0,

			axisPointer : {
				show : true,
				type : 'cross',
				lineStyle : {
					type : 'dashed',
					width : 1
				}
			}
		},
		legend : {
			data : [ '数量' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataZoom : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		xAxis : [ {
			type : 'value',
			scale : true,
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [ {
			name : '数量',
			type : 'scatter',
			data : [ [ 161.2, 51.6 ] ],
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		},

		]
	};

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/scatter';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},

					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 散点（气泡）图
function tdButton33(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis',
			showDelay : 0,

			axisPointer : {
				show : true,
				type : 'cross',
				lineStyle : {
					type : 'dashed',
					width : 1
				}
			}
		},
		legend : {
			data : [ '数量' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataZoom : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		xAxis : [ {
			type : 'value',
			scale : true,
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [ {
			name : '数量',
			type : 'scatter',
			data : [ [ 161.2, 51.6 ] ],
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		},

		]
	};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/scatter';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},

					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 饼（圆环）图
function columnButton35(buto) {

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	var param = [];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/pie';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					param.push(result[i].name);
				}
				setButtonEcharts35(result, param, columnName);
			}
		}
	});

}

// 饼（圆环）图
function setButtonEcharts35(result, param, columnName) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : columnName,
			subtext : '连续型',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : param
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							x : '25%',
							width : '50%',
							funnelAlign : 'left',
							max : 1548
						}
					}
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		series : [ {
			name : '',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : result
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 饼（圆环）图
function setAjaxEcharts35(column, result) {
	var param = [];
	for (var i = 0; i < result.length; i++) {
		param.push(result[i].name);
	}
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : param
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							x : '25%',
							width : '50%',
							funnelAlign : 'left',
							max : 1548
						}
					}
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		series : [ {
			name : '',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : result
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);

}

// 饼（圆环）图
function setAllAjaxEcharts35(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData35(columns,id);
		}
	});
}

// 饼（圆环）图
function getLinesData35(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/pies';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts35(result, columns[0]);
		}
	});
}
function setEcharts35(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : '',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'left',
				data : [ '', '' ]
			},
			toolbox : {
				show : true,
				feature : {}
			},
			calculable : true,
			series : [ {
				name : '',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : lineLists[i]
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts35(column, lineLists[0]);
}

// 饼（圆环）图
function divButton35(divbott) {
	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/pie';
	var param = [];
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					param.push(result[i].name);
				}
				setDivEcharts35(result, columnName, param);
			}
		}
	});

}

// 饼（圆环）图
function setDivEcharts35(result, columnName, param) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : columnName,
			subtext : '连续型',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : param
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							x : '25%',
							width : '50%',
							funnelAlign : 'left',
							max : 1548
						}
					}
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		series : [ {
			name : '',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : result
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 饼（圆环）图
function tdButton35(tdbott) {
	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/pie';
	var param = [];
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					param.push(result[i].name);
				}
				setTdEcharts35(result, columnName, param);
			}
		}
	});

}

function setTdEcharts35(result, columnName, param) {

	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : columnName,
			subtext : '连续型',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : param
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							x : '25%',
							width : '50%',
							funnelAlign : 'left',
							max : 1548
						}
					}
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		series : [ {
			name : '',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : result
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);

}

// 雷达面积图
function columnButton36(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			x : 'center',
			data : [ '' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		polar : [ {
			indicator : [ {
				text : '最大值',
				max : 1000
			}, {
				text : '最小值',
				max : 1000
			}, {
				text : '平均值',
				max : 1000
			}, {
				text : '个数',
				max : 1000
			}, {
				text : '总和',
				max : 1000
			} ],
			radius : 130
		} ],
		series : [ {
			name : '数据',
			type : 'radar',
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ {
				value : [ 97, 42, 88, 94, 90 ]
			} ]
		} ]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/radar';
	var arrayValue = [];
	var maxValue = 0;
	var sumValue = 0;
	var minValue = 0;
	var countValue = 0;
	var avgValue = 0;

	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				arrayValue.push(result[0].max);
				arrayValue.push(result[0].min);
				arrayValue.push(result[0].avg);
				arrayValue.push(result[0].count);
				arrayValue.push(result[0].sum);
				maxValue = result[0].max + 10;
				sumValue = result[0].sum + 10;
				minValue = result[0].min + 10;
				countValue = result[0].count + 10;
				avgValue = result[0].avg + 10;
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					polar : [ {
						indicator : [ {
							text : '最大值',
							max : maxValue
						}, {
							text : '最小值',
							max : minValue
						}, {
							text : '平均值',
							max : avgValue
						}, {
							text : '个数',
							max : countValue
						}, {
							text : '总和',
							max : sumValue
						} ],
						radius : 130
					} ],
					legend : {
						x : 'center',
						data : [ columnName ]
					},
					series : [ {
						data : [ {
							name : columnName,
							value : arrayValue
						} ]
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 雷达面积图
function setAllAjaxEcharts36(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData36(columns,id);
		}
	});
}

// 雷达面积图
function getLinesData36(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/radars';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts36(result, columns[0]);
		}
	});
}
// 雷达面积图
function setEcharts36(lineList, column) {
	var arrayValue = [];
	var maxValue = 0;
	var sumValue = 0;
	var minValue = 0;
	var countValue = 0;
	var avgValue = 0;

	for (var i = 0; i < lineList.length; i++) {
		arrayValue.push(lineList[i].max);
		arrayValue.push(lineList[i].min);
		arrayValue.push(lineList[i].avg);
		arrayValue.push(lineList[i].count);
		arrayValue.push(lineList[i].sum);
		maxValue = lineList[i].max + 10;
		sumValue = lineList[i].sum + 10;
		minValue = lineList[i].min + 10;
		countValue = lineList[i].count + 10;
		avgValue = lineList[i].avg + 10;
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : ''
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				x : 'center',
				data : [ '' ]
			},
			toolbox : {
				show : true,
				feature : {}
			},
			calculable : true,
			polar : [ {
				indicator : [ {
					text : '最大值',
					max : maxValue
				}, {
					text : '最小值',
					max : minValue
				}, {
					text : '平均值',
					max : avgValue
				}, {
					text : '个数',
					max : countValue
				}, {
					text : '总和',
					max : sumValue
				} ],
				radius : 130
			} ],
			series : [ {
				name : '完全实况球员数据',
				type : 'radar',
				itemStyle : {
					normal : {
						areaStyle : {
							type : 'default'
						}
					}
				},
				data : [ {
					value : arrayValue,
					name : '字段'
				} ]
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts36(lineList[0], column);
}

// 雷达面积图
function setAjaxEcharts36(result, column) {

	var arrayValue = [];
	var maxValue = 0;
	var sumValue = 0;
	var minValue = 0;
	var countValue = 0;
	var avgValue = 0;
	arrayValue.push(result.max);
	arrayValue.push(result.min);
	arrayValue.push(result.avg);
	arrayValue.push(result.count);
	arrayValue.push(result.sum);
	maxValue = result.max + 10;
	sumValue = result.sum + 10;
	minValue = result.min + 10;
	countValue = result.count + 10;
	avgValue = result.avg + 10;

	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			x : 'center',
			data : [ column ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		polar : [ {
			indicator : [ {
				text : '最大值',
				max : maxValue
			}, {
				text : '最小值',
				max : minValue
			}, {
				text : '平均值',
				max : avgValue
			}, {
				text : '个数',
				max : countValue
			}, {
				text : '总和',
				max : sumValue
			} ],
			radius : 130
		} ],
		series : [ {
			name : '数据',
			type : 'radar',
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ {
				name : column,
				value : arrayValue
			} ]
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 雷达（面积）图
function divButton36(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			x : 'center',
			data : [ '' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		polar : [ {
			indicator : [ {
				text : '最大值',
				max : 1000
			}, {
				text : '最小值',
				max : 1000
			}, {
				text : '平均值',
				max : 1000
			}, {
				text : '个数',
				max : 1000
			}, {
				text : '总和',
				max : 1000
			} ],
			radius : 130
		} ],
		series : [ {
			name : '数据',
			type : 'radar',
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ {
				value : [ 97, 42, 88, 94, 90 ]
			} ]
		} ]
	};

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/radar';
	var arrayValue = [];
	var maxValue = 0;
	var sumValue = 0;
	var minValue = 0;
	var countValue = 0;
	var avgValue = 0;

	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					arrayValue.push(result[i].max);
					arrayValue.push(result[i].min);
					arrayValue.push(result[i].avg);
					arrayValue.push(result[i].count);
					arrayValue.push(result[i].sum);
					maxValue = result[i].max + 10;
					sumValue = result[i].sum + 10;
					minValue = result[i].min + 10;
					countValue = result[i].count + 10;
					avgValue = result[i].avg + 10;

				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					polar : [ {
						indicator : [ {
							text : '最大值',
							max : maxValue
						}, {
							text : '最小值',
							max : minValue
						}, {
							text : '平均值',
							max : avgValue
						}, {
							text : '个数',
							max : countValue
						}, {
							text : '总和',
							max : sumValue
						} ],
						radius : 130
					} ],
					legend : {
						x : 'center',
						data : [ columnName ]
					},
					series : [ {
						data : [ {
							name : columnName,
							value : arrayValue
						} ]
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 雷达（面积）图
function tdButton36(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '罗纳尔多 vs 舍普琴科',
			subtext : '完全实况球员数据'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			x : 'center',
			data : [ '' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		polar : [ {
			indicator : [ {
				text : '最大值',
				max : 1000
			}, {
				text : '最小值',
				max : 1000
			}, {
				text : '平均值',
				max : 1000
			}, {
				text : '个数',
				max : 1000
			}, {
				text : '总和',
				max : 1000
			} ],
			radius : 130
		} ],
		series : [ {
			name : '数据',
			type : 'radar',
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ {
				value : [ 97, 42, 88, 94, 90 ]
			} ]
		} ]
	};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/radar';
	var arrayValue = [];
	var maxValue = 0;
	var sumValue = 0;
	var minValue = 0;
	var countValue = 0;
	var avgValue = 0;
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					arrayValue.push(result[i].max);
					arrayValue.push(result[i].min);
					arrayValue.push(result[i].avg);
					arrayValue.push(result[i].count);
					arrayValue.push(result[i].sum);
					maxValue = result[i].max + 10;
					sumValue = result[i].sum + 10;
					minValue = result[i].min + 10;
					countValue = result[i].count + 10;
					avgValue = result[i].avg + 10;

				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					polar : [ {
						indicator : [ {
							text : '最大值',
							max : maxValue
						}, {
							text : '最小值',
							max : minValue
						}, {
							text : '平均值',
							max : avgValue
						}, {
							text : '个数',
							max : countValue
						}, {
							text : '总和',
							max : sumValue
						} ],
						radius : 130
					} ],
					legend : {
						x : 'center',
						data : [ columnName ]
					},
					series : [ {
						data : [ {
							name : columnName,
							value : arrayValue
						} ]
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 力导向分布图
function columnButton38(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params) {
				if (params.indicator2) { // is edge
					return params.value.weight;
				} else {// is node
					return params.name
				}
			}
		},
		toolbox : {
			show : true,
			feature : {
				restore : {
					show : true
				},
				magicType : {
					show : true,
					type : [ 'force', 'chord' ]
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			x : 'left',
			data : [ '第一组', '第二组', '第三组', '第四组' ]
		},
		series : [ {
			type : 'force',
			sort : 'ascending',
			sortSub : 'descending',
			showScale : true,
			showScaleText : true,
			data : [ {
				name : '第一组'
			}, {
				name : '第二组'
			}, {
				name : '第三组'
			}, {
				name : '第四组'
			} ],
			itemStyle : {
				normal : {
					label : {
						show : false
					}
				}
			},
			matrix : [ [ 11975, 5871, 8916, 2868 ],
					[ 1951, 10048, 2060, 6171 ], [ 8010, 16145, 8090, 8045 ],
					[ 1013, 990, 940, 6907 ] ]
		} ]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/force';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						matrix : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 里导向分布图
function setAllAjaxEcharts38(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData38(columns,id);
		}
	});
}

// 里导向分布图
function getLinesData38(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/forces';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts38(result, columns[0]);
		}
	});
}

// 里导向分布图
function setEcharts38(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : '',
				x : 'right',
				y : 'bottom'
			},
			tooltip : {
				trigger : 'item',
				formatter : function(params) {
					if (params.indicator2) { // is edge
						return params.value.weight;
					} else {// is node
						return params.name
					}
				}
			},
			toolbox : {
				show : true,
				feature : {}
			},
			legend : {
				x : 'left',
				data : [ '', '', '', '' ]
			},
			series : [ {
				type : 'force',
				sort : 'ascending',
				sortSub : 'descending',
				showScale : true,
				showScaleText : true,
				data : [ {
					name : '第一组'
				}, {
					name : '第二组'
				}, {
					name : '第三组'
				}, {
					name : '第四组'
				} ],
				itemStyle : {
					normal : {
						label : {
							show : false
						}
					}
				},
				matrix : lineLists[i]
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts38(column, lineLists[0]);
}

// 里导向分布图
function setAjaxEcharts38(column, result) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params) {
				if (params.indicator2) { // is edge
					return params.value.weight;
				} else {// is node
					return params.name
				}
			}
		},
		toolbox : {
			show : true,
			feature : {
				restore : {
					show : true
				},
				magicType : {
					show : true,
					type : [ 'force', 'chord' ]
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			x : 'left',
			data : [ '第一组', '第二组', '第三组', '第四组' ]
		},
		series : [ {
			type : 'force',
			sort : 'ascending',
			sortSub : 'descending',
			showScale : true,
			showScaleText : true,
			data : [ {
				name : '第一组'
			}, {
				name : '第二组'
			}, {
				name : '第三组'
			}, {
				name : '第四组'
			} ],
			itemStyle : {
				normal : {
					label : {
						show : false
					}
				}
			},
			matrix : result
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 里导向分布图
function divButton38(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params) {
				if (params.indicator2) { // is edge
					return params.value.weight;
				} else {// is node
					return params.name
				}
			}
		},
		toolbox : {
			show : true,
			feature : {
				restore : {
					show : true
				},
				magicType : {
					show : true,
					type : [ 'force', 'chord' ]
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			x : 'left',
			data : [ '第一组', '第二组', '第三组', '第四组' ]
		},
		series : [ {
			type : 'force',
			sort : 'ascending',
			sortSub : 'descending',
			showScale : true,
			showScaleText : true,
			data : [ {
				name : '第一组'
			}, {
				name : '第二组'
			}, {
				name : '第三组'
			}, {
				name : '第四组'
			} ],
			itemStyle : {
				normal : {
					label : {
						show : false
					}
				}
			},
			matrix : [ [ 11975, 5871, 8916, 2868 ],
					[ 1951, 10048, 2060, 6171 ], [ 8010, 16145, 8090, 8045 ],
					[ 1013, 990, 940, 6907 ] ]
		} ]
	};

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/force';
	var param = [];

	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						matrix : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 里导向分布图
function tdButton38(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params) {
				if (params.indicator2) { // is edge
					return params.value.weight;
				} else {// is node
					return params.name
				}
			}
		},
		toolbox : {
			show : true,
			feature : {
				restore : {
					show : true
				},
				magicType : {
					show : true,
					type : [ 'force', 'chord' ]
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			x : 'left',
			data : [ '第一组', '第二组', '第三组', '第四组' ]
		},
		series : [ {
			type : 'force',
			sort : 'ascending',
			sortSub : 'descending',
			showScale : true,
			showScaleText : true,
			data : [ {
				name : '第一组'
			}, {
				name : '第二组'
			}, {
				name : '第三组'
			}, {
				name : '第四组'
			} ],
			itemStyle : {
				normal : {
					label : {
						show : false
					}
				}
			},
			matrix : [ [ 11975, 5871, 8916, 2868 ],
					[ 1951, 10048, 2060, 6171 ], [ 8010, 16145, 8090, 8045 ],
					[ 1013, 990, 940, 6907 ] ]
		} ]
	};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/force';
	var param = [];
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						matrix : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 图柱状(条形)图
function columnButton39(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '总数' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : [ '1月' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '总数',
			type : 'bar',
			data : [ 2.0 ],
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		} ]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var id = $(buto).attr('title');

	var columnName = $("#" + buttonID).html();
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/bar';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					xAxis : [ {
						data : result.secondParame
					} ],
					series : [ {
						data : result.firstParame
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 图柱状(条形)图
function setAllAjaxEcharts39(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData39(columns,id);
			
		}
	});
}
// 图柱状(条形)图
function getLinesData39(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/getBars';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : false, // 异步请求
//		dataType : "json",
		success : function(result) {
			setEcharts39(result, columns[0]);
		}
	});
}
// 图柱状(条形)图
function setEcharts39(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : ''
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '' ]
			},
			toolbox : {
				show : true,
				feature : {
				}
			},
			calculable : true,
			xAxis : [ {
				show : false,
				type : 'category',
				data : lineLists[i].secondParame,
			} ],
			yAxis : [ {
				show : false,
				type : 'value'
			} ],
			series : [ {
				name : '总数',
				type : 'bar',
				data : lineLists[i].firstParame,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts39(column, lineLists[0]);
}

// 图柱状(条形)图
function setAjaxEcharts39(column, result) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '总数' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : result.secondParame
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '总数',
			type : 'bar',
			data : result.firstParame,
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 图柱状(条形)图
function divButton39(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '总数' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : [ '1' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '总数',
			type : 'bar',
			data : [ 2.0 ],
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		} ]
	};

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/bar';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
					},
					xAxis : [ {
						data : result.secondParame
					} ],
					series : [ {
						data : result.firstParame
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 图柱状(条形)图
function tdButton39(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '总数' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : [ '1' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '总数',
			type : 'bar',
			data : [ 2.0 ],
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		} ]
	};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/bar';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
					},
					xAxis : [ {
						data : result.secondParame
					} ],
					series : [ {
						data : result.firstParame
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 仪表盘图
function columnButton40(buto) {
	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/gauge';
	var arrayValue = [];
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setColumnButton40(columnName,result);
			}
		}
	});
	
}

//仪表盘图
function setColumnButton40(columnName,result){
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	var intParam = parseFloat(result[0].para);
	option = {
			title : {
				text : columnName,
				subtext : '连续型'
			},
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    series : [
		        {
		            name:columnName,
		            type:'gauge',
		            detail : {formatter:'{value}%'},
		            data:[{value: 50, name: '平均率'}]
		        }
		    ]
		};
		var timeTicket = 0;
		clearInterval(timeTicket);
		timeTicket = setInterval(function (){
		    option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0*intParam;
		    mychart.hideLoading();// 隐藏
		    // 3.载入Echarts图标
		    mychart.setOption(option, true);
		},2000);
		                    
}

// 仪表盘图
function setAllAjaxEcharts40(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData40(columns,id);
		}
	});
}
// 仪表盘图
function getLinesData40(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/gauges';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts40(result, columns[0]);
		}
	});
}
// 仪表盘图
function setEcharts40(lineLists, column) {

	for (var i = 0; i < lineLists.length; i++) {
		var result = lineLists[i];
		var intParam = parseFloat(result[i].para*100);
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		mychart.showLoading();
		option = {
			    tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    toolbox: {
			        show : true,
			        feature : {}
			    },
			    series : [
			        {
			            name:column,
			            type:'gauge',
			            detail : {formatter:'{value}%'},
			            data:[{value: intParam, name: ''}]
			        }
			    ]
			};

			    mychart.hideLoading();// 隐藏
			    // 3.载入Echarts图标
			    mychart.setOption(option, true);
	}
	setAjaxEcharts40(lineLists[0], column);
}

// 仪表盘图
function setAjaxEcharts40(result, column) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	var intParam = parseFloat(result[0].para);
	// 折线面积图
	option = {
			title : {
				text : column,
				subtext : '连续型'
			},
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    series : [
		        {
		            name:column,
		            type:'gauge',
		            detail : {formatter:'{value}%'},
		            data:[{value: 50, name: '平均率'}]
		        }
		    ]
		};
		var timeTicket = 0;
		clearInterval(timeTicket);
		timeTicket = setInterval(function (){
		    option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0*intParam;
		    mychart.hideLoading();// 隐藏
		    // 3.载入Echarts图标
		    mychart.setOption(option, true);
		},2000);
}
// 仪表盘图
function divButton40(divbott) {
	
	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/gauge';

	
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setDivButton40(columnName,result);
			}
		}
	});
}

//仪表盘图
function setDivButton40(columnName,result){
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	var intParam = parseFloat(result[0].para);
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	// 折线面积图
	option = {
			title : {
				text : columnName,
				subtext : '连续型'
			},
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    series : [
		        {
		            name:columnName,
		            type:'gauge',
		            detail : {formatter:'{value}%'},
		            data:[{value: 50, name: '平均率'}]
		        }
		    ]
		};
		var timeTicket = 0;
		clearInterval(timeTicket);
		timeTicket = setInterval(function (){
		    option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0*intParam;
		    mychart.hideLoading();// 隐藏
		    // 3.载入Echarts图标
		    mychart.setOption(option, true);
		},2000);
}
// 仪表盘图
function tdButton40(tdbott) {
	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/gauge';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setTdButton40(result,columnName);
			}
		}
	});

}

function setTdButton40(result,columnName){
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	var intParam = parseFloat(result[0].para);
	// 折线面积图
	option = {
			title : {
				text : columnName,
				subtext : '连续型'
			},
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    series : [
		        {
		            name:columnName,
		            type:'gauge',
		            detail : {formatter:'{value}%'},
		            data:[{value: 50, name: '平均率'}]
		        }
		    ]
		};
		var timeTicket = 0;
		clearInterval(timeTicket);
		timeTicket = setInterval(function (){
		    option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0*intParam;
		    mychart.hideLoading();// 隐藏
		    // 3.载入Echarts图标
		    mychart.setOption(option, true);
		},2000);
}


// 漏斗图
function columnButton41(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c}%"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			data : []
		},
		calculable : true,
		series : [ {
			name : '漏斗图',
			type : 'funnel',
			width : '40%',
			data : [ {
				value : 60,
				name : ''
			} ]
		} ]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	var param = [];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/funnel';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					param.push(result[i].name);
				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					legend : {
						data : param
					},
					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 漏斗图
function setAllAjaxEcharts41(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData41(columns,id);
		}
	});
}
// 漏斗图
function getLinesData41(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/funnels';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts41(result, columns[0]);
		}
	});
}
// 漏斗图
function setEcharts41(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : ''
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c}%"
			},
			toolbox : {
				show : true,
				feature : {/*
							 * mark : { show : true }, dataView : { show : true,
							 * readOnly : false }, restore : { show : true },
							 * saveAsImage : { show : true }
							 */}
			},
			legend : {
				data : []
			},
			calculable : true,
			series : [ {
				name : '漏斗图',
				type : 'funnel',
				width : '40%',
				data : lineLists[i]
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts41(column, lineLists[0]);
}

// 漏斗图
function setAjaxEcharts41(column, result) {
	var param = [];
	for (var i = 0; i < result.length; i++) {
		param.push(result[i].name);
	}
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c}%"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			data : param
		},
		calculable : true,
		series : [ {
			name : '漏斗图',
			type : 'funnel',
			width : '40%',
			data : result
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 漏斗图
function divButton41(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c}%"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			data : []
		},
		calculable : true,
		series : [ {
			name : '漏斗图',
			type : 'funnel',
			width : '40%',
			data : [ {
				value : 60,
				name : ''
			} ]
		} ]
	};

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/funnel';
	var param = [];

	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					param.push(result[i].name);
				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					legend : {
						data : param
					},
					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}
// 漏斗图
function tdButton41(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c}%"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			data : []
		},
		calculable : true,
		series : [ {
			name : '漏斗图',
			type : 'funnel',
			width : '40%',
			data : [ {
				value : 60,
				name : ''
			} ]
		} ]
	};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/funnel';
	var param = [];
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					param.push(result[i].name);
				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					legend : {
						data : param
					},
					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 热力图
function columnButton42(buto) {
	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/heatmap';

	// 在数据为动态加载之前建筑loading动画

	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setButtonDate42(result, columnName);
			}
		}
	});
}

// 热力图
function setButtonDate42(result, columnName) {
	var maxVal;
	var minVal;
	var avgVal;
	maxVal = result[0].maxValue + 100;
	minVal = result[0].minValue + 20;
	avgVal = result[0].countValue + 200;
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	var heatData = [];
	for (var i = 0; i < minVal; ++i) {
		heatData.push([ 400 + Math.random() * 300, 5 + Math.random() * 10,
				Math.random() ]);
	}
	for (var i = 0; i < maxVal; ++i) {
		heatData.push([ 100 + Math.random() * 600, 150 + Math.random() * 50,
				Math.random() ]);
	}
	for (var i = 0; i < avgVal; ++i) {
		heatData.push([ Math.random() * 1000, Math.random() * 800,
				Math.random() * 0.5 ]);
	}
	option = {
		title : {
			text : '热力图'
		},
		series : [ {
			type : 'heatmap',
			data : heatData,
			hoverable : false
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 热力图
function setAllAjaxEcharts42(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData42(columns,id);
		}
	});
}
// 热力图
function getLinesData42(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/heatmaps';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts42(result);
		}
	});
}
// 热力图
function setEcharts42(lineLists) {
	for (var i = 0; i < lineLists.length; i++) {
		setbtEcharts42(lineLists[i], i);
	}
	setAjaxEcharts42(lineLists[0]);
}
// 热力图
function setbtEcharts42(result, i) {
	var maxVal;
	var minVal;
	var avgVal;
	maxVal = result[0].maxValue + 100;
	minVal = result[0].minValue + 20;
	avgVal = result[0].countValue + 200;
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy" + i));
	// 折线面积图
	var heatData = [];
	for (var j = 0; j < minVal; ++j) {
		heatData.push([ 400 + Math.random() * 300, 5 + Math.random() * 10,
				Math.random() ]);
	}
	for (var j = 0; j < maxVal; ++j) {
		heatData.push([ 100 + Math.random() * 600, 150 + Math.random() * 50,
				Math.random() ]);
	}
	for (var j = 0; j < avgVal; ++j) {
		heatData.push([ Math.random() * 1000, Math.random() * 800,
				Math.random() * 0.5 ]);
	}
	option = {
		title : {
			text : '热力图'
		},
		series : [ {
			type : 'heatmap',
			data : heatData,
			hoverable : false
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 热力图
function setAjaxEcharts42(result) {
	var maxVal;
	var minVal;
	var avgVal;
	maxVal = result[0].maxValue + 100;
	minVal = result[0].minValue + 20;
	avgVal = result[0].countValue + 200;
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	var heatData = [];
	for (var i = 0; i < minVal; ++i) {
		heatData.push([ 400 + Math.random() * 300, 5 + Math.random() * 10,
				Math.random() ]);
	}
	for (var i = 0; i < maxVal; ++i) {
		heatData.push([ 100 + Math.random() * 600, 150 + Math.random() * 50,
				Math.random() ]);
	}
	for (var i = 0; i < avgVal; ++i) {
		heatData.push([ Math.random() * 1000, Math.random() * 800,
				Math.random() * 0.5 ]);
	}
	option = {
		title : {
			text : '热力图'
		},
		series : [ {
			type : 'heatmap',
			data : heatData,
			hoverable : false
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 热力图
function divButton42(divbott) {
	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/heatmap';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setdivButtonDate42(result, columnName);
			}
		}
	});
}

// 热力图
function setdivButtonDate42(result, columnName) {
	var maxVal;
	var minVal;
	var avgVal;
	maxVal = result[0].maxValue + 100;
	minVal = result[0].minValue + 20;
	avgVal = result[0].countValue + 200;
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	var heatData = [];
	for (var i = 0; i < minVal; ++i) {
		heatData.push([ 400 + Math.random() * 300, 5 + Math.random() * 10,
				Math.random() ]);
	}
	for (var i = 0; i < maxVal; ++i) {
		heatData.push([ 100 + Math.random() * 600, 150 + Math.random() * 50,
				Math.random() ]);
	}
	for (var i = 0; i < avgVal; ++i) {
		heatData.push([ Math.random() * 1000, Math.random() * 800,
				Math.random() * 0.5 ]);
	}
	option = {
		title : {
			text : '热力图'
		},
		series : [ {
			type : 'heatmap',
			data : heatData,
			hoverable : false
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 热力图
function tdButton42(tdbott) {

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/heatmap';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				settdButtonDate42(result, columnName);
			}
		}
	});
}
// 热力图
function settdButtonDate42(result, columnName) {
	var maxVal;
	var minVal;
	var avgVal;
	maxVal = result[0].maxValue + 100;
	minVal = result[0].minValue + 20;
	avgVal = result[0].countValue + 200;
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	var heatData = [];
	for (var i = 0; i < minVal; ++i) {
		heatData.push([ 400 + Math.random() * 300, 5 + Math.random() * 10,
				Math.random() ]);
	}
	for (var i = 0; i < maxVal; ++i) {
		heatData.push([ 100 + Math.random() * 600, 150 + Math.random() * 50,
				Math.random() ]);
	}
	for (var i = 0; i < avgVal; ++i) {
		heatData.push([ Math.random() * 1000, Math.random() * 800,
				Math.random() * 0.5 ]);
	}
	option = {
		title : {
			text : '热力图'
		},
		series : [ {
			type : 'heatmap',
			data : heatData,
			hoverable : false
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 韦恩图
function columnButton44(buto) {
	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');

	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/venn';

	// 在数据为动态加载之前建筑loading动画

	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setButtonDate44(result, columnName);

			}
		}
	});

}

// 韦恩图
function setButtonDate44(result, columnName) {
	var maxVal;
	var minVal;
	var avgVal;
	maxVal = result[0].maxValue;
	minVal = result[0].minValue;
	avgVal = result[0].avgValue;
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : columnName,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c}"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : false,
		series : [ {
			name : '韦恩图',
			type : 'venn',
			itemStyle : {
				normal : {
					label : {
						show : true,
						textStyle : {
							fontFamily : 'Arial, Verdana, sans-serif',
							fontSize : 16,
							fontStyle : 'italic',
							fontWeight : 'bolder'
						}
					},
					labelLine : {
						show : false,
						length : 10,
						lineStyle : {
							// color: 各异,
							width : 1,
							type : 'solid'
						}
					}
				},
				emphasis : {
					color : '#cc99cc',
					borderWidth : 3,
					borderColor : '#996699'
				}
			},
			data : [ {
				value : maxVal,
				name : 'max'
			}, {
				value : avgVal,
				name : 'avg'
			}, {
				value : minVal,
				name : 'min'
			} ]
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 韦恩图
function setAllAjaxEcharts44(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData44(columns,id);
		}
	});
}
// 韦恩图
function getLinesData44(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/venns';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts44(result, columns[0]);
		}
	});
}
// 韦恩图
function setEcharts44(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		var result = lineLists[i];
		var maxVal;
		var minVal;
		var avgVal;
		maxVal = result[i].maxValue;
		minVal = result[i].minValue;
		avgVal = result[i].avgValue;
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : ''
			},
			tooltip : {
				trigger : 'item',
				formatter : "{b}: {c}"
			},
			toolbox : {
				show : true,
				feature : {/*
							 * mark : {show: true}, dataView : {show: true,
							 * readOnly: false}, restore : {show: true},
							 * saveAsImage : {show: true}
							 */}
			},
			calculable : false,
			series : [ {
				name : '韦恩图',
				type : 'venn',
				itemStyle : {
					normal : {
						label : {
							show : true,
							textStyle : {
								fontFamily : 'Arial, Verdana, sans-serif',
								fontSize : 16,
								fontStyle : 'italic',
								fontWeight : 'bolder'
							}
						},
						labelLine : {
							show : false,
							length : 10,
							lineStyle : {
								// color: 各异,
								width : 1,
								type : 'solid'
							}
						}
					},
					emphasis : {
						color : '#cc99cc',
						borderWidth : 3,
						borderColor : '#996699'
					}
				},
				data : [ {
					value : maxVal,
					name : 'max'
				}, {
					value : avgVal,
					name : 'avg'
				}, {
					value : minVal,
					name : 'min'
				} ]
			} ]

		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts44(column, lineLists[0]);
}

// 韦恩图
function setAjaxEcharts44(column, result) {

	var maxVal;
	var minVal;
	var avgVal;
	maxVal = result[0].maxValue;
	minVal = result[0].minValue;
	avgVal = result[0].avgValue;
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c}"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : false,
		series : [ {
			name : '韦恩图',
			type : 'venn',
			itemStyle : {
				normal : {
					label : {
						show : true,
						textStyle : {
							fontFamily : 'Arial, Verdana, sans-serif',
							fontSize : 16,
							fontStyle : 'italic',
							fontWeight : 'bolder'
						}
					},
					labelLine : {
						show : false,
						length : 10,
						lineStyle : {
							// color: 各异,
							width : 1,
							type : 'solid'
						}
					}
				},
				emphasis : {
					color : '#cc99cc',
					borderWidth : 3,
					borderColor : '#996699'
				}
			},
			data : [ {
				value : maxVal,
				name : 'max'
			}, {
				value : avgVal,
				name : 'avg'
			}, {
				value : minVal,
				name : 'min'
			} ]
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);

}

// 韦恩图
function divButton44(divbott) {
	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/venn';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setdivButtonDate44(result, columnName);
			}
		}
	});
}

// 韦恩图
function setdivButtonDate44(result, columnName) {
	var maxVal;
	var minVal;
	var avgVal;
	maxVal = result[0].maxValue;
	minVal = result[0].minValue;
	avgVal = result[0].avgValue;
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : columnName,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c}"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : false,
		series : [ {
			name : '韦恩图',
			type : 'venn',
			itemStyle : {
				normal : {
					label : {
						show : true,
						textStyle : {
							fontFamily : 'Arial, Verdana, sans-serif',
							fontSize : 16,
							fontStyle : 'italic',
							fontWeight : 'bolder'
						}
					},
					labelLine : {
						show : false,
						length : 10,
						lineStyle : {
							// color: 各异,
							width : 1,
							type : 'solid'
						}
					}
				},
				emphasis : {
					color : '#cc99cc',
					borderWidth : 3,
					borderColor : '#996699'
				}
			},
			data : [ {
				value : maxVal,
				name : 'max'
			}, {
				value : avgVal,
				name : 'avg'
			}, {
				value : minVal,
				name : 'min'
			} ]
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 韦恩图
function tdButton44(tdbott) {
	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/venn';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				settdButtonDate44(result, columnName);
			}
		}
	});
}
// 韦恩图
function settdButtonDate44(result, columnName) {
	var maxVal;
	var minVal;
	var avgVal;
	maxVal = result[0].maxValue;
	minVal = result[0].minValue;
	avgVal = result[0].avgValue;
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : columnName,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c}"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : false,
		series : [ {
			name : '韦恩图',
			type : 'venn',
			itemStyle : {
				normal : {
					label : {
						show : true,
						textStyle : {
							fontFamily : 'Arial, Verdana, sans-serif',
							fontSize : 16,
							fontStyle : 'italic',
							fontWeight : 'bolder'
						}
					},
					labelLine : {
						show : false,
						length : 10,
						lineStyle : {
							// color: 各异,
							width : 1,
							type : 'solid'
						}
					}
				},
				emphasis : {
					color : '#cc99cc',
					borderWidth : 3,
					borderColor : '#996699'
				}
			},
			data : [ {
				value : maxVal,
				name : 'max'
			}, {
				value : avgVal,
				name : 'avg'
			}, {
				value : minVal,
				name : 'min'
			} ]
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 矩形树图
function columnButton45(buto) {
	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/rectangle';

	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setButtonEcharts45(columnName, result);
			}
		}
	});

}
// 矩形树图
function setButtonEcharts45(columnName, result) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : columnName,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c}"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : false,
		series : [ {
			name : '矩形图',
			type : 'treemap',
			itemStyle : {
				normal : {
					label : {
						show : true,
						formatter : "{b}"
					},
					borderWidth : 1
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : result
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 矩形树图
function setAllAjaxEcharts45(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData45(columns,id);
		}
	});
}
// 矩形树图
function getLinesData45(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/rectangles';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts45(result, columns[0]);
		}
	});
}
// 矩形树图
function setEcharts45(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : ''
			},
			tooltip : {
				trigger : 'item',
				formatter : "{b}: {c}"
			},
			toolbox : {
				show : true,
				feature : {/*
							 * mark : {show: true}, dataView : {show: true,
							 * readOnly: false}, restore : {show: true},
							 * saveAsImage : {show: true}
							 */}
			},
			calculable : false,
			series : [ {
				name : '',
				type : 'treemap',
				itemStyle : {
					normal : {
						label : {
							show : true,
							formatter : "{b}"
						},
						borderWidth : 1
					},
					emphasis : {
						label : {
							show : true
						}
					}
				},
				data : lineLists[i]
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts45(column, lineLists[0]);
}

// 矩形树图
function setAjaxEcharts45(column, result) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c}"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : false,
		series : [ {
			name : '矩形图',
			type : 'treemap',
			itemStyle : {
				normal : {
					label : {
						show : true,
						formatter : "{b}"
					},
					borderWidth : 1
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : result
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}
// 矩形树图
function divButton45(divbott) {

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/rectangle';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setDivEcharts45(columnName, result);
			}
		}
	});

}
// 矩形树图
function setDivEcharts45(columnName, result) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : columnName,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c}"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : false,
		series : [ {
			name : '矩形图',
			type : 'treemap',
			itemStyle : {
				normal : {
					label : {
						show : true,
						formatter : "{b}"
					},
					borderWidth : 1
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : result
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 矩形树图
function tdButton45(tdbott) {
	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs=column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/rectangle';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (columnName != null) {
				setTdEcharts45(columnName, result);
			}
		}
	});

}
// 矩形树图
function setTdEcharts45(columnName, result) {

	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : columnName,
			subtext : '连续型'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c}"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : false,
		series : [ {
			name : '矩形图',
			type : 'treemap',
			itemStyle : {
				normal : {
					label : {
						show : true,
						formatter : "{b}"
					},
					borderWidth : 1
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : result
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 字符云
function columnButton48(buto) {
	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var id = $(buto).attr('title');
	var columnName = $("#" + buttonID).html();
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/wordCloud';

	// 在数据为动态加载之前建筑loading动画

	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setButtonDate48(result, columnName);
			}
		}
	});
}

// 字符云
function setButtonDate48(result, columnName) {
	var minVal = result[0].minValue + 500;
	var maxVal = result[0].maxValue + 500;
	var countVal = result[0].countValue + 500;
	var avgVal = result[0].avgValue + 500;
	var sumVal = result[0].sumValue + 500;
	var hypotVal = result[0].hypotValue + 500;
	var cbrtVal = result[0].cbrtValue + 500;
	var sinVal = result[0].sinValue + 500;	
	var cosVal = result[0].cosValue + 500;
	var tanVal = result[0].tanValue + 500;
	var asinVal = result[0].asinValue + 500;
	var acosVal = result[0].acosValue + 500;
	var atanVal = result[0].atanValue + 500;
	var toDegreesVal = result[0].toDegreesValue + 500;
	var toRadiansVal = result[0].toRadiansValue + 500;
	var avgcbrtVal = result[0].avgcbrtValue + 500;
	var avgsinVal = result[0].avgsinValue + 500;
	var avgcosVal = result[0].avgcosValue + 500;
	var avgtanVal = result[0].avgtanValue + 500;
	var avgasinVal = result[0].avgasinValue + 500;
	var avgacosVal = result[0].avgacosValue + 500;
	var avgatanVal = result[0].avgatanValue + 500;
	var avgtoDegreesVal = result[0].avgtoDegreesValue + 500;
	var avgtoRadiansVal = result[0].avgtoRadiansValue + 500;
	

	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图

	function createRandomItemStyle() {
		return {
			normal : {
				color : 'rgb('
						+ [ Math.round(Math.random() * 160),
								Math.round(Math.random() * 160),
								Math.round(Math.random() * 160) ].join(',')
						+ ')'
			}
		};
	}

	option = {
		title : {
			text : columnName,
		},
		tooltip : {
			show : true
		},
		series : [ {
			name : columnName,
			type : 'wordCloud',
			size : [ '80%', '80%' ],
			textRotation : [ 0, 45, 90, -45 ],
			textPadding : 0,
			autoSize : {
				enable : true,
				minSize : 14
			},
			data : [ {
				name : columnName,
				value : 1000,
				itemStyle : {
					normal : {
						color : 'black'
					}
				}
			}, {
				name : "Min",
				value : minVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Max",
				value : maxVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Count",
				value : countVal,
				itemStyle : createRandomItemStyle()
			},  {
				name : "Avg",
				value : avgVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sum",
				value : sumVal,
				itemStyle : createRandomItemStyle()
			} ,{
				name : "Hypot",
				value :hypotVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt",
				value :cbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Max",
				value :sinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Max",
				value :cosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Max",
				value :tanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Max",
				value :asinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Max",
				value :acosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Max",
				value :atanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Max",
				value :toDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Max",
				value :toRadiansVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt Avg",
				value :avgcbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Avg",
				value :avgsinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Avg",
				value :avgcosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Avg",
				value :avgtanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Avg",
				value :avgasinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Avg",
				value :avgacosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Avg",
				value :avgatanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Avg",
				value :avgtoDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Avg",
				value :avgtoRadiansVal,
				itemStyle : createRandomItemStyle()
			}	
			]
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 字符云
function setAllAjaxEcharts48(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData48(columns,id);
		}
	});
}
// 字符云
function getLinesData48(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/wordClouds';
	data = [//查询的参数
            {name:'columnArray',value:columns},
            {name:'id',value:id},
];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts48(result, columns[0]);
		}
	});
}
// 字符云
function setEcharts48(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		setForEcharts48(lineLists[i], i);
	}
	setAjaxEcharts48(lineLists[0], column);
}
// 字符云
function setForEcharts48(result, i) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy" + i));
	var minVal = result.minValue + 500;
	var maxVal = result.maxValue + 500;
	var countVal = result.countValue + 500;
	var avgVal = result.avgValue + 500;
	var sumVal = result.sumValue + 500;
	var hypotVal = result.hypotValue + 500;
	var cbrtVal = result.cbrtValue + 500;
	var sinVal = result.sinValue + 500;	
	var cosVal = result.cosValue + 500;
	var tanVal = result.tanValue + 500;
	var asinVal = result.asinValue + 500;
	var acosVal = result.acosValue + 500;
	var atanVal = result.atanValue + 500;
	var toDegreesVal = result.toDegreesValue + 500;
	var toRadiansVal = result.toRadiansValue + 500;
	var avgcbrtVal = result.avgcbrtValue + 500;
	var avgsinVal = result.avgsinValue + 500;
	var avgcosVal = result.avgcosValue + 500;
	var avgtanVal = result.avgtanValue + 500;
	var avgasinVal = result.avgasinValue + 500;
	var avgacosVal = result.avgacosValue + 500;
	var avgatanVal = result.avgatanValue + 500;
	var avgtoDegreesVal = result.avgtoDegreesValue + 500;
	var avgtoRadiansVal = result.avgtoRadiansValue + 500;
	

	// 折线面积图

	function createRandomItemStyle() {
		return {
			normal : {
				color : 'rgb('
						+ [ Math.round(Math.random() * 160),
								Math.round(Math.random() * 160),
								Math.round(Math.random() * 160) ].join(',')
						+ ')'
			}
		};
	}

	option = {
		title : {
			text : '',
		},
		tooltip : {
			show : true
		},
		series : [ {
			name : '',
			type : 'wordCloud',
			size : [ '80%', '80%' ],
			textRotation : [ 0, 45, 90, -45 ],
			textPadding : 0,
			autoSize : {
				enable : true,
				minSize : 14
			},
			data : [ {
				name : "",
				value : 1000,
				itemStyle : {
					normal : {
						color : 'black'
					}
				}
			}, {
				name : "Min",
				value : minVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Max",
				value : maxVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Count",
				value : countVal,
				itemStyle : createRandomItemStyle()
			},  {
				name : "Avg",
				value : avgVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sum",
				value : sumVal,
				itemStyle : createRandomItemStyle()
			} ,{
				name : "Hypot",
				value :hypotVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt",
				value :cbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Max",
				value :sinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Max",
				value :cosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Max",
				value :tanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Max",
				value :asinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Max",
				value :acosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Max",
				value :atanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Max",
				value :toDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Max",
				value :toRadiansVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt Avg",
				value :avgcbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Avg",
				value :avgsinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Avg",
				value :avgcosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Avg",
				value :avgtanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Avg",
				value :avgasinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Avg",
				value :avgacosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Avg",
				value :avgatanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Avg",
				value :avgtoDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Avg",
				value :avgtoRadiansVal,
				itemStyle : createRandomItemStyle()
			}]
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);

}

// 字符云
function setAjaxEcharts48(result, column) {

	var minVal = result.minValue + 500;
	var maxVal = result.maxValue + 500;
	var countVal = result.countValue + 500;
	var avgVal = result.avgValue + 500;
	var sumVal = result.sumValue + 500;
	var hypotVal = result.hypotValue + 500;
	var cbrtVal = result.cbrtValue + 500;
	var sinVal = result.sinValue + 500;	
	var cosVal = result.cosValue + 500;
	var tanVal = result.tanValue + 500;
	var asinVal = result.asinValue + 500;
	var acosVal = result.acosValue + 500;
	var atanVal = result.atanValue + 500;
	var toDegreesVal = result.toDegreesValue + 500;
	var toRadiansVal = result.toRadiansValue + 500;
	var avgcbrtVal = result.avgcbrtValue + 500;
	var avgsinVal = result.avgsinValue + 500;
	var avgcosVal = result.avgcosValue + 500;
	var avgtanVal = result.avgtanValue + 500;
	var avgasinVal = result.avgasinValue + 500;
	var avgacosVal = result.avgacosValue + 500;
	var avgatanVal = result.avgatanValue + 500;
	var avgtoDegreesVal = result.avgtoDegreesValue + 500;
	var avgtoRadiansVal = result.avgtoRadiansValue + 500;

	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图

	function createRandomItemStyle() {
		return {
			normal : {
				color : 'rgb('
						+ [ Math.round(Math.random() * 160),
								Math.round(Math.random() * 160),
								Math.round(Math.random() * 160) ].join(',')
						+ ')'
			}
		};
	}

	option = {
		title : {
			text : column,
		},
		tooltip : {
			show : true
		},
		series : [ {
			name : column,
			type : 'wordCloud',
			size : [ '80%', '80%' ],
			textRotation : [ 0, 45, 90, -45 ],
			textPadding : 0,
			autoSize : {
				enable : true,
				minSize : 14
			},
			data : [ {
				name : column,
				value : 1000,
				itemStyle : {
					normal : {
						color : 'black'
					}
				}
			}, {
				name : "Min",
				value : minVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Max",
				value : maxVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Count",
				value : countVal,
				itemStyle : createRandomItemStyle()
			},  {
				name : "Avg",
				value : avgVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sum",
				value : sumVal,
				itemStyle : createRandomItemStyle()
			} ,{
				name : "Hypot",
				value :hypotVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt",
				value :cbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Max",
				value :sinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Max",
				value :cosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Max",
				value :tanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Max",
				value :asinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Max",
				value :acosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Max",
				value :atanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Max",
				value :toDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Max",
				value :toRadiansVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt Avg",
				value :avgcbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Avg",
				value :avgsinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Avg",
				value :avgcosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Avg",
				value :avgtanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Avg",
				value :avgasinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Avg",
				value :avgacosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Avg",
				value :avgatanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Avg",
				value :avgtoDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Avg",
				value :avgtoRadiansVal,
				itemStyle : createRandomItemStyle()
			}]
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);

}

// 字符云
function divButton48(divbott) {
	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/wordCloud';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				setdivButtonDate48(result, columnName);
			}
		}
	});
}

// 字符云
function setdivButtonDate48(result, columnName) {
	var minVal = result[0].minValue + 500;
	var maxVal = result[0].maxValue + 500;
	var countVal = result[0].countValue + 500;
	var avgVal = result[0].avgValue + 500;
	var sumVal = result[0].sumValue + 500;
	var hypotVal = result[0].hypotValue + 500;
	var cbrtVal = result[0].cbrtValue + 500;
	var sinVal = result[0].sinValue + 500;	
	var cosVal = result[0].cosValue + 500;
	var tanVal = result[0].tanValue + 500;
	var asinVal = result[0].asinValue + 500;
	var acosVal = result[0].acosValue + 500;
	var atanVal = result[0].atanValue + 500;
	var toDegreesVal = result[0].toDegreesValue + 500;
	var toRadiansVal = result[0].toRadiansValue + 500;
	var avgcbrtVal = result[0].avgcbrtValue + 500;
	var avgsinVal = result[0].avgsinValue + 500;
	var avgcosVal = result[0].avgcosValue + 500;
	var avgtanVal = result[0].avgtanValue + 500;
	var avgasinVal = result[0].avgasinValue + 500;
	var avgacosVal = result[0].avgacosValue + 500;
	var avgatanVal = result[0].avgatanValue + 500;
	var avgtoDegreesVal = result[0].avgtoDegreesValue + 500;
	var avgtoRadiansVal = result[0].avgtoRadiansValue + 500;
	

	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图

	function createRandomItemStyle() {
		return {
			normal : {
				color : 'rgb('
						+ [ Math.round(Math.random() * 160),
								Math.round(Math.random() * 160),
								Math.round(Math.random() * 160) ].join(',')
						+ ')'
			}
		};
	}

	option = {
		title : {
			text : columnName,
		},
		tooltip : {
			show : true
		},
		series : [ {
			name : columnName,
			type : 'wordCloud',
			size : [ '80%', '80%' ],
			textRotation : [ 0, 45, 90, -45 ],
			textPadding : 0,
			autoSize : {
				enable : true,
				minSize : 14
			},
			data : [ {
				name : columnName,
				value : 1000,
				itemStyle : {
					normal : {
						color : 'black'
					}
				}
			}, {
				name : "Min",
				value : minVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Max",
				value : maxVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Count",
				value : countVal,
				itemStyle : createRandomItemStyle()
			},  {
				name : "Avg",
				value : avgVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sum",
				value : sumVal,
				itemStyle : createRandomItemStyle()
			} ,{
				name : "Hypot",
				value :hypotVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt",
				value :cbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Max",
				value :sinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Max",
				value :cosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Max",
				value :tanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Max",
				value :asinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Max",
				value :acosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Max",
				value :atanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Max",
				value :toDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Max",
				value :toRadiansVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt Avg",
				value :avgcbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Avg",
				value :avgsinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Avg",
				value :avgcosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Avg",
				value :avgtanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Avg",
				value :avgasinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Avg",
				value :avgacosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Avg",
				value :avgatanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Avg",
				value :avgtoDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Avg",
				value :avgtoRadiansVal,
				itemStyle : createRandomItemStyle()
			}]
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 字符云
function tdButton48(tdbott) {

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/wordCloud';
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				settdButtonDate48(result, columnName);
			}
		}
	});
}

// 字符云
function settdButtonDate48(result, columnName) {
	var minVal = result[0].minValue + 500;
	var maxVal = result[0].maxValue + 500;
	var countVal = result[0].countValue + 500;
	var avgVal = result[0].avgValue + 500;
	var sumVal = result[0].sumValue + 500;
	var hypotVal = result[0].hypotValue + 500;
	var cbrtVal = result[0].cbrtValue + 500;
	var sinVal = result[0].sinValue + 500;	
	var cosVal = result[0].cosValue + 500;
	var tanVal = result[0].tanValue + 500;
	var asinVal = result[0].asinValue + 500;
	var acosVal = result[0].acosValue + 500;
	var atanVal = result[0].atanValue + 500;
	var toDegreesVal = result[0].toDegreesValue + 500;
	var toRadiansVal = result[0].toRadiansValue + 500;
	var avgcbrtVal = result[0].avgcbrtValue + 500;
	var avgsinVal = result[0].avgsinValue + 500;
	var avgcosVal = result[0].avgcosValue + 500;
	var avgtanVal = result[0].avgtanValue + 500;
	var avgasinVal = result[0].avgasinValue + 500;
	var avgacosVal = result[0].avgacosValue + 500;
	var avgatanVal = result[0].avgatanValue + 500;
	var avgtoDegreesVal = result[0].avgtoDegreesValue + 500;
	var avgtoRadiansVal = result[0].avgtoRadiansValue + 500;
	

	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图

	function createRandomItemStyle() {
		return {
			normal : {
				color : 'rgb('
						+ [ Math.round(Math.random() * 160),
								Math.round(Math.random() * 160),
								Math.round(Math.random() * 160) ].join(',')
						+ ')'
			}
		};
	}
	option = {
		title : {
			text : columnName,
		},
		tooltip : {
			show : true
		},
		series : [ {
			name : columnName,
			type : 'wordCloud',
			size : [ '80%', '80%' ],
			textRotation : [ 0, 45, 90, -45 ],
			textPadding : 0,
			autoSize : {
				enable : true,
				minSize : 14
			},
			data : [ {
				name : columnName,
				value : 1000,
				itemStyle : {
					normal : {
						color : 'black'
					}
				}
			}, {
				name : "Min",
				value : minVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Max",
				value : maxVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Count",
				value : countVal,
				itemStyle : createRandomItemStyle()
			},  {
				name : "Avg",
				value : avgVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sum",
				value : sumVal,
				itemStyle : createRandomItemStyle()
			} ,{
				name : "Hypot",
				value :hypotVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt",
				value :cbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Max",
				value :sinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Max",
				value :cosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Max",
				value :tanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Max",
				value :asinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Max",
				value :acosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Max",
				value :atanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Max",
				value :toDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Max",
				value :toRadiansVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cbrt Avg",
				value :avgcbrtVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Sin Avg",
				value :avgsinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Cos Avg",
				value :avgcosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Tan Avg",
				value :avgtanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Asin Avg",
				value :avgasinVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Acos Avg",
				value :avgacosVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "Atan Avg",
				value :avgatanVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Degrees Avg",
				value :avgtoDegreesVal,
				itemStyle : createRandomItemStyle()
			}, {
				name : "To Radians Avg",
				value :avgtoRadiansVal,
				itemStyle : createRandomItemStyle()
			}]
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 混搭图
function columnButton47(buto) {// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'axis'
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		legend : {
			x : 'left',
			data : [ '最大值', '最小值', '平均值' ]
		},
		xAxis : [ {
			type : 'category',
			data : [ '' ]
		} ],
		yAxis : [ {
			type : 'value',
			name : '数量值',
			axisLabel : {
				formatter : '{value}'
			}
		}, {
			type : 'value',
			name : '平均值',
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [

		{
			name : '最大值',
			type : 'bar',
			data : [ 2.0 ]
		}, {
			name : '最小值',
			type : 'bar',
			data : [ 2.6 ]
		}, {
			name : '平均值',
			type : 'line',
			yAxisIndex : 1,
			data : [ 2.0 ]
		} ]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var id = $(buto).attr('title');
	var columnName = $("#" + buttonID).html();
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/mix';
	var arrayMax = [];
	var arrayMin = [];
	var arrayParam = [];
	var arrayAvg = [];

	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					arrayMax.push(result[i].max);
					arrayMin.push(result[i].min);
					arrayParam.push(result[i].param);
					arrayAvg.push(result[i].avg);
				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					xAxis : [ {
						data : arrayParam
					} ],
					series : [ {
						name : '最大值',
						type : 'bar',
						data : arrayMax
					}, {
						name : '最小值',
						type : 'bar',
						data : arrayMin
					}, {
						name : '平均值',
						type : 'line',
						yAxisIndex : 1,
						data : arrayAvg
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 混搭图
function setAllAjaxEcharts47(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData47(columns,id);
		}
	});
}

// 混搭图
function getLinesData47(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/mixs';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts47(result, columns[0]);
		}
	});
}

// 混搭图
function setEcharts47(lineLists, column) {
	var arrayMax = [];
	var arrayMin = [];
	var arrayParam = [];
	var arrayAvg = [];
	for (var i = 0; i < lineLists.length; i++) {
		var lineList = lineLists[i];
		for (var j = 0; j < lineList.length; j++) {
			arrayMax.push(lineList[j].max);
			arrayMin.push(lineList[j].min);
			arrayParam.push(lineList[j].param);
			arrayAvg.push(lineList[j].avg);
		}
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : '',
				x : 'right',
				y : 'bottom'
			},
			tooltip : {
				trigger : 'axis'
			},
			toolbox : {
				show : true,
				feature : {}
			},
			calculable : true,
			legend : {
				x : 'left',
				data : [ '', '', '' ]
			},
			xAxis : [ {
				show : false,
				type : 'category',
				data : arrayParam
			} ],
			yAxis : [ {
				show : false,
				type : 'value',
				name : '',
				axisLabel : {
					formatter : '{value}'
				}
			}, {
				type : 'value',
				name : '',
				axisLabel : {
					formatter : '{value}'
				}
			} ],
			series : [

			{
				name : '',
				type : 'bar',
				data : arrayMax
			}, {
				name : '',
				type : 'bar',
				data : arrayMin
			}, {
				name : '',
				type : 'line',
				yAxisIndex : 1,
				data : arrayAvg
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts47(column, lineLists[0]);
}

// 混搭图
function setAjaxEcharts47(column, result) {
	var arrayMax = [];
	var arrayMin = [];
	var arrayParam = [];
	var arrayAvg = [];
	for (var i = 0; i < result.length; i++) {
		arrayMax.push(result[i].max);
		arrayMin.push(result[i].min);
		arrayParam.push(result[i].param);
		arrayAvg.push(result[i].avg);
	}
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'axis'
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		legend : {
			x : 'left',
			data : [ '最大值', '最小值', '平均值' ]
		},
		xAxis : [ {
			type : 'category',
			data : arrayParam
		} ],
		yAxis : [ {
			type : 'value',
			name : '数量值',
			axisLabel : {
				formatter : '{value}'
			}
		}, {
			type : 'value',
			name : '平均值',
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [ {
			name : '最大值',
			type : 'bar',
			data : arrayMax
		}, {
			name : '最小值',
			type : 'bar',
			data : arrayMin
		}, {
			name : '平均值',
			type : 'line',
			yAxisIndex : 1,
			data : arrayAvg
		} ]

	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 混搭图
function divButton47(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'axis'
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		legend : {
			x : 'left',
			data : [ '最大值', '最小值', '平均值' ]
		},
		xAxis : [ {
			type : 'category',
			data : [ '' ]
		} ],
		yAxis : [ {
			type : 'value',
			name : '数量值',
			axisLabel : {
				formatter : '{value}'
			}
		}, {
			type : 'value',
			name : '平均值',
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [

		{
			name : '最大值',
			type : 'bar',
			data : [ 2.0 ]
		}, {
			name : '最小值',
			type : 'bar',
			data : [ 2.6 ]
		}, {
			name : '平均值',
			type : 'line',
			yAxisIndex : 1,
			data : [ 2.0 ]
		} ]
	};
	var arrayMax = [];
	var arrayMin = [];
	var arrayParam = [];
	var arrayAvg = [];
	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/mix';

	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					arrayMax.push(result[i].max);
					arrayMin.push(result[i].min);
					arrayParam.push(result[i].param);
					arrayAvg.push(result[i].avg);
				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					xAxis : [ {
						data : arrayParam
					} ],
					series : [ {
						name : '最大值',
						type : 'bar',
						data : arrayMax
					}, {
						name : '最小值',
						type : 'bar',
						data : arrayMin
					}, {
						name : '平均值',
						type : 'line',
						yAxisIndex : 1,
						data : arrayAvg
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 混搭图
function tdButton47(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'axis'
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		legend : {
			x : 'left',
			data : [ '最大值', '最小值', '平均值' ]
		},
		xAxis : [ {
			type : 'category',
			data : [ '' ]
		} ],
		yAxis : [ {
			type : 'value',
			name : '数量值',
			axisLabel : {
				formatter : '{value}'
			}
		}, {
			type : 'value',
			name : '平均值',
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [

		{
			name : '最大值',
			type : 'bar',
			data : [ 2.0 ]
		}, {
			name : '最小值',
			type : 'bar',
			data : [ 2.6 ]
		}, {
			name : '平均值',
			type : 'line',
			yAxisIndex : 1,
			data : [ 2.0 ]
		} ]
	};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/mix';
	var arrayMax = [];
	var arrayMin = [];
	var arrayParam = [];
	var arrayAvg = [];
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 0; i < result.length; i++) {
					arrayMax.push(result[i].max);
					arrayMin.push(result[i].min);
					arrayParam.push(result[i].param);
					arrayAvg.push(result[i].avg);
				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					xAxis : [ {
						data : arrayParam
					} ],
					series : [ {
						name : '最大值',
						type : 'bar',
						data : arrayMax
					}, {
						name : '最小值',
						type : 'bar',
						data : arrayMin
					}, {
						name : '平均值',
						type : 'line',
						yAxisIndex : 1,
						data : arrayAvg
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 和弦图
function columnButton37(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params) {
				if (params.indicator2) { // is edge
					return params.value.weight;
				} else {// is node
					return params.name
				}
			}
		},
		toolbox : {
			show : true,
			feature : {
				restore : {
					show : true
				},
				magicType : {
					show : true,
					type : [ 'force', 'chord' ]
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			x : 'left',
			data : [ '第一组', '第二组', '第三组', '第四组' ]
		},
		series : [ {
			type : 'chord',
			sort : 'ascending',
			sortSub : 'descending',
			showScale : true,
			showScaleText : true,
			data : [ {
				name : '第一组'
			}, {
				name : '第二组'
			}, {
				name : '第三组'
			}, {
				name : '第四组'
			} ],
			itemStyle : {
				normal : {
					label : {
						show : false
					}
				}
			},
			matrix : [ [ 11975, 5871, 8916, 2868 ],
					[ 1951, 10048, 2060, 6171 ], [ 8010, 16145, 8090, 8045 ],
					[ 1013, 990, 940, 6907 ] ]
		} ]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var id = $(buto).attr('title');
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/chord';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						matrix : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 和弦图
function setAllAjaxEcharts37(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData37(columns,id);
		}
	});
}
// 和弦图
function getLinesData37(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/chords';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts37(result, columns[0]);
		}
	});
}
// 和弦图
function setEcharts37(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : '',
				subtext : '',
				x : 'right',
				y : 'bottom'
			},
			tooltip : {
				trigger : 'item',
				formatter : function(params) {
					if (params.indicator2) { // is edge
						return params.value.weight;
					} else {// is node
						return params.name
					}
				}
			},
			toolbox : {
				show : true,
				feature : {/*
							 * restore : {show: true}, magicType: {show: true,
							 * type: ['force', 'chord']}, saveAsImage : {show:
							 * true}
							 */}
			},
			legend : {
				x : 'left',
				data : [ '', '', '', '' ]
			},
			series : [ {
				type : 'chord',
				sort : 'ascending',
				sortSub : 'descending',
				showScale : true,
				showScaleText : true,
				data : [ {
					name : '第一组'
				}, {
					name : '第二组'
				}, {
					name : '第三组'
				}, {
					name : '第四组'
				} ],
				itemStyle : {
					normal : {
						label : {
							show : false
						}
					}
				},
				matrix : lineLists[i]
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts37(column, lineLists[0]);
}

// 和弦图
function setAjaxEcharts37(column, result) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : column,
			subtext : '连续型',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params) {
				if (params.indicator2) { // is edge
					return params.value.weight;
				} else {// is node
					return params.name
				}
			}
		},
		toolbox : {
			show : true,
			feature : {
				restore : {
					show : true
				},
				magicType : {
					show : true,
					type : [ 'force', 'chord' ]
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			x : 'left',
			data : [ '第一组', '第二组', '第三组', '第四组' ]
		},
		series : [ {
			type : 'chord',
			sort : 'ascending',
			sortSub : 'descending',
			showScale : true,
			showScaleText : true,
			data : [ {
				name : '第一组'
			}, {
				name : '第二组'
			}, {
				name : '第三组'
			}, {
				name : '第四组'
			} ],
			itemStyle : {
				normal : {
					label : {
						show : false
					}
				}
			},
			matrix : result
		} ]
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}
// 和弦图
function divButton37(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params) {
				if (params.indicator2) { // is edge
					return params.value.weight;
				} else {// is node
					return params.name
				}
			}
		},
		toolbox : {
			show : true,
			feature : {
				restore : {
					show : true
				},
				magicType : {
					show : true,
					type : [ 'force', 'chord' ]
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			x : 'left',
			data : [ '第一组', '第二组', '第三组', '第四组' ]
		},
		series : [ {
			type : 'chord',
			sort : 'ascending',
			sortSub : 'descending',
			showScale : true,
			showScaleText : true,
			data : [ {
				name : '第一组'
			}, {
				name : '第二组'
			}, {
				name : '第三组'
			}, {
				name : '第四组'
			} ],
			itemStyle : {
				normal : {
					label : {
						show : false
					}
				}
			},
			matrix : [ [ 11975, 5871, 8916, 2868 ],
					[ 1951, 10048, 2060, 6171 ], [ 8010, 16145, 8090, 8045 ],
					[ 1013, 990, 940, 6907 ] ]
		} ]
	};

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/chord';
	var param = [];

	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						matrix : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 和弦图
function tdButton37(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : '',
			subtext : '',
			x : 'right',
			y : 'bottom'
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params) {
				if (params.indicator2) { // is edge
					return params.value.weight;
				} else {// is node
					return params.name
				}
			}
		},
		toolbox : {
			show : true,
			feature : {
				restore : {
					show : true
				},
				magicType : {
					show : true,
					type : [ 'force', 'chord' ]
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			x : 'left',
			data : [ '第一组', '第二组', '第三组', '第四组' ]
		},
		series : [ {
			type : 'chord',
			sort : 'ascending',
			sortSub : 'descending',
			showScale : true,
			showScaleText : true,
			data : [ {
				name : '第一组'
			}, {
				name : '第二组'
			}, {
				name : '第三组'
			}, {
				name : '第四组'
			} ],
			itemStyle : {
				normal : {
					label : {
						show : false
					}
				}
			},
			matrix : [ [ 11975, 5871, 8916, 2868 ],
					[ 1951, 10048, 2060, 6171 ], [ 8010, 16145, 8090, 8045 ],
					[ 1013, 990, 940, 6907 ] ]
		} ]
	};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/chord';
	var param = [];
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						matrix : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

function columnButton34(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : ''
		},
		tooltip : {
			trigger : 'axis',
			formatter : function(params) {
				var res = params[0].seriesName + ' ' + params[0].name;
				res += '<br/>  总数 : ' + params[0].value[0] + '  最低 : '
						+ params[0].value[3];
				res += '<br/>  最高 : ' + params[0].value[1] + '   平均: '
						+ params[0].value[2];
				return res;
			}
		},
		legend : {
			data : [ '' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataZoom : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		dataZoom : {
			show : true,
			realtime : true,
			start : 50,
			end : 100
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : true,
			axisTick : {
				onGap : false
			},
			splitLine : {
				show : false
			},
			data : [ "2013/1/24" ]
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			boundaryGap : [ 0.01, 0.01 ]
		} ],
		series : [ {
			name : '',
			type : 'k',
			data : [ // 开盘，收盘，最低，最高
			[ 2320.26, 2302.6, 2287.3, 2362.94 ] ]
		} ]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var id = $(buto).attr('title');
	var columnName = $("#" + buttonID).html();

	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/kline';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	var param = [];
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 1; i <= result.length; i++) {
					param.push(i);
				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					xAxis : [ {
						data : param
					} ],
					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 和弦图
function divButton34(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : ''
		},
		tooltip : {
			trigger : 'axis',
			formatter : function(params) {
				var res = params[0].seriesName + ' ' + params[0].name;
				res += '<br/>  总数 : ' + params[0].value[0] + '  最低 : '
						+ params[0].value[3];
				res += '<br/>   最高: ' + params[0].value[1] + '  平均 : '
						+ params[0].value[2];
				return res;
			}
		},
		legend : {
			data : [ '' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataZoom : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		dataZoom : {
			show : true,
			realtime : true,
			start : 50,
			end : 100
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : true,
			axisTick : {
				onGap : false
			},
			splitLine : {
				show : false
			},
			data : [ "2013/1/24" ]
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			boundaryGap : [ 0.01, 0.01 ]
		} ],
		series : [ {
			name : '',
			type : 'k',
			data : [ // 开盘，收盘，最低，最高
			[ 2320.26, 2302.6, 2287.3, 2362.94 ] ]
		} ]
	};

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/kline';
	var param = [];

	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 1; i <= result.length; i++) {
					param.push(i);
				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					xAxis : [ {
						data : param
					} ],
					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 和弦图
function tdButton34(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : ''
		},
		tooltip : {
			trigger : 'axis',
			formatter : function(params) {
				var res = params[0].seriesName + ' ' + params[0].name;
				res += '<br/>  总数 : ' + params[0].value[0] + '  最低 : '
						+ params[0].value[3];
				res += '<br/>   最高: ' + params[0].value[1] + '  平均 : '
						+ params[0].value[2];
				return res;
			}
		},
		legend : {
			data : [ '' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataZoom : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		dataZoom : {
			show : true,
			realtime : true,
			start : 50,
			end : 100
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : true,
			axisTick : {
				onGap : false
			},
			splitLine : {
				show : false
			},
			data : [ "2013/1/24" ]
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			boundaryGap : [ 0.01, 0.01 ]
		} ],
		series : [ {
			name : '',
			type : 'k',
			data : [ // 开盘，收盘，最低，最高
			[ 2320.26, 2302.6, 2287.3, 2362.94 ] ]
		} ]
	};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/kline';
	var param = [];
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName,
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				for (var i = 1; i <= result.length; i++) {
					param.push(i);
				}
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					xAxis : [ {
						data : param
					} ],
					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

function setAllAjaxEcharts34(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData34(columns,id);
		}
	});
}
// 和弦图
function getLinesData34(columns,id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/klines';
	var data = [//查询的参数
	            {name:'columnArray',value:columns},
	            {name:'id',value:id},
	];
	$.ajax({
		url : urlColumn,
		data : data,
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts34(result,columns[0]);
		}
	});
}
// 和弦图
function setEcharts34(lineLists,column) {
	var param = [];
	for (var i = 0; i < lineLists.length; i++) {
		var lineList = lineLists[0];
		for (var j = 1; j <= lineList.length; j++) {
			param.push(j);
		}
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
			title : {
				text : ''
			},
			tooltip : {
				trigger : 'axis',
				formatter : function(params) {
					var res = params[0].seriesName + ' ' + params[0].name;
					res += '<br/>  总数 : ' + params[0].value[0] + '  最低 : '
							+ params[0].value[3];
					res += '<br/>   最高: ' + params[0].value[1] + '  平均 : '
							+ params[0].value[2];
					return res;
				}
			},
			legend : {
				data : [ '' ]
			},
			toolbox : {
				show : true,
				feature : {}
			},
			dataZoom : {
				show : true,
				realtime : true,
				start : 50,
				end : 100
			},
			xAxis : [ {
				type : 'category',
				boundaryGap : true,
				axisTick : {
					onGap : false
				},
				splitLine : {
					show : false
				},
				data : param
			} ],
			yAxis : [ {
				type : 'value',
				scale : true,
				boundaryGap : [ 0.01, 0.01 ]
			} ],
			series : [ {
				name : '',
				type : 'k',
				data : lineLists[i]
			} ]
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	var paramOne = [];
	var result = lineLists[0];
	for (var j = 1; j <= result.length; j++) {
		paramOne.push(j);
	}

	setEchartsOne34(paramOne, result,column);

}

function setEchartsOne34(paramOne, result,column) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	option = {
		title : {
			text : column
		},
		tooltip : {
			trigger : 'axis',
			formatter : function(params) {
				var res = params[0].seriesName + ' ' + params[0].name;
				res += '<br/>  总和 : ' + params[0].value[0] + '  最低 : '
						+ params[0].value[3];
				res += '<br/>   最高: ' + params[0].value[1] + '  平均 : '
						+ params[0].value[2];
				return res;
			}
		},
		legend : {
			data : [ '' ]
		},
		toolbox : {
			show : true,
			feature : {}
		},
		dataZoom : {
			show : true,
			realtime : true,
			start : 50,
			end : 100
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : true,
			axisTick : {
				onGap : false
			},
			splitLine : {
				show : false
			},
			data : paramOne
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			boundaryGap : [ 0.01, 0.01 ]
		} ],
		series : [ {
			name : '',
			type : 'k',
			data : result
		} ]
	};

	// 3.载入Echarts图标
	mychart.setOption(option);
}

// 树图
function columnButton46(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		title : {
			text : ''
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		series : [ {
			name : '树图',
			type : 'tree',
			orient : 'horizontal', // vertical horizontal
			rootLocation : {
				x : 100,
				y : 230
			}, // 根节点位置 {x: 100, y: 'center'}
			nodePadding : 8,
			layerPadding : 200,
			hoverable : false,
			roam : true,
			symbolSize : 6,
			itemStyle : {
				normal : {
					color : '#4883b4',
					label : {
						show : true,
						position : 'right',
						formatter : "{b}",
						textStyle : {
							color : '#000',
							fontSize : 5
						}
					},
					lineStyle : {
						color : '#ccc',
						type : 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

					}
				},
				emphasis : {
					color : '#4883b4',
					label : {
						show : false
					},
					borderWidth : 0
				}
			},

			data : [ {
				"name" : "name",
				"children" : [ {
					"name" : "",
					"children" : [ {
						"name" : ""
					}]
				} ]
			} ]
		} ]
	};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();

	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/tree';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}



function tdButton46(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
			title : {
				text : ''
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			series : [ {
				name : '树图',
				type : 'tree',
				orient : 'horizontal', // vertical horizontal
				rootLocation : {
					x : 100,
					y : 230
				}, // 根节点位置 {x: 100, y: 'center'}
				nodePadding : 8,
				layerPadding : 200,
				hoverable : false,
				roam : true,
				symbolSize : 6,
				itemStyle : {
					normal : {
						color : '#4883b4',
						label : {
							show : true,
							position : 'right',
							formatter : "{b}",
							textStyle : {
								color : '#000',
								fontSize : 5
							}
						},
						lineStyle : {
							color : '#ccc',
							type : 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

						}
					},
					emphasis : {
						color : '#4883b4',
						label : {
							show : false
						},
						borderWidth : 0
					}
				},

				data : [ {
					"name" : "name",
					"children" : [ {
						"name" : "",
						"children" : [ {
							"name" : ""
						}]
					} ]
				} ]
			} ]
		};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/tree';
	var param = [];
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}


//树图
function divButton46(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
			title : {
				text : ''
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			series : [ {
				name : '树图',
				type : 'tree',
				orient : 'horizontal', // vertical horizontal
				rootLocation : {
					x : 100,
					y : 230
				}, // 根节点位置 {x: 100, y: 'center'}
				nodePadding : 8,
				layerPadding : 200,
				hoverable : false,
				roam : true,
				symbolSize : 6,
				itemStyle : {
					normal : {
						color : '#4883b4',
						label : {
							show : true,
							position : 'right',
							formatter : "{b}",
							textStyle : {
								color : '#000',
								fontSize : 5
							}
						},
						lineStyle : {
							color : '#ccc',
							type : 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

						}
					},
					emphasis : {
						color : '#4883b4',
						label : {
							show : false
						},
						borderWidth : 0
					}
				},

				data : [ {
					"name" : "name",
					"children" : [ {
						"name" : "",
						"children" : [ {
							"name" : ""
						}]
					} ]
				} ]
			} ]
		};

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/tree';
	var param = [];

	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						data : result
					} ]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}



//树图
function setAllAjaxEcharts46(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData46(columns);
		}
	});
}

//树图
function getLinesData46(columns) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/trees';
	$.ajax({
		url : urlColumn,
		data : {
			columnArray : columns
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts46(result, columns[0]);
		}
	});
}

//树图
function setEcharts46(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		// 在数据为动态加载之前建筑loading动画
		/*
		 * mychart.showLoading();
		 */// 折线面积图
		option = {
				title : {
					text : ''
				},
				toolbox : {
					show : true,
					feature : {}
				},
				series : [ {
					name : '树图',
					type : 'tree',
					orient : 'horizontal', // vertical horizontal
					rootLocation : {
						x : 100,
						y : 230
					}, // 根节点位置 {x: 100, y: 'center'}
					nodePadding : 8,
					layerPadding : 200,
					hoverable : false,
					roam : true,
					symbolSize : 6,
					itemStyle : {
						normal : {
							color : '#4883b4',
							label : {
								show : true,
								position : 'right',
								formatter : "{b}",
								textStyle : {
									color : '#000',
									fontSize : 5
								}
							},
							lineStyle : {
								color : '#ccc',
								type : 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

							}
						},
						emphasis : {
							color : '#4883b4',
							label : {
								show : false
							},
							borderWidth : 0
						}
					},

					data : lineLists[i]
				} ]
			};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts46(column, lineLists[0]);
}

//树图
function setAjaxEcharts46(column, result) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
			title : {
				text : column
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			series : [ {
				name : '树图',
				type : 'tree',
				orient : 'horizontal', // vertical horizontal
				rootLocation : {
					x : 100,
					y : 230
				}, // 根节点位置 {x: 100, y: 'center'}
				nodePadding : 8,
				layerPadding : 200,
				hoverable : false,
				roam : true,
				symbolSize : 6,
				itemStyle : {
					normal : {
						color : '#4883b4',
						label : {
							show : true,
							position : 'right',
							formatter : "{b}",
							textStyle : {
								color : '#000',
								fontSize : 5
							}
						},
						lineStyle : {
							color : '#ccc',
							type : 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

						}
					},
					emphasis : {
						color : '#4883b4',
						label : {
							show : false
						},
						borderWidth : 0
					}
				},

				data : result
			} ]
		};
	// 3.载入Echarts图标
	mychart.setOption(option);
}


//地图
function divButton43(divbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		    title : {
		        text: '',
		        subtext: '',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item'
		    },
		    legend: {
		        orient: 'vertical',
		        x:'left',
		        data:['个数','最小值','最大值','平均值']
		    },
		    dataRange: {
		        min: 0,
		        max: 2500,
		        x: 'left',
		        y: 'bottom',
		        text:['高','低'],           // 文本，默认为数值文本
		        calculable : true
		    },
		    toolbox: {
		        show: true,
		        orient : 'vertical',
		        x: 'right',
		        y: 'center',
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    roamController: {
		        show: true,
		        x: 'right',
		        mapTypeControl: {
		            'china': true
		        }
		    },
		    series : [
		        {
		            name: '个数',
		            type: 'map',
		            mapType: 'china',
		            roam: false,
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        },
		        {
		            name: '最小值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        },
		        {
		            name: '最大值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        },
		        {
		            name: '平均值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        }
		    ]
		};
		                    

	// 获取列名的值
	var column_id = $(divbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/map';
	var paramAvg = [];
	var paramCount = [];
	var paramMax = [];
	var paramMin = [];


	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				 paramAvg = result["avgP"];
				 paramCount = result["countP"];
				 paramMax = result["maxP"];
				 paramMin = result["minP"];
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						data : paramCount
					},{
						data : paramMin
					},{
						data : paramMax
					},{
						data : paramAvg
					}]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}

function tdButton43(tdbott) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		    title : {
		        text: '',
		        subtext: '',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item'
		    },
		    legend: {
		        orient: 'vertical',
		        x:'left',
		        data:['个数','最小值','最大值','平均值']
		    },
		    dataRange: {
		        min: 0,
		        max: 2500,
		        x: 'left',
		        y: 'bottom',
		        text:['高','低'],           // 文本，默认为数值文本
		        calculable : true
		    },
		    toolbox: {
		        show: true,
		        orient : 'vertical',
		        x: 'right',
		        y: 'center',
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    roamController: {
		        show: true,
		        x: 'right',
		        mapTypeControl: {
		            'china': true
		        }
		    },
		    series : [
		        {
		            name: '个数',
		            type: 'map',
		            mapType: 'china',
		            roam: false,
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        },
		        {
		            name: '最小值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        },
		        {
		            name: '最大值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        },
		        {
		            name: '平均值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        }
		    ]
		};

	// 获取列名的值
	var column_id = $(tdbott).attr('title');
	var strs= new Array(); //定义一数组 
	strs = column_id.split("-"); //字符分割 
	var columnName =strs[0];
	var id = strs[1];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/map';
	var paramAvg = [];
	var paramCount = [];
	var paramMax = [];
	var paramMin = [];
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				 paramAvg = result["avgP"];
				 paramCount = result["countP"];
				 paramMax = result["maxP"];
				 paramMin = result["minP"];
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						data : paramCount
					},{
						data : paramMin
					},{
						data : paramMax
					},{
						data : paramAvg
					}]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}


//树图
function columnButton43(buto) {
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	// 折线面积图
	option = {
		    title : {
		        text: '',
		        subtext: '',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item'
		    },
		    legend: {
		        orient: 'vertical',
		        x:'left',
		        data:['个数','最小值','最大值','平均值']
		    },
		    dataRange: {
		        min: 0,
		        max: 2500,
		        x: 'left',
		        y: 'bottom',
		        text:['高','低'],           // 文本，默认为数值文本
		        calculable : true
		    },
		    toolbox: {
		        show: true,
		        orient : 'vertical',
		        x: 'right',
		        y: 'center',
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    roamController: {
		        show: true,
		        x: 'right',
		        mapTypeControl: {
		            'china': true
		        }
		    },
		    series : [
		        {
		            name: '个数',
		            type: 'map',
		            mapType: 'china',
		            roam: false,
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        },
		        {
		            name: '最小值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        },
		        {
		            name: '最大值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        },
		        {
		            name: '平均值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '',value: Math.round(Math.random()*1000)}
		            ]
		        }
		    ]
		};

	// 获取列名的值
	var buttonID = $(buto).attr('id');
	var columnName = $("#" + buttonID).html();
	var paramAvg = [];
	var paramCount = [];
	var paramMax = [];
	var paramMin = [];
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/map';
	// 在数据为动态加载之前建筑loading动画
	mychart.showLoading();
	$.ajax({
		url : urlColumn,
		data : {
			columnName : columnName
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			if (result != null) {
				 paramAvg = result["avgP"];
				 paramCount = result["countP"];
				 paramMax = result["maxP"];
				 paramMin = result["minP"];
				mychart.hideLoading();// 隐藏
				mychart.setOption({
					title : {
						text : columnName,
						subtext : '连续型'
					},
					series : [ {
						data : paramCount
					},{
						data : paramMin
					},{
						data : paramMax
					},{
						data : paramAvg
					}]
				})
			}
		}
	});
	// 3.载入Echarts图标
	mychart.setOption(option);
}





//地图
function setAllAjaxEcharts43(id) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/queryColumn';
	$.ajax({
		url : urlColumn,
		data : {
			id : id
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			var columns = new Array();
			/* 转换成可变量内容 */
			// var data = eval("("+result+")");
			// 遍历结果
			$.each(result, function(index, item) {
				columns.push(result[index].characteristicColumn);
			});
			getLinesData43(columns);
		}
	});
}

//地图
function getLinesData43(columns) {
	// 查询的URL地址
	var urlColumn = window.ctx + '/statisticalanalysis/maps';
	$.ajax({
		url : urlColumn,
		data : {
			columnArray : columns
		},
		type : "post",
		async : true, // 异步请求
		dataType : "json",
		success : function(result) {
			setEcharts43(result, columns[0]);
		}
	});
}

//地图
function setEcharts43(lineLists, column) {
	for (var i = 0; i < lineLists.length; i++) {
		var result = lineLists[i];
		var paramAvg = [];
		var paramCount = [];
		var paramMax = [];
		var paramMin = [];
		 paramAvg = result["avgP"];
		 paramCount = result["countP"];
		 paramMax = result["maxP"];
		 paramMin = result["minP"];
		// 加载dom之后初始化Echarts实例
		var mychart = echarts.init(document.getElementById("dy" + i));
		//地图
		option = {
			    title : {
			        text: '',
			        subtext: '',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item'
			    },
			    legend: {
			        orient: 'vertical',
			        x:'left',
			        data:['','','','']
			    },
			 
			    toolbox: {
			        show: true,
			        orient : 'vertical',
			        x: 'right',
			        y: 'center',
			        feature : {}
			    },
			    series : [
			        {
			            name: '个数',
			            type: 'map',
			            mapType: 'china',
			            roam: false,
			            itemStyle:{
			                normal:{label:{show:true}},
			                emphasis:{label:{show:true}}
			            },
			            data:paramCount
			        },
			        {
			            name: '最小值',
			            type: 'map',
			            mapType: 'china',
			            itemStyle:{
			                normal:{label:{show:true}},
			                emphasis:{label:{show:true}}
			            },
			            data:paramMin
			        },
			        {
			            name: '最大值',
			            type: 'map',
			            mapType: 'china',
			            itemStyle:{
			                normal:{label:{show:true}},
			                emphasis:{label:{show:true}}
			            },
			            data:paramMax
			        },
			        {
			            name: '平均值',
			            type: 'map',
			            mapType: 'china',
			            itemStyle:{
			                normal:{label:{show:true}},
			                emphasis:{label:{show:true}}
			            },
			            data:paramAvg
			        }
			    ]
			
				
		};

		// 3.载入Echarts图标
		mychart.setOption(option);
	}
	setAjaxEcharts43(column, lineLists[0]);
}

//地图
function setAjaxEcharts43(column, result) {
	var paramAvg = [];
	var paramCount = [];
	var paramMax = [];
	var paramMin = [];
	 paramAvg = result["avgP"];
	 paramCount = result["countP"];
	 paramMax = result["maxP"];
	 paramMin = result["minP"];
	// 加载dom之后初始化Echarts实例
	var mychart = echarts.init(document.getElementById("dy"));
	//地图
	option = {
		    title : {
		        text: column,
		        subtext: '连续型',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item'
		    },
		    legend: {
		        orient: 'vertical',
		        x:'left',
		        data:['个数','最小值','最大值','平均值']
		    },
		    dataRange: {
		        min: 0,
		        max: 2500,
		        x: 'left',
		        y: 'bottom',
		        text:['高','低'],           // 文本，默认为数值文本
		        calculable : true
		    },
		    toolbox: {
		        show: true,
		        orient : 'vertical',
		        x: 'right',
		        y: 'center',
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    roamController: {
		        show: true,
		        x: 'right',
		        mapTypeControl: {
		            'china': true
		        }
		    },
		    series : [
		        {
		            name: '个数',
		            type: 'map',
		            mapType: 'china',
		            roam: false,
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:paramCount
		        },
		        {
		            name: '最小值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:paramMin
		        },
		        {
		            name: '最大值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:paramMax
		        },
		        {
		            name: '平均值',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:paramAvg
		        }
		    ]
		
			
	};
	// 3.载入Echarts图标
	mychart.setOption(option);
}





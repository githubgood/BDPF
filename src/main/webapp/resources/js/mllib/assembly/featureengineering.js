/**
 * @description 特征工程JS脚本
 * @auth jm
 */


/**
 * 主成分分析点击查询
 */
function model_25(expid,id,state){
    if(state == '0'){
        $('#model_25_save').off('click').on('click',model_25_save);
    }
    var url = window.ctx + '/featureengineering/model_25_list';//查询的URL地址
    var data = [//查询的参数
        {name:'expid',value:expid},
        {name:'id',value:id},
    ];
    $.get(url, data, function(page){
        var html = "";
        html+= '<div class="dms-prop-wrap model_4_box">' +
            '<header class="dms-prop-header has-tab">' +
            '<ul class="dms-prop-tab dms-PerformTuning-tab">' +
            '<li>参数设置</li>' +
            '</ul>' +
            '</header>'+
            '<section class="dms-prop-body dms-PerformTuning-box">' +
            '<input type="hidden" name="id" value='+page.featureengineeringAssembly.id +'>'+
            '<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
            '<div class="dms-prop-page">' +
            '<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>K值</label>'+
            '<input type="number" onchange="model_25_save();" name="k" class="form-control" value='+page.featureengineeringAssembly.k+'>'+
            '</div>' +
            '</form>' +
            '</div>' +
            '</div>' +
            '</section>' +
            '</div>';
        $('.pai-side-right').html(html);
        tab("dms-PerformTuning-tab","dms-PerformTuning-box");
        popBox("pai-select-field","createPortGroup-box","880");

            checkAll("createPortGroup-box");
        });
};
/**
 * 主成分分析编辑
 */

function model_25_save(){
    var url = window.ctx + '/featureengineering/model_25_save';//查询的URL地址
    var expid = $("input[name='expid']").val();
    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
        {name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
        {name:'k',value:$("input[name='k']").val()},
    ];
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){
                $("input[name='id']").val(page.data.featureengineeringAssembly.id);
                model_25(expid,page.data.featureengineeringAssembly.id,'1');
                $('.createPortGroup-box').modal('hide');
                console.log(JSON.stringify(page));
            }else {
                alert(page.message);
            }
        }
    });
};


//PCA查看数据
function model_25_show(id,sid){
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
        // $.each(page.tableChilds, function(i,value){//进行数据拼接
        //     head+= '<th>'+value.name+'</th>';
        // });
        head+="</tr></thead>";
        //显示表头
        $(".view-data-box .ui-datatable-head .table").html(head);
        $.each(page.resultList, function(i,resultvalue){//进行数据拼接
            if(i<99){//只需要显示前99行
                body+= '<tr><td>'+(i+1)+'</td>';
                $.each(resultvalue,function(j,data){
                    body+= '<td>'+data+'</td>';
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
 * 特征离散查询
 */
function model_24(expid,id,state){
    if(state == '0'){
        $('#model_24_save').off('click').on('click',model_24_save);
    }
    var url = window.ctx + '/featureengineering/model_24_list';//查询的URL地址
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
            '<li>参数设置</li>' +
            '</ul>' +
            '</header>'+
            '<section class="dms-prop-body dms-PerformTuning-box">' +
            '<input type="hidden" name="id" value='+page.featureengineeringAssembly.id +'>'+
            '<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
            '<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>选择一个字段 <span class="dms-prop-tooltip">类型为double或int等数值型</span></label>';
        if(page.featureengineeringChildAssemblies !=null && page.featureengineeringChildAssemblies.length > 0){
            html+=			'<a  class="btn pai-select-field">已选择' +
                '<span class="number">'+page.featureengineeringChildAssemblies.length+'</span>个字段' +
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
            '<label>离散方法</label>'+
            '<select class="form-control">' +
            '<option value="等频离散">等频离散</option>'+
            '</select>'+
            '</div>' +
            '<div class="form-group">' +
            '<label>离散区间</label>'+
            '<input type="number" onchange="model_24_save();" name="equidistantDispersionInterval" class="form-control" value='+page.featureengineeringAssembly.equidistantDispersionInterval+'>'+
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

            if(page.featureengineeringChildAssemblies!=null&&page.featureengineeringChildAssemblies.length!=0){
                $.each(page.featureengineeringChildAssemblies, function(i,value){//进行数据拼接
                    right+= '<tr class="'+value.name+'_'+value.dataType+'"><td>';
                    right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
                    right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
                    right+='<input type="hidden" name="ids" value='+value.id+'>'+
                        '<input type="hidden" name="columnName" value='+value.name+'>'+
                        '<input type="hidden" name="dataType" value='+value.dataType+'>';
                    right+= '</tr>';
                    $(".left_wrap").find("."+value.name+'_'+value.dataType).prop("checked",true);
                });

            }
            $('.check-list').html(right);
            checkAll("createPortGroup-box");
        });
    });
};
/**
 * 特征离散编辑
 */

function model_24_save(){
    var url = window.ctx + '/featureengineering/model_24_save';//查询的URL地址
    console.log(url);
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
    console.log("当前tr长度"+$(".right_wrap .table-body tr").length);
    console.log("ids ->"+ids);
    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
        {name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
        {name:'columnName',value:columnNames},
        {name:'columnType',value:dataTypes},
        {name:'featureEngineeringChildAssemblyId',value:ids},
        {name:'equidistantDispersion',value:"等频离散"},
        {name:'equidistantDispersionInterval',value:$("input[name='equidistantDispersionInterval']").val()},
    ];
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){
                $("input[name='id']").val(page.data.featureengineeringAssembly.id);
                model_24(expid,page.data.featureengineeringAssembly.id,'1');
                console.log( $(".right_wrap .table-body .check-list tr:visible").length);
                $('.createPortGroup-box').modal('hide');
                console.log(JSON.stringify(page));
            }else {
                alert(page.message);
            }
        }
    });
};


//特征离散查看数据
function model_24_show(id,sid){
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
 * 过滤式特征选择查询
 */
function model_28(expid,id,state){
    if(state == '0'){
        $('#model_28_save').off('click').on('click',model_28_save);
    }
    var url = window.ctx + '/featureengineering/model_28_list';//查询的URL地址
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
            '<input type="hidden" name="id" value='+page.featureengineeringAssembly.id +'>'+
            '<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
            '<div class="dms-prop-page dms-prop-input">' +
            '<form>' +
            '<div class="form-group">' +
            '<label>默认全选 <span class="dms-prop-tooltip">默认为全选字段</span></label>';
        if(page.featureengineeringChildAssemblies !=null && page.featureengineeringChildAssemblies.length > 0){
            html+=			'<a  class="btn pai-select-field">已选择' +
                '<span class="number">'+page.featureengineeringChildAssemblies.length+'</span>个字段' +
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

            if(page.featureengineeringChildAssemblies!=null&&page.featureengineeringChildAssemblies.length!=0){
                $.each(page.featureengineeringChildAssemblies, function(i,value){//进行数据拼接
                    right+= '<tr class="'+value.name+'_'+value.dataType+'"><td>';
                    right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
                    right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
                    right+='<input type="hidden" name="ids" value='+value.id+'>'+
                        '<input type="hidden" name="columnName" value='+value.name+'>'+
                        '<input type="hidden" name="dataType" value='+value.dataType+'>';
                    right+= '</tr>';
                    $(".left_wrap").find("."+value.name+'_'+value.dataType).prop("checked",true);
                });

            }
            $('.check-list').html(right);
            checkAll("createPortGroup-box");
        });
    });
};
/**
 * 过滤式特征选择编辑
 */

function model_28_save(){
    var url = window.ctx + '/featureengineering/model_28_save';//查询的URL地址
    console.log(url);
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
    console.log("当前tr长度"+$(".right_wrap .table-body tr").length);
    console.log("ids ->"+ids);
    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
        {name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
        {name:'columnName',value:columnNames},
        {name:'columnType',value:dataTypes},
        {name:'featureEngineeringChildAssemblyId',value:ids},
    ];
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){
                $("input[name='id']").val(page.data.featureengineeringAssembly.id);
                model_28(expid,page.data.featureengineeringAssembly.id,'1');
                console.log( $(".right_wrap .table-body .check-list tr:visible").length);
                $('.createPortGroup-box').modal('hide');
                console.log(JSON.stringify(page));
            }else {
                alert(page.message);
            }
        }
    });
};

//过滤式特征选择查看数据
function model_28_show(id,sid){
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
 * 特征重要性点击查询
 */
function model_26(expid,id,state){
    if(state == '0'){
        $('#model_26_save').off('click').on('click',model_26_save);
    }
    var url = window.ctx + '/featureengineering/model_26_list';//查询的URL地址
    var data = [//查询的参数
        {name:'expid',value:expid},
        {name:'id',value:id},
    ];
    $.get(url, data, function(page){
        var html = "";
        html+= '<div class="dms-prop-wrap model_4_box">' +
            '<header class="dms-prop-header has-tab">' +
            '<ul class="dms-prop-tab dms-PerformTuning-tab">' +

            '</ul>' +
            '</header>'+
            '<section class="dms-prop-body dms-PerformTuning-box">' +
            '<input type="hidden" name="id" value='+page.featureengineeringAssembly.id +'>'+
            '<input type="hidden" name="bdpfMllibAssemblyId" value='+id+'>'+
            '</section>' +
            '</div>';
        $('.pai-side-right').html(html);
        tab("dms-PerformTuning-tab","dms-PerformTuning-box");
        popBox("pai-select-field","createPortGroup-box","880");

        checkAll("createPortGroup-box");
    });
};
/**
 * 特征重要性编辑
 */

function model_26_save(){
    var url = window.ctx + '/featureengineering/model_26_save';//查询的URL地址
    var expid = $("input[name='expid']").val();
    var data = [//查询的参数
        {name:'id',value:$("input[name='id']").val() == "null" ? "" : $("input[name='id']").val()},
        {name:'bdpfMllibAssemblyId',value:$("input[name='bdpfMllibAssemblyId']").val()},
    ];
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        success: function(page){
            if(page.successful == true){
                $("input[name='id']").val(page.data.featureengineeringAssembly.id);
                model_26(expid,page.data.featureengineeringAssembly.id,'1');
                $('.createPortGroup-box').modal('hide');
                console.log(JSON.stringify(page));
            }else {
                alert(page.message);
            }
        }
    });
};

//特征重要性查看数据
function model_26_show(id,sid){
    var url = window.ctx + '/assembly/data_show';//查询的URL地址
    var expid = $("input[name='expid']").val();
    var data = [//查询的参数
        {name:'id',value:id},
        {name:'sid',value:sid},
        {name:'expid',value:expid},
    ];
    $.get(url, data, function(page){//获取返回结果
        var head = "<thead><tr><th title='colname'>colname</th>";//标题
        var body = "<tbody>";//内容
        $.each(page.tableChilds, function(i,value){//进行数据拼接
            head+= '<th>'+value.name+'</th>';
        });
        head+="</tr></thead>";
        //显示表头
        $(".view-data-box .ui-datatable-head .table").html(head);
        body+= '<tr><td>weight</td>';
        for (var i = 0 ; i< page.resultList.length;i++){
            body+= '<td>'+page.resultList[i]+'</td>';
        }
        body+= '</tr>';
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
 * 二值化列表
 */
function model_21(expid,id,state){
	if(state == '0'){
		$('#model_21_save').off('click').on('click',model_21_save);
	}
	var url = window.ctx + '/featureengineering/model_21_list';//查询的URL地址
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
		if(page.featureengineeringChildAssemblys!=null&&page.featureengineeringChildAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.featureengineeringChildAssemblys.length+'</span>个字段</a>';
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
			if(page.featureengineeringChildAssemblys!=null&&page.featureengineeringChildAssemblys.length!=0){
					$.each(page.featureengineeringChildAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.name+'_'+value.dataType+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.name+'_'+value.dataType).prop("checked",true);
					});
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * 二值化列表数据保存
 */
function model_21_save(){
	var url = window.ctx + '/featureengineering/model_21_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+21+";"+$(this).find(".dataType").text());
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
        		model_21(expid,page.id,"1");
    		}
        }
    });
};

//特征尺寸变换查看数据
function model_21_show(id,sid){
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
		for(var i = 0; i < page.columnNames21.length;i++){
			head+= '<th>'+page.columnNames21[i]+'</th>';
			heads[i] = page.columnNames21[i];
		}
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
 * one-hot编码列表
 */
function model_23(expid,id,state){

	if(state == '0'){
		$('#model_23_save').off('click').on('click',model_23_save);
	}
	var url = window.ctx + '/featureengineering/model_23_list';//查询的URL地址
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
		if(page.featureengineeringChildAssemblys!=null&&page.featureengineeringChildAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.featureengineeringChildAssemblys.length+'</span>个字段</a>';
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
			if(page.featureengineeringChildAssemblys!=null&&page.featureengineeringChildAssemblys.length!=0){
					$.each(page.featureengineeringChildAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.name+'_'+value.dataType+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.name+'_'+value.dataType).prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * one-hot编码列表数据保存
 */
function model_23_save(){
	var url = window.ctx + '/featureengineering/model_23_save';//查询的URL地址
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+23+";"+$(this).find(".dataType").text());
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
        		model_23(expid,page.id,"1");
    		}
        }
    });
};


//one-hot编码查看数据
function model_23_show(id,sid){
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
		for(var i = 0; i < page.columnNames23.length;i++){
			head+= '<th>'+page.columnNames23[i]+'</th>';
			heads[i] = page.columnNames23[i];
		}
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
 * 特征尺寸变换列表
 */
function model_20(expid,id,state){

	if(state == '0'){
		$('#model_20_save').off('click').on('click',model_20_save);
	}
	var url = window.ctx + '/featureengineering/model_20_list';//查询的URL地址
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
		if(page.featureengineeringChildAssemblys!=null&&page.featureengineeringChildAssemblys.length!=0){
			html+= '<a  class="btn pai-select-field"  id="0">已选择<span class="number">'+page.featureengineeringChildAssemblys.length+'</span>个字段</a>';
		}else{
			html+= '<a  class="btn pai-select-field"  id="0">选择<span class="number"></span>字段</a>';
		}
		html+='</div>';
		html+='<div class="form-group">' +
        '<label>特征尺寸值<span class ="dms-prop-tooltip">正整数</span></label>'+
        '<input type="number" name="proportionalityCoefficient" onchange="model_20_save();" step="1" value="0" placeholder="" class="form-control">'+
        '</div>';
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
			if(page.featureengineeringChildAssemblys!=null&&page.featureengineeringChildAssemblys.length!=0){
					$.each(page.featureengineeringChildAssemblys, function(i,value){//进行数据拼接
						right+= '<tr class="'+value.name+'_'+value.dataType+'"><td>';
						right+= '<span class="icon icon-delete btn-delete" onclick="del(this)"></span></td>';
						right+= '<td class="name">'+value.name+'</td><td class="dataType">'+value.dataType+'</td>';
						right+= '</tr>';
						$(".left_wrap").find("."+value.name+'_'+value.dataType).prop("checked",true);
					});
				
			}
			$('.check-list').html(right);
			$('.check-list').attr("id",thisid);
			checkAll("createPortGroup-box");
		})
	});
};

/**
 * 特征尺寸变换列表数据保存
 */
function model_20_save(){
	var expid = $("input[name='expid']").val();
	var url = window.ctx + '/featureengineering/model_20_save';//查询的URL地址
	var proportionalityCoefficient = $("input[name='proportionalityCoefficient']").val();
	if(proportionalityCoefficient != "" && proportionalityCoefficient != 0 && proportionalityCoefficient != null){
		var url = window.ctx + '/featureengineering/model_20_save_coefficient';//查询的URL地址
		var data = [//查询的参数
		            {name:'id',value:$('.table-body').attr("id")},
		            {name:'proportionalityCoefficient',value:proportionalityCoefficient},
		];
		$.ajax({
	        type: 'post',
	        url: url,
	        data:data,
	        async: false,//把异步改成同步
	        success: function(page){
	        	if(page.result > 0){//判断是否有数据
	    		}
	        	if(page.msg != "" && page.msg != null){
	        		alert(page.msg);
	        	}
	        },
	    });	
	}
	var conn = [];//已选数组
	var $tds = $(".right_wrap .table-body tr:visible");
	$tds.each(function(){
		conn.push($(this).find(".name").text()+";"+20+";"+$(this).find(".dataType").text());
	})
	var data = [//查询的参数
	            {name:'id',value:$('.table-body').attr("id")},
	            {name:'conn',value:conn},
	            {name:'convertType',value:$('.check-list').attr("id")},
	];
	var html = '';
	$.ajax({
        type: 'post',
        url: url,
        data:data,
        async: false,//把异步改成同步
        success: function(page){
        	if(page.result > 0){//判断是否有数据
        		$('.createPortGroup-box').modal('hide');
        		model_20(expid,page.id,"1");
    		}
        }
    });	
};


//特征尺寸变换查看数据
function model_20_show(id,sid){
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
		for(var i = 0; i < page.columnNames20.length;i++){
			head+= '<th>'+page.columnNames20[i]+'</th>';
			heads[i] = page.columnNames20[i];
		}
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


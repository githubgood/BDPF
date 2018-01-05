/**
 * @description 机器学习JS脚本
 * @auth jm
 */
//频繁项集挖掘算法查看数据
function model_66_show(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th>序号</th><th>频繁项</th><th>频繁项集</th></tr></thead>";//标题
		var body = "<tbody>";//内容
		//显示表头
		$(".view-data-box .ui-datatable-head .table").html(head);
		var i=0;
		$.each(page.resultMap, function(key,values){//进行数据拼接
			body+= '<tr><td>'+(i++)+'</td>';
			body+= '<td>'+key+'</td>';
			body+= '<td>'+values+'</td>';
			body+= '</tr>';
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

//推荐预测查看数据
function model_68_show(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th>序号</th><th>预测值</th><th>真实值</th></tr></thead>";//标题
		var body = "<tbody>";//内容
		//显示表头
		$(".view-data-box .ui-datatable-head .table").html(head);
		$.each(page.outlist, function(i,values){//进行数据拼接
			if(i<99){//只需要显示前99行
				body+= '<tr><td>'+(i+1)+'</td>';
				$.each(values, function(key,value){//进行数据拼接
					body+= '<td>'+key+'</td>';
					body+= '<td>'+value.toFixed(1)+'</td>';
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

//分类预测查看数据
function model_69_show(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th>序号</th><th>预测值</th><th>真实值</th></tr></thead>";//标题
		var body = "<tbody>";//内容
		//显示表头
		$(".view-data-box .ui-datatable-head .table").html(head);
		$.each(page.outlist, function(i,values){//进行数据拼接
			if(i<99){//只需要显示前99行
				body+= '<tr><td>'+(i+1)+'</td>';
				$.each(values, function(key,value){//进行数据拼接
					body+= '<td>'+key+'</td>';
					body+= '<td>'+value.toFixed(1)+'</td>';
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

//回归预测查看数据
function model_70_show(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th>序号</th><th>预测值</th><th>真实值</th></tr></thead>";//标题
		var body = "<tbody>";//内容
		//显示表头
		$(".view-data-box .ui-datatable-head .table").html(head);
		$.each(page.outlist, function(i,values){//进行数据拼接
			if(i<99){//只需要显示前99行
				body+= '<tr><td>'+(i+1)+'</td>';
				$.each(values, function(key,value){//进行数据拼接
					body+= '<td>'+key+'</td>';
					body+= '<td>'+value.toFixed(1)+'</td>';
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

//聚类预测查看数据
function model_71_show(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th>序号</th><th>类标</th></tr></thead>";//标题
		var body = "<tbody>";//内容
		//显示表头
		$(".view-data-box .ui-datatable-head .table").html(head);
		$.each(page.outlist, function(i,value){//进行数据拼接
			body+= '<tr><td>'+(i+1)+'</td>';
			body+= '<td>'+value+'</td>';
			body+= '</tr>';
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

//推荐评估查看评估报告
function model_73_report(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th>MSE值</th></tr></thead>";//标题
		var body = "<tbody>";//内容
		//显示表头
		$(".assessReport-box .ui-data-head .table").html(head);
		body+= '<tr>';
		body+= '<td>'+page.mse+'</td>';
		body+= '</tr>';
		body+="</tbody>";
//		//显示数据内容
		$(".assessReport-box .ui-data-body .table").html(body);
		/*图表*/
//		reportChart();
	    //查看数据
		popDataBox("btn-report","assessReport-box","1100");
		$("#data_submit").on("click",function(){
			$('.assessReport-box').modal('hide');
		})
	});
}

//分类评估查看评估报告
function model_75_report(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th>模型</th><th>准确率</th><th>召回率</th><th>F1指标</th><th>F2指标</th></tr></thead>";//标题
		var body = "<tbody>";//内容
		//显示表头
		$(".assessReport-box .ui-data-head .table").html(head);
		$.each(page.classifieds, function(i,value){//进行数据拼接
			if(i<99){//只需要显示前99行
				body+= '<tr>';
				body+= '<td>'+value.label+'</td>';
				body+= '<td>'+value.precision+'</td>';
				body+= '<td>'+value.recall+'</td>';
				body+= '<td>'+value.f1Score+'</td>';
				body+= '<td>'+value.f2Score+'</td>';
				body+= '</tr>';
			}
		});
		body+="</tbody>";
//		//显示数据内容
		$(".assessReport-box .ui-data-body .table").html(body);
		/*图表*/
		reportChart_ROC(page.auc);
	    //查看数据
		popDataBox("btn-report","assessReport-box","1100");
		$("#data_submit").on("click",function(){
			$('.assessReport-box').modal('hide');
		})
	});
}

//回归评估查看评估报告
function model_77_report(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th>指标</th><th>值</th></tr></thead>";//标题
		var body = "<tbody>";//内容
		//显示表头
		$(".assessReport-box .ui-data-head .table").html(head);
		$.each(page.metricsMap, function(key,value){//进行数据拼接
			body+= '<tr>';
			body+= '<td>'+key+'</td>';
			body+= '<td>'+value+'</td>';
			body+= '</tr>';
		});
		body+="</tbody>";
//		//显示数据内容
		$(".assessReport-box .ui-data-body .table").html(body);
		/*图表*/
		reportChart_77();
	    //查看数据
		popDataBox("btn-report","assessReport-box","1100");
		$("#data_submit").on("click",function(){
			$('.assessReport-box').modal('hide');
		})
	});
}

//聚类评估查看评估报告
function model_79_report(id,sid){
	var url = window.ctx + '/assembly/data_show';//查询的URL地址
	var expid = $("input[name='expid']").val();
	var data = [//查询的参数
	            {name:'id',value:id},
	            {name:'sid',value:sid},
	            {name:'expid',value:expid},
	];
	$.get(url, data, function(page){//获取返回结果
		var head = "<thead><tr><th>WSSE值</th></tr></thead>";//标题
		var body = "<tbody>";//内容
		//显示表头
		$(".assessReport-box .ui-data-head .table").html(head);
		body+= '<tr>';
		body+= '<td>'+page.wsse+'</td>';
		body+= '</tr>';
		body+="</tbody>";
//		//显示数据内容
		$(".assessReport-box .ui-data-body .table").html(body);
		/*图表*/
//		reportChart();
	    //查看数据
		popDataBox("btn-report","assessReport-box","1100");
		$("#data_submit").on("click",function(){
			$('.assessReport-box').modal('hide');
		})
	});
}

/*绘制图表*/
function reportChart_77(){
    //初始化charts实例
    var myChart = echarts.init(document.getElementById("charts-report"));
    //指定图表配置项和数据
    option = {
        title : {
            text: 'residual',
            subtext: '连续型'
        },
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['121205','121315','122205','130205','137205','123505','124105','124805','125205','126005','126805','127205']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'',
                type:'bar',
                data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };
    //使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
}

/*绘制图表*/
function reportChart(){
	//初始化charts实例
	var myChart = echarts.init(document.getElementById("charts-report"));
	//指定图表配置项和数据
	option = {
		    title : {
		        text: 'residual',
		        subtext: '连续型'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : ['121205','121315','122205','130205','137205','123505','124105','124805','125205','126005','126805','127205']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'',
		            type:'bar',
		            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
	//使用刚指定的配置项和数据显示图表
	myChart.setOption(option);
}

/*绘制图表*/
function reportChart_ROC(c){
	//初始化charts实例
	var myChart = echarts.init(document.getElementById("charts-report"));
	//指定图表配置项和数据
	option = {
			title: {
				text: 'ROC',
				subtext: 'AUC值:'+c
			},
		    tooltip : {
		        trigger: 'axis'
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['0','0.0204','0.0204','0.0204','0.1020','0.1428','0.2040','0.3061','0.4489','0.5918','0.6938','0.9591']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            data:[0, 0.2, 0.4, 0.6, 0.8, 1]
		        }
		    ],
		    series : [
		        {
		            name:'ROC',
		            type:'line',
		            stack: '总量',
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:[0.0238,0.1428,0.3509,0.4761,0.5476,0.6904,0.8095,0.9047,0.9523,0.9523,0.9523,0.9761]
		        }
		    ]
		};
	//使用刚指定的配置项和数据显示图表
	myChart.setOption(option);
}
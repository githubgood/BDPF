$(function(){
	//loadGif();//加载动画
		getCloudInfo();
		getNetworkInfo();
	})
	/**
	 * 获取云主机信息
	 */
	function getCloudInfo() {
	var url = window.ctx + '/home/getCloudInfo';//获取的URL地址
	$.get(url,function(maps){//获取返回结果
		var html = "";
		var run = "";
		var stop = "";
		var other = "";
		if(maps != ''){
			$.each(maps, function(i,value){//进行数据拼接
				run = value.runNum;
				stop = value.stopNum;
				other = value.other;
				$('#run').val(run);
				$('#stop').val(stop);
				$('#other').val(other);
			});
		}else{
			run = 0;
			stop = 0;
			other = 0;
			$('#run').val(run);
			$('#stop').val(stop);
			$('#other').val(other);
		}
			$('#prun').text(run);
			$('#pstop').text(stop);
			$('#pother').text(other);
			$('#cloudNub').text(run+stop+other);
			//closeGif();//关闭动画
			var a=$("#run").val();
			var b=$("#stop").val();
			var d=$("#other").val();
			pie(a,b,d);
	});
}
/**
 * 获取网络信息
 */
function getNetworkInfo() {
	var url = window.ctx + '/home/getNetworkInfo';//获取的URL地址
	$.get(url,function(info){//获取返回结果
		var html = "";
		var run = "";
		var stop = "";
		var all = "";
		var other = "";
		var ram = "";
		var ram_used = "";
		var cores = "";
		var cores_used = "";
		var instances = "";
		var instances_used = "";
		
		if(info != ''){//进行数据拼接
				run = info.start;
				stop = info.stop;
				other = info.other;
				all = info.allsize;
				ram = info.ram;
				ram_used = info.ramUsed;
				cores = info.cores;
				cores_used = info.coresUsed;
				instances = info.instances;
				instances_used = info.instancesUsed;
		}else{
			run = 0;
			stop = 0;
			other = 0;
			all ="0";
			ram = "0";
			ram_used = "0";
			cores = "0";
			cores_used = "0";
			instances = "0";
			instances_used = "0";
		}
		$('#nstart').text(run);
		$('#nstop').text(stop);
		$('#nother').text(other);
		$('#netNub').text(all);
		$('#ram_text').text('共'+ram+'G/已用'+ram_used+'G');
		$('#cpu_text').text('共'+cores+'核/已用'+cores_used+'核');
		$('#instances_text').text('可创建'+instances+'台/已创建'+instances_used+'台');
		//closeGif();//关闭动画
		pie_host(run,stop,other);
	});
}
	
/*饼状图*/
//饼状图-1
function pie(a,b,d){
	// 基于准备好的dom，初始化echarts实例
	var vm = echarts.init(document.getElementById('vm-pic'));
	// 指定图表的配置项和数据
	option = {
		tooltip: {
			trigger: 'item',
			 formatter: "{b}: {d}%"
		},
		legend: {
			orient: 'vertical',
			x: 'left'
		},
		series: [
			{
				/*name:'访问来源',*/
				type: 'pie',
				radius: ['35%', '70%'],
				avoidLabelOverlap: false,
					label: {
					normal: {
						show: false,
						position: 'center'
					},
					emphasis: {
						show: false,
						textStyle: {
							fontSize: '0',
							fontWeight: 'none'
						}
					}
				},
				color:['#87e1ad','#e1e187','#d1d5db'],
				labelLine: {
					normal: {
						show: false
					}
				},
				data:[{value:a, name:'运行'},  {value:b, name:'停止'},{value:d, name:'其他'},]
			}
		]
	};
	// 使用刚指定的配置项和数据显示图表。
	vm.setOption(option);
}

//饼状图-2
function pie_host(a,b,d){
	// 基于准备好的dom，初始化echarts实例
	var host = echarts.init(document.getElementById('host-stat'));
	// 指定图表的配置项和数据
	option = {
		tooltip: {
			trigger: 'item',
			 formatter: "{b}: {d}%"
		},
		legend: {
			orient: 'vertical',
			x: 'left'
		},
		series: [
			{
				/*name:'访问来源',*/
				type: 'pie',
				radius: ['35%', '70%'],
				avoidLabelOverlap: false,
					label: {
					normal: {
						show: false,
						position: 'center'
					},
					emphasis: {
						show: false,
						textStyle: {
							fontSize: '0',
							fontWeight: 'none'
						}
					}
				},
				color:['#88dedf','#c6cbd2','#e5a0bc','#d7c2ee'],
				labelLine: {
					normal: {
						show: false
					}
				},
				data:[{value:a, name:'运行'}, {value:b, name:'其他'}, {value:d, name:'停止'},]
			}
		]
	};
	// 使用刚指定的配置项和数据显示图表。
	host.setOption(option);
}
function toSystem() {
	window.location.href = window.ctx + '/user';
}
/**
 * 进入实验js
 */

var exp = {};
//可以增加时间的次数
var batch =0;      //实验最大加时次数
var addNum = 0;    //实验加时次数
var secondSurp = 0;//实验剩余时间
var url = "";//vnc地址
var timeH = 0;//小时  实验剩余时间
var timeM = 0;//分钟  实验剩余时间
var timeS =0;//秒        实验剩余时间
var dragleft = 614;
	$(function(){
		loadGif();//加载动画
		//导航子列表显示
		$('#message').on('click', '#btn-cancel', back);
		var $item = $(".hNav li");
		$item.click(function(){
			$item.removeClass("current");
			$(this).hasClass("current")?$(this).removeClass("current"):$(this).addClass("current")
		})
		$item.hover(function(){
			$(this).find(".dropdown_menu").stop().slideDown();
		},function(){
			$(this).find(".dropdown_menu").stop().slideUp();
		})
		//布局切换
		$(".et-layout span").on("click",function(){
			$(this).addClass("active").siblings("span").removeClass("active");
		})
		$(".left-layout").on("click",function(){
			$(".et-left-content").stop().show().animate({width:"100%"},2000);
			$(".et-right-content").stop().hide().animate({width:"0"},2000);
			$(".et-drag").stop().hide();
            $("#vnc").focus();
		})
		$(".both-layout").on("click",function(){
			$(".et-drag").hide();
            $(".et-left-content").stop().show().animate({width:"530px"},1000);
			$(".et-right-content").stop().show().animate({width:"550px"},1000);
			$(".et-drag").delay(500).show(500).css("left",dragleft + "px");
            $("#vnc").focus();
		})
		$(".right-layout").on("click",function(){
			$(".et-left-content").stop().hide(0).animate({width:"0px"},2000);
			$(".et-right-content").stop().show().animate({width:"100%"},2000);
			$(".et-drag").stop().hide();
            $("#vnc").focus();
		})
		//设置最小实验时间
		var oCountDown = document.getElementById("countdown");
		var aStrong = oCountDown.getElementsByTagName("strong");
		var minTime = $('#minTime').val();
		var maxTime = $('#maxTime').val();
		//获取每次增加实验时间
		var addTime = $('#addTime').val();
		//获取可以增加的实验时间次数
		//如果上次有实验未完成，最大实验加时次数要减去已加时次数
		addNum = $('#num').val();
		batch = (((maxTime-minTime)*60)/(addTime*60))-addNum;
		//如果上次有实验未完成，就续做
		if($('#time').val() !=""){
			if($('#time').val()>0){
				secondSurp = formatSeconds($('#time').val());
			}
		}else{//如果是新的实验，就实验本次实验时间
			updateStartTimes() ;//更新开始时间
			secondSurp = formatSeconds(minTime*60);
		}
		
		var strs= new Array(); //定义一数组
		strs = secondSurp.split(",");
		for (i=0;i<strs.length ;i++ )
		{
            aStrong[i].innerHTML = format(strs[i]);
		} 
		console.log(secondSurp+"....."+$('#num').val());
		//获取实验地址
		var id = $('#id').val();
		var vmId = $('#vmId').val();
		getExpInfo(id,vmId);
		tabItems("et-left-nav","et-left-item");
        tabItems("et-right-nav","et-right-list");
		/*倒计时*/
		var timer = null;
		timer = setInterval(updateTime, 1000);
		/**
		 * 更新时间,每一秒递减1
		 */
		function updateTime ()
		{
			var aStrong = oCountDown.getElementsByTagName("strong");
			var oRemain = aStrong[0].innerHTML.replace(/^0/,'') * 3600 + aStrong[1].innerHTML.replace(/^0/,'')*60+parseInt(aStrong[2].innerHTML.replace(/^0/,''));
			if(oRemain <= 0)
			{
				clearInterval(timer);
				return
			}
			oRemain--;
			aStrong[0].innerHTML = format(parseInt(oRemain / 3600));
			oRemain %= 3600;
			aStrong[1].innerHTML = format(parseInt(oRemain/60));
			oRemain %= 60;
			aStrong[2].innerHTML = format(parseInt(oRemain));
			//重新赋值
			timeH = parseInt(aStrong[0].innerHTML);
			timeM = parseInt(aStrong[1].innerHTML);
			timeS = parseInt(aStrong[2].innerHTML);
			//当时间剩余15分钟时，提示。
			if(aStrong[0].innerHTML == 0 && aStrong[1].innerHTML == 15 && aStrong[2].innerHTML == 59){
				if(batch >=1){
					showTimeDialog();
					batch--;
				}
				//点击确定按钮  先解除绑定，再绑定事件
				$(".btn-save").unbind("click").on("click",function(){
					$('.modal').modal('hide');//先隐藏弹出框
					updateTimes(timeH,timeM,timeS,addNum);
				})
				//点击加时按钮  先解除绑定，再绑定事件
				$(".modal-box-time .btn-cancel").unbind("click").on("click",function(){
					aStrong[0].innerHTML == 0; 
					/*alert(parseInt(aStrong[1].innerHTML)+parseInt(addTime*10));*/
					aStrong[1].innerHTML = parseInt(aStrong[1].innerHTML)+parseInt(addTime); 
					addNum++;
					updateTimes(timeH,timeM,timeS,addNum);
					$("#vnc").focus();
				})
			}
			//提示提交实验报告，虚拟机将在几分钟后自动关闭。
			if(aStrong[0].innerHTML == 0 && aStrong[1].innerHTML == 5 && aStrong[2].innerHTML == 1){
				updateTimes(timeH,timeM,timeS,addNum);
				showError("请尽快提交实验报告，虚拟机将在5分钟后自动关闭！")
			}
			//当时间达到最大实验时间时，提示
			if(aStrong[0].innerHTML == 0 && aStrong[1].innerHTML == 0 && aStrong[2].innerHTML == 0){
				var $box = $(".modal-box .modal-content");
				$box.append("<div class='modal-footer'><a class='btn btn-primary btn-save mr30'>确定</a></div>");
				updateTimes(timeH,timeM,timeS,addNum);
				$box.find(".btn-save").remove();
				showError("您本次实验已经达到最大时间，虚拟机将关闭！")
					updateLastTimes();
				//点击确定按钮
				$(".btn-save").on("click",function(){
					$('.modal').modal('hide');//先隐藏弹出框
					$box.find(".btn-save").remove();
					updateLastTimes();
					$("#vnc").focus();
				})
			}
			//每5秒记录下实验时间
			if(timeS%5 == 0){
				updateTimes(timeH,timeM,timeS,addNum);
			}
		}
	})
	
	/**
	 * 定时上传实验数据
	 * @param times
	 * @param num
	 */
	function updateTimes(time1,time2,time3,num) {
		var secd = time1*3600+time2*60+time3;
		var url = window.ctx + '/exp/updateTime';//上传实验时间地址
		var data = [//查询的参数
		            {name:'id',value:$('#id').val()},
		            {name:'num',value:num},
		            {name:'time',value:secd},
		            {name:'groupId',value:$('#groupId').val()}
		];
		$.get(url, data, function(page){//获取返回结果
			
		});
	}
	/**
	 * 达到最大时间后，更新时间
	 */
	function updateLastTimes() {
		var url = window.ctx + '/exp/updateLastTime';//上传实验时间地址
		var data = [//查询的参数
		            {name:'id',value:$('#id').val()},
		            {name:'comment',value:$('#vmId').val()},
		            {name:'groupId',value:$('#groupId').val()}
		];
		$.get(url, data, function(page){//获取返回结果
			//关闭页面
			$("#vnc").focus();
			$('.modal').modal('hide');//先隐藏弹出框
			 closeGif();
			 window.opener.location.reload(); //刷新父窗口中的网页
			 window.close();//关闭当前窗窗口
		});
	}
	
	/**
	 * 第一次进入实验后，更新时间
	 */
	function updateStartTimes() {
		var url = window.ctx + '/exp/updateStartTime';//上传实验时间地址
		var data = [//查询的参数
		            {name:'id',value:$('#id').val()},
		            {name:'groupId',value:$('#groupId').val()}
		];
		$.get(url, data, function(page){//获取返回结果
		});
	}
	/**
	 * 获取实验信息
	 * @param id 实验id
	 */
	function getExpInfo(id,vmId) {
		var url = window.ctx + '/exp/getExpInfo';//获取的URL地址
		var array =[] ;
		var data = [//查询的参数
		            {name:'id',value:id},
		            {name:'vmId',value:vmId},
		            {name:'groupId',value:$('#groupId').val()}
		];
		$.get(url, data, function(expCloud){//获取返回结果
			var html = "";
			var htmlnode = "";
			if(expCloud != ""){//判断是否有数据
				$.each(expCloud, function(i,value){//进行数据拼接
					html+= '<option>'+ value.ip +'</option>'; 
					if(value.node == "Node1"){
						htmlnode+='<span class="et-nav-item active vnc" onclick="showVnc(\''+value.url+'\')" >'+value.node+'</span>';
						htmlnode+='<input type="hidden" id="'+value.node+'" value="'+value.url+'">'
						url = value.url;
					}else{
						htmlnode+='<span class="et-nav-item vnc" onclick="showVnc(\''+value.url+'\')">'+value.node+'</span>';
						htmlnode+='<input type="hidden" id="'+value.node+'" value="'+value.url+'">'
//						htmlurl+='<div id="vnc'+i+'"  class="tab" style="display: none;">';
//						htmlurl+='<iframe src="" width="100%" height="650"></iframe>';
//						htmlurl+='</div>';
					}
				});
				showVnc(url);
			}else{
				htmlnode += '<tr><td colspan="8">当前实验没有虚拟机！</td></tr>';
			}
			$('#iplist').html(html);//将拼接的结果放到指定的div中  iplist
			$('#nodelist').html(htmlnode);//将拼接的结果放到指定的div中  nodelist
			closeGif();//关闭动画
			//tabs("et-right-nav","et-right-list");
			tabs();
		});
	}
	
	//课程实验任务form表单验证
	exp.validateForm = function(tagid){
		validator = $("#" + tagid).validate({
			rules :{//对指定的字段进行判空或者重复
				debug:false,
				textfield:{//上传文件
					required:true,
				},			
			},
			messages:{//对指定的字段进行消息提示
				textfield:{//上传文件
					required:'请选择文件上传!',
			    },			    
			},
			 submitHandler:function(form){
				 loadGif();//加载提示框
				var url = window.ctx + '/exp/save';
				var suf=$('#'+tagid).serialize();
				url=url+"?"+suf;
				var data = $('#'+tagid).serializeArray();
			    var fileName = $("#file").val();//文件名
			    fileName = fileName.split("\\");  
			    jQuery.ajaxSettings.traditional = true;  
			    fileName = fileName[fileName.length-1]; 
			    $.ajaxFileUpload({  
			        url : url,  
			        secureuri : false,//安全协议  
			        fileElementId:'file',//id  
			        type : 'POST',  
			        dataType : 'json',  
			        data:data,  
			        async : false,  
			        error : function(data,status,e) {  
			        },  
			        success : function(feedbak){
						if(feedbak.successful){
							var $box = $(".modal-box .modal-content");
							$box.append("<div class='modal-footer'><a class='btn btn-primary btn-save mr30'>确定</a></div>");
							showError("实验报告已提交，虚拟机将关闭！")
							//点击确定按钮
							$(".btn-save").on("click",function(){
								$('.modal').modal('hide');//先隐藏弹出框
								$box.find(".btn-save").remove();
								//更新实验剩余时间
								updateTimes(timeH,timeM,timeS,addNum);
								updateLastTimes();//更新实验结束时间
							})
						}else{
							showError(feedbak.message);	
							closeGif();//关闭动画
						}
					}
			    });  
			}
		});
	}; 

//tab切换
function tabItems(a,b){
	var $tab = $("."+a+" a"),
		$oBox = $("."+b+" .tab");
	$tab.click(function(e){
		e.preventDefault();
		var $index = $(this).index();
		$(this).addClass("active").siblings("a").removeClass("active");
		$oBox.hide().eq($index).show();
		if(a==="et-left-nav"){
					$(".et-drag").hide();
					console.log("hide");
					switch ($index){
						case 0:
							$(".et-left-content").stop().show().animate({width:"530px"},1000);
							$(".et-right-content").stop().show().animate({width:"550px"},1000);
							screen();
							break;
						case 1:
							$(".et-left-content").stop().show().animate({width:"390px"},1000);
							$(".et-right-content").stop().show().animate({width:"690px"},1000);
							screen();
							break;
						case 2:
							$(".et-left-content").stop().show().animate({width:"530px"},1000);
							$(".et-right-content").stop().show().animate({width:"550px"},1000);
							screen()
							break;
						case 3:
							$(".et-left-content").stop().show().animate({width:"390px"},1000);
							$(".et-right-content").stop().show().animate({width:"690px"},1000);
							screen()
							break;
					}
				}

	})
}

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

function format(a)
{
	return a.toString().replace(/^(\d)$/,'0$1')
}
/**
 * 传入时间秒数，转换成小时分钟秒，以‘，’号分隔。
 * @param value
 * @returns {String}
 */
function formatSeconds(value) {
	   var theTime = parseInt(value);// 秒
	   var theTime1 = 0;// 分
	   var theTime2 = 0;// 小时
	   var result ="";
	  // alert(theTime);
	   if(theTime > 60) {
	      theTime1 = parseInt(theTime/60);
	      theTime = parseInt(theTime%60);
	      // alert(theTime1+"-"+theTime);
	      if(theTime1 > 60) {
	         theTime2 = parseInt(theTime1/60);
	         theTime1 = parseInt(theTime1%60);
	       }
	   }
	       
	       if(theTime1 == 0 && theTime2 == 0){
	    	   result = "0,0,"+parseInt(theTime);
	       }else if(theTime1 > 0) {
	       result = theTime2+","+parseInt(theTime1)+","+parseInt(theTime);
	       }else if(theTime2 > 0) {
	       result = ""+parseInt(theTime2)+","+parseInt(theTime2)+","+result;
	       }
	       
	       return result;
	   } 

//显示vnc页面
function showVnc(url) {
	var htmlurl = "";
	htmlurl+='<div class="tab" style="display: block;">';
	htmlurl+='<iframe id="vnc" src="'+url+'" width="100%" height="650"  onload="vnc.focus()" ></iframe>';
	htmlurl+='</div>';
	$('#urllist').html(htmlurl);//将拼接的结果放到指定的div中  urllist
}
//设置点击效果
function tabs(){
	var $tab = $("#nodelist .et-nav-item");
	$tab.on("click",function(){
		$(this).addClass("active").siblings("span").removeClass("active")
	})
}
function screen(){
	
	var $value = $(".et-layout span.active").attr("class").split(" ")[0];
	switch($value){
		case "left-layout":
			$(".et-left-content").stop().show().animate({width:"100%"},2000);
			$(".et-right-content").stop().hide().animate({width:"0"},2000);
			break;
		case "both-layout":
			$(".et-drag").delay(500).show(500).css("left",dragleft + "px");
			$(".et-left-content").stop().show().animate({width:"530px"},1000);
			$(".et-right-content").stop().show().animate({width:"550px"},1000);
			break;
	}
}
function back(){
	window.location.href = window.ctx+'/exp';
}
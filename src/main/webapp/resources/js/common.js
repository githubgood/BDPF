/*左侧菜单*/
window.onload = function(){
	menu("pai-test-box");//组件,数据源，实验
	menu("pai-bpmn-left");
	//表选择
//	tab("dms-prop-tab","dms-chart-box");
	//右侧tab
	tab("selected-tab","right_wrap");
	tab("selected-tab","wrap-right-wrap");
//	//字段(参数)设置
//	tab("dms-field-tab","dms-field-box");
//	//字段设置，执行调优
//	tab("dms-PerformTuning-tab","dms-PerformTuning-box");
//	//参数设置，执行调优
//	tab("dms-argumentTune-tab","dms-argumentTune-box");
//	//字段设置，参数设置，执行调优
//	tab("dms-setTuning-tab","dms-setTuning-box");
	/*首页，组件,数据源，实验切换*/
	navTab("btn-home-page");
	navTab("btn-module");
	navTab("btn-data-origin");
	navTab("btn-experiment");
	/*实验名称切换*/
	designation();
	//实验
	toggleTest("ui-context-menu-layer");
	//新建实验
	popBox("template-empty", "copy-test-box", "400")

//左侧(新建实验)
	//popBox("new-test-btn","copy-test-box","400")
}
function menu(a){
	//获取外层元素
	var $menuItem = $("."+a);
	//判断当前元素
	switch (a){
	//最外层div，一级菜单
	case "pai-test-box":
			$($menuItem).on("click",function(){
				$(this).nextAll(".pai-menu-list").is(":hidden")?$(this).nextAll(".pai-menu-list").show():$(this).nextAll(".pai-menu-list").hide();
				$(this).nextAll(".pai-menu-list").is(":hidden")?$(this).find("i").addClass("active"):$(this).find("i").removeClass("active");
			})
			break;
			//工作区名称切换
		case "pai-bpmn-left":
			var $d = $($menuItem).find(".box");
			$d.on("click",function(){
				$(this).nextAll("ul").is(":hidden")?$(this).nextAll("ul").show():$(this).nextAll("ul").hide();
				$(this).nextAll("ul").is(":hidden")?$(this).removeClass("is-opened"):$(this).addClass("is-opened");
			})
			break;
		default:
			break;
	}
}
/*右侧tab切换*/
function tab(a,b){
	var $tabBtn = $("."+a+" li");
	var $page = $("."+b+ " .dms-prop-page");
	$tabBtn.on("click",function(){
		var $index = $(this).index();
		$(this).hasClass("selected")?$(this).attr("class","selected"):$(this).addClass("selected").siblings("li").removeClass("selected");
		$page.eq($index).show().siblings(".dms-prop-page").hide();
	})
}

/*工作区右键菜单*/
function workArea(b){
	//获取右键点击的box
	var $itemName = $("#container .model"),
	//获取右键菜单
	style = $("."+b)[0].style;
	//获取右键菜单内删除
	var $remove = $(".removeElement");
	//获取右键菜单从此处开始执行
	var $executeBtn = $(".btn-execute-begin");
	//获取右键菜单查看数据
	var $viewBtn = $(".btn-view-data");
	//获取邮件菜单查看评估报告
	var $viewReportBtn = $(".btn-report");
	//获取右键菜单重命名
	var $renameBtn = $(".btn-rename");
	//获取右键菜单查看分析报告
	var $echartsBtn = $(".btn-view-chart");
	//初始化右键菜单
	style.display = "none";
	//当前元素下右键菜单
	$itemName.on("contextmenu",function(event){
		var event = event || window.event;
		var $this = $(this);
		var sid = $this.find("span.element-text").attr("id");
		//分类、推荐、回归、聚类评估显示评估报告
		if(sid == "73"||sid == "75"||sid == "77"||sid == "79"){
			$(".ui-workarea-menu-layer .btn-report").show();
		}else{
			$(".ui-workarea-menu-layer .btn-report").hide();
		}
		//统计分析显示分析报告
		if(Number(sid) >= 32 && Number(sid) <= 48){
			$(".ui-workarea-menu-layer .btn-view-chart").show();
		}else{
			$(".ui-workarea-menu-layer .btn-view-chart").hide();
		}
		//指定显示查看数据
		if((Number(sid) >= 32 && Number(sid) <= 48)||(Number(sid) >= 50 && Number(sid) <= 67)
				||sid == "73"||sid == "75"||sid == "77"||sid == "79"){
			$(".ui-workarea-menu-layer .btn-view-data").hide();
		}else{
			$(".ui-workarea-menu-layer .btn-view-data").show();
		}
		var aDoc = [document.documentElement.offsetWidth, document.documentElement.offsetHeight];
		//显示右键菜单
		style.display = "block";
		//设置右键菜单的坐标
		style.top = event.clientY+"px";
		style.left = event.clientX + "px";
		//最大显示范围
		maxWidth = aDoc[0] - $("."+b+" ul")[0].offsetWidth;
		maxHeight = aDoc[1] - $("."+b+" ul")[0].offsetHeight;
		//防止菜单溢出
		$("."+b)[0].offsetTop > maxHeight && (style.top = maxHeight + "px");
		$("."+b)[0].offsetLeft > maxWidth && (style.left = maxWidth + "px");
		//删除菜单对应的元素
		$remove.unbind("click").on("click",function(){
			var element = $($this);
			if(confirm("确定删除该模型？模型后若有连接其他模型，请删除其他模型，否则会对运行产生影响")){
				var id = element.find("span.name").attr("id");
				jsPlumb.remove(element);
				var connects = [];    //存储连线的数组
				$.each(jsPlumb.getAllConnections(),function(idx,connection){
					connects.push({
						ConnectionId:connection.id,
						start:$(connection.source).attr("data-index"),
						end:$(connection.target).attr("data-index"),
						originSign:$(connection.source).attr("data-sign"),
						destinationSign:$(connection.target).attr("data-sign")
					})
				})
				
				var mainArr = [];     //存储元素的数组
				
				$("#container .item").each(function(){
					mainArr.push({
						offset:$(this).position(),
						text:$(this).text(),
						index:$(this).attr("data-index"),
						sign:$(this).attr("data-sign"),
						pid:$(this).attr("data-id"),
						id:$(this).attr("id"),
						sid:$(this).attr("data-sid")
					})
				})
				//组件删除
				eval(delAssembly(id,sid,connects,mainArr));
			}
			style.display = "none";
		})
		//从此处执行时，状态变化
		$executeBtn.unbind("click").on("click",function () {
			$this.find(".status").attr("class","status icon-loading");
			style.display = "none";
		})
		//查看数据
		$viewBtn.unbind("click").on("click",function () {
			var a = $this.attr("id");
			var id = $this.find("span.name").attr("id");//组件主键ID
			var sid = $this.find("span.element-text").attr("id");//组件ID
			if(sid>=32&&sid<=48){//如果是统计分分析，则单独处理
				eval(model_show(id,sid));
			}else{
				eval(a.split("-", 1)+"_show("+"\""+id+"\",\""+sid+"\")");
			}
		})
		//重命名组件名称
//		$renameBtn.unbind("click").on("click",function () {
//			var a = $this.attr("id");
//			var id = $this.find("span.name").attr("id");//组件主键ID
//			var sid = $this.find("span.element-text").attr("id");//组件ID
//			eval(a.split("-", 1)+"_edit("+"\""+id+"\",\""+sid+"\")");
//		})
		//查看评估报告
		$viewReportBtn.unbind("click").on("click",function () {
			var a = $this.attr("id");
			var id = $this.find("span.name").attr("id");//组件主键ID
			var sid = $this.find("span.element-text").attr("id");//组件ID
			eval(a.split("-", 1)+"_report("+"\""+id+"\",\""+sid+"\")");
		})
		//查看分析报告
		$echartsBtn.unbind("click").on("click",function () {
			var a = $this.attr("id");
			var id = $this.find("span.name").attr("id");//组件主键ID
			var sid = $this.find("span.element-text").attr("id");//组件ID
			eval(setEchartsHTML(id,sid));
		})
		return false;
	});
	$("."+b+" .ui-context-menu-item").on("click",function(){
		style.display = "none";
	})
	$("body").on("click",function(){
		style.display = "none";
	})
}

/*选择字段(列表跟编辑)*/
/*--左侧切换--*/
function navTab(a){
	switch (a){
		case "btn-home-page"://首页
			$("."+a).on("click",function(event){
				event.stopPropagation();
				$(".pai-home").animate({"bottom":0},0);
			})
			break;
		case "btn-experiment": //实验
			$("."+a).on("click",function(event){
				event.stopPropagation();
				$(".pai-home").animate({"bottom":"100%"},0);
				$("#leftMenu .pai-toolbox-sec").hide();
				$("#leftMenu .pai-toolbox-sec").eq(2).show();
			})
			break;
		case "btn-data-origin"://数据源
			$("."+a).on("click",function(event){
				event.stopPropagation();
				$(".pai-home").animate({"bottom":"100%"},0);
				$("#leftMenu .pai-toolbox-sec").hide();
				$("#leftMenu .pai-toolbox-sec").eq(1).show();
			})
			break;
		case "btn-module"://组件
			$("."+a).on("click",function(event){
				event.stopPropagation();
				$(".pai-home").animate({"bottom":"100%"},0);
				$("#leftMenu .pai-toolbox-sec").hide();
				$("#leftMenu .pai-toolbox-sec").eq(0).show();
			})
			break;
	}
}
/*实验名称切换*/
function designation(){
	var $desiName = $(".pai-bpmn-left .text");
	var $textItem = $(".pai-bpmn-left .pai-dropdown li");
	$textItem.on("click",function(){
		if($(this).hasClass("selected")){
			return;
		}else{
			$(this).addClass("selected").siblings("li").removeClass("selected");
			$
		}
		var $text = $(this).find(".text-item").text();
		$desiName.html($text);
	})
}


/*拖拽*/
//模型计数器
/**模型计数器*/
var modelCounter = 0;
/**
 * 克隆对象
 */
function deepCopy(p,c){
	var c = c || {};
	for(var i in p){
		if(! p.hasOwnProperty(i)){
			continue;
		}
		if(typeof p[i]==="object"){
			c[i] = (p[i].constructor === Array)?[]:{};
			deepCopy(p[i],c[i]);
		}else{
			c[i]=p[i];
		}
	}
	return  c;
}

/*
*保存
* */
var mainHTML = "";
jsPlumb.ready(function(){
	var connectorPaintStyle = {     //基本连接线样式
			lineWidth:2,            //连线宽度
			strokeStyle:"#61B7CF", //连线颜色
			joinstyle:"round",     //连线样式
			outlineColor:"white",
			outlineWidth:0
	};
	var connectorHoverStyle = {      //鼠标悬浮在连线上的样式
			lineWidth:3,                 //鼠标悬浮连线宽度
			strokeStyle:"#216477",      //鼠标悬浮颜色
			outlineColor:"white",
			outlineWidth:0,
	};
	var origin = {
			//起点
			endpoint:["Dot",{radius:8}],     //端点的半径
			connectorStyle:connectorPaintStyle,//连接线的颜色，大小样式
			connectorHoverStyle:connectorHoverStyle,//鼠标放到连线上显示的效果
			paintStyle:{
				strokeStyle:"#1e8151",
				fillStyle:"transparent",
				radius:4,
				lineWidth:2
			},              //端点的颜色样式
			auchor:"AutoDefault",
			isSource:true,     //是否可以拖动(作为连线起点)
			connector:["Bezier",{stub:[40,60],gap:10,cornerRadius:5,alwaysRespectStubs:true}], //连接线样式种类
			isTarget:false,      //是否可以放置(连接终点)
			maxConnections:-1,    //设置连接点最多可以连接几条线，-1表示无限大
			connectorOverlays:[["Arrow",{width:10,length:10,location:1 }],//控制箭头形状
			                   [ "Label", {//鼠标拉出来的线的属性
			                	   location: 0.4,
			                	   id: "label",
			                   }]]
	};
	var destination = { //终点
			endpoint:["Dot",{radius:5}], //端点的形状
			connectorStyle:connectorPaintStyle, //连接线的颜色，大小样式
			connectorHoverStyle:connectorHoverStyle,
			paintStyle:{fillStyle:"#1e8151"},   //端点的颜色样式
			isSource:false,                     //是否可以拖动(作为连接起点)
			connector:["Bezier",{stub:[40,60],gap:10,cornerRadius:5,alwaysRespectStubs:true}],//连接线的样式种类
			isTarget:true,   //是否可以放置(连接终点)
			maxConnections:-1, //设置连接点最多可以连接几条线，-1表示无限大
			connectorOverlays:[["Arrow",{width:10,length:10,location:1 }]]
	}
	$("#leftMenu dd").draggable({
		helper:"clone",          //复制
		scope:"ss",              //标识为了判断是否可以放置
	})
	var elementSign = 0;      //标志元素唯一性
	$("#container").droppable({
		scope:"ss",
		drop:function(event,ui){  //在目标容器上释放鼠标，ui.draggable[0]为开始拖拽的元素
			var id =  "model_" + $(ui.draggable).find("span.element-text").attr("id")+"-"+elementSign++;
			var sourceId = $(ui.draggable).find("span.element-text");
			var uuid = getUUID();
			var left = parseInt(ui.offset.left-$(this).offset().left);
			var top = parseInt(ui.offset.top-$(this).offset().top);
			var ele = $('<div class="item model pane-node-content" data-sid="'+sourceId.attr("id")+'" data-sign="'+elementSign+'" data-id="'+uuid+'"  data-index="'+$(ui.draggable[0]).attr("data-index")+'"  id="' + id+'"><span class="icon icon-doc"></span><span class="name" id="'+uuid+'">' + $(ui.helper).html() + '</span><span  class="status icon-success"></span></div>');
			ele.css({"left":left,"top":top,position:"absolute",margin:0});
			$(this).append(ele);
			jsPlumb.addEndpoint(ele,{anchors:"BottomCenter"},origin);  //起点
			jsPlumb.addEndpoint(ele,{anchors:"TopCenter"},destination); //终点
			jsPlumb.draggable(ele,{containment:"parent"});      //端点可以拖动设置，并且将端点限制在父级内
			$(ele).draggable({     //设置拖动到main区域中的元素还可以拖拽
				containment:"parent" //限制拖动不超过父级边框
			});
			
			workArea("ui-workarea-menu-layer");
			
			var sid = $(this).find("#"+id+" span.name").attr("id");
			
			tabToggle(id,sid);
			
			var connects = [];//存储连线的数组
			$.each(jsPlumb.getAllConnections(),function(idx,connection){
				connects.push({
					ConnectionId:connection.id,
					start:$(connection.source).attr("data-index"),
					end:$(connection.target).attr("data-index"),
					originSign:$(connection.source).attr("data-sign"),
					destinationSign:$(connection.target).attr("data-sign")
				})
			})
			
			var mainArr = [];//存储元素的数组
			$("#container .item").each(function(){
				mainArr.push({
					offset:$(this).position(),
					text:$(this).text(),
					index:$(this).attr("data-index"),
					sign:$(this).attr("data-sign"),
					pid:$(this).attr("data-id"),
					id:$(this).attr("id"),
					sid:$(this).attr("data-sid")
				})
			})
			
			saveAssembly(sourceId,'',sid,'',connects,mainArr);//保存组件
			
			/*右侧基本切换*/
			function tabToggle(a,b){
				var $btn = $("#"+a);
				$btn.on("click",function(){
					var expid = $("input[name='expid']").val();
					$('.modal-footer.choose-save').find(".btn-primary").attr("id",a.split("-", 1)+'_save');
//					$('#'+a.split("-", 1)+'_save').on('click',a.split("-", 1)+'_save()');
					eval(a.split("-", 1)+"(\""+expid+"\",\""+b+"\",'0')");//查询画布内容
				})
			}
		}
	})
	//监听新的连接
	jsPlumb.bind("click",function(conn,originalEvent){   //点击线段删除
		if(confirm("确定删除？")){
			jsPlumb.detach(conn);
			/** add ylh 2017-12-14
			 *	连线删除
			 *	begin
			 **/
            var sourceId = $("#" + conn.sourceId).find("span.element-text");
            var targetId = $("#" + conn.targetId).find("span.element-text");
            var sid = $("#" + conn.sourceId).find("span.name").attr("id");
            var tid = $("#" + conn.targetId).find("span.name").attr("id");
            //var targetName = $("#" + conn.targetId).attr("id");
            var connects = [];//存储连线的数组
            $.each(jsPlumb.getAllConnections(),function(idx,connection){
                connects.push({
                    ConnectionId:connection.id,
                    start:$(connection.source).attr("data-index"),
                    end:$(connection.target).attr("data-index"),
                    originSign:$(connection.source).attr("data-sign"),
                    destinationSign:$(connection.target).attr("data-sign")
                })
            })

            var mainArr = [];//存储元素的数组
            $("#container .item").each(function(){
                mainArr.push({
                    offset:$(this).position(),
                    text:$(this).text(),
                    index:$(this).attr("data-index"),
                    sign:$(this).attr("data-sign"),
                    pid:$(this).attr("data-id"),
                    id:$(this).attr("id"),
                    sid:$(this).attr("data-sid")
                })
            })
			deleteLabel(sourceId,targetId,sid,tid,connects,mainArr);
			/** end **/
		}
	})
	//自己连自己管控
	jsPlumb.bind("connection",function(connInfo,originalEvent){
		init(connInfo.connection);
		if(connInfo.connection.sourceId == connInfo.connection.targetId){
			jsPlumb.detach(connInfo);
			alert("不能连接自己！");
		}
	})
	//当连接成功后，将箭头上的label改为连接ID
//	jsPlumb.bind("connection", function (info) {
//		info.connection.getOverlay("label").setLabel(info.connection.id);
//	});
	
	if(sessionStorage.getItem("flowsheet")!=null){//判断是否有保存过
		var flowsheet = JSON.parse(sessionStorage.getItem("flowsheet"));
		var mainHTML = "";
		if(flowsheet.mainArr!=null){
			for(var i=0;i<flowsheet.mainArr.length;i++){
				if(elementSign<flowsheet.mainArr[i].sign){     //如果已经保存过，即将标记更新
					elementSign = flowsheet.mainArr[i].sign;
				}
				mainHTML += '<div class="model pane-node-content item" data-sid="'+flowsheet.mainArr[i].sid+'" id="'+flowsheet.mainArr[i].id+'"  data-id="'+flowsheet.mainArr[i].pid+'" data-sign="'+flowsheet.mainArr[i].sign+'" data-index="'+flowsheet.mainArr[i].index+'"  style="left:'+flowsheet.mainArr[i].offset.left+'px;top:'+flowsheet.mainArr[i].offset.top+'px;position:absolute;margin:0"  ><span class="icon icon-doc"></span><span class="name" id="'+flowsheet.mainArr[i].pid+'"><span id="'+flowsheet.mainArr[i].sid+'" class="element-text">' +  flowsheet.mainArr[i].text + '</span></span><span  class="status icon-success"></span></div>'
			};
			$("#container").append(mainHTML);
			$("#container .item").each(function(){
				jsPlumb.addEndpoint(this,{anchors:"BottomCenter"},deepCopy(origin,{uuid:$(this).attr("data-sign")+"origin"}));//起点
				jsPlumb.addEndpoint(this,{anchors:"TopCenter"},deepCopy(destination,{uuid:$(this).attr("data-sign")+"destination"}));//终点
				jsPlumb.draggable(this,{containment:"parent"});//端点可以拖动设置，并且将端点限制在父级内
				$(this).draggable({//设置拖动到main区域中的元素还可以拖拽
					containment:"parent" //限制拖动不超过父级边框
				})
			})
		}
		if(flowsheet.connects!=null){
			//固定连线
			for(var i=0;i<flowsheet.connects.length;i++){
				jsPlumb.connect({uuids:[flowsheet.connects[i].originSign+"origin",flowsheet.connects[i].destinationSign+"destination"]})
			}
		}
		
		workArea("ui-workarea-menu-layer");
		
		linePlay();
		
		var $test = $(".model");
		$test.hover(function(){
			var id = $(this).attr("id");
			var sid = $(this).find("span.name").attr("id");
			tabToggle(id,sid);
		})
		/*右侧基本切换*/
		function tabToggle(a,b){
			var $btn = $("#"+a);
			$btn.off("click").on("click",function(){
				var expid = $("input[name='expid']").val();
				$('.modal-footer.choose-save').find(".btn-primary").attr("id",a.split("-", 1)+'_save');
				eval(a.split("-", 1)+"(\""+expid+"\",\""+b+"\",'0')");//查询画布内容
			})
		}
	}
	
	//设置连接Label的label
	function init(conn)
	{
		var label_text;
		$("#select_sourceList").empty();
		$("#select_targetList").empty();
		
		var sourceId = $("#" + conn.sourceId).find("span.element-text");
		var targetId = $("#" + conn.targetId).find("span.element-text");
		var sid = $("#" + conn.sourceId).find("span.name").attr("id");
		var tid = $("#" + conn.targetId).find("span.name").attr("id");
		var targetName = $("#" + conn.targetId).attr("id");
		
		var connects = [];//存储连线的数组
		$.each(jsPlumb.getAllConnections(),function(idx,connection){
			connects.push({
				ConnectionId:connection.id,
				start:$(connection.source).attr("data-index"),
				end:$(connection.target).attr("data-index"),
				originSign:$(connection.source).attr("data-sign"),
				destinationSign:$(connection.target).attr("data-sign")
			})
		})
		
		var mainArr = [];//存储元素的数组
		$("#container .item").each(function(){
			mainArr.push({
				offset:$(this).position(),
				text:$(this).text(),
				index:$(this).attr("data-index"),
				sign:$(this).attr("data-sign"),
				pid:$(this).attr("data-id"),
				id:$(this).attr("id"),
				sid:$(this).attr("data-sid")
			})
		})
		
		saveAssembly(sourceId,targetId,sid,tid,connects,mainArr);//保存组件
		
		$("#submit_label").unbind("click");
		$("#submit_label").on("click",function(){
			setlabel(conn);
		});
		//线条设置id
		$("#" + targetName).siblings("svg._jsPlumb_connector").last().attr("id",sourceId.attr("id")+"_"+targetId.attr("id"));
	}
	
//setlabel
	function setlabel(conn)
	{
		conn.getOverlay("label").setLabel($("#select_sourceList").val()
				+ ' '
				+ $("#select_comparison").val()
				+ ' '
				+ $("#select_targetList").val());
		if($("#twoWay").val()=="true"){
			conn.setParameter("twoWay",true);
		}else{
			conn.setParameter("twoWay",false);
			conn.hideOverlay("arrow_backwards");
		}
	}
})

//保存流程图
function saveFlowsheet(state,connects,mainArr){
	if(state == 1){
		 sessionStorage.setItem("flowsheet",JSON.stringify({"connects":connects,"mainArr":mainArr}));
	}else{
		 //清空画布
		 $("#container").html("");
		 sessionStorage.clear();
	}
}

//将值存入数组
function val(){
	var conn = [];
	var $tds = $(".right_wrap .table-body tr:visible .abc");
	$tds.each(function(){
		conn.push($(this).text())
	})
}
//线条动效
function linePlay(){
	/*var $line = $("#jsPlumb_1_34");
	$line.prev("svg").attr("class","_jsPlumb_connector active");*/
}
/*判断左右值是否一样*/
function arr(a){
	var $left = $("."+a+" .left_wrap  .zero-accordion-item");
	var $right = $("."+a+" .right_wrap tbody tr");
	for(var i=0;i<$left.length;i++){
		var $val = $($left[i]).parent("ul").find("li:first-child span").text();
		$($right[i]).find("td:last-child").text($val);
	}
}

/*--------实验---------*/
//我的实验--右键效果
function toggleTest(a){
	//获取我的实验
	var $btnTest = $(".pai-btn-test"),
		//获取右键菜单
		style = $("."+a)[0].style,
		//新建实验按钮
		$newTest = $(".copy-test-btn");
	//添加右击事件
	$btnTest.on("contextmenu",function(event){
		var event = event || window.event;
		//显示右键菜单
		style.display = "block";
		//设置右键菜单的坐标
		style.top = event.clientY+"px";
		style.left = event.clientX + "px";
		//新建模板实验
		$(".new-template-btn,.new-et-btn").on("click",function(event){
			event.stopPropagation();
			$(".pai-home").animate({"bottom":0},0);
			style.display = "none";
		})
		return false;
	})
//	$(".new-et-btn").on("click",function(event){
//		event.stopPropagation();
//		$(".pai-home").animate({"bottom":0},0);
//		style.display = "none";
//	})
	$("body").on("click",function(){
		style.display = "none";
	})
}

//实时获取UUID
function getUUID(){
	var sty;
	var url = window.ctx + '/assembly/getUUID';//查询的URL地址
	$.ajax({
        type: 'post',
        url: url,
        async: false,//把异步改成同步
        success: function(originalRequest){
              sty = originalRequest;
        }
    });
	return sty
}
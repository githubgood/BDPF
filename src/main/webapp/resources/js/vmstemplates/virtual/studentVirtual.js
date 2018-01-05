
/**
 * @description 学生端虚拟机管理JS脚本
 * @auth wwq
 */	
$.ajaxSetup({  
    async : false  
});

function bb(obj){
	
	 var aa = document.getElementsByName("virtual");
	 for (var i = 0; i < aa.length; i++) {
	  aa[i].checked = false;
	 }
	 obj.checked = true;
	}

var startNumb = 0;//当前虚拟机运行中的数量
var taskId = "";
var groupId = "";
var ips=new Array();
var names=new Array();


function check(c){
	c.stopPropagation();
	var id="";
	var ischeck=0;
	var uncheck=0;
	var url = window.ctx + '/virtual/selectVirtual';
	$.get(url,function(result){
	id=result[0].mid+","+result[0].sid;
	 var r=document.getElementsByName("vir");
	      for(var i=0;i<r.length;i++){
	    	
	    	  if(id.indexOf(r[i].value)>=0 ){
	    		  if(r[i].checked){
	    			  ischeck++;
	    		  }
	    		  if(!r[i].checked){
	    			  uncheck++;
	    		  }
	    	  }

	      }
	    for(var i=0;i<r.length;i++){
	    if(ischeck==1){
	    	if(id.indexOf(r[i].value)>=0 ){
	    		r[i].checked=true;
       	 }
	    }
	    if(uncheck==1){
	    	if(id.indexOf(r[i].value)>=0 ){
	    		r[i].checked=false;
       	 }
	    }
	    } 
	   
	});
	return false;
}

$(function(){
	$('#content-r').on('click', '#search', search);
	$('#content-r').on('click', '#add', add);
	$('#content-r').on('click', '#install', install);
	$('#content-r').on('click', '#destroy', destroy);
	search();//查询列表
	})
var installs= new Array();
function list(){
	var mid="";
	var sid="";
	var install="";
	
	var html="";
	var url = window.ctx + '/virtual/selectVirtual';
	
	$.get(url,function(result){
		if(result!=""){
			mid=result[0].mid;
			sid=result[0].sid;
			install=result[0].install;
			if(install!=null&&install!=""){
				if(install=="all"){
					install="所有集群";
				}
				html='已经安装'+install;
				
				$('#showVirtual').html(html);
			}else{
				
			}
			
		}
		
		});

	
	startNumb =0;
	ips.splice(0,ips.length); 
	names.splice(0,names.length); 
	var url = window.ctx + '/virtual/studentlist';//查询的URL地址
	var data = [//查询的参数
	            {name:'keywords',value:$('#keywords').val()},
	];
	
	$.get(url, data, function(page){//获取返回结果
		
		var html = "";
		if (page.totalCount > 0) {
			
		var onclick="";
			$.each(page.result, function(i,value){//进行数据拼接
				
				if(value.runstatus=="运行中"){
					if(value.type == "true"){
						startNumb++;
					}
					onclick='<a onclick="stop(\''+value.id+'\',event)" href="javascript:void(0)"  class="vm-com-btn vm-shutdown-btn">关机</a><a onclick="restart(\''+value.id+'\',event)" href="javascript:void(0)" class="vm-com-btn vm-reset-btn">重启</a>';
				}else {
					onclick='<a onclick="start(\''+value.id+'\',event)" href="javascript:void(0)"  class="vm-com-btn vm-startingUp-btn">开机</a><a onclick="restart(\''+value.id+'\',event)" href="javascript:void(0)" class="vm-com-btn vm-reset-btn">重启</a>';
				}
//				html+='<input type="checkbox" name="vir"  id=\''+value.id+'\' value=\''+value.id+'\'>'
				html+= '<li  onclick="showVnc(\''+value.vnc+'\')" class="VMstudent-item pr" ><i class="state_strip proceed_state"></i>';
				html+='<h2 class="com-box">';
				html+='<p class="checkbox_box">';
				html+='<input class="checkBox" type="checkbox" name="vir"  onclick="check(event)" value=\''+value.id+'\'>';
				
				if(mid==value.id){
					html+='<span class="checkBox">'+value.imageName+':主机</span>';
					ips.push(value.ip);
					names.push(value.name);
				}else if(sid.indexOf(value.id)>=0){
					html+='<span class="checkBox">'+value.imageName+':从机</span>';
					ips.push(value.ip);
					names.push(value.name);
				}else{
					html+='<span class="checkBox">'+value.imageName+'</span>';
				}
				
				html+='<i class="checkBox-img"></i>';
				html+='</p>';
				html+='</h2>';
//				html+= '<h2>'+ value.imageName+'</h2>';
				html+= '<div class="fsize12 c-67e mt10">状态：'+value.runstatus+'</div>';
				html+= '<div class="fsize12 c-67e mt10">IP：'+ value.ip +'</div>';
				html+= '<div class="fsize12 c-67e mt10">VNC：'+ value.vncInfo +'</div>';
				html+= '<div class="vm-btn mt30">'+onclick+'</div>';
				html+= '</li>';
				});
		} else {
			html += '<div style="margin: auto;">对不起，暂时没有您所需要的数据！</div>';
		}
		$('#studentvm').html(html);//将拼接的结果放到指定的tbody中
		closeGif();//关闭动画
		});
	
};
/**
 * 虚拟机查询
 */
function search(){
	loadGif();//加载动画
	list();
};



/**
 * 安装集群
 */
function install(){
	var virName="";
	 var r=document.getElementsByName("virtual");
	    for(var i=0;i<r.length;i++){
	         if(r[i].checked){
	        	 virName=r[i].value;
	         
	       }
	    } 
	var url = window.ctx + '/virtual/write';
	var data=[
             {name:'ips',value:ips},
             {name:'names',value:names},
             {name:'virName',value:virName}
              ];
	if(virName==""){
		showError("请选择安装集群");
	}else{
		 $.post(url,data,function(reslut){
 			 if(reslut){
 				showError("写入配置文件成功！开始集群安装！");
 				loadGif();
 				var url = window.ctx + '/virtual/install';
 				$.post(url,function(reslut){
 					if(reslut){
 						var url = window.ctx + '/virtual/updateVirtual';
 						var data=[
 					            {name:'virName',value:virName}
 					            ];
 						$.post(url,data,function(reslut){
 							
 						 });
 						closeGif();//关闭动画
 						showError("集群安装成功！");
 						search();
 					}else{
 					closeGif();
 					showError("集群安装失败");
 					}
 				 });
 				}else{
 					showError("写入配置文件失败");
 				}
 			 
	 });
	}

	
};

/**
 * 销毁集群
 */
function destroy(){
	var desIds="";
    
	var r=document.getElementsByName("vir");
		    for(var i=0;i<r.length;i++){
		         if(r[i].checked){
       	         desIds+=r[i].value+",";
		       }
		    }  
	
	
		showDialog();
		//点击确定按钮
//		$(".btn-save").on("click",function(){
		$(".btn-save").unbind('click').click(function(){
			$('.modal').modal('hide');//先隐藏弹出框
			loadGif();//加载动画
			
			//执行删除功能
			var url = window.ctx + '/virtual/destroy';
			var data=[
             {name:'ids',value:desIds}
			          ];
			$.post(url, data,function(feedbak){
				if(feedbak.successful){//成功，提示信息
					setTimeout('search()',3000); 
				}else{
					remind(feedbak.message);
					closeGif();//关闭动画
				}
			});
		});
	  
};

/**
 * 添加虚拟机
 */
function add(){
	var url = window.ctx + '/virtual/to-add';
	$.get(url, function(page){
		$('#content-r').html(page);
		$('#content-r').on('click', '#save',save);
	});
	getData();
};

/**
 * 虚拟机关闭
 * @param id 当前班级管理主键ID
 */
function stop(id,event){
	event.stopPropagation();
	var url = window.ctx + '/virtual/stop/' + id;
	loadGif();//加载动画
	$.post(url, function(feedbak){
		if(feedbak.successful){//成功，提示信息
			setTimeout('search()',8000); 
		}else{
		    showError(feedbak.message);
		    closeGif();//关闭动画
		}
	});
};

/**
 * 虚拟机开机
 * @param id 当前班级管理主键ID
 */
function start(id,event){
	event.stopPropagation();
	if(startNumb<1){//开启
		var url = window.ctx + '/virtual/start/' + id;
		loadGif();//加载动画
		$.post(url, function(feedbak){
			if(feedbak.successful){//成功，提示信息
				setTimeout('search()',3000); 
			}else{
			   showError(feedbak.message);
			   closeGif();//关闭动画
			}
		});
	}else{//当运行虚拟机个数大于1时，提示
		showError("当前只能运行一个虚拟机");
	}
};

/**
 * 虚拟机重启
 * @param id
 */
function restart(id,event){
	event.stopPropagation();
	var url = window.ctx + '/virtual/restart/' + id;
	loadGif();//加载动画
	$.post(url, function(feedbak){
		if(feedbak.successful){//成功，提示信息
			setTimeout('search()',3000); 
		}else{
		   showError(feedbak.message);
		   closeGif();//关闭动画
		}
	});
};
/**
 * 弹出VNC地址
 * @param vnc
 */
function showVnc(vnc) {
	/*if(status != "运行中"){
		showError("虚拟机未开机！")
		return;
	}*/
	window.open(vnc);
}

/**
 * 获取数据
 */
function getData() {
	loadGif();
	var url = window.ctx + '/virtual/getData';
	$.get(url, function(introData){
		var zoneHtml="";//详情
		var imageHtml="";//源
		var flavorsHtml="";//虚拟机类型
		var keyHtml="";//秘钥组
		var taskHtml="";//任务
		var secHtml="";//安全组
		var networkHtml="";//网络组
		if(introData != ''){
		$.each(introData.imageList,function(i, image) {//源
			imageHtml+='<tr>';
			imageHtml+='<td class="Image"style="display: none">'+image.id+'</td>';
			imageHtml+='<td class="Images">'+image.name+'</td>';
			imageHtml+='<td>'+image.size+'</td>';
			imageHtml+='<td>Image</td>';
			imageHtml+='<td>'+image.status+'</td>';
			imageHtml+='<td class="tac"><a class="cd-icon et-icon add-icon" href="javascript:void(0);" title="添加"></a></td>';
			imageHtml+='</tr>';
		});
		//任务列表
		$.each(introData.taskList,function(i, task) {//任务
			if(i == 0){
				taskHtml+='<option value="">--请选择--</option>';
			}
			taskHtml+='<option value="'+task.id+'">'+task.name+'</option>';
		});
		$.each(introData.flavorsList,function(i, flavors) {//虚拟机类型
			flavorsHtml+='<tr>';
			flavorsHtml+='<td class="Flavors"style="display: none">'+flavors.id+'</td>';
			flavorsHtml+='<td>'+flavors.name+'</td>';
			flavorsHtml+='<td>'+flavors.vcpus+'</td>';
			flavorsHtml+='<td>'+flavors.ram+'</td>';
			flavorsHtml+='<td>'+flavors.disk+'</td>';
			flavorsHtml+='<td>'+flavors.swap+'</td>';
			flavorsHtml+='<td>'+flavors.publics+'</td>';
			flavorsHtml+='<td class="tac"><a class="cd-icon et-icon add-icon" href="javascript:void(0);" title="添加"></a></td>';
			flavorsHtml+='</tr>';
		});
		$.each(introData.keyPairList,function(i, keyPair) {//秘钥组
			keyHtml+='<option value="'+keyPair.name+'">'+keyPair.name+'</option>';
		});
		$.each(introData.secgroupList,function(i, secgroup) {//安全组
			secHtml+='<option value="'+secgroup.name+'">'+secgroup.name+'</option>';
		});
		$.each(introData.zoneList,function(i, zone) {//详情
			zoneHtml+='<option value="'+zone.zoneName+'">'+zone.zoneName+'</option>';
		});
		$.each(introData.networkList,function(i, network) {//网络
			networkHtml+='<tr>';
			networkHtml+='<td class="network"style="display: none">'+network.id+'</td>';
			networkHtml+='<td>'+network.name+'</td>';
			networkHtml+='<td>'+network.name+'</td>';
			networkHtml+='<td>'+network.shared+'</td>';
			networkHtml+='<td>'+network.adminStateUp+'</td>';
			networkHtml+='<td>'+network.status+'</td>';
			networkHtml+='<td class="tac"><a class="cd-icon et-icon add-icon" id="add-a" href="javascript:void(0);" onclick="addNetwork(this)"  title="添加"></a><a class="cd-icon et-icon reduce-icon" id="del-a" style="display: none;" onclick="removeNetwork(this)" href="javascript:void(0);" title="添加"></a></td>';
			networkHtml+='</tr>';
		});
		$('#availabilityZone').html(zoneHtml);
		$('#tbody-list').html(imageHtml);
		$('#task').html(taskHtml);
		$('#tbody-flavors').html(flavorsHtml);
		$('#keypairName').html(keyHtml);
		$('#securityGroupsName').html(secHtml);
		$('#tbody-network-list').html(networkHtml);
		}
		closeGif();
		/*源*/
		ramoves("resources");
		/*云主机类型*/
		ramoves("messages");
		$('#task').change("task",changeTask);
		
		alert('<%=Session["develop"] %>');
	});
}

//获取课程分组列表
function getDataList(taskId) {
		var url = window.ctx + '/virtual/getGroupList';
		var data=[
		    {name:'taskId',value:taskId}];
		$.post(url, data,function(list){
			//任务列表
			if(list.length>0){
				var groupHtml = "";
				$.each(list,function(i, group) {//任务
					if(i == 0){
						groupHtml+='<option value="">--请选择--</option>';
					}
					groupHtml+='<option value="'+group.id+'">'+group.name+'</option>';
				});
				$('#group').html(groupHtml);
				$('#group').change("group",changeGroup);
				
			}else{//隐藏
				$('#group_view').hide();
			}
		});
}

/**
 * 源和虚拟机类型选择方法，只能选择一条数据
 * @param a
 * @returns {Boolean}
 */
function ramoves(a){
	$("#"+a+" .add-icon").on("click",function(){
		var $tr = $(this).parents("tr").html();
		$("#"+a+" .table-allot tbody").html("<tr>"+$tr+"</tr>");
		$("#"+a+" .table-allot tbody").find('a').removeClass("add-icon").addClass("reduce-icon").bind("click",function(){
			$(this).parents("tr").remove();
		})
	})
	$("#"+a+" .reduce-icon").on("click",function(){
		$(this).parents("tr").remove();
	})
	return false;
}

/**
 * 添加网络数据
 */
function addNetwork(e){
	var tempFlavorsTr = '<tr>' + $(e).parent().parent().html() + '</tr>';
	$(e).parent().parent().remove();
	$('#network-tbody').append(tempFlavorsTr);
	$('#network-tbody').find('tr:last').find('#add-a').hide();
	$('#network-tbody').find('tr:last').find('#del-a').show();
};

/**
 * 移除已经网络数据
 * @param e
 */
function removeNetwork(e){
	var tempFlavorsTr = '<tr>' + $(e).parent().parent().html() + '</tr>';
	$(e).parent().parent().remove();
	$('#tbody-network-list').append(tempFlavorsTr);
	$('#tbody-network-list').find('tr:last').find('#add-a').show();
	$('#tbody-network-list').find('tr:last').find('#del-a').hide();
	
};


function save(){
	/*$('.progress').hide();*/
	var name = $('#name').val();
	
	var availabilityZone = $('#availabilityZone').val();
//	var adminPass = $('#adminPass').val();
	var count = $('#count').val();
	var account = $('#account').val();
	/*var imageid = $('#list-tbody-resources').find('tr:first').find('#add-a').attr('data-id');
	var flavorid = $('#list-tbody-flavors').find('tr:first').find('#add-a').attr('data-id');*/
	var imageid=$("#Image-tbody").find('td:first').text();
	var flavorid=$("#Flavors-tbody").find('td:first').text();
	var networkEntities = [];
	/*$('#list-tbody-network tr').each(function(e){
		var id = $(this).find('#add-a').attr('data-id');
		if(id != undefined && id != '' && id != null)
			networkEntities.push({'id' : id});
	});*/
	$("#network-tbody").find('tr').each(function(index,element){
		networkEntities.push($(element).find('td:first').text());
	});
	var securityGroupsName = $('#securityGroupsName').val();
	var keypairName = $('#keypairName').val();
	if(name == undefined || name == '' || name == null){
		$('#name').parent().addClass('has-error');
		removeClass('home-li');
		showError("虚拟机名称不能为空!");
		return false;
	}
	if(availabilityZone == undefined || availabilityZone == '' || availabilityZone == null){
		$('#availabilityZone').parent().addClass('has-error');
		removeClass('home-li');
		return false;
	}
	if(count == undefined || count == '' || count == null || count <= 0){
		$('#count').parent().addClass('has-error');
		removeClass('home-li');
		return false;
	}
	if(account == undefined || account == '' || account == null || account <= 0){
		$('#account').parent().addClass('has-error');
		removeClass('home-li');
		showError("学号不能为空!");
		return false;
	}
	if(imageid == undefined || imageid == '' || imageid == null){
		removeClass('resources-li');
		return false;
	}
	if(networkEntities == undefined || networkEntities == '' || networkEntities == null || networkEntities.length <= 0){
		removeClass('network-li');
		return false;
	}
	loadGif();
	/*var data={
			"name": name,
			"imageId" : imageid,
			"flavorId" : flavorid,
			"adminPass" : adminPass,
			"availabilityZone" : availabilityZone,
			"count" : count,
			"networkEntities" : networkEntities,
			"keyName" : keypairName,
			"securityGroupsName" : securityGroupsName
	};*/
	var data = [//查询的参数
	            {name:'name',value:$('#name').val()},
	            {name:'imageId',value:imageid},
	            {name:'flavorId',value:flavorid},
	            {name:'keyName',value:$("#keypairName").val()},
	            {name:'securityGroupsName',value:$("#securityGroupsName").val()},
//	            {name:'adminPass',value:$("#adminPass").val()},
	            {name:'availabilityZone',value:$("#availabilityZone").val()},
	            {name:'count',value:$("#count").val()},
	            {name:'account',value:$("#account").val()},
	            {name:'id',value:networkEntities},
	            {name:'taskId',value:taskId},
	            {name:'groupId',value:groupId}
	];
	var url = window.ctx + "/virtual/save";
	$.post(url, data, function(feedbak){//获取返回结果
		closeGif();
		if(feedbak.successful){//成功，提示信息
			var $box = $(".modal-box .modal-content");
			$box.append("<div class='modal-footer'><a class='btn btn-primary btn-save mr30'>确定</a></div>");
			if(feedbak.message.length>0){
				showError(feedbak.message);
			}else{
				showError("虚拟机创建成功，点击确定返回！");
			}
			$(".btn-save").on("click",function(){
				$('.modal').modal('hide');//先隐藏弹出框
				$box.find(".btn-save").remove();
				back();
			})
		}else{
			showError(feedbak.message);
		}
	});
};

function checkVirtualName(account){
	var urls = window.ctx + "/virtual/checkVirtualName";
	var datas = [//查询的参数
	            {name:'name',value:$("#name").val()}
	];
	$.post(urls, datas, function(feedbak){//获取返回结果
		if(feedbak){//成功，提示信息
			showError("输入的虚拟机名称已经存在!");
			$('#name').parent().addClass('has-error');
			removeClass('home-li');
//			$("#name").val("");
			return false;
		}else{
			return true;
		}
	});
}

function checkAccount(account){
	var urls = window.ctx + "/virtual/checkStudentAccount";
	var datas = [//查询的参数
	            {name:'account',value:account}
	];
	$.post(urls, datas, function(feedbak){//获取返回结果
		if(feedbak){//成功，提示信息
			showError("输入的学号或工号不存在!");
			$('#account').parent().addClass('has-error');
			removeClass('home-li');
//			$("#count").val("");
			return false;
		}else{
			return true;
		}
	});
}

function removeClass(tagid){
	$('#tab-ul li').each(function(e){
		$(this).removeClass('active');
	});
	$('.tab-content div.tab-pane').each(function(e){
		$(this).removeClass('active');
	});
	$('#' + tagid).addClass('active');
	$('#' + tagid.split('-')[0]).addClass('active');
}

/**
 * 返回
 */
function back(){
	window.location.href=window.ctx +"/virtual";
};



/**
 * 下拉选择事件--- 任务
 */
var changeTask = function() { 
	 var id= $(this).attr("id")
	 var value= $(this).val();
	 $("#"+id+' '+'option').attr('selected',false); 
	 $("#"+id).val(value);
	 $("#"+id+' '+'option[value='+value+']').attr("selected",true) 
	 taskId = value;
	 getDataList(value);
	}
/**
 * 下拉选择事件---课程分组
 */
var changeGroup =  function() {
	var id= $(this).attr("id")
	 var value= $(this).val();
	 $("#"+id+' '+'option').attr('selected',false); 
	 $("#"+id).val(value);
	 $("#"+id+' '+'option[value='+value+']').attr("selected",true) 
	 groupId = value;
}
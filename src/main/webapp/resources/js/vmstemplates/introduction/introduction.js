/**
 * @description 虚拟机入门JS脚本
 * @auth wwq
 */
var taskId = "";
var groupId = "";

var introduction = {};
introduction.prePageHtml = null;
$(function(){//绑定事件
	$('#content-r').on('click', '#create-virtual',addVirtual);
	introduction.prePageHtml = $('#content-r').html();
	$('#content-r').on('click', '#btn-cancel', backHome);
});

/**
 * 返回
 */
function back(){
	window.location.href=window.ctx +"/virtual";
};
/**
 * 返回
 */
function backHome(){
	window.location.href=window.ctx +"/introduction";
};

/**
 * 添加虚拟机
 */
function addVirtual(){
	var url = window.ctx + '/introduction/to-add';
	$.get(url, function(page){
		$('#content-r').html(page);
		$('#content-r').on('click', '#save',save);
	});
	getData();
};
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
 * 获取数据
 */
function getData() {
	loadGif();
	var url = window.ctx + '/introduction/getData';
	$.get(url, function(introData){
		var zoneHtml="";//详情
		var imageHtml="";//源
		var taskHtml="";//任务
		var flavorsHtml="";//虚拟机类型
		var keyHtml="";//秘钥组
		var secHtml="";//安全组
		var networkHtml="";//网络组
		if(introData.imageList != null){
		$.each(introData.imageList,function(i, image) {//源
			imageHtml+='<tr>';
			imageHtml+='<td class="Image"style="display: none">'+image.id+'</td>';
			imageHtml+='<td class="Images">'+image.name+'</td>';
			imageHtml+='<td>'+(((image.size)/(1024))/1024).toFixed(2)+' M</td>';
			imageHtml+='<td>Image</td>';
			imageHtml+='<td>'+image.description+'</td>';
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
			flavorsHtml+='<td>'+(flavors.ram)/1024+' G</td>';
			flavorsHtml+='<td>'+flavors.disk+' G</td>';
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
		ramoves("flavors");
		$('#task').change("task",changeTask);
	});
}
//获取课程分组列表
function getDataList(taskId) {
		var url = window.ctx + '/introduction/getGroupList';
		var data=[
		    {name:'taskId',value:taskId}];
		$.post(url, data,function(list){
			//任务列表
			if(list.length>0){
				$('#group_view').show();
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
 * 导入模板
 */
function importTemplate() {
	var url = window.ctx + '/introduction/to-import';
	$.get(url, function(page) {
		$('#content-r').html(page);
		introduction.validateForm('add-introduction-form');
	});
}


/**
 * js提交验证
 */
introduction.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{
			debug:false,
			name:{
				required:true,
				stringMaxLength:20,
			},
			textfield:{//上传文件
				required:true,
			},	
			description:{//描述
				required:false,
				stringMaxLength:20,
			},
		},
		messages:{
			name:{
				required:'请输入模板名称!',
			},
			textfield:{//上传文件
				required:'请选择文件上传!',
		    },
		},
		 submitHandler:function(form){
			 loadGif();
				var url = window.ctx + '/introduction/imports';
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
							remind("success-remind-box");
							//$('#content-r').html(introduction.prePageHtml);
							closeGif();
							window.location.href = window.ctx + '/template';
						}else{
							showError(feedbak.message);	
						}
					}
			    });  
			}
	});
}; 


function save(){
	var flaid =$('#flaid').val();
	var name = $('#name').val();
	var availabilityZone = $('#availabilityZone').val();
	var count = $('#count').val();
	var account = $('#account').val();
	var imageid=$("#Image-tbody").find('td:first').text();
	var flavorid=$("#Flavors-tbody").find('td:first').text();
	var networkEntities = [];
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
	if(flavorid == undefined || flavorid == '' || flavorid == null){
		removeClass('flavors-li');
		return false;
	}
	if(networkEntities == undefined || networkEntities == '' || networkEntities == null || networkEntities.length <= 0){
		removeClass('network-li');
		return false;
	}
	if(flaid == flavorid){
		groupId = "";
	}
	loadGif();
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
	var url = window.ctx + "/introduction/save";
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
	var urls = window.ctx + "/introduction/checkVirtualName";
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
	var urls = window.ctx + "/introduction/checkStudentAccount";
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



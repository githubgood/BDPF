/**
 * @description 资源管理JS脚本
 * @auth wanglf
 */

var resource = {};

$(function(){
	resource.validateForm('edit-res-form');
});

/**
 * 输入其中一个资源比例的数值，自动计算另外一个资源比例的数值
 * @param obj 当前资源比例的类型
 */
function changeNum(obj){
	//课堂比例的值
	var num1 = $("#classExperimentProportion").val();
	//开放比例的值
	var num2 = $("#openExperimentProportion").val();
	if(obj == 0){//课堂比例
		$("#openExperimentProportion").val(10-num1);
	}
	if(obj == 1){//开放比例
		$("#classExperimentProportion").val(10-num2);
	}
}

/**
 * js提交验证
 */
resource.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{
			debug:false,
			Id:{
				required:false,
			},
			classExperimentProportion:{
				required:true,
				stringMaxLength:2,//最大2个字符
				number:true,//只可输入数字
				gt2:11,//输入1-10
			},
			openExperimentProportion:{
				required:true,
				stringMaxLength:2,//最大2个字符
				number:true,//只可输入数字
				gt2:11,//输入1-10
			},
		},
		messages:{
			openExperimentProportion:{
				required:'请设置开放比例!',
			},
			classExperimentProportion:{
				required:'请设置课堂比例!',
			},
		},
		 submitHandler:function(form){
			var url = window.ctx + '/resource/save';
			var data = $('#'+tagid).serializeArray();
			$.post(url, data, function(feedbak){
				if (feedbak.successful) {
					// 保存成功
					remind("success-remind-box");
				} else {
					remind("fail-remind-box");
				}
			});
		}
	});
}; 


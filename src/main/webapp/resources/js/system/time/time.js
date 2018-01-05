/**
 * @description 实验时间设置JS脚本
 * @auth wanglf
 */

var time = {};

$(function(){
	time.validateForm('edit-time-form');
});

/**
 * js提交验证
 */
time.validateForm = function(tagid){
	validator = $("#" + tagid).validate({
		rules :{
			debug:false,
			Id:{
				required:false,
			},
			minTime:{
				stringMaxLength:10,//最大10个字符
				required:true,
				number:true,
			},
			maxTime:{
				stringMaxLength:10,//最大10个字符
				required:true,
				number:true,
			},
			addTime:{
				stringMaxLength:10,//最大10个字符
				required:true,
				number:true,
			},
			maxNumber:{
				stringMaxLength:10,//最大10个字符
				required:true,
				number:true,
			},
		},
		messages:{
			minTime:{
				required:'请输入最小时间!',
			},
			maxTime:{
				required:'请输入最大时间!',
			},
			addTime:{
				required:'请输入增加时间!',
			},
			maxNumber:{
				required:'请输入上限人数!',
			},
		},
		 submitHandler:function(form){
			 var min = $('#minTime').val();
			 var max = $('#maxTime').val();
			 var add = $('#addTime').val();
			 var maxNumber = $('#maxNumber').val();
			 if(parseInt(min) > parseInt(max)){
				 showError("最小时间要小于最大时间。");
				 return;
			 }
			 if((parseInt(add)+parseInt(min))> parseInt(max)){
				 showError("最小时间加上增加的时间要小于或等于最大时间。");
				 return;
			 }
			var url = window.ctx + '/time/save';
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

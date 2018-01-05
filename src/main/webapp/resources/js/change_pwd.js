/**
 * 修改密码js
*/
var pwd = {};
/*内容高度自适应*/
	function height(){
		var $main = $(".bd-pwd-content"),
			h = document.documentElement.clientHeight-290;
		$main.height(h);
		return;
	}
	$(function(){
		height()
		pwd.validateForm('change-pwd-form');
	})
	$(".remind-text").show();
	
	/**
	 * js提交验证
	 */
	pwd.validateForm = function(tagid){
		validator = $("#" + tagid).validate({
			rules :{
				debug:false,
				Id:{
					required:false,
				},
				old_pwd:{
					stringMaxLength:12,
					ispassword:true,
					required:true,
					remote:{//根据当前账号名称查询数据库否是重复
			               type:"POST",
			               url:window.ctx + "/user/checkPassword",
			               data:{
			            	   Id:function(){ return $("#Id").val();},
			            	   old_pwd:function(){ return $("#old_pwd").val();}
			               }
			         },
				},
				password:{
					stringMaxLength:12,
					ispassword:true,
					required:true,
				},
				sure_pwd:{
					stringMaxLength:12,
					ispassword:true,
					equalTo: "#password",
					required:true,
				},
			},
			messages:{
				old_pwd:{
					required:'请输入旧的密码!',
					remote:'旧密码不存在!',
				},
				password:{
					required:'请输入新的密码!',
				},
				sure_pwd:{
					required:'请再次输入新的密码!', 
					equalTo: "对不起，您2次输入的密码不一致!",
				},
			},
			 submitHandler:function(form){
				var url = window.ctx + '/user/changepwd';
				var data = $('#'+tagid).serializeArray();
				$.post(url, data, function(feedbak){
					if (feedbak.successful) {
						// 保存成功
						remind("success-remind-box");
						window.location.href = window.ctx+"/login/loginout";
					} else {
						remind("fail-remind-box");
					}
				});
			}
		});
	}; 
	
	
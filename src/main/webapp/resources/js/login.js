$(function(){
	//placeholder兼容ie
	//判断浏览器是否支持placeholder属性
	supportPlaceholder='placeholder'in document.createElement('input'),
		placeholder=function(input){
			var text = input.attr('placeholder'),
				defaultValue = input.defaultValue;
			if(!defaultValue){
				input.val(text).addClass("phcolor");
			}
			input.focus(function(){
				if(input.val() == text){
					$(this).val("");
				}
			});
			input.blur(function(){
				if(input.val() == ""){
					$(this).val(text).addClass("phcolor");
				}
			});
			//输入的字符不为灰色
			input.keydown(function(){
				$(this).removeClass("phcolor");
			});
		};
	//当浏览器不支持placeholder属性时，调用placeholder函数
	if(!supportPlaceholder){
		$('input').each(function(){
			text = $(this).attr("placeholder");
			if($(this).attr("type") == "text"||$(this).attr("type") == "password"){
				placeholder($(this));
			}
		});
	}
	//登录回车事件
	document.onkeydown = function(e){
		if(e.keyCode == 13){
			login();
	  	}
	};
});
/**
 * 登录
 */
function login() {
	var username = $("#username").val();
	var password = $("#password").val();
	var type = $('input:radio:checked').val();
		tipHide();
	if (username == "") {
		tip(".user-box", "请输入用户名！");
	} else if (username != "" && !isUser(username)) {
		tip(".user-box", "请输入2-16位字母，数字组合！");
	} else if (password == "") {
		tip(".psw-box", "请输入密码！");
	} else if (password != "" && !isPwd(password)) {
		tip(".psw-box", "请输入2-12位字母，数字组合");
	} else {
		tipHide();
		loadGif();
		var url = window.ctx + '/login/check';
		var data = [ {
			name : 'username',
			value : $("#username").val()
		}, {
			name : 'password',
			value : $("#password").val()
		} ];
		$.get(url, data, function(result) {
			closeGif();//关闭加载动画
			if (result == "0") {
				tip(".user-box", "用户名或密码错误！");
			} else if (result == "1") {//教师
				window.location.href = window.ctx + '/home';
			} else if(result == "2"){//学生
				window.location.href = window.ctx + '/home';
			}
		});
	}
}
/**
 * 自动检测输入项
 */
$(function() {
	$('#username').bind('input propertychange', function() {
		var username = $("#username").val();
			tipHide();
		if (username == "") {
			tip(".user-box", "请输入用户名！");
		} else if (username != "" && !isUser(username)) {
			tip(".user-box", "请输入2-16位字母，数字组合！");
		} else {
			tipHide();
		}
	});
	$('#password').bind('input propertychange',function() {
		var password = $("#password").val();
			tipHide();
		if (password == "") {
			tip(".psw-box", "请输入密码！");
		} else if (password != "" && !isPwd(password)) {
			tip(".psw-box", "请输入2-12位字母，数字组合！");
		} else {
			tipHide();
		}
	});
});

/**
 * 提示信息显示汽泡
 * 
 * @param inputstyle
 *            显示控件
 * @param message
 *            提示消息
 */
function tip(inputstyle, message) {
	$(inputstyle).poshytip({
		content : message,
		showOn : 'none',
		alignTo : 'target',
		alignX : 'inner-left',
		offsetX : 5,
		offsetY : 0
	});
	$(inputstyle).poshytip("show");
}
/**
 * 汽泡消失
 */
function tipHide() {
	$(".tip-yellow").hide();
}
/*获得焦点失去焦点*/

function login_bg(){
	var $bg = $(".login-box input");
	$bg.removeClass("error-bg").parent().removeClass("error-bg");
	$bg.on("focus",function(){
		$(this).addClass("error-bg").parent().addClass("error-bg");
	}).on("blur",function(){
		$(this).removeClass("error-bg").parent().removeClass("error-bg")
	})
}
$(function(){
	login_bg()
})
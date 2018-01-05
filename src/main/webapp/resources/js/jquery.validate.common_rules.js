
$(document).ready(function(){
	
	// 邮政编码验证      
	jQuery.validator.addMethod("isZipCode", function(value, element) {      
		var tel = /^[0-9]{6}$/;      
		return this.optional(element) || (tel.test(value));      
	}, "请正确填写您的邮政编码");   
	
	// 联系电话(手机/电话皆可)验证  
	jQuery.validator.addMethod("isPhone", function(value,element) {  
		var length = value.length;  
		var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;  
		var tel = /^\d{3,4}-?\d{7,9}$/;  
		return this.optional(element) || (tel.test(value) || mobile.test(value));  
	}, "请正确填写您的联系电话");
	
	// 电话号码验证
	jQuery.validator.addMethod("isTel", function(value, element) {      
		var tel = /^\d{3,4}-?\d{7,9}$/;    //电话号码格式010-12345678  
		return this.optional(element) || (tel.test(value));      
	}, "请正确填写您的电话号码"); 
	
	// 手机号码验证      
	jQuery.validator.addMethod("isMobile", function(value, element) {      
		var length = value.length;  
		var mobile = /^1[34578]\d{9}$/;
		return this.optional(element) || (length == 11 && mobile.test(value));      
	}, "请正确填写您的手机号码");
	
	// 实验时间验证，只包含数字和小数点
	jQuery.validator.addMethod("isNumber", function(value, element) {      
		var length = value.length;  
		var numbers = /^\d+(\.\d+)?$/;  
		return this.optional(element) || numbers.test(value);      
	}, "输入内容只包含数字或小数");
	
	// 身份证号码验证      
	jQuery.validator.addMethod("isIdCardNo", function(value, element) {
     //return this.optional(element) || /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(value)||/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/.test(value);      
		return this.optional(element) || /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/.test(value);
	}, "请正确输入您的身份证号码");
	
	// 字符最小长度验证（一个中文字符长度为2）
	jQuery.validator.addMethod("stringMinLength", function(value, element, param) {
		var length = value.length;
		for ( var i = 0; i < value.length; i++) {
			if (value.charCodeAt(i) > 127) {
				length++;
			}
		}
		return this.optional(element) || (length >= param);
	}, $.validator.format("长度不能小于{0}!"));
	
	// 字符最大长度验证（一个中文字符长度为2）
	jQuery.validator.addMethod("stringMaxLength", function(value, element, param) {
		var length = value.length;
		for ( var i = 0; i < value.length; i++) {
			if (value.charCodeAt(i) > 127) {
				length++;
			}
		}
		return this.optional(element) || (length <= param);
	}, $.validator.format("长度不能大于{0}!"));
	
	// 字符验证      
	jQuery.validator.addMethod("stringCheck", function(value, element) {      
		return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);      
	}, "只能包括中文字、英文字母、数字和下划线");
	
	// 中文字两个字节      
	jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {      
		var length = value.length;      
			for(var i = 0; i < value.length; i++){      
			if(value.charCodeAt(i) > 127){      
				length++;      
			}
		}      
		return this.optional(element) || ( length >= param[0] && length <= param[1] );
	}, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)"); 
	
	// 字符验证
	jQuery.validator.addMethod("string", function(value, element) {
		return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
	}, "不允许包含特殊符号!");
	
	// 必须以特定字符串开头验证
	jQuery.validator.addMethod("begin", function(value, element, param) {
		var begin = new RegExp("^" + param);
		return this.optional(element) || (begin.test(value));
	}, $.validator.format("必须以 {0} 开头!"));
	
	// 验证两次输入值是否不相同
	jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
		return value != $(param).val();
	}, $.validator.format("两次输入不能相同!"));
	
	// 验证值不允许与特定值等于
	jQuery.validator.addMethod("notEqual", function(value, element, param) {
		return value != param;
	}, $.validator.format("输入值不允许为{0}!"));
	
	// 验证值必须大于特定值(不能等于)
	jQuery.validator.addMethod("gt", function(value, element, param) {
		return value > param;
	}, $.validator.format("输入值必须大于{0}!"));	
	
	// 验证值必须小于特定值(能等于)
	jQuery.validator.addMethod("gt2", function(value, element,param) {
		return value > 0&&value < param;
	}, "输入值必须大于0且小于等于{0}!");
	
	// 验证值小数位数不能超过两位
	jQuery.validator.addMethod("decimal", function(value, element) {
		var decimal = /^-?\d+(\.\d{1,2})?$/;
		return this.optional(element) || (decimal.test(value));
	}, $.validator.format("小数位数不能超过两位!"));
	
	//字母数字
	jQuery.validator.addMethod("alnum", function(value, element) {
		return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
	}, "只能包括英文字母和数字");
	
	//字母
	jQuery.validator.addMethod("letter", function(value, element) {
		return this.optional(element) || /^[a-z]+$/.test(value);
	}, "只能包括英文字母,区分大小写");
	
	//数字
	jQuery.validator.addMethod("number", function(value, element) {
//		return this.optional(element) || /^[0-9]+(.[0-9]{1,3})?+$/.test(value);
		return this.optional(element) || /^[0-9]+$/.test(value);
	}, "只能包括数字");
	
	// 汉字
	jQuery.validator.addMethod("chcharacter", function(value, element) {
		var tel = /^[\u4e00-\u9fa5]+$/;
		return this.optional(element) || (tel.test(value));
	}, "请输入汉字");
	
	//字母数字
	jQuery.validator.addMethod("alnum2", function(value, element) {
		return this.optional(element) || /^\w+$/.test(value);
	}, "只能包括英文字母和数字、下划线");
	
	//用户账号
	jQuery.validator.addMethod("useraccount", function(value, element) {
		return this.optional(element) || /^[A-Za-z0-9][a-zA-Z0-9]{1,15}$/.test(value);
	}, "请输入2-16位字母，数字组合.");
	
	//用户密码
	jQuery.validator.addMethod("ispassword", function(value, element) {
		return this.optional(element) || /^[A-Za-z0-9][a-zA-Z0-9]{1,11}$/.test(value);
	}, "请输入2-12位字母，数字组合.");
	
	//验证邮箱    
    jQuery.validator.addMethod("email",function(value,element){    
            var myreg = /^[_a-zA-Z0-9\-]+(\.[_a-zA-Z0-9\-]*)*@[a-zA-Z0-9\-]+([\.][a-zA-Z0-9\-]+)+$/;  
            return  this.optional(element) || myreg.test(value);
     } ,  "请输入有效的E_mail");
	//教师工号
	jQuery.validator.addMethod("staCode", function(value, element) {
		return this.optional(element) || /T[A-Za-z0-9][a-zA-Z0-9]{4,11}$/.test(value);
	}, "请输入6-12位字母，数字组合.必须大写T开头");
	//学生学号
	jQuery.validator.addMethod("stuCode", function(value, element) {
		return this.optional(element) || /S[A-Za-z0-9][a-zA-Z0-9]{4,11}$/.test(value);
	}, "请输入6-12位字母，数字组合.必须大写S开头");
	
	jQuery.validator.addMethod("isSelect",function(value,element){
		 return element.checked             
	}," ！请同意条款"); 
	
	//文件类型判断
	jQuery.validator.addMethod("checkFileType", function(value, element) {
		var fileType=$("#fileType").val();
		var txtFoo=$("#txtFoo").val();
	    //获得上传文件名
	    var suffix=txtFoo.substring(txtFoo.lastIndexOf('.')+1,txtFoo.length)
	    //切割出后缀文件名
	    if(fileType==0){//文档
			if(suffix=="doc" ||suffix=="xls"  || suffix=="txt" || suffix=="pdf"){
				return true;
			}else{
				return false;
			}
		}else if(fileType==1){//视频
			if(suffix=="avi" ||suffix=="mp4"||suffix=="mov"){
				return true;
			}else{
				return false;
			}
		}else if(fileType==2){//图片
			if(suffix=="bmp" ||suffix=="png"||suffix=="jpeg"||suffix=="jpg"||suffix=="gif"){
				return true;
			}else{
				return false;
			}
		}else if(fileType==3){//数据
			if(suffix=="csv" ||suffix=="json"||suffix=="xml"||suffix=="xls"){
				return true;
			}else{
				return false;
			}
		}else if(fileType==4){//工具
			if(suffix=="rpm" ||suffix=="gz" ||suffix=="exe"){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	},"上传文件与文件类型不匹配!");
	
	jQuery.validator.addMethod("isclassId",function(value,element){ 
	    var myreg =  $('input:checkbox[name=classId]:checked'); 
	    return  this.optional(element) || myreg.length!=0;
	} ,  "请先选择班级！");
	
	//字母
	jQuery.validator.addMethod("numberMax4", function(value, element) {
		return this.optional(element) || /^([1-4]|4)$/.test(value);
	}, "最大数为4");
});

/**
 * 页面字符串判空处理
 */
function empty(item){
	if(typeof(item) == "undefined" || typeof(item) == "null" || item== null || item== "null" ){
		item="";
	}
	return item;
}



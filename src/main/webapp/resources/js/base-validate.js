/*基础验证方法*/

/**
 * 验证是否满足邮箱格式
 */
function isEmail(str) {
	var myreg = /^[_a-zA-Z0-9\-]+(\.[_a-zA-Z0-9\-]*)*@[a-zA-Z0-9\-]+([\.][a-zA-Z0-9\-]+)+$/;
	if(myreg.test(str)){
		return true;
	}else{
		return false;//提示" 请输入有效的E_mail地址"
	}
}

/**
 * 邮政编码验证
 * @param str
 */
function isZipCode(str) {
	var tel = /^[0-9]{6}$/;  
	if(tel.test(str)){
		return true;
	}else{
		return false;//提示" 请正确填写您的邮政编码"
	}
}

/**
 * 联系电话(手机/电话皆可)验证
 * @param str
 */
function isPhone(str) {
	var length = value.length;  
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;  
	var tel = /^\d{3,4}-?\d{7,9}$/;  
	if(mobile.test(str) || tel.test(str)){
		return true;
	}else{
		return false;//提示" 请正确填写您的联系电话"
	}
}

/**
 * 电话号码验证    
 * @param str
 */
function isTel(str) {
	var tel = /^\d{3,4}-?\d{7,9}$/;    //电话号码格式010-12345678  
	if(tel.test(str)){
		return true;
	}else{
		return false;//提示" 请正确填写您的电话号码"
	}
}

/**
 * 手机号码验证
 * @param str
 */
function isMobile(str) {
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;  
	if(mobile.test(str)){
		return true;
	}else{
		return false;//提示" 请正确填写您的手机号码"
	}
}

/**
 * 身份证号码验证
 * @param str
 */
function isIdCardNo(str) {
	var card = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;
	if(card.test(str)){
		return true;
	}else{
		return false;//提示" 请正确输入您的身份证号码"
	}
}

/**
 * 用户名验证
 * @param str
 */
function isUser(str) {
	var tel = /^[a-zA-Z0-9]{1,16}$/;
	if(tel.test(str)){
		return true;
	}else{
		return false;//提示" 用户名长度为5到16位字母，数字组合"
	}
}


/**
 * 用户密码验证
 * @param str
 */
function isPwd(str) {
	var pwd = /^[a-zA-Z0-9]{2,12}$/;
	if(pwd.test(str)){
		return true;
	}else{
		return false;//提示"请输入2-12位字母，数字组合"
	}
}

/**
 * 学生学号验证
 * @param str
 */
function stuCode(str) {
	var tes = /S[A-Za-z0-9][a-zA-Z0-9]{4,11}$/;
	if(tes.test(str)){
		return true;
	}else{
		return false;//提示"请输入6-12位字母，数字组合.必须大写S开头"
	}
}

/**
 * 教师工号验证
 * @param str
 */
function staCode(str) {
	var tes = /T[A-Za-z0-9][a-zA-Z0-9]{4,11}$/;
	if(tes.test(str)){
		return true;
	}else{
		return false;//提示"请输入6-12位字母，数字组合.必须大写T开头"
	}
}

/**
 * 用户账号
 * @param str
 */
function useraccount(str) {
	var tes = /^[A-Za-z0-9][a-zA-Z0-9]{1,19}$/;
	if(tes.test(str)){
		return true;
	}else{
		return false;//提示"请输入2-20位字母，数字组合"
	}
}

/**
 * 字母数字
 * @param str
 */
function alnum2(str) {
	var tes = /^\w+$/;
	if(tes.test(str)){
		return true;
	}else{
		return false;//提示"只能包括英文字母和数字、下划线"
	}
}

/**
 * 汉字
 * @param str
 */
function chcharacter(str) {
	var tes = /^[\u4e00-\u9fa5]+$/;
	if(tes.test(str)){
		return true;
	}else{
		return false;//提示"请输入汉字"
	}
}

/**
 * 
 * @param str
 */
function number(str) {
	var tes = /^[0-9]+$/;
	if(tes.test(str)){
		return true;
	}else{
		return false;//提示"只能包括数字"
	}
}

/**
 * 字母数字
 * @param str
 * @returns {Boolean}
 */
function alnum(str) {
	var tes = /^[a-zA-Z0-9]+$/;
	if(tes.test(str)){
		return true;
	}else{
		return false;//提示"只能包括英文字母和数字"
	}
}

/**
 * 验证值小数位数不能超过两位
 * @param str
 * @returns {Boolean}
 */
function decimal(str) {
	var tes = /^-?\d+(\.\d{1,2})?$/;
	if(tes.test(str)){
		return true;
	}else{
		return false;//提示"小数位数不能超过两位!"
	}
}

/**
 * 字符验证
 * @param str
 * @returns {Boolean}
 */
function string(str) {
	var tes = /^[\u0391-\uFFE5\w]+$/;
	if(tes.test(str)){
		return true;
	}else{
		return false;//提示"不允许包含特殊符号!"
	}
}


//成功，失败直接显示 
function remind(y){
		$("."+y).stop().animate({"opacity":"1"},{step:function(){
		$("body").addClass("modal-open").css("padding-right","17px");
		$(this).show();
		},duration:1000}).delay(1000).animate({"opacity":"0"},{step:function(){
		$("body").removeClass("modal-open").css("padding-right","0px");
		$(this).hide();
		},duration:1000});
}

//显示提示框
function showDialog(){
	$('.modal-box-1').modal('show');
	$(".modal-box-1.modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
}
//显示时间提示框
function showTimeDialog(){
	$('.modal-box-time').modal('show');
	$(".modal-box-time.modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
}
//显示警告框
function showError(str){
	$('.modal-box').modal('show');
	$(".modal-box .modal-body p").text(str);
	$(".modal-box .modal-dialog").width("400");
	$(".modal-box.modal_align").css({"display":"table","height":"100%","width":"400px","left":"50%","margin-left":"-200px"})
}
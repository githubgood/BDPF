<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="add-area-form" class="form-inline">
			<p class="et-manage-title">
				<span>添加数据</span>
			</p>
			<div id="message" class="et-teachingPlan-content">
			<div class="et-manage-operate">

				<div class="bd-infor-content of" style="margin-top:0;">
				<div class="bd-infor-right bd-infor-edit of">
				
					<div class="form-group" id="">
							标<i style="margin-left: 2em;"></i>题：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control"  id="title" name="title" placeholder="1~50个字符">
							</div>
							
						<div class="form-group" id="">
									数据领域：<i style="margin-left:1em;"></i>
									<select class="form-control" id="siteId" name="siteId">
									<c:forEach items="${site}" var="res">
                                    <option value="${res.id}">${res.name}</option>
                                    </c:forEach>
									</select>
							</div>
								<div class="form-group" id="">
								摘<i style="margin-left: 3em;"></i>要：
								<input type="text" class="form-control" id="abstract" name="abstractArea" placeholder="1~100个字符">
								</div>
							

							<div class="form-group" id="">
							关<i style="margin-left: 1em;"></i>键<i style="margin-left: 1em;"></i>字：
							<input type="text" class="form-control" id="keyword" name="keyword" placeholder="1~20个字符">
							
							</div>
							
							<div class="form-group" id="">
							数据提供方：
							<input type="text" class="form-control" id="data_provider_employer" name="data_provider_employer" placeholder="1~50个字符">
							</div>
							
							<div class="form-group" id="">
							接口描述：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control" id="api_description" name="api_description" placeholder="1~100个字符">
							</div>
							
							<div class="form-group" id="">
							文件地址：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control" id="api_address" name="api_address" placeholder="1~50个字符">
							</div>
							
							<div class="form-group" id="">
							支持格式：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control" id="api_format" name="api_format" placeholder="1~20个字符">
							</div>
							
							<div class="form-group" id="">
							请求示例：<i style="margin-left: 1em;"></i>
							<input type="text" class="form-control" id="api_example" name="api_example" placeholder="1~100个字符">
							</div>
											
					</div>
						
					</div>
					<div class="bd-infor-left mt20" >
					
			     		<div id="addTable"></div>
					</div>
					
				</div>
<!-- 				<a href="javascript:void(0);" class="btn bd-down-btn bd-upload-btn mt10" onclick="showBox()"> -->
<!-- 									<i class="db-icon down-icon"></i> -->
<!-- 									生成示例 -->
<!-- 								</a> -->
				<div class="edit-btns tac">
				
 			     <a href="javascript:void(0);" class="btn bd-down-btn bd-upload-btn" onclick="showBox()">
							<!-- <i class="db-icon down-icon"></i> -->
							生成示例
						</a>
					<input id="btn-save" name="btn-save" type="submit" class="btn btn-primary btn-save mr30" value="添                加" /> 	
<!-- 					<button type="button" onclick="save()" class="btn btn-primary btn-save mr30">保存</button> -->
					<button type="button" onclick="addAreaCancal()" class="btn btn-default btn-cancel"  data-dismiss="modal">取消</button>
				</div>
			</div>
	
	
</form>
<!--/main(end)-->
<!--footer(begin)-->

<!--footer(end)-->

<script>
$.ajaxSetup({  
    async : false  
});
function showBox(){
		dialog({
		    id: 'ptn950',              	
		    content: '<form method="POST" name="card" action>'
		    	+'文件所在的服务器ip地址：'
		    	+'<input type="text" class="form-control" id="ip">'
		    	+'用户名：'
		    	+'<input type="text" class="form-control" id="user">'
		    	+'密码：'
		    	+'<input type="text" class="form-control" id="pwd">'
		    	+ '文件名：'
		        +'<input type="text" class="form-control" id="fname">'
		       	+'</form>',
		    	title: '生成示例',
		    lock: true,
		    fixed: true,
		    drag:true,
		    width:400, height: 230,
		    ok: function () {
		    	$("#api_example").val('第一步连接ftp: ftp '+$("#ip").val()+' 第二步输入用户名: '+$("#user").val()+' 第三步输入密码: '+$("#pwd").val()+' 第四步获取文件: get '+$("#fname").val());
		    	
 		    },
		    okValue: '确定',
		   
		    cancel: function () {
		    	
		    },
		    cancelValue: '取消',
		}).show();
}



	$(function(){
	/* 	//内容区域高度自适应
		function autoHeight(a,b){
			var main = document.getElementsByClassName(b)[0];
			var cHeight = document.documentElement.clientHeight;
			main.style.height = cHeight-a+"px";
			return false;
		}
		autoHeight(190,"teachingPlan-content"); */
		//导航子列表显示
		var $item = $(".hNav li");
		$item.click(function(){
			$item.removeClass("current");
			$(this).hasClass("current")?$(this).removeClass("current"):$(this).addClass("current")
		})
		$item.hover(function(){
			$(this).find(".dropdown_menu").stop().slideDown();
		},function(){
			$(this).find(".dropdown_menu").stop().slideUp();
		})
		//table
		/* $(document).ready(function(){
			$('#myTable').DataTable({
				paging: false,
				searching: false,
				ordering:  false
			});
		}); */
		//弹窗
		/* $(".bd-upload-btn").on("click",function(){
			$('.modal').modal('show');
			$(".modal_align").css({"display":"table","height":"100%","width":"600px","left":"50%","margin-left":"-300px"})
		}) */
		//表格点击编辑
		function edit(){
			var $edit_box = $(".edit-box td:gt(0),.bd-infor-title");
			$edit_box.on("click",function(){
				var $val = $(this).find("span").text();
				$(this).css("padding","5px");
				$(this).find("span").hide();
				$(this).find("input").show().val($val).focus();
				$(this).find("input").on("blur",function(){
					$(this).parent("td").css("padding","11px");
					var $text = $(this).val();
					$(this).prev().show().text($text);
					$(this).hide().val();
				})
			})
		}
		edit();
	})
</script>
</body>
</html>
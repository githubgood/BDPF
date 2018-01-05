<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<article>
	<section class="vm-template-box w1100">
		<p class="vm-template-title">虚拟机和模板视图提供虚拟机以及虚拟机模板的创建、操作、管理功能。</p>
		<section class="cd-right-content">
			<div class="cd-title">
				<span>创建虚拟机</span>
				<div class="fr mr20 cd-operate cd-create-btn">
					<a  id="save" class="btn btn-icon create-btn" href="javascript:void(0)">创建</a>
				</div>
			</div>
			<!---->
			<div class="cd-right-tab mt20 mb20">
				<div class="of">
					<form action="" class="cd-create-host">
						<input type="hidden" id="flaid" value="${develop }">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" id="tab-ul" role="tablist">
							<li role="presentation" id="home-li" class="active"><a href="#home"
								aria-controls="home" role="tab" data-toggle="tab">详情<font color="red">*</font></a></li>
							
							<li role="presentation" id="resources-li"><a href="#resources"
								aria-controls="profile" role="tab" data-toggle="tab">源<font color="red">*</font></a></li>
							
							<li role="presentation" id="flavors-li" ><a href="#flavors"
								aria-controls="messages" role="tab" data-toggle="tab">虚拟机类型<font color="red">*</font></a></li>
							
							<li role="presentation" id="network-li"><a href="#network"
								aria-controls="messages" role="tab" data-toggle="tab">网络<font color="red">*</font></a></li>
							
							<li role="presentation" id="securitygroup-li"><a href="#securitygroup"
								aria-controls="messages" role="tab" data-toggle="tab">安全组</a></li>
							
							<li role="presentation" id="keypair-li"><a href="##keypair"
								aria-controls="messages" role="tab" data-toggle="tab">秘钥对</a></li>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content">
							<!--详情-->
							<div role="tabpanel" class="tab-pane active" id="home">
								<div class="cd-role-list cd-minHeight">
									<div class="form-group">
										<label class="control-label mr30">虚拟机名称</label> <input
											type="text" class="form-control" id="name" placeholder="请输入虚拟机名称"  onblur="checkVirtualName(this.value);">
									</div>
									<div class="form-group">
										<label class="control-label mr30">可用域</label> <select
											class="form-control" id="availabilityZone" name="availabilityZone">
										</select>
									</div>
									<!-- 
									<div class="form-group">
										<label class="control-label mr30">管理密码</label> <input
											type="text" class="form-control" id="adminPass">
									</div>
									-->
									<div class="form-group">
										<label class="control-label mr30">数量</label> <input
											type="text" class="form-control" id="count" placeholder="请输入数量">
									</div>
									<!-- 选择课程实验还是开放实验 -->
									
									
									<div class="form-group">
										<label class="control-label mr30">实验类型</label> 
										<i class="radio_btn_1 vam mr5 current"></i><span class="mr20">课程实验</span>
										<i class="radio_btn_2 vam mr5"></i><span>开放实验</span>
									</div>
									<div class="form-group toggle-box">
										<label class="control-label mr30">课程任务</label> 
										<select class="form-control" id="task" name="task">
										</select>
									</div>
									<!-- <div class="form-group toggle-box">
										<label class="control-label mr30">课程资源</label> <select
											class="form-control" id="data1" name="data1">
										</select>
									</div> -->
									<div class="form-group toggle-box" id="group_view">
										<label class="control-label mr30">实验小组</label> <select
											class="form-control" id="group" name="group">
										</select>
									</div>
									<div class="form-group">
									<!-- 学生创建时默认显示当前学生的account -->
										<label class="control-label mr30">学号</label>
										<c:if test="${loginuser.userType == 1}">
										<input type="text" class="form-control" id="account" placeholder="请输入学号" onblur="checkAccount(this.value);"  value="${loginuser.account }">
										</c:if>
										<c:if test="${loginuser.userType != 1}">
										<input type="text" class="form-control" id="account" placeholder="请输入学号" onblur="checkAccount(this.value);" >
										</c:if>
									</div>
									
								</div>
							</div>
							<!--/详情-->
							<!--源-->
							<div role="tabpanel" class="tab-pane pb20" id="resources">
								<div class="of mt10 mb10 pl10 pr10 cd-manage-list ">
									<div class="table-responsive table-allot">
										<table class="table">
											<caption class="cd-table-title">已分配</caption>
											<thead>
												<tr>
													<th>名称</th>
													<th>大小</th>
													<th>类型</th>
													<th>描述</th>
													<th>状态</th>
													<th class="tac">动作</th>
												</tr>
											</thead>
											<tbody id="Image-tbody">
											
											</tbody>
										</table>
									</div>
									<div class="table-responsive">
										<table class="table">
											<caption class="cd-table-title">可用</caption>
											<thead>
												<tr>
													<th>名称</th>
													<th>大小</th>
													<th>类型</th>
													<th>描述</th>
													<th>状态</th>
													<th class="tac">动作</th>
												</tr>
											</thead>
											<tbody id="tbody-list">
											<%-- <c:forEach items="${Image}" var="Image">
													<tr >
													<td class="Image"style="display: none">${Image.id}</td>
													<td class="Images">${Image.name}</td>
													<td>${Image.size}</td>
													<td>Image</td>
													<td>${Image.status}</td>
													<td class="tac"><a class="cd-icon et-icon add-icon"
														href="javascript:void(0);" title="添加"></a></td>
												</tr>
											</c:forEach>	 --%>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<!--/源-->
							<!--虚拟机类型-->
							<div role="tabpanel" class="tab-pane pb20" id="flavors">
								<div class="of mt10 mb10 pl10 pr10 cd-manage-list ">
									<div class="table-responsive table-allot">
										<table class="table">
											<caption class="cd-table-title">已分配</caption>
											<thead>
												<tr>
													<th>名称</th>
													<th>虚拟机内核</th>
													<th>内存</th>
													<th>磁盘统计</th>
													<th>交换空间</th>
													<th>公有</th>
													<th class="tac">动作</th>
												</tr>
											</thead>
											<tbody id="Flavors-tbody">
												
											</tbody>
										</table>
									</div>
									<div class="table-responsive">
										<table class="table">
											<caption class="cd-table-title">可用</caption>
											<thead>
												<tr>
													<th>名称</th>
													<th>虚拟机内核</th>
													<th>内存</th>
													<th>磁盘统计</th>
													<th>交换空间</th>
													<th>公有</th>
													<th class="tac">动作</th>
												</tr>
											</thead>
											<tbody id="tbody-flavors">
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<!--/虚拟机类型-->
							<!--网络-->
							<div role="tabpanel" class="tab-pane" id="network">
								<div class="of mt10 mb10 pl10 pr10 cd-manage-list ">
									<div class="table-responsive table-allot">
										<table class="table">
											<caption class="cd-table-title">已分配</caption>
											<thead>
												<tr>
													<th>网络</th>
													<th>已连接的子网</th>
													<th>共享的</th>
													<th>管理员状态</th>
													<th>状态</th>
													<th class="tac">动作</th>
												</tr>
											</thead>
											<tbody id="network-tbody">
	
											</tbody>
										</table>
									</div>
									<div class="table-responsive table-clean">
										<table class="table">
											<caption class="cd-table-title">可用</caption>
											<thead>
												<tr>
													<th>网络</th>
													<th>已连接的子网</th>
													<th>共享的</th>
													<th>管理员状态</th>
													<th>状态</th>
													<th class="tac">动作</th>
												</tr>
											</thead>
											<tbody id="tbody-network-list">
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<!--/网络-->
							<!--安全组-->
							<div role="tabpanel" class="tab-pane" id="securitygroup">
								<div class="cd-role-list cd-minHeight">
									<div class="form-group">
										<label class="control-label mr30">安全组</label>
										 <select
											class="form-control" id="securityGroupsName" name="securityGroupsName">
											<%-- <c:forEach items="${defaults}" var="defaults">
												<option value="${defaults.name}">${defaults.name}</option>
											</c:forEach> --%>
										</select>
									</div>
								</div>
							</div>
							<!--/安全组-->
							<!--密钥对-->
							<div role="tabpanel" class="tab-pane" id="keypair">
								<div class="cd-role-list cd-minHeight">
									<div class="form-group">
										<label class="control-label mr30">密钥对</label> <select class="form-control" id="keypairName" name="keypairName">
											<%-- <c:forEach items="${mykey}" var="mykey">
												<option value="${mykey.name}">${mykey.name}</option>
											</c:forEach> --%>
										</select>
									</div>
								</div>
							</div>
							<!--/密钥对-->
						</div>
					</form>
				</div>
			</div>
		</section>
	</section>
</article>
<script>
 function radioBtn(){
	var radioBtn1 = $(".radio_btn_1");
	var radioBtn2 = $(".radio_btn_2");
	var toggleBox = $(".toggle-box");
	radioBtn1.on("click",function(){
		if($(this).hasClass("current")){
			return ;
		}else{
			$(this).addClass("current").siblings("i").removeClass("current");
			toggleBox.show();
			if(taskId.length != 0 ){
				getDataList(taskId);
			}
		}
		
	})
	radioBtn2.on("click",function(){
		if($(this).hasClass("current")){
			return;
		}else{
			$(this).addClass("current").siblings("i").removeClass("current");
			toggleBox.hide();
		}
		
	})
}
radioBtn() 

</script>
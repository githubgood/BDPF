<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form class="form-inline" id="add-user-form">
	<div class="content-r" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>增加用户</span>
		</p>
		<div id="message" class="et-teachingPlan-content">
			<input type="hidden" id="Id" name="Id" value="" />
			<div class="form-group">
				<label>用户账号</label>
				<input type="text" class="form-control account"	name="account" id="account" placeholder="2~16个字符，可使用数字、字母">
			</div>
			<div class="form-group">
				<label>用户密码</label>
				<input type="text" class="form-control password"	id="password" name="password" placeholder="2~12个字符，可使用数字、字母">
			</div>
			<div class="form-group">
				<label>用户名称</label>
				<input type="text" class="form-control name"	id="name" name="name" placeholder="1~50个字符">
			</div>
			<div class="form-group">
				<label>手机号码</label>
				<input type="text" class="form-control mobile"	id="mobile" name="mobile" placeholder="11个字符，只可使用数字">
			</div>
			<div class="form-group">
				<label>邮箱地址</label>
				<input type="text" class="form-control email"	id="email" name="email" placeholder="6-32个字符，可使用字母、数字、下划线">
			</div>
			<div class="form-group roleId" >
				<label>角<i style="margin-left: 2em;"></i>色</label>
				<select class="form-control" id="roleId" name="roleId">
					<option value="">--请选择--</option>
					<c:forEach items="${rolelist }" var="item">
						<option value="${item.id}" >${item.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group classId"  id="class-view">
				<label class="fl">班<i style="margin-left: 2em;"></i>级</label>
					 <select class="form-control" id="classId" name="classId">
					 	<option value="">--请选择--</option>
						<c:forEach items="${classlist }" var="classs">
							<option value="${classs.id}">${classs.name}</option>
						</c:forEach>
					</select>
				<div class="fl" id="teacher-view" style="width:400px">
					<c:forEach items="${classlist}" var="classs" step="1" varStatus="status">
						<p class="checkbox_box mr20" style="display:inline-block">
							<input class="checkBox" type="checkbox" name="tClassId" value="${classs.id }">
							<span	class="checkBox" style="vertical-align:sub;">${classs.name}</span>
						</p>
					</c:forEach>
				</div>
			</div>
			<!-- <div class="form-group resource-group userType">
				<label>用户类型</label>
				<p class="radio_box">
					<input class="radio" type="radio" name="userType" id="userType" value="0"
						checked="checked"> <span class="radio"> 老师 </span>
				</p>
				<p class="radio_box">
					<input class="radio" type="radio" name="userType" id="userType" value="1">
					<span class="radio"> 学生 </span>
				</p>
			</div> -->
			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit"  class="btn affirm-btn mr40" value="添加" />
				<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取消" />
			</div>
		</div>
	</div>
</form>
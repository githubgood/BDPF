<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form class="form-inline" id="add-student-form">
	<div class="content-r" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>增加学生</span>
		</p>
		<div id="message" class="et-teachingPlan-content">
			<input type="hidden" id="Id" name="Id" value="" />
			<div class="form-group">
				<label>学<i style="margin-left: 2em;"></i>号</label> 
				<input type="text" class="form-control account"	id="account" name="account" placeholder="2~16个字符，可使用字母、数字">
			</div>
			<div class="form-group">
				<label>名<i style="margin-left: 2em;"></i>称</label> 
				<input type="text" class="form-control name" id="name" name="name" placeholder="1~50个字符">
			</div>
			<div class="form-group">
				<label>手机号码</label>
				<input type="text" class="form-control mobile"  id="mobile" name="mobile" placeholder="11个字符，只可使用数字">
			</div>
			<div class="form-group">
				<label>邮箱地址</label>
				<input type="text" class="form-control email"	id="email" name="email" placeholder="6-32个字符，可使用字母、数字、下划线">
			</div>
			<div class="form-group" >
				<label>班<i style="margin-left: 2em;"></i>级</label> 
				<select class="form-control" id="classId" name="classId">
					<option value="">--请选择--</option>
					<c:forEach items="${classlist }" var="course">
						<option value="${course.id}">${course.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="添加" /> 
				<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取消" />
			</div>
		</div>
	</div>
</form>
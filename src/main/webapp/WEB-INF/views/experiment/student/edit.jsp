<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form class="form-inline" id="edit-student-form">
	<div class="et-manage-content"  style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>修改学生</span>
		</p>
		<div  id="message" class="et-teachingPlan-content">
			<input type="hidden" id="Id" name="Id" value="${user.id }" />
			<input type="hidden" id="password" name="password" value="${user.password }" />
			<input type="hidden" id="userType" name="userType" value="${user.userType }" />
			<div class="form-group">
				<label>学<i style="margin-left: 2em;"></i>号</label> 
				<input type="text" class="form-control"	id="account" name="account"  placeholder="2~16个字符，可使用字母、数字"  value="${user.account }">
			</div>
			<div class="form-group">
				<label>名<i style="margin-left: 2em;"></i>称</label>
				<input type="text" class="form-control"	id="name" name="name"  placeholder="1~50个字符"  value="${user.name }">
			</div>
			<div class="form-group">
				<label>手机号码</label>
				<input type="text" class="form-control"	id="mobile" name="mobile"  placeholder="11个字符，只可使用数字"  value="${user.mobile }">
			</div>
			<div class="form-group">
				<label>邮箱地址</label>
				<input type="text" class="form-control"	id="email" name="email"  placeholder="6-32个字符，可使用字母、数字、下划线"  value="${user.email }">
			</div>
			<div class="form-group" id="">
				<label>班<i style="margin-left: 2em;"></i>级</label>
					<select class="form-control" id="classId" name="classId">
						<c:forEach items="${classlist }" var="eClass">
							<c:if test="${user.classId == eClass.id }">
									<option value="${eClass.id}" selected="selected">${eClass.name}</option>
							</c:if>
							<c:if test="${user.classId != eClass.id }">
									<option value="${eClass.id}">${eClass.name}</option>
							</c:if>
						</c:forEach>
				</select>
			</div>
			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="修改" /> 
				<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取消" />
			</div>
		</div>
	</div>
</form>
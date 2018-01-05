<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form class="form-inline" id="edit-user-form">
<div class="et-manage-content" style="margin-left: 0px;">
	<p class="et-manage-title">
		<span>修改用户</span>
	</p>
	<div  id="message" class="et-teachingPlan-content">
			<input type="hidden" id="Id" name="Id" value="${user.id}" />
			<div class="form-group">
				<label>用户账号</label>
				<input type="text" class="form-control account"	id="account" readonly="readonly" name="account" placeholder="2~16个字符，可使用数字、字母" value="${user.account }">
			</div>
			<div class="form-group">
				<label>用户密码</label>
				<input type="text" class="form-control password"	id="password" name="password" placeholder="2~12个字符，可使用数字、字母" value="${user.password }">
			</div>
			<div class="form-group">
				<label>用户名称</label>
				<input type="text" class="form-control name"	id="name" name="name" placeholder="1~50个字符" value="${user.name }">
			</div>
			<div class="form-group">
				<label>手机号码</label>
				<input type="text" class="form-control mobile" id="mobile" name="mobile" placeholder="11个字符，只可使用数字" value="${user.mobile }">
			</div>
			<div class="form-group">
				<label>邮箱地址</label>
				<input type="text" class="form-control email"	id="email" name="email" placeholder="6-32个字符，可使用字母、数字、下划线" value="${user.email }">
			</div>
			<c:if test="${user.userType != 2 }">
			<div class="form-group" id="">
				<label>角<i style="margin-left: 2em;"></i>色</label>
				<select class="form-control"  name="roleId" id="roleId">
					<option value="">--请选择--</option>
					<c:forEach items="${rolelist }" var="item">
						<%
							boolean flag = false;
						%>
						<c:forEach items="${checklist }" var="role">
							<c:if test="${item.id == role.roleId }">
								<option value="${item.id}" selected="selected">${item.name}
								</option>
								<%
									flag = true;
								%>
							</c:if>
						</c:forEach>
						<%
							if (!flag) {
						%>
						<option value="${item.id}">${item.name}</option>
						<%
							}
						%>
					</c:forEach>
				</select>
			</div>
			</c:if>
			<div class="form-group classId" id="class-view">
				<label class="fl">班<i style="margin-left: 2em;"></i>级</label>
				<select class="form-control" id="classId" name="classId">
					<option value="">--请选择--</option>
					<c:forEach items="${classlist }" var="eClass">
						<option value="${eClass.id}"  <c:if test="${user.classId == eClass.id }">selected="selected"</c:if>  >${eClass.name}</option>
					</c:forEach>
				</select>
				<div class="fl" id="teacher-view" style="width:400px">
					<c:forEach items="${classlist}" var="classs" step="1" varStatus="status">
						<p class="checkbox_box mr20" style="display:inline-block">
							<input class="checkBox" type="checkbox" data-name="teacher-cid" name="tClassId"  <c:if test="${fn:contains(user.classId,classs.id)}">checked="checked"</c:if>  value="${classs.id }">
							<span	class="checkBox" style="vertical-align:sub;">${classs.name}</span>
						</p>
					</c:forEach>
				</div>
			</div>
			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit" 	class="btn affirm-btn mr40" value="修改" />
				<input id="btn-cancel" name="btn-cancel" type="button"	class="btn cancel-btn" value="取消" />
			</div>
			</div>
	</div>
</form>
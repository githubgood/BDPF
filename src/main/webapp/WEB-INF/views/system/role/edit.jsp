<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form class="form-inline" id="edit-role-form">
<div id="content-r" class="content-r" style="margin-left:0px;">
	<p class="et-manage-title">
		<span>修改角色</span>
	</p>
	<div id="message" class="et-teachingPlan-content  cd-role-list">
		<input type="hidden" id="Id" name="Id" value="${role.id }" />
			<div class="form-group">
				<label>角色名称</label>
				<input type="text" class="form-control name" id="name" value="${role.name }" name="name"  placeholder="1~50个字符">
			</div>
			<div class="form-group">
				<label>角色描述</label>
				<input type="text" class="form-control description" id="description" name="description" value="${role.description }"   placeholder="1~100个字符">
			</div>
			<div class="form-group of">
				<label class="ontrol-label mr30 fl mb10">权限列表</label>
				<div class="fl w600">
					<div>
						<c:forEach items="${muneList }" var="menu">
								<div class="mb5 cd-checkbox" >
								<!-- 加载一级菜单，有选择框 -->
											<%
												boolean flw = false;
											%>
									<c:forEach items="${checkList }" var="list">
									<!-- 如果有选择一级菜单 ，就加选中效果 -->
										<c:if test="${list.competenceId ==menu.id }">
											<div class="fsize14 c-5ad  fb">
												<p class="checkbox_box">
													<input class="checkBox" type="checkbox" name="${menu.resCode}" checked="checked" value="${menu.id}" onclick="DoCheck('${menu.resCode}')">
													<span class="checkBox">${menu.name}</span>
												</p>
											</div>
											<%
												flw = true;
											%>
										</c:if>
									</c:forEach>
									<%
										if (!flw) {
									%>
									<!-- 设置非选中状态 -->
									<div class="fsize14 c-5ad  fb">
												<p class="checkbox_box">
													<input class="checkBox" type="checkbox" name="${menu.resCode}" value="${menu.id}" onclick="DoCheck('${menu.resCode}')">
													<span class="checkBox">${menu.name}</span>
												</p>
											</div>
									<%
										}
									%>
								<!-- 显示权限列表二级功能菜单  -->
								<c:forEach items="${compList }" var="comp">
									<!-- 判断是否显示在此一级菜单下  -->
									<c:if test="${menu.resCode == comp.parentId}">
									<%
										boolean flag = false;
									%>
									<c:forEach items="${checkList }" var="list">
									<!-- 判断当前角色是否已经拥有此权限 -->
									<c:if test="${list.competenceId == comp.id}">
									<!-- 设置选中状态 -->
									<p class="checkbox_box">
										<input class="checkBox" type="checkbox" checked="checked" name="${comp.parentId}" value="${comp.id}">
										<span class="checkBox">${comp.name}</span>
									</p>
									<%
										flag = true;
									%>
									</c:if>
									</c:forEach>
								 <%
										if (!flag) {
									%>
									<!-- 设置非选中状态 -->
									<p class="checkbox_box">
										<input class="checkBox" type="checkbox"   name="${comp.parentId}" value="${comp.id}">
										<span class="checkBox">${comp.name}</span>
									</p>
									<%
										}
									%>
									</c:if>
								</c:forEach>
								</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="修改" />
				<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取消" />
			</div>
	</div>
</div>
</form>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form class="form-inline" id="add-role-form">
<div id="content-r" class="content-r" style="margin-left:0px;">
	<p class="et-manage-title">
		<span>增加角色</span>
	</p>
	<div id="message" class="et-teachingPlan-content cd-role-list">
		<input type="hidden" id="Id" name="Id" value="" />
			<div class="form-group">
				<label>角色名称</label>
				<input type="text" class="form-control name" id="name" name="name" placeholder="1~50个字符">
			</div>
			<div class="form-group">
				<label>角色描述</label>
				<input type="text" class="form-control description" id="description" name="description"  placeholder="1~100个字符">
			</div>
			<div class="form-group of">
				<label class="ontrol-label mr30 fl mb10">权限列表</label>
				<div class="fl w600">
					<div>
						<c:forEach items="${muneList }" var="menu">
								<div class="mb5 cd-checkbox" >
									<div class="fsize14 c-5ad fb" >
										<p class="checkbox_box">
											<input class="checkBox" type="checkbox" name="${menu.resCode}" value="${menu.id}" onclick="DoCheck('${menu.resCode}')">
											<span class="checkBox">${menu.name}</span>
										</p>
									</div>
									<%-- <input type="checkbox" name="check" value="${menu.id}" />模块： --%>
									<%-- <span>${menu.name}</span>  --%>
								<c:forEach items="${compList }" var="comp">
									<c:if test="${menu.resCode == comp.parentId}">
									<p class="checkbox_box">
										<input class="checkBox" type="checkbox" name="${comp.parentId}" value="${comp.id}">
										<span class="checkBox">${comp.name}</span>
									</p>
									<%-- <td><input type="checkbox" name="check"  value="${comp.id}" />${comp.name}</td> --%>
									</c:if>
								</c:forEach>
								
								</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="添加" />
				<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取消" />
			</div>
	</div>
</div>
</form>
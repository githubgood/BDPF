<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="edit-plan-form" class="form-inline">
	<div class="et-manage-content" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>修改课程教学计划</span>
		</p>
		<div id="message"  class="et-teachingPlan-content">
		<input type="hidden" id="id" name="id" value="${plan.id }" >
			<div class="form-group">
				<label>计划课时</label>
				<input type="text" class="form-control"	id="learningTime" name="learningTime" value="${plan.learningTime }" placeholder="1~10个字符，可使用数字、小数点">
			</div>
			<div class="form-group">
				<label>计划名称</label>
				<input type="text" class="form-control"	id="name" name="name" value="${plan.name }"  placeholder="1~50个字符">
			</div>
			<div class="form-group" id="">
				<label class="">课程类型</label>
				<select class="form-control" id="courseType" name="courseType">
					<option value="0" <c:if test="${plan.courseType==0}">selected</c:if>>课程实验</option>
					<option value="1" <c:if test="${plan.courseType==1}">selected</c:if>>公开实验</option>
				</select>
			</div>
			<div class="form-group">
				<label>描<i style="margin-left: 2em;"></i>述</label>
				<input type="text" class="form-control" id="description" name="description" value="${plan.description }" placeholder="1~100个字符">
			</div>
			<div class="form-group of">
				<label class="fl">路 <i style="margin-left: 2em;"></i>线</label>
				<div class="fl teaching-tabs">
					<input type="hidden" id="line" value="comp-one">
					<ul id="myTab" class="nav nav-tabs">
						<li class="active">
							<a href="#one" class="line-a" name="comp-one" data-toggle="tab"> 推荐路线 </a>
						</li>
					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="one">
							<div class="select-list">
								<ul>
									<c:forEach items="${course}" var="item" step="1" varStatus="status">
										<c:choose>
												<c:when test="${fn:contains(planLine,item.id)}">
												<li class="select-item">
													<p class="checkbox_box pr">
														<input id="${item.id }" class="checkBox" type="checkbox" name="comp-one" value="${item.id }" checked="checked">
														<span class="checkBox">${item.title}</span>
													</p>
												</li>
											</c:when>
											<c:otherwise>
												<li class="select-item">
													<p class="checkbox_box pr">
														<input id="${item.id }" class="checkBox" type="checkbox" name="comp-one" value="${item.id }">
														<span	class="checkBox">${item.title}</span>
													</p>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
						</div>
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
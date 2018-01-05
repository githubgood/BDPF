<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
.checkbox_box {
  display: inline-block;
  min-width: 25px;
}
input[type='checkbox'].checkBox {
  position: absolute;
  opacity: 0;
}
span.checkBox {
  background: url(${ctx}/resources/images/no_checked.png) no-repeat;
  display: inline-block;
  padding-left: 20px;
  height: 18px;
  line-height: 18px;
}
 input[type='checkbox'].checkBox:checked + .checkBox {
  background: url(${ctx}/resources/images/checked.png) no-repeat;
}
</style>
<form id="add-plan-form" class="form-inline">
	<div class="et-manage-content" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>增加课程教学计划</span>
		</p>
		<div id="message"  class="et-teachingPlan-content">
			<div class="form-group">
				<label>计划课时</label>
				<input type="text" class="form-control"	id="learningTime" name="learningTime" placeholder="1~10个字符，可使用数字、小数点">
			</div>
			<div class="form-group">
				<label>计划名称</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="1~50个字符">
			</div>
			<div class="form-group" id="">
				<label class="">课程类型</label>
				<select class="form-control" id="courseType" name="courseType">
					<option value="">--请选择--</option>
					<option value="0">课程实验</option>
					<option value="1">公开实验</option>
				</select>
			</div>
			<div class="form-group">
				<label>描<i style="margin-left: 2em;"></i>述</label>
				<input type="text" class="form-control" id="description" 	name="description" placeholder="1~100个字符">
			</div>
			<div class="form-group of">
				<label class="fl">路 <i style="margin-left: 2em;"></i>线</label>
				<div class="fl teaching-tabs">
					<input type="hidden" id="line" value="comp-one">
					<ul id="myTab" class="nav nav-tabs">
						<li class="active">
							<a href="#one" class="line-a" name="comp-one" data-toggle="tab">推荐路线1 </a>
						</li>
						<li>
							<a href="#two" class="line-a" name="comp-two" data-toggle="tab">推荐路线2</a>
						</li>
						<li>
							<a href="#three" class="line-a" name="comp-three" data-toggle="tab"> 推荐路线3 </a>
						</li>
						<li>
							<a href="#four" class="line-a" name="comp-four" data-toggle="tab">自定义</a>
						</li>
					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="one">
							<div class="select-list">
								<ul>
									<c:forEach items="${course}" var="item" step="1" varStatus="status">
										<c:choose>
											<c:when test="${item.xh%3==0}">
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
						<div class="tab-pane fade" id="two">
							<div class="select-list">
								<ul>
									<c:forEach items="${course}" var="item" step="1" varStatus="status">
										<c:choose>
											<c:when test="${item.xh%3==1}">
												<li class="select-item">
													<p class="checkbox_box pr">
														<input id="${item.id }" class="checkBox" type="checkbox" name="comp-two" value="${item.id }" checked="checked">
														<span class="checkBox">${item.title}</span>
													</p>
												</li>
											</c:when>
											<c:otherwise>
												<li class="select-item">
													<p class="checkbox_box pr">
														<input id="${item.id }" class="checkBox" type="checkbox" name="comp-two" value="${item.id }">
														<span	class="checkBox">${item.title}</span>
													</p>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="tab-pane fade" id="three">
							<div class="select-list">
								<ul>
									<c:forEach items="${course}" var="item" step="1" varStatus="status">
										<c:choose>
											<c:when test="${item.xh%3==2}">
												<li class="select-item">
													<p class="checkbox_box pr">
														<input id="${item.id }" class="checkBox" type="checkbox" name="comp-three" value="${item.id }" checked="checked">
														<span class="checkBox">${item.title}</span>
													</p>
												</li>
											</c:when>
											<c:otherwise>
												<li class="select-item">
													<p class="checkbox_box pr">
														<input id="${item.id }" class="checkBox" type="checkbox" name="comp-three" value="${item.id }">
														<span	class="checkBox">${item.title}</span>
													</p>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="tab-pane fade" id="four">
							<div class="select-list">
								<ul>
									<c:forEach items="${course}" var="item" step="1" varStatus="status">
										<li class="select-item">
											<p class="checkbox_box pr">
												<input id="${item.id }" class="checkBox" type="checkbox" name="comp-four" value="${item.id }">
												<span	class="checkBox">${item.title}</span>
											</p>
										</li>
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

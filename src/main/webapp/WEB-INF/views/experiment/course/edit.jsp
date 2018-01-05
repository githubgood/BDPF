<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="edit-course-form" class="form-inline">
	<div class="et-manage-content" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>修改课程资源</span>
		</p>
		<div id="message" class="et-teachingPlan-content">
			<input type="hidden" id="id" name="id" value="${course.id}">
			<input type="hidden" id="file" name="file" value="">
			<div class="form-group">
				<label>资源序号</label>
				<input type="text" class="form-control" readonly="readonly" id="xh" name="xh" value="${course.xh }" placeholder="1~10个字符，只可使用数字">
			</div>
			<div class="form-group">
				<label>资源名称</label>
				<input type="text" class="form-control" id="title" name="title" value="${course.title }" placeholder="1~50个字符">
			</div>
			<div class="form-group" id="">
				<label class="">资源分类</label>
				<select class="form-control" id="courseResourceType" name="courseResourceType">
					<option value="0" <c:if test="${course.courseResourceType==0}">selected</c:if>>安装维护类</option>
					<option value="1" <c:if test="${course.courseResourceType==1}">selected</c:if>>应用开发类</option>
					<option value="2" <c:if test="${course.courseResourceType==2}">selected</c:if>>数据挖掘类</option>
				</select>
			</div>
			<div class="form-group ">
				<label class="">文件类型</label>
				<select class="form-control" id="fileType" name="fileType">
					<option value="${course.fileType}">
						<c:if test="${course.fileType==0}">文档</c:if>
						<c:if test="${course.fileType==1}">视频</c:if>
						<c:if test="${course.fileType==2}">图片</c:if>
						<c:if test="${course.fileType==3}">数据</c:if>
						<c:if test="${course.fileType==4}">工具</c:if>
						<c:if test="${course.fileType==5}">源代码</c:if>
					</option>
				</select>
			</div>
			<c:choose>
				<c:when test="${course.fileType==0}">
					<div class="form-group" id="courseMirror-Name">
						<label class="">模板名称</label>
						<select class="form-control" id="courseMirrorId" name="courseMirrorId">
						  <c:forEach items="${sessionScope.image.imageList}" var="image">
							 <option value="${image.id}" <c:if test="${image.id ==course.courseMirrorId}">selected</c:if>>${image.name}</option>
						  </c:forEach>
						</select>
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group" id="parent-Id">
						<label class="">父<i style="margin-left: 0.5em;"></i>及<i style="margin-left: 0.5em;"></i>第</label>
						<select class="form-control" id="parentId" name="parentId">
							<c:forEach items="${courselist }" var="itms">
								<option value="${itms.id}" <c:if test="${itms.id ==course.parentId}">selected</c:if>>${itms.title}</option>
							</c:forEach>
						</select>
					</div>
				</c:otherwise>
			</c:choose>
			<div class="form-group">
				<label>资源描述</label>
				<input type="text" class="form-control"	id="description" name="description" value="${course.description }" placeholder="1~100个字符">
			</div>
			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="修                改" /> 
				<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取                消" />
			</div>
		</div>
	</div>
</form>
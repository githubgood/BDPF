<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="add-course-form" class="form-inline">
	<div class="content-r" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>增加课程资源</span>
		</p>
		<div id="message" class="et-teachingPlan-content">
          <input type="hidden" id="id" name="id" value="" >
			<div class="form-group ">
				<label>资源序号</label>
				<input type="text" class="form-control " id="xh" readonly="readonly" name="xh" value="${maxxh}" placeholder="1~10个字符，只可使用数字">
			</div>
			<div class="form-group ">
				<label>资源名称</label>
				<input type="text" class="form-control "  id="title" name="title" placeholder="1~50个字符">
			</div>
			<div class="form-group" id="">
				<label class="">资源分类</label>
				<select class="form-control" id="courseResourceType" name="courseResourceType">
					<option value="">--请选择--</option>
					<option value="0">安装维护类</option>
					<option value="1">应用开发类</option>
					<option value="2">数据挖掘类</option>
				</select>
			</div>
			<div class="form-group" id="">
				<label class="">文件类型</label>
				<select class="form-control" id="fileType" name="fileType">
					<option value="">--请选择--</option>
					<option value="0">文档</option>
					<option value="1">视频</option>
					<option value="2">图片</option>
					<option value="3">数据</option>
					<option value="4">工具</option>
					<option value="5">源代码</option>
				</select>
				<p id="type_file"></p>
			</div>
			<div id="fileUpLoad" class="form-group file-remind-one pr">
				<label>上传文件</label>
				<input type="file" style="display: none;"	id="file" name="file" onchange="txtFoo.value=this.value; " />
				<input type="text" class="form-control" id="txtFoo" name="txtFoo"	readonly="readonly" style="width: 300px" />
				<button class="fileBtn" type="button"  onclick="file.click()">选择文件</button>
			</div>
			<div class="form-group" id="courseMirror-Name">
				<label class="">模板名称</label>
				<select class="form-control" id="courseMirrorId" name="courseMirrorId">
					<option value="">--请选择--</option>
					  <c:forEach items="${sessionScope.image.imageList}" var="image">
						 <option value="${image.id}" >${image.name}</option>
					  </c:forEach>
				</select>
			</div>
			<c:if test="${courselist!=null}">
			<div class="form-group" id="parent-Id">
				<label class="">父<i style="margin-left: 0.5em;"></i>及<i style="margin-left: 0.5em;"></i>第</label> 
				<select class="form-control" id="parentId" name="parentId">
					<option value="">--请选择--</option>
					<c:forEach items="${courselist }" var="course">
						<option value="${course.id}">${course.title}</option>
					</c:forEach>
				</select>
			</div>
			</c:if>
			<div class="form-group">
				<label>资源描述</label>
				<input type="text" class="form-control"	id="description" name="description" placeholder="1~100个字符">
			</div>
			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="添                加" /> 
				<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取                消" />
			</div>
		</div>
	</div>
</form>
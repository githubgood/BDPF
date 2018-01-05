<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="add-task-form" class="form-inline">
	<div class="et-manage-content" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>增加课程任务发布</span>
		</p>
		<div id="message" class="et-teachingPlan-content com-box">
			<input type="hidden" id="id" name="id" value="" >
			<div class="form-group">
				<label class=" tar">任务名称</label>
				<input type="text" class="form-control" placeholder="1~50个字符"  id="name" name="name">
			</div>
			<div class="form-group">
				<label class=" tar">计划名称</label>
				<select	class="form-control" id="teachingPlanId" name="teachingPlanId">
				    <option value="">--请选择--</option>
					<c:forEach items="${plan}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group  div-show pr" >
				<label class=" tar">班<i style="margin-left: 2em;"></i>级</label>
				<c:forEach items="${clas}" var="item">
					<p class="checkbox_box">
						<input class="checkBox" type="checkbox" name="classId" value="${item.id}"> 
						<span	class="checkBox"> ${item.name}</span>
					</p>
				</c:forEach>
			</div>
			<div id="courseStatu">
			<input type="hidden" id="groupType" name="groupType" value="0">
			<div class="form-group">
				<label class=" tar">分组类型</label>
				<p class="radio_box mr30 group-btn type-box">
					<span id="radio"  class="radio active">自动</span>
				</p>
				<p class="radio_box person-btn type-box">
					<span class="radio">手动</span>
				</p>
			</div>
			<div class="form-group   div-show"  >
				<label class=" tar">分组名称</label>
				<input type="text" class="form-control" placeholder="1~50个字符" id="groupName" name="groupName">
			</div>
			<div class="form-group">
				<div class="toggle-box   div-show" >
					<label class=" tar">小组人数</label>
					<input type="text" class="form-control" placeholder="1~2个数字" id="groupNum" name="groupNum">
				</div>
				<div class="toggle-box   div-hidden"  style="display: none;">
					<div class="upload-group-title">
						<label class=" tar">上传分组</label>
					</div>
					<div class="upload-group-infor  pr file-remind-one  file-remind-two">
						<p class="mb5">
							<a class="upload-hf" href="${ctx}/resources/data/分组信息.xls">下载模板</a>
						</p>
						<input type="text" class="form-control fl" id="txt" name="txt"  placeholder="" style="width:310px;">
						<input class="upload-file"  id="file" name="file" type="file" value="上传" onchange="txt.value=this.value;" accept=".xls"  style="display:none;"> 
						<button class="fileBtn" type="button"  onclick="file.click()">选择文件</button>
					</div>
				</div>
			</div>
			</div>
			<div class="form-group">
				<label class=" tar">任务描述</label>
				<input type="text" class="form-control" placeholder="1~100个字符" id="description" name="description">
			</div>
			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="添加" />
				<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取消" />
			</div>
		</div>
	</div>
</form>
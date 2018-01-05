<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="send-exp-form" class="form-inline">
	<div class="et-manage-content" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>提交实验报告</span>
		</p>
          <input type="hidden" id="id" name="id" value="${grade.id }" >
          <input type="hidden" id="groupId" name="groupId" value="${grade.groupId }" >
		<div id="message" class="et-teachingPlan-content">
			<div class="form-group ">
				<label class="tar" style="width: 84px">实验名称</label> 
				<input type="text" class="form-control " id="exp_name" name="exp_name" value="${grade.title }" placeholder="" readonly>
			</div>
			<div class="form-group ">
				<label class="tar" style="width: 84px">实验报告状态</label> 
				<input type="text" class="form-control " id="exp_state" name="exp_state" placeholder="" value='<c:if test="${grade.reportStatus == '0' }">未提交</c:if><c:if test="${grade.reportStatus == '1' }">已提交</c:if>' readonly>
			</div>
			<div class="form-group ">
				<label class="tar" style="width: 84px">实验模板</label> <input type="text" class="form-control " id="exp_temp" name="exp_temp" value="该实验没有添加实验报告模板" placeholder="" readonly>
			</div>

			<div id="fileUpLoad" class="form-group">
				<label class="tar" style="width: 84px">上传文件</label> <input type="file" style="display: none;" id="file" name="file" onchange="txtFoo.value=this.value; " accept=".doc" />
				<input type="text" class="form-control" id="txtFoo" name="txtFoo" readonly="readonly" style="width: 300px" />
				<button type="button"  onclick="file.click()" style="height: 25px;">选择文件</button>
			</div>

			<div class="tac">
				<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="提交" /> 
				<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取消" />
			</div>
		</div>
	</div>
</form>
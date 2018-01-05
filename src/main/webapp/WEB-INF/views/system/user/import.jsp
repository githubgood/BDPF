<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
 <script src="${ctx}/resources/js/system/user/to_user.js"></script> 
 <script type="text/javascript" src="${ctx}/resources/js/ajaxfileupload.js"></script>
<form id="import-user-form" class="form-inline">
	<div class="et-manage-content" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>导入教师信息</span>
		</p>
		<div id="message" class="et-teachingPlan-content">
		<div id="fileUpLoad" class="form-group">
			<label class="tar" style="width: 84px">上传文件</label> <input type="file" style="display: none;" id="file" name="file" onchange="txtFoo.value=this.value; " accept=".xls" />
			<input type="text" class="form-control" id="txtFoo" name="txtFoo" readonly="readonly" style="width: 300px" />
			<button type="button"  onclick="file.click()" style="height: 25px;">选择文件</button>
		</div>
		<div class="tac">
			<input id="btn-save" name="btn-save" type="submit" class="btn affirm-btn mr40" value="确定"  /> 
			<input id="btn-cancel" name="btn-cancel" type="button" class="btn cancel-btn" value="取消"  onclick="back()"/>
		</div>
	</div>
  </div>
</form>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form class="form-inline" id="add-introduction-form">
	<div class="content-r et-manage-content et-MI-box" style="margin-left: 0px;">
		<p class="et-manage-title">
			<span>创建模板</span>
		</p>
		<div  id="message"  class="et-teachingPlan-content">
					<div class="form-group">
						<label>模板名称</label>
						<input type="text" class="form-control"  placeholder="请输入模板名称" style="width:600px;" id="name" name="name">
					</div>
					<div class="form-group resource-group bd-file-box">
						<label>文<i class="ml2"></i>件</label>
						<input type='text' name='textfield' id='textfield' class='txt form-control' />
						<input type='button' class='btn' value='选择文件' />
						<input type="file" name="file" class="file" id="file" size="28" accept=".img" onchange="document.getElementById('textfield').value=this.value" />
					</div>
					<div class="form-group">
						<label>模板描述</label>
						<input type="text" class="form-control"  placeholder="请输入模板描述" style="width:600px;" id="description" name="description">
					</div>
					<!-- <div class="form-group">
						<label>模板格式</label>
						<select class="form-control" style="width:600px;">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<div class="form-group">
						<label  class="control-label mr30 fl">模板共享</label>
						<div class="fl w600">
							<div class="mb30">
								<p class="fsize14 c-5ad mb20 fb">可见性</p>
								<p class="radio_box">
									<input class="radio" type="radio" name="public" value="">
									<span class="radio"> 公有 </span>
								</p>
								<p class="radio_box">
									<input class="radio" type="radio" name="public" value="">
									<span class="radio"> 私有  </span>
								</p>
							</div>
							<div class="mb30">
								<p class="fsize14 c-5ad mb20 fb">受保护的</p>
								<p class="radio_box">
									<input class="radio" type="radio" name="private" value="">
									<span class="radio"> 是 </span>
								</p>
								<p class="radio_box">
									<input class="radio" type="radio" name="private" value="">
									<span class="radio"> 否  </span>
								</p>
							</div>
						</div>
					</div> -->
					<div class="tac">
				<input id="btn-save" type="submit"
					class="btn affirm-btn mr40" value="添                加" /> <input
					id="btn-cancel" type="button"
					class="btn cancel-btn" value="取                消" />
			</div>
			</div>
	</div>
</form>
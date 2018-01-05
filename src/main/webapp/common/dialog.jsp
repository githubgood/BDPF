<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--弹窗-->
<div class="modal modal-box-1 fade modal_align" tabindex="-1" role="dialog" >
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">温馨提示</h4>
			</div>
			<div class="modal-body">
				<p class="tac mt50 mb50 fsize18" style="color:#fa5301;">确定要删除选中的数据吗</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-save mr30">确定</button>
				<button type="button" class="btn btn-default btn-cancel"  data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/弹窗-->

<!--时间弹窗-->
<div class="modal modal-box-time fade modal_align" tabindex="-1" role="dialog" >
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">温馨提示</h4>
			</div>
			<div class="modal-body">
				<p class="tac mt50 mb50 fsize18" style="color:#fa5301;">您本次实验还有是15分钟！是否加时？</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-save mr30">不用了</button>
				<button type="button" class="btn btn-default btn-cancel btn-time"  data-dismiss="modal" style="background-color:#cc6600;">加时</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/时间弹窗-->

<!--警告弹窗-->
<div class="modal-box modal modal_align fade" tabindex="-1" role="dialog" >
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">温馨提示</h4>
			</div>
			<div class="modal-body">
				<p class="tac mt50 mb50 fsize18" style="color:#fa5301;">请选择要删除的数据！</p>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/警告弹窗-->
<!--成功-->
<div class="success-remind-box" >
	<img src="${ctx}/resources/images/success.png" alt="成功">
</div>
<!--/成功-->
<!--失败-->
<div class="fail-remind-box">
	<img src="${ctx}/resources/images/fail.png" alt="失败">
</div>
<!--/失败-->
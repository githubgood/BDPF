<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$(".VMware-operate .fl a:lt(2),.VMware-operate .fr").hide();
$(".VMware-operate .fl a:last").show();
</script>
<div class="table-responsive com-table">
<input type="hidden" id="classId" name="classId" value="${classId}">
<input type="hidden" id="id" name="id"  value="${id}">
	<table class="table" border="0">
		<thead>
			<tr>
				<th>实验资源</th>
				<th>学生学号</th>
				<th>学生名称</th>
				<th>实验报告状态</th>
				<th>任务状态</th>
			</tr>
		</thead>
		<tbody id="list-tbody">
			<tr>
				<td colspan="8">对不起，暂时没有您所需要的数据！</td>
			</tr>
		</tbody>
	</table>
	<!--分页-->
	<div id="paginator-tool-detail" style="margin: auto;"></div>
	<script type="text/javascript">
		/*表格行间隔色*/
		$('.table-list tbody tr:odd').addClass('odd');
	</script>
</div>
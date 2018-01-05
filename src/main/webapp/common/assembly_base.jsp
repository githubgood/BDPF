<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="bdpf"  value="讯方大数据创新实训系统" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>${bdpf}</title>
 <link rel="stylesheet" type="text/css" href="${ctx}/resources/assembly/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/assembly/css/base.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/assembly/css/common.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/assembly/css/page-style.css">
</head>
<body>
<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/echarts-all.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.jsPlumb-1.6.2-min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript">
window.ctx = '${ctx}'; //javascript context 项目名称
window.pageNo = '${pageNo+1}';//从第几条开始取数据
window.pageSize ='${pageSize}';//每页显示多少条数据

</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>大数据-二级菜单管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
</head>
<body class="ly-bg">
			<c:forEach items="${sessionScope.menuMap}" var="menu">
				<c:if test="${menu.key.resCode == param.modelId}">
					<c:forEach items="${menu.value}" var="menulist" varStatus="status">
						<dd class="et-nav-item <c:if test="${fn:contains(menulist.reqMapping,param.menuId)}"> active </c:if>">
							<i class="active-line"></i><a href="${ctx}${menulist.reqMapping}">${menulist.name}</a>
						</dd>
					</c:forEach>
				</c:if>
			</c:forEach>
</body>
</html>
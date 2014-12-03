<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <%@ page import="java.util.*" %> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Autoshow</title>
</head>
<body>
	<div class="hello">Hello! We have ${markCount } cars marks!</div>
	<div class="markList">
		<c:forEach var="mark" items="${markList }">
			<div class="markName">${mark.name }</div>
		</c:forEach>
	</div>
	<div class="pageList">
		<c:forEach var="page" begin="0" end="${pageCount }">
			<a href="${pageContext.request.contextPath }/index?page=${page}">${page + 1 }</a>
		</c:forEach>
	</div>
</body>
</html>
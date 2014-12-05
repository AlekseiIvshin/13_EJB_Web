<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Autoshow</title>
</head>
<body>
	<div class="errorMessage">${hardStatus }</div>
	<div class="hello">Hello! We have ${markCount } cars marks!</div>
	<c:choose>
		<c:when test="${username ne null }">${username }<a
				href="${pageContext.request.contextPath }/account">Выход</a>
		</c:when>
		<c:otherwise>
			<form action="account" method="post">
				<input type="text" name="username" /> <input type="submit"
					value="Логин" />
			</form>
		</c:otherwise>
	</c:choose>
	<div>
		<c:if test="${param.error ne null }">
			<div class="errorMessage">${param.error }</div>
		</c:if>
		<c:if test="${param.success ne null }">
			<div class="successMessage">${param.success }</div>
		</c:if>
		Добавить марку:
		<form action="mark.create" method="post">
			<input type="text" name="name" /> <input type="submit"
				value="Создать" />
		</form>
	</div>
	<div class="listconfig">
		<form action="configuration" method="post">
			Кол-во автомобилей на странице: <input type="text"
				name="view.mark.count.perpage" /> <input type="submit" value="Ок" />
		</form>
	</div>
	<div class="markList">
		<c:forEach var="mark" items="${markList }">
			<div class="markName">${mark.name }</div>
		</c:forEach>
	</div>
	<div class="pageList">
		<c:forEach var="page" begin="1" end="${pageCount }">
			<a href="${pageContext.request.contextPath }/index?page=${page-1}">${page }</a>
		</c:forEach>
	</div>

</body>
</html>
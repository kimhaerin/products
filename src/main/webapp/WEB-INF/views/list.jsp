<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<style>
li {
	list-style: none;
}
</style>

	<script src="http://code.jquery.com/jquery-latest.min.js"></script>

	<h1>List</h1>
	<form action="cartAction" method="post">
	<ul>
		<c:forEach items="${file}" var="vo">
			<li><input type="checkbox" id="check" name="check" value="${vo.pno}"><img
				src="show?name=${vo.pimg}">&nbsp;&nbsp;&nbsp; <b>${vo.pname}</b>&nbsp;&nbsp;&nbsp;
				: &nbsp;&nbsp;&nbsp; ${vo.pmaker}</li>
		</c:forEach>
	</ul>
	<input type="submit">
	</form>


</body>
</html>
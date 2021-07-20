<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empList</title>
<style>
	body {font-size:11pt; color:teal;}
	div {margin:0 auto}
</style>
</head>
<body>
	<div>
		<h2>사원정보 리스트</h2>
		<c:forEach var="e" items="${empList }">
			${e.empno }
			<a href="empInfo?empno=${e.empno }">${e.ename }</a>
			${e.deptno } ${e.job } ${e.sal } <br>
		</c:forEach>
		<p><a href="empInputForm">사원정보 추가</a></p>	
	</div>
</body>
</html>
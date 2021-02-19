<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	테스트페이지 입니다.<br>
	전달된 히든 값이 서버를 거쳐 반사되어 돌아오고 있습니다.<br>
	여러면 누르면 값이 점점 커집니다.<br>
	<form action="test.do" method="post">
		<input type="hidden" name="paramTest" value="${reflection }">
		<input type="submit" value="히든값 전달	">
	</form>
	
	서버에서 되돌아 오는 값 : ${reflection }<br>
	<a href="main.do">메인페이지로</a>
	
</body>
</html>

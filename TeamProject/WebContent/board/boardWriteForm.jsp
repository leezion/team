<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="loginID" value="${sessionScope.loginID }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:choose>
	<c:when test="${loginID ne null }">
		<form action="boardWrite.do" method="post">
			<table>
				<tr>
					<td>글 제목</td>
					<td><input type="text" name="title">
					<td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="글쓰기">&nbsp;<a
						href="boardlist.jsp"><button>뒤로가기</button></a></td>
				</tr>
			</table>
			<input type="hidden" name="userid" value="${sessionScope.loginID }">
		</form>
	</c:when>



	<c:otherwise>
		<body>
			<form action="boardWrite.do" method="post">
				<table>
					<tr>
						<td>아이디</td>
						<td>비밀번호</td>
					</tr>
					<tr>

						<td><input type="hidden" name="userid" value="">
							<input type="text" name="userid_off"></td>
						<td><input type="password" name="password_off"></td>
					</tr>
					<tr>
						<td>글 제목</td>
						<td><input type="text"  name="title">
						<td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea name="content"></textarea></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="글쓰기">&nbsp;<a
							href="boardlist.do"><button>뒤로가기</button></a></td>
					</tr>
				</table>

			</form>
	</c:otherwise>
</c:choose>
</body>
</html>
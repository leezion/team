<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginID" value="${sessionScope.loginID }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center">
<b>글수정</b><br><br>

		<form action="updatePro.do?pageNum=${pageNum }"
			method="post" name="writeForm">


			<table width="400" border="1" cellpadding="0" cellspacing="0"
				align="center">

				<tr>
					<td width="70" align="center">이름</td>
					<td width="330" align="left"><input type="text" size="10"
						maxlength="10" name="userid" value="${article.userid}${article.userid_off}"> <input
						type="hidden" name="articleid" value=${article.articleid }></td>
				</tr>

				<tr>
					<td width="70" align="center">제목</td>
					<td width="330" align="left"><input type="text" size="50"
						maxlength="50" name="title" value="${article.title}"></td>
				</tr>

				<tr>
					<td width="70" align="center">내용</td>
					<td width="330" align="left"><textarea rows="13" cols="50"
							name="content">${article.content}</textarea></td>
				</tr>

				<c:choose>
					<c:when test="${loginID eq null }">
					<tr>
						<td width="70" align="center">비밀번호</td>
						<td width="330" align="left"><input type="password" size="10"
						maxlength="10" name="password"></td>
					</tr>
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
</c:choose>
				<tr>
					<td colspan="3" align="center"><input type="submit"
						value="글수정"> <input type="reset" value="다시작성"> <input
						type="button" value="목록"
						onclick="document.location.href='/boardlist.do?pageNum=${pageNum}' ">
					</td>
				</tr>

			</table>

		</form>
	</div>
</body>
</html>
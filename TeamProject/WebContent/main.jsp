<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="loginID" value="${sessionScope.loginID }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
div.left {
	float: left;
}

div.right {
	float: right;
}

div.center {
	float: center;
}
</style>
</head>
<body>

		<div class="left">
	<c:choose>
		<c:when test="${loginID ne null }">
			<table>
				<tr>
					<td><c:out value="${loginID }">님 ㅎㅇㅎㅇ<br></c:out></td>
				</tr>
				<tr>
					<td>
					<button onclick="location.href='logout.do'">로그아웃</button>
					<button onclick="location.href='*'">마이메뉴</button>
			
					</td>
				</tr>
				</table>
		</c:when>



		<c:otherwise>
			
			<c:if test="${check == 0}">
				<script>
					alert('아이디 혹은 비밀번호가 틀렸습니다.');
				</script>
			</c:if>
			
			<c:if test="${requestScope.check eq -1}">
				<script>
					alert('아이디 혹은 비밀번호가 틀렸습니다.');
				</script>
			</c:if>
		
			<form action="login.do" method="post">
				<div>
					<div>
						<div>ID</div>
						<div>
							<input type="text" name="userid">
						</div>
					</div>
					<div>
						<div>PASSWORD</div>
						<div>
							<input type="password" name="password">
						</div>
					</div>
					<div>
						<input type="submit" value="로그인"> <input type="button"
							value="회원가입" onclick="location='register/register.jsp'">
					</div>
				</div>
			</form>
		</c:otherwise>
	</c:choose>

		<hr>
		<div>게시판</div>
		<div>
			<ul>
				<li>공지사항</li>
				<li><a href="boardlist.do">자유게시판</a></li>
				<li>Q&amp;A게시판</li>
			</ul>
		</div>
		<div>
			<input type="search">
		</div>
		<div>판매자랭킹</div>
		<div>유의사항</div>
		<div>사기피해방지</div>
		<div>신고/문의</div>
	</div>
	
	<div class="right">
		<ul>
			<ins>메인화면</ins>
			<ins>__상태1(비로그인)</ins>
			<li>1로그인</li>
			<li>2회원가입</li>
			<ins>__상태2(로그인)</ins>
			<li>1로그아웃</li>
			<li>2마이메뉴</li>
			<ins>__공통</ins>
			<li>3상품리스트확인</li>
			<li>4상품검색</li>
			<li>5커뮤니티</li>
			<li>6판매자랭킹</li>
			<li>7유의사항</li>
			<li>8사기피해방지</li>
			<li>9신고</li>

		</ul>

	</div>

	<div class="center">
		<div>
			<div align = "left">한글판</div>
			
			<div align = "right">카드용품</div>
			<div align = "center">일본판</div>
		</div>
		<hr>
		<div>
			메인페이지 입니다.<br> <a href="test.do">test.do</a>의 파라미터 전달 예시를
			확인해보세요.<br>
		</div>
		<hr>
		<div>
			<div><a href = "boardlist.do">실시간 게시글</a></div>
			<div>
				<div></div>
				<div></div>
			</div>
		</div>
	</div>

	
</body>
</html>
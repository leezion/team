<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="register.do" method="post">
		<div align="center">
			<div>아이디</div>
			<div>
				<input type="text" name="userid">
			</div>
			<div>비밀번호</div>
			<div>
				<input type="password" name="password">
			</div>
			<div>이름</div>
			<div>
				<input type="text" name="name">
			</div>
			<div>이메일</div>
			<div>
				<input type="text" name="eamilss">@<input list="emails">
				<datalist id="emails">
					<option value="naver.com">
					<option value="zum.com">
					<option value="hanmail.net">
					<option value="gmail.com">
					<option value="icloud.com">
				</datalist>
			</div>
			<div>전화번호</div>
			<div>
				<input list="phones" size="3">-<input type="text"
					name="phoness" size="4">-<input type="text" name="phonesss"
					size="4">
				<datalist id="phones">
					<option value="010">
					<option value="02">
					<option value="031">
					<option value="032">
					<option value="053">
					<option value="063">
				</datalist>
			</div>
			<div>오픈 카톡 주소</div>
			<div>
				<input type="text" name="kakao_open">
			</div>
			<div>카카오톡 아이디</div>
			<div>
				<input type="text" name="kakao_id">
			</div>
			<div>우편번호</div>
			<div>
				<input type="text" name="zipcode"><input type="button"
					value="검색">
			</div>
			<div>주소</div>
			<div>
				<input type="text" name="address1">
			</div>
			<div>상세주소</div>
			<div>
				<input type="text" name="address2">
			</div>
			<div>
				<input type="submit" value="회원가입">
				<button onclick="location='main.jsp'">나중에</button>
			</div>
		</div>
	</form>
	W

</body>
</html>
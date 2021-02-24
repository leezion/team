<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function deleteSave() {
		if (document.delForm.pass.value == "") {

			alert("비밀번호를 입력해 주세요.");
			document.delForm.pass.focus();
			return false;
		}
	}
</script>
</head>
<body>

	<div align="center">
		<b>글삭제</b> <br>
		<br>
		<form action="deletePro.do?pageNum=${pageNum}" method="post"
			name="delForm" onsubmit="return deleteSave()">

			<table border="1" align="center" cellpadding="0" cellspacing="0"
				width="360">

				<tr height="30">
					<td align="center"><b>비밀번호를 입력해 주세요.</b></td>
				</tr>

				<tr height="30">
					<td align="center">비밀번호: <input type="password"
						name="password" size="8" maxlength="12"> <input
						type="hidden" name="articleid" value="${articleid }">
					</td>
				</tr>


				<tr height="30">
					<td align="center"><input type="submit" value="글삭제"> <input
						type="button" value="글목록"
						onclick="document.location.href='/TeamProject/board/boardlist.do?pageNum=${pageNum}'">
					</td>
				</tr>

			</table>

		</form>

	</div>

</body>
</html>
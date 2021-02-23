<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function frm_sub(i){
	i_frm.action="boardlist.do?pageNum="+i;
	i_frm.submit();
}
function check(){
	if(document.find_frm.find_box.value==""){
		alert("검색어를 입력해 주세요");
		return false;
	}
}
</script>

</head>
<body bgcolor="${bodyback_c }">

	<a href="main.do">메인으로</a>
	<div align="center">
		<b>글목록(전체 글: ${count})</b>

		<table width="700">

			<tr>
				<td align="right" bgcolor="${value_c }"><a
					href="/TeamProject/board/boardWriteForm.jsp">글쓰기</a></td>
			</tr>
		</table>
		<c:if test="${count == 0 }">
			<table width="700" border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">게시판에 글이 없어요.. 글을 추가하세요</td>
				</tr>
			</table>
		</c:if>

		<c:if test="${count > 0 }">

			<table width="700" border="1" cellpadding="0" cellspacing="0"
				align="center">

				<tr height="30" bgcolor="${value_c }">
					<td align="center" width="50">번호</td>
					<td align="center" width="250">제목</td>
					<td align="center" width="100">작성자</td>
					<td align="center" width="150">작성일</td>
					<td align="center" width="50">조회</td>
					<td align="center" width="100">IP</td>
				</tr>

				<c:forEach var="article" items="${articleList }">

					<tr height="30">
						<td align="center" width="50"><c:out value="${number }" /> <c:set
								var="number" value="${number - 1}" /></td>

						<td width="250">
						 <%-- 게시글의 클릭하면 페이지 이동하면서 
                                 쿼리스트리밍으로 url 출력 (상세 페이지로 이동) 
                                 페이지는 현재 페이지 
                          --%> <%-- 6 --%> <a
							href="boardlist.do?num=${article.articleid}&pageNum=${currentPage}">
								${article.title}</a></td>

						<td align="center" width="100"> ${article.userid} ${article.userid_off }</td>

						<td align="center" width="150">${article.regdate }</td>

						<td align="center" width="50">${article.readcount }</td>

						<td align="center" width="100">${article.ip }</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<br>
		<br>
		<c:if test="${count > 0 }">

			<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1 }" />
			<fmt:parseNumber var="pageCount" value="${count/pageSize+imsi}"
				integerOnly="true" />
			<c:set var="pageBlock" value="${3}" />
			<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}"
				integerOnly="true" />
			<c:set var="startPage" value="${result * pageBlock + 1 }" />
			<c:set var="endPage" value="${startPage + pageBlock-1}" />
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>
			<c:if test="${startPage > pageBlock}">
				<a href="#" onClick="frm_sub(${startPage - pageBlock })">[이전]</a>
			</c:if>


			<c:forEach var="i" begin="${startPage }" end="${endPage}">
				<a href="#" onClick="frm_sub(${i})">[${i}]</a>
			</c:forEach>


			<c:if test="${endPage < pageCount }">
				<a href="#" onClick="frm_sub(${startPage + pageBlock})">[다음]</a>
			</c:if>
		</c:if>
		<br />
		<br />
		<form method="post" name="i_frm">
			<input type="hidden" name="find_box" value="${find_box}" /> <input
				type="hidden" name="find" value="${find}" />
		</form>
		<form method="post" name="find_frm" action="boardlist.do"
			onsubmit="return check()">
			<select name="find" size="1">
				<option value="writer">작성자</option>
				<option value="subject">제목</option>
				<option value="content">내용</option>
			</select> &nbsp; <input type="text" name="find_box" /> &nbsp; <input
				type="submit" value="검색" />
		</form>

	</div>
</body>
</html>
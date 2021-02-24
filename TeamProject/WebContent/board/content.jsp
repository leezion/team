<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>

<form>

<table width="500" border="1" cellpadding="0" cellspacing="0"
 align="center">

<tr height="30">
      <td width="125" align="center">글번호</td>
      <td width="125" align="center">${articleid }</td>
      
      <td width="125" align="center">조회수</td>
      <td width="125" align="center" >${readcount}</td>
</tr>

<tr height="30">
      <td width="125" align="center">작성자</td>
      <td width="125" align="center" >${userid }${userid_off }</td>
      
      <td width="125" align="center">작성일</td>
      <td width="125" align="center" >${regdate }</td>
</tr>

<tr height="30">
      <td width="125" align="center">글 제목</td>
       <td align="left" width="250" colspan="3">${title }
       </td>
</tr>

<tr height="30">
      <td width="125" align="center">글 내용</td>
       <td align="left" width="375" colspan="3">
       <pre>${content }</pre>
       </td>
</tr>

<tr height="30">
      
   <td colspan="4" align="right">
        <input type="button" value="글수정" 
       onclick="document.location.href='updateForm.do?articleid=${articleid }&pageNum=${pageNum }'">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="글삭제" 
       onclick="document.location.href='delete.do?articleid=${articleid }&pageNum=${pageNum }'">
        
      <input type="button" value="글목록" 
       onclick="document.location.href='boardlist.do?pageNum=${pageNum }'">
      
      </td>
</tr>


</table>

</form>


</body>
</html>
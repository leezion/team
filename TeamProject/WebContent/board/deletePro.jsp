<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:if test="${check == 1 }">

<meta charset="UTF-8" http-equiv="Refresh"
 content="0;url=/TeamProject/board/boardlist.do?pageNum=${pageNum }">
 </c:if>
 
 <c:if test="${check == 0 }">
  비밀번호가 틀립니다.
  <br>
  <a href="javascript:history.go(-1)">[글 삭제 폼으로 돌아가기]</a>
</c:if>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
   int check;
   if(request.getParameter("check")==null){
      check = -1;   
   }else{
      check = Integer.parseInt(request.getParameter("check"));
   }
   
   response.sendRedirect("login.do");
   
   if(check==1){
   response.sendRedirect("../main.jsp");
   }else if (check ==0||check==-1){
      %>
      <script>
      alert('��ȣ ���̵� Ʋ��')
      </script>
      <%
      response.sendRedirect("../main.jsp");
   }
   
%>


   
</body>
</html>
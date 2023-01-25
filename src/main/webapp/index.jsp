<%@page pageEncoding="utf-8"%>

<%
   //嵌入语法不能使用el表达式${pageContext.request.contextPath}形式.
   response.sendRedirect(request.getContextPath()+"/employee/ListEmp.do");
%>
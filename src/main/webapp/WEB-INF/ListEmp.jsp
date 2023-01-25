<%@page pageEncoding="utf-8" 
        import="java.util.*,entity.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>职员信息列表</title>
	<link rel="stylesheet" 
	    type="text/css" href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<div id="wrap">
		<%@include file="head.jsp"%>
		<div id="middle">	
			
				<table class="table" border="0px">
				    <caption>
				       <h2>职员信息列表</h2>
				    </caption>
					<tr class="table_header">
						<td>ID号</td>
						<td>姓名</td>
						<td>职位</td>
						<td>薪水</td>
						<td>操作</td>
					</tr>
					<%--获得数据库表中的集合数据 --%>
					<c:forEach var="employee" items="${employees }">
					 <tr>
						<td>${employee.id}</td>
						<td>${employee.name}</td>
						<td>${employee.job}</td>
						<td>${employee.salary }</td>
						<td>
						  <a href="DeleteEmp.do?id=${employee.id }" 
						     onclick="return confirm('确实要删除吗？');">删除</a>
						  &nbsp;&nbsp;
						  <a href="LoadEmp.do?id=${employee.id}">更新</a>
						</td>
					 </tr>
                    </c:forEach>
				</table>
				<p>
					<input type="button" class="button" value="添加职员"
						onclick="location='AddForm.do'">
				</p>
			
		</div>
		<%@include file="foot.jsp" %>
		
	</div>
</body>
</html>
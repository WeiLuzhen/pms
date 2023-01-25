<%@ page pageEncoding="utf-8" 
         import="entity.*"%>
<html>
<head>
	<title>加载数据</title>
	<link type="text/css"
          href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<%@ include file="head.jsp" %>
		<div id="middle">	
				<h1>更新职员信息</h1>
				<form action="UpdateEmp.do?id=${employee.id }" method="post">
					<table class="form_table" cellspacing="0" border="0">
					  <tr>
					  	<td>ID号：</td>
					  	<td>${employee.id }</td>
					  </tr>
					  <tr>
					  	<td>姓名：</td>
					  	<td>
					  	  <input class="inputgri" type="text" 
					  	         name="name" value="${employee.name}"/>
					  	</td>
					  </tr>
					  <tr>
					  	<td>职位：</td>
					  	<td>
					  	  <input class="inputgri" type="text" 
					  	         name="job" value="${employee.job}"/>
					  	</td>
					  </tr>
					  <tr>
					  	<td>薪水：</td>
					  	<td>
					  	  <input class="inputgri" type="text" 
					  	         name="salary" value="${employee.salary}"/>
					  	</td>
					  </tr>
					</table>
					<p>
					  <input class="button" type="submit" value="确认" />
					</p>
				</form>
		</div>
		<%@ include file="foot.jsp" %>
	</div>
</body>
</html>
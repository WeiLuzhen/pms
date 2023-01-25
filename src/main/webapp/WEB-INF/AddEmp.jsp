<%@ page pageEncoding="utf-8"%>
<html>
<head>
	<title>增加职员</title>
	<link type="text/css"
		  href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" >
</head>
<body>
<div id="wrap">
	<%@include file="head.jsp" %>
	<div id="middle">
		<h1>增加职员</h1>
		<form action="InsertEmp.do" method="post">
			<table class="form_table" cellspacing="0" border="0" >
				<tr>
					<td align="right">姓名：</td>
					<td align="left">
						<input class="inputgri" type="text" name="name" />
					</td>
				</tr>
				<tr>
					<td align="right">职位：</td>
					<td align="left">
						<input class="inputgri" type="text" name="job" />
					</td>
				</tr>
				<tr>
					<td align="right">薪水：</td>
					<td align="left">
						<input class="inputgri" type="text" name="salary" />
					</td>
				</tr>
			</table>
			<p>
				<input class="button" type="submit" value="确认"/>
			</p>
		</form>
	</div>
	<%@include file="foot.jsp" %>
</div>
</body>
</html>
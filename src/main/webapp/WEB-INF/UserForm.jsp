<%@ page pageEncoding="utf-8"%>
<html>
<head>
	<title>用户登录</title>
	<link type="text/css"
          href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" >
</head>
<body>
	<div id="wrap">
		<%@include file="head.jsp"%>
		<div id="middle">
			<h1>用户登录</h1>
			<form action="UserLogin.do" method="post">
				<div style="color:red;font-size:18px">
				     ${errorMessage }
				</div>
				<table cellspacing="0" border="0">
					<tr>
						<td align="right">用户：</td>
						<td align="left"><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td align="right">密码：</td>
						<td align="left"><input type="text" name="password" /></td>
					</tr>

				</table>
				<p>
					<input class="button" type="submit" value="登录" />
				</p>
			</form>
		</div>
		<%@include file="foot.jsp"%>
	</div>
</body>
</html>
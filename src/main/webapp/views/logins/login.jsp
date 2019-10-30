<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
  body{background-color:lightgray;}
  table {  	
  	margin: 0 auto;
  	}
  th{font-size:12px}
  td{font-size:12px}
</style>
<body>	
	<center>
   <form method="post" action="<c:url value='/j_spring_security_check' />" id="loginForm">
	<table>
	<tr>
		<th>用户名：</th>
		<td><input type="text" name="j_username"></td>
	</tr>
	<tr>
		<th>密码：</th>
		<td><input type="password" name="j_password"></td>
	</tr>
	<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	<tr>
		<td><input type="submit" value="登录"/>
		<td><input type="reset" value="重置"/>
	</tr>
	</table>
  </form>
 </center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更改密码</title>
</head>
<body>
<div  style="padding:10px;vertical-align: middle">
<form id="changPasswordForm" method="post">
  <table style="width:100%;height:100%">
  <tr>
	<th>原密码：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="oldPassword" data-options="required:true"/>
	</td>
  </tr>
  <tr>
	<th>新密码：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="newPassword" data-options="required:true"/>
	</td>
  </tr>
  </table>
</form>
</div>
</body>
<style type="text/css">
	input{width:120px}
</style>
</html>
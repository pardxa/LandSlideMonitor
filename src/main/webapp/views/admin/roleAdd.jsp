<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加角色</title>
</head>
<body>
<div  style="padding:10px;vertical-align: middle">
<form id="roleAddForm" method="post">
 	<input type="hidden" name="roleId"/>
  <table style="width:100%;height:100%">
  <tr>
	<th>角色名称：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="roleName" data-options="required:true"/>
	</td>
  </tr>
  <tr>
	<th>备注：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="roleNote" data-options="required:true"/>
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
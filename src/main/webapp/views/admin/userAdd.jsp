<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
</head>
<body>
<div  style="padding:10px;vertical-align: middle">
<form id="userAddForm" method="post">
 	<input type="hidden" name="userId"/>
 	<input type="hidden" name="userInfoId" />
  <table style="width:100%;height:100%">
  <tr>
	<th>账号名：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="userName" data-options="required:true"/>
	</td>
	<th>姓名：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="userShowName" data-options="required:true"/>
	</td>
  </tr>
  <tr>
	<th>密码：</th>
	<td>
		<input class="easyui-validatebox" type="password" name="passWord" data-options="required:true"/>
	</td>
	<th>是否可用：</th>
	<td>
	<input id="isEnabled" class="easyui-combobox" name="isEnabled"
    data-options="valueField:'dictCode',textField:'dictName',url:'getdicitems.biz?dicttypeid=DIC_YES_NO',value:'1'">
	</td>
  </tr>
  <tr>
	<th>未锁定：</th>
	<td>
	<input id="isActNonLocked" class="easyui-combobox" name="isActNonLocked"
    data-options="valueField:'dictCode',textField:'dictName',url:'getdicitems.biz?dicttypeid=DIC_YES_NO',value:'1'">
	</td>
	<th>账户到期日：</th>
	<td>
		<input class="easyui-datebox" type="text" name="actExpireDate"/>
	</td>
  </tr>
  <tr>
	<th>证书到期日:</th>
	<td>
		<input class="easyui-datebox" type="text" name="credentialsExpireDate" />
	</td>
	<th>联系电话：</th>
	<td>
	<input class="easyui-validatebox" type="text" name="userTel"/>
	</td>
  </tr>
  <tr>
	<th>工作单位：</th>
	<td colspan="3">
	<input class="easyui-validatebox" type="text" name="userCompany" style="width:100%"/>
	</td>
  </tr>
  </table>
</form>
</div>
</body>
<style type="text/css">
	input{width:80px}
</style>

<script type="text/javascript">
<!--

//-->
</script>
</html>
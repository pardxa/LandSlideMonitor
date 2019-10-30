<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加阀值</title>
</head>
<body>
<div  style="padding:10px;vertical-align: middle">
<form id="thdAddForm" method="post">
 	<input type="hidden" name="bhId" id="bhId"/>
 	<input type="hidden" name="clCode1" id="clCode1"/>
 	<input type="hidden" name="clCode2" id="clCode2"/>
 	<input type="hidden" name="clCode3" id="clCode3"/>
 	<input type="hidden" name="clCode4" id="clCode4"/>
 	<input type="hidden" name="thdId1" id="thdId1"/>
 	<input type="hidden" name="thdId2" id="thdId2"/>
 	<input type="hidden" name="thdId3" id="thdId3"/>
 	<input type="hidden" name="thdId4" id="thdId4"/>
  <table style="width:100%;height:100%">
  <tr>
	<th>1道阀值：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="C1" id="C1" data-options="required:true"/>
	</td>
	<th>2道阀值：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="C2" id="C2" data-options="required:true"/>
	</td>
  </tr>
  <tr>
	<th>3道阀值：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="C3" id="C3" data-options="required:true"/>
	</td>
	<th>4道阀值：</th>
	<td>
		<input class="easyui-validatebox" type="text" name="C4" id="C4" data-options="required:true"/>
	</td>
  </tr>
  </table>
</form>
</div>
</body>
<style type="text/css">
	input{width:120px}
</style>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div  style="padding:10px;vertical-align: middle">
<form id="boreholeAddForm" method="post">
	<input type="hidden" name="bhId"/>
	<input type="hidden" name="nkId" id="nkId"/>
	<table>
		<tr>
			<th>桩点名称</th>
			<td>
				<input class="easyui-validatebox" type="text" name="bhName" data-options="required:true"/>
			</td>
			<th>设备编号</th>
			<td>
				<input class="easyui-validatebox" type="text" name="dvCode" data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<th>设备名称</th>
			<td>
				<input class="easyui-validatebox" type="text" name="dvName" data-options="required:true"/>
			</td>
			<th>SIM卡号</th>
			<td>
				<input class="easyui-validatebox" type="text" name="dvSimCode" data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<th>经度</th>
			<td>
				<input class="easyui-numberbox" 
					data-options="min:-999999.999999999,max:999999.999999999,precision:9" name="bhLg"/>
			</td>
			<th>纬度</th>
			<td>
				<input class="easyui-numberbox" 
					data-options="min:-999999.999999999,max:999999.999999999,precision:9" name="bhLt"/>
			</td>
		</tr>
		<tr>
			<th>位移范围</th>
			<td>
				<input class="easyui-combobox" id="bhDisplaceRange" name="bhDisplaceRange" style="width:120px"
            data-options="valueField:'dictCode',textField:'dictName',url:'getdicitems.biz?dicttypeid=DIC_BH_DISPLACE_RANGE'"/>
			</td>
			<th>初始坐标DE</th>
			<td>
				<input class="easyui-numberbox" 
					data-options="min:-999999.999999999,max:999999.999999999,precision:9" name="bhIntCoordDe"/>
			</td>			
		</tr>
		<tr>
			<th>初始坐标DN</th>
			<td>
				<input class="easyui-numberbox" 
					data-options="min:-999999.999999999,max:999999.999999999,precision:9" name="bhIntCoordDn"/>
			</td>
			<th>初始坐标DU</th>
			<td>
				<input class="easyui-numberbox" 
					data-options="min:-999999.999999999,max:999999.999999999,precision:9" name="bhIntCoordDu"/>
			</td>
		</tr>
		<tr>
		<th>联系人</th>
		<td>
		<input class="easyui-validatebox" type="text" name="bhContact"/>
		</td>
		<th>联系电话</th>
		<td>
		<input class="easyui-validatebox" type="text" name="bhTel"/>
		</td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="3">
		<input class="easyui-textbox" 
		name="bhNote" 
		data-options="multiline:true" 
		style="width:100%;height:50px"/>
		</td>
	</tr>		
	</table>
</form>
</div>
</body>
</html>
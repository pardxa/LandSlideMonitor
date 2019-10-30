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
<form id="networkAddForm" method="post">
	<input type="hidden" name="nkId"/>
	<input type="hidden" name="nkStatus"/>
	<table>
	<tr>
		<th>监测网名称</th>
		<td>
			<input class="easyui-validatebox" type="text" name="nkName" id="nkName" data-options="required:true"/>
		</td>
		<th>监测网编号</th>
		<td>
			<input class="easyui-validatebox" type="text" name="nkCode" id="nkCode"/>
		</td>
	</tr>
	<tr>
		<th>监测网类型</th>
		<td>
		<input class="easyui-combobox" name="nkType" id="nkType"
    data-options="valueField:'dictCode',textField:'dictName',url:'getdicitems.biz?dicttypeid=DIC_NETWORK_TYPE',value:'1'"/>
		</td>
		<th>监测网启用时间</th>
		<td>
			<input class="easyui-datebox" type="text" name="nkStartDate" id="nkStartDate"/>
		</td>
	</tr>
	<tr>
		<th>服务器主IP</th>
		<td>
			<input class="easyui-validatebox" type="text" name="nkPrimaryIp" id="nkPrimaryIp" data-options="required:true"/>
		</td>
		<th>服务器备用IP</th>
		<td>
			<input class="easyui-validatebox" type="text" name="nkBackupIp" id="nkBackupIp" data-options="required:true"/>
		</td>		
	</tr>
	<tr>
		<th>服务器端口</th>
		<td>
			<input class="easyui-numberbox" precision="0" name="nkPort" id="nkPort"/>
		</td>
		<th>服务器备用端口</th>
		<td>
			<input class="easyui-numberbox" precision="0" name="nkBackupPort" id="nkBackupPort"/>
		</td>		
	</tr>
	<tr>
		<th>设备数据上传时间间隔</th>
		<td>
			<input class="easyui-numberbox" precision="2" name="nkDataUploadInv" id="nkDataUploadInv"/>
		</td>
		<th>数据加密标记 </th>
		<td>
			<input type="radio" id="nkDataEncryptiony" name="nkDataEncryption" value="1" checked />加密
			<input type="radio" id="nkDataEncryptionn" name="nkDataEncryption" value="0"/>未加密
		</td>		
	</tr>
	<tr>
		<th>详细地址</th>
		<td colspan="3">
			<input class="easyui-validatebox" type="text" name="addr" id="addr" style="width:100%"/>
		</td>
	</tr>
	<tr>
		<th>省/市/县</th>
		<td colspan="3">
		<input class="easyui-combobox" id="province" name="province" style="width:120px"
            data-options="valueField:'dictCode',textField:'dictName',url:'getdicitems.biz?dicttypeid=DIC_REGION'"/> -
		<input class="easyui-combobox" id="city" name="city" style="width:100px"
            data-options="valueField:'dictCode',textField:'dictName'"/> -
		<input class="easyui-combobox" id="region" name="region" style="width:100px"
            data-options="valueField:'dictCode',textField:'dictName'"/>			
		</td>
	</tr>
	<tr>
		<th>联系人</th>
		<td>
		<input class="easyui-validatebox" type="text" name="nkContact" id="nkContact"/>
		</td>
		<th>联系电话</th>
		<td>
		<input class="easyui-validatebox" type="text" name="nkTel" id="nkTel"/>
		</td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="3">
		<input class="easyui-textbox" 
		name="nkNote" id="nkNote"
		data-options="multiline:true" 
		style="width:100%;height:50px"/>
		</td>
	</tr>
	</table>	
</form>
</div>
</body>
</html>
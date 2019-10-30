<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>滑坡数据表</title>
</head>
<%@include file="../common/Common.jsp" %>
<body>
       <!-- dataSearchToolbar -->
       <div class="easyui-panel">
       	<form id="dataSearch" method="post">
       	<table>
  			<tr>
				<td>
					&nbsp;监测网名称:<input id="nk" class="easyui-combobox" name="nk"
    					data-options="valueField:'nkId',textField:'nkName',
    					url:'comboquerynetworklist.biz',onSelect: function(rec){loadBorehole(rec);}">
					&nbsp;桩点名称：<input id="bh" class="easyui-combobox" name="bh"
    					data-options="valueField:'bhId',textField:'bhName'">
					&nbsp;时间：<input class="easyui-datetimebox" name="startTime" id="startTime"
        				data-options="showSeconds:true" style="width:150px"/>
        			-
        				<input class="easyui-datetimebox" name="endTime" id="endTime"
        				data-options="showSeconds:true" style="width:150px"/>	
				</td>
				<td>
					<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				</td>
				<td>
					<a id="export" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="javascript:exportData()">导出</a>
				</td>
  			</tr>
  		</table>
       	</form>
       </div><!--end of dataSearchToolbar -->
       <!-- dataListGrid -->
         <table id="dataListGrid"></table>         
</body>
<script type="text/javascript">
$(function(){
	$('#btn').bind('click',function(){
		queryData();
	});
	createDataListGrid();
});
function loadBorehole(rec){
	var nkId=rec.nkId;
	var url="comboqueryboreholeslist.biz?nkId="+nkId;
	$('#bh').combobox('reload', url);
}
//查询
function queryData(){
	var bhData=$('#bh').combobox('getData');
	if(bhData!=null&&bhData.length>0){
		var bhId=$('#bh').combobox('getValue');
		var params=null;
		//debugger;
		var startTimeData=$('#startTime').datebox('getValue');
		var endTimeData=$('#endTime').datebox('getValue');
		if(startTimeData==''||endTimeData==''){
			alert("请选择起止日期！");
			return;
		}
		params={bhId:bhId,startTime:startTimeData,endTime:endTimeData};
		$('#dataListGrid').datagrid('load',params);
		/*
		var url="queryrealmonitordata.biz";
		$.post(url,params,function(o){
			debugger;
			var d=$.parseJSON(o);
			updateData(d);
		});	*/		
	}
}
//导出
function exportData(){
	var bhData=$('#bh').combobox('getData');
	if(bhData!=null&&bhData.length>0){
		var bhId=bhData[0].bhId;
		var params=null;
		//debugger;
		var startTimeData=$('#startTime').datebox('getValue');
		var endTimeData=$('#endTime').datebox('getValue');
		if(startTimeData==''||endTimeData==''){
			alert("请选择起止日期！");
			return;
		}
		params={bhId:bhId,startTime:startTimeData,endTime:endTimeData};
		exportExcelFromIFrame("realMonitorExcel","realMonitorExcel",params);
	}	
}
//1.1.创建用户列表 
function createDataListGrid(){
	  $('#dataListGrid').datagrid({
		  //data:data,
		  url:'queryrealmonitorgrid.biz?bhId=0',
		  //
		  singleSelect:true,
		  rownumbers:true,
		  collapsible:true,
		  fitColumns:true,
		  pagination:true,
		  columns:[[
		            {field:'monitorId',title:'监控数据ID',hidden:true},
		            {field:'mdRecordTime',title:'记录时间',width:80,formatter:formatMdRecordTime},
		            {field:'clCode',title:'通道编号',width:80},
		            {field:'mdDisplaceAlert',title:'位移量',width:80},		            
		            {field:'bhName',title:'钻孔名称',width:80},
		            {field:'dvCode',title:'设备编号',width:80},
		            {field:'dvName',title:'设备名称',width:80},
		            {field:'dvSimCode',title:'SIM卡号',width:80},
		            {field:'nkName',title:'监测网名称',width:80},
		            {field:'nkCode',title:'监测网编号',width:80,fixed:true}		            
		            ]]
	  });
}
function formatMdRecordTime(value,row,index){
	  return formatTimestemp(row.mdRecordTime);
}
</script>
</html>
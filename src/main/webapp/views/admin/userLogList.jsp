<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>13.用户日志</title>
</head>
<%@include file="../common/Common.jsp" %>
<body class="easyui-layout">
<div data-options="region:'north',title:'',split:true" style="height:40px;">
       	<table>
  			<tr>
				<td>
					&nbsp;用户:<input id="ur" class="easyui-combobox" name="ur"
    					data-options="valueField:'userId',textField:'userShowName',
    					onSelect: function(rec){selected(rec);},
    					url:'queryalluser.biz'">
					&nbsp;时段：<input class="easyui-datetimebox" name="startTime" id="startTime"
        				data-options="showSeconds:true" style="width:150px"/>
        			-
        				<input class="easyui-datetimebox" name="endTime" id="endTime"
        				data-options="showSeconds:true" style="width:150px"/>	
				</td>
				<td>
					<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				</td>
  			</tr>
  		</table>
</div>
    <div data-options="region:'south',title:'',split:true" style="height:50px;">
    bottom condition
    </div>
<div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
	<div>
		<table id="userLogListGrid"></table>
	</div>   	
</div>
</body>
<script type="text/javascript">
	var userId=null;
	$(function(){
		$('#btn').bind('click',function(){
			queryData();
		});
	  	beforePageLoad();
	});
	function beforePageLoad(){
		createUserLogListGrid();
	}
	function createUserLogListGrid(){
		$('#userLogListGrid').datagrid({
			  url:'queryuserlog.biz',
			  singleSelect:true,
			  rownumbers:true,
			  collapsible:true,
			  fitColumns:true,
			  pagination:true,
			  columns:[[
			            {field:'userId',title:'人员ID',hidden:true},
			            {field:'userName',title:'账号',width:100,align:'center',fixed:true},
			            {field:'userShowName',title:'姓名',width:100,align:'center',fixed:true},
			            {field:'operTime',title:'操作时间',width:200,align:'center',fixed:true,formatter:formatOperTime},
			            {field:'operType',title:'操作类型',width:70,align:'center',fixed:true},		            			            
			            {field:'operDetail',title:'操作详情',width:80}
			            ]],
			   toolbar:[]
		  });
	}
	function formatOperTime(value,row,index){
		  return formatTimestemp(row.operTime);
	}
	//
	function queryData(){
			//debugger;
			var startTimeData=$('#startTime').datebox('getValue');
			var endTimeData=$('#endTime').datebox('getValue');
			var params={userId:userId,startTimeData:startTimeData,endTimeData:endTimeData};
			$('#userLogListGrid').datagrid('load', params);

	}
	function selected(rec){
		userId=rec.userId;
	}
</script>
</html>
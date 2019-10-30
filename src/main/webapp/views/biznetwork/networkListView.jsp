<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>4.监测网数据</title>
</head>
	<%@include file="../common/Common.jsp" %>
<body>
<div>
<table id="networkListGrid"></table>
</div>
<div id="addNK"></div>
</body>
  <script type="text/javascript">
  <!--
  var netType='';
  $(function(){
	  beforePageLoad();
  });
  function beforePageLoad(){
	  //监测网类型
	  var url="getdicitems.biz";
	  $.post(url,{dicttypeid:'DIC_NETWORK_TYPE'},function(data){
		  //debugger;
		  netType=data;
		  createNetworkListGrid();
	  });
		  
  }
  function createNetworkListGrid(){
	  $('#networkListGrid').datagrid({
		  url:'querynetwork.biz',
		  singleSelect:true,
		  rownumbers:true,
		  collapsible:true,
		  fitColumns:true,
		  pagination:true,
		  columns:[[
		            {field:'nkId',title:'监测网ID',hidden:true},
		            {field:'nkStatus',title:'状态',width:60,align:'center',fixed:true,formatter:formatNkStatus},
		            {field:'nkName',title:'监测网名称',width:80,formatter:formatNkName},
		            {field:'nkCode',title:'监测网编号',width:80},
		            {field:'nkType',title:'监测网类型',width:70,align:'center',fixed:true,formatter:formatNkType},		            
		            {field:'nkStartDate',title:'监测网启用时间',width:80,formatter:formatNkStartDate},
		            {field:'nkContact',title:'联系人',width:80},
		            {field:'nkTel',title:'联系电话',width:80},
		            {field:'_operate',width:80,title:'桩点',align:'center',formatter:formatOper}
		            ]]
	  });	  
  }
  //formatter
  function formatNkType(value,row,index){
	  var val="";
	  if(row.nkType){		  
		  $.each(netType,function(index,arrayChild){
			  //debugger;
			  if(row.nkType==arrayChild.dictCode){
				  val=arrayChild.dictName;
			  }
		  });		  
	  }
	  return val;
  }
  function formatNkStatus(value,row,index){
	  debugger;
	  if(row.nkStatus){
		  if(row.nkStatus==1){
			  return "<img src='./resources/frame/images/ok.gif' />";
		  }else{
			  return "<img src='./resources/frame/images/no.gif' />";
		  }
	  }
  }
  function formatNkStartDate(value,row,index){
	  return formatDate(row.nkStartDate);
  }
  function formatNkName(value,row,index){
	  return '<a href="#" onclick="viewNetwork('+index+')">'+row.nkName+'</a>'; 
  }
  function viewNetwork(index){
	   $('#networkListGrid').datagrid('selectRow',index);// 关键在这里  
	   var row = $('#networkListGrid').datagrid('getSelected');
		  if(row==null){
			  return;
		  }
		  $('#addNK').dialog({
			    title: '查看网络',
			    width: 650,
			    height: 350,
			    closed: false,
			    cache: false,
			    href: 'networkadd.biz',
			    modal: true,
			    buttons:[{
			    	text:'关闭',
			    	handler:function(){
			    		$('#addNK').dialog('close');
			    	}
			    }],
		    onLoad:function(){
		    	$('#networkAddForm').form('clear');
		    	$('#province').combobox({onSelect:function(param){
		    		var dictCode=param.dictCode;
		    		var url='getdictreeitems.biz?dictTypeId=DIC_REGION&&bizDicItemPid='+dictCode;
		    		$('#city').combobox('reload', url);
		    	}});
		    	$.post('getnetwork.biz',{nkId:row.nkId},function(data){
		    		if(data.nkStartDate){
		    			data.nkStartDate=formatDate(new Date(data.nkStartDate));
			    	}
		    		$('#networkAddForm').form('load',data);
		    		//设置数据加密标记
		    		if(data.nkDataEncryption){
		    			$("#nkDataEncryptiony").prop('checked',true);
		    		}else{
		    			$("#nkDataEncryptionn").prop('checked',true);
		    		}
		    		//省市区
		    		var prv=data.province;
		    		if(prv){
		    			var url='getdictreeitems.biz?dictTypeId=DIC_REGION&&bizDicItemPid='+prv;
			    		$('#city').combobox('reload', url);
		    		}
		    		$("#nkName").prop("readonly", true);
		    		$("#nkCode").prop("readonly", true); 
		    		$("#nkType").combobox("readonly", true); 
		    		$("#nkStartDate").datebox("readonly", true); 
		    		$("#nkPrimaryIp").prop("readonly", true); 
		    		$("#nkBackupIp").prop("readonly", true); 
		    		$("#nkPort").numberbox("readonly", true); 
		    		$("#nkBackupPort").numberbox("readonly", true); 
		    		$("#nkDataUploadInv").numberbox("readonly", true); 
		    		$("#nkDataEncryptiony").prop('disabled',true);
		    		$("#nkDataEncryptionn").prop('disabled',true);
		    		$("#addr").prop("readonly", true); 
		    		$("#province").combobox("readonly", true); 
		    		$("#city").combobox("readonly", true); 
		    		$("#region").combobox("readonly", true); 
		    		$("#nkContact").prop("readonly", true); 
		    		$("#nkTel").prop("readonly", true); 
		    		$("#nkNote").textbox("readonly", true); 
		    	});
		    }
		  });
  }

  function formatOper(value,row,index){
	  return '<a href="#" onclick="viewBorehole('+index+')">查看</a>'; 
  }
  function viewBorehole(index){
	   $('#networkListGrid').datagrid('selectRow',index);// 关键在这里  
	   var row = $('#networkListGrid').datagrid('getSelected');
	   var url='boreholeview.biz?nkId='+row.nkId;
	   parent.switchMainPage(url);
  }
  -->
  </script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>8.监测网编辑</title>
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
					{field:'ck',checkbox:true,width:2},
		            {field:'nkId',title:'监测网ID',hidden:true},
		            {field:'nkStatus',title:'状态',width:60,align:'center',fixed:true,formatter:formatNkStatus},
		            {field:'nkName',title:'监测网名称',width:80},
		            {field:'nkCode',title:'监测网编号',width:80},
		            {field:'nkType',title:'监测网类型',width:70,align:'center',fixed:true,formatter:formatNkType},		            
		            {field:'nkStartDate',title:'监测网启用时间',width:80,formatter:formatNkStartDate},
		            {field:'nkContact',title:'联系人',width:80},
		            {field:'nkTel',title:'联系电话',width:80},
		            {field:'_operate',width:80,title:'编辑桩点',align:'center',formatter:formatOper}
		            ]],
		   toolbar:[
		            {
		            	iconCls:'icon-add',
		            	text:'新增',
		            	handler:function(){addNetwork();}
		            },'-',
		            {
		            	iconCls:'icon-edit',
		            	text:'编辑',
		            	handler:function(){editNetwork();}
		            },'-',{
		            	iconCls:'icon-remove',
		            	text:'删除',
		            	handler:function(){deleteNetwork();}
		            },'-',{
		            	iconCls:'icon-ok',
		            	text:'启用',
		            	handler:function(){enableNetwork();}
		            },'-',{
		            	iconCls:'icon-no',
		            	text:'停用',
		            	handler:function(){disableNetwork();}
		            }]
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
  //添加网络
  function addNetwork(){
	  $('#addNK').dialog({
		    title: '添加网络',
		    width: 650,
		    height: 350,
		    closed: false,
		    cache: false,
		    href: 'networkadd.biz',
		    modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls: 'icon-save', 
		    	handler:function(){
		    		$('#networkAddForm').form('submit',{
		    			url:'addnetwork.biz?type=add',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){
		    			    	 $('#networkListGrid').datagrid('reload');
		    			    	 $('#addNK').dialog('close');  
		    			     }
		    			     else{
		    			    	 alert(data.message)
		    			    	 $('#addNK').dialog('close'); 
		    			     }		    			     
		    			},
		    			onSubmit:function(){
		                     return $(this).form('enableValidation').form('validate');
		                }
		    		});
				}
		    },{
		    	text:'清除',
		    	handler:function(){
		    		$('#networkAddForm').form('clear');
		    	}
		    }],
	    onLoad:function(){
	    	$('#networkAddForm').form('clear');
	    	$('#province').combobox({onSelect:function(param){
	    		var dictCode=param.dictCode;
	    		var url='getdictreeitems.biz?dictTypeId=DIC_REGION&&bizDicItemPid='+dictCode;
	    		$('#city').combobox('reload', url);
	    	}});
	    	//设置数据加密标记,默认不加密
	    	$("#nkDataEncryptionn").prop('checked',true);
	    }
	  });	  
  }
  //编辑网络
  function editNetwork(){
	  var row=$('#networkListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $('#addNK').dialog({
		    title: '编辑网络',
		    width: 650,
		    height: 350,
		    closed: false,
		    cache: false,
		    href: 'networkadd.biz',
		    modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls: 'icon-save', 
		    	handler:function(){
		    		$('#networkAddForm').form('submit',{
		    			url:'addnetwork.biz?type=edit',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){
		    			    	 $('#networkListGrid').datagrid('reload');
		    			    	 $('#addNK').dialog('close');  
		    			     }
		    			     else{
		    			    	 alert(data.message);
		    			    	 $('#addNK').dialog('close'); 
		    			     }		    			     
		    			},
		    			onSubmit:function(params){
		    				debugger;
		                    return $(this).form('enableValidation').form('validate');
		                }
		    		});
				}
		    },{
		    	text:'清除',
		    	handler:function(){
		    		$('#networkAddForm').form('clear');
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
	    	});
	    }
	  });
  }
  //删除网络
  function deleteNetwork(){
	  var row=$('#networkListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $.messager.confirm('Confirm', '确定要删除?', function(r){
		  if (r){
			  var jsonStr={'nkId':row.nkId,'type':'delete'};
			  $.post('addnetwork.biz',jsonStr,function(data){
					 var rslt=$.parseJSON(data);
				     if (!rslt.success){
				    	 alert(rslt.message);
				     }
				     $('#networkListGrid').datagrid('reload');
			  });
		  }
	  });
  }
  //启用
  function enableNetwork(){
	  var row=$('#networkListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  if(row.nkStatus!=1){
		  var jsonStr={'nkId':row.nkId,'nkStatus':1}; 
		  $.post('updatenkntatus.biz',jsonStr,function(data){
				 var rslt=$.parseJSON(data);
			     if (!rslt.success){
			    	 alert(rslt.message);
			     }
			     $('#networkListGrid').datagrid('reload');
		  });
	  }
  }
  //停用
  function disableNetwork(){
	  var row=$('#networkListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  if(row.nkStatus!=2){
		  var jsonStr={'nkId':row.nkId,'nkStatus':2}; 
		  $.post('updatenkntatus.biz',jsonStr,function(data){
				 var rslt=$.parseJSON(data);
			     if (!rslt.success){
			    	 alert(rslt.message);
			     }
			     $('#networkListGrid').datagrid('reload');
		  });		  
	  }
  }
  function formatOper(value,row,index){
	  return '<a href="#" onclick="editBorehole('+index+')">编辑桩点</a>'; 
  }
  function editBorehole(index){
	   $('#networkListGrid').datagrid('selectRow',index);// 关键在这里  
	   var row = $('#networkListGrid').datagrid('getSelected');
	   var url='boreholemanage.do?nkId='+row.nkId;
	   parent.switchMainPage(url);
  }
  -->
  </script>
</html>
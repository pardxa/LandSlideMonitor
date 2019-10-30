<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@include file="../common/Common.jsp" %>
<body>
<div>
<table id="boreholeListGrid"></table>
</div>
<div id="addBH"></div>
<div id="addFileBH"></div>
</body>
<script type="text/javascript">
 <!--
 var nkId=${nkId};
 $(function(){
	 beforePageLoad();
 });
 function beforePageLoad(){
	 createBoreholeListGrid();
 }
 function createBoreholeListGrid(){
	  $('#boreholeListGrid').datagrid({
		  url:'queryborehole.biz?nkId='+nkId,
		  singleSelect:true,
		  rownumbers:true,
		  collapsible:true,
		  fitColumns:true,
		  pagination:true,
		  columns:[[
					{field:'ck',checkbox:true,width:2},
		            {field:'bhId',title:'钻孔ID',hidden:true},		            
		            {field:'bhName',title:'桩点名称',width:80},
		            {field:'dvCode',title:'设备编号',width:80},
		            {field:'dvName',title:'设备名称',width:80,align:'center'},
		            {field:'bhContact',title:'联系人',width:80},
		            {field:'bhTel',title:'联系电话',width:80},		            
		            {field:'dvSimCode',title:'SIM卡号',width:80,align:'center',fixed:true},
		            {field:'_operate',width:80,title:'现场照片上传',align:'center',formatter:formatOper}
		            ]],
		   toolbar:[
		            {
		            	iconCls:'icon-add',
		            	text:'新增',
		            	handler:function(){addBorehole();}
		            },'-',
		            {
		            	iconCls:'icon-edit',
		            	text:'编辑',
		            	handler:function(){editBorehole();}
		            },'-',{
		            	iconCls:'icon-remove',
		            	text:'删除',
		            	handler:function(){deleteBorehole();}
		            }]
	  });		 
 }
 function addBorehole(){
	  $('#addBH').dialog({
		    title: '添加桩点',
		    width: 650,
		    height: 350,
		    closed: false,
		    cache: false,
		    href: 'boreholeadd.biz',
		    modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls: 'icon-save', 
		    	handler:function(){
		    		$('#boreholeAddForm').form('submit',{
		    			url:'addborehole.biz?type=add',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){
		    			    	 $('#boreholeListGrid').datagrid('reload');
		    			    	 $('#addBH').dialog('close');  
		    			     }
		    			     else{
		    			    	 alert(data.message)
		    			    	 $('#addBH').dialog('close'); 
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
		    		$('#boreholeAddForm').form('clear');
		    		$('#nkId').val(nkId);
		    	}
		    }],
	    onLoad:function(){
	    	$('#boreholeAddForm').form('clear');
	    	$('#nkId').val(nkId);
	    }
	  });
 };
 function editBorehole(){
	  var row=$('#boreholeListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $('#addBH').dialog({
		    title: '编辑桩点',
		    width: 650,
		    height: 350,
		    closed: false,
		    cache: false,
		    href: 'boreholeadd.biz',
		    modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls: 'icon-save', 
		    	handler:function(){
		    		$('#boreholeAddForm').form('submit',{
		    			url:'addborehole.biz?type=edit',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){
		    			    	 $('#boreholeListGrid').datagrid('reload');
		    			    	 $('#addBH').dialog('close');  
		    			     }
		    			     else{
		    			    	 alert(data.message)
		    			    	 $('#addBH').dialog('close'); 
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
		    		$('#boreholeAddForm').form('clear');
		    		$('#nkId').val(nkId);
		    	}
		    }],
	    onLoad:function(){
	    	$('#boreholeAddForm').form('clear');
	    	$.post('getborehole.biz',{bhId:row.bhId},function(data){
	    		$('#nkId').val(nkId);
	    		$('#boreholeAddForm').form('load',data);
	    	});
	    	
	    }
	  });
 };
 function deleteBorehole(){
	  var row=$('#boreholeListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $.messager.confirm('Confirm', '确定要删除?', function(r){
		  if (r){
			  var jsonStr={'bhId':row.bhId,'type':'delete'};
			  $.post('addborehole.biz',jsonStr,function(data){
					 var rslt=$.parseJSON(data);
				     if (!rslt.success){
				    	 alert(rslt.message);
				     }
				     $('#boreholeAddForm').datagrid('reload');
			  });
		  }
	  });
 };
 function formatOper(value,row,index){
	 return '<a href="#" onclick="addBhPhoto('+index+')">添加</a>';
 }
 function addBhPhoto(index){
	 $('#boreholeListGrid').datagrid('selectRow',index);// 关键在这里  
	 var row = $('#boreholeListGrid').datagrid('getSelected');
	 	  $('#addFileBH').dialog({
		    title: '现场照片上传',
		    width: 350,
		    height: 200,
		    closed: false,
		    cache: false,
		    href: 'boreholefileadd.biz',
		    modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls: 'icon-save', 
		    	handler:function(){
		    		$('#boreholeFileAddForm').form('submit',{
		    			url:'uploadboreholefile.biz',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){		    			    	  
		    			     }
		    			     else if(data.height){
		    			    	 var ht=data.height;
		    			    	 var wd=data.width;
		    			    	 alert("上传失败，图片大小应是："+wd+"x"+ht);
		    			     }else{
		    			    	 alert(data.message);		    			    	
		    			     }
		    			     $('#addFileBH').dialog('close');
		    			},
		    			onSubmit:function(){
		                     //return $(this).form('enableValidation').form('validate');
		                }
		    		});
				}
		    },{
		    	text:'清除',
		    	handler:function(){
		    		$('#boreholeFileAddForm').form('clear');
		    		$('#bhId').val(row.bhId);
		    	}
		    }],
	    onLoad:function(){
	    	$('#boreholeFileAddForm').form('clear');
	    	$('#bhId').val(row.bhId);
	    }
	  });
	 //
 }
 //-->
</script>
</html>
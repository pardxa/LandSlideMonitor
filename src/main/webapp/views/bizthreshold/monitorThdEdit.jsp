<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预警阀值设定</title>
</head>
<%@include file="../common/Common.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/frame/datagrid-detailview.js"></script>
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
				</td>
				<td>
					<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				</td>
  			</tr>
  		</table>
       	</form>
       </div><!--end of dataSearchToolbar -->
       <table id="boreholeListGrid" class="easyui-datagrid" style="width:100%;height:400px"
            url="querynwborehole.biz" 
            singleSelect="true" fitColumns="true" collapsible="true"  pagination="true">
        <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true,width:2"></th>
                <th data-options="field:'bhId',title:'钻孔ID',hidden:true"></th>
                <th data-options="field:'nkName',title:'监测网名称',width:80"></th>
                <th data-options="field:'bhName',title:'桩点名称',width:80"></th>
				<th data-options="field:'dvCode',title:'设备编号',width:80"></th>
				<th data-options="field:'dvName',title:'设备名称',width:80,align:'center'"></th>
				<th data-options="field:'dvSimCode',title:'SIM卡号',width:80,align:'center',fixed:true"></th>
            </tr>
        </thead>
    	</table>
    	<div id="addThd"></div>
</body>
<script type="text/javascript">
$(function(){
	$('#btn').bind('click',function(){
		queryData();
	});
	$('#boreholeListGrid').datagrid({
		view:detailview,
		detailFormatter:function(index,row){
            return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
        onExpandRow: function(index,row){
        	var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
            ddv.datagrid({
                url:'thdQuery.biz?bhId='+row.bhId,
                fitColumns:true,
                singleSelect:true,
                rownumbers:true,
                loadMsg:'',
                height:'auto',
                columns:[[
                    {field:'thdId',title:'Order ID',hidden:true},
                    {field:'bhId',title:'Order ID',hidden:true},
                    {field:'clCode',title:'道',width:100,align:'center'},
                    {field:'thd',title:'阀值',width:100,align:'left'}
                ]],
                onResize:function(){
                    $('#boreholeListGrid').datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){
                    setTimeout(function(){
                        $('#boreholeListGrid').datagrid('fixDetailRowHeight',index);
                    },0);
                }
            });
        },
		toolbar:[
		            {
		            	iconCls:'icon-add',
		            	text:'新增阀值',
		            	handler:function(){addThd();}
		            },'-',
		            {
		            	iconCls:'icon-edit',
		            	text:'编辑阀值',
		            	handler:function(){editThd();}
		            },'-',{
		            	iconCls:'icon-remove',
		            	text:'删除阀值',
		            	handler:function(){deleteThd();}
		            }]
	});
});
function addThd(){
	  var row=$('#boreholeListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
  	$.post("thdQuery.biz",{'bhId':row.bhId},function(o){
		var data = eval('(' + o + ')');
		if(data.rows!=undefined&&data.rows!=null){
			var rows=data.rows;
			if(rows.length==0){
				//
				  $('#addThd').dialog({
					    title: '添加阀值',
						width: 600,
						height: 150,
						closed: false,
						cache: false,
						href: 'addThd.biz',
						modal: true,
						buttons:[{
							text:'保存',
							iconCls: 'icon-save', 
							handler:function(){
								$('#thdAddForm').form('submit',{
									url:'thdAdd.biz?type=add',
									success: function(data){
										//debugger;
		    				 			var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    							if (data.success){
		    								$('#addThd').dialog('close');  
		    								}
		    							else{
		    								alert(data.message)
		    			    	 			$('#addThd').dialog('close'); 
		    								}		    			     
		    							}
								});

								}
						},{
							text:'清除',
							handler:function(){
								$('#thdAddForm').form('clear');
								}
						}],
						onLoad:function(){
							$('#thdAddForm').form('clear');
							$('#bhId').val(row.bhId);
							}
					});				
				//
			}    			
		}
	});
	
}
function editThd(){
	  var row=$('#boreholeListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $.post("thdQuery.biz",{'bhId':row.bhId},function(o){
	    		var data = eval('(' + o + ')');
	    		if(data.rows!=undefined&&data.rows!=null){
	    			var rows=data.rows;
	    			if(rows.length>0){
	    			//
	    			$('#addThd').dialog({
		    			title: '编辑阀值',
		    			width: 600,
		    			height: 150,
		    			closed: false,
		    			cache: false,
		    			href: 'addThd.biz',
		    			modal: true,
		    			buttons:[{
		    				text:'保存',
		    				iconCls: 'icon-save', 
		    				handler:function(){
		    					$('#thdAddForm').form('submit',{
		    						url:'thdAdd.biz?type=update',
		    						success: function(data){
		    							//debugger;
		    				 			var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    							if (data.success){
		    								$('#addThd').dialog('close');
		    								$('#boreholeListGrid').datagrid('reload');		    			    	 
		    								}
		    							else{
		    								alert(data.message)
		    			    	 			$('#addThd').dialog('close'); 
		    								}		    			     
		    							}
		    					});

		    					}
		    			},{
		    				text:'清除',
		    				handler:function(){
		    					$('#thdAddForm').form('clear');
		    					}
		    			}],
		    			onLoad:function(){
		    				$('#thdAddForm').form('clear');
		    				$('#bhId').val(row.bhId);
		    				$.post("thdQuery.biz",{'bhId':row.bhId},function(o){
		    					var data = eval('(' + o + ')');
		    					if(data.rows!=undefined&&data.rows!=null){
		    						var rows=data.rows;
		    						for(var idx=0;idx<rows.length;idx++){
		    							var item=rows[idx];
		    							var num=idx+1;
		    							$('#clCode'+num).val(item.clCode);
		    							$('#thdId'+num).val(item.thdId);
		    							$('#C'+num).val(item.thd);
		    							}	    			
		    						}
		    					});
		    				}
	    			});
	    			//
	    			}    			
	    		}
	  });


}
function deleteThd(){
	  var row=$('#boreholeListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $.messager.confirm('Confirm', '确定要删除?', function(r){
			if (r){
				  $.post("thdDel.biz",{'bhId':row.bhId},function(o){
					  var data = eval('(' + o + ')');
					  if(data.success){
						  $('#boreholeListGrid').datagrid('reload');
						  alert('删除成功!');
					  }
				  });
			}
		});
}
function queryData(){
	var nkId=$('#nk').combobox('getValue');
	var bhId=$('#bh').combobox('getValue');
	var params=null;
	//debugger;
	params={bhId:bhId,nkId:nkId};
	$('#boreholeListGrid').datagrid('load',params);
}
function loadBorehole(rec){
	var nkId=rec.nkId;
	var url="comboqueryboreholeslist.biz?nkId="+nkId;
	$('#bh').combobox('reload', url);
}
</script>
</html>
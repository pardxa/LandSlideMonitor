<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<style>
  body{background-color:lightgray;}
</style>
	<%@include file="../common/Common.jsp" %>
<body>
<div id="tt" class="easyui-tabs" style="fit:true;">
    <div title="用户管理" style="padding:20px;">
       <!-- userSearchToolbar -->
       <div class="easyui-panel">
       	<form id="userSearch" method="post">
       		<table>
  			<tr>
				<td>
					<input class="easyui-textbox" style="width:300px">
				</td>
				<td>
					<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				</td>
				<td></td>
  			</tr>
  			</table>
       	</form>
       </div>
       <!-- userListGrid -->
         <table id="userListGrid"></table>              
    </div><!--end of div title="用户管理" -->
    <div title="角色管理" style="padding:20px;">
        <table id="roleListGrid"></table>
    </div><!--end of div title="角色管理" -->
</div>
<div id="addUser"></div>
<div id="addRole"></div>
<div id='userRoleDlg' class="easyui-dialog" 
			title="所属角色" 
			style="width:400px;height:200px;padding:10px"
            data-options="
            closed:true,
            buttons: [{
                    text:'保存',
                    iconCls:'icon-ok',
                    handler:function(){
                        alert('ok');
                    }
                },{
                    text:'取消',
                    handler:function(){
                        alert('cancel');;
                    }
                }]">
         <table id="uRoleListGrid"></table>       
</div><!-- end of userRoleDlg -->
<div id='roleResourceDlg' class="easyui-dialog" 
			title="挂接资源" 
			style="width:400px;height:200px;padding:10px"
            data-options="
            closed:true,
            buttons: [{
                    text:'保存',
                    iconCls:'icon-ok',
                    handler:function(){
                        alert('ok');
                    }
                },{
                    text:'取消',
                    handler:function(){
                        alert('cancel');
                    }
                }]">
         <table id="uResourceListGrid"></table>       
</div><!-- end of roleResourceDlg -->
</body>

  <script type="text/javascript">
  <!--
  $(function(){
	  createUserListGrid();
	  createRoleListGrid();
  });

  /**
   ** 1.用户列表
   **/
  //1.1.创建用户列表 
  function createUserListGrid(){
	  $('#userListGrid').datagrid({
		  //data:data,
		  url:'queryUser.biz',
		  singleSelect:true,
		  rownumbers:true,
		  collapsible:true,
		  fitColumns:true,
		  pagination:true,
		  columns:[[
					{field:'ck',checkbox:true,width:2},
		            {field:'userId',title:'人员ID',hidden:true},
		            {field:'userInfoId',title:'用户ID',hidden:true},
		            {field:'userName',title:'账号',width:80},
		            {field:'userShowName',title:'姓名',width:80},
		            {field:'isEnabled',title:'是否可用',width:60,align:'center',fixed:true,formatter:formatIsEnabled},
		            {field:'isActNonLocked',title:'未锁定',width:80,align:'center',fixed:true,formatter:formatIsActNonLocked},
		            {field:'actExpireDate',title:'账户到期日',width:80,formatter:formatActExpireDate},
		            {field:'credentialsExpireDate',title:'证书到期日',width:80,formatter:formatCredentialsExpireDate},
		            {field:'userCompany',title:'工作单位',width:80},
		            {field:'userTel',title:'联系电话',width:80,fixed:true}
		            ]],
		   toolbar:[
		            {
		            	iconCls:'icon-add',
		            	text:'新增',
		            	handler:function(){addUser();}
		            },'-',
		            {
		            	iconCls:'icon-edit',
		            	text:'编辑',
		            	handler:function(){editUser();}
		            },'-',{
		            	iconCls:'icon-remove',
		            	text:'删除',
		            	handler:function(){deleteUser();}
		            },'-',{
		            	iconCls:'icon-sum',
		            	text:'用户所属角色',
		            	handler:function(){modifyUserRole();}
		            }]
	  });
  }
  function formatIsEnabled(value,row,index){
	  if(row.enabled){
		  return '是';
	  }
	  return '否';
  }
  function formatIsActNonLocked(value,row,index){
	  if(row.actNonLocked){
		  return '是';
	  }
	  return '否';
  }
  function formatActExpireDate(value,row,index){
	  return formatDate(row.actExpireDate);
  }
  function formatCredentialsExpireDate(value,row,index){
	  return formatDate(row.credentialsExpireDate);
  }
  //1.2.添加用户
  function addUser(){
	  $('#addUser').dialog({
		    title: '添加用户',
		    width: 600,
		    height: 230,
		    closed: false,
		    cache: false,
		    href: 'useradd.biz',
		    modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls: 'icon-save', 
		    	handler:function(){
		    		$('#userAddForm').form('submit',{
		    			url:'adduser.biz?type=add',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){
		    			    	 $('#addUser').dialog('close');  
		    			     }
		    			     else{
		    			    	 alert(data.message)
		    			    	 $('#addUser').dialog('close'); 
		    			     }		    			     
		    			}
		    		});

				}
		    },{
		    	text:'清除',
		    	handler:function(){
		    		$('#userAddForm').form('clear');
		    	}
		    }],
	    onLoad:function(){
	    	$('#userAddForm').form('clear');
	    	$('#isEnabled').combobox('setValue',1);
	    	$('#isActNonLocked').combobox('setValue',1);
	    }
	  });
  }
  //1.3.编辑用户
  function editUser(){
	  var row=$('#userListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $('#addUser').dialog({
		    title: '编辑用户',
		    width: 600,
		    height: 230,
		    closed: false,
		    cache: false,
		    href: 'useradd.biz',
		    modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls: 'icon-save', 
		    	handler:function(){
		    		$('#userAddForm').form('submit',{
		    			url:'adduser.biz?type=edit',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){
		    			    	 $('#addUser').dialog('close');  
		    			     }
		    			     else{
		    			    	 alert(data.message);
		    			    	 $('#addUser').dialog('close'); 
		    			     }		    			     
		    			}
		    		});

				}
		    },{
		    	text:'清除',
		    	handler:function(){
		    		$('#userAddForm').form('clear');
		    	}
		    }],
		    onLoad:function(){
		    	if(row.actExpireDate){
		    		row.actExpireDate=formatDate(new Date(row.actExpireDate));
		    	}
		    	if(row.credentialsExpireDate){
		    		row.credentialsExpireDate=formatDate(new Date(row.credentialsExpireDate));
		    	}
		    	$('#userAddForm').form('load',row);
		    }
	  });	  
	  
  }
  //1.4.删除用户
  function deleteUser(){
	  var row=$('#userListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $.messager.confirm('Confirm', '确定要删除?', function(r){
			if (r){
				  var jsonStr={'userId':row.userId,'type':'delete'};
				  $.post('adduser.biz',jsonStr,function(data){
						 var rslt=$.parseJSON(data);
						 debugger;
					     if (!rslt.success){
					    	 alert(rslt.message);
					     }
				  });
			}
		});
  }
  //1.5.用户所属角色
  function modifyUserRole(){
	var row=$('#userListGrid').datagrid('getSelected');
	if(row==null){
		  return;
	}
	$('#userRoleDlg').dialog({
	    	title: '所属角色',
	    	width: 350,
	    	height: 300,
	    	top:1,
			onOpen:function(){
				  $('#uRoleListGrid').datagrid({
					  url:'queryRole.biz',
					  singleSelect:false,
					  rownumbers:true,
					  collapsible:true,
					  fitColumns:true,
					  pagination:false,
					  columns:[[
								{field:'ck',checkbox:true,width:2},
					            {field:'roleId',title:'角色ID',hidden:true},
					            {field:'roleNote',title:'备注',width:80}
					            ]],
					  onLoadSuccess:function(data){
						  $.post("queryrolesbelong.biz",{userId:row.userId},function(o){
							  var idArray=o;
							  var AllRoles=data.rows;
							  for(var idx=0;idx<AllRoles.length;idx++){
								  var r=AllRoles[idx];
								  for(var i=0;i<idArray.length;i++){
									  var selId=idArray[i];
									  if(selId==r.roleId){
										  $('#uRoleListGrid').datagrid('selectRow',idx);
									  }
								  }
							  }
							  
						  });
					  }
				  });//datagrid				 
			 },
			 buttons:[
			          {
			        	  text:'保存',
			        	  handler:function(){
			        		  var userId=row.userId;
			        		  var roleRows=$('#uRoleListGrid').datagrid('getSelections');
			        		  var roleIds=new Array();
			        		  if(roleRows.length>0){
			        			  for(var idx in roleRows){
			        				  roleIds.push(roleRows[idx].roleId);
			        			  }
				        		  if(userId){
				        			  var params={userId:userId,roleIds:roleIds};
				        			  $.post('modifyuserrole.biz',params,function(o){
				 						 var rslt=$.parseJSON(o);
									     if (!rslt.success){
									    	 alert("修改未完成！");
										 }
									     $('#userRoleDlg').dialog('close'); 
				        			  });
				        		  }			        			  
			        		  }
			        	  }
			          },{
			        	  text:'取消',
			        	  handler:function(){
			        		  $('#userRoleDlg').dialog('close'); 
			        	  }
			          }]
			 
		 });	  
	$('#userRoleDlg').dialog('open'); 
  }

  /**
   ** 2.角色
  **/
  //2.1.创建角色列表
    function createRoleListGrid(){
	  $('#roleListGrid').datagrid({
		  url:'queryRole.biz',
		  singleSelect:true,
		  rownumbers:true,
		  collapsible:true,
		  fitColumns:true,
		  pagination:true,
		  columns:[[
					{field:'ck',checkbox:true,width:2},
		            {field:'roleId',title:'角色ID',hidden:true},		           
		            {field:'roleName',title:'角色名称',width:80},
		            {field:'roleNote',title:'备注',width:80}
		            ]],
		   toolbar:[
		            {
		            	iconCls:'icon-add',
		            	text:'新增',
		            	handler:function(){addRole();}
		            },'-',
		            {
		            	iconCls:'icon-edit',
		            	text:'编辑',
		            	handler:function(){editRole();}
		            },'-',{
		            	iconCls:'icon-remove',
		            	text:'删除',
		            	handler:function(){deleteRole();}
		            },'-',{
		            	iconCls:'icon-sum',
		            	text:'角色挂接资源',
		            	handler:function(){modifyRoleResource();}
		            }]
	  });
  }
  //2.2.添加角色
  function addRole(){
	  $('#addRole').dialog({
		    title: '添加角色',
		    width: 400,
		    height: 150,
		    closed: false,
		    cache: false,
		    href: 'roleAdd.biz',
		    modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls: 'icon-save', 
		    	handler:function(){
		    		$('#roleAddForm').form('submit',{
		    			url:'addrole.biz?type=add',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){
		    			    	 $('#addRole').dialog('close');  
		    			     }
		    			     else{
		    			    	 alert(data.message)
		    			    	 $('#addRole').dialog('close'); 
		    			     }		    			     
		    			}
		    		});

				}
		    },{
		    	text:'清除',
		    	handler:function(){
		    		$('#roleAddForm').form('clear');
		    	}
		    }],
	    onLoad:function(){
	    	$('#roleAddForm').form('clear');
	    }
	  });
  }
  //2.3.编辑角色
  function editRole(){
	  var row=$('#roleListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $('#addRole').dialog({
		    title: '编辑角色',
		    width: 400,
		    height: 150,
		    closed: false,
		    cache: false,
		    href: 'roleAdd.biz',
		    modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls: 'icon-save', 
		    	handler:function(){
		    		$('#roleAddForm').form('submit',{
		    			url:'addrole.biz?type=edit',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){
		    			    	 $('#addRole').dialog('close');  
		    			     }
		    			     else{
		    			    	 alert(data.message);
		    			    	 $('#addRole').dialog('close'); 
		    			     }		    			     
		    			}
		    		});

				}
		    },{
		    	text:'清除',
		    	handler:function(){
		    		$('#roleAddForm').form('clear');
		    	}
		    }],
		    onLoad:function(){
		    	$('#roleAddForm').form('load',row);
		    }
	  });
  }
  //2.4.删除角色
  function deleteRole(){
	  var row=$('#roleListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  $.messager.confirm('Confirm', '确定要删除?', function(r){
			if (r){
				  var jsonStr={'roleId':row.roleId,'type':'delete'};
				  $.post('addrole.biz',jsonStr,function(data){
						 var rslt=$.parseJSON(data);
						 debugger;
					     if (!rslt.success){
					    	 alert(rslt.message);
					     }
				  });
			}
		});
  }
  //角色挂接资源
  function modifyRoleResource(){
	  var row=$('#roleListGrid').datagrid('getSelected');
	  if(row==null){
		  return;
	  }
	  	$('#roleResourceDlg').dialog({
	    	title: '挂接资源',
	    	width: 350,
	    	height: 430,
	    	top:1,
			onOpen:function(){
				  $('#uResourceListGrid').datagrid({
					  url:'queryResources.biz',
					  singleSelect:false,
					  rownumbers:true,
					  collapsible:true,
					  fitColumns:true,
					  pagination:false,
					  columns:[[
								{field:'ck',checkbox:true,width:2},
					            {field:'rcId',title:'资源ID',hidden:true},
					            {field:'rcName',title:'资源名称',width:80}
					            ]],
					  onLoadSuccess:function(data){
						  $.post("queryresourcesbelong.biz",{roleId:row.roleId},function(o){
							  var idArray=o;
							  var AllRources=data.rows;
							  for(var idx=0;idx<AllRources.length;idx++){
								  var r=AllRources[idx];
								  for(var i=0;i<idArray.length;i++){
									  var selId=idArray[i];
									  if(selId==r.rcId){
										  $('#uResourceListGrid').datagrid('selectRow',idx);
									  }
								  }
							  }
							  
						  });
					  }
				  });//datagrid				 
			 },
			 buttons:[
			          {
			        	  text:'保存',
			        	  handler:function(){
			        		  var roleId=row.roleId;
			        		  var rourcesRows=$('#uResourceListGrid').datagrid('getSelections');
			        		  var rourcesIds=new Array();
			        		  if(rourcesRows.length>0){
			        			  for(var idx in rourcesRows){
			        				  rourcesIds.push(rourcesRows[idx].rcId);
			        			  }
				        		  if(roleId){
				        			  var params={roleId:roleId,rourcesIds:rourcesIds};
				        			  $.post('modifyroleresource.biz',params,function(o){
				 						 var rslt=$.parseJSON(o);
									     if (!rslt.success){
									    	 alert("修改未完成！");
										 }
									     $('#roleResourceDlg').dialog('close'); 
				        			  });
				        		  }			        			  
			        		  }
			        	  }
			          },{
			        	  text:'取消',
			        	  handler:function(){
			        		  $('#roleResourceDlg').dialog('close'); 
			        	  }
			          }]
			 
		 });	  
	$('#roleResourceDlg').dialog('open'); 
  }
  //-->
  </script>
</html>
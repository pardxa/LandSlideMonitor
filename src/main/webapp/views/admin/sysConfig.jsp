<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统配置</title>
</head>
<style>
  /*body{background-color:lightgray;}*/
  /*table {
  	margin: 0 auto;
  	}*/
  th{font-size:12px}
  td{font-size:12px}
  input{width:120px}
</style>
	<%@include file="../common/Common.jsp" %>
<body class="easyui-layout">
		<div id="p" data-options="region:'west'" title="系统参数" style="width:45%;padding:10px">
            <div  style="padding:10px;vertical-align: middle">
<form id="sysConfigForm" method="post">
  <table style="width:100%;height:100%;">
  <tr>
	<th>页面默认刷新周期：</th>
	<td>
		<input style="display:none;" name="refreshFrequency" id="refreshFrequency"/> 
		<input id="yr" name="yr" value='0' class="easyui-numberspinner" style="width:60px;"
        required="required" data-options="min:0,max:100,editable:false"/>年
        <input id="mn" name="mn" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:11,editable:false"/>月
		<input id="dy" name="dy" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:31,editable:false"/>日
		<input id="hr" name="hr" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:23,editable:false"/>小时
		<input id="mi" name="mi" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:59,editable:false"/>分
		<input id="se" name="se" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:59,editable:false"/>秒
	</td>
	</tr>
	<tr>
	<th>默认实时查询起点：</th>
	<td>
		<input style="display:none;" name="realQueryStart" id="realQueryStart"/>
		<input id="syr" name="yr" value='0' class="easyui-numberspinner" style="width:60px;"
        required="required" data-options="min:0,max:100,editable:false"/>年
        <input id="smn" name="mn" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:11,editable:false"/>月
		<input id="sdy" name="dy" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:31,editable:false"/>日
		<input id="shr" name="hr" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:23,editable:false"/>小时
		<input id="smi" name="mi" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:59,editable:false"/>分
		<input id="sse" name="se" value='0' class="easyui-numberspinner" style="width:40px;"
        required="required" data-options="min:0,max:59,editable:false"/>秒
	</td>
  </tr>
  <tr>
	<th>桩点图片宽度：</th>
	<td>
		<input class="easyui-numberbox" name="bhSitePhotoW" precision="0"/>
	</td>
	</tr><tr>
	<th>桩点图片高度：</th>
	<td>
		<input class="easyui-numberbox" name="bhSitePhotoH" precision="0"/>
	</td>
  </tr>
  <tr>
  	<td>
  		        
  	</td>
  	<td>
  		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="submitForm()">保存</a>
  	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()">清除</a>
  	</td>  	
  </tr>
  </table>
</form>
</div>
        </div><!-- end of west -->
        <div data-options="region:'center'" title="系统菜单">
        	<div class="easyui-layout" data-options="fit:true">
        		<table id="treeGrid"></table>                
            </div>        	
        </div><!-- end of center -->
	<div id="mm" class="easyui-menu" style="width:120px;">
        <div onclick="append()" data-options="iconCls:'icon-add'">追加</div>
        <div onclick="newAppend()" data-options="iconCls:'icon-add'">新增</div>
        <div onclick="removeIt()" data-options="iconCls:'icon-remove'">删除</div>
    </div>
</body>
<script type="text/javascript">
var menuStatusDic=null;
var menuRcId=null;
$(function(){
	init();
});

function init(){
	$.post("getdicitems.biz",{dicttypeid:'DIC_MENU_STATUS'},function(data){
		menuStatusDic=data;
		$.post("querycomboresources.biz",{},function(rcids){
			menuRcId=rcids;
			initSysConfigForm();
			initSysConfigTreeGrid();
		});


	});	
}
function initSysConfigForm(){
	var url="querysysconfig.biz";
	$.post(url,{},function(data){
		$('#sysConfigForm').form('load',data);
		//debugger;
		setTime(data.refreshFrequency,data.realQueryStart);
	});
}
function initSysConfigTreeGrid(){
	var url="getMenu.biz";
	$('#treeGrid').treegrid({
	    url:url,
	    idField:'menuId',
	    treeField:'menuName',
	    rownumbers: true,
	    lines: true,
	    animate: true,
        collapsible: true,
        fitColumns: true,
        pagination:true,
	    columns:[[
			{field:'menuId',title:'ID',hidden:true},
			{field:'menuPid',title:'PID',hidden:true},
			{field:'menuName',title:'名称',width:180,editor:'text'},
	        {field:'menuDispName',title:'显示名称',width:180,editor:'text'},	        
	        {field:'rcId',title:'关联资源',width:180,editor:{
                type:'combobox',
                options:{
                    valueField:'rcId',
                    textField:'rcName',
                    url:'querycomboresources.biz',
                    required:true
                }},formatter:formatRcId},
            {field:'menuOrder',title:'显示顺序',width:80,editor:'numberbox',align:'center'},
	        {field:'status',title:'菜单状态',width:80,align:'center',editor:{
                type:'combobox',
                options:{
                    valueField:'dictCode',
                    textField:'dictName',
                    url:'getdicitems.biz?dicttypeid=DIC_MENU_STATUS',
                    required:true
                }},formatter:formatStatus},
	        {title:'是否叶子节点',field:'isLeaf',fixed:true,align:'center',editor:{
                type:'combobox',
                options:{
                    valueField:'dictCode',
                    textField:'dictName',
                    url:'getdicitems.biz?dicttypeid=DIC_YES_NO',
                    required:true
                }},formatter:formatIsLeaf}
	    ]],
	    onLoadSuccess:function(row, data){
	    	$('#treeGrid').treegrid('collapseAll');
	    },
	    onDblClickRow:function(row){
	    	editTreeGrid();
	    },
	    onContextMenu:function(e,row){
	    	onContextMenu(e,row);
	    }
	});	
	//按钮显示在Grid下方
	var pager = $('#treeGrid').datagrid('getPager');	// get the pager of datagrid
			pager.pagination({
				layout:[],
				displayMsg:'',
				buttons:[{
	            	iconCls:'icon-save',
	            	text:'保存',
	            	handler:function(){
	            		saveTreeGrid();
	            	}
	            },'-',
	            {
	            	iconCls:'icon-cancel',
	            	text:'取消',
	            	handler:function(){
	            		cancelTreeGrid();
	            	}
	            },'-',{
	            	iconCls:'icon-reload',
	            	text:'刷新菜单',
	            	handler:function(){
	            		top.getMenuData();
	            	}	            	
	            }]
			});
}
//TreeGrid显示ContextMenu
function onContextMenu(e,row){
	if(editingId){
		return;
	}
    e.preventDefault();
    $('#treeGrid').treegrid('select', row.menuId);
    $('#mm').menu('show',{
        left: e.pageX,
        top: e.pageY
    });
}
function append(){
	var node = $('#treeGrid').treegrid('getSelected');
	//debugger;
	if(node.leaf){
		return;
	}
    $('#treeGrid').treegrid('append',{
        parent: node.menuId,
        data: [{
        	menuId:-1,
        	menuPid:node.menuId,
        	menuName:'',
        	menuDispName:'',
        	menuOrder:0,
        	status:1,
        	isLeaf:1
        }]
    });
    $('#treeGrid').treegrid('select', -1);
    editingId = -1;
    $('#treeGrid').treegrid('beginEdit', editingId);
}
function newAppend(){
    $('#treeGrid').treegrid('append',{
        data: [{
        	menuId:-1,
        	menuPid:0,
        	menuName:'',
        	menuDispName:'',
        	menuOrder:0,
        	status:1,
        	isLeaf:1
        }]
    });
    $('#treeGrid').treegrid('select', -1);
    editingId = -1;
    $('#treeGrid').treegrid('beginEdit', editingId);	
}
function formatRcId(value,row,index){
	var rcId=row.rcId;
	if(!menuRcId){
		return'';
	}
	for(var i=0;i<menuRcId.length;i++){
		if(menuRcId[i].rcId==rcId){
			return menuRcId[i].rcName;
		}
	}
	return '';
}
function formatStatus(value,row,index){
	if(row.status==menuStatusDic[0].dictCode){
		return menuStatusDic[0].dictName;
	}else if(row.status==menuStatusDic[1].dictCode){
		return menuStatusDic[1].dictName;
	}
	return '';
}
function formatIsLeaf(value,row,index){
	  if(row.leaf){
		  return '是';
	  }
	  return '否';
}
var editingId;
function editTreeGrid(){
    if (editingId != undefined){
        $('#treeGrid').treegrid('select', editingId);
        return;
    }
    var row = $('#treeGrid').treegrid('getSelected');
    if (row){
        editingId = row.menuId;
        $('#treeGrid').treegrid('beginEdit', editingId);
    }
}
function removeIt(){
	$.messager.confirm('Confirm', '确定要删除?', function(r){
		if (r){
		    var node = $('#treeGrid').treegrid('getSelected');
		    if (node){
		    	var url="deletemenu.biz";
		    	$.post(url,{menuId:node.menuId},function(rslt){
		    		var o=$.parseJSON(rslt);
		    		if(o.success){
		    			$('#treeGrid').treegrid('remove',o.menuId );
		    		}
		    	});		        
		    }			
		}
	});

}
function cancelTreeGrid(){
    if (editingId != undefined){
        $('#treeGrid').treegrid('cancelEdit', editingId);
        editingId = undefined;
    }
}
function saveTreeGrid(){
    if (editingId != undefined){
        var t = $('#treeGrid');
        t.treegrid('endEdit', editingId);
    	var sameRows=findRowsSameLevel(editingId);
    	if(!checkMenuOrderUnique(sameRows)){
    		editingId = undefined;
    		alert('同级别的菜单项顺序号不能重复!');    		
    		return;
    	}
  		//debugger;
        editRow=t.treegrid('find',editingId);
        var url='addmenu.biz';
        if(editingId>0){
        	url='updatemenu.biz';
        }
        $.post(url,editRow,function(rslt){
        	var o=$.parseJSON(rslt);
        	if(o.success){
        		if(o.menuId){
        			var menuId=t.treegrid('refresh',o.menuId);
        		}
        	}
        	editingId = undefined;
        });
    }
}
function submitForm(){
	var url="updatesysconfig.biz";
	$('#sysConfigForm').form('submit',{
		url:url,
		onSubmit: function(){
			getTime();
		},
		success:function(o){
			var rslt=$.parseJSON(o);
		    if (rslt.success){
		    	 alert("保存成功！");
			}
		}
	});
}
function clearForm(){
	$('#sysConfigForm').form('clear');
	var initdata={refreshFrequency:0,bhSitePhotoH:0,bhSitePhotoW:0};
	$('#sysConfigForm').form('load',initdata);
}
function getTime(){
	var yr=$('#yr').numberspinner('getValue'); 
	var mn=$('#mn').numberspinner('getValue'); 
	var dy=$('#dy').numberspinner('getValue'); 
	var hr=$('#hr').numberspinner('getValue'); 
	var mi=$('#mi').numberspinner('getValue'); 
	var se=$('#se').numberspinner('getValue'); 
	var mils=parseInt(yr)*365*24*60*60*1000
	+parseInt(mn)*30*24*60*60*1000
	+parseInt(dy)*24*60*60*1000
	+parseInt(hr)*60*60*1000
	+parseInt(mi)*60*1000
	+parseInt(se)*1000;
	$("#refreshFrequency").val(mils);
	var syr=$('#syr').numberspinner('getValue'); 
	var smn=$('#smn').numberspinner('getValue'); 
	var sdy=$('#sdy').numberspinner('getValue'); 
	var shr=$('#shr').numberspinner('getValue'); 
	var smi=$('#smi').numberspinner('getValue'); 
	var sse=$('#sse').numberspinner('getValue'); 
	var smils=parseInt(syr)*365*24*60*60*1000
	+parseInt(smn)*30*24*60*60*1000
	+parseInt(sdy)*24*60*60*1000
	+parseInt(shr)*60*60*1000
	+parseInt(smi)*60*1000
	+parseInt(sse)*1000;
	$("#realQueryStart").val(smils);
}
function setTime(refreshFrequency,realQueryStart){
	//debugger;
	var seconds=parseInt(refreshFrequency)/1000;
	var yr=Math.floor(seconds/(365*24*60*60));
	seconds=seconds%(365*24*60*60);
	var mn=Math.floor(seconds/(30*24*60*60));
	seconds=seconds%(30*24*60*60);
	var dy=Math.floor(seconds/(24*60*60));
	seconds=seconds%(24*60*60);
	var hr=Math.floor(seconds/(60*60));
	seconds=seconds%(60*60);
	var mi=Math.floor(seconds/60);
	var se=seconds%60;
	$('#yr').numberspinner('setValue',yr); 
	$('#mn').numberspinner('setValue',mn); 
	$('#dy').numberspinner('setValue',dy); 
	$('#hr').numberspinner('setValue',hr); 
	$('#mi').numberspinner('setValue',mi); 
	$('#se').numberspinner('setValue',se); 
	//
	var sseconds=Math.floor(realQueryStart)/1000;
	var syr=Math.floor(sseconds/(365*24*60*60));
	sseconds=sseconds%(365*24*60*60);
	var smn=Math.floor(sseconds/(30*24*60*60));
	sseconds=sseconds%(30*24*60*60);
	var sdy=Math.floor(sseconds/(24*60*60));
	sseconds=sseconds%(24*60*60);
	var shr=Math.floor(sseconds/(60*60));
	sseconds=sseconds%(60*60);
	var smi=Math.floor(sseconds/60);
	var sse=sseconds%60;
	$('#syr').numberspinner('setValue',syr); 
	$('#smn').numberspinner('setValue',smn); 
	$('#sdy').numberspinner('setValue',sdy); 
	$('#shr').numberspinner('setValue',shr); 
	$('#smi').numberspinner('setValue',smi); 
	$('#sse').numberspinner('setValue',sse); 	
}
///
//检查唯一性
function checkMenuOrderUnique(rows){
	var result=[];
	$.each(rows,function(idx,elt){
		if($.inArray(elt.menuOrder,result)==-1){
			result.push(elt.menuOrder);
		}		
	});
	return (result.length==rows.length);
}
function findRowsSameLevel(menuId){
	var editRow=$('#treeGrid').treegrid('find',menuId);
	var allRows=$('#treeGrid').treegrid('getData');
	var rows=[];
	$.each(allRows,function(idx,elt){
		if(editRow.menuPid==elt.menuPid){
			rows.push(elt);
		}
	});
	return rows;
	
}
</script>
</html>
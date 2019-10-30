<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>滑坡数据图</title>
</head>
<%@include file="../common/Common.jsp" %>
<link href='${pageContext.request.contextPath}/resources/frame/css/SlideMonitor.css' rel="stylesheet" type="text/css">
<script type="text/javascript" src='${pageContext.request.contextPath}/resources/frame/flot/jquery.flot.min.js'></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/frame/flot/jquery.flot.symbol.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/frame/flot/jquery.flot.time.js"></script>
<body class="easyui-layout">	
	<div data-options="region:'north',title:'',split:false" style="height:35px;overflow:hidden;">
		<!--<div style="float:left;width:100%">  -->
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
					<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">显示</a>
				</td>
				<td>
					<input id="isRealtime" type="checkbox" onchange="toggleCheckbox(this)"/>实时
				</td>
				<th>刷新间隔(秒)：</th>
				<td>
					<input class="easyui-textbox" id="freqctl" data-options="readonly:'true'" style="width:100px">  						
				</td>
  			</tr>
  		</table>
  		<!-- </div> -->

	</div>
    <div data-options="region:'south',title:'',split:false" style="height:35px;overflow:hidden;">
    <table>
    	<tr>
    		<th>图例：</th>
    		<td><div id="legendholder"></div></td>
    		<td><div id="timeholder"></div></td>
    	</tr>
    </table>
    </div>
    <div data-options="region:'center',title:''" style="padding:5px;background:#eee;overflow:hidden;">
    	<div class="content" >
    		<div class="gcontainer">
    			<div id="dataGraph"></div>
    		</div>    	
    	</div>   	
    </div><!-- end of region -->
</body>
<script type="text/javascript">
	//页面刷新频率
	var freq=${freq};
	//
	var freqSec=parseInt(freq)/1000;
	var realStart=${realStart};
	//图
	var options = {
				xaxis: {
					mode: "time",
					timezone: "browser",
					timeformat:"%Y年%m月%d日 %H:%M:%S"
				},
				series: {
					lines:{
						show: true,
						//线宽
		                lineWidth: 1
					}
				},
				grid: {
					hoverable: true,
					clickable: true
				},
				legend: { 
					show: true,
					noColumns:20, //图例平铺
					container: '#legendholder' 
				}
	};
	//移动鼠标显示数值的背景正方形
	$("<div id='tooltip'></div>").css({
		position: "absolute",
		display: "none",
		"font-size":"11px",
		"text-align":"center",
		border: "1px solid #fdd",
		padding: "2px",
		"background-color": "#F0F0F0",
		opacity: 0.80
	}).appendTo("body");
	
	//HoveOver显示数值
	$('#dataGraph').bind("plothover", function (event, pos, item) {
		if (item) {
			var t = new Date(parseInt(item.datapoint[0]));
			var tStr=t.getFullYear()+'/'+(t.getMonth()+1)+'/'+t.getDate()+" "+t.getHours()+":"+t.getMinutes()+":"+t.getSeconds();
			var	y = item.datapoint[1].toFixed(2);
			$("#tooltip").html('<b>'+y+'<br>['+tStr+']</b>')
				.css({top: item.pageY+5, left: item.pageX+5})
				.fadeIn(200);
		} else {
			$("#tooltip").hide();
		}
	});
	//计时器对象
	var tr=null;
	$(function(){
		$('#freqctl').textbox('setValue',freqSec);
		$('#btn').bind('click',function(){
			search();
		});
		queryData();
	});
	//
	function search(){
		queryData();
	}
	//更新数据
	function updateData(dt){
		var timeformat="%Y年%m月%d日 %H:%M:%S";
		var timeYMD="";
		if(dt.timeformat=="day"){
			timeformat="%H:%M:%S";
			timeYMD="%Y年%m月%d日";
		}else if(dt.timeformat=="month"){
			timeformat="%d日 %H:%M:%S";
			timeYMD="%Y年%m月";				
		}else if(dt.timeformat=="year"){
			timeformat="%m月%d日 %H:%M:%S";
			timeYMD="%Y年";	
		}
		options.xaxis.timeformat=timeformat;
		plot=$.plot('#dataGraph',dt.result.data,options);
		if(timeYMD!=""){
			//debugger;
			var mit = new Date(plot.getAxes().xaxis.min);
			var mitStr=mit.getFullYear()+'/'+(mit.getMonth()+1)+'/'+mit.getDate();//+" "+mit.getHours()+":"+mit.getMinutes()+":"+mit.getSeconds();
			var mxt = new Date(plot.getAxes().xaxis.max);
			var mxtStr=mxt.getFullYear()+'/'+(mxt.getMonth()+1)+'/'+mxt.getDate();//+" "+mxt.getHours()+":"+mxt.getMinutes()+":"+mxt.getSeconds();			
			$("#timeholder").text(" 时间范围：["+mitStr+"--"+mxtStr+"]");
		}
		//
		//plot.setData(data);
		//plot.draw();
	}
	//查询
	function queryData(){
		var bhData=$('#bh').combobox('getData');
		if(bhData!=null&&bhData.length>0){
			var bhId=bhData[0].bhId;
			var params=null;
			debugger;
			if($('#isRealtime').is(':checked')){
				params={bhId:bhId,startTime:'',endTime:'',realStart:realStart};
			}else{
				var startTimeData=$('#startTime').datebox('getValue');
				var endTimeData=$('#endTime').datebox('getValue');
				if(startTimeData==''||endTimeData==''){
					alert("请选择起止日期！");
					return;
				}
				params={bhId:bhId,startTime:startTimeData,endTime:endTimeData,realStart:0};
			}

			
			var url="queryrealmonitordata.biz";
			$.post(url,params,function(o){
				debugger;
				var d=$.parseJSON(o);
				updateData(d);
			});			
		}
	}
	//设置计时器
	function setTimer(){
		queryData();
		tr=setTimeout(function(){ setTimer() }, freq);
	}
	function clearTimer(){
		clearTimeout(tr);
	}
	//
	function loadBorehole(rec){
		var nkId=rec.nkId;
		var url="comboqueryboreholeslist.biz?nkId="+nkId;
		$('#bh').combobox('reload', url);
	}
	function toggleCheckbox(element){
		if(element.checked){
			$('#startTime').datebox("clear");
			$('#startTime').datebox("readonly", true);
			$('#endTime').datebox("clear");
			$('#endTime').datebox("readonly", true);
			setTimer();
		}else{
			$('#startTime').datebox("readonly", false);
			$('#endTime').datebox("readonly", false);
			clearTimer();
		}
	}
</script>
</html>
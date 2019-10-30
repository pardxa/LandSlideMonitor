<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.landslide.data.security.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<%@include file="../common/Common.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/frame/css/mainFrame.css" />
<body>
<!--//最外层使用一个DIV -->
 <div id="wraper" class="wrap">
	<!--//使用header,container,footer将页面分开 -->
	<div id="header">
		<div class="head-in clearfix">
			<!-- //系统名称区 -->
			<div class="fl clearfix">
				<h1 class="logo"></h1>
				<h2 class="name">系统名称</h2>
			</div> 
			<!--//用户信息区-->
			<div class="user">
				<p class="tips">
					<img style="vertical-align:bottom;margin-bottom:2px;" src="${pageContext.request.contextPath}/resources/frame/images/h-head.gif" width="18" height="18" alt="" />
					<span class="font-5">
						<%
							User ur=(User)(session.getAttribute("user"));
						%>
						<strong>您好，<%=ur.getUserShowName()%>，</strong>
					</span>
					<span id="currentData" class="font-5" style="font-weight:bold;"></span>
					<span>
						<a class="set" href="#" onclick="changePassword()">修改密码</a>
						<a class="login-out" href="j_spring_security_logout" target="_top">注销</a>
					</span>
				</p>
			</div>
		</div>	
	</div><!-- //header end -->
	<div id="container">
		<!-- //包含菜单和内容 -->
		<div id="wrapper" class="wrap">
			<!--//水平菜单-->
			<div id="menu_p">
				<div id="menuBtn" class="easyui-panel" >
    			</div>
    		</div>
			<!-- //内容 -->
			<div class="submain radius" style=" height:92%;" >
				<!-- //fieldset draws a box around the related elements 圈边框 -->
				<fieldset style=" height:100%;  font-size:10; overflow:hidden;position:relative;">
			        <!-- <legend>
						<div id="nav1" class="navbar">
							<span>首页</span>
						</div>
					</legend> -->
					<div class="nui-fit" style="padding:0px;height:100%">
						<iframe id="mainframe" src="welcome.do" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
					</div>
				</fieldset>
			</div>
		</div>
	</div><!-- //container end -->
	<div id="footer">
		<p>&copy; XXX All Rights Reserved.</p><iframe id="download_iframe" style="display:none;"></iframe>
	</div>
	<div id="changePasswordDlg"></div>
 </div> 
 </body>
 <script type="text/javascript">
	$(function(){
		getMenuData();
	});
	function getMenuData(){
		var url="getMenu.biz";
		$.post(url,{},function(data){
			var Items=data;
			//如果无菜单项，判断为已logout
			if(Items==null||Items.length==0){
				switchMainPage("j_spring_security_logout");
				window.location.replace("j_spring_security_logout");
			}
			//debugger;
			$("#menuBtn").empty();
			$.each(Items,function(idx,item){
				var menuBtnStr="";
				///按照有无子项，区分是 menubutton 还是linkbutton
				if(item.children.length>0){
					///先生成子项，然后在生成menubutton。注意顺序.
					var content='<div id="mm'+item.menuId+'" style="width:150px;">';
					content+=createMenuItem(item);
					content+='</div>';
					$("#menu_p").append(content);
					$("#mm"+item.menuId).menu({
						onClick:function(item){
							///item代表一个menu div子项，其属性data-options有如下固定键值：
							///(disabled:false,href:"",id:undefined,name:undefined,onclick:null,target:,text)
							///这里将url放入name中
							switchMainPage(item.name);
						}
					});
					//
					menuBtnStr='<a href="javascript:void(0)" id="mmid'+item.menuId+'">'+item.menuDispName+'</a>';
					$("#menuBtn").append($(menuBtnStr));
					$('#mmid'+item.menuId).menubutton({
						menu:'#mm'+item.menuId,
						iconCls:'icon-edit'
						});
				}
				else{
					menuBtnStr='<a href="#" id="mmid'+item.menuId+'">'+item.menuDispName+'</a>';	
					$("#menuBtn").append($(menuBtnStr));
					$('#mmid'+item.menuId).linkbutton({
						plain:true,
						iconCls:'icon-edit'
						});
					$('#mmid'+item.menuId).bind('click',function(){
						switchMainPage(item.rcUrl);
					});
				}
			});
			
		});		
	}
	///创建菜单子项目
	function createMenuItem(parentItem){
		var content="";
		var children=parentItem.children;		
		$.each(parentItem.children,function(idx,childItem){
			if(childItem.children.length>0){
				content+='<div>'+createMenuItem(childItem)+'</div>';
			}else{
				content+='<div data-options="name:\''+childItem.rcUrl+'\'">'+childItem.menuDispName+'</div>';
			}				
		});
		return content;
	}
	///切换页面
	function switchMainPage(url){
		//$('iframe').attr('src',url);
		$('#mainframe').attr('src',url);
		//alert(url);
	}
	function changePassword(){
		$('#changePasswordDlg').dialog({
            title: '更改密码',
            width: 290,
            height: 150,
            closed: false,//关闭按钮
            cache: false,
            href: 'changpwd.biz',
            modal: true,
            buttons:[{   //位于dialog box 下方的按钮
                 text:'保存',
                 iconCls: 'icon-save',
                 handler:function(){
 		    		$('#changPasswordForm').form('submit',{
		    			url:'changepassordaction.biz',
		    			success: function(data){
		    				//debugger;
		    				 var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    			     if (data.success){
		    			    	 $('#changePasswordDlg').dialog('close');
		    			    	 alert("修改成功！");
		    			     }
		    			     else if(data.error=='badpwd'){
		    			    	 alert("原密码不匹配！");
		    			    	 return;
		    			     }
		    			     else{
		    			    	 alert(data.message)
		    			    	 $('#changePasswordDlg').dialog('close'); 
		    			     }		    			     
		    			}
		    		});
                 }
            },{
                 text:'关闭',
                 handler:function(){
                	 $('#changePasswordDlg').dialog('close');
                 }
            }]
     });
	}
 </script>
</html>
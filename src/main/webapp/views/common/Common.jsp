<% 
	String jspFramePath=request.getContextPath()+"/views/frame/";
%>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/frame/themes/default/easyui.css" />
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/frame/themes/icon.css" />
 <script type="text/javascript" src='${pageContext.request.contextPath}/resources/frame/jquery.min.js'></script>	
 <script type="text/javascript" src='${pageContext.request.contextPath}/resources/frame/jquery.easyui.min.js'></script>
 <script type="text/javascript" src='${pageContext.request.contextPath}/resources/frame/locale/easyui-lang-zh_CN.js'></script>
 <script type="text/javascript">
  <!--
  ///公用函数
  ///将millisecond日期转化为可读格式
  function formatDate(date){
	  if(date==undefined||date==null){
		  return'';
	  }
	  var dt=new Date(date);
	  var dtStr=dt.getFullYear()+"-"+(dt.getMonth()+1)+"-"+dt.getDate();
	  return dtStr;
  }	
  function formatTimestemp(date){
	  var dt=new Date(date);
	  var dateStr=formatDate(date);
	  var timeStr=dt.getHours()+":"+dt.getMinutes()+":"+dt.getSeconds();
	  return dateStr+" "+timeStr;	  
  }
  ///导出Excel
  function exportExcelFromIFrame(beanId,fileName,data){
		var frm='<form method="post" action="exportExcel.biz" target="_parent">';
		$.each(data,function(n,v){
	        frm+='<input type="hidden" name="'+n+'" value="'+v+'" />';
	    });
		frm+='<input type="hidden" name="beanId" value="'+beanId+'" />';
		frm+='<input type="hidden" name="fileName" value="'+fileName+'" />';
		frm+="</form>";
		$('#download_iframe',window.parent.document).contents().find('body').append(frm);
		$('#download_iframe',window.parent.document).contents().find('form').submit().remove();	  
  }
  //-->
  </script>
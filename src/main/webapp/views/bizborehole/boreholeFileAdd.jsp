<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div  style="padding:10px;vertical-align: middle">
<form id="boreholeFileAddForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bhId" id="bhId"/>
	<table>
		<tr>
			<th>现场照片</th>
			<td>
				<input type="file" name="bhPhoto" id="bhPhoto"/>
			</td>
		</tr>		
	</table>
</form>
</div>
</body>
</html>
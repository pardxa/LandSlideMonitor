<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="listVar" items="${bhs}"> 
	<table>
    	<tr>
			<th>桩点名称</th>
			<td>
				<c:out value="${listVar.bhName}"/>
			</td>
			<th>设备编号</th>
			<td>
				<c:out value="${listVar.dvCode}"/>
			</td>
			<td rowspan="6">
				<img src="data:image/jpeg;base64,<c:out value="${listVar.bhPhotoBase64}"/>" 
				alt="..." width='<c:out value="${listVar.bhPhotoWidth}"/>' 
				height='<c:out value="${listVar.bhPhotoHeight}"/>'>	
			</td>
		</tr>
		<tr>
			<th>设备名称</th>
			<td>
				<c:out value="${listVar.dvName}"/>
			</td>
			<th>SIM卡号</th>
			<td>
				<c:out value="${listVar.dvSimCode}"/>
			</td>
		</tr>
		<tr>
			<th>经度</th>
			<td>
				<c:out value="${listVar.bhLg}"/>
			</td>
			<th>纬度</th>
			<td>
				<c:out value="${listVar.bhLt}"/>
			</td>
		</tr>
		<tr>
			<th>位移范围</th>
			<td>
				<c:out value="${listVar.bhDisplaceRange}"/>
			</td>
			<th>初始坐标DE</th>
			<td>
				<c:out value="${listVar.bhIntCoordDe}"/>
			</td>			
		</tr>
		<tr>
			<th>初始坐标DN</th>
			<td>
				<c:out value="${listVar.bhIntCoordDn}"/>
			</td>
			<th>初始坐标DU</th>
			<td>
				<c:out value="${listVar.bhIntCoordDu}"/>
			</td>
		</tr>
		<tr>
		<th>联系人</th>
		<td>
			<c:out value="${listVar.bhContact}"/>
		</td>
		<th>联系电话</th>
		<td>
			<c:out value="${listVar.bhTel}"/>
		</td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="3">
			<c:out value="${listVar.bhNote}"/>
		</td>
	</tr>
	</table>
</c:forEach>
</body>
</html>
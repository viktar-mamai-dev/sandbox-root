<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		<!DOCTYPE>
		<html>

		<head>
			<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css' />" /> --%>
			<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>
				<tiles:insertAttribute name="title" />
			</title>
		</head>

		<body>
			<table class="container" style="border: 3px solid black; width: 100%;">
				<tr>
					<td colspan="2">
						<tiles:insertAttribute name="header" />
					</td>
				</tr>
				<tr>
					<td style="width: 200px; background-color: #FCFCFC; vertical-align: top;">
						<tiles:insertAttribute name="menu" />
					</td>
					<td>
						<tiles:insertAttribute name="body" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<tiles:insertAttribute name="footer" />
					</td>
				</tr>
			</table>
		</body>

		</html>
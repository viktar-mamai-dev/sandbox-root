<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<%@ taglib prefix="customtags" tagdir="/WEB-INF/tags" %>
			<fmt:setLocale value="${language }" />
			<fmt:setBundle basename="pagecontent" />
			<html>

			<head>
				<title>
					<fmt:message key="title.error" />
				</title>
				<link rel="stylesheet" type="text/css" href="css/form.css">
			</head>

			<body>

				<jsp:include page="../common/header.jsp" />
				<div id="error-div">
					<customtags:error />
				</div>
				<hr />
				<customtags:form-back />
				<jsp:include page="../common/footer.jsp" />

			</body>

			</html>
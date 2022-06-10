<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<fmt:setBundle basename="config" />
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			</head>

			<body>
				<%-- <jsp:forward page="jsp/user/register.jsp">
					</jsp:forward> --%>
					<c:redirect url="controller">
						<c:param name="command" value="show_mag_list"></c:param>
					</c:redirect>
			</body>

			</html>
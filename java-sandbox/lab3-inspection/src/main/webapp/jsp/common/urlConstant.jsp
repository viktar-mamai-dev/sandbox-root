<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<fmt:setBundle basename="config" />
		<html>

		<body>
			<c:url value="controller" var="loginUrl" scope="session">
				<c:param name="command" value="redirection" />
				<c:param name="page">
					<fmt:message key="page.login" />
				</c:param>
			</c:url>
			<c:url value="controller" var="registerUrl" scope="session">
				<c:param name="command" value="redirection" />
				<c:param name="page">
					<fmt:message key="page.register" />
				</c:param>
			</c:url>
			<c:url value="controller" var="magListUrl" scope="session">
				<c:param name="command" value="show_mag_list" />
			</c:url>
			<c:url value="controller" var="userItemUrl" scope="session">
				<c:param name="command" value="show_user_item" />
			</c:url>
			<c:url value="controller" var="resListUrl" scope="session">
				<c:param name="command" value="show_res_list" />
			</c:url>
			<c:url value="controller" var="addMagUrl" scope="session">
				<c:param name="command" value="new_magazine" />
			</c:url>
			<c:url value="controller" var="addSubUrl" scope="session">
				<c:param name="command" value="new_subscription" />
			</c:url>
			<c:url value="controller" var="ruLangUrl" scope="session">
				<c:param name="command" value="change_locale" />
				<c:param name="language" value="ru" />
			</c:url>
			<c:url value="controller" var="enLangUrl" scope="session">
				<c:param name="command" value="change_locale" />
				<c:param name="language" value="en" />
			</c:url>

		</body>

		</html>
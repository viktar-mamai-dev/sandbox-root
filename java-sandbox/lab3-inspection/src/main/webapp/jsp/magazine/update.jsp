<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<%@ taglib prefix="customtags" tagdir="/WEB-INF/tags" %>
			<fmt:setLocale value="${language }" />
			<fmt:setBundle basename="pagecontent" />
			<html>

			<head>
				<title>
					<fmt:message key="title.magazine.update" />
				</title>
				<link rel="stylesheet" type="text/css" href="css/form.css">
			</head>

			<body>

				<jsp:include page="../common/header.jsp" />

				<form action="controller" method="post" class="register-frm">
					<input type="hidden" name="command" value="update_magazine" /> <input type="hidden" name="mag_id"
						value="${mag.id }" />
					<h1>
						<fmt:message key="header.magazine.update" />
					</h1>
					<table>
						<tr>
							<td>
								<fmt:message key="label.magazine.title" />
							</td>
							<td><input type="text" name="title" value="${mag.title }" required="required"
									title="<fmt:message key=" error.empty" />"></td>
							<td>${titleError }</td>
						</tr>
						<tr>
							<td>
								<fmt:message key="label.magazine.annotation" />
							</td>
							<td><textarea name="annotation" required="required" title="<fmt:message key="
									error.empty" />">${mag.annotation }</textarea></td>
							<td>${annotationError }</td>
						</tr>
						<tr>
							<td>
								<fmt:message key="label.magazine.periodicity" />
							</td>
							<td>
								<customtags:select itemList="${periodList}" name="period_id" defaultId="${mag.period.id}" 
								clazz="period-select" />
							</td>
						</tr>
						<tr>
							<td><input type="submit" class="register-btn" value="<fmt:message key=" button.save" />">
							</td>
						</tr>
					</table>
				</form>

				<customtags:form-back />

				<jsp:include page="../common/footer.jsp" />
			</body>

			</html>
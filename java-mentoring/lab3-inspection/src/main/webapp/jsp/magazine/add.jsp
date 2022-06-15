<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@ taglib prefix="customtags" tagdir="/WEB-INF/tags" %>
				<fmt:setLocale value="${language }" />
				<fmt:setBundle basename="pagecontent" />
				<html>

				<head>
					<title>
						<fmt:message key="title.magazine.add" />
					</title>

					<link rel="stylesheet" type="text/css" href="css/form.css">
				</head>

				<body>
					<jsp:include page="../common/header.jsp" />

					<form action="controller" method="post" class="register-frm">
						<input type="hidden" name="command" value="add_magazine" />
						<h1>
							<fmt:message key="header.magazine.add" />
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
									<fmt:message key="label.magazine.periodicity" />${mag.period.id }
								</td>
								<td>
									<customtags:select name="period_id" clazz="period-select" itemList="${periodList }"
										defaultId="${period_id }" />
								</td>
							</tr>
							<tr>
								<td>
									<fmt:message key="label.magazine.location" />${mag.period.id }
								</td>
								<td><input type="file" name="location" value="${mag.location }" id="fileupload"></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" class="register-btn" value="<fmt:message key="
										button.save" />"></td>
							</tr>
						</table>

					</form>

					<customtags:form-back />

					<jsp:include page="../common/footer.jsp" />

				</body>

				</html>
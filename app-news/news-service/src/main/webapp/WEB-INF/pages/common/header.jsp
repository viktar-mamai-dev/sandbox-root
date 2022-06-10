<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
			<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
			<table id="header" style="border-bottom: 3px solid black;">
				<tr>
					<td id="cell1"><a href="<c:url value=" /news/page/1" />">
						<spring:messagecode="label.news.portal" /></a>
					</td>
					<td id="cell2">
						<table id="right-table">
							<tr>
								<sec:authorize access="isAuthenticated()">
									<c:if test="${pageContext.request.userPrincipal.name != null}">
										<td
											style="padding-right: 10px; padding-left: 10px; font-size: 20px; font-weight: bold;">
											<spring:message code="label.hello" /> ,
											${pageContext.request.userPrincipal.name}
										</td>
										<c:url value="/j_spring_security_logout" var="logout" />
										<td>
											<form action="${logout}" method="post"
												style="padding-left: 20px; padding-right: 20px; margin-top: 10px;">
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}" /> <input type="submit" class="submit-btn"
													value="<spring:message code=" button.logout" />">
											</form>
										</td>
									</c:if>
								</sec:authorize>
								<sec:authorize access="!isAuthenticated()">
									<td style="padding-right: 20px;">
										<a href="<c:url value=" /login" />">
										<spring:messagecode="label.login" /></a>
									<td>
								</sec:authorize>
								<td>
									<a href="?lang=en">
										<spring:message code="label.lang.english" />
									</a> | <a href="?lang=ru">
										<spring:message code="label.lang.russian" />
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
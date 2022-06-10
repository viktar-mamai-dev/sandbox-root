<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language }" scope="session" />
<fmt:setBundle basename="pagecontent" />
<h3>
	<fmt:message key="header.error" />
</h3>
<c:choose>
	<c:when test="${pageContext.errorData.statusCode eq 500}">
		<ul>
			<li><span><fmt:message key="errorData.servletName" />:</span><span
				class="error-span"> ${pageContext.errorData.servletName}</span></li>
			<li><span><fmt:message key="errorData.excType" />:</span><span
				class="error-span">
					${pageContext.errorData.throwable['class'].name}</span></li>
			<li><span><fmt:message key="errorData.requestURI" />:</span><span
				class="error-span"> ${pageContext.errorData.requestURI}</span></li>
			<li><span><fmt:message key="exception.message" />:</span><span
				class="error-span"> ${pageContext.exception.message}</span></li>
		</ul>
	</c:when>
	<c:otherwise>
		<ul>
			<li><span><fmt:message key="errorData.statusCode" />:</span><span
				class="error-span"> ${pageContext.errorData.statusCode}</span></li>
			<li><span><fmt:message key="errorData.requestURI" />:</span><span
				class="error-span"> ${pageContext.errorData.requestURI}</span></li>
		</ul>
	</c:otherwise>
</c:choose>
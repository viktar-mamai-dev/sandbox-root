<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
		<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css' />"> --%>
			<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
			<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
			<script>
				$(function () {
					$("#menu-list a").each(function () {
						if (this.href.split("?")[0] == window.location.href.split("?")[0]) {
							$(this).css("font-weight", "bold");
						}
					});
				});
			</script>
			<div id="menu-list" style="padding-left: 10px; padding-right: 10px; height: 250px;">
				<table style="width: 100%; height: 200px;">
					<tr>
						<td><a href="<c:url value=" /news/page/1" />">
							<spring:message code="link.news.list" /> </a>
						</td>
					</tr>
					<tr>
						<td><a href="<c:url value=" /news/create" />">
							<spring:message code="link.news.create" /> </a>
						</td>
					</tr>
					<tr>
						<td><a href="<c:url value=" /author/list" />">
							<spring:message code="link.author.create" /> </a>
						</td>
					</tr>
					<tr>
						<td><a href="<c:url value=" /tag/list" />">
							<spring:message code="link.tag.create" /> </a>
						</td>
					</tr>

				</table>
			</div>
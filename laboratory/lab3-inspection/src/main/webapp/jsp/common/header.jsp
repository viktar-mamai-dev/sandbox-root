<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<fmt:setLocale value="${language }" scope="session" />
			<fmt:setBundle basename="pagecontent" />
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
				<link rel="stylesheet" type="text/css" href="css/menu.css">
			</head>

			<body>
				<jsp:include page="urlConstant.jsp"></jsp:include>
				<c:set var="isLogin" value="${role eq 'reader' or role eq 'moderator' or role eq 'admin'}" />

				<table width="100%">
					<tr>
						<td width="20%">
							<c:if test="${isLogin}">
								<div id="logout">
									<form action="controller" method="post">
										<button name="command" value="logout">
											<fmt:message key="button.logout" />
										</button>
									</form>
								</div>
							</c:if>
						</td>
						<td style="background-color: yellow" width="60%">
							<ul class="main-list">
								<li><a href="${magListUrl}">
										<fmt:message key="link.magazine.list" />
									</a></li>
								<c:if test="${role eq 'reader' }">
									<li><a href="${userItemUrl}">
											<fmt:message key="link.res.user" />
										</a></li>
								</c:if>
								<c:if test="${role eq 'moderator' }">
									<li><a href="${resListUrl}">
											<fmt:message key="link.res.list" />
										</a></li>
								</c:if>
								<c:if test="${role eq 'admin' }">
									<li><a href="${addMagUrl}">
											<fmt:message key="link.magazine.add" />
										</a></li>
									<li><a href="${addSubUrl}">
											<fmt:message key="link.sub.add" />
										</a></li>
								</c:if>
							</ul>
						</td>

						<td>
							<table id="language" cellpadding="5px" cellspacing="5px" style="margin: 0 auto"
								align="center">
								<tr>
									<td width="50%" align="center"><a href="${ruLangUrl }"><img src="img/rus.png"
												style="display: block;" /><span style="display: block;">
												<fmt:message key="link.locale.ru" />
											</span>
										</a></td>
									<td width="50%" align="center"><a href="${enLangUrl }"><img src="img/en.png"
												style="display: block;"><span style="display: block">
												<fmt:message key="link.locale.en" />
											</span></a></td>
								</tr>
								<tr class="border_bottom">
									<c:choose>
										<c:when test="${isLogin}">
											<td colspan="2" align="center" style="color: rgb(230, 54, 58)">
												${user.userName }</td>
										</c:when>
										<c:otherwise>
											<td align="center"><a href="${loginUrl }">
													<fmt:message key="link.login" />
												</a></td>
											<td align="center"><a href="${registerUrl }">
													<fmt:message key="link.register" />
												</a></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr />
			</body>

			</html>
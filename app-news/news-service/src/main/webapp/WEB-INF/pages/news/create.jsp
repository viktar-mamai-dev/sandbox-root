<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
				<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
					<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
						<%@ taglib prefix="custom" uri="/WEB-INF/tag/custom.tld" %>
							<%@ include file="../common/dropdownstyle.jsp" %>
								<!DOCTYPE>
								<html>

								<head>
									<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
									<%-- <link rel="stylesheet" type="text/css"
										href="<c:url value='/resources/css/style.css' />"> --%>
										<link type="text/css"
											href="${pageContext.request.contextPath}/resources/css/style.css"
											rel="stylesheet">
										<script>
											$(function () {
												val = $("#pattern-datepicker").val();
												$("#datepicker").datepicker(
													$.datepicker.regional[$("#hidden-locale").val()]).attr(
														'required', 'required').datepicker("option", "dateFormat", val);
												$("#datepicker").keydown(function (event) {
													event.preventDefault();
												});
											});
										</script>
								</head>

								<body>

									<c:if test="${not empty errorMessage}">
										<div class="error-message-div">
											<div>
												<c:out value="${errorMessage}" />
											</div>
										</div>
									</c:if>

									<c:choose>
										<c:when test="${not empty newsEntity.id }">
											<c:url var="actionPath" value="/news/update" />
											<c:set var="newsEntity" value="${newsObject.newsEntity }" scope="request" />
										</c:when>
										<c:otherwise>
											<c:url var="actionPath" value="/news/create" />
										</c:otherwise>
									</c:choose>

									<c:set var="locale" value="${pageContext.response.locale }" />
									<c:if test="${locale == 'ru'}">
										<input type="hidden" value="ru" id="hidden-locale" />
										<input type="hidden" value="dd.mm.yy" id="pattern-datepicker" />
										<fmt:formatDate value="${newsEntity.modificationDate}" pattern="dd.MM.yy"
											var="newsDate" />
									</c:if>
									<c:if test="${locale == 'en'}">
										<input type="hidden" value="" id="hidden-locale" />
										<input type="hidden" value="mm/dd/yy" id="pattern-datepicker" />
										<fmt:formatDate value="${newsEntity.modificationDate}" pattern="MM/dd/yy"
											var="newsDate" />
									</c:if>


									<form:form action="${actionPath}" commandName="newsEntity" method="post">
										<table id="news-entity"
											style="margin-left: auto; margin-right: auto; margin-top: 30px; margin-bottom: 30px; border-spacing: 20px;">
											<tr>
												<td>
													<form:hidden path="id" />
												</td>
												<td>
													<form:hidden path="version" />
												</td>
											</tr>
											<tr>
												<td>
													<form:label path="title" class="label-field">
														<spring:message code="label.news.title" />
													</form:label>
												</td>
												<td>
													<form:input path="title" class="input-field" maxlength="100"
														required="required" />
												</td>
											</tr>
											<tr>
												<td>
													<form:label path="shortText" class="label-field">
														<spring:message code="label.news.shortText" />
													</form:label>
												</td>
												<td>
													<form:textarea path="shortText" class="input-field" maxlength="100"
														required="required" rows="6" />
												</td>
											</tr>
											<tr>
												<c:choose>
													<c:when test="${empty newsEntity.id }">

														<td>
															<form:label path="creationDate" class="label-field">
																<spring:message code="label.news.creationDate" />
															</form:label>
														</td>
														<td>
															<form:input path="creationDate" id="datepicker" />
														</td>
													</c:when>
													<c:otherwise>
														<td>
															<form:label path="modificationDate" class="label-field">
																<spring:message code="label.news.modificationDate" />
															</form:label>
														</td>
														<td>
															<form:input path="modificationDate" value="${newsDate }"
																id="datepicker" />
														</td>
													</c:otherwise>
												</c:choose>
											</tr>
											<tr>
												<td>
													<form:label path="fullText" class="label-field">
														<spring:message code="label.news.fullText" />
													</form:label>
												</td>
												<td>
													<form:textarea path="fullText" class="input-field" rows="8"
														required="required" maxlength="2000" />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<table style="width: 100%; border-spacing: 20px">
														<tr>
															<td><select name="authorId" id="author-select">
																	<c:forEach items="${authorList }" var="author">
																		<option value="${author.id}"
																			${newsEntity.author.id eq author.id
																			? 'selected' : '' }>
																			<c:out value="${author.name }" />
																	</c:forEach>
																</select></td>
															<td><select name="tagId" id="tag-select"
																	header="<spring:message code=" tag.select" />"
																tag-count="
																<spring:message code="tag.count" />"
																multiple="multiple">
																<option value="">
																	<spring:message code="tag.all"></spring:message>
																	<c:forEach items="${tagList }" var="tag">
																<option value="${tag.id}"
																	${custom:contains(newsEntity.tags, tag) ? 'selected'
																	: '' }>
																	<c:out value="${tag.name }" />
																	</c:forEach>
																	</select>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td colspan="2" align="right"><input type="submit" class="submit-btn"
														value="<spring:message code=" button.save" />" /></td>
											</tr>
										</table>
									</form:form>

								</body>

								</html>
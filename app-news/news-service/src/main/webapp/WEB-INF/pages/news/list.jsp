<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
				<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
					<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
						<%@ taglib prefix="custom" uri="/WEB-INF/tag/custom.tld" %>
							<%@ include file="../common/dropdownstyle.jsp" %>
								<!DOCTYPE>
								<html>

								<head>
									<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
									<script type="text/javascript">
										function deleteNews(message) {
											var numberOfChecked = $(".news-object").find("input:checkbox:checked").length;
											if (numberOfChecked == 0) {
												alert("No one news selected to delete");
												event.preventDefault();
												return;
											}
											if (!confirm(message)) {
												event.preventDefault();
											}
											return true;
										}
									</script>
								</head>

								<body>

									<div class="success-message-div">
										<c:if test="${not empty successMessage}">
											<div>
												<c:out value="${successMessage}" />
											</div>
										</c:if>
									</div>

									<table style="border-spacing: 20px; margin-left: auto; margin-right: auto;">
										<tr>
											<td><select id="tag-select" name="tagId" form="filter-frm"
													header="<spring:message code=" tag.select" />"
												tag-count="
												<spring:message code="tag.count" />"
												style="position: relative" multiple="multiple">
												<option value="0">
													<spring:message code="tag.all" />
													<c:forEach items="${tagList }" var="tag">
												<option value="${tag.id}" ${custom:contains(filteredItem.tagIdList,
													tag.id) ? 'selected' : '' }>
													<c:out value="${tag.name }" />
													</c:forEach>
													</select>
											</td>
											<td><select id="author-select" name="authorId" form="filter-frm"
													style="margin-left: 20px">
													<option value="0">
														<spring:message code="author.select"></spring:message>
													</option>
													<c:forEach items="${authorList }" var="author">
														<option value="${author.id}" ${filteredItem.authorId eq
															author.id ? 'selected' : '' }>
															<c:out value="${author.name }" />
														</option>
													</c:forEach>
												</select></td>
											<td>
												<form method="POST" id="filter-frm"
													action="${pageContext.request.contextPath}/news/filter">
													<button type="submit">
														<spring:message code="button.filter" />
													</button>
												</form>
											</td>

											<td>
												<form method="GET" id="filter-frm"
													action="${pageContext.request.contextPath}/news/page/1">
													<button type="submit">
														<spring:message code="button.reset" />
													</button>
												</form>
											</td>
										</tr>
									</table>

									<c:forEach items="${newsItem.newsList}" var="newsEntity">
										<c:url value="/news/${newsEntity.id}" var="item" />
										<table class="news-object">
											<tr>
												<td colspan="3"><span style="font-weight: bold; margin-right: 20px;"><a
															href="${item }">
															<c:out value="${newsEntity.title}" />
														</a></span><span>
														( by
														<c:out value="${newsEntity.author.name}" /> )
													</span></td>
												<td colspan="2" style="text-align: right; text-decoration: underline;">
													<fmt:formatDate value="${newsEntity.modificationDate}"
														dateStyle="short" />
												</td>
											</tr>
											<tr>
												<td colspan="5">
													<c:out value="${newsEntity.shortText}" />
												</td>
											</tr>
											<tr>
												<td colspan="2"></td>
												<td style="color: #D0D0D0; width: 15%;">
													<c:forEach items="${newsEntity.tags }" var="tag">
														#
														<c:out value="${tag.name }" />
													</c:forEach>
												</td>
												<td style="color: red; width: 15%; text-align: right;">
													<spring:message code="label.comments" />(
													<c:out value="${fn:length(newsEntity.comments) }" />)
												</td>

												<c:url value="/news/update/${newsEntity.id}" var="update" />
												<td style="width: 15%; text-align: right;"><a href="${update }">
														<spring:message code="link.news.edit" />
													</a><input type="checkbox" name="newsId" form="delete-frm"
														value="${newsEntity.id}" style="margin-left: 10px;"></td>
											</tr>

										</table>
									</c:forEach>

									<c:choose>
										<c:when test="${not empty errorEmptyMessage}">
											<div class="error-message-div">
												<c:out value="${errorEmptyMessage}" />
											</div>
										</c:when>
										<c:otherwise>
											<table class="news-object">
												<tr>
													<td colspan="5">
														<div>
															<form
																action="${pageContext.request.contextPath}/news/delete"
																id="delete-frm" method="post">
																<input type="hidden" name="pageNumber"
																	value="${newsItem.pageNumber}"> <input type="submit"
																	class="submit-btn" style="float: right;"
																	value="<spring:message code=" button.delete" />"
																style="width: 100px; height: 40px;"
																onclick="deleteNews('
																<spring:message code="question.news.delete" />')">
															</form>
														</div>
													</td>
												</tr>
											</table>
										</c:otherwise>
									</c:choose>

									<c:choose>
										<c:when test="${empty isFilter }">
											<c:url value="/news/page" var="pageUrl" />
										</c:when>
										<c:otherwise>
											<c:url value="/news/filter" var="pageUrl" />
										</c:otherwise>
									</c:choose>
									<%-- <div class="pagination">
										<c:forEach begin="1" end="${newsItem.pageCount }" var="i">
											<c:choose>
												<c:when test="${newsItem.pageNumber eq i }">
													<span class="page-span">
														<c:out value="${i}" />
													</span>
												</c:when>
												<c:otherwise>
													<a href="${pageUrl}/${i}" class="page-link">
														<c:out value="${i}" />
													</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										</div> --%>

										<c:set var="pageNum" value="${newsItem.pageNumber }"></c:set>
										<c:set var="pageCount" value="${newsItem.pageCount }"></c:set>

										<div class="pagination">

											<c:if test="${pageNum eq 1}">
												<span class="page-span">
													<c:out value="${pageNum}" />
												</span>
												<c:if test="${pageCount ge 2}">
													<a href="${pageUrl}/${pageNum+1}" class="page-link">
														<c:out value="${pageNum+1}" />
													</a>
												</c:if>
											</c:if>
											<c:if test="${pageNum eq 3}">
												<a href="${pageUrl}/${1}" class="page-link">
													<c:out value="${1}" />
												</a>
											</c:if>
											<c:if test="${pageNum ge 4}">
												<a href="${pageUrl}/${1}" class="page-link">
													<c:out value="${1}" />
												</a>
												<span class="page-span">...</span>
											</c:if>

											<c:if test="${pageNum ne 1 and pageNum ne pageCount}">
												<a href="${pageUrl}/${pageNum-1}" class="page-link">
													<c:out value="${pageNum-1}" />
												</a>
												<span class="page-span">
													<c:out value="${pageNum}" />
												</span>
												<a href="${pageUrl}/${pageNum+1}" class="page-link">
													<c:out value="${pageNum+1}" />
												</a>
											</c:if>


											<c:if test="${pageNum eq pageCount and pageCount ge 2}">
												<a href="${pageUrl}/${pageNum-1}" class="page-link">
													<c:out value="${pageNum-1}" />
												</a>
												<span class="page-span">
													<c:out value="${pageNum}" />
												</span>
											</c:if>
											<c:if test="${pageNum eq pageCount - 2}">
												<a href="${pageUrl}/${pageCount}" class="page-link">
													<c:out value="${pageCount}" />
												</a>
											</c:if>
											<c:if test="${pageNum le pageCount - 3}">
												<span class="page-span">...</span>
												<a href="${pageUrl}/${pageCount}" class="page-link">
													<c:out value="${pageCount}" />
												</a>
											</c:if>
										</div>
								</body>

								</html>
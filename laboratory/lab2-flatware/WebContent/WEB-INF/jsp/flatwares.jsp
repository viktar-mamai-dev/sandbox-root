<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Flatwares</title>

			<style type="text/css">
				a {
					color: #FF0000;
					text-decoration: none;
					background-color: #B2FF99;
					font-size: 3em;
				}

				div {
					margin: 40px;
				}

				a:hover {
					color: #FF00FF;
				}

				table,
				th,
				td {
					border: 1px solid black;
				}

				table {
					width: 80%;
					/* 	text-align: center;
 */
					margin-top: 40px;
					margin-bottom: 20px;
					background: #E08BD1;
				}

				table caption {
					font-size: 1.5em;
					font-weight: bold;
				}

				td,
				th {
					padding: 5px;
					color: blue;
					text-align: center;
				}
			</style>
		</head>

		<body>

			<h2 align="center">
				<c:out value="${parserType }" />
				parser
			</h2>
			<table align="center">
				<caption>Primary Flatwares</caption>
				<tr>
					<th>Flatware type</th>
					<th>Origin</th>
					<th>value</th>
					<th>Visual</th>
					<th>price</th>
				</tr>
				<c:forEach var="elem" items="${primary}">
					<tr>
						<td>
							<c:out value="${elem.flatware}" />
						</td>
						<td>
							<c:out value="${elem.origin}" />
						</td>
						<td>
							<c:out value="${elem.value}" />
						</td>
						<td>
							<c:if test="${not empty elem.visual}">
								Blade length :
								<c:out value="${elem.visual.bladeLength }" />
								<br /> Blade width :
								<c:out value="${elem.visual.bladeWidth }" />
								<br /> Prong length :
								<c:out value="${elem.visual.prongLength }" />
								<br />Blade material :
								<c:out value="${elem.visual.blade }" />
								<br /> Helve material :
								<c:out value="${elem.visual.helve }" />
							</c:if>
						</td>
						<td>
							<c:out value="${elem.price}" />
						</td>
					</tr>
				</c:forEach>
			</table>

			<table align="center">
				<caption>Secondary Flatwares</caption>
				<tr>
					<th>Flatware type</th>
					<th>Origin</th>
					<th>Value</th>
					<th>Visual</th>
					<th>Production date</th>
					<th>Durability</th>
					<th>Dishes</th>
				</tr>
				<c:forEach var="elem" items="${secondary}">
					<tr>
						<td>
							<c:out value="${elem.flatware}" />
						</td>
						<td>
							<c:out value="${elem.origin}" />
						</td>
						<td>
							<c:out value="${elem.value}" />
						</td>
						<td>
							<c:if test="${not empty elem.visual}">
								Blade length :
								<c:out value="${elem.visual.bladeLength }" />
								<br /> Blade width :
								<c:out value="${elem.visual.bladeWidth }" />
								<br /> Prong length :
								<c:out value="${elem.visual.prongLength }" />
								<br />Blade material :
								<c:out value="${elem.visual.blade }" />
								<br /> Helve material :
								<c:out value="${elem.visual.helve }" />
							</c:if>
						</td>
						<td>
							<c:out value="${elem.productionDate}" />
						</td>
						<td>
							<c:choose>
								<c:when test="${elem.durability eq 0}">
									<c:out value="unknown" />
								</c:when>
								<c:otherwise>
									<c:out value="${elem.durability }" />
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<ul>
								<c:forEach var="dish" items="${elem.dishes}">
									<li>
										<c:out value="${dish}" />
								</c:forEach>
							</ul>
						</td>
					</tr>
				</c:forEach>
			</table>

			<div align="center">
				<a href="/Task7">Back to start page</a>
			</div>

		</body>

		</html>
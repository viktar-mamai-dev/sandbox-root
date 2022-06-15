<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Start page</title>
		<style type="text/css">
			input {
				margin: 10px;
			}
		</style>
	</head>

	<body>

		<div align="center" style="margin-top: 20px">
			<p>Choose method for parsing
			<form action="FlatwareServlet" method="post">
				<input type="radio" name="parser" value="sax" checked="checked">SAX<br />
				<input type="radio" name="parser" value="dom">DOM<br />
				<input type="radio" name="parser" value="stax">STAX<br />
				<input type="submit" value="Go!">
			</form>
		</div>
	</body>

	</html>
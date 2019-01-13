<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>ERROR</title>
</head>
<body>
	<div>
		<table width="100%" border="0">
			<tr>
				<td colspan="2" bgcolor="#b5dcb3">
					<h1>RABO BANK</h1> <form:form action="redirect"
						method="GET">
						<table>
							<tbody>
								<tr>
									<td><input type="submit" class="btn btn-primary" value="Home Page" /></td>
								</tr>
							</tbody>
						</table>
					</form:form>
				</td>
			</tr>
			<tr valign="top">
				<td bgcolor="#aaa" width="50"></td>

				<td bgcolor="#eee" width="100" height="200">
					<h3>ERROR</h3> Exception: ${errormessage} <br /> <br />
				</td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="#b5dcb3">
					<h5>Copyright © 2019</h5>
				</td>
			</tr>
		</table>
	</div>
</body>

</html>
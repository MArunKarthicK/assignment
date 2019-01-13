<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>HOME</title>
</head>
<body>
	<div>
		<table width="100%" border="0">
			<tr>
				<td colspan="2" bgcolor="#b5dcb3">
					<h1>RABO BANK</h1>
				</td>
			</tr>
			<tr valign="top">
				<td bgcolor="#aaa" width="50">
				</td>
				<td bgcolor="#eee" width="100" height="200">
					<h3>File Upload:</h3> 
					<br /> 
					<h4>Choose file format and upload Transaction Report:</h4> 
					<br />
					<select name="filetype" class="btn btn-default" form="uploadform">
						<option value="xml">XML</option>
						<option value="csv">CSV</option>
					</select>
					<br />
					<br /> 
					<form id="uploadform" action="upload" method="post"
						enctype="multipart/form-data">
						<input type="file" class="file btn btn-lg btn-default" name="file" />
						<br /> <br /> 
						<input type="submit" class="btn btn-primary" value="Generate Report" />
					</form>
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

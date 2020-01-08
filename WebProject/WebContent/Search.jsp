<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
<style>
* {
	text-align: center;
	vertical-align: middle;
}
input{
	text-align: left;
}
h1 {
	font-family: verdana;
}
</style>
</head>

<body>
<h1>Couple</h1>
	<form action='${requestUri}' method='get'>
		<input type='text' name='keyword' placeholder='enter' />
		<input type='submit' value='Go' />
	</form>
</body>

</html>
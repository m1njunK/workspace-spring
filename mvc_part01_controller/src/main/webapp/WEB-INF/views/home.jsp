<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! - ${sessionScope.test}
</h1>
<h2>
	model : ${requestScope.modelTest}
</h2>
<h2>
	doC
</h2>
<a href="doA">doA</a> <br/>
<a href="doB">doB</a> <br/>
<a href="doC">doC</a> <br/>
<a href="doD?msg=helloSpring">doD</a> <br/>
<a href="doD">non-param doD</a> <br/>
<a href="doE">non-param doE</a> <br/>
<hr/>
<form action="doF" method="POST">
	<input type="text" name="msg" required/>
	<input type="number" name="age" required/>
	<input type="submit" value="doF POST"/>
</form>
<hr/>
<h2>product</h2>
<form action="productWrite" method="POST">
	<input type="text" name="name" required/>
	<input type="number" name="price" required/>
	<input type="submit" value="PRODUCT WRITE"/>
</form>
<hr/>
<h2>product submit</h2>
<form action="productWriteSubmit" method="POST">
	<input type="text" name="name" required/>
	<input type="number" name="price" required/>
	<input type="submit" value="PRODUCT WRITE"/>
</form>
<hr/>
<a href="redirect">redirect</a>
</body>
</html>

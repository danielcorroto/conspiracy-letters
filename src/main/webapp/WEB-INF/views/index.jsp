<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>">
        
        <link rel="stylesheet" href="<c:url value="/static/css/bootstrap-theme.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>">

        <script src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"/>"></script>
		
		<style>
			body { padding-top: 70px; }
			
			.container-separator { margin-bottom: 10px; }
		</style>
    </head>
	<body>

	Bienvenido
	<form name='f' action="j_spring_security_check" method='POST'>
		<input id="username" name='j_username' type="text" />
		<input name='j_password' type="password" />
		<input type="submit" value="Login" />
	</form>
	<a href="player/">Link directo dentro</a>


	<script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js"/>"></script>

	<script src="<c:url value="/static/js/vendor/bootstrap.min.js"/>"></script>

	<script src="<c:url value="/static/js/main.js"/>"></script>
		
	<script type="text/javascript">
	$(document).ready(function() {
		$("#username").focus();
	});

	</script>
	</body>
</html>
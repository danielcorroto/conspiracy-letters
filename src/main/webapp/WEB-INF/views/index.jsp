<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="no-js">
<%@include file="includes/header.jsp"%>
    
<body>

	Bienvenido
	<form name='f' action="j_spring_security_check" method='POST'>
		<input id="username" name='j_username' type="text" />
		<input name='j_password' type="password" />
		<input type="submit" value="Login" />
	</form>
	<a href="player/">Link directo dentro</a>


	<%@include file="includes/footer.jsp"%>
		
	<script type="text/javascript">
		$(document).ready(function() {
			$("#username").focus();
		});
	</script>
</body>
</html>
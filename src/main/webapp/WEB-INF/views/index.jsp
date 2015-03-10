<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="no-js">
<%@include file="includes/header.jsp"%>
<jsp:include page="includes/header.jsp">
		<jsp:param name="header" value="header" />
	</jsp:include>
    
<body>

	<div class="container">
		<div class="row" id="pwd-container">
			<div class="col-md-4 col-md-offset-4">
				<section class="login-form">
					<form method="post" action="j_spring_security_check" name='login_form' role="login">
						<img alt="Brand" src="<c:url value="/static/img/logo_cl.png"/>" class="img-responsive" alt="Conspiracy Letters" />
						<input id="username" name='j_username' type="text" placeholder="Jugador" required class="form-control input-lg" />
						<input name='j_password' type="password" class="form-control input-lg" id="password" placeholder="Contraseña" required="" />
						<div class="pwstrength_viewport_progress"></div>
						<button type="submit" name="go"
							class="btn btn-lg btn-primary btn-block">Iniciar sesión</button>
						<!-- div>
							<a href="#">Create account</a> or <a href="#">reset password</a>
						</div-->
					</form>
				</section>
			</div>
			<div class="col-md-4"></div>
		</div>

	</div>


	<%@include file="includes/footer.jsp"%>
		
	<script type="text/javascript">
		$(document).ready(function() {
			$("#username").focus();
		});
	</script>
</body>
</html>
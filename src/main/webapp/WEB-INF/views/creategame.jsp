<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="no-js">
    <%@include file="includes/header.jsp"%>
    
	<body>
	<jsp:include page="includes/menu.jsp">
		<jsp:param name="selected" value="creategame" />
	</jsp:include>
	
	<form action="#" method="get">
	<div class="container container-separator">
		<div class="row">
			<div class="col-xs-12 col-md-4 col-md-offset-4">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span></span>
					<input id="username" name="guest" type="text" class="form-control" placeholder="Jugador" aria-describedby="basic-addon1">
				</div>
			</div>
		</div>
	</div>
	<div class="container container-separator">
		<div class="row">
			<div class="col-xs-12 col-md-4 col-md-offset-4">
				<button class="btn btn-success btn-lg btn-block" type="submit">
					<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
					INVITAR
				</button>
			</div>
		</div>
	</div>
	
	</form>
	
	<div class="container container-separator">
		<div class="row">
			<div class="col-xs-12 col-md-4 col-md-offset-4">
				<div id="invitation_ok" style="display:none" class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok"></span> Jugador invitado</div>
				<div id="invitation_ko" style="display:none" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove"></span> El jugador no existe</div>
			</div>
		</div>
	</div>
	
	<%@include file="includes/footer.jsp"%>
		
	<script type="text/javascript">
	$(document).ready(function() {
		$("#username").focus();
		
		// SAVE
		$("form").submit(function(e) {
			var url = "${pageContext.servletContext.contextPath}" + "/api/invitation/add";
			$.post(url, $(this).serialize(), function(response) {
				$("#invitation_ok").slideUp();
				$("#invitation_ko").slideUp();
				if (response) {
					$("#invitation_ok").slideDown();
				} else {
					$("#invitation_ko").slideDown();
				}
			});
			e.preventDefault(); // prevent actual form submit and page reload
		});
	});
	</script>
</body>
</html>
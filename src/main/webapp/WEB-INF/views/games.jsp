<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="no-js">
    <%@include file="includes/header.jsp"%>
    
	<body>
	<jsp:include page="includes/menu.jsp">
		<jsp:param name="selected" value="games" />
	</jsp:include>

	<div class="container container-separator">
		<div class="row">
			<div class="col-xs-12 col-md-4 col-md-offset-4">
				<button type="button" class="btn btn-success btn-lg btn-block" onclick="window.location.href='<c:url value="/player/creategame" />'">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Nueva partida
				</button>
			</div>
		</div>
	</div>
	
	<!-- div class="container container-separator" id="my_games">
		<div class="row">
			<div class="progress" id="loading">
				<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
			</div>
		</div>
	</div -->
    
	<div class="container container-separator" id="my_games">
		<div class="row">
			<div class="col-xs-12 col-md-4 col-md-offset-4">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">Mis partidas</h3>
					</div>
					
					<div class="list-group" id="my_games_list">
					</div>
				</div>
			</div>
		</div>
	</div>
	

	<%@include file="includes/footer.jsp"%>
		
	<script type="text/javascript">
	$(document).ready(function() {
		loadGamesList();
	});

	</script>
</body>
</html>
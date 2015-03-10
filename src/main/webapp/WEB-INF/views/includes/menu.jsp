<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/player/" />">
					<img alt="Brand" src="<c:url value="/static/img/logo_cl.png"/>" width="24" height="24">
				</a>
				<a class="navbar-brand" href="<c:url value="/player/" />">Conspiracy Letters</a>
				<p class="navbar-text">${username }</p>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<c:choose>
						<c:when test="${param.selected == 'games'}"><li class="active"></c:when>
						<c:otherwise><li></c:otherwise>
					</c:choose>
						<a href="<c:url value="/player/games" />"><span class="glyphicon glyphicon-tasks"></span> Mis partidas</a></li>
					<c:choose>
						<c:when test="${param.selected == 'creategame'}"><li class="active"></c:when>
						<c:otherwise><li></c:otherwise>
					</c:choose>
						<a href="<c:url value="/player/creategame" />"><span class=" glyphicon glyphicon-plus"></span> Nueva partida</a></li>
					<li>
						<a href="<c:url value="/logout" />" class="logout"><span class="glyphicon glyphicon-log-out"></span> Salir</a></li>
				</ul>
			</div>
		</div>
	</nav>
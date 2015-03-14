<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="no-js">
    <%@include file="includes/header.jsp"%>
    
	<body>
	<jsp:include page="includes/menu.jsp">
		<jsp:param name="selected" value="game" />
	</jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-md-5 col-md-offset-3">
				<span class="label label-primary">Partida: Rey listo y tonto</span>
			</div>
			<div class="col-xs-4 col-md-1 text-right">
				<span class="glyphicon glyphicon-info-sign info" data-toggle="modal" data-target="#rolListModal"></span>
			</div>
		</div>
		<div clasS="row">
			<div class="col-xs-4 col-md-2 col-md-offset-3 text-center">
				<a href="#" data-toggle="modal" data-target="#player1Modal">user</a> <span class="badge label-success">4</span>
			</div>
			<div class="col-xs-4 col-md-2 text-center">
				<a href="#" data-toggle="modal" data-target="#player2Modal">admin</a> <span class="badge">3</span>
			</div>
			<div class="col-xs-4 col-md-2 text-center">
				Baraja
			</div>
		</div>
		<div clasS="row">
			<div class="col-xs-4 col-md-2 col-md-offset-3">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_bottom.png" />" class="img-responsive">
			</div>
			<div class="col-xs-4 col-md-2">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_bottom.png" />" class="img-responsive">
			</div>
			<div class="col-xs-4 col-md-2">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_back.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_bottom.png" />" class="img-responsive">
			</div>
		</div>
	</div>

	<div class="container container-separator">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 col-md-4 col-md-offset-4">
				<button id="play" class="btn btn-xs btn-primary btn-block">Jugar</button>
			</div>
		</div>
		<div class="row container-separator">
			<div class="col-xs-6 col-md-2 col-md-offset-4">
				<img src="<c:url value="/static/img/card_2_top.png" />" class="img-responsive card-top-selected">
				<img src="<c:url value="/static/img/card_2_bottom.png" />" class="img-responsive card-bottom-selected">
			</div>
			<div class="col-xs-6 col-md-2">
				<img src="<c:url value="/static/img/card_3_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_3_bottom.png" />" class="img-responsive">
			</div>
			
		</div>
	</div>
	
	<div class="container container-separator">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 col-md-2 col-md-offset-5">
				<div id="attack_1" class="dropup">
					<button class="btn btn-default btn-block dropdown-toggle" type="button" id="attack1Menu" data-toggle="dropdown" aria-expanded="true">
						Atacar a:
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu" aria-labelledby="attack1Menu">
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#attack_1">Sacerdote</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#attack_1">Barón</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#attack_1">Doncella</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#attack_1">Príncipe</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#attack_1">Rey</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#attack_1">Condesa</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#attack_1">Princesa</a></li>
					</ul>
				</div>
				<div id="attack_5" class="dropup">
					<button class="btn btn-default btn-block dropdown-toggle" type="button" id="attack5Menu" data-toggle="dropdown" aria-expanded="true">
						Atacar a:
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu" aria-labelledby="attack5Menu">
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#attack_5">user</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#attack_5">admin</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal rules -->
	<div class="modal fade" id="rolListModal" tabindex="-1" role="dialog" aria-labelledby="rolListModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Personajes</h4>
				</div>
				<div class="modal-body">
				<p>8. <b>Princesa</b> Annette:
				si descartas a la Princesa, no importa cómo o por qué, ella ha arrojado tu carta a la chimenea. Eres eliminado de la ronda
				<p>7. <b>Condesa</b> Wilhelmina:
				Si en algún momento tienes a la Condesa y al Rey o al Príncipe en tu mano, estás descartar a la condesa. No tienes que revelar la otra carta de tu mano. Por supuesto tú puedes descartar a la condesa incluso si no tienes a un miembro de la familia real en tu mano. A la condesa le encanta provocar intrigas...
				<p>6. <b>Rey</b> Arnaud IV:
				Al descartar al rey Arnaud IV, intercambia la carta en tu mano por la que tenga otro jugador de tu elección. No puedes cambiarla con un jugador ya eliminado en la ronda ni con uno protegido por la doncella. Si todos los jugadores están protegidos por la doncella la carta no tiene efecto.
				<p>5. <b>Príncipe</b> Arnaud:
				Al descartar al príncipe Arnaud, escoge un jugador que todavía esté en la ronda (incluido tú). Ese jugador descarta su mano (sin aplicar el efecto) y roba una nueva carta. Si el mazo se termina ese jugador robará la carta que fue eliminada al principio de la ronda. Si los demás jugadores están protegidos por la doncella, debes elegirte a ti mismo.
				<p>4. <b>Doncella</b> Susannah:
				Al descartar a la doncella, te haces inmune a los efectos de las cartas de los demás jugadores hasta que comience tu siguiente turno. Si todos los jugadores salvo el actual están protegidos por Susannah, ese jugador deberá elegirse a sí mismo si le es posible.
				<p>3. <b>Barón</b> Talus:
				Al ser descartado elije a un jugador que permanezca en la ronda. Tú y ese jugador comparáis en secreto vuestras manos. Aquel con una carta más baja es eliminado de la ronda. En caso de empate no ocurre nada. Si los demás jugadores están protegidos por la doncella la carta no tiene efecto.
				<p>2. Tomás el <b>sacerdote</b>:
				Al descartar al clérigo puedes mirar la mano de otro jugador. No la muestres a los demás jugadores.
				<p>1. Odette la <b>guardia</b>:
				Al descartar a la guardia, elige un jugador y nombra una carta (que no sea la Guardia). Si ese jugador tiene esa carta, es eliminado de la ronda. Si los demás jugadores están protegidos por la doncella la carta no tiene efecto.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal player 1 -->
	<div class="modal fade" id="player1Modal" tabindex="-1" role="dialog" aria-labelledby="player1ModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Player log</h4>
				</div>
				<div class="modal-body">Jugó tal y cual...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal player 2 -->
	<div class="modal fade" id="player2Modal" tabindex="-1" role="dialog" aria-labelledby="player2ModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Player log</h4>
				</div>
				<div class="modal-body">Jugó tal y cual...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
		
	<script type="text/javascript">
	$(document).ready(function() {
		loadGame();
	});

	</script>
</body>
</html>
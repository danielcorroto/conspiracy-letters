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
			<div class="col-xs-8 col-md-3 col-md-offset-4">
				<span class="label label-primary">Partida: Rey listo y tonto</span>
			</div>
			<div class="col-xs-4 col-md-1 text-right">
				<span class="glyphicon glyphicon-info-sign info" data-toggle="modal" data-target="#rolListModal"></span>
			</div>
		</div>
		<div clasS="row">
			<div class="col-xs-6 col-md-2 col-md-offset-4 text-center">
				user <span class="badge label-success">4</span>
			</div>
			<div class="col-xs-6 col-md-2 text-center">
				admin <span class="badge">3</span>
			</div>
		</div>
		<div clasS="row">
			<div class="col-xs-6 col-md-2 col-md-offset-4">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_bottom.png" />" class="img-responsive">
			</div>
			<div class="col-xs-6 col-md-2">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_top.png" />" class="img-responsive">
				<img src="<c:url value="/static/img/card_1_bottom.png" />" class="img-responsive">
			</div>
		</div>
		<div clasS="row">
			<div class="col-xs-6 col-md-2 col-md-offset-4">
				<a href="#" data-toggle="modal" data-target="#player1Modal">Final 1</a>
			</div>
			<div class="col-xs-6 col-md-2">
				<a href="#" data-toggle="modal" data-target="#myModalLog2">Final 2</a>
			</div>
		</div>
	</div>
	
	<!-- Modal rules -->
	<div class="modal fade" id="rolListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
				si descartas a la Princesa, no importa c�mo o por qu�, ella ha arrojado tu carta a la chimenea. Eres eliminado de la ronda
				<p>7. <b>Condesa</b> Wilhelmina:
				Si en alg�n momento tienes a la Condesa y al Rey o al Pr�ncipe en tu mano, est�s descartar a la condesa. No tienes que revelar la otra carta de tu mano. Por supuesto t� puedes descartar a la condesa incluso si no tienes a un miembro de la familia real en tu mano. A la condesa le encanta provocar intrigas...
				<p>6. <b>Rey</b> Arnaud IV:
				Al descartar al rey Arnaud IV, intercambia la carta en tu mano por la que tenga otro jugador de tu elecci�n. No puedes cambiarla con un jugador ya eliminado en la ronda ni con uno protegido por la doncella. Si todos los jugadores est�n protegidos por la doncella la carta no tiene efecto.
				<p>5. <b>Pr�ncipe</b> Arnaud:
				Al descartar al pr�ncipe Arnaud, escoge un jugador que todav�a est� en la ronda (incluido t�). Ese jugador descarta su mano (sin aplicar el efecto) y roba una nueva carta. Si el mazo se termina ese jugador robar� la carta que fue eliminada al principio de la ronda. Si los dem�s jugadores est�n protegidos por la doncella, debes elegirte a ti mismo.
				<p>4. <b>Doncella</b> Susannah:
				Al descartar a la doncella, te haces inmune a los efectos de las cartas de los dem�s jugadores hasta que comience tu siguiente turno. Si todos los jugadores salvo el actual est�n protegidos por Susannah, ese jugador deber� elegirse a s� mismo si le es posible.
				<p>3. <b>Bar�n</b> Talus:
				Al ser descartado elije a un jugador que permanezca en la ronda. T� y ese jugador compar�is en secreto vuestras manos. Aquel con una carta m�s baja es eliminado de la ronda. En caso de empate no ocurre nada. Si los dem�s jugadores est�n protegidos por la doncella la carta no tiene efecto.
				<p>2. Tom�s el <b>sacerdote</b>:
				Al descartar al cl�rigo puedes mirar la mano de otro jugador. No la muestres a los dem�s jugadores.
				<p>1. Odette la <b>guardia</b>:
				Al descartar a la guardia, elige un jugador y nombra una carta (que no sea la Guardia). Si ese jugador tiene esa carta, es eliminado de la ronda. Si los dem�s jugadores est�n protegidos por la doncella la carta no tiene efecto.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal player 1 -->
	<div class="modal fade" id="player1Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Player log</h4>
				</div>
				<div class="modal-body">Jug� tal y cual...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal player 2 -->


	<%@include file="includes/footer.jsp"%>
		
	<script type="text/javascript">
	$(document).ready(function() {
		loadGame();
	});

	</script>
</body>
</html>
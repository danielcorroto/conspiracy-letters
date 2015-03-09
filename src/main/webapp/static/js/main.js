// My games
function loadGamesList() {
	$.getJSON("../api/games", function(data) {
		$.each(data, function(key, val) {
			console.log(key);
			console.log(val);
			$("#my_games_list").append('<a href="game/' + val["id"] + '" class="list-group-item"><span class="badge label-success">' + val["ownPoints"] + '</span>' + val["name"] + '</a>');
		});
	});
}

// Games I have been invited
function loadInvitationList() {
	$.getJSON("../api/invitation/list", function(data) {
		$.each(data, function(key, val) {
			console.log(key);
			console.log(val);
			$("#my_invitation_list").append('<li class="list-group-item" id="invitation_' + val["id"] + '">' + val["name"] + ' a <i>' + val["guest"] + '</i><div class="row container-separator">' +
					'<div class="col-xs-6"><button type="button" class="btn btn-danger" onclick="cancelInvitation(' + val["id"] + ')">Cancelar</button></div>' +
					'</div></li>');
		});
	});
}

// Games I invite
function loadInvitedList() {
	$.getJSON("../api/invited/list", function(data) {
		$.each(data, function(key, val) {
			console.log(key);
			console.log(val);
			$("#my_invited_list").append('<li class="list-group-item" id="invited_' + val["id"] + '">' + val["name"] + ' de <i>' + val["guest"] + '</i><div class="row container-separator">' +
					'<div class="col-xs-6"><button type="button" class="btn btn-danger" onclick="rejectInvitation(' + val["id"] + ')">Rechazar</button></div>' +
					'<div class="col-xs-6 text-right"><button type="button" class="btn btn-success" onclick="acceptInvitation(' + val["id"] + ')">Aceptar</button></div>' +
					'</div></li>');
		});
	});
}

// Cancel game I invite
function cancelInvitation(id) {
	$.getJSON("../api/invitation/cancel/"+id, function(data) {
		$("#invitation_"+data).remove();
	});
}

// Accept game I have been invited
function acceptInvitation(id) {
	$.getJSON("../api/invited/accept/"+id, function(data) {
		//$("#invited_"+data["id"]).remove();
		//$("#my_games_list").append('<a href="game/' + data["id"] + '" class="list-group-item"><span class="badge label-success">' + data["ownPoints"] + '</span>' + data["name"] + '</a>');
	});
}

// Reject game I have been invited
function rejectInvitation(id) {
	$.getJSON("../api/invited/reject/"+id, function(data) {
		$("#invited_"+data).remove();
	});
}
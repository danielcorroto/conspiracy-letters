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
					'<div class="col-xs-6"><button type="button" class="btn btn-danger" onclick="rejectInvited(' + val["id"] + ')">Rechazar</button></div>' +
					'<div class="col-xs-6 text-right"><button type="button" class="btn btn-success" onclick="acceptInvited(' + val["id"] + ')">Aceptar</button></div>' +
					'</div></li>');
		});
	});
}

// Cancel game I invite
function cancelInvitation(id) {
	$.getJSON("../api/invitation/cancel/"+id, function(data) {
		$("#invitation_"+id).remove();
	});
}

// Accept game I have been invited
function acceptInvited(id) {
	$.getJSON("../api/invited/accept/"+id, function(data) {
		console.log(id);
		$("#invited_"+id).remove();
		loadGamesList();
	});
}

// Reject game I have been invited
function rejectInvited(id) {
	$.getJSON("../api/invited/reject/"+id, function(data) {
		$("#invited_"+id).remove();
	});
}

function loadGame() {
	console.log(window.location.href);
	console.log(document.URL);
}
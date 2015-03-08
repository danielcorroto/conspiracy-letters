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
			$("#my_invitation_list").append('<a href="invitation/' + val["id"] + '" class="list-group-item">' + val["name"] + '</a>');
		});
	});
}

// Games I invite
function loadInvitedList() {
	$.getJSON("../api/invited/list", function(data) {
		$.each(data, function(key, val) {
			console.log(key);
			console.log(val);
			$("#my_invited_list").append('<li class="list-group-item">' + val["name"] + ' de <i>' + val["guest"] + '</i><div class="row container-separator">' +
					'<div class="col-xs-6"><button type="button" class="btn btn-danger" onclick="rejectInvitation(' + val["id"] + ')">Rechazar</button></div>' +
					'<div class="col-xs-6 text-right"><button type="button" class="btn btn-success" onclick="acceptInvitation(' + val["id"] + ')">Aceptar</button></div>' +
					'</div></li>');
		});
	});
}

// Cancel game I invite
function cancelInvitation(id) {
	// TODO
}

// Accept game I have been invited
function acceptInvitation(id) {
	// TODO
}

// Reject game I have been invited
function rejectInvitation(id) {
	// TODO
}
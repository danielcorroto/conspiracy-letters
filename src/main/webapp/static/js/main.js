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
function loadInvitedList() {
	$.getJSON("../api/invitation/list", function(data) {
		$.each(data, function(key, val) {
			console.log(key);
			console.log(val);
			$("#my_invitation_list").append('<a href="invitation/' + val["id"] + '" class="list-group-item">' + val["name"] + '</a>');
		});
	});
}

// Games I invite
function loadInvitationList() {
	$.getJSON("../api/invited/list", function(data) {
		$.each(data, function(key, val) {
			console.log(key);
			console.log(val);
			$("#my_invited_list").append('<a href="invitated/' + val["id"] + '" class="list-group-item">' + val["name"] + '</a>');
		});
	});
}
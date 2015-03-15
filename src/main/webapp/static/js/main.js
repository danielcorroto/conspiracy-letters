// My games
function loadGamesList() {
	$.getJSON("../api/games", function(data) {
		if (data.length == 0) {
			$("#my_games").hide();
		}
		$("#my_games_list").empty();
		$.each(data, function(key, val) {
			$("#my_games_list").append('<a href="game/' + val["id"] + '" class="list-group-item"><span class="badge label-success">' + val["ownPoints"] + '</span>' + val["name"] + '</a>');
		});
	});
}

// Games I have been invited
function loadInvitationList() {
	$.getJSON("../api/invitation/list", function(data) {
		if (data.length == 0) {
			$("#my_invitation").hide();
		}
		$.each(data, function(key, val) {
			$("#my_invitation_list").append('<li class="list-group-item" id="invitation_' + val["id"] + '">' + val["name"] + ' a <i>' + val["guest"] + '</i><div class="row container-separator">' +
					'<div class="col-xs-6"><button type="button" class="btn btn-danger" onclick="cancelInvitation(' + val["id"] + ')">Cancelar</button></div>' +
					'</div></li>');
		});
	});
}

// Games I invite
function loadInvitedList() {
	$.getJSON("../api/invited/list", function(data) {
		if (data.length == 0) {
			$("#my_invited").hide();
		}
		$.each(data, function(key, val) {
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
		len = $("#my_invitation_list").length;
		if ($("#my_invitation_list").children().length == 0) {
			$("#my_invitation").hide();
		}
	});
}

// Accept game I have been invited
function acceptInvited(id) {
	$.getJSON("../api/invited/accept/"+id, function(data) {
		$("#invited_"+id).remove();
		if ($("#my_invited_list").children().length == 0) {
			$("#my_invited").hide();
		}
		loadGamesList();
	});
}

// Reject game I have been invited
function rejectInvited(id) {
	$.getJSON("../api/invited/reject/"+id, function(data) {
		$("#invited_"+id).remove();
		if ($("#my_invited_list").children().length == 0) {
			$("#my_invited").hide();
		}
	});
}

function loadGame() {
	var base = "../..";
	
	var pos = window.location.href.lastIndexOf("/");
	var id = window.location.href.substring(pos+1);
	
	$.getJSON(base+"/api/game/"+id, function(data) {
		$("#game_name").text(data.name);

		$("#game_player1").text(data.me.name);
		$("#game_player2").text(data.rival.name);
		$("#game_points1").text(data.me.points);
		$("#game_points2").text(data.rival.points);
		
		for (var i=0; i<data.me.played.length; i++) {
			$("#game_played1").append("<img src='"+base+"/static/img/card_"+data.me.played[i]+"_top.png' class='img-responsive'>");
			if (i+1 == data.me.played.length) {
				$("#game_played1").append("<img src='"+base+"/static/img/card_"+data.me.played[i]+"_bottom.png' class='img-responsive'>");
			}
		}
		for (var i=0; i<data.rival.played.length; i++) {
			$("#game_played2").append("<img src='"+base+"/static/img/card_"+data.rival.played[i]+"_top.png' class='img-responsive'>");
			if (i+1 == data.rival.played.length) {
				$("#game_played2").append("<img src='"+base+"/static/img/card_"+data.rival.played[i]+"_bottom.png' class='img-responsive'>");
			}
		}
		for (var i=0; i<data.discarded.length; i++) {
			$("#game_deck").append("<img src='"+base+"/static/img/card_"+data.discarded[i]+"_top.png' class='img-responsive'>");
			if (i+1 == data.rival.played.length && data.unplayed == 0) {
				$("#game_deck").append("<img src='"+base+"/static/img/card_"+data.discarded[i]+"_bottom.png' class='img-responsive'>");
			}
		}
		for (var i=0; i<data.unplayed; i++) {
			$("#game_deck").append("<img src='"+base+"/static/img/card_back_top.png' class='img-responsive'>");
			if (i+1 == data.unplayed) {
				$("#game_deck").append("<img src='"+base+"/static/img/card_1_bottom.png' class='img-responsive'>");
			}
		}
		
		$("#game_option1").append("<img src='"+base+"/static/img/card_"+data.me.hand[0]+"_top.png' class='img-responsive' onclick='selectCard(\"game_option1\",\"game_option2\","+data.me.hand[0]+")'>");
		$("#game_option1").append("<img src='"+base+"/static/img/card_"+data.me.hand[0]+"_bottom.png' class='img-responsive' onclick='selectCard(\"game_option1\",\"game_option2\","+data.me.hand[0]+")'>");
		$("#game_option2").append("<img src='"+base+"/static/img/card_"+data.me.hand[1]+"_top.png' class='img-responsive' onclick='selectCard(\"game_option2\",\"game_option1\","+data.me.hand[1]+")'>");
		$("#game_option2").append("<img src='"+base+"/static/img/card_"+data.me.hand[1]+"_bottom.png' class='img-responsive' onclick='selectCard(\"game_option2\",\"game_option1\","+data.me.hand[1]+")'>");
	});
}

function selectCard(active,inactive, value) {
	var e_active = $("#"+active).children();
	$(e_active[0]).addClass("card-top-selected");
	$(e_active[1]).addClass("card-bottom-selected");
	
	var e_inactive = $("#"+inactive).children();
	$(e_inactive[0]).removeClass("card-top-selected");
	$(e_inactive[1]).removeClass("card-bottom-selected");
	
	$("#game_form_selected").val(value);
}
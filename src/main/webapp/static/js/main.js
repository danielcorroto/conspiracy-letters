
function loadGamesList() {
	$.getJSON("../api/games", function(data) {
		$.each(data, function(key, val) {
			console.log(key);
			console.log(val);
			$("#my_games_list").append('<a href="game/' + val["id"] + '" class="list-group-item"><span class="badge label-success">' + val["ownPoints"] + '</span>' + val["name"] + '</a>');
		});
	});
}
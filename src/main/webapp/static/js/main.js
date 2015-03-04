
function loadGamesList() {
	$.getJSON("/rest/games/list", function(data) {
		$.each(data, function(key, val) {
			console.log(key);
			console.log(val);
			$("#list").append('<form id="b_'+val["id"]+'" method="post"><table><tr><input type="hidden" name="id" value="'+val["id"]+'"><td><input type="text" name="name" value="'
				+ val["name"]
				+ '" onkeydown="changed(this)"></td><td><input type="text" name="authors" value="'
				+ val["authors"]
				+ '" onkeydown="changed(this)"></td><td><input type="submit" value="enviar"></td></tr></table></form>');
		});
	});
}
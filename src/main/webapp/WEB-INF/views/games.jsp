<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>">
        
        <link rel="stylesheet" href="<c:url value="/static/css/bootstrap-theme.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>">

        <script src="<c:url value="/static/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"/>"></script>
		
		<style>
			body { padding-top: 70px; }
			
			.container-separator { margin-bottom: 10px; }
		</style>
    </head>
	
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">
					<img alt="Brand" src="<c:url value="/static/img/logo_cl.png"/>" width="30" height="30">
				</a>
				<a class="navbar-brand" href="#">Conspiracy Letters</a>
				<p class="navbar-text">_Conspiracy Letters_</p>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Link1 <span class="sr-only">(current)</span></a></li>
					<li><a href="#">Link2</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container container-separator">
		<div class="row">
			<div class="col-xs-12 col-md-4 col-md-offset-4">
				<button type="button" class="btn btn-success btn-lg btn-block">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> NEW
				</button>
			</div>
		</div>
	</div>
	
	<div class="container container-separator" id="my_games">
		<div class="row">
			<div class="progress" id="loading">
				<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
			</div>
		</div>
	</div>
    
	<div class="container container-separator" id="my_games">
		<div class="row">
			<div class="col-xs-12 col-md-4 col-md-offset-4">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">Panel content</div>
					
					<div class="list-group" id="my_games_list">
						<a href="#" class="list-group-item"><span class="badge badge-success">14</span><span class="badge">13</span>Cras justo odio</a>
						<a href="#" class="list-group-item">Dapibus ac facilisis in</a>
						<a href="#" class="list-group-item">Morbi leo risus</a>
						<a href="#" class="list-group-item">Porta ac consectetur ac</a>
						<a href="#" class="list-group-item">Vestibulum at eros</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	

	<script src="<c:url value="/static/js/vendor/jquery-1.11.1.min.js"/>"></script>

	<script src="<c:url value="/static/js/vendor/bootstrap.min.js"/>"></script>

	<script src="<c:url value="/static/js/main.js"/>"></script>
		
	<script type="text/javascript">
	$(document).ready(function() {
		loadGamesList();
	});

	</script>
</body>
</html>
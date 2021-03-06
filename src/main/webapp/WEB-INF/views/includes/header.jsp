<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Conspiracy Letters</title>
		<meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="icon" type="image/png" href="<c:url value="/static/img/logo_cl.png"/>">

		<link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>">
		
		<link rel="stylesheet" href="<c:url value="/static/css/bootstrap-theme.min.css"/>">
		<link rel="stylesheet" href="<c:url value="/static/css/main.css"/>">
		
		<c:if test="${param.header == 'header'}">
		<link rel="stylesheet" href="<c:url value="/static/css/login.css"/>">
		</c:if>

		<script src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"/>"></script>
	</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	my view
	<a href="<c:url value="/j_spring_security_logout" />"> Logout</a><br>
	Hola: <c:out value="${name}"></c:out>
</body>
</html>
<%@tag description="Base Layout Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="page" required="true" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Modern web applications with Jakarta EE and Tomcat - ${page}</title>
	<link rel="stylesheet" href="https://unpkg.com/chota@latest">
</head>
<body>
<header class="container">
	<nav class="nav">
		<div class="nav-left">
			<a class="brand" href="#">My Posts</a>
			<div class="tabs">
				<a href="${mvc.uri('overview')}">Posts</a>
			</div>
		</div>

	</nav>
</header>
<main class="container">
	<c:if test="${commonMessage != null}">
		<div class="card">
			<p>${commonMessage}</p>
		</div>
	</c:if>
	<jsp:doBody/>
</main>
</body>
</html>
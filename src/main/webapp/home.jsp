<%@page import="util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/home.css" />
<title>Game Rush</title>
<link rel="stylesheet" href="style.css" />


</head>
<jsp:include page="<%=StringUtil.PAGE_URL_HEADER%>" />
<body>

	<section class="hero-section">
		<div class="hero">
			<h2>Welcome to Game Rush!</h2>
			<p>Immerse yourself in the thrilling realm of gaming and bring
				your gaming fantasies to life. Explore an infinite universe of
				adventures, challenges, and victories - embark on your gaming
				journey and shape your digital destiny with us.</p>

			<div class="buttons">
				<a href="#game-section" class="buy">Buy Now</a> <a href="#game-section"
					class="view">View more games</a>
			</div>
		</div>
		<div class="img">
			<img
				src="${pageContext.request.contextPath}/resources/images/others/gameHome.jpg" />
		</div>
	</section>

	<!-- <div class="container" id="game-section">
		<h2>Genres</h2>
		<main class="grid genre">
			<article>
				<img src="https://picsum.photos/600/400?image=103"
					alt="Sample photo">
				<div class="text">
					<a href="#" class="btn">Action</a>
				</div>
			</article>

			<article>
				<img src="https://picsum.photos/600/400?image=103"
					alt="Sample photo">
				<div class="text">
					<a href="#" class="btn">Adventure</a>
				</div>
			</article>

			<article>
				<img src="https://picsum.photos/600/400?image=1084"
					alt="Sample photo">
				<div class="text">
					<a href="#" class="btn">RPG</a>
				</div>
			</article>

			<article>
				<img src="https://picsum.photos/600/400?image=1033"
					alt="Sample photo">
				<div class="text">
					<a href="#" class="btn">Simulation</a>
				</div>
			</article>

			<article>
				<img src="https://picsum.photos/600/400?image=1043"
					alt="Sample photo">
				<div class="text">
					<a href="#" class="btn">Horror</a>
				</div>
			</article>

			<article>
				<img src="https://picsum.photos/600/400?image=1045"
					alt="Sample photo">
				<div class="text">
					<a href="#" class="btn">Sports</a>
				</div>
			</article>

		</main>
	</div> -->

	<div class="container" id="game-section">
		<h2>Games available</h2>
		<main class="grid game">

			<c:if test="${empty gameList}">
				<p>No games found.</p>
			</c:if>

			<c:if test="${not empty gameList}">
				<c:forEach var="game" items="${gameList}">
					<article>
						<img
							src="${pageContext.request.contextPath}/resources/images/game/${game.imageUrlFromPart}"
							alt="Game photo not available">
						<div class="text">
							<p class="genre-text">${game.gameGenre}</p>
							<p class="game-text">${game.gameTitle}</p>
							<p class="price-text">Rs. ${game.gamePrice}</p>
						</div>
						<div class="text">
							<!-- <a href="#" class="btn">More></a> -->

							<form id="view-${game.gameID}"
								action="<%=contextPath + StringUtil.SERVLET_URL_GAME_DETAILS %>">
								<input type="hidden" name="<%=StringUtil.VIEW_ID %>"
									value="${game.gameID}" />
								<button type="submit" class="btn">View</button>

							</form>
						</div>

					</article>
				</c:forEach>
			</c:if>
		</main>
	</div>
</body>
<%@ include file="/pages/footer.jsp"%>

<script type="text/javascript">
	function view(gameID) {
		document.getElementById("view-" + gameID).submit();
	}
</script>
</html>



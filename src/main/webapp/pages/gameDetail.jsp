<%@page import="util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/gameDetail.css" />
<title>Insert title here</title>
</head>
<jsp:include page="<%=StringUtil.PAGE_URL_HEADER%>" />
<body>
	<main class="container">
		<!-- Left Column / Headphones Image -->
		<div class="left-column">
			<img data-image="black" src="resources/images/game/${game.imageUrlFromPart}"
				alt="image not available">
		</div>


		<!-- Right Column -->
		<div class="right-column">

			<!-- Product Description -->
			<div class="product-description">
				<span class="game-title">Genre: ${game.gameGenre}</span>
				<h1>${game.gameTitle}</h1>
				<p>${game.description}</p>
				<span class="release-date">Release Date: ${game.releaseDate}</span>
			</div>
			<!-- Product Pricing -->
			<div class="product-price">

				<span>Price: Rs. ${game.gamePrice}</span>
				<!-- <a href="#" class="cart-btn">Add to cart</a> -->
			</div>

		</div>



	</main>
</body>
<%@ include file="/pages/footer.jsp"%>

<script type="text/javascript">
	$(document).ready(
			function() {

				$('.color-choose input').on(
						'click',
						function() {
							var headphonesColor = $(this).attr('data-image');

							$('.active').removeClass('active');
							$(
									'.left-column img[data-image = '
											+ headphonesColor + ']').addClass(
									'active');
							$(this).addClass('active');
						});

			});
</script>

</html>
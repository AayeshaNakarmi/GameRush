<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<!-- Coding By CodingNepal - www.codingnepalweb.com -->
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Responsive Footer HTML and CSS | CodingNepal</title>
<!-- Fonts Links For Icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/footer.css" />
</head>
<body>
	<section class="footer">
		<div class="footer-row">
			<div class="footer-col">
				<h4>Explore</h4>
				<p>
					At GameRush, we're more than a platform - we're a passionate gaming
					community. Whether you're casual or competitive, find everything to
					level up your experience with us <br>
					<br>Join us today and embark on a journey to gaming greatness.
					The rush awaits!
				</p>
			</div>
			<div class="footer-col">
				<h4>Info</h4>
				<ul class="links">
					<li><a href="#">Discover</a></li>
					<li><a href="#">Games</a></li>
					<li><a href="#">Game Genres</a></li>
					<!--    <li><a href="#">Service</a></li>
            <li><a href="#">Collection</a></li> -->
				</ul>
			</div>

			<div class="footer-col">
				<h4>Newsletter</h4>
				<p>Stay ahead of the game with our newsletter, delivering a
					weekly dose of gaming news, updates, pro tips, and exclusive offers
					directly to your inbox.</p>

				<form action="#">
					<input type="text" placeholder="Your email" required>
					<button type="submit">SUBSCRIBE</button>
				</form>
				<div class="icons">
					<i class="fa-brands fa-facebook-f"></i> <i
						class="fa-brands fa-twitter"></i> <i class="fa-brands fa-linkedin"></i>
					<i class="fa-brands fa-github"></i>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
<%@page import="util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/registerLogin.css" />

<%
// Get the session and request objects
HttpSession userSession = request.getSession();
String currentUser = (String) userSession.getAttribute(StringUtil.USERNAME);
String contextPath = request.getContextPath();
String userRole = (String) userSession.getAttribute(StringUtil.ROLE);
%>

<title>Insert title here</title>
</head>
<body>
	<nav class="nav transparent" id="navbar">
		<div class="nav-logo">
			<p>GAME RUSH .</p>
		</div>
		<div class="nav-menu" id="navMenu">
			<ul>
			 <% if (userRole != null && userRole.equals("admin")) { %>
                    <li><a href="${pageContext.request.contextPath}/pages/adminTable.jsp" class="link">Dashboard</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/adminTable.jsp#games-table" class="link">Games</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/adminTable.jsp#users-table" class="link">Users</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/addGameForm.jsp" class="link">Add games</a></li>
                <% } else { %>
				<li><a href="${pageContext.request.contextPath}/pages/home.jsp"
					class="link">Discover</a></li>
				<li><a
					href="${pageContext.request.contextPath}/pages/home.jsp#game-section"
					class="link">Games</a></li>
					<% } %>
			<!-- 	<li><a href="#" class="link">Wishlist</a></li>
				<li><a href="#" class="link">Cart</a></li>
				<li><a href="#" class="link">Library</a></li> -->
			</ul>
		</div>

		<div class="user-registration">


			<%-- 		<form action="<%
                    // Conditionally set the action URL based on user session
                    if (currentUser != null) {
                        out.print(contextPath + StringUtils.SERVLET_URL_LOGOUT);
                    } else {
                        out.print(contextPath + StringUtils.PAGE_URL_LOGIN);
                    }
                %>" method="post"></form>
                    <input type="submit" value="<%
                        // Conditionally set the button label based on user session
                        if (currentUser != null) {
                            out.print(StringUtils.LOGOUT);
                        } else {
                            out.print(StringUtils.LOGIN);
                        }
                    %>"/>
		 --%>


			<form
				action="<%if (currentUser != null) {
	out.print(contextPath + StringUtil.SERVLET_URL_LOGOUT);
} else {
	out.print(contextPath + StringUtil.PAGE_URL_LOGIN);
}%>"
				method="post">
				<div class="nav-button">
					<button class="btn white-btn" id="loginBtn"  >
						<%
						if (currentUser != null) {
							out.print(StringUtil.LOGOUT);
						} else {
							out.print(StringUtil.LOGIN);
						}
						%>
					</button>
				</div>
			</form>

			<div class="nav-profile-circle">
				<a href="${pageContext.request.contextPath}/UserProfile"
					class="bx bx-user"></a>
			</div>
			
</div>
			
			

		<div class="nav-menu-btn">
			<i class="bx bx-menu" onclick="myMenuFunction()"></i>
		</div>

	</nav>
</body>

<script type="text/javascript">
	window.addEventListener('scroll', function() {
		var navbar = document.getElementById('navbar');
		var scrollPosition = window.scrollY;

		if (scrollPosition > 100) { // Adjust the threshold as needed
			navbar.classList.add('solid');
			navbar.classList.remove('transparent');
		} else {
			navbar.classList.remove('solid');
			navbar.classList.add('transparent');
		}
	});

	$("a").on("click", function() {
		$("a").removeClass("active");
		$(this).addClass("active");

	});
	
	document.querySelectorAll('.link').forEach(item => {
		  item.addEventListener('click', event => {
		    // Remove 'active' class from all links
		    document.querySelectorAll('.link').forEach(link => {
		      link.classList.remove('active');
		    });
		    // Add 'active' class to the clicked link
		    event.currentTarget.classList.add('active');
		  });
		});
</script>

</html>
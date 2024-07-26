<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="util.StringUtil"%>

<%
String errLoginMsg = (String) request.getAttribute(StringUtil.MESSAGE_ERROR_LOGIN);
String successLoginMsg = (String) request.getAttribute(StringUtil.MESSAGE_SUCCESS);
String errRegisterMsg = (String) request.getAttribute(StringUtil.MESSAGE_ERROR_REGISTER);
String successRegisterMsg = (String) request.getAttribute(StringUtil.MESSAGE_SUCCESS);
String contextPath = request.getContextPath();
String username = (String) request.getAttribute(StringUtil.USERNAME);
String successParam = request.getParameter(StringUtil.SUCCESS);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/registerLogin.css" />
<title>Login & Registration</title>
</head>
<body>
	<jsp:include page="<%=StringUtil.PAGE_URL_HEADER%>" />
	<!-- include directive -->
	<%-- <%@ include file="/pages/header.jsp"%> --%>
	<div class="wrapper">
		<!----------------------------- Form box ----------------------------------->
		<div class="form-box">
			<!------------------- login form -------------------------->

			<div class="login-container" id="login">
				<form action="${pageContext.request.contextPath}/Login"
					method="post">
					<div class="top">
						<span>Don't have an account? <a href="#"
							onclick="register()">Sign Up</a></span>
						<header>Login</header>
					</div>
					<div class="input-box">
						<input type="text" class="input-field" name="loginUserName"
							placeholder="Username"> <i class="bx bx-user"></i>
					</div>
					<div class="input-box">
						<input type="password" class="input-field" name="loginPassword"
							placeholder="Password"> <i class="bx bx-lock-alt"></i>
					</div>


					<%
					if (errLoginMsg != null) {
					%>
					<p class="error-msg">
						 <%= errLoginMsg %>
					</p>
					<%
					}

					if (successParam != null && successParam.equals(StringUtil.TRUE)) {
					%>
					<h2 class="success-msg">Registration Successful!</h2>
					<%
					}
					%>

					<div class="input-box">
						<input type="submit" class="submit" value="Sign In">
					</div>
					<!-- <div class="two-col">
						<div class="one">
							<input type="checkbox" id="login-check"> <label
								for="login-check"> Remember Me</label>
						</div>
						<div class="two">
							<label><a href="#">Forgot password?</a></label>
						</div>
					</div> -->
				</form>
			</div>
			<!------------------- registration form -------------------------->
			<div class="register-container" id="register">
				<form action="${pageContext.request.contextPath}/RegisterUser"
					method="post" enctype="multipart/form-data">
					<div class="top">
						<span>Have an account? <a href="#" onclick="login()">Login</a></span>
						<header>Sign Up</header>
					</div>
					<div class="two-forms">
						<div class="input-box">
							<input type="text" class="input-field" name="firstName"
								placeholder="Firstname*" required> <i class="bx bx-user"></i>
						</div>
						<div class="input-box">
							<input type="text" class="input-field" name="lastName"
								placeholder="Lastname*" required> <i class="bx bx-user"></i>
						</div>
					</div>
					<div class="two-forms">
						<div class="input-box">
							<input type="text" class="input-field" name="userName"
								placeholder="Username*" required> <i class="bx bx-user"></i>
						</div>
						<div class="input-box">
							<input type="text" class="input-field" name="phoneNumber"
								placeholder="Phone"> <i class="bx bx-phone"></i>
						</div>
					</div>


					<div class="two-forms">
						<div class="input-box">
							<input type="text" class="input-field" name="email"
								placeholder="Email*" required> <i class="bx bx-envelope"></i>
						</div>
						<div class="input-box">
							<input type="date" class="input-field" name="dob"
								placeholder="DOB*" required> <i class="bx bx-calendar"></i>
						</div>
					</div>

					<div class="two-forms">
						<div class="input-box">
							<input type="password" class="input-field" name="password"
								placeholder="Password*" required> <i
								class="bx bx-lock-alt"></i>
						</div>
						<div class="input-box">
							<input type="password" class="input-field" name="retypePassword"
								placeholder="Retype password*" required> <i
								class="bx bx-lock-alt"></i>
						</div>
					</div>

					<div class="input-box profile-picture">
						<input type="file" id="image" class="input-field"
							name="profileImage" onchange="displayFileName(this)"> <label
							for="image">Choose profile image</label> <span
							id="fileNameDisplay" class="file-name"></span>
					</div>

					<%
					if (errRegisterMsg != null) {
					%>
					<p class="error-msg">
						 <%= errRegisterMsg %>
					</p>
					<%
					}

					if (successRegisterMsg != null) {
					// print
					%>
					<p class="success-msg">
						<%
						out.println(successRegisterMsg);
						%>
					</p>
					<%
					}
					%>

					<div class="input-box">
						<input type="submit" class="submit" value="Register">
					</div>
					<!-- 				<div class="two-col">
						<div class="one">
							<input type="checkbox" id="register-check"> <label
								for="register-check"> Remember Me</label>
						</div>
						<div class="two">
							<label><a href="#">Terms & conditions</a></label>
						</div>
					</div> -->
				</form>
			</div>
		</div>

	</div>
	<script>
		function myMenuFunction() {
			var i = document.getElementById("navMenu");
			if (i.className === "nav-menu") {
				i.className += " responsive";
			} else {
				i.className = "nav-menu";
			}
		}
	</script>
	<script>
		// Variable declarations
		var a = document.getElementById("loginBtn");
		var b = document.getElementById("registerBtn");
		var x = document.getElementById("login");
		var y = document.getElementById("register");

		// login function definition
		function login() {
			x.style.left = "4px";
			y.style.right = "-520px";
			a.className += " white-btn";
			b.className = "btn";
			x.style.opacity = 1;
			y.style.opacity = 0;
		}

		// register function definition
		function register() {
			x.style.left = "-510px";
			y.style.right = "5px";
			a.className = "btn";
			b.className += " white-btn";
			x.style.opacity = 0;
			y.style.opacity = 1;
		}

		// Function to display file name
		function displayFileName(input) {
			var fileNameDisplay = document.getElementById('fileNameDisplay');
			fileNameDisplay.textContent = input.files[0].name;
		}
		
<%-- 	    // Check if scrollTo attribute is set
	    var scrollTo = '<%=request.getAttribute("scrollTo")%>
		';
		if (scrollTo) {
			// Scroll to the specified section instantly
			var element = document.getElementById(scrollTo);
			if (element) {
				element.scrollIntoView();
			}
		}  --%>
	</script>

	<!-- include action tag	 -->
	<jsp:include page="/pages/footer.jsp"></jsp:include>

</body>
</html>
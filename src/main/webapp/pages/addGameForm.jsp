<%@page import="util.StringUtil"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(StringUtil.MESSAGE_ERROR);
String successMsg = (String) request.getAttribute(StringUtil.MESSAGE_SUCCESS);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/productForm.css" />
<title>Insert title here</title>
</head>
<jsp:include page="<%=StringUtil.PAGE_URL_HEADER%>" />
<body>
	<div class="main-wrapper">
		<!-- Author: Your Name -->
		<div class="form-wrapper">
			<h2>Add game</h2>
			<%
			if (errMsg != null) {
			%>
			<p class="error-msg">
				<%=errMsg%>
			</p>
			<%
			}

			if (successMsg != null) {
			// print
			%>
			<p class="success-msg">
				<%=successMsg%>
			</p>
			<%
			}
			%>
			
			<form action="<%=contextPath + StringUtil.SERVLET_URL_ADD_GAME%>" method="POST" enctype="multipart/form-data">

				<div class="mb-3">
					<label for="gameID" class="form-label">Game ID</label> <input
						type="text" name="gameID" id="gameID" placeholder="Enter Game ID"
						class="form-input" />
				</div>
				<div class="input-flex">
					<div class="mb-3">
						<label for="gameTitle" class="form-label">Game Title</label> <input
							type="text" name="gameTitle" id="gameTitle"
							placeholder="Enter Game Title" class="form-input" />
					</div>
					<div class="mb-3">
						<label for="gamePrice" class="form-label">Price</label> <input
							type="text" name="gamePrice" id="gamePrice" placeholder="In NPR"
							class="form-input" />
					</div>

				</div>

				<div class="input-flex">
					<div class="mb-3">

						<%
						String defaultDate = LocalDate.now().toString();
						%>
						<label for="releaseDate" class="form-label">Release date</label> <input
							type="date" name="releaseDate" id="releaseDate"
							class="form-input" value="<%=defaultDate%>" readonly />
					</div>

					<div class="mb-3">
						<label for="gameGenre" class="form-label">Genre</label> <select
							class="form-input" name="gameGenre" id="gameGenre">
							<option value="Action">Action</option>
							<option value="Adventure">Adventure</option>
							<option value="RPG">RPG</option>
							<option value="Horror">Horror</option>
							<option value="Simulation">Simulation</option>
							<option value="Sports">Sports</option>
							<option value="Survival">Survival</option>
							<option value="Other">Other</option>
						</select>
					</div>
				</div>

				<div class="mb-3">
					<label for="gamePicture" class="form-label">Game picture</label> <input
						type="file" name="gameImage" id="gamePicture"
						class="form-input form-file" />
				</div>

				<div class="mb-3">
					<label for="comments" class="form-label">Game description</label>
					<textarea rows="6" name="description" id="description"
						placeholder="Enter game description.." class="form-input"></textarea>
				</div>

				<button class="add-game-btn">Add Game</button>
			</form>
		</div>
	</div>
</body>
</html>

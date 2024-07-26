<%@page import="util.StringUtil"%>
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
	href="<%=contextPath%>/stylesheets/productForm.css" />
<title>Insert title here</title>
</head>
<jsp:include page="<%=StringUtil.PAGE_URL_HEADER%>" />
<body>
	<div class="main-wrapper">
		<!-- Author: Your Name -->
		<div class="form-wrapper">
			<h2>Edit game</h2>
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
			<form id="updateForm-${game.gameID}"
				action="<%=contextPath + StringUtil.SERVLET_URL_MODIFY_GAME%>"
				method="post" enctype="multipart/form-data">
				<div class="mb-3">
					<label for="gameId" class="form-label">Game ID</label> <input
						type="text" name="gameID" id="gameId" placeholder="Enter Game ID"
						class="form-input" value="${game.gameID}" readonly />
				</div>
				<div class="input-flex">
					<div class="mb-3">
						<label for="gameTitle" class="form-label">Game Title</label> <input
							type="text" name="gameTitle" id="gameTitle"
							placeholder="Enter Game Title" class="form-input"
							value="${game.gameTitle}" />
					</div>
					<div class="mb-3">
						<label for="gamePrice" class="form-label">Price</label> <input
							type="text" name="gamePrice" id="gamePrice" placeholder="In NPR"
							class="form-input" value="${game.gamePrice}" />
					</div>
				</div>

				<div class="input-flex">
					<div class="mb-3">
						<label for="releaseDate" class="form-label">Release date</label> <input
							type="date" name="releaseDate" id="releaseDate"
							class="form-input" value="${game.releaseDate}" />
					</div>

					<div class="mb-3">
						<label for="gameGenre" class="form-label">Genre</label> <select
							class="form-input" name="gameGenre" id="gameGenre">
							<option value="Action"
								${game.gameGenre == 'Action' ? 'selected' : ''}>Action</option>
							<option value="Adventure"
								${game.gameGenre == 'Adventure' ? 'selected' : ''}>Adventure</option>
							<option value="RPG" ${game.gameGenre == 'RPG' ? 'selected' : ''}>RPG</option>
							<option value="Horror"
								${game.gameGenre == 'Horror' ? 'selected' : ''}>Horror</option>
							<option value="Simulation"
								${game.gameGenre == 'Simulation' ? 'selected' : ''}>Simulation</option>
							<option value="Sports"
								${game.gameGenre == 'Sports' ? 'selected' : ''}>Sports</option>
							<option value="Survival"
								${game.gameGenre == 'Survival' ? 'selected' : ''}>Survival</option>
							<option value="Other"
								${game.gameGenre == 'Other' ? 'selected' : ''}>Other</option>
						</select>
					</div>
				</div>
				<div class="mb-3">
					<label for="gamePicture" class="form-label">Current Game
						Picture</label> <br> <img src="resources/images/game/${game.imageUrlFromPart}"
						alt="Current Game Picture" width="200">
					<!-- Assuming game.imageUrl contains the URL -->
				</div>
				<input type="hidden" name="existingImageUrl" value="${game.imageUrlFromPart}">

				<div class="mb-3">
					<label for="gameImage" class="form-label">Change Game picture</label> <input
						type="file" name="gameImage" id="gamePicture"
						class="form-input form-file" />
				</div>

				<div class="mb-3">
					<label for="comments" class="form-label">Game description</label>
					<textarea rows="6" name="description" id="description"
						placeholder="Enter game description.." class="form-input">${game.description}</textarea>
				</div>

				<input type="hidden" name="<%=StringUtil.UPDATE_ID%>"
					value="${game.gameID}" />
				<button class="add-game-btn" id="update-button" type="button"
					onclick="update('${game.gameID}', '${game.gameTitle}')">Update
					Game</button>

			</form>
		</div>
	</div>
	<script type="text/javascript">
		function update(gameID, gameTitle) {
			if (confirm("Are you sure you want to update the game: "
					+ gameTitle + "?")) {
				document.getElementById("updateForm-" + gameID).submit();
			}
		}
	</script>


</body>
</html>
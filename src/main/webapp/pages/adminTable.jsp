<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.UserModel"%>
<%@ page import="model.GameModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.StringUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/adminTable.css">
<title>Insert title here</title>
</head>
<jsp:include page="<%=StringUtil.PAGE_URL_HEADER%>" />
<body>

	<div class="info-container">
		<div class="info-section" id="user-count-section">
			<h3>
				Total Users<br /> <span id="count"><%= request.getAttribute(StringUtil.USER_COUNT) %></span>
			</h3>
		</div>

		<div class="info-section" id="product-count-section">
			<h3>
				Total Products <br /> <span id="count"><%=
					request.getAttribute(StringUtil.GAME_COUNT) %></span>
			</h3>
		</div>
	</div>

	<div class="table-container" id="games-table">
		<div class="header-container">
			<h2>Games</h2>

			<a href="${pageContext.request.contextPath}/pages/addGameForm.jsp">
				<button class="action-button" id="add-button">Add Game</button>
			</a>
		</div>

		<table>
			<thead>
				<tr>
					<th scope="col">Game ID</th>
					<th scope="col">Title</th>
					<th scope="col">Genre</th>
					<th scope="col">Release Date</th>
					<th scope="col">Price (in NPR)</th>
					<th class="button-cell"></th>
				</tr>
			</thead>
			<tbody>
			<tbody>
				<c:if test="${empty gameList}">
					<p>No games found.</p>
				</c:if>

				<c:if test="${not empty gameList}">
					<c:forEach var="game" items="${gameList}">
						<tr>
							<td data-label="Game ID">${game.gameID}</td>
							<td data-label="Title">${game.gameTitle}</td>
							<td data-label="Genre">${game.gameGenre}</td>
							<td data-label="Release Date">${game.releaseDate}</td>
							<td data-label="Price">Rs.${game.gamePrice}</td>

							<td class="button-cell">
								<div class="button-group">

									<form id="updateForm-${game.gameID}"
										action="<%=contextPath + StringUtil.SERVLET_URL_MODIFY_GAME %>">
										<input type="hidden" name="<%=StringUtil.UPDATE_ID %>"
											value="${game.gameID}" />
										<button class="action-button" id="update-button" type="submit"
											onclick="update('${game.gameID}')">Edit</button>
									</form>

									<form id="deleteForm-${game.gameID}" method="post"
										action="<%=contextPath + StringUtil.SERVLET_URL_MODIFY_GAME %>">
										<input type="hidden" name="<%=StringUtil.DELETE_ID %>"
											value="${game.gameID}" />

										<button class="action-button" id="delete-button" type="button"
											onclick="confirmDelete('${game.gameID}','${game.gameTitle}')">Delete</button>
									</form>

									<form id="view-${game.gameID}"
										action="<%=contextPath + StringUtil.SERVLET_URL_GAME_DETAILS %>">
										<input type="hidden" name="<%=StringUtil.VIEW_ID %>"
											value="${game.gameID}" />

										<button class="view-button">View</button>
									</form>
								</div>

							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>


	<div class="table-container" id="users-table">
		<div class="header-container">
			<h2>Users</h2>
		</div>
		<table>
			<thead>
				<tr>
					<th scope="col">Username</th>
					<th scope="col">Email</th>
					<th scope="col">First name</th>
					<th scope="col">Last name</th>
					<th scope="col">DOB</th>
					<th scope="col">Phone number</th>
				</tr>
			</thead>
			<%-- <sql:query var="users" dataSource="${dbConnection}">
			SELECT * FROM user
			</sql:query> --%>
			<tbody>
				<%-- 	<c:forEach var="user" items="${users.rows}"> --%>
				<c:if test="${empty userList}">
					<p>No users found.</p>
				</c:if>

				<c:if test="${not empty userList}">
					<c:forEach var="user" items="${userList}">
						<tr>
							<td data-label="Username">${user.userName}</td>
							<td data-label="Email">${user.email}</td>
							<td data-label="First Name">${user.firstName}</td>
							<td data-label="Last Name">${user.lastName}</td>
							<td data-label="DOB">${user.dob}</td>
							<td data-label="Phone Number">${user.phoneNumber}</td>
						</tr>
					</c:forEach>
				</c:if>
				<%-- </c:forEach> --%>
			</tbody>
		</table>
	</div>
</body>

<script type="text/javascript">
	function confirmDelete(gameID, gameTitle) {
		  if (confirm("Are you sure you want to delete the game: " + gameTitle + "?")) {
		    document.getElementById("deleteForm-" + gameID).submit();
		  }
		}
	function update(gameID) {
	    document.getElementById("updateForm-" + gameID).submit();
	}

</script>
</html>
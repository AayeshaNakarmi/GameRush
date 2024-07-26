<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/userProfile.css">
<title>User Profile</title>
</head>
<body>
<body>
	<%@ include file="/pages/header.jsp"%>

	<div class="container-box">
		<div class="container">
			<div class="sidebar">
				<h2>Menu</h2>
				<ul>
					<li><button id="profile-btn" class="tab-btn active">Profile</button></li>
					<li><button id="change-password-btn" class="tab-btn">Change
							Password</button></li>
				</ul>
			</div>
			<div class="content">
				<div id="profile" class="tab">
					<div class="profile">
						<div class="avatar">
							<img src="resources/images/user/${userModel.imageUrlFromPart}"
								alt="Profile Image"> <input type="file" id="avatar-input"
								class="hidden-file-input" onchange="displayFileName()">


							<div class="row">

								<br> <label for="avatar-input" class="upload-avatar-btn">Change
									Image</label> <span id="file-name" class="file-name"></span>

								<form id="deleteForm-${userModel.userName}" method="post"
									action="<%=contextPath + StringUtil.SERVLET_URL_PROFILE %>">
									<input type="hidden" name="<%=StringUtil.DELETE_ID %>"
										value="${game.gameID}" />

									<button class="delete-btn" id="delete-button" type="button"
										onclick="confirmDelete('${userModel.userName}')">Delete</button>
								</form>
							</div>

						</div>
						<div class="profile-info">
							<div class="profile-header">
								<h2 class="profile-header">User Profile</h2>
								<!-- <button id="edit-profile-btn" class="edit-profile-btn">Edit
									Profile</button> -->
							</div>
							<c:if test="${empty userModel}">
								<p>No user logged in.</p>
							</c:if>
							<c:if test="${not empty userModel}">

								<form id="updateForm-${userModel.userName}"
									action="<%=contextPath + StringUtil.SERVLET_URL_PROFILE%>"
									method="POST">
									<div class="row">
										<div class="form-group">
											<label for="username">Username</label> <input type="text"
												id="username" name="userName" value="${userModel.userName}"
												readonly>

										</div>
										<div class="form-group">
											<label for="email">Email</label> <input type="email"
												id="email" name="email" value="${userModel.email}" readonly>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label for="firstName">First Name</label> <input type="text"
												id="firstName" name="firstName"
												value="${userModel.firstName}">
										</div>
										<div class="form-group">
											<label for="lastName">Last Name</label> <input type="text"
												id="lastName" name="lastName" value="${userModel.lastName}">
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label for="dob">Date of Birth</label> <input type="date"
												id="dob" name="dob" value="${userModel.dob}">
										</div>
										<div class="form-group">
											<label for="phoneNumber">Phone Number</label> <input
												type="text" id="phoneNumber" name="phoneNumber"
												value="${userModel.phoneNumber}" readonly>
										</div>
									</div>


									<input type="hidden" name="<%=StringUtil.UPDATE_ID %>"
										value="${userModel.userName}" />
									<div class="form-group">
										<button onclick="update('${usermodel.userName}')"
											type="submit" class="update-btn">Update Profile</button>
									</div>
								</form>



							</c:if>
						</div>
					</div>
				</div>
				<div id="change-password" class="tab">
					<form id="password-form">
						<div class="form-group">
							<label for="old-password">Old Password:</label> <input
								type="password" id="old-password" name="old-password">
						</div>
						<div class="row">
							<div class="form-group">
								<label for="new-password">New Password:</label> <input
									type="password" id="new-password" name="new-password">
							</div>
							<div class="form-group">
								<label for="confirm-password">Confirm New Password:</label> <input
									type="password" id="confirm-password" name="confirm-password">
							</div>
						</div>

						<div class="form-group">
							<button type="submit" class="update-btn">Change Password</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
        // JavaScript code to handle tab navigation
        document.addEventListener("DOMContentLoaded", function() {
            const tabs = document.querySelectorAll('.tab');
            const links = document.querySelectorAll('.sidebar ul li button');

            links.forEach(link => {
                link.addEventListener('click', () => {
                    const target = link.getAttribute('id').replace("-btn", "");
                    tabs.forEach(tab => {
                        if (tab.id === target) {
                            tab.style.display = 'block';
                        } else {
                            tab.style.display = 'none';
                        }
                    });
                });
            });

            // JavaScript code to handle profile edit functionality
            const editProfileBtn = document.getElementById('edit-profile-btn');
            const profileForm = document.getElementById('update-form');
            const profileInputs = profileForm.querySelectorAll('input, textarea');
            const updateBtn = profileForm.querySelector('.update-btn');

            editProfileBtn.addEventListener('click', () => {
                profileInputs.forEach(input => {
                    input.removeAttribute('readonly');
                });
                updateBtn.removeAttribute('disabled');
            });
        });
        
        function displayFileName() {
            const fileInput = document.getElementById('avatar-input');
            const fileNameSpan = document.getElementById('file-name');
            if (fileInput.files.length > 0) {
                fileNameSpan.textContent = fileInput.files[0].name;
            } else {
                fileNameSpan.textContent = '';
            }
        }
        
        
    	function update(username) {
    		if (confirm("Are you sure you want to update your profile?")){
    			   document.getElementById("updateForm-" + username).submit();
    		}
    	}
    </script>
</body>

</body>
</html>
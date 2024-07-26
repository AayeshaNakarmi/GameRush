package model;

import java.io.File;
import java.time.LocalDate;
import javax.servlet.http.Part;
import util.StringUtil;

public class UserModel {
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String phoneNumber;
	private String role;
	private String password;
	private String imageUrlFromPart;

	public UserModel() {}

	public UserModel(String userName, String email, String firstName, String lastName, LocalDate dob, String phoneNumber, String role, String password, Part imagePart) {
		super();
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.password = password;
		this.imageUrlFromPart = getImageUrl(imagePart);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}

	public void setImageUrlFromPart(String imageUrlFromPart) {
		this.imageUrlFromPart = imageUrlFromPart;
	}

	private String getImageUrl(Part part) {
		String savePath = StringUtil.IMAGE_DIR_USER;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;

		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");

		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
				break;
			}
		}

		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "default_user.jpg";
		}

		return imageUrlFromPart;
	}
}
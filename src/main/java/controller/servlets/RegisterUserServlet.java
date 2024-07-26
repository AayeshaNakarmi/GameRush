package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.UserModel;
import util.StringUtil;
import util.ValidationUtil;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterUser" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUserServlet() {
		this.dbController = new DBController();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter(StringUtil.USERNAME);
		String email = request.getParameter(StringUtil.EMAIL);
		String firstName = request.getParameter(StringUtil.FIRST_NAME);
		String lastName = request.getParameter(StringUtil.LAST_NAME);
		String dobString = request.getParameter(StringUtil.DOB);
		LocalDate dob = LocalDate.parse(dobString);
		String phoneNumber = request.getParameter(StringUtil.PHONE_NUMBER);
		String role = "user";
		if (userName.equals("admin")) {
			role = "admin";
		}
		String password = request.getParameter(StringUtil.PASSWORD);
		String retypePassword = request.getParameter(StringUtil.RETYPE_PASSWORD);
		Part imagePart = request.getPart("profileImage");

		if (!ValidationUtil.isTextOnly(firstName)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_INVALID_FIRST_NAME);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}

		if (!ValidationUtil.isTextOnly(lastName)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_INVALID_LAST_NAME);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}

		if (!ValidationUtil.isAlphanumeric(userName)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_INVALID_USER_NAME);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}

		if (dbController.checkUsernameExists(userName)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_USERNAME);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);

			return;
		}

		if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_INVALID_PHONE_NUMBER);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}

		if (dbController.checkPhoneNumberExists(phoneNumber)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_PHONE_NUMBER);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}

		if (!ValidationUtil.isEmail(email)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_INVALID_EMAIL);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}

		if (dbController.checkEmailExists(email)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_EMAIL);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}

		if (!ValidationUtil.isAtLeast13YearsOld(dob)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_AGE_RESTRICTION);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}

		if (!ValidationUtil.isValidPassword(password)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_INVALID_PASSWORD);
			request.setAttribute("scrollTo", "register"); 
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}
		if (!ValidationUtil.isImage(imagePart.getSubmittedFileName())) {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_INVALID_IMAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		}

		UserModel newUser = new UserModel(userName, email, firstName, lastName, dob, phoneNumber, role, password, imagePart);

		int result = dbController.registerUser(newUser);

		if (password.equals(retypePassword)) {
			if (result == 1)  {
				String fileName = newUser.getImageUrlFromPart();



				if (!fileName.isEmpty() && fileName != null) {
					String savePath = StringUtil.IMAGE_DIR_USER;
					imagePart.write(savePath + fileName);
				}
				request.setAttribute(StringUtil.MESSAGE_SUCCESS, StringUtil.MESSAGE_SUCCESS_REGISTER);
				response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_LOGIN);
			}
			else if (result == 0) {
				request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_REGISTER);
				request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			} else {
				request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_SERVER);
				request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			}
		}
		else {
			request.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_PASSWORD_UNMATCHED);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
		}
	}
}


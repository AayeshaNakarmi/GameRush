package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.UserModel;
import util.StringUtil;
import util.ValidationUtil;

/**
 * Servlet implementation class UserProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserProfile" })
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBController dbController = new DBController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfileServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("userName") != null) {
			String username = (String) session.getAttribute("userName");
			UserModel userDetails = dbController.getUserProfileInfo(username);

			request.setAttribute("userModel", userDetails);
			request.getRequestDispatcher(StringUtil.PAGE_URL_USER_PROFILE).forward(request, response);		
		} 

		else {
			//			//			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);	
			response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_LOGIN);
		}
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String updateId = req.getParameter(StringUtil.UPDATE_ID);
		System.out.println(updateId);

		String deleteId = req.getParameter(StringUtil.DELETE_ID);

		if (updateId != null && !updateId.isEmpty()) {
			doPut(req, resp);
		}

		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(req, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String userName=req.getParameter(StringUtil.USERNAME);
		String email=req.getParameter(StringUtil.EMAIL);
		String firstName=req.getParameter(StringUtil.FIRST_NAME);
		String lastName=req.getParameter(StringUtil.LAST_NAME);
		LocalDate dob=LocalDate.parse(req.getParameter(StringUtil.DOB));
		String phoneNumber=req.getParameter(StringUtil.PHONE_NUMBER);

		if (!ValidationUtil.isTextOnly(firstName)) {
			req.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_INVALID_FIRST_NAME);
			req.setAttribute("scrollTo", "register"); 
			req.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(req, resp);
			return;
		}
		
		if (!ValidationUtil.isTextOnly(lastName)) {
			req.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_INVALID_LAST_NAME);
			req.setAttribute("scrollTo", "register"); 
			req.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(req, resp);
			return;
		}
		
		if (!ValidationUtil.isAtLeast13YearsOld(dob)) {
			req.setAttribute(StringUtil.MESSAGE_ERROR_REGISTER, StringUtil.MESSAGE_ERROR_AGE_RESTRICTION);
			req.setAttribute("scrollTo", "register"); 
			req.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(req, resp);
			return;
		}

		UserModel updatedUserDetail=new UserModel();
		updatedUserDetail.setUserName(userName);
		updatedUserDetail.setEmail(email);
		updatedUserDetail.setFirstName(firstName);
		updatedUserDetail.setLastName(lastName);
		updatedUserDetail.setDob(dob);
		updatedUserDetail.setPhoneNumber(phoneNumber);

		HttpSession session = req.getSession();

		if(dbController.updateProfile(updatedUserDetail)==1) {
			session.setAttribute(StringUtil.MESSAGE_SUCCESS, StringUtil.MESSAGE_SUCCESS_UPDATE);
		}
		else {
			session.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_UPDATE);
		}
		resp.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_ADMIN_TABLE);
		return;
	}
}

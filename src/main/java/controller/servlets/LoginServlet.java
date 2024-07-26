package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.LoginModel;
import util.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController Controller;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		this.Controller = new DBController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession(false);
		    if (session != null && session.getAttribute("userName") != null) {
		        response.sendRedirect(request.getContextPath() + "/pages/profile.jsp");
		    } 
//		    else {
//		        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
//		    }
//		    return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("loginUserName");
		String password = req.getParameter("loginPassword");

		LoginModel loginModel=new LoginModel(userName, password);
		int loginResult = Controller.getUserLoginInfo(loginModel);
		System.out.println("Login result "+loginResult);

		if (loginResult == 1) {
			HttpSession userSession=req.getSession();
			userSession.setAttribute("userName", userName);
			userSession.setMaxInactiveInterval(15*60);

			Cookie userCookie=new Cookie("user",userName);
			userCookie.setMaxAge(30*60);
			resp.addCookie(userCookie);
			
//			HttpSession session = req.getSession();
//			session.setAttribute("successMessage", StringUtil.MESSAGE_SUCCESS_LOGIN);
//			response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_HOME);

			
//			 req.setAttribute(StringUtil.MESSAGE_SUCCESS,StringUtil.MESSAGE_SUCCESS_LOGIN);
			 	
			   // Check if the user is an admin
		        boolean isAdmin = Controller.isAdmin(userName);
		        
		        // Set the role attribute based on user's role
		        String role = isAdmin ? "admin" : "user";
		        userSession.setAttribute("role", role);

		        // Redirect the user based on their role
		        if (isAdmin) {
		        	resp.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_ADMIN_TABLE + "?message=" + StringUtil.MESSAGE_SUCCESS_LOGIN);
		        } else {
		        	resp.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_HOME + "?message=" + StringUtil.MESSAGE_SUCCESS_LOGIN);
//		            req.getRequestDispatcher(StringUtil.PAGE_URL_HOME).forward(req, resp);
		        }
		} else if (loginResult == 0) {
			req.setAttribute(StringUtil.MESSAGE_ERROR_LOGIN, StringUtil.MESSAGE_ERROR_INCORRECT_CREDENTIALS);
			req.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(req, resp);	
		} 
		else if (loginResult == -1) {
			req.setAttribute(StringUtil.MESSAGE_ERROR_LOGIN, StringUtil.MESSAGE_ERROR_CREATE_ACCOUNT);
			req.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(req, resp);
		} 
		else {
			req.setAttribute(StringUtil.MESSAGE_ERROR_LOGIN, StringUtil.MESSAGE_ERROR_SERVER);
			req.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(req, resp);
		}
//		return;
	}
}

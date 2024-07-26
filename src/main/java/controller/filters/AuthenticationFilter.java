package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtil;

public class AuthenticationFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Cast the request and response to HttpServletRequest and HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// Get the requested URI
		String uri = req.getRequestURI();


		if(uri.endsWith(StringUtil.PAGE_URL_HOME) || uri.endsWith("/")) {
			request.getRequestDispatcher(StringUtil.SERVLET_URL_HOME).forward(request, response);
			return;

			////	        res.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_HOME);			return;
		}

		if(uri.endsWith(StringUtil.PAGE_URL_ADMIN_TABLE)) {
			request.getRequestDispatcher(StringUtil.SERVLET_URL_ADMIN_DASHBOARD).forward(request, response);	    	
			return;
		}

		// Allow access to static resources (CSS) and the index page without checking login
		if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
			chain.doFilter(request, response);
			return;
		}

		//		if(uri.endsWith(StringUtil.SERVLET_URL_LOGOUT)) {
		//			resp.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_HOME);
		//			return;
		//		}


		// Separate flags for login, login/logout servlets, and register page/servlet for better readability
		boolean isLoginRegister = uri.endsWith(StringUtil.PAGE_URL_LOGIN);
		boolean isLoginServlet = uri.endsWith(StringUtil.SERVLET_URL_LOGIN);
		boolean isLogoutServlet = uri.endsWith(StringUtil.SERVLET_URL_LOGOUT);
		boolean isHomeServlet = uri.endsWith(StringUtil.SERVLET_URL_HOME);
		boolean isRegisterServlet = uri.endsWith(StringUtil.SERVLET_URL_REGISTER);

		// Check if a session exists and if the username attribute is set to determine login status
		HttpSession session = req.getSession(false); // Don't create a new session if one doesn't exist
		boolean isLoggedIn = session != null && session.getAttribute(StringUtil.USERNAME) != null;

		// Redirect to login if user is not logged in and trying to access a protected resource
		//		if (!isLoggedIn && !uri.endsWith(StringUtil.PAGE_URL_LOGIN) && !uri.endsWith(StringUtil.SERVLET_URL_LOGIN) && !uri.endsWith(StringUtil.SERVLET_URL_REGISTER)) {
		//			resp.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_LOGIN);
		//		return;
		//		}

		// Redirect logged-in users to the index page if trying to access login page again
		//		if (isLoggedIn && (uri.endsWith(StringUtil.PAGE_URL_LOGIN) || uri.endsWith(StringUtil.SERVLET_URL_LOGIN))) {
		//			resp.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_HOME);
		//			return;		}
		//
		//		// Only allow admin users to access the admin page
		//		if (isLoggedIn && uri.endsWith(StringUtil.PAGE_URL_ADMIN_TABLE) && !session.getAttribute("role").equals("admin")) {
		//			resp.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_HOME);
		//			return;
		//		}

		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
	}

}
package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.GameModel;
import util.StringUtil;

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/HomePage" })
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBController dbController = new DBController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<GameModel> games = dbController.getGameInfo();
		request.setAttribute(StringUtil.GAME_LIST, games);

//		response.sendRedirect(request.getContextPath()+ StringUtil.PAGE_URL_HOME);
		request.getRequestDispatcher(StringUtil.PAGE_URL_HOME).forward(request, response); 
		return;
	}
}

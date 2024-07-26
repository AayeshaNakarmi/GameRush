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
import model.UserModel;
import util.StringUtil;

/**
 * Servlet implementation class UserInfoServlet
 * 
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/AdminDash" })
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBController dbController=new DBController();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		ArrayList<UserModel> users = dbController.getUserInfo();

		request.setAttribute(StringUtil.USER_LIST, users);

		ArrayList<GameModel> games = dbController.getGameInfo();
		request.setAttribute(StringUtil.GAME_LIST, games);
		
		int userCount=dbController.getTotalUsers();
		request.setAttribute(StringUtil.USER_COUNT, userCount);
		
		int gameCount=dbController.getTotalGames();
		request.setAttribute(StringUtil.GAME_COUNT, gameCount);

		request.getRequestDispatcher(StringUtil.PAGE_URL_ADMIN_TABLE).forward(request, response);
	}
}
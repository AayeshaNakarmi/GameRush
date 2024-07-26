package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.GameModel;
import util.StringUtil;

/**
 * Servlet implementation class GameDetailsServlet
 */
@WebServlet(asyncSupported = true, name = "GameDetails", urlPatterns = { "/GameDetails" })
public class GameDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameDetailsServlet() {
    	this.dbController = new DBController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewId = request.getParameter(StringUtil.VIEW_ID);

		GameModel gameDetail = dbController.getGameInfoByID(Integer.parseInt(viewId));
		request.setAttribute("game", gameDetail);
		request.getRequestDispatcher(StringUtil.PAGE_URL_GAME_DETAILS).forward(request, response);
	}
}

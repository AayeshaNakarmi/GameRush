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
import model.GameModel;
import util.StringUtil;
import util.ValidationUtil;


/**
 * Servlet implementation class ModifyGameServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ModifyGame" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class ModifyGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyGameServlet() {
		this.dbController = new DBController();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String updateId = req.getParameter(StringUtil.UPDATE_ID);

		GameModel gameDetail = dbController.getGameInfoByID(Integer.parseInt(updateId));

		req.setAttribute("game", gameDetail);
		req.getRequestDispatcher(StringUtil.PAGE_URL_UPDATE_GAME_FORM).forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateId = request.getParameter(StringUtil.UPDATE_ID);
		String deleteId = request.getParameter(StringUtil.DELETE_ID);

		if (updateId != null && !updateId.isEmpty()) {
				doPut(request, response);
			}

		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gameID=req.getParameter(StringUtil.GAME_ID);
		String updatedGameTitle=req.getParameter(StringUtil.GAME_TITLE);
		String updatedGameGenre=req.getParameter(StringUtil.GAME_GENRE);
		LocalDate updatedReleaseDate =LocalDate.parse(req.getParameter(StringUtil.RELEASE_DATE));
		String updatedGamePrice=req.getParameter(StringUtil.GAME_PRICE);;
		String updatedDescription=req.getParameter(StringUtil.DESCRIPTION);
		Part updatedImageGamePart = req.getPart(StringUtil.GAME_IMAGE);

		if (!ValidationUtil.isNumbersOnly(gameID)) {
			req.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_INVALID_GAME_TITLE);
			req.getRequestDispatcher(StringUtil.PAGE_URL_UPDATE_GAME_FORM).forward(req, resp);
			return;
		}

		if (!ValidationUtil.isPositiveFloatOnly(updatedGamePrice)) {
			req.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_INVALID_GAME_TITLE);
			req.getRequestDispatcher(StringUtil.PAGE_URL_UPDATE_GAME_FORM).forward(req, resp);
			return;
		}
		GameModel updatedGame=new GameModel();
		updatedGame.setGameID(gameID);
		updatedGame.setGameTitle(updatedGameTitle);
		updatedGame.setGameGenre(updatedGameGenre);
		updatedGame.setReleaseDate(updatedReleaseDate);
		updatedGame.setGamePrice(updatedGamePrice);
		updatedGame.setDescription(updatedDescription);
		if (updatedImageGamePart!=null){
			updatedGame.setImageUrlFromPart(updatedImageGamePart.getSubmittedFileName());
		}
		else {
			updatedGame.setImageUrlFromPart(req.getParameter("existingImageUrl"));
		}
		System.out.println(dbController.updateGame(updatedGame));
		if (dbController.updateGame(updatedGame) == 1) {
			 req.setAttribute(StringUtil.MESSAGE_SUCCESS, StringUtil.MESSAGE_SUCCESS_UPDATE);
			    resp.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_ADMIN_TABLE);
			    return;
			}
		else if (dbController.updateGame(updatedGame) == 0) {
			req.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_UPDATE);
			req.getRequestDispatcher(StringUtil.PAGE_URL_UPDATE_GAME_FORM).forward(req, resp);
		} else {
			req.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_SERVER);
			req.getRequestDispatcher(StringUtil.PAGE_URL_UPDATE_GAME_FORM).forward(req, resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");
		if (dbController.deleteGame(Integer.parseInt(req.getParameter(StringUtil.DELETE_ID)))==1) {
			req.setAttribute(StringUtil.MESSAGE_SUCCESS, StringUtil.MESSAGE_SUCCESS_DELETE);
		} else {
			req.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_DELETE);
		}	
		 resp.sendRedirect(req.getContextPath() + StringUtil.PAGE_URL_ADMIN_TABLE); return; 
	}
}

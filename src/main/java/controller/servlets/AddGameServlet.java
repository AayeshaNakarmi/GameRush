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
 * Servlet implementation class AddGameServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddGame" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class AddGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddGameServlet() {
		this.dbController = new DBController();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String gameTitle=request.getParameter(StringUtil.GAME_TITLE);
		String gameID=request.getParameter(StringUtil.GAME_ID);
		String gameGenre=request.getParameter(StringUtil.GAME_GENRE);
		LocalDate releaseDate =LocalDate.parse(request.getParameter(StringUtil.RELEASE_DATE));
		String gamePrice=request.getParameter(StringUtil.GAME_PRICE);
		String description=request.getParameter(StringUtil.DESCRIPTION);
		Part imagePart = request.getPart(StringUtil.GAME_IMAGE);

		if (!ValidationUtil.isNumbersOnly(gameID)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_INVALID_GAME_ID);
			request.getRequestDispatcher(StringUtil.PAGE_URL_ADD_GAME_FORM).forward(request, response);
			return;
		}

		if (!ValidationUtil.isAlphanumericSpace(gameTitle)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_INVALID_GAME_TITLE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_ADD_GAME_FORM).forward(request, response);
			return;
		}

		if (!ValidationUtil.isPositiveFloatOnly(gamePrice)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_INVALID_GAME_PRICE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_ADD_GAME_FORM).forward(request, response);
			return;
		}

		if (dbController.checkGameIDExists(gameID)) {
			request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_GAME_ID_EXISTS);
			request.getRequestDispatcher(StringUtil.PAGE_URL_ADD_GAME_FORM).forward(request, response);
			return;
		}


		if (!ValidationUtil.isImage(imagePart.getSubmittedFileName())) {
			request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_INVALID_IMAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_ADD_GAME_FORM).forward(request, response);
			return;
		}
		
		GameModel newGame=new GameModel(gameID, gameTitle, gameGenre, releaseDate, gamePrice, description, imagePart);
		int result=dbController.addGame(newGame);


		if (result == 1) {
			String fileName = newGame.getImageUrlFromPart();
			

			if (!fileName.isEmpty() && fileName != null) {
				String savePath = StringUtil.IMAGE_DIR_GAME;
				imagePart.write(savePath + fileName);
			}

			request.setAttribute(StringUtil.MESSAGE_SUCCESS, StringUtil.MESSAGE_SUCCESS_ADD_GAME);
			response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_ADMIN_TABLE);
		} else if (result == 0) {
			request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_ADD_GAME);
			request.getRequestDispatcher(StringUtil.PAGE_URL_ADD_GAME_FORM).forward(request, response);
		} else {
			request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtil.PAGE_URL_ADD_GAME_FORM).forward(request, response);
		}
	}
}




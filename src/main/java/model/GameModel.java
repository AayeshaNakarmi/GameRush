package model;

import java.io.File;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtil;

public class GameModel {
	private String gameID;
	private String gameTitle;
	private String gameGenre;
	private LocalDate releaseDate;
	private String gamePrice;
	private String description;
	private String imageUrlFromPart;
	
	public GameModel() {}
	
	public GameModel(String gameID, String gameTitle, String gameGenre, LocalDate releaseDate, String gamePrice, String description, Part imagePart) {
		super();
		this.gameID = gameID;
		this.gameTitle = gameTitle;
		this.gameGenre = gameGenre;
		this.releaseDate = releaseDate;
		this.gamePrice = gamePrice;
		this.description = description;
		this.imageUrlFromPart = getImageUrl(imagePart);
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public String getGameGenre() {
		return gameGenre;
	}

	public void setGameGenre(String gameGenre) {
		this.gameGenre = gameGenre;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGamePrice() {
		return gamePrice;
	}

	public void setGamePrice(String gamePrice) {
		this.gamePrice = gamePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}

	public void setImageUrlFromPart(String imageUrlFromPart) {
		this.imageUrlFromPart = imageUrlFromPart;
	}
	
	private String getImageUrl(Part part) {
	    String savePath = StringUtil.IMAGE_DIR_GAME;
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
	        imageUrlFromPart = "default_product.jpg";
	    }
	    return imageUrlFromPart;
	}
}

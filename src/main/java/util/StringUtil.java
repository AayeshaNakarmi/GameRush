package util;

public class StringUtil {

	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/game_rush";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	// End: DB Connection


	// Start: Images location
	public static final String IMAGE_ROOT_PATH = "Users\\aayes\\eclipse-workspace\\GameRush\\src\\main\\webapp\\resources\\images\\";
	public static final String IMAGE_DIR_GAME = "C:/" + IMAGE_ROOT_PATH + "game\\";
	public static final String IMAGE_DIR_USER = "C:/" + IMAGE_ROOT_PATH + "user\\";
	// End: Images location

	// Start: Queries
	public static final String QUERY_REGISTER_USER = "INSERT INTO user ("
			+ "userName, email, firstName, lastName, DOB, phoneNumber, role, password, profileImage) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String QUERY_ADD_GAME = "INSERT INTO game ("
			+ "gameID, gameTitle, gameGenre, releaseDate, gamePrice, description, gameImage) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

	public static final String QUERY_GET_ROLE = "SELECT role FROM user WHERE userName = ?";
	public static final String QUERY_USER_INFO_BY_ID = "SELECT * FROM user WHERE userName = ?";
	public static final String QUERY_GAMES_INFO = "SELECT * FROM game";
	public static final String QUERY_GAMES_INFO_FROM_ID = "SELECT * FROM game WHERE gameID=?";
	public static final String QUERY_USER_INFO = "SELECT * FROM user";
	public static final String QUERY_GET_USERNAME_COUNT = "SELECT COUNT(*) FROM user WHERE userName = ?";
	public static final String QUERY_GET_PHONE_COUNT = "SELECT COUNT(*) FROM user WHERE phoneNumber = ?";
	public static final String QUERY_GET_EMAIL_COUNT = "SELECT COUNT(*) FROM user WHERE email = ?";
	public static final String QUERY_GET_GAME_ID_COUNT = "SELECT COUNT(*) FROM game WHERE gameID = ?";
	public static final String QUERY_DELETE_GAME="DELETE FROM game WHERE gameID=?";
	public static final String QUERY_UPDATE_GAME = "UPDATE game "
			+ "SET gameTitle=?, gameGenre=?, releaseDate=?, gamePrice=?, description=?, gameImage=? "
			+ "WHERE gameID=?";
	public static final String QUERY_UPDATE_USER_PROFILE = "UPDATE user "
			+ "SET email=?, firstName=?, lastName=?, DOB=?, phoneNumber=?"
			+ "WHERE username=?";
	public static final String QUERY_TOTAL_USER_COUNT = "SELECT COUNT(*) FROM user";
	public static final String QUERY_TOTAL_GAME_COUNT = "SELECT COUNT(*) FROM game";
	
	// End: Queries

	// Start: Parameter names for user
	public static final String USERNAME = "userName";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	public static final String EMAIL = "email";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String COUNTRY = "country";
	public static final String DOB = "dob";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String SUBJECT = "subject";
	public static final String PROFILE_IMAGE = "profileImage";
	public static final String ROLE = "role";

	// Start: Parameter names for game
	public static final String GAME_ID = "gameID";
	public static final String GAME_TITLE = "gameTitle";
	public static final String GAME_GENRE = "gameGenre";
	public static final String RELEASE_DATE = "releaseDate";
	public static final String GAME_PRICE = "gamePrice";
	public static final String DESCRIPTION = "description";
	public static final String GAME_IMAGE = "gameImage";
	// End: Parameter names for game

	// Start: Validation Messages
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_ERROR_LONG_NAME = "Name is too long. Maximum length allowed is 30 characters.";
	public static final String MESSAGE_ERROR_SHORT_USERNAME = "Username is too short. Minimum length is 6 characters.";
	public static final String MESSAGE_ERROR_INVALID_FIRST_NAME ="Invalid name format for first name. Please enter alphabets only.";
	public static final String MESSAGE_ERROR_INVALID_LAST_NAME ="Invalid name format for last name. Please enter alphabets only.";
	public static final String MESSAGE_ERROR_INVALID_USER_NAME ="Invalid name format for username. Please enter alphabets and numbers only.";
	public static final String MESSAGE_ERROR_INVALID_EMAIL ="Invalid email format.";
	public static final String MESSAGE_ERROR_INVALID_ ="Invalid name format for username. Please enter alphabets only.";
	
	public static final String MESSAGE_ERROR_INVALID_PHONE_NUMBER ="Invalid phone number. Please ensure country code and number is valid";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password and retype password is not matched.";
	public static final String MESSAGE_ERROR_AGE_RESTRICTION ="The user must be 13 or older to register.";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";
	public static final String MESSAGE_ERROR_INVALID_PASSWORD = "Please enter a password with at least one uppercase letter, one lowercase letter, one digit, and one symbol from @$!%*?&.";
	public static final String MESSAGE_ERROR_INVALID_IMAGE = "Invalid image format. Please provide a file with one of the following extensions: JPG, JPEG, PNG, GIF, BMP, or SVG.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_INCORRECT_CREDENTIALS = "Either username or password is not correct!";
	
	//Add Game Page Messages
	public static final String MESSAGE_ERROR_INVALID_GAME_TITLE = "Invalid game title. Please enter alphabets and numbers only";
	public static final String MESSAGE_ERROR_INVALID_GAME_ID = "Invalid GameID. Please enter numbers only";
	public static final String MESSAGE_ERROR_INVALID_GAME_PRICE= "Invalid game price. Please enter float value only";
	public static final String MESSAGE_ERROR_GAME_ID_EXISTS = "Game ID already exists. Please enter a unique Game ID.";
	public static final String MESSAGE_ERROR_ADD_GAME = "Game not added!";
	
	
	public static final String MESSAGE_SUCCESS_ADD_GAME = "Successfully Added!";
	//Add Game Page Messages

	// Other Messages
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the game!";
	public static final String MESSAGE_SUCCESS_UPDATE = "Successfully Updated!";
	public static final String MESSAGE_ERROR_UPDATE = "Cannot update the game!";
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	public static final String MESSAGE_ERROR_LOGIN = "errorMessageLogin";
	public static final String MESSAGE_ERROR_REGISTER = "errorMessageRegister";
	// End: Validation Messages

	// Start: JSP Route
	/* public static final String PAGE_URL_LOGIN = "/pages/login.jsp"; */
	public static final String PAGE_URL_LOGIN = "/pages/register.jsp";
	public static final String PAGE_URL_REGISTER_FORM = "/pages/register.jsp#register";
	public static final String PAGE_URL_HOME = "/pages/home.jsp";
	public static final String PAGE_URL_FOOTER = "/pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "/pages/header.jsp";
	public static final String PAGE_URL_ADMIN_TABLE="/pages/adminTable.jsp";
	public static final String PAGE_URL_ADD_GAME_FORM="/pages/addGameForm.jsp";
	public static final String PAGE_URL_UPDATE_GAME_FORM="/pages/updateGameForm.jsp";
	public static final String PAGE_URL_USER_PROFILE="pages/userProfile.jsp";
	public static final String PAGE_URL_GAME_DETAILS="pages/gameDetail.jsp";
	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_REGISTER = "/Register";
	public static final String SERVLET_URL_LOGOUT = "/Logout";
	public static final String SERVLET_URL_HOME = "/HomePage";
	public static final String SERVLET_URL_MODIFY_GAME = "/ModifyGame";
	public static final String SERVLET_URL_ADD_GAME = "/AddGame";
	public static final String SERVLET_URL_ADMIN_DASHBOARD = "/AdminDash";
	public static final String SERVLET_URL_PROFILE = "/UserProfile";
	public static final String SERVLET_URL_GAME_DETAILS = "/GameDetails";
	// End: Servlet Route


	// Start: Normal Text
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String USER_MODEL = "userModel";
	public static final String USER_LIST= "userList";
	public static final String GAME_LIST = "gameList";
	public static final String USER_COUNT = "userCount";
	public static final String GAME_COUNT = "gameCount";
	public static final String SLASH= "/";
	public static final String DELETE_ID= "deleteId";
	public static final String UPDATE_ID= "updateId";
	public static final String VIEW_ID= "viewId";
	public static final String DEFAULT_PRODUCT_IMG="default_product.jpg";
	public static final String DEFAULT_USER_IMG="default_user.jpg";
	//End: Normal text





}

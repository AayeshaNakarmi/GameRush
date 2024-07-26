package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.GameModel;
import model.LoginModel;
import model.PasswordEncryptionWithAes;
import model.UserModel;
import util.StringUtil;

public class DBController {
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName(StringUtil.DRIVER_NAME);
		return DriverManager.getConnection(StringUtil.LOCALHOST_URL, StringUtil.LOCALHOST_USERNAME,
				StringUtil.LOCALHOST_PASSWORD);
	}

	public int registerUser(UserModel user) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtil.QUERY_REGISTER_USER);

			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setDate(5, Date.valueOf(user.getDob()));
			stmt.setString(6, user.getPhoneNumber());
			stmt.setString(7, user.getRole());
			stmt.setString(8, PasswordEncryptionWithAes.encrypt(user.getUserName(), user.getPassword()));
			stmt.setString(9, user.getImageUrlFromPart());

			int result=stmt.executeUpdate();

			if (result>=1){
				return 1;
			}
			else {
				return 0;
			}
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean checkUsernameExists(String username) {
		try(Connection con=getConnection()){
			PreparedStatement st=con.prepareStatement(StringUtil.QUERY_GET_USERNAME_COUNT);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}  catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean checkPhoneNumberExists(String phoneNumber) {
		try(Connection con=getConnection()){
			PreparedStatement st=con.prepareStatement(StringUtil.QUERY_GET_PHONE_COUNT);
			st.setString(1, phoneNumber);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}  catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean checkEmailExists(String email) {
		try(Connection con=getConnection()){
			PreparedStatement st=con.prepareStatement(StringUtil.QUERY_GET_EMAIL_COUNT);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}  catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public int getUserLoginInfo(LoginModel loginModel) {
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtil.QUERY_USER_INFO_BY_ID);
			st.setString(1, loginModel.getUserName());
			ResultSet result = st.executeQuery();

			if (result.next()) {
				// User name and password match in the database
				String userDb = result.getString(StringUtil.USERNAME);
				String passwordDb = result.getString(StringUtil.PASSWORD);
				String decryptedPwd=PasswordEncryptionWithAes.decrypt(passwordDb, userDb);

				if (decryptedPwd!=null && userDb.equals(loginModel.getUserName()) && decryptedPwd.equals(loginModel.getPassword()))
					return 1;
				else
					return 0; // Incorrect password
			}else {
				return -1; 	// Username not found
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -2;
		}
	}

	public int addGame(GameModel game) {
		try {
			PreparedStatement stmt=getConnection().prepareStatement(StringUtil.QUERY_ADD_GAME);

			stmt.setString(1, game.getGameID());
			stmt.setString(2, game.getGameTitle());
			stmt.setString(3, game.getGameGenre());
			stmt.setDate(4, Date.valueOf(game.getReleaseDate()));
			stmt.setString(5, game.getGamePrice());
			stmt.setString(6, game.getDescription());
			stmt.setString(7, game.getImageUrlFromPart());

			int result=stmt.executeUpdate();

			if (result>=1){
				return 1;
			}
			else {
				return 0;
			}
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("SQlException");
			return -1;
		}
	}

	public int deleteGame(int gameID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtil.QUERY_DELETE_GAME);
			st.setInt(1, gameID);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public int updateGame(GameModel updatedGameModel) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtil.QUERY_UPDATE_GAME);
			st.setString(1, updatedGameModel.getGameTitle());
			st.setString(2, updatedGameModel.getGameGenre());
			st.setDate(3, Date.valueOf(updatedGameModel.getReleaseDate()));
			st.setString(4, updatedGameModel.getGamePrice());
			st.setString(5, updatedGameModel.getDescription());
			st.setString(6, updatedGameModel.getImageUrlFromPart());
			st.setString(7, updatedGameModel.getGameID());
			
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	public int updateProfile(UserModel updatedUserProfile) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtil.QUERY_UPDATE_USER_PROFILE);

			st.setString(1, updatedUserProfile.getEmail());
			st.setString(2, updatedUserProfile.getFirstName());
			st.setString(3, updatedUserProfile.getLastName());
			st.setDate(4, Date.valueOf(updatedUserProfile.getDob()));
			st.setString(5, updatedUserProfile.getPhoneNumber());
			st.setString(6, updatedUserProfile.getUserName());

			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	public ArrayList<GameModel> getGameInfo(){
		try(Connection con=getConnection()){
			PreparedStatement st=con.prepareStatement(StringUtil.QUERY_GAMES_INFO);
			ResultSet rs=st.executeQuery();
			ArrayList<GameModel> games =new ArrayList<GameModel>();

			while (rs.next()) {
				GameModel gameInfo=new GameModel();

				gameInfo.setGameID(rs.getString(StringUtil.GAME_ID));
				gameInfo.setGameTitle(rs.getString(StringUtil.GAME_TITLE));
				gameInfo.setGameGenre(rs.getString(StringUtil.GAME_GENRE));
				gameInfo.setReleaseDate(rs.getDate(StringUtil.RELEASE_DATE).toLocalDate());
				gameInfo.setGamePrice(rs.getString(StringUtil.GAME_PRICE));
				gameInfo.setDescription(rs.getString(StringUtil.DESCRIPTION));
				gameInfo.setImageUrlFromPart(rs.getString(StringUtil.GAME_IMAGE));
				games.add(gameInfo);
			}
			return games;
		}
		catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;

		}
	}

	public ArrayList<UserModel> getUserInfo(){
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtil.QUERY_USER_INFO);
			ResultSet result = stmt.executeQuery();

			ArrayList<UserModel> users = new ArrayList<UserModel>();

			while (result.next()) {
				UserModel user = new UserModel();
				user.setUserName(result.getString(StringUtil.USERNAME));
				user.setEmail(result.getString(StringUtil.EMAIL));
				user.setFirstName(result.getString(StringUtil.FIRST_NAME));
				user.setLastName(result.getString(StringUtil.LAST_NAME));
				user.setDob(result.getDate(StringUtil.DOB).toLocalDate());
				user.setPhoneNumber(result.getString(StringUtil.PHONE_NUMBER));		
				users.add(user);
			}
			return users;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean isAdmin(String username) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(StringUtil.QUERY_GET_ROLE);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String role = rs.getString("role");
				return role != null && role.equals("admin");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


	public UserModel getUserProfileInfo(String username){
		try(Connection con=getConnection()){
			PreparedStatement st=con.prepareStatement(StringUtil.QUERY_USER_INFO_BY_ID);
			st.setString(1, username);
			ResultSet rs=st.executeQuery();

			UserModel user = new UserModel(); 

			if (rs.next()) {
				user.setUserName(rs.getString(StringUtil.USERNAME));
				user.setEmail(rs.getString(StringUtil.EMAIL));
				user.setFirstName(rs.getString(StringUtil.FIRST_NAME));
				user.setLastName(rs.getString(StringUtil.LAST_NAME));
				user.setDob(rs.getDate(StringUtil.DOB).toLocalDate());
				user.setPhoneNumber(rs.getString(StringUtil.PHONE_NUMBER));
				user.setImageUrlFromPart(rs.getString(StringUtil.PROFILE_IMAGE));
			}			
			return user;
		}
		catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;

		}
	}

	public GameModel getGameInfoByID(int gameID){
		try(Connection con=getConnection()){
			PreparedStatement st=con.prepareStatement(StringUtil.QUERY_GAMES_INFO_FROM_ID);
			st.setInt(1, gameID);
			ResultSet rs=st.executeQuery();
			GameModel gameInfo=new GameModel();

			while (rs.next()) {
				gameInfo.setGameID(rs.getString(StringUtil.GAME_ID));
				gameInfo.setGameTitle(rs.getString(StringUtil.GAME_TITLE));
				gameInfo.setGameGenre(rs.getString(StringUtil.GAME_GENRE));
				gameInfo.setReleaseDate(rs.getDate(StringUtil.RELEASE_DATE).toLocalDate());
				gameInfo.setGamePrice(rs.getString(StringUtil.GAME_PRICE));
				gameInfo.setDescription(rs.getString(StringUtil.DESCRIPTION));
				gameInfo.setImageUrlFromPart(rs.getString(StringUtil.GAME_IMAGE));
			}
			return gameInfo;
		}
		catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}	

	public boolean checkGameIDExists(String gameID) {
		try(Connection con=getConnection()){
			PreparedStatement st=con.prepareStatement(StringUtil.QUERY_GET_GAME_ID_COUNT);
			st.setString(1, gameID);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}  catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public int getTotalUsers() {
		try(Connection con=getConnection()){
			PreparedStatement st=con.prepareStatement(StringUtil.QUERY_TOTAL_USER_COUNT);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
		}  catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		 return 0;
	}
	
	public int getTotalGames() {
		try(Connection con=getConnection()){
			PreparedStatement st=con.prepareStatement(StringUtil.QUERY_TOTAL_GAME_COUNT);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
		}  catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		 return 0;
	}
}



package com.revature.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.EmployeeUser;
import com.revature.model.ManagerUser;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDao {
	public static boolean insertUser(User user) {
		PreparedStatement ps = null;
		String sql;

		try (Connection conn = ConnectionUtil.getConnection()) {
			sql = "INSERT INTO ERS_USERS (U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ID) "
					+ "VALUES(?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getPosition());

			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean checkUsernameUnique(String username) {
		PreparedStatement ps = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT COUNT(U_USERNAME) FROM ERS_USERS WHERE U_USERNAME = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int unique = rs.getInt("COUNT(U_USERNAME)");

				if (unique == 1) {
					System.out.println("Your username already exists. Please try a new one.");
					return false;
				}
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public static boolean checkUsernammePassword(String username, String password) {
		PreparedStatement ps = null;
		String sql;
		int existance = 1000;

		try (Connection conn = ConnectionUtil.getConnection()) {

			sql = "SELECT Count(U_ID) FROM ERS_USERS WHERE u_username=? AND u_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				existance = rs.getInt("COUNT(U_ID)");
			}

			ps.close();
			rs.close();

			if (existance == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static User loginUser(String username, String password) {
		PreparedStatement ps = null;
		String sql;
		String firstName = "";
		String lastName = "";
		String email = "";
		int pos = 0;
		int id = 0;

		try (Connection conn = ConnectionUtil.getConnection()) {

			sql = "SELECT * FROM ERS_USERS WHERE u_username=? AND u_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("U_ID");
				pos = rs.getInt("UR_ID");
				firstName = rs.getString("U_FIRSTNAME");
				lastName = rs.getString("U_LASTNAME");
				email = rs.getString("U_EMAIL");
			}

			ps.close();
			rs.close();

			if (pos == 1)
				return new EmployeeUser(id, firstName, lastName, username, password, email, 1);
			else if (pos == 2)
				return new ManagerUser(id, firstName, lastName, username, password, email, 2);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static User retrieveUserByID(int id) {
		PreparedStatement ps = null;
		String sql;
		String username = "";
		String firstName = "";
		String lastName = "";
		String password = "";
		String email = "";
		int pos = 0;

		try (Connection conn = ConnectionUtil.getConnection()) {

			sql = "SELECT * FROM ERS_USERS WHERE U_ID=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pos = rs.getInt("UR_ID");
				firstName = rs.getString("U_FIRSTNAME");
				lastName = rs.getString("U_LASTNAME");
				email = rs.getString("U_EMAIL");
				username = rs.getString("U_USERNAME");
				password = rs.getString("U_PASSWORD");
			}

			ps.close();
			rs.close();

			if (pos == 1)
				return new EmployeeUser(id, firstName, lastName, username, password, email, 1);
			else if (pos == 2)
				return new ManagerUser(id, firstName, lastName, username, password, email, 2);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<EmployeeUser> retrieveAllEmployees() {
		PreparedStatement ps = null;
		String sql;
		int id = 0;
		String firstName = "";
		String lastName = "";
		String username = "";
		String password = "";
		String email = "";

		ArrayList<EmployeeUser> emps = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			sql = "SELECT * FROM ERS_USERS WHERE UR_ID=1";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("U_ID");
				firstName = rs.getString("U_FIRSTNAME");
				lastName = rs.getString("U_LASTNAME");
				email = rs.getString("U_EMAIL");
				username = rs.getString("U_USERNAME");
				password = rs.getString("U_PASSWORD");

				emps.add(new EmployeeUser(id, firstName, lastName, username, password, email, 1));
			}

			ps.close();
			rs.close();

			return emps;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean updateEmployeeByID(int id, String changeInfo, String field) {
		PreparedStatement ps = null;
		String sql = "";

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			if (changeInfo.equals("First Name"))
				sql = "UPDATE ERS_USERS SET U_FIRSTNAME=? WHERE U_ID=?";
			else if (changeInfo.equals("Last Name"))
				sql = "UPDATE ERS_USERS SET U_LASTNAME=? WHERE U_ID=?";
			else if (changeInfo.equals("Email"))
				sql = "UPDATE ERS_USERS SET U_EMAIL=? WHERE U_ID=?";
			
			ps = conn.prepareStatement(sql);				
			ps.setString(1, field);
			ps.setInt(2, id);
			int count = ps.executeUpdate();
			
			if (count > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

}

package com.revature.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.EmployeeUser;
import com.revature.model.ManagerUser;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDao {
	public static boolean insertUser(User user) {
		PreparedStatement ps = null;
		String sql;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
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
			if(count > 0) {
				return true;
			}
			else {
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean checkUsernameUnique(String username) {
        PreparedStatement ps = null;
        
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT COUNT(U_USERNAME) FROM ERS_USERS WHERE U_USERNAME = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int unique = rs.getInt("COUNT(U_USERNAME)");

                if(unique == 1) {
                    System.out.println("Your username already exists. Please try a new one.");
                    return false;
                }
            }

            rs.close();
            ps.close();

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return true;
    }
	
	public static boolean checkUsernammePassword(String username, String password) {
		PreparedStatement ps = null;
		String sql;
		int existance = 1000;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			sql = "SELECT Count(U_ID) FROM ERS_USERS WHERE u_username=? AND u_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				existance = rs.getInt("COUNT(U_ID)");
			}
			
			ps.close();
			rs.close();
			
			if(existance == 1) return true;
			else return false;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
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
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			sql = "SELECT * FROM ERS_USERS WHERE u_username=? AND u_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				pos = rs.getInt("UR_ID");
				firstName = rs.getString("U_FIRSTNAME");
				lastName = rs.getString("U_LASTNAME");
				email = rs.getString("U_EMAIL");
			}
			
			ps.close();
			rs.close();
			
			if(pos == 1) return new EmployeeUser(firstName, lastName, username, password, email, 1); 
			else if(pos == 2) return new ManagerUser(firstName, lastName, username, password, email, 2);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static User retrieveUser(String username) {
		PreparedStatement ps = null;
		String sql;
		String firstName = "";
		String lastName = "";
		String password = "";
		String email = "";
		int pos = 0;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			sql = "SELECT * FROM ERS_USERS WHERE u_username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				pos = rs.getInt("UR_ID");
				firstName = rs.getString("U_FIRSTNAME");
				lastName = rs.getString("U_LASTNAME");
				email = rs.getString("U_EMAIL");
				password = rs.getString("U_PASSWORD");
			}
			
			ps.close();
			rs.close();
			
			if(pos == 1) return new EmployeeUser(firstName, lastName, username, password, email, 1); 
			else if(pos == 2) return new ManagerUser(firstName, lastName, username, password, email, 2);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}

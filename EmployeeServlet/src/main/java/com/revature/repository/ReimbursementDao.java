package com.revature.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDao {
	public static boolean insertReimbursement(Reimbursement reim) {
		PreparedStatement ps = null;
		String sql;

		try (Connection conn = ConnectionUtil.getConnection()) {
			sql = "INSERT INTO ERS_REIMBURSEMENTS(R_AMOUNT, R_DESCRIPTION, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(sql);
			ps.setDouble(1, reim.getAmount());
			ps.setString(2, reim.getDescription());
			ps.setTimestamp(3, reim.getTime_submitted());
			ps.setTimestamp(4, reim.getTime_resolved());
			ps.setInt(5, reim.getId_author());
			ps.setInt(6, reim.getId_resolver());
			ps.setInt(7, reim.getType());
			ps.setInt(8, reim.getStatus());

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
	
	public static ArrayList<Reimbursement> retrieveReimbursementsByAuthor(int id) {
		PreparedStatement ps = null;
		String sql;
		
		int r_id = 0;
		double amt = 0;
		String desc = "";
		Timestamp time_sub;
		Timestamp time_res;
		int id_res;
		int type;
		int status;
		ArrayList<Reimbursement> reim = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				r_id = rs.getInt("R_ID");
				amt = rs.getDouble("R_AMOUNT");
				desc = rs.getString("R_DESCRIPTION");
				time_sub = rs.getTimestamp("R_SUBMITTED");
				time_res = rs.getTimestamp("R_RESOLVED");
				id_res = rs.getInt("U_ID_RESOLVER");
				type = rs.getInt("RT_TYPE");
				status = rs.getInt("RT_STATUS");
				
				reim.add(new Reimbursement(r_id, amt, desc, time_sub, time_res, id, id_res, type, status));	
			}
			
			ps.close();
			rs.close();
			
			return reim;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static boolean deleteReimbursementByID(int id) {
		PreparedStatement ps = null;
		String sql;

		try (Connection conn = ConnectionUtil.getConnection()) {
			sql = "DELETE FROM ERS_REIMBURSEMENTS WHERE R_ID=?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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

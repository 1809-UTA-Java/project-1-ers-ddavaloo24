package com.revature.repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

/**
 * 
 * Data access object for linking the Reimbursement object and the ERS-DB
 * associated tables. Has methods to insert, update, and delete reimbursements
 * 
 * @author Daria Davaloo
 *
 */
public class ReimbursementDao {
	public static boolean insertReimbursement(Reimbursement reim) {
		PreparedStatement ps = null;
		String sql;

		try (Connection conn = ConnectionUtil.getConnection()) {
			sql = "INSERT INTO ERS_REIMBURSEMENTS(R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(sql);
			ps.setDouble(1, reim.getAmount());
			ps.setString(2, reim.getDescription());

			if (reim.getImage() != null) {
				ps.setBinaryStream(3, new ByteArrayInputStream(reim.getImage()));
			} else {
				ps.setObject(3, null);
			}
			ps.setTimestamp(4, reim.getTime_submitted());
			ps.setTimestamp(5, reim.getTime_resolved());
			ps.setInt(6, reim.getId_author());
			ps.setInt(7, reim.getId_resolver());
			ps.setInt(8, reim.getType());
			ps.setInt(9, reim.getStatus());

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
		byte[] image = null;
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

				if (rs.getBlob("R_RECEIPT") != null) {
					Blob blob = rs.getBlob("R_RECEIPT");
					image = blob.getBytes(1L, (int) blob.length());
				}

				reim.add(new Reimbursement(r_id, amt, desc, image, time_sub, time_res, id, id_res, type, status));
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

			if (count == 1) {
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

	public static ArrayList<Reimbursement> retrieveAllReimbursements() {
		PreparedStatement ps = null;
		String sql;

		int r_id = 0;
		double amt = 0;
		String desc = "";
		byte[] image = null;
		Timestamp time_sub;
		Timestamp time_res;
		int id_auth = 0;
		int id_res = 0;
		int type = 0;
		int status = 0;

		ArrayList<Reimbursement> reims = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			sql = "SELECT * FROM ERS_REIMBURSEMENTS";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				r_id = rs.getInt("R_ID");
				amt = rs.getDouble("R_AMOUNT");
				desc = rs.getString("R_DESCRIPTION");
				time_sub = rs.getTimestamp("R_SUBMITTED");
				time_res = rs.getTimestamp("R_RESOLVED");
				id_auth = rs.getInt("U_ID_AUTHOR");
				id_res = rs.getInt("U_ID_RESOLVER");
				type = rs.getInt("RT_TYPE");
				status = rs.getInt("RT_STATUS");

				if (rs.getBlob("R_RECEIPT") != null) {
					Blob blob = rs.getBlob("R_RECEIPT");
					image = blob.getBytes(1L, (int) blob.length());
				} else {
					image = null;
				}

				reims.add(new Reimbursement(r_id, amt, desc, image, time_sub, time_res, id_auth, id_res, type, status));
			}

			ps.close();
			rs.close();

			return reims;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean approveOrDenyReimbursementByID(int reimID, String approveOrDeny, int resID) {

		PreparedStatement ps = null;
		String sql;

		try (Connection conn = ConnectionUtil.getConnection()) {
			sql = "UPDATE ERS_REIMBURSEMENTS SET RT_STATUS=?, R_RESOLVED=?, U_ID_RESOLVER=? WHERE R_ID=?";

			ps = conn.prepareStatement(sql);

			if (approveOrDeny.equals("Approve"))
				ps.setInt(1, 2);
			else if (approveOrDeny.equals("Deny"))
				ps.setInt(1, 3);

			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setInt(3, resID);

			ps.setInt(4, reimID);

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

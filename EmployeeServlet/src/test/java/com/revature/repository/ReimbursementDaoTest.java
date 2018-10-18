package com.revature.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.revature.model.Reimbursement;

public class ReimbursementDaoTest {

	@Test
	public void testInsertReimbursementNoBlob() {
		Reimbursement reim = new Reimbursement(0, "theTest", null, 0, 1, 2);
		boolean result = ReimbursementDao.insertReimbursement(reim);
		assertEquals(result, true);
	}
	
	@Test
	public void testInsertReimbursementNoBlobForDeletion() {
		Reimbursement reim = new Reimbursement(0, "theTestDELETION", null, 0, 1, 2);
		boolean result = ReimbursementDao.insertReimbursement(reim);
		assertEquals(result, true);
	}

	@Test
	public void testInsertReimbursementWithBlob() {

		String imageString = "IMAGE";
		byte[] image = imageString.getBytes();
		Reimbursement reim = new Reimbursement(0, "theTestWithBlob", image, 0, 1, 2);

		boolean result = ReimbursementDao.insertReimbursement(reim);
		if (result) {
			ArrayList<Reimbursement> allReim = ReimbursementDao.retrieveReimbursementsByAuthor(0);
			for (Reimbursement r : allReim) {
				if (r.getImage() != null) {
					assertTrue(true);
					return;
				}
			}
		}

		assertEquals(result, true);
	}

	@Test
	public void testInsertReimbursementFakeAuthor() {
		Reimbursement reim = new Reimbursement(0, "theTest", null, 900000, 1, 2);
		boolean result = ReimbursementDao.insertReimbursement(reim);
		assertEquals(result, false);
	}

	@Test
	public void testRetrieveReimbursementsByAuthor() {
		ArrayList<Reimbursement> reim = ReimbursementDao.retrieveReimbursementsByAuthor(0);
		
		boolean result1 = true;
		boolean result2 = false;
		
		for(Reimbursement r: reim) {
			if(r.getDescription().equals("theTestWithBlob")) {
				result1 = true;
			}
			if(r.getDescription().equals("theTest")) {
				result2 = true;
			}
		}
		
		assertEquals(result1, result2);
	}
	
	@Test
	public void testRetrieveReimbursementsByFakeAuthor() {
		ArrayList<Reimbursement> reim = ReimbursementDao.retrieveReimbursementsByAuthor(90000);
		assertEquals(reim.size(), 0);
	}

	@Test
	public void testDeleteReimbursementByID() {
		ArrayList<Reimbursement> reim = ReimbursementDao.retrieveReimbursementsByAuthor(0);

		int id = 0;
		for(Reimbursement r : reim) {
			if(r.getDescription().equals("theTestDELETION")) {
				id = r.getId();
			}
		}
		
		boolean result = ReimbursementDao.deleteReimbursementByID(id);
		
		assertEquals(result, true);
	}
	
	@Test
	public void testDeleteReimbursementByWrongID() {
		boolean result = ReimbursementDao.deleteReimbursementByID(0);
		
		assertEquals(result, false);
	}
	
	@Test
	public void testRetrieveAllReimbursements() {
		ArrayList<Reimbursement> reim = ReimbursementDao.retrieveAllReimbursements();
		
		assertEquals(reim.size() % 2, 0);
	}

	@Test
	public void testApproveOrDenyReimbursementByID() {
		
		ArrayList<Reimbursement> reim = ReimbursementDao.retrieveAllReimbursements();
		int id = reim.get(reim.size()-1).getId();
		
		boolean result = ReimbursementDao.approveOrDenyReimbursementByID(id, "Approve", 0);
		
		assertEquals(result, true);
	}

	@Test
	public void testApproveOrDenyReimbursementByIDCheckStatus() {
		
		ArrayList<Reimbursement> reim = ReimbursementDao.retrieveAllReimbursements();
		int id = reim.get(reim.size()-1).getId();

		boolean result = ReimbursementDao.approveOrDenyReimbursementByID(id, "Deny", 0);
		
		if(result) {
			if(reim.get(reim.size()-1).getStatus() == 3) {
				assertTrue(true);
				return;
			}
		}
		
		assertEquals(result, true);
	}
}

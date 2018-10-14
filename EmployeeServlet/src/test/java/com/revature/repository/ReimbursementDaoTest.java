package com.revature.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.revature.model.Reimbursement;

public class ReimbursementDaoTest {

	@Test
	public void testInsertReimbursement() {
		Reimbursement reim = new Reimbursement(0, "theTest", 0, 1, 2);
		boolean result = ReimbursementDao.insertReimbursement(reim);
		assertEquals(result, true);
	}

	@Test
	public void testRetrieveReimbursementsByAuthor() {
		ArrayList<Integer> check = new ArrayList<>();
		ArrayList<Reimbursement> reim = new ArrayList<>();
		
		check.add(1);
		reim = ReimbursementDao.retrieveReimbursementsByAuthor(0);
		
		assertEquals(check.size(), reim.size());
	}

}

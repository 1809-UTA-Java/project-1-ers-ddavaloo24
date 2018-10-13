package com.revature.repository;

import org.junit.Test;
import static org.junit.Assert.*;

import com.revature.model.ManagerUser;
import com.revature.model.User;

public class UserDaoTest {

	@Test
	public void testInsertUser() {
		User newUser = new ManagerUser("tester", "theTest", "testUNP", "testPass", "test123@test.com", 2);
		boolean result = UserDao.insertUser(newUser);
		assertEquals(result, true);
	}
	
	@Test
	public void testCheckUsernammePassword() {
		String username = "timmycat";
		String password = "Tomcat123";
		
		boolean result = UserDao.checkUsernammePassword(username, password);
		assertEquals(result, true);
	}
	
	@Test
	public void testCheckUsernammePasswordBad() {
		String username = "testBADUSERNAME";
		String password = "testPass";
		
		boolean result = UserDao.checkUsernammePassword(username, password);
		assertEquals(result, false);
	}

}

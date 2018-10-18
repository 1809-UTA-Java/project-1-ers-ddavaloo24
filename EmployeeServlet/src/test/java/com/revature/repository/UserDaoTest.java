package com.revature.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import com.revature.model.EmployeeUser;
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
		String username = "timdeck";
		String password = "decktim";
		
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

	@Test
	public void testCheckUsernameUniqueExists() {
		String username = "baseAdmin";
		boolean result = UserDao.checkUsernameUnique(username);
		
		assertEquals(result, false);
	}
	
	@Test
	public void testCheckUsernameUniqueDoesNotExist() {
		String username = "baseAdminNOTHERE";
		boolean result = UserDao.checkUsernameUnique(username);
		
		assertEquals(result, true);
	}

	@Test
	public void testLoginUser() {
		User user = new ManagerUser(0, "baseAdmin", "baseAdmin", "base", "base", null, 2);
		User userRet = UserDao.loginUser("baseAdmin", "baseAdmin");
		
		assertEquals(user.getId(), userRet.getId());
	}
	
	@Test
	public void testLoginUserFail() {
		User userRet = UserDao.loginUser("base", "base");
		
		assertEquals(userRet, null);
	}

	@Test
	public void testRetrieveUserByID() {
		User user = UserDao.retrieveUserByID(0);
		assertEquals(user.getId(), 0);
	}
	
	@Test
	public void testRetrieveUserByIDWrong() {
		User user = UserDao.retrieveUserByID(-1);
		assertEquals(user, null);
	}

	@Test
	public void testRetrieveAllEmployees() {
		ArrayList<EmployeeUser> emps = UserDao.retrieveAllEmployees();
		
		assertEquals(emps.size(), 1);
	}
	
	@Test
	public void testUpdateEmployeeByIDFirstNameFail() {
		boolean result = UserDao.updateEmployeeByID(0, "First Name", "a");
		
		assertEquals(result, false);
	}
	
	@Test
	public void testUpdateEmployeeByIDFirstNameSuccess() {
		boolean result = UserDao.updateEmployeeByID(0, "First Name", "aaron");
		
		assertEquals(result, true);
	}
	
	@Test
	public void testUpdateEmployeeByIDLastNameFail() {
		boolean result = UserDao.updateEmployeeByID(0, "Last Name", "a");
		
		assertEquals(result, false);
	}
	
	@Test
	public void testUpdateEmployeeByIDLastNameSuccess() {
		boolean result = UserDao.updateEmployeeByID(0, "Last Name", "funes");
		
		assertEquals(result, true);
	}

}

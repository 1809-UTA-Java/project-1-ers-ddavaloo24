package com.revature.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginUtilTest {

	@Test
	public void testNameCheckerLength() {
		assertEquals(LoginUtil.nameChecker("a"), false);
		assertEquals(LoginUtil.nameChecker("lolololololololololol"), false);
	}
	
	@Test
	public void testNameCheckerSpecialChars() {
		assertEquals(LoginUtil.nameChecker("h@ppy"), false);
	}
	
	@Test
	public void testNameCheckerPass() {
		assertEquals(LoginUtil.nameChecker("Daria"), true);
	}

	@Test
	public void testUsernameCheckerWithSpaces() {
		assertEquals(LoginUtil.usernameChecker("Daria yay"), false);
	}
	
	@Test
	public void testUsernameCheckerUnique() {
		assertEquals(LoginUtil.usernameChecker("timdeck"), false);
	}
}

package com.revature.model;

import java.io.PrintWriter;

import com.revature.repository.UserDao;

public abstract class User {

	protected String firstName;
	protected String lastName;
	protected String username;
	protected String password;
	protected String email;
	protected int position;

	public User(String fn, String ln, String un, String pw, String em, int pos) {
		this.firstName = fn;
		this.lastName = ln;
		this.username = un;
		this.password = pw;
		this.email = em;
		this.position = pos;
	}

	public static User login(String username, String password) {

		boolean existance = UserDao.checkUsernammePassword(username, password);

		if (!existance)
			return null;

		return UserDao.loginUser(username, password);
	}

	public abstract void logout();

	public abstract void viewHome(PrintWriter pw);

	public abstract void viewPendingReqs();

	public abstract void viewInfo();

	
	
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public int getPosition() {
		return position;
	}
}

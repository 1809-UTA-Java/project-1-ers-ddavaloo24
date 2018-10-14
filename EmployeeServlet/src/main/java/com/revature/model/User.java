package com.revature.model;

import java.io.PrintWriter;

import com.revature.repository.UserDao;

public abstract class User {

	protected int id;
	protected String firstName;
	protected String lastName;
	protected String username;
	protected String password;
	protected String email;
	protected int position;

	//With ID
	public User(int id, String fn, String ln, String un, String pw, String em, int pos) {
		this.id = id;
		this.firstName = fn;
		this.lastName = ln;
		this.username = un;
		this.password = pw;
		this.email = em;
		this.position = pos;
	}
	
	
	//WITHOUT ID
	public User(String firstName, String lastName, String username, String password, String email, int position) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.position = position;
	}



	public static User login(String username, String password) {

		boolean existance = UserDao.checkUsernammePassword(username, password);

		if (!existance)
			return null;

		return UserDao.loginUser(username, password);
	}

	
	
	public abstract void viewHome(PrintWriter pw);

	public abstract void viewPendingReqs();

	public abstract void viewInfo();

	
	
	
	public int getId() {
		return id;
	}

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

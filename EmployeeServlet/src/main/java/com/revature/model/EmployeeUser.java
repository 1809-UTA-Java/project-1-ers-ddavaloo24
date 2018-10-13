package com.revature.model;

import java.io.PrintWriter;

public class EmployeeUser extends User {

	public EmployeeUser(String fn, String ln, String un, String pw, String em, int pos) {
		super(fn, ln, un, pw, em, pos);
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
	}

	@Override
	public void viewHome(PrintWriter pw) {
		pw.println("<html> <body> ");
		pw.println("THIS IS THE EMPLOYEE HOME PAGE");
		pw.println("<a href=\"main-menu/logout\">Logout</a>");
		pw.println("</body> </html> ");

	}

	@Override
	public void viewPendingReqs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewInfo() {
		// TODO Auto-generated method stub

	}

}

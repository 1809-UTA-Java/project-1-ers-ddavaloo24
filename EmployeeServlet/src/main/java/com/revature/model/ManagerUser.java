package com.revature.model;

import java.io.PrintWriter;

public class ManagerUser extends User {

	public ManagerUser(int id, String fn, String ln, String un, String pw, String em, int pos) {
		super(id, fn, ln, un, pw, em, pos);
	}
	

	public ManagerUser(String firstName, String lastName, String username, String password, String email,
			int position) {
		super(firstName, lastName, username, password, email, position);
	}



	@Override
	public void viewHome(PrintWriter pw, String name) {
		pw.println("<html> <body> ");
		pw.println("THIS IS THE MANAGER HOME PAGE");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("HELLO " + name);
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"/ERS-Servlet/reimbursements\">All Reimbursements</a>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"/ERS-Servlet/employees\">View Employee Information</a>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"main-menu/logout\">Logout</a>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("</body> </html> ");
	}

	@Override
	public String viewInfo() {
		// TODO Auto-generated method stub
		return null;

	}

}

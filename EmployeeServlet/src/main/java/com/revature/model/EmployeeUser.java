package com.revature.model;

import java.io.PrintWriter;

public class EmployeeUser extends User {

	public EmployeeUser(int id, String fn, String ln, String un, String pw, String em, int pos) {
		super(id, fn, ln, un, pw, em, pos);
	}

	public EmployeeUser(String firstName, String lastName, String username, String password, String email,
			int position) {
		super(firstName, lastName, username, password, email, position);
	}

	@Override
	public void viewHome(PrintWriter pw, String name) {
		pw.println("<html> <body> ");
		pw.println("THIS IS THE EMPLOYEE HOME PAGE");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("HELLO " + name);
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"createreimbursement.html\">Create a New Reimbursement</a>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"/ERS-Servlet/reimbursements\">My Reimbursements</a>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"/ERS-Servlet/employees/" + id + "\">View and Update Personal Information</a>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"main-menu/logout\">Logout</a>");
		pw.println("</body> </html> ");

	}

	@Override
	public String viewInfo() {
		return ("ID: " + id + ",Name:" + this.getFirstName() + " " + this.getLastName());
	}
	
	public void viewAllInfo(PrintWriter pw) {
		pw.println("Employee ID: " + id + "<br>");
		pw.println("<br>First Name: " + firstName + "<br>");
		pw.println("<br>Last Name: " + lastName + "<br>");
		pw.println("<br>Username: " + username + "<br>");
		pw.println("<br>Password: " + password + "<br>");
		pw.println("<br>Email: " + email + "<br>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<br>");
	}

}

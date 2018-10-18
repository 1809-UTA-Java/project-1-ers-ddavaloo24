package com.revature.model;

import java.io.PrintWriter;

import com.revature.util.StyleUtil;

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
		
		StyleUtil.employeeMainMenuStyle(pw);
		
		pw.println("<h1 id=\"head\">This is the Employee Main Menu</h1>");
		pw.println("<br>");
		pw.println("<p id=\"message\">Hello <br>" + name + "</p>");
		pw.println("<br>");
		pw.println("<div id=\"options\">");
		pw.println("<a href=\"createreimbursement.html\">Create a New Reimbursement</a>");
		pw.println("<br>");
		pw.println("<a href=\"/ERS-Servlet/reimbursements\">My Reimbursements</a>");
		pw.println("<br>");
		pw.println("<a href=\"/ERS-Servlet/employees/" + id + "\">View and Update Personal Information</a>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"main-menu/logout\">Logout</a>");
		pw.println("</div>");
		pw.println("</body> </html> ");

	}

	@Override
	public String viewInfo() {
		return ("ID: " + id + ",Name:" + this.getFirstName() + " " + this.getLastName());
	}
	
	public void viewAllInfo(PrintWriter pw) {
		pw.println("<p id=\"message\">Employee ID:<br>" + id + "</p>");
		pw.println("<p id=\"message\">First Name:<br>" + firstName + "</p>");
		pw.println("<p id=\"message\">Last Name:<br>" + lastName + "</p>");
		pw.println("<p id=\"message\">Username:<br>" + username + "</p>");
		pw.println("<p id=\"message\">Password:<br>" + password + "</p>");
		pw.println("<p id=\"message\">Email:<br>" + email + "</p>");
		pw.println("<br>");
		pw.println("<br>");
	}

}

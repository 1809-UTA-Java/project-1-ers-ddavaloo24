package com.revature.model;

import java.io.PrintWriter;
import java.util.ArrayList;

import com.revature.repository.ReimbursementDao;

public class EmployeeUser extends User {

	public EmployeeUser(int id, String fn, String ln, String un, String pw, String em, int pos) {
		super(id, fn, ln, un, pw, em, pos);
	}

	public EmployeeUser(String firstName, String lastName, String username, String password, String email,
			int position) {
		super(firstName, lastName, username, password, email, position);
	}

	@Override
	public void viewHome(PrintWriter pw) {
		pw.println("<html> <body> ");
		pw.println("THIS IS THE EMPLOYEE HOME PAGE");
		pw.println("<br>");
		pw.println("<a href=\"createreimbursement.html\">Create a New Reimbursement</a>");
		pw.println("<br>");
		pw.println("<a href=\"/ERS-Servlet/reimbursements\">My Reimbursements</a>");
		pw.println("<br>");
		pw.println("<a href=\"main-menu/logout\">Logout</a>");
		pw.println("<br>");
		pw.println("</body> </html> ");

	}

	@Override
	public void viewPendingReqs() {
		
		
		
	}

	@Override
	public void viewInfo() {
		// TODO Auto-generated method stub

	}

}

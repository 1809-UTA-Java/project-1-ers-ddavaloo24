package com.revature.model;

import java.io.PrintWriter;

import com.revature.util.StyleUtil;

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
		StyleUtil.managerMainMenuStyle(pw);
	
		pw.println("<h1 id=\"head\">This is the Manager Main Menu</h1>");
		pw.println("<br>");
		pw.println("<p id=\"message\">Hello <br>" + name + "</p>");
		pw.println("<br>");
		pw.println("<div id=\"options\">");
		pw.println("<a href=\"/ERS-Servlet/reimbursements\">All Reimbursements</a>");
		pw.println("<br>");
		pw.println("<a href=\"/ERS-Servlet/employees\">View Employee Information</a>");
		pw.println("<br>");
		pw.println("<br>");
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
		// TODO Auto-generated method stub
		return null;

	}

}

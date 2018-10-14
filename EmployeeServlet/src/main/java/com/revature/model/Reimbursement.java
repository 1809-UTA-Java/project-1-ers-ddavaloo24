package com.revature.model;

import java.io.PrintWriter;
import java.sql.Timestamp;

public class Reimbursement {
	int id;
	double amount;
	String description;
	// RECEIPT
	Timestamp time_submitted;
	Timestamp time_resolved;
	int id_author;
	int id_resolver;
	int type;
	int status;

	public Reimbursement(int id, double amount, String description, Timestamp time_submitted, Timestamp time_resolved,
			int id_author, int id_resolver, int type, int status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.time_submitted = time_submitted;
		this.time_resolved = time_resolved;
		this.id_author = id_author;
		this.id_resolver = id_resolver;
		this.type = type;
		this.status = status;
	}

	public Reimbursement(double amount, String description, int id_author, int type, int status) {
		super();
		this.amount = amount;
		this.description = description;
		this.time_submitted = new Timestamp(System.currentTimeMillis());
		this.time_resolved = null;
		this.id_author = id_author;
		this.id_resolver = 0;
		this.type = type;
		this.status = status;
	}

	public String viewReimbursement() {
		String strDouble = String.format("%.2f", amount);
		return ("ID: " + id + " ,Amount: $" + strDouble + "\n");
	}

	public void viewReimbursementFull(PrintWriter pw) {
		
		String strDouble = String.format("%.2f", amount);
		
		pw.println("Reimbursement ID: " + id + "<br>");
		pw.println("<br>Amount Requested: $" + strDouble + "<br>");
		pw.println("<br>Description: " + description + "<br>");
		pw.println("<br>Date and Time submitted: " + time_submitted + "<br>");
		pw.println("<br>Date and Time resolved: " + time_resolved + "<br>");
		pw.println("<br>Request Type: " + type + "<br>");

		if (status == 1) {
			pw.println("<br>Status: Pending<br>");
			pw.println("<br>");
			pw.println("<form action=\"cancel\" method=\"post\">"
					+ "<button type=\"submit\">Delete Current Request</button>"
					+ "</form>");
			
		}
		else if(status == 2) {
			pw.println("<br>Status: Approved<br>");
		} else {
			pw.println("<br>Status: Denied<br>");
		}
	}

	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public Timestamp getTime_submitted() {
		return time_submitted;
	}

	public Timestamp getTime_resolved() {
		return time_resolved;
	}

	public int getId_author() {
		return id_author;
	}

	public int getId_resolver() {
		return id_resolver;
	}

	public int getType() {
		return type;
	}

	public int getStatus() {
		return status;
	}
}

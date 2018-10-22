package com.revature.model;

import java.io.PrintWriter;
import java.sql.Timestamp;

import com.revature.repository.UserDao;

/**
 * 
 * Blueprint for a reimbursement with descriptors that match the database table
 * housing them. There are methods to view the reimbursements in a short form
 * used for links and a long form for displaying all fields to both employee and
 * manager
 * 
 * @author Daria Davaloo
 *
 */
public class Reimbursement {
	int id;
	double amount;
	String description;
	byte[] image;
	Timestamp time_submitted;
	Timestamp time_resolved;
	int id_author;
	int id_resolver;
	int type;
	int status;

	// Constructors
	public Reimbursement(int id, double amount, String description, byte[] image, Timestamp time_submitted,
			Timestamp time_resolved, int id_author, int id_resolver, int type, int status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.image = image;
		this.time_submitted = time_submitted;
		this.time_resolved = time_resolved;
		this.id_author = id_author;
		this.id_resolver = id_resolver;
		this.type = type;
		this.status = status;
	}

	public Reimbursement(double amount, String description, byte[] image, int id_author, int type, int status) {
		super();
		this.amount = amount;
		this.description = description;
		this.image = image;
		this.time_submitted = new Timestamp(System.currentTimeMillis());
		this.time_resolved = null;
		this.id_author = id_author;
		this.id_resolver = 0;
		this.type = type;
		this.status = status;
	}

	// View the reimbursement as a short form of id and amount
	public String viewReimbursement() {
		String strDouble = String.format("%.2f", amount);
		return ("ID: " + id + " , Amount: $" + strDouble + "\n");
	}

	// View entire reimbursement with all fields formatted using html
	public void viewReimbursementFull(PrintWriter pw) {

		User user = UserDao.retrieveUserByID(id_author);
		String name = user.getFirstName() + " " + user.getLastName();
		String strDouble = String.format("%.2f", amount);

		pw.println("<div id=\"options\">");

		pw.println("<p id=\"message\">Reimbursement Author:<br>" + name + "</p><br>");
		pw.println("<p id=\"message\">Amount Requested:<br>$" + strDouble + "</p><br>");
		pw.println("<p id=\"message\">Description:<br>" + description + "</p><br>");
		pw.println("<p id=\"message\">Date and Time submitted:<br>" + time_submitted + "</p><br>");
		pw.println("<p id=\"message\">Date and Time resolved:<br>" + time_resolved + "</p><br>");

		if (type == 1) {
			pw.println("<p id=\"message\">Request Type:<br>Medical</p><br>");
		} else if (type == 2) {
			pw.println("<p id=\"message\">Request Type:<br>Travel</p><br>");
		} else if (type == 3) {
			pw.println("<p id=\"message\">Request Type:<br>Business Expense</p><br>");
		}

		if (status == 1) {
			pw.println("<p id=\"message\">Status:<br>Pending</p><br>");
		} else if (status == 2) {
			pw.println("<p id=\"message\">Status:<br>Approved</p><br>");
		} else {
			pw.println("<p id=\"message\">Status:<br>Denied</p><br>");
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

	public byte[] getImage() {
		return image;
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

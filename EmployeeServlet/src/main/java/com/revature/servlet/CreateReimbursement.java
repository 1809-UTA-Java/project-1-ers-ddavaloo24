package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementDao;

@WebServlet("/createreimbursementhandler")
@SuppressWarnings("serial")
public class CreateReimbursement extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);

		if (session != null) {

			double amount = Double.parseDouble(req.getParameter("amount"));
			String typeString = req.getParameter("type");
			String description = req.getParameter("description");
			int id_author = (Integer) session.getAttribute("id");

			int type;
			if (typeString.equals("Medical")) {
				type = 1;
			} else if (typeString.equals("Travel")) {
				type = 2;
			} else {
				type = 3;
			}

			Reimbursement reim = new Reimbursement(amount, description, id_author, type, 1);
			boolean result = ReimbursementDao.insertReimbursement(reim);

			if (result) {
				pw.println("Pending reimbursement created. You will not be redirected to the main menu");
			} else {
				pw.println("Failed to make a new reimbursement. You will not be redirected to the main menu");
			}

			resp.setHeader("Refresh", "3; URL=main-menu");
		} else {
			pw.println("BRO YOU GOTTA LOGIN FIRST!! WE ARE TAKING YOU HOME TO LOGIN MY DUDE");
			resp.setHeader("Refresh", "3; URL=home");
		}

	}
}

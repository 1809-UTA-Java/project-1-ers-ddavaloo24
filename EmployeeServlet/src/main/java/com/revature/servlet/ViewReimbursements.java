package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementDao;
import com.revature.repository.UserDao;

@WebServlet("/reimbursements")
@SuppressWarnings("serial")
public class ViewReimbursements extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);

		if (session != null) {
			int id = (Integer) session.getAttribute("id");
			ArrayList<Reimbursement> reim = ReimbursementDao.retrieveReimbursementsByAuthor(id);

			int i = 0;
			pw.println("Pending Requests: \n");
			for (Reimbursement re : reim) {
				if (re.getStatus() == 1) {
					pw.println(re.viewReimbursement());
					i++;
				}
			}
			if (i == 0)
				pw.println("None");

			i = 0;
			pw.println("Approved Requests: \n");
			for (Reimbursement re : reim) {
				if (re.getStatus() == 2) {
					pw.println(re.viewReimbursement());
					i++;
				}
			}
			if (i == 0)
				pw.println("None");

			i = 0;
			pw.println("Denied Requests: \n");
			for (Reimbursement re : reim) {
				if (re.getStatus() == 3) {
					pw.println(re.viewReimbursement());
					i++;
				}
			}
			if (i == 0)
				pw.println("None");

		} else {
			pw.println("BRO YOU GOTTA LOGIN FIRST!! WE ARE TAKING YOU HOME TO LOGIN MY DUDE");
			resp.setHeader("Refresh", "3; URL=home");
		}

	}
}

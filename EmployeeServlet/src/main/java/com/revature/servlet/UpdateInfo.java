package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.repository.UserDao;
import com.revature.util.LoginUtil;

/**
 * 
 * Servlet that uses the update employee fields form to update the different
 * information fields
 * 
 * @author Daria Davaloo
 *
 */
@WebServlet("/employees/updateinfo")
@SuppressWarnings("serial")
public class UpdateInfo extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();

		if (session != null) {
			int id = (Integer) session.getAttribute("id");
			String field = req.getParameter("field");
			String changeInfo = req.getParameter("changeinfo");
			boolean validator = true;

			pw.println("<html><body style=\"background-color: lightblue;\">");
			
			// Validate the information they want to update
			if (changeInfo.equals("First Name")) {
				if (!LoginUtil.nameChecker(field)) {
					validator = false;

					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Your first name must be 2-20 characters and contain no special characters<br></p>");
				}
			} else if (changeInfo.equals("Last Name")) {
				if (!LoginUtil.nameChecker(field)) {
					validator = false;

					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Your last name must be 2-20 characters and contain no special characters<br></p>");
				}
			} else if (changeInfo.equals("Username")) {
				if (!LoginUtil.usernameChecker(field)) {
					validator = false;

					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Your username must be 8-12 characters, unique, and contain no spaces<br></p>");
				}
			} else if (changeInfo.equals("Password")) {
				if (!LoginUtil.passwordChecker(field)) {
					validator = false;

					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Your password must be 8-12 characters and contain no spaces<br></p>");
				}
			}

			if (!validator) {
				pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
						+ "Update failed!<br>Redirecting back to the main menu...</p>");
			} else {
				boolean result = UserDao.updateEmployeeByID(id, changeInfo, field);
				if (result) {
					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Update successful!<br> Redirecting back to the main menu...</p>");
				} else {
					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Update failed!<br> Redirecting back to the main menu...</p>");
				}
			}

			resp.setHeader("Refresh", "3; URL=/ERS-Servlet/main-menu");

		} else {
			pw.println("<html><body style=\"background-color: #f27171;\">");
			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "You must be logged in to access this page.<br>Sending you to the login page</p>");
			resp.setHeader("Refresh", "3; URL=main-menu");
		}

		pw.println("</body> </html> ");
		pw.close();

	}
}

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
			
			boolean result = UserDao.updateEmployeeByID(id, changeInfo, field);
			
			pw.println("<html><body style=\"background-color: lightblue;\">");
			if (result) {
				pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
						+ "Update successful! Redirecting back to the main menu</p>");
			} else {
				pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
						+ "Update failed! Redirecting back to the main menu</p>");
			}

			resp.setHeader("Refresh", "3; URL=/ERS-Servlet/main-menu");

		} else {
			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "You must be logged in to access this page.<br>Sending you to the login page</p>");			
			resp.setHeader("Refresh", "3; URL=main-menu");
		}
		
		pw.println("</body> </html> ");
		pw.close();

	}
}

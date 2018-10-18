package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.EmployeeUser;
import com.revature.model.ManagerUser;
import com.revature.model.User;
import com.revature.repository.UserDao;

@SuppressWarnings("serial")
@WebServlet("/main-menu/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		PrintWriter pw = resp.getWriter();

		if (session != null) {

			int id = (Integer) session.getAttribute("id");
			User user = UserDao.retrieveUserByID(id);

			if (user instanceof EmployeeUser) {
				pw.println("<html><body style=\"background-color: lightblue;\">");
			} else if (user instanceof ManagerUser) {
				pw.println("<html><body style=\"background-color: #cf9ce5;\">");
			}

			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "BRO YOU ARE LOGGED OUT</p>");
			pw.println("</body></html>");

			session.invalidate();

			resp.setHeader("Refresh", "2; URL=/ERS-Servlet/home");
		}
		
		pw.close();
	}
}

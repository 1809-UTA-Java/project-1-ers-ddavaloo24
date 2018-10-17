package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.User;
import com.revature.repository.UserDao;

@SuppressWarnings("serial")
@WebServlet("/main-menu")
public class MainMenu extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		PrintWriter pw = resp.getWriter();

		if (session != null) {
			int id = (Integer) session.getAttribute("id");
			User user = UserDao.retrieveUserByID(id);
			String name = user.getFirstName() + " " + user.getLastName();
			user.viewHome(pw, name);
		} else {

			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "You must be logged in to access this page.<br>Sending you to the login page</p>");
			pw.println("</body> </html> ");			
			
			resp.setHeader("Refresh", "3; URL=home");
		}

		pw.close();
	}
}

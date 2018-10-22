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

/**
 * 
 * Servlet which handles logging in the user and creating a session if the user
 * presented valid credentials
 * 
 * @author Daria Davaloo
 *
 */
@SuppressWarnings("serial")
@WebServlet("/logging")
public class LoginPage extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Invalidate any old sessions
		HttpSession pastSesh = req.getSession(false);
		if (pastSesh != null) {
			pastSesh.invalidate();
		}

		PrintWriter pw = resp.getWriter();

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = User.login(username, password);

		if (user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("id", user.getId());
			session.setAttribute("username", username);
			resp.sendRedirect("main-menu");
		}

		else {
			pw.println("<html><body style=\"background-color: #f27171;\">");
			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "Failed to login. You will not be redirected to the home page" + "</p>");
			pw.println("</body></html>");

			resp.setHeader("Refresh", "2; URL=/ERS-Servlet/home");
		}

		pw.close();

	}

}

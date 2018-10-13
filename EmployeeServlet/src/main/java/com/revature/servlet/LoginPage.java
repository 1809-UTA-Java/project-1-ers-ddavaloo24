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

@SuppressWarnings("serial")
@WebServlet("/logging")
public class LoginPage extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User user = User.login(username, password);

		if (user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("username", username);
			
			// TODO: MAYBE ADD MORE ATTRIBUTES TO TAKE WITH ME LATER
			
			resp.sendRedirect("main-menu");
			
		} else {
			pw.println("Failed to login. You will not be redirected to the home page");
			resp.setHeader("Refresh", "2; URL=home");
		}

	}

}

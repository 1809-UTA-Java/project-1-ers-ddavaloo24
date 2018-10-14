package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.EmployeeUser;
import com.revature.model.ManagerUser;
import com.revature.model.User;
import com.revature.repository.UserDao;
import com.revature.util.LoginUtil;

@SuppressWarnings("serial")
@WebServlet("/accountcreated")
public class AccountCreated extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user;
		PrintWriter pw = resp.getWriter();

		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String position = req.getParameter("position");

		boolean firstCheck = LoginUtil.nameChecker(firstName);
		boolean lastCheck = LoginUtil.nameChecker(lastName);
		boolean userCheck = LoginUtil.usernameChecker(username);
		boolean passCheck = LoginUtil.passwordChecker(password);
		
		pw.println("<html> <body> ");

		if (firstCheck && lastCheck && userCheck && passCheck) {
			if (position.equals("Employee")) {
				user = new EmployeeUser(firstName, lastName, username, password, email, 1);

			} else {
				user = new ManagerUser(firstName, lastName, username, password, email, 2);
			}

			UserDao.insertUser(user);
			pw.println("Congrats on the new account! You will now be redirected to the home page");

		} else if (!firstCheck || !lastCheck) {
			pw.println("Failed to create the account. You will now be redirected to the home page");
			pw.println("<br>");
			pw.println("Please make sure your first and last name meet the requirements");
		} else if (!userCheck) {
			pw.println("Failed to create the account. You will now be redirected to the home page ");
			pw.println("<br>");
			pw.println("Make sure your username is unique and meet the size requirements and contains no spaces");
		} else if (!passCheck) {
			pw.println("Failed to create the account. You will now be redirected to the home page");
			pw.println("<br>");
			pw.println("Please make sure your passowrd has no spaces and meets the size requirements");
		}
		
		pw.println("</body></html>");

		resp.setHeader("Refresh", "4; URL=home");
	}

}

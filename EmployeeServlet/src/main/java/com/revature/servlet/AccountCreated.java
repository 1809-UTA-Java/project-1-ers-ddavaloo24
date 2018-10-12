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

@SuppressWarnings("serial")
@WebServlet("/accountcreated")
public class AccountCreated extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		User user;
		
		PrintWriter pw = resp.getWriter();
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String position = req.getParameter("position");
		
		if(position.equals("Employee")) {
			user = new EmployeeUser(firstName, lastName, username, password, email, 1);		
			
		}
		else {
			user = new ManagerUser(firstName, lastName, username, password, email, 2);
		}
		
		boolean result = UserDao.insertUser(user);
		
		
		if(result) pw.println("Congrats on the new account! You will now be redirected to the home page");
		else pw.println("Failed to create the account. You will not be redirected to the home page");
		
		//resp.sendRedirect("home");
		
	}

}

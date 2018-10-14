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
			
			System.out.println(id + " " + field + " " + changeInfo);

			boolean result = UserDao.updateEmployeeByID(id, changeInfo, field);
			
			if (result) {
				pw.println("Update successful! Redirecting back to the main menu");
			} else {
				pw.println("Update failed! Redirecting back to the main menu");
			}

			resp.setHeader("Refresh", "3; URL=/ERS-Servlet/main-menu");

		} else {
			pw.println("BRO YOU GOTTA LOGIN FIRST!! WE ARE TAKING YOU HOME TO LOGIN MY DUDE");
			resp.setHeader("Refresh", "3; URL=main-menu");
		}

	}
}

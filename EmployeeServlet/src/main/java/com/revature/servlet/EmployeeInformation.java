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

import com.revature.model.EmployeeUser;
import com.revature.model.ManagerUser;
import com.revature.model.User;
import com.revature.repository.UserDao;

@WebServlet("/employees/*")
@SuppressWarnings("serial")
public class EmployeeInformation extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		String path = req.getPathInfo();

		if (session != null) {

			int id = (Integer) session.getAttribute("id");
			User user = UserDao.retrieveUserByID(id);

			pw.println("<html><body>");

			if (user instanceof ManagerUser) {

				ArrayList<EmployeeUser> emps = UserDao.retrieveAllEmployees();

				if (path == null || path.equals("/")) {
					pw.println("All Employees: \n");
					for (EmployeeUser e : emps) {
						pw.println("<br>");
						pw.println("<a href=\"employees/" + e.getId() + "\">");
						pw.println(e.viewInfo());
						pw.println("</a>");
					}
				}

				String[] pathSplits = path.split("/");

				if (pathSplits.length != 2) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}

				int empID = Integer.parseInt(pathSplits[1]);
				EmployeeUser found = null;
				for (EmployeeUser e : emps) {
					if (e.getId() == id) {
						found = e;
					}
				}

				if (found != null) {

					if (session.getAttribute("empID") != null) {
						session.removeAttribute("empID");
					}

					session.setAttribute("empID", found.getId());
					found.viewAllInfo(pw);
				}

			} else if (user instanceof EmployeeUser) {
				if (path == null || path.equals("/")) {
					pw.println("YOU DO NOT HAVE ACCESS TO THIS PAGE. REDIRECTING BACK TO THE MAIN MENU");
					resp.setHeader("Refresh", "3; URL=/ERS-Servlet/main-menu");
					return;
				}

				String[] pathSplits = path.split("/");

				if (pathSplits.length != 2) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}

				int empID = Integer.parseInt(pathSplits[1]);

				if (id != empID) {
					pw.println("YOU DO NOT HAVE ACCESS TO THIS PAGE. REDIRECTING BACK TO THE MAIN MENU");
					resp.setHeader("Refresh", "3; URL=/ERS-Servlet/main-menu");
					return;
				}

				if (session.getAttribute("empID") != null) {
					session.removeAttribute("empID");
				}

				session.setAttribute("empID", user.getId());
				((EmployeeUser) user).viewAllInfo(pw);
			}

			pw.println("</body></html>");
		} else {
			pw.println("BRO YOU GOTTA LOGIN FIRST!! WE ARE TAKING YOU HOME TO LOGIN MY DUDE");
			resp.setHeader("Refresh", "3; URL=home");
		}

		pw.close();
	}
}

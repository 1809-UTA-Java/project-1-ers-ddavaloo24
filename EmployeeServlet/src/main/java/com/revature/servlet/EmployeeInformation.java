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
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.repository.ReimbursementDao;
import com.revature.repository.UserDao;
import com.revature.util.StyleUtil;

/**
 * 
 * Servlet that provides access to viewing employee information as both types of
 * users.
 * 
 * @author Daria Davaloo
 *
 */
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

			// As a manager, you can view all employee records as well as specific records
			if (user instanceof ManagerUser) {

				StyleUtil.managerViewEmployeeStyle(pw);
				ArrayList<EmployeeUser> emps = UserDao.retrieveAllEmployees();

				if (path == null || path.equals("/")) {
					pw.println("<div id=\"options\">");
					pw.println("All Employees: \n");
					for (EmployeeUser e : emps) {
						pw.println("<br>");
						pw.println("<a href=\"employees/" + e.getId() + "\">");
						pw.println(e.viewInfo());
						pw.println("</a>");
					}
					pw.println("</div>");
				}

				// Use split to view specific employees
				String[] pathSplits = path.split("/");
				if (pathSplits.length != 2) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}

				int empID = Integer.parseInt(pathSplits[1]);
				ArrayList<Reimbursement> reim = ReimbursementDao.retrieveReimbursementsByAuthor(empID);
				EmployeeUser found = null;

				for (EmployeeUser e : emps) {
					if (e.getId() == empID) {
						found = e;
					}
				}

				// View the information as well as display the pending accounts of the specific
				// employee
				if (found != null) {
					pw.println("<div id=\"options\">");

					found.viewAllInfo(pw);

					int i = 0;
					pw.println("<p id=\"message\">Pending Requests</p>");
					for (Reimbursement re : reim) {
						if (re.getStatus() == 1) {
							pw.println("<a href=\"/ERS-Servlet/reimbursements/" + re.getId() + "\">");
							pw.println(re.viewReimbursement());
							pw.println("</a>");
							i++;
						}
					}
					if (i == 0)
						pw.println("None");

					pw.println("</div>");
				}

			} else if (user instanceof EmployeeUser) {

				// As an employee you cannot view all records, just your own
				StyleUtil.employeerViewEmployeeStyle(pw);

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

				// View your record as well as option to update certain fields
				pw.println("<div id=\"options\">");
				((EmployeeUser) user).viewAllInfo(pw);
				pw.println("</div>");
				StyleUtil.updateEmployee(pw);
			}

			pw.println("</body></html>");
		} else {
			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "You must be logged in to access this page.<br>Sending you to the login page</p>");
			pw.println("</body> </html> ");
			resp.setHeader("Refresh", "3; URL=/ERS-Servlet/home");
		}

		pw.close();
	}
}

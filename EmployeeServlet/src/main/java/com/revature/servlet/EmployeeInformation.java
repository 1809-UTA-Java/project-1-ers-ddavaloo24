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

				if (found != null) {
					if (session.getAttribute("empID") != null) {
						session.removeAttribute("empID");
					}
					
					pw.println("<div id=\"options\">");

					session.setAttribute("empID", found.getId());
					found.viewAllInfo(pw);

					int i = 0;
					pw.println("Pending Requests <br>");
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

				if (session.getAttribute("empID") != null) {
					session.removeAttribute("empID");
				}
				
				pw.println("<div id=\"options\">");

				session.setAttribute("empID", user.getId());
				((EmployeeUser) user).viewAllInfo(pw);
				
				pw.println("</div>");
				pw.println("<div id=\"accountapp\">");
				pw.println("<form action=\"updateinfo\" method=\"post\">");
				pw.println("<p id=\"message\">Change account information:</p><br>\n" + "<p id=\"field\">Information field you want to change:</p>\n"
						+ "	<input list=\"changeinfo\" name=\"changeinfo\" required>\n"
						+ "	<datalist id=\"changeinfo\">\n" + "<option value=\"First Name\">\n"
						+ "	    <option value=\"Last Name\">\n" + "<option value=\"Email\">\n" + "	</datalist><br> ");
				pw.println("<br><p id=\"field\">Update field to:</p>\n" + "<input type=\"text\" name=\"field\" required>\n");
				pw.println("<br><button type=\"submit\">Change Info</button>");
				pw.println("</form>");
				pw.println("</div>");
			}

			pw.println("</body></html>");
		} else {
			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "You must be logged in to access this page.<br>Sending you to the login page</p>");
			pw.println("</body> </html> ");
			resp.setHeader("Refresh", "3; URL=home");
		}

		pw.close();
	}
}

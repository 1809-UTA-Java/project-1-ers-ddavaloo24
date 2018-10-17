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

@WebServlet("/reimbursements/*")
@SuppressWarnings("serial")
public class ViewReimbursements extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		String path = req.getPathInfo();
		ArrayList<Reimbursement> reim = new ArrayList<>();

		if (session != null) {

			int id = (Integer) session.getAttribute("id");
			User user = UserDao.retrieveUserByID(id);

			if (user instanceof ManagerUser) {
				reim = ReimbursementDao.retrieveAllReimbursements();
				StyleUtil.managerViewReimbursementStyle(pw);

			} else if (user instanceof EmployeeUser) {
				reim = ReimbursementDao.retrieveReimbursementsByAuthor(id);
				StyleUtil.employeeViewReimbursementStyle(pw);
			}

			if (path == null || path.equals("/")) {

				pw.println("<div id=\"options\">");

				int i = 0;
				pw.println("Pending Requests \n");
				for (Reimbursement re : reim) {
					if (re.getStatus() == 1) {
						pw.println("<br>");
						pw.println("<a href=\"reimbursements/" + re.getId() + "\">");
						pw.println(re.viewReimbursement());
						pw.println("</a>");
						i++;
					}
				}
				if (i == 0)
					pw.println("<br>None");

				i = 0;
				pw.println("<br><br>Approved Requests \n");
				for (Reimbursement re : reim) {
					if (re.getStatus() == 2) {
						pw.println("<br>");
						pw.println("<a href=\"reimbursements/" + re.getId() + "\">");
						pw.println(re.viewReimbursement());
						pw.println("</a>");
						i++;
					}
				}
				if (i == 0)
					pw.println("<br>None");

				if (user instanceof EmployeeUser) {
					i = 0;
					pw.println("<br><br>Denied Requests \n");
					for (Reimbursement re : reim) {
						if (re.getStatus() == 3) {
							pw.println("<br>");
							pw.println("<a href=\"reimbursements/" + re.getId() + "\">");
							pw.println(re.viewReimbursement());
							pw.println("</a>");
							i++;
						}
					}
					if (i == 0)
						pw.println("<br>None");

					pw.println("</div>");
				}
			}

			String[] pathSplits = path.split("/");
			if (pathSplits.length != 2) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}

			int reimID = Integer.parseInt(pathSplits[1]);
			Reimbursement found = null;
			for (Reimbursement r : reim) {
				if (r.getId() == reimID) {
					found = r;
				}
			}

			if (found != null) {

				User resolver = UserDao.retrieveUserByID(found.getId_resolver());
				String name = resolver.getFirstName() + " " + resolver.getLastName();

				if (session.getAttribute("reimID") != null) {
					session.removeAttribute("reimID");
				}
				session.setAttribute("reimID", found.getId());
				
				session.removeAttribute("imageBytes");
				found.viewReimbursementFull(pw);

				if (found.getImage() != null) {
					session.setAttribute("imageBytes", found.getImage());
					pw.println("<a href=\"/ERS-Servlet/receipt/" + found.getId() +  "\">Receipt</a>");
				}
				pw.println("</div>");

				if (user.getPosition() == 1 && found.getStatus() == 1) {

					pw.println("<div id=\"accountapp\">");
					pw.println("<form action=\"cancel\" method=\"post\">"
							+ "<button type=\"submit\">Delete Current Request</button>" + "</form>");
					pw.println("</div>");

				} else if (user.getPosition() == 2) {

					pw.println("<div id=\"accountapp\">");
					if (found.getStatus() == 1) {
						pw.println("<form action=\"approveordeny\" method=\"post\">");
						pw.println("<p id=\"field\">Choose to approve or deny the request</p>\n"
								+ "	<input list=\"approveordeny\" name=\"approveordeny\" required>\n"
								+ "	<datalist id=\"approveordeny\">\n" + "	  <option value=\"Approve\">\n"
								+ "	    <option value=\"Deny\">\n" + "	</datalist> ");
						pw.println("<br>");
						pw.println("<button type=\"submit\">Confirm</button>");
						pw.println("</form>");

					} else if (found.getStatus() == 2) {
						pw.println("<p id=\"field\">Approved by</p><br>" + name);
					} else {
						pw.println("<p id=\"field\">Denied by</p><br>" + name);
					}

					pw.println("</div>");
				}
			}
		} else {
			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "You must be logged in to access this page.<br>Sending you to the login page</p>");
			pw.println("</body> </html> ");

			resp.setHeader("Refresh", "3; URL=home");
		}

		pw.println("</body></html>");
		pw.close();
	}
}

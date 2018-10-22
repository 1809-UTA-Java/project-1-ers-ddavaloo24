package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.revature.model.ManagerUser;
//import com.revature.model.User;
import com.revature.repository.ReimbursementDao;
//import com.revature.repository.UserDao;

/**
 * 
 * Servlet used to approve or deny a reimbursement request from a manager
 * account. Retrieves the reimbursement id and resolver id from the session and
 * takes the resolution type to call the DAO and print a corresponding message
 * 
 * @author Daria Davaloo
 *
 */
@WebServlet("/reimbursements/approveordeny")
@SuppressWarnings("serial")
public class ApproveOrDeny extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();

		if (session != null) {
			String approveOrDeny = req.getParameter("approveordeny");
			int reimID = (Integer) session.getAttribute("reimID");
			int resID = (Integer) session.getAttribute("id");

			boolean result = ReimbursementDao.approveOrDenyReimbursementByID(reimID, approveOrDeny, resID);

			pw.println("<html><body style=\"background-color: #cf9ce5;\">");
			if (result) {
				if (approveOrDeny.equals("Approve")) {
					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Approval successful! Redirecting back to the main menu</p>");
				} else {
					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Denial successful! Redirecting back to the main menu</p>");
				}
			} else {
				if (approveOrDeny.equals("Approve")) {
					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Approval failed! Redirecting back to the main menu</p>");
				} else {
					pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
							+ "Denial failed! Redirecting back to the main menu</p>");
				}
			}

			pw.println("</body> </html> ");
			resp.setHeader("Refresh", "3; URL=/ERS-Servlet/main-menu");

		} else {
			pw.println("<html><body style=\"background-color: #f27171;\">");
			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "You must be logged in to access this page.<br>Sending you to the login page</p>");
			pw.println("</body> </html> ");
			resp.setHeader("Refresh", "3; URL=/ERS-Servlet/home");
		}

		pw.close();

	}
}

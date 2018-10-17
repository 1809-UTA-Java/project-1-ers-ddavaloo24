package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.repository.ReimbursementDao;

@SuppressWarnings("serial")
@WebServlet("/reimbursements/cancel")
public class CancelReimbursement extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doDelete(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		PrintWriter pw = resp.getWriter();
		
		pw.println("<html><body style=\"background-color: lightblue;\">");
		
		if(session != null) {
			int reimID = (Integer) session.getAttribute("reimID");
			
			boolean result = ReimbursementDao.deleteReimbursementByID(reimID);
			if(result) {
				pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
						+ "Deletion successful! Redirecting back to the main menu</p>");
			} else {
				pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
						+ "Deletion failed! Redirecting back to the main menu</p>");
			}
			
			resp.setHeader("Refresh", "3; URL=/ERS-Servlet/main-menu");
			
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

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

@WebServlet("/reimbursements/approveordeny")
@SuppressWarnings("serial")
public class ApproveOrDeny extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		
		if(session != null) {
			String approveOrDeny = req.getParameter("approveordeny");
			int reimID = (Integer) session.getAttribute("reimID");
			int resID = (Integer) session.getAttribute("id");
			
			boolean result = ReimbursementDao.approveOrDenyReimbursementByID(reimID, approveOrDeny, resID);
			
			if(result) {
				if(approveOrDeny.equals("Approve")) {
					pw.println("Approval successful! Redirecting back to the main menu");
				}
				else {
					pw.println("Denial successful! Redirecting back to the main menu");

				}
				
			} else {
				if(approveOrDeny.equals("Approve")) {
					pw.println("Approval failed! Redirecting back to the main menu");
				}
				else {
					pw.println("Denial failed! Redirecting back to the main menu");

				}
			}
			
			resp.setHeader("Refresh", "3; URL=/ERS-Servlet/main-menu");
			
		} else {
			pw.println("BRO YOU GOTTA LOGIN FIRST!! WE ARE TAKING YOU HOME TO LOGIN MY DUDE");
			resp.setHeader("Refresh", "3; URL=home");
		}
		
	}
}

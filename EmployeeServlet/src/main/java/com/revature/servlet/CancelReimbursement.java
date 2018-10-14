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
		
		if(session != null) {
			int reimID = (Integer) session.getAttribute("reimID");
			
			boolean result = ReimbursementDao.deleteReimbursementByID(reimID);
			if(result) {
				pw.println("Deletion successful! Redirecting back to the main menu");
			} else {
				pw.println("Deletion failed! Redirecting back to the main menu");
			}
			
			resp.setHeader("Refresh", "3; URL=/ERS-Servlet/main-menu");
			
		} else {
			pw.println("BRO YOU GOTTA LOGIN FIRST!! WE ARE TAKING YOU HOME TO LOGIN MY DUDE");
			resp.setHeader("Refresh", "3; URL=home");
		}
		
	}
}

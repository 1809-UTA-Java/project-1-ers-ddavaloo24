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

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementDao;

@WebServlet("/reimbursements/*")
@SuppressWarnings("serial")
public class ViewReimbursements extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		String path = req.getPathInfo();
		

		if (session != null) {

			int id = (Integer) session.getAttribute("id");
			ArrayList<Reimbursement> reim = ReimbursementDao.retrieveReimbursementsByAuthor(id);
			
			pw.println("<html><body>");
			
			if (path == null || path.equals("/")) {
				int i = 0;
				pw.println("Pending Requests: \n");
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
					pw.println("None");

				i = 0;
				pw.println("<br><br>Approved Requests: \n");
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
					pw.println("None");

				i = 0;
				pw.println("<br><br>Denied Requests: \n");
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
					pw.println("None");
			}
			
			String[] pathSplits = path.split("/");
			
			if(pathSplits.length != 2) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			int reimID = Integer.parseInt(pathSplits[1]);
			Reimbursement found = null;
			for(Reimbursement r : reim) {
				if(r.getId() == reimID) {
					found = r;
				}
			}
			
			if(found != null) {
				
				if(session.getAttribute("reimID") != null) {
					session.removeAttribute("reimID");
				}
				
				session.setAttribute("reimID", found.getId());				
				found.viewReimbursementFull(pw);
			}
			
			pw.println("</body></html>");
		} else {
			pw.println("BRO YOU GOTTA LOGIN FIRST!! WE ARE TAKING YOU HOME TO LOGIN MY DUDE");
			resp.setHeader("Refresh", "3; URL=home");
		}
		
		pw.close();
	}
}

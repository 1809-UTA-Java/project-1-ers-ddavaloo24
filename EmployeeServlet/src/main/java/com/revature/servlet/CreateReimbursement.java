package com.revature.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementDao;

/**
 * 
 * Servlet that processes the createreimbursement.html form and retrieve the
 * text and file (if applicable) from the form and inserts the information into
 * the database
 * 
 * @author Daria Davaloo
 *
 */
@WebServlet("/createreimbursementhandler")
@SuppressWarnings("serial")
@MultipartConfig
public class CreateReimbursement extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);

		if (session != null) {

			double amount = Double.parseDouble(req.getParameter("amount"));
			String typeString = req.getParameter("type");
			String description = req.getParameter("description");
			int id_author = (Integer) session.getAttribute("id");
			byte[] bytes = null;

			if (req.getPart("image") != null) {
				Part image = req.getPart("image");
				InputStream is = image.getInputStream();
				bytes = IOUtils.toByteArray(is);
			}

			int type;
			if (typeString.equals("Medical")) {
				type = 1;
			} else if (typeString.equals("Travel")) {
				type = 2;
			} else {
				type = 3;
			}

			Reimbursement reim = new Reimbursement(amount, description, bytes, id_author, type, 1);
			boolean result = ReimbursementDao.insertReimbursement(reim);

			pw.println("<html><body style=\"background-color: lightblue;\">");

			if (result) {
				pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
						+ "Pending reimbursement created.<br>You will now be redirected to the main menu...</p>");
			} else {
				pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
						+ "Failed to make a new reimbursement.<br>You will now be redirected to the main menu...</p>");
			}

			pw.println("</body> </html> ");

			resp.setHeader("Refresh", "3; URL=main-menu");
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

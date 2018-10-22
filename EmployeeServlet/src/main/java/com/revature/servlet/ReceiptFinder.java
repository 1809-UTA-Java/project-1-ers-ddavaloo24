package com.revature.servlet;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * Servlet that uses the reimbursement id to give a unique URI to the receipt
 * found in the current user and display the image
 * 
 * @author Daria Davaloo
 *
 */
@WebServlet("/receipt/*")
@SuppressWarnings("serial")
public class ReceiptFinder extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String path = req.getPathInfo();

		if (session != null) {
			resp.setContentType("image/jpg");

			if (path == null || path.equals("/")) {
				resp.sendRedirect("reimbursements");
				return;
			}

			String[] pathSplits = path.split("/");
			if (pathSplits.length != 2) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}

			//Retrieve image bytes from the session
			byte[] image = (byte[]) session.getAttribute("imageBytes");

			ByteArrayInputStream bis = new ByteArrayInputStream(image);
			BufferedImage receipt = ImageIO.read(bis);
			File imageFile = new File(
					"/home/developer/Workspace/project-1-ers-ddavaloo24/EmployeeServlet/src/main/webapp/receipt.jpg");
			ImageIO.write(receipt, "jpg", imageFile);

			ServletOutputStream out;
			out = resp.getOutputStream();
			FileInputStream fin = new FileInputStream(imageFile);

			BufferedInputStream bin = new BufferedInputStream(fin);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			int ch = 0;

			//Print the bytes and display the full image using reimbursement id as URI
			while ((ch = bin.read()) != -1) {
				bout.write(ch);
			}

			// Delete the file after read and close all streams
			imageFile.delete();
			bin.close();
			fin.close();
			bout.close();
			out.close();

		} else {
			PrintWriter pw = resp.getWriter();
			pw.println("<html><body style=\"background-color: #f27171;\">");
			pw.println("<p style=\"text-align:center;font-size:40px;margin-top:200px;font-weight:bold;\">"
					+ "You must be logged in to access this page.<br>Sending you to the login page</p>");
			pw.println("</body> </html> ");

			resp.setHeader("Refresh", "3; URL=home");
		}
	}
}

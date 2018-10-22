package com.revature.model;

import java.io.PrintWriter;

import com.revature.util.StyleUtil;

/**
 * 
 * Model for a default Manager which extends from the abstract class User. Has
 * methods to view the main menu, and an unused inherited method.
 * 
 * @author Daria Davaloo
 *
 */
public class ManagerUser extends User {

	// Constructors
	public ManagerUser(int id, String fn, String ln, String un, String pw, String em, int pos) {
		super(id, fn, ln, un, pw, em, pos);
	}

	public ManagerUser(String firstName, String lastName, String username, String password, String email,
			int position) {
		super(firstName, lastName, username, password, email, position);
	}

	// View the home page
	@Override
	public void viewHome(PrintWriter pw, String name) {
		pw.println("<html> <body> ");
		StyleUtil.managerMainMenuStyle(pw);

		pw.println("<h1 id=\"head\">This is the Manager Main Menu</h1>");
		pw.println("<br>");
		pw.println("<p id=\"message\">Hello <br>" + name + "</p>");
		pw.println("<br>");
		pw.println("<div id=\"options\">");
		pw.println("<a href=\"/ERS-Servlet/reimbursements\">All Reimbursements</a>");
		pw.println("<br>");
		pw.println("<a href=\"/ERS-Servlet/employees\">View Employee Information</a>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"main-menu/logout\">Logout</a>");
		pw.println("</div>");
		pw.println("</body> </html> ");
	}

	@Override
	public String viewInfo() {
		return null;

	}

//	public void sendMail() {
//		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//
//		Properties props = System.getProperties();
//		props.setProperty("mail.smtp.host", "smtp.gmail.com");
//		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
//		props.setProperty("mail.smtp.socketFactory.fallback", "false");
//		props.setProperty("mail.smtp.port", "465");
//		props.setProperty("mail.smtp.socketFactory.port", "465");
//		
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.debug", "true");
//		props.put("mail.store.protocol", "pop3");
//		props.put("mail.transport.protocol", "smtp");
//		
//		final String uname = "daria.davaloo@gmail.com";
//		final String pword = "Football360!";
//		
//		try {
//			Session session = Session.getDefaultInstance(props, new Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(uname, pword);
//				}
//			});
//
//			Message msg = new MimeMessage(session);
//
//			msg.setFrom(new InternetAddress("daria.davaloo@gmail.com"));
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tadeckert7@gmail.com", false));
//			msg.setSubject("Hello");
//			msg.setText("How are you");
//			msg.setSentDate(new Date());
//			Transport.send(msg);
//
//			System.out.println("Message sent.");
//		} catch (MessagingException e) {
//			System.out.println("Erreur d'envoi, cause: " + e);
//		}
//
//	}

}

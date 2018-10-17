package com.revature.util;

import java.io.PrintWriter;

public class StyleUtil {

	public static void employeeMainMenuStyle(PrintWriter pw) {
		pw.println("<style>\n" + "body {\n" + "	background-color: lightblue;\n" + "}\n" + "\n" + "p, form {\n"
				+ "	margin: auto;\n" + "	text-align: center;\n" + "}\n" + "\n" + "a:link, a:visited {\n"
				+ "    background-color: #627bf7;\n" + "    color: white;\n" + "    text-align: center; \n"
				+ "    text-decoration: none;\n" + "    display: inline-block;\n" + "    padding: 20px;\n"
				+ "	 margin: auto;\n" + "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "\n"
				+ "a:hover, a:active {\n" + "    background-color: #405df2;\n" + "    padding: 20px;\n"
				+ "	 margin: auto;\n" + "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "\n" + "#head {\n"
				+ "	margin: auto;\n" + "	margin-top: 50px;\n" + "	text-align: center;\n" + "	font-size: 45px;\n"
				+ "}\n" + "\n" + "#message {\n" + "	margin: auto;\n" + "	text-align: center;\n"
				+ "	padding: 15px;\n" + "	font-size: 24px;\n" + "	font-weight: bold;\n" + "}\n" + "\n"
				+ "#options {\n" + "	margin: auto;\n" + "	margin-top: 25px;\n" + "	width: 400px;\n"
				+ "	height: 350px;\n" + "	padding: 50px 30px;\n" + "	padding-top: 10px;\n"
				+ "	background-color: white;\n" + "   text-align: center; \n" + "}\n" + "</style>");
	}

	public static void managerMainMenuStyle(PrintWriter pw) {
		pw.println("<style>\n" + "body {\n" + "	background-color: #cf9ce5;\n" + "}\n" + "\n" + "p, form {\n"
				+ "	margin: auto;\n" + "	text-align: center;\n" + "}\n" + "\n" + "a:link, a:visited {\n"
				+ "    background-color: #995fb2;\n" + "    color: white;\n" + "    text-align: center; \n"
				+ "    text-decoration: none;\n" + "    display: inline-block;\n" + "    padding: 20px;\n"
				+ "	 margin: auto;\n" + "	 margin: 10px;\n" + "	 width: 300px;\n" +

				"}\n" + "\n" + "a:hover, a:active {\n" + "    background-color: #8d36b2;\n" + "    padding: 20px;\n"
				+ "	 margin: auto;\n" + "	 margin: 10px;\n" + "	 width: 300px;\n" +

				"}\n" + "\n" + "#head {\n" + "	margin: auto;\n" + "	margin-top: 50px;\n"
				+ "	text-align: center;\n" + "	font-size: 45px;\n" + "}\n" + "\n" + "#message {\n" + "	margin: auto;\n"
				+ "	text-align: center;\n" + "	padding: 15px;\n" + "	font-size: 24px;\n" + "	font-weight: bold;\n"
				+ "}\n" + "\n" + "#options {\n" + "	margin: auto;\n" + "	margin-top: 25px;\n" + "	width: 400px;\n"
				+ "	height: 300px;\n" + "	padding: 50px 30px;\n" + "	padding-top: 10px;\n"
				+ "	background-color: white;\n" + "   text-align: center; \n" + "}\n" + "</style>");
	}

	public static void employeeViewReimbursementStyle(PrintWriter pw) {
		pw.println("<html>");
		pw.println("<style>\n" + "body {\n" + "	background-color: lightblue;\n" + "}\n" + "\n" + "p, form {\n"
				+ "	margin: auto;\n" + "	text-align: center;\n" + "}\n" + "\n" + "button {\n"
				+ "	padding:3px 6px;\n" + "	margin-top: 10px;\n" + "}\n" + "\n" + "a:link, a:visited {\n"
				+ "    background-color: #627bf7;\n" + "    color: white;\n" + "    text-align: center; \n"
				+ "    text-decoration: none;\n" + "    display: inline-block;\n" + "    padding: 20px;\n"
				+ "	 margin: auto;\n" + "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "\n"
				+ "a:hover, a:active {\n" + "    background-color: #405df2;\n" + "    padding: 20px;\n"
				+ "	 margin: auto;\n" + "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "#message {\n"
				+ "	margin: auto;\n" + "	text-align: center;\n" + "	padding: 10px;\n" + "	font-size: 16px;\n"
				+ "	font-weight: bold;\n" + "}\n" + "\n" + "#field {\n" + "	margin: auto;\n" + "   margin-top:-15px;"
				+ "	text-align: center;\n" + "	padding-bottom: 3px;\n" + "}\n" + "\n" + "#options {\n"
				+ "	margin: auto;\n" + "	margin-top: 50px;\n" + "	width: 400px;\n" + "	padding: 50px 30px;\n"
				+ "	padding-top: 20px;\n" + "	background-color: white;\n" + "   text-align: center; \n"
				+ "   min-height: 150px; \n" + "}\n" + "\n" + "#accountapp {\n" + "	width: 400px;\n"
				+ "	height: 30px;\n" + "	margin: auto;\n" + "	margin-top: 10px;\n" + "	padding: 45px 30px;\n"
				+ "	background-color: white;\n" + "	text-align: center;\n" + "}\n" + "</style>");
		pw.println("<body>");
	}

	public static void managerViewReimbursementStyle(PrintWriter pw) {
		pw.println("<html>");
		pw.println("<style>\n" + "body {\n" + "	background-color: #cf9ce5;\n" + "}\n" + "\n" + "p, form {\n"
				+ "	margin: auto;\n" + "	text-align: center;\n" + "}\n" + "button {\n" + "	padding:3px 6px;\n"
				+ "	margin-top: 10px;\n" + "}\n" + "\n" + "a:link, a:visited {\n" + "    background-color: #995fb2;\n"
				+ "    color: white;\n" + "    text-align: center; \n" + "    text-decoration: none;\n"
				+ "    display: inline-block;\n" + "    padding: 20px;\n" + "	 margin: auto;\n"
				+ "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "\n" + "a:hover, a:active {\n"
				+ "    background-color: #8d36b2;\n" + "    padding: 20px;\n" + "	 margin: auto;\n"
				+ "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "\n" + "#field {\n" + "	margin: auto;\n"
				+ "   margin-top:-20px;" + "	text-align: center;\n" + "	padding-bottom: 3px;\n" + "}\n"
				+ "#message {\n" + "	margin: auto;\n" + "	text-align: center;\n" + "	padding: 10px;\n"
				+ "	font-size: 16px;\n" + "	font-weight: bold;\n" + "}\n" + "#options {\n" + "	margin: auto;\n"
				+ "	margin-top: 20px;\n" + "	width: 400px;\n" + "	padding: 50px 30px;\n"
				+ "	padding-top: 50px;\n" + "	background-color: white;\n" + "   text-align: center; \n"
				+ "   min-height: 150px; \n" + "}\n" + "#accountapp {\n" + "	width: 400px;\n" + "	height: 30px;\n"
				+ "	margin: auto;\n" + "	margin-top: 10px;\n" + "	padding: 45px 30px;\n"
				+ "	background-color: white;\n" + "	text-align: center;\n" + "}\n" + "</style>");
		pw.println("<body>");

	}

	public static void managerViewEmployeeStyle(PrintWriter pw) {
		pw.println("<html>");
		pw.println("<style>\n" + "body {\n" + "	background-color: #cf9ce5;\n" + "}\n" + "\n" + "p, form {\n"
				+ "	margin: auto;\n" + "	text-align: center;\n" + "}\n" + "button {\n" + "	padding:3px 6px;\n"
				+ "	margin-top: 10px;\n" + "}\n" + "\n" + "a:link, a:visited {\n" + "    background-color: #995fb2;\n"
				+ "    color: white;\n" + "    text-align: center; \n" + "    text-decoration: none;\n"
				+ "    display: inline-block;\n" + "    padding: 20px;\n" + "	 margin: auto;\n"
				+ "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "\n" + "a:hover, a:active {\n"
				+ "    background-color: #8d36b2;\n" + "    padding: 20px;\n" + "	 margin: auto;\n"
				+ "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "\n" + "#field {\n" + "	margin: auto;\n"
				+ "   margin-top:-15px;" + "	text-align: center;\n" + "	padding-bottom: 3px;\n" + "}\n"
				+ "#message {\n" + "	margin: auto;\n" + "	text-align: center;\n" + "	padding: 10px;\n"
				+ "	font-size: 16px;\n" + "	font-weight: bold;\n" + "}\n" + "#options {\n" + "	margin: auto;\n"
				+ "	margin-top: 50px;\n" + "	width: 400px;\n" + "	padding: 30px 30px;\n"
				+ "	padding-top: 30px;\n" + "	background-color: white;\n" + "   text-align: center; \n"
				+ "   min-height: 100px; \n" + "}\n" + "</style>");
		pw.println("<body>");

	}

	public static void employeerViewEmployeeStyle(PrintWriter pw) {
		pw.println("<html>");
		pw.println("<style>\n" + "body {\n" + "	background-color: lightblue;\n" + "}\n" + "\n" + "p, form {\n"
				+ "	margin: auto;\n" + "	text-align: center;\n" + "}\n" + "\n" + "button {\n"
				+ "	padding:3px 6px;\n" + "	margin-top: 10px;\n" + "}\n" + "\n" + "a:link, a:visited {\n"
				+ "    background-color: #627bf7;\n" + "    color: white;\n" + "    text-align: center; \n"
				+ "    text-decoration: none;\n" + "    display: inline-block;\n" + "    padding: 20px;\n"
				+ "	 margin: auto;\n" + "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "\n"
				+ "a:hover, a:active {\n" + "    background-color: #405df2;\n" + "    padding: 20px;\n"
				+ "	 margin: auto;\n" + "	 margin: 10px;\n" + "	 width: 300px;\n" + "}\n" + "#message {\n"
				+ "	margin: auto;\n" + "	text-align: center;\n" + "	padding: 10px;\n" + "	font-size: 20px;\n"
				+ "	font-weight: bold;\n" + "}\n" + "\n" + "#field {\n" + "	margin: auto;\n" + "   margin-top:-15px;"
				+ "	text-align: center;\n" + "	padding-bottom: 3px;\n" + "}\n" + "\n" + "#options {\n"
				+ "	margin: auto;\n" + "	margin-top: 50px;\n" + "	width: 400px;\n" + "	padding: 30px 30px;\n"
				+ "	padding-top: 30px;\n" + "	background-color: white;\n" + "   text-align: center; \n"
				+ "   min-height: 100px; \n" + "}\n" + "\n" + "#accountapp {\n" + "	width: 400px;\n"
				+ "	min-height: 100px;\n" + "	margin: auto;\n" + "	margin-top: 10px;\n"
				+ "	padding: 45px 30px;\n" + "	background-color: white;\n" + "	text-align: center;\n" + "}\n"
				+ "</style>");
		pw.println("<body>");
	}
}

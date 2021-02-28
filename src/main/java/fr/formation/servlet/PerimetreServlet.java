package fr.formation.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/perimetre")
public class PerimetreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String message = "";
		PrintWriter pw = response.getWriter();

		pw.print("<!DOCTYPE html>");
		pw.print("<html><head>");
		pw.print("<meta charset=\"UTF-8\"><title>Insert title here</title></head>");
		pw.print("<body>");
		pw.print("<h1>Calcul du perimetre d'un cercle</h1>");
		pw.print("<form action='perimetre' method='GET'>");
		pw.print("Rayon : <input type='text' name='rayon'>");
		pw.print("<button>Calcul !</button>");
		pw.print("</form>");

		String rayon = request.getParameter("rayon");
		if (rayon != null) {
			try {
				int r = Integer.parseInt(rayon);
				if (r <= 0) {
					message = "Le rayon doit etre strictement positif !";
				}else {
					double perim = 2 * Math.PI * r;	
					message = "Le perimetre du cercle de rayon " + r + " est  : " + perim;
				}
				
				
			} catch (NumberFormatException e) {
				message = "Le rayon doit etre un entier";
			}
		}
		
		pw.print("<p>" + message+"</p>");
		
		pw.print("</body>");
		pw.print("</html>");
		
	}

}

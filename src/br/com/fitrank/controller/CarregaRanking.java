package br.com.fitrank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CarregaRanking
 */

public class CarregaRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String modalidade = null;
	String modo = null;  
	String turno = null;
	String periodo = null;
	String fav = null;
	String padrao = null;
	
	
    public CarregaRanking() {
    	
    }
    
    private void inicia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	modalidade = (String) request.getParameter("modalidade");
    	modo = (String) request.getParameter("modo");  
    	turno = (String) request.getParameter("turno");
    	periodo = (String) request.getParameter("periodo");
//    	fav = (String) request.getParameter("fav");
//    	padrao = (String) request.getParameter("default");
    	
    	request.setAttribute("modalidade", modalidade);
		request.setAttribute("modo", modo);
		request.setAttribute("turno", turno);
		request.setAttribute("periodo", periodo);
		
		RequestDispatcher rd = null;
		
		rd = request.getRequestDispatcher("ranking.jsp");
		
		rd.forward(request, response);
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}

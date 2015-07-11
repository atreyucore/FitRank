package br.com.fitrank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.service.PessoaServico;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

public class CarregaEscolhaRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public CarregaEscolhaRanking() {
      
    }

   
  protected void inicia(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   
	   FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));
	   User facebookUser = facebookClient.fetchObject("me", User.class);

	   
//TODO	   Recuperar configurção de favorito aqui!
	   request.setAttribute("token", request.getParameter("token"));
	   
//	   request.setAttribute("ids", ids);
//	   request.setAttribute("profPicUrl", profPicUrls);
	   
	   
	   RequestDispatcher rd = request.getRequestDispatcher("/escolhaRanking.jsp");  
	   rd.forward(request,response);  
   }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}

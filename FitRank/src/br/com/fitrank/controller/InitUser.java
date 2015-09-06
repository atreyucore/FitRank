package br.com.fitrank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.service.PessoaServico;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class InitUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public InitUser() {
      
    }

   
  protected void inicia(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   
	   FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));
	   User facebookUser = facebookClient.fetchObject("me", User.class);

	   Pessoa pessoa = new Pessoa();
	   PessoaServico pessoaServico = new PessoaServico();
	   if (pessoaServico.lePessoaServico(facebookUser) == null ) {
		   pessoa = pessoaServico.adicionaPessoaServico(facebookUser);
	   }
	   
//TODO	   Recuperar configuração de favorito aqui!
	   request.setAttribute("token", request.getParameter("token"));
	   
//	   request.setAttribute("ids", ids);
//	   request.setAttribute("profPicUrl", profPicUrls);
	   
	   
	   RequestDispatcher rd = request.getRequestDispatcher("/escolheModalidade.jsp");  
	   rd.forward(request,response);  
   }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}

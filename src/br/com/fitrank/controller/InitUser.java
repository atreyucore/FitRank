package br.com.fitrank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.service.AmizadeServico;
import br.com.fitrank.service.PessoaServico;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

public class InitUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	PessoaServico pessoaServico = new PessoaServico();
	AmizadeServico amizadeServico = new AmizadeServico();
   
    public InitUser() {
      
    }

   
  protected void inicia(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   
	   FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));
	   User facebookUser = facebookClient.fetchObject("me", User.class);
	   Connection<User> friendsFB = facebookClient.fetchConnection("me/friends", User.class, Parameter.with("fields", "name, id"));
	   
	   
	   Pessoa pessoa = new Pessoa();
	   
	   if (pessoaServico.lePessoaServico(facebookUser) == null ) {
		   pessoa = pessoaServico.adicionaPessoaServico(facebookUser);
	   }
	   
//	   String[] profPicUrls = new String[friendsFB.getData().size()];
//	   String[] ids = new String[friendsFB.getData().size()];
	   for (int i=0;i < friendsFB.getData().size(); i++) {
		  String idAmigo = friendsFB.getData().get(i).getId();
		  
		  if (amizadeServico.leAmizadeServico(facebookUser.getId(), idAmigo) == null ) {
			  amizadeServico.adicionaAmizadeServico(facebookUser.getId(), idAmigo);
		  }
//		  ids[i] = id;
//		  profPicUrls[i] = friendsFB.getData().get(i).getPicture() != null ? friendsFB.getData().get(i).getPicture().getUrl() : "imagem/401.png" ;
		 
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

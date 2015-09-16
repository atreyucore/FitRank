package br.com.fitrank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitrank.modelo.fb.PostFitnessFB;
import br.com.fitrank.service.ConfiguracaoServico;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;

public class CarregaEscolhaRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public CarregaEscolhaRanking() {
      
    }

   
  protected void inicia(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   
	   FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));
	   Connection<PostFitnessFB> fitConnection = facebookClient.fetchConnection("me/fitness." + request.getParameter("modalidade"), PostFitnessFB.class, Parameter.with("limit", "1"));
	   ConfiguracaoServico configuracaoServico = new ConfiguracaoServico();
	   
		List<PostFitnessFB> postsFit = new ArrayList<PostFitnessFB>();
		
		for(PostFitnessFB postFit : fitConnection.getData()) {
			postsFit.add(postFit);
		}
		
	   RequestDispatcher rd = null;
	   
	   if ( postsFit.size() == 0) {
		   request.setAttribute("erro", "Você não tem nenhum registro nesta categoria.");
		   rd = request.getRequestDispatcher("/escolheModalidade.jsp");
	   } else {
		   rd = request.getRequestDispatcher("/escolhaRanking.jsp"); 
	   }
//TODO gvsribeiro Recuperar dados de Pessoa!
//TODO	   Recuperar configurção de favorito aqui!
//	   configuracaoServico.leConfiguracaoPadraoModalidade(idPessoa, modalidade)
	   
	   request.setAttribute("token", request.getParameter("token"));
	   	     
	   rd.forward(request,response);  
   }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}

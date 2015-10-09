package br.com.fitrank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitrank.modelo.Aplicativo;
import br.com.fitrank.modelo.fb.PostFitness.PostFitnessFB;
import br.com.fitrank.util.ConstantesFitRank;
import br.com.fitrank.util.DateConversor;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

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
    	
    	FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));
    	
    	
    	modalidade = (String) request.getParameter("modalidade");
    	modo = (String) request.getParameter("modo");  
    	turno = (String) request.getParameter("turno");
    	periodo = (String) request.getParameter("periodo");
//    	fav = (String) request.getParameter("fav");
//    	padrao = (String) request.getParameter("default");
    	
    	String meuValorRanking = calculaValorRankingFB(facebookClient, "me");
    	
    	Connection<User> friendsFB = facebookClient.fetchConnection("me/friends", User.class, Parameter.with("fields", "name, id"));
    	
    	request.setAttribute("modalidade", modalidade);
		request.setAttribute("modo", modo);
		request.setAttribute("turno", turno);
		request.setAttribute("periodo", periodo);
		
		RequestDispatcher rd = null;
		
		rd = request.getRequestDispatcher("ranking.jsp");
		
		rd.forward(request, response);
    	
    }
    
    private String calculaValorRankingFB(FacebookClient facebookClient, String idUsuario) {
    	
    	List<PostFitnessFB> postsFit = new ArrayList<PostFitnessFB>();
    	
//    	Connection<PostFitnessFB> fitConnection = facebookClient.fetchConnection(idUsuario + "/fitness." + defineModalidade(modalidade), PostFitnessFB.class, Parameter.with("limit", "30"));
    	
//    	for (PostFitnessFB postFit : fitConnection.getData()) {
    		
//    		switch (periodo) {
//	    		case "0":
//	    			//TODO É isso mesmo? Dia anterior?
//	    			if ( postFit.getStartTime().after(DateConversor.getPreviousDay()) ) {
//	    				postsFit.add(postFit);
//	    			}
//	    			break;
//	    		case "1":
//	    			if ( postFit.getStartTime().after(DateConversor.getPreviousWeek()) ) {
//	    				postsFit.add(postFit);
//	    			}
//	    			break;
//	    		case "2":
//	    			if ( postFit.getStartTime().after(DateConversor.getPreviousMonth()) ) {
//	    				postsFit.add(postFit);
//	    			}
//	    			break;
//	    		default:
//	    			break;
//	    		}
//    		
//    		switch (turno) {
//				case "dia":
//					int hora = DateConversor.getHourFromDate(postFit.getStartTime());
//					if ( hora >= 18 && hora < 5 ) { //das 18 as 5 da manha é considerado noite.
//						postsFit.remove(postFit);
//					}
//					break;
//				case "noite":
//					if ( DateConversor.getHourFromDate(postFit.getStartTime()) < 18 ) {
//						postsFit.remove(postFit);
//					}
//					break;
//			    default:
//			    	break;
//			}
    		
//		}
    	
    	return "";
    }
    
    private String defineModalidade(String parameter) {
		switch (parameter) {
		case ConstantesFitRank.MODALIDADE_CAMINHADA:
			return "walks";
			
		case ConstantesFitRank.MODALIDADE_CORRIDA:
			return "runs";

		case ConstantesFitRank.MODALIDADE_BICICLETA:
			return "bikes";

		default:
			return null;
			
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}

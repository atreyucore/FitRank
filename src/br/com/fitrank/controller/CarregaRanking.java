package br.com.fitrank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.modelo.Ranking;
import br.com.fitrank.modelo.RankingPessoa;
import br.com.fitrank.modelo.fb.PostFitness.PostFitnessFB;
import br.com.fitrank.service.ConfiguracaoServico;
import br.com.fitrank.service.RankingPessoaServico;
import br.com.fitrank.service.RankingServico;
import br.com.fitrank.util.ConstantesFitRank;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

/**
 * Servlet implementation class CarregaRanking
 */

public class CarregaRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RankingPessoaServico rankingPessoaServico = new RankingPessoaServico();
	
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
    	
    	List<RankingPessoa> listRankingPessoas = new ArrayList<RankingPessoa>();
    	ConfiguracaoServico configuracaoServico = new ConfiguracaoServico();
    	RankingServico rankingServico = new RankingServico();
    	
    	modalidade = request.getAttribute("modalidade") == null ? (String) request.getParameter("modalidade") : (String) request.getAttribute("modalidade") ;
    	modo = request.getAttribute("modo") == null ? (String) request.getParameter("modo") : (String) request.getAttribute("modo") ;
    	turno = request.getAttribute("turno") == null ? (String) request.getParameter("turno") : (String) request.getAttribute("turno") ;
    	periodo = request.getAttribute("periodo") == null ? (String) request.getParameter("periodo") : (String) request.getAttribute("periodo") ;
//    	fav = (String) request.getParameter("fav");
//    	padrao = (String) request.getParameter("default");
    	
//    	String meuValorRanking = calculaValorRankingFB(facebookClient, "me");
    	
    	User me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id"));
//    	Connection<User> friendsFB = facebookClient.fetchConnection("me", User.class, Parameter.with("fields", "id"));
    	
    	Configuracao configuracao = new Configuracao();
    	
    	configuracao.setIdPessoa(me.getId());
    	configuracao.setModalidade(modalidade);
		configuracao.setIntervaloData(definePeriodo(periodo));
		
		switch (defineModo(modo)) {
			case ConstantesFitRank.VELOCIDADE_MEDIA:
				listRankingPessoas = rankingPessoaServico.geraRankingVelocidadeMedia(configuracao);
				break;
			case ConstantesFitRank.DISTANCIA:
				listRankingPessoas = rankingPessoaServico.geraRankingDistancia(configuracao);
			default:
				break;
		}
		
		configuracao.setFavorito(false);
		configuracao.setPadraoModalidade(false);
		configuracao.setModo(modo);
		configuracao = configuracaoServico.adicionaConfiguracao(configuracao);
		
		if(configuracao.getIdConfiguracao() != ConstantesFitRank.INT_RESULTADO_INVALIDO){
		
			Ranking ranking = new Ranking();
			ranking.setId_configuracao(configuracao.getIdConfiguracao());
			ranking = rankingServico.adicionaRanking(ranking);
			
			if(ranking.getId_ranking() != ConstantesFitRank.INT_RESULTADO_INVALIDO){
				rankingPessoaServico.gravaRankingPessoa(listRankingPessoas, ranking.getId_ranking());
			}
		}
    	
    	request.setAttribute("modalidade", modalidade);
		request.setAttribute("modo", modo);
//		request.setAttribute("turno", turno);
		request.setAttribute("periodo", periodo);
		request.setAttribute("listaRanking", listRankingPessoas);
		
		request.setAttribute("token", (String) request.getParameter("token"));
		
		
		RequestDispatcher rd = null;
		
		rd = request.getRequestDispatcher("ranking.jsp");
		
		rd.forward(request, response);
    	
    }
    
    private String defineModo(String modo) {
		switch (modo) {
			case "velocidade":
				this.modo = ConstantesFitRank.VELOCIDADE_MEDIA;
				return ConstantesFitRank.VELOCIDADE_MEDIA;
			case "distancia":
				this.modo = ConstantesFitRank.DISTANCIA;
				return ConstantesFitRank.DISTANCIA;
			default:
				return modo;
		}
	}
    
    
    private String defineTurno(String turno) {
	    switch(turno) {
			case "dia":
				return ConstantesFitRank.DIA;
			case "noite":
				return ConstantesFitRank.NOITE;
			default:
				return turno;
		}
	    
    }
    
    private String definePeriodo(String periodo) {
		switch(periodo) {
			case "0":
				//Dia
				return ConstantesFitRank.DIA;
			case "1":
				//Semana

				return ConstantesFitRank.SEMANA;
			case "2":
				//Mes
				return ConstantesFitRank.MES;
			case "3":
				//Mes
				return ConstantesFitRank.ANO;
			default:
				return periodo;
		}
		
    }
    
    private String defineModalidade(String modalidade) {
		switch (modalidade) {
			case "walks":
				return ConstantesFitRank.MODALIDADE_CAMINHADA;
	
			case "runs":
				return ConstantesFitRank.MODALIDADE_CORRIDA;
	
			case "bikes":
				return ConstantesFitRank.MODALIDADE_BICICLETA;
	
			default:
				return modalidade;
	
			}
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
    
//    private String defineModalidade(String parameter) {
//		switch (parameter) {
//		case ConstantesFitRank.MODALIDADE_CAMINHADA:
//			return "walks";
//			
//		case ConstantesFitRank.MODALIDADE_CORRIDA:
//			return "runs";
//
//		case ConstantesFitRank.MODALIDADE_BICICLETA:
//			return "bikes";
//
//		default:
//			return null;
//			
//		}
//	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}

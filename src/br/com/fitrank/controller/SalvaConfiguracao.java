package br.com.fitrank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.service.ConfiguracaoServico;

/**
 * Servlet implementation class SalvaConfiguracao
 */
//@WebServlet("/SalvaConfiguracao")
public class SalvaConfiguracao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConfiguracaoServico configuracaoServico = new ConfiguracaoServico(); 
	Configuracao configuracao = new Configuracao();
	
	String modalidade = null;
	String modo = null;  
	String turno = null;
	String periodo = null;
	String fav = null;
	String padrao = null;
	
    public SalvaConfiguracao() {
    	super();
    }
    
    private void inicia(HttpServletRequest request, HttpServletResponse response) {
    	
    	FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));
    	
    	User facebookUser = facebookClient.fetchObject("me", User.class);
    	
    	modalidade = (String) request.getParameter("modalidade");
    	modo = (String) request.getParameter("modo");  
    	turno = (String) request.getParameter("turno");
    	periodo = (String) request.getParameter("periodo");
    	fav = (String) request.getParameter("fav");
    	padrao = (String) request.getParameter("default");
    	
    	String userID = (String) facebookUser.getId();
    	Boolean isValido = isParametrosValidos(modalidade, modo, turno, periodo, fav, padrao);
    	
    	if (isValido) {
        	
        	configuracao.setModalidade(modalidade);
        	configuracao.setModo(modo);
        	configuracao.setDiaNoite(turno);
        	configuracao.setIntervaloData(periodo);
        	configuracao.setFavorito(fav.equals("S") ? true : false);
        	configuracao.setPadraoModalidade(padrao.equals("S") ? true : false);
        	configuracao.setIdPessoa(userID);
        	
        	if (fav.equals("S")) {
        		Configuracao configFav = configuracaoServico.leConfiguracaoFavorita(userID);
    	    	if (configFav == null) {
    	    		configuracaoServico.adicionaConfiguracao(configuracao);
    	    	} else {
    	    		configuracao.setIdConfiguracao(configFav.getIdConfiguracao());
    	    		configuracaoServico.atualizaConfiguracao(configuracao);
    	    	}
        	} else if (padrao.equals("S")) {
        		Configuracao configPadrao = configuracaoServico.leConfiguracaoPadraoModalidade(userID, modalidade);
        		if (configPadrao == null) {
    	    		configuracaoServico.adicionaConfiguracao(configuracao);
    	    	} else {
    	    		configuracao.setIdConfiguracao(configPadrao.getIdConfiguracao());
    	    		configuracaoServico.atualizaConfiguracao(configuracao);
    	    	}
        	}
        	
    	} else {
    		//ocorreu algum erro
    	}

    }
    
	private boolean isParametrosValidos(String modalidade, String modo, String turno,
			String periodo, String fav, String padrao) {
		
		switch(modo) {
			case "velocidade":
				this.modo = "V";
				break;
			case "distancia":
				this.modo = "D";
				break;
			default:
				return false;
		}
		
		switch(turno) {
			case "dia":
				this.turno = "D";
				break;
			case "noite":
				this.turno = "N";
				break;
			default:
				return false;
		}
		
		switch(periodo) {
			case "0":
				//Dia
				this.periodo = "D";
				break;
			case "1":
				//Semana
				this.periodo = "S";
				break;
			case "2":
				//Mes
				this.periodo = "M";
				break;
			default:
				return false;
		}
		
		switch(fav) {
			case "S":
				this.fav = "S";
				break;
			case "N":
				this.fav = "N";
				break;
			default:
				return false;
		}
		
		switch(padrao) {
			case "S":
				this.padrao = "S";
				break;
			case "N":
				this.padrao = "N";
				break;
			default:
				return false;
		}
		
		if (padrao.equals(fav)) {
			return false;
		}
		
		return true;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}
	
}

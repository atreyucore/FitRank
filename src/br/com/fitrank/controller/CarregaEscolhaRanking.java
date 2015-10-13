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
import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.modelo.PostFitness;
import br.com.fitrank.modelo.fb.PostFitness.PostFitnessFB;
import br.com.fitrank.service.AplicativoServico;
import br.com.fitrank.service.ConfiguracaoServico;
import br.com.fitrank.service.PessoaServico;
import br.com.fitrank.service.PostFitnessServico;
import br.com.fitrank.util.ConstantesFitRank;
import br.com.fitrank.util.DateConversor;
import br.com.fitrank.util.PostFitnessUtil;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

public class CarregaEscolhaRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AplicativoServico aplicativoServico     = new AplicativoServico();
	
	ConfiguracaoServico configuracaoServico = new ConfiguracaoServico();
	
	PessoaServico pessoaServico				= new PessoaServico();
	
	PostFitnessServico postFitnessServico   = new PostFitnessServico();
	
	public CarregaEscolhaRanking() {

	}

	protected void inicia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;
		
		ArrayList<Aplicativo> aplicativos = new ArrayList<Aplicativo>();
		
		List<PostFitnessFB> postsFit = new ArrayList<PostFitnessFB>();
		
		ArrayList<Aplicativo> aplicativosNaoInserir = new ArrayList<Aplicativo>();
		
		FacebookClient facebookClient = new DefaultFacebookClient(
				request.getParameter("token"));
		
		User facebookUser = facebookClient.fetchObject("me", User.class);
		
		String fav = (String) request.getParameter("fav");
		
		Configuracao configuracao = null;
		
		if (! fav.equals("S") && fav != null ) {
			
			String modalidade = defineModalidade((String) request.getParameter("modalidade"));
			
			Connection<PostFitnessFB> fitConnection = facebookClient
					.fetchConnection("me/fitness." + modalidade,
							PostFitnessFB.class, Parameter.with("limit", "1"));
			
			for (PostFitnessFB postFit : fitConnection.getData()) {
	
				postsFit.add(postFit);
	
				// Adiciona aplicativo à Lista
				Aplicativo aplicativo = new Aplicativo();
	
				aplicativo.setId_aplicativo(postFit.getApplication().getId());
				aplicativo.setNome(postFit.getApplication().getName());
	
				if (!aplicativos.contains(aplicativo)) {
					aplicativos.add(aplicativo);
				}
			}
	
			// Insere aplicativos que estão sendo utilizados pelo usuário, no banco.
			if (aplicativos.size() > 1) {
	
				aplicativosNaoInserir = aplicativoServico
						.leListaAplicativosServico(aplicativos);
	
				if (aplicativosNaoInserir != null) {
					aplicativos.removeAll(aplicativosNaoInserir);
				}
	
				if (aplicativos.size() > 1)
					aplicativoServico.adicionaAplicativosServico(aplicativos);
			}
	
			if (aplicativos.size() == 1) {
				if (aplicativoServico.leAplicativoServico(aplicativos.get(0)
						.getId_aplicativo()) == null) {
					aplicativoServico.adicionaAplicativoServico(aplicativos.get(0));
				}
			}
	
			
	
			if (postsFit.size() == 0) {
				request.setAttribute("erro",
						"Você não tem nenhum registro nesta categoria.");
				request.setAttribute("token", (String) request.getParameter("token"));
				request.getRequestDispatcher("/escolheModalidade.jsp").forward(request, response);;
				return;
			} else {
				rd = request.getRequestDispatcher("/CarregaRanking");
			}
		// TODO gvsribeiro Recuperar dados de Pessoa!
		// TODO Recuperar configurção de favorito aqui!
			
				configuracao = configuracaoServico.leConfiguracaoPadraoModalidade( facebookUser.getId(), (String) request.getParameter("modalidade"));
		}
		
		if (fav.equals("S")){
			configuracao = configuracaoServico.leConfiguracaoFavorita(facebookUser.getId());
			rd = request.getRequestDispatcher("/CarregaRanking");
		}
		
		if (configuracao != null) {
			request.setAttribute("modalidade", configuracao.getModalidade());
			request.setAttribute("modo", configuracao.getModo());
			request.setAttribute("turno", configuracao.getDiaNoite());
			request.setAttribute("favorito", configuracao.isFavorito() ? "S" : "N");
			request.setAttribute("default", configuracao.isPadraoModalidade() ? "S" : "N");
			request.setAttribute("periodo", configuracao.getIntervaloData());
			
		} else {
			request.setAttribute("modalidade", request.getParameter("modalidade") != null ? (String) request.getParameter("modalidade") : "");
		}
		
		
		request.setAttribute("token", (String) request.getParameter("token"));
		
		rd.forward(request, response);
	}
	
	private void handlePrimeiraAtividade(String modalidade, FacebookClient facebookClient, User facebookUser ) {

		switch (modalidade) {
			case ConstantesFitRank.MODALIDADE_CAMINHADA:
				
				if (pessoaServico.lePessoaServico(facebookUser).getData_ultima_atualizacao_walks() == null) {
					executaPrimeiraAtualizacao(ConstantesFitRank.MODALIDADE_CAMINHADA, facebookClient, facebookUser);
				}
				
				break;
			case ConstantesFitRank.MODALIDADE_CORRIDA:
				
				if (pessoaServico.lePessoaServico(facebookUser).getData_ultima_atualizacao_runs() == null) {
					
				}
				
				break;	
			case ConstantesFitRank.MODALIDADE_BICICLETA:
				
				if (pessoaServico.lePessoaServico(facebookUser).getData_ultima_atualizacao_bikes() == null) {
					
				}
				
				break;	
			default:
				break;			
		}
	}
	
	
	private void executaPrimeiraAtualizacao(String modalidade, FacebookClient facebookClient, User facebookUser) {
		
		Connection<PostFitnessFB> fitConnection = facebookClient.fetchConnection("me/fitness." + defineModalidade(modalidade), 
				PostFitnessFB.class, Parameter.with("limit", "99999"));
		
		ArrayList<PostFitness> postsFit = new ArrayList<PostFitness>();
		
		
		for (PostFitnessFB postFit : fitConnection.getData()) {
			
			
			//id pessoa
			//modalidade
			
			// Adiciona aplicativo à Lista
			PostFitness postFitness= new PostFitness();

			postFitness.setId_publicacao(postFit.getId());
			postFitness.setId_pessoa(facebookUser.getId());
			
			
			postFitness.setId_app(postFit.getApplication().getId());
			
			switch (postFit.getApplication().getId()) {
				case ConstantesFitRank.ID_APP_NIKE:
					postFitness.setDistancia_percorrida(PostFitnessUtil.getNikeDistance(postFit.getDataCourse().getCourse().getTitle()));
					postFitness.setDuracao(PostFitnessUtil.getNikeDuration(postFit.getStartTime(), postFit.getEndTime()));
				case ConstantesFitRank.ID_APP_RUNTASTIC:
					postFitness.setDistancia_percorrida(PostFitnessUtil.getRuntasticDistance(postFit.getDataCourse().getCourse().getTitle()));
					postFitness.setDuracao(PostFitnessUtil.getRuntasticDuration(postFit.getDataCourse().getCourse().getTitle()));
				case ConstantesFitRank.ID_APP_RUNKEEPER:
					postFitness.setDistancia_percorrida(PostFitnessUtil.getRunKeeperDistance(postFit.getDataCourse().getCourse().getTitle()));
					postFitness.setDuracao(PostFitnessUtil.getRunKeeperDuration(postFit.getDataCourse().getCourse().getTitle()));
				default:
					break;
			}
			
			postFitness.setData_publicacao(DateConversor.DateToString(postFit.getStartTime()));
			
			postFitness.setUrl(postFit.getDataCourse().getCourse().getUrl());
			
			postFitness.setModalidade(modalidade);

			postsFit.add(postFitness);
			
		}
		
		
		postFitnessServico.adicionaListaPostFitnessServico(postsFit);
		
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
			return parameter;
			
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}

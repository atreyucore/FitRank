package br.com.fitrank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitrank.modelo.Aplicativo;
import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.modelo.Pessoa;
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

	AplicativoServico aplicativoServico = new AplicativoServico();

	ConfiguracaoServico configuracaoServico = new ConfiguracaoServico();

	PessoaServico pessoaServico = new PessoaServico();

	PostFitnessServico postFitnessServico = new PostFitnessServico();
	
	ArrayList<Aplicativo> aplicativos = new ArrayList<Aplicativo>();
	
	List<PostFitnessFB> postsFit = new ArrayList<PostFitnessFB>();
	
	ArrayList<Aplicativo> aplicativosNaoInserir = new ArrayList<Aplicativo>();
	
	public CarregaEscolhaRanking() {

	}

	protected void inicia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;

		aplicativos.clear();
		
		postsFit.clear();

		aplicativosNaoInserir.clear();

		FacebookClient facebookClient = new DefaultFacebookClient(
				request.getParameter("token"));

		User facebookUser = facebookClient.fetchObject("me", User.class);

		String fav = (String) request.getParameter("fav");

		String modalidade = (String) request.getParameter("modalidade");

		Configuracao configuracao = null;

		if (fav == null || !fav.equals("S")) {

			Date ultimaAtualizacao = handleUltimaAtividade(modalidade,
					facebookClient, facebookUser);

			if (ultimaAtualizacao != null) {

				int limit = calculaLimiteDeBusca(ultimaAtualizacao);

				limit = limit == 0 ? 1 : limit;

				String modalidadeFB = defineModalidade((String) request
						.getParameter("modalidade"));

				Connection<PostFitnessFB> fitConnection = facebookClient
						.fetchConnection("me/fitness." + modalidadeFB,
								PostFitnessFB.class,
								Parameter.with("limit", String.valueOf(limit)));

				verificaAplicativos(fitConnection);

				if (postsFit.size() == 0) {
					request.setAttribute("erro",
							"Voc� n�o tem nenhum registro nesta categoria.");
					request.setAttribute("token",
							(String) request.getParameter("token"));
					request.getRequestDispatcher("/escolheModalidade.jsp")
							.forward(request, response);

					return;
				} else {
					rd = request.getRequestDispatcher("/escolhaRanking.jsp");
				}
				// TODO gvsribeiro Recuperar dados de Pessoa!

				configuracao = configuracaoServico
						.leConfiguracaoPadraoModalidade(facebookUser.getId(),
								modalidade);
			}

		} else if (fav.equals("S")) {

			configuracao = configuracaoServico
					.leConfiguracaoFavorita(facebookUser.getId());

			if (configuracao == null) {
				request.setAttribute("erro",
						"Voc� ainda n�o cadastrou configura��o favorita.");
				request.setAttribute("token",
						(String) request.getParameter("token"));
				request.getRequestDispatcher("/escolheModalidade.jsp").forward(
						request, response);
				return;
			} else {
				rd = request.getRequestDispatcher("/CarregaRanking");
			}
		}

		if (configuracao != null) {
			request.setAttribute("modalidade", configuracao.getModalidade());
			request.setAttribute("modo", configuracao.getModo());
			request.setAttribute("turno", configuracao.getDiaNoite());
			request.setAttribute("favorito", configuracao.isFavorito() ? "S"
					: "N");
			request.setAttribute("default",
					configuracao.isPadraoModalidade() ? "S" : "N");
			request.setAttribute("periodo", configuracao.getIntervaloData());

		} else {

			request.setAttribute(
					"modalidade",
					request.getParameter("modalidade") != null ? (String) request
							.getParameter("modalidade") : "");
			request.setAttribute(
					"modo",
					request.getParameter("modo") != null ? (String) request
							.getParameter("modo") : "");
			request.setAttribute(
					"periodo",
					request.getParameter("periodo") != null ? (String) request
							.getParameter("periodo") : "");
		}

		request.setAttribute("token", (String) request.getParameter("token"));

		rd.forward(request, response);
	}

	private void verificaAplicativos(Connection<PostFitnessFB> fitConnection) {
		for (PostFitnessFB postFit : fitConnection.getData()) {

			postsFit.add(postFit);

			// Adiciona aplicativo � Lista
			Aplicativo aplicativo = new Aplicativo();

			aplicativo.setId_aplicativo(postFit.getApplication().getId());
			aplicativo.setNome(postFit.getApplication().getName());

			if (!aplicativos.contains(aplicativo)) {
				aplicativos.add(aplicativo);
			}
		}

		// Insere aplicativos que est�o sendo utilizados pelo
		// usu�rio, no banco.
		if (aplicativos.size() > 1) {

			aplicativosNaoInserir = aplicativoServico
					.leListaAplicativosServico(aplicativos);

			if (aplicativosNaoInserir != null) {
				
				ArrayList<Aplicativo> aplicativosAux = (ArrayList<Aplicativo>) aplicativos.clone();
				
				for(Aplicativo app :aplicativos) {
					for (Aplicativo appNaoInserir: aplicativosNaoInserir) {
						if( app.getId_aplicativo().equals(appNaoInserir.getId_aplicativo()) ){
							aplicativosAux.remove(app);
						}
					}	
				}
				
				aplicativos = aplicativosAux;
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

	}

	private int calculaLimiteDeBusca(Date ultimaAtualizacao) {
		return DateConversor.getDaysDifference(new Date(), ultimaAtualizacao) * 2;
	}

	private Date handleUltimaAtividade(String modalidade,
			FacebookClient facebookClient, User facebookUser) {
		Pessoa pessoa = new Pessoa();

		switch (modalidade) {
		case ConstantesFitRank.MODALIDADE_CAMINHADA:
			Date ultimoWalk = pessoaServico.lePessoaServico(facebookUser)
					.getData_ultima_atualizacao_walks();

			if (ultimoWalk == null) {
				// Primeira atividade desta modalidade
				pessoa = executaPrimeiraAtualizacao(
						ConstantesFitRank.MODALIDADE_CAMINHADA, facebookClient,
						facebookUser);

				ultimoWalk = pessoa.getData_ultima_atualizacao_walks();
			}

			return ultimoWalk;
		case ConstantesFitRank.MODALIDADE_CORRIDA:
			Date ultimoRuns = pessoaServico.lePessoaServico(facebookUser)
					.getData_ultima_atualizacao_runs();

			if (ultimoRuns == null) {
				// Primeira atividade desta modalidade
				pessoa = executaPrimeiraAtualizacao(
						ConstantesFitRank.MODALIDADE_CORRIDA, facebookClient,
						facebookUser);

				ultimoRuns = pessoa.getData_ultima_atualizacao_runs();
			}

			return ultimoRuns;
		case ConstantesFitRank.MODALIDADE_BICICLETA:
			Date ultimoBikes = pessoaServico.lePessoaServico(facebookUser)
					.getData_ultima_atualizacao_bikes();

			if (ultimoBikes == null) {
				// Primeira atividade desta modalidade
				pessoa = executaPrimeiraAtualizacao(
						ConstantesFitRank.MODALIDADE_BICICLETA, facebookClient,
						facebookUser);

				ultimoBikes = pessoa.getData_ultima_atualizacao_bikes();
			}

			return ultimoBikes;
		default:
			return null;
		}
	}

	private Pessoa executaPrimeiraAtualizacao(String modalidade,
			FacebookClient facebookClient, User facebookUser) {

		Connection<PostFitnessFB> fitConnection = facebookClient
				.fetchConnection("me/fitness." + defineModalidade(modalidade),
						PostFitnessFB.class, Parameter.with("limit", "99999"));
		
		verificaAplicativos(fitConnection);
		
		ArrayList<PostFitness> postsFit = new ArrayList<PostFitness>();

		for (PostFitnessFB postFit : fitConnection.getData()) {

			// id pessoa
			// modalidade

			// Adiciona aplicativo � Lista
			PostFitness postFitness = new PostFitness();

			postFitness.setId_publicacao(postFit.getId());
			postFitness.setId_pessoa(facebookUser.getId());

			postFitness.setId_app(postFit.getApplication().getId());
			
			try {

				switch (postFit.getApplication().getId()) {
				case ConstantesFitRank.ID_APP_NIKE:
					postFitness.setDistancia_percorrida(PostFitnessUtil
							.getNikeDistance(postFit.getDataCourse().getCourse()
									.getTitle()));
					postFitness.setDuracao(PostFitnessUtil.getNikeDuration(
							postFit.getStartTime(), postFit.getEndTime()));
					break;
				case ConstantesFitRank.ID_APP_RUNTASTIC:
					postFitness.setDistancia_percorrida(PostFitnessUtil
							.getRuntasticDistance(postFit.getDataCourse()
									.getCourse().getTitle()));
					postFitness.setDuracao(PostFitnessUtil
							.getRuntasticDuration(postFit.getDataCourse()
									.getCourse().getTitle()));
					break;
				case ConstantesFitRank.ID_APP_RUNKEEPER:
					postFitness.setDistancia_percorrida(PostFitnessUtil
							.getRunKeeperDistance(postFit.getDataCourse()
									.getCourse().getTitle()));
					postFitness.setDuracao(PostFitnessUtil
							.getRunKeeperDuration(postFit.getDataCourse()
									.getCourse().getTitle()));
					break;
				default:
					break;
				}
				
			} catch (NumberFormatException e) {
				continue;
			}

			postFitness.setData_publicacao(DateConversor.DateToString(postFit
					.getPublishTime()));

			postFitness.setUrl(postFit.getDataCourse().getCourse().getUrl());

			postFitness.setModalidade(modalidade);

			postsFit.add(postFitness);

		}

		postFitnessServico.adicionaListaPostFitnessServico(postsFit);

		Pessoa pessoa = pessoaServico.lePessoaServico(facebookUser);

		switch (modalidade) {
		case ConstantesFitRank.MODALIDADE_CAMINHADA:
			pessoa.setData_ultima_atualizacao_walks(new Date());
			break;
		case ConstantesFitRank.MODALIDADE_CORRIDA:
			pessoa.setData_ultima_atualizacao_runs(new Date());
			break;
		case ConstantesFitRank.MODALIDADE_BICICLETA:
			pessoa.setData_ultima_atualizacao_bikes(new Date());
			break;
		default:
			break;

		}

		Pessoa pessoaReturn = pessoaServico.atualizaPessoaServico(pessoa);

		return pessoaReturn;
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

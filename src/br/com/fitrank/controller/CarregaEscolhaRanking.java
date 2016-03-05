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

import br.com.fitrank.modelo.Amizade;
import br.com.fitrank.modelo.Aplicativo;
import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.modelo.PostFitness;
import br.com.fitrank.modelo.fb.PostFitness.PostFitnessFB;
import br.com.fitrank.service.AmizadeServico;
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
		Date horainicio = new Date();
		RequestDispatcher rd = null;

		aplicativos.clear();
		postsFit.clear();
		aplicativosNaoInserir.clear();

		FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));
		User facebookUser = facebookClient.fetchObject("me", User.class);

		String fav = (String) request.getParameter("fav");
		String modalidade = (String) request.getParameter("modalidade");
		Configuracao configuracao = null;

		if (fav == null || !fav.equals("S")) {

			Date ultimaAtualizacao = handleUltimaAtividade(modalidade, facebookClient, facebookUser);

			if (ultimaAtualizacao != null) {
				
				atualizaCorridasAmigos(facebookUser.getId(), modalidade, facebookClient, request);

				if (postsFit.size() == 0) {
					request.setAttribute("erro",
							"Você não tem nenhum registro nesta categoria.");
					request.setAttribute("token",
							(String) request.getParameter("token"));
					request.getRequestDispatcher("/escolheModalidade.jsp")
							.forward(request, response);

					return;
				} else {
					rd = request.getRequestDispatcher("/escolhaRanking.jsp");
				}

				configuracao = configuracaoServico
						.leConfiguracaoPadraoModalidade(facebookUser.getId(),
								modalidade);
			}

		} else if (fav.equals("S")) {

			configuracao = configuracaoServico
					.leConfiguracaoFavorita(facebookUser.getId());

			if (configuracao == null) {
				request.setAttribute("erro",
						"Você ainda não cadastrou configuração favorita.");
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
		
		Date horaFim = new Date();
		System.out.println("\n\nTempo de processamento CarregaEscolhaRanking: " + (horainicio.getTime() - horaFim.getTime())/1000 + " segundos.\n");

		rd.forward(request, response);
	}
	
	private void atualizaCorridasAmigos(String idUsuario, String modalidade, FacebookClient facebookClient, HttpServletRequest request){
		AmizadeServico amizadeServico = new AmizadeServico();
		List<Amizade> amigos = amizadeServico.listaAmizades(idUsuario);
		
		for(Amizade amizade : amigos){
		
			User facebookUser = facebookClient.fetchObject(amizade.getId_amigo(), User.class);
			handleUltimaAtividade(modalidade, facebookClient, facebookUser);
		}
	}

	private void verificaAplicativos(Connection<PostFitnessFB> fitConnection) {
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

		// Insere aplicativos que estão sendo utilizados pelo
		// usuário, no banco.
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

	private String calculaLimiteDeBusca(Date ultimaAtualizacao) {
		Integer limit;
		if(null != ultimaAtualizacao){
			limit = DateConversor.getDaysDifference(new Date(), ultimaAtualizacao) * ConstantesFitRank.LIMITE_CORRIDAS_REALIZADAS_POR_DIA;
			limit = limit == 0 ? 1 : limit;
		} else {
			limit = ConstantesFitRank.LIMITE_MAX_RECUPERA_FB;
		}
		return limit.toString();
	}

	private Date handleUltimaAtividade(String modalidade, FacebookClient facebookClient, User facebookUser) {
		Pessoa pessoa = new Pessoa();

		switch (modalidade) {
		case ConstantesFitRank.MODALIDADE_CAMINHADA:
			Date ultimoWalk = pessoaServico.lePessoaServico(facebookUser).getData_ultima_atualizacao_walks();
			pessoa = executaAtualizacao(ConstantesFitRank.MODALIDADE_CAMINHADA, facebookClient, facebookUser, ultimoWalk);
			ultimoWalk = pessoa.getData_ultima_atualizacao_walks();
			return ultimoWalk;
			
		case ConstantesFitRank.MODALIDADE_CORRIDA:
			Date ultimoRuns = pessoaServico.lePessoaServico(facebookUser).getData_ultima_atualizacao_runs();
			pessoa = executaAtualizacao(ConstantesFitRank.MODALIDADE_CORRIDA, facebookClient, facebookUser, ultimoRuns);
			ultimoRuns = pessoa.getData_ultima_atualizacao_runs();
			return ultimoRuns;
			
		case ConstantesFitRank.MODALIDADE_BICICLETA:
			Date ultimoBikes = pessoaServico.lePessoaServico(facebookUser).getData_ultima_atualizacao_bikes();
			pessoa = executaAtualizacao(ConstantesFitRank.MODALIDADE_BICICLETA, facebookClient, facebookUser, ultimoBikes);
			ultimoBikes = pessoa.getData_ultima_atualizacao_bikes();
			return ultimoBikes;
			
		default:
			return null;
		}
	}

	private Pessoa executaAtualizacao(String modalidade, FacebookClient facebookClient, User facebookUser, Date dataUltimaAtualizacao) {
		
		Connection<PostFitnessFB> listaFitConnection = facebookClient
				.fetchConnection(facebookUser.getId()+"/fitness." + defineModalidade(modalidade),
						PostFitnessFB.class, Parameter.with("limit", calculaLimiteDeBusca(dataUltimaAtualizacao)));
		
		postFitnessServico = new PostFitnessServico();
		ArrayList<PostFitness> postsUsuarioNaoInserir = (ArrayList<PostFitness>) postFitnessServico.lePostFitnessPorIdPessoa(facebookUser.getId());
		
		verificaAplicativos(listaFitConnection);
		
		ArrayList<PostFitness> postsFit = new ArrayList<PostFitness>();

		for (PostFitnessFB postFit : listaFitConnection.getData()) {
			// Primeira atividade desta modalidade OU publicacoes ainda nao armazenadas
			if(dataUltimaAtualizacao == null || postFit.getPublishTime().after(dataUltimaAtualizacao)){
				// Adiciona aplicativo à Lista
				PostFitness postFitness = new PostFitness();
				postFitness.setId_publicacao(postFit.getId());
				postFitness.setId_pessoa(facebookUser.getId());
				postFitness.setId_app(postFit.getApplication().getId());
				postFitness.setData_publicacao(DateConversor.DateToString(postFit.getPublishTime()));
				postFitness.setUrl(postFit.getDataCourse().getCourse().getUrl());
				postFitness.setModalidade(modalidade);
				
				try {
	
					switch (postFit.getApplication().getId()) {
					case ConstantesFitRank.ID_APP_NIKE:
						postFitness.setDistancia_percorrida(PostFitnessUtil.getNikeDistance(postFit.getDataCourse().getCourse().getTitle()));
						postFitness.setDuracao(PostFitnessUtil.getNikeDuration(postFit.getStartTime(), postFit.getEndTime()));
						break;
					case ConstantesFitRank.ID_APP_RUNTASTIC:
						postFitness.setDistancia_percorrida(PostFitnessUtil.getRuntasticDistance(postFit.getDataCourse().getCourse().getTitle()));
						postFitness.setDuracao(PostFitnessUtil.getRuntasticDuration(postFit.getDataCourse().getCourse().getTitle()));
						break;
					case ConstantesFitRank.ID_APP_RUNKEEPER:
						postFitness.setDistancia_percorrida(PostFitnessUtil.getRunKeeperDistance(postFit.getDataCourse().getCourse().getTitle()));
						postFitness.setDuracao(PostFitnessUtil.getRunKeeperDuration(postFit.getDataCourse().getCourse().getTitle()));
						break;
					default:
						break;
					}
					
				} catch (NumberFormatException e) {
					continue;
				}
	
				postsFit.add(postFitness);
			}

		}
		
		for (PostFitness postFitness : postsFit) {
			for (PostFitness postNaoInserir : postsUsuarioNaoInserir) {
				if(postFitness.getId_publicacao().equals(postNaoInserir.getId_publicacao()) && postFitness.getId_pessoa().equals(postNaoInserir.getId_pessoa())){
					postsFit.remove(postFitness);
				}
			}
		}
		Pessoa pessoa = pessoaServico.lePessoaServico(facebookUser);
		
		postFitnessServico.adicionaListaPostFitnessServico(postsFit);

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

		Pessoa pessoaReturn = pessoaServico.atualizaPessoaServico(pessoa, false);

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

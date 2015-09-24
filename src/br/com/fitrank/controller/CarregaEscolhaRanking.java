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
import br.com.fitrank.modelo.fb.PostFitnessFB;
import br.com.fitrank.service.AplicativoServico;
import br.com.fitrank.service.ConfiguracaoServico;
import br.com.fitrank.util.ConstantesFitRank;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;

public class CarregaEscolhaRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AplicativoServico aplicativoServico = new AplicativoServico();

	public CarregaEscolhaRanking() {

	}

	protected void inicia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String modalidade = null;

		switch (request.getParameter("modalidade")) {
		case ConstantesFitRank.MODALIDADE_CAMINHADA:
			modalidade = "walks";
			break;
		case ConstantesFitRank.MODALIDADE_CORRIDA:
			modalidade = "runs";
			break;

		case ConstantesFitRank.MODALIDADE_BICICLETA:
			modalidade = "bikes";
			break;

		default:
			break;
		}

		FacebookClient facebookClient = new DefaultFacebookClient(
				request.getParameter("token"));

		Connection<PostFitnessFB> fitConnection = facebookClient
				.fetchConnection("me/fitness." + modalidade,
						PostFitnessFB.class, Parameter.with("limit", "1"));

		ConfiguracaoServico configuracaoServico = new ConfiguracaoServico();

		List<PostFitnessFB> postsFit = new ArrayList<PostFitnessFB>();

		ArrayList<Aplicativo> aplicativos = new ArrayList<Aplicativo>();

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

			ArrayList<Aplicativo> aplicativosNaoInserir = new ArrayList<Aplicativo>();
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

		RequestDispatcher rd = null;

		if (postsFit.size() == 0) {
			request.setAttribute("erro",
					"Você não tem nenhum registro nesta categoria.");
			rd = request.getRequestDispatcher("/escolheModalidade.jsp");
		} else {
			rd = request.getRequestDispatcher("/escolhaRanking.jsp");
		}
		// TODO gvsribeiro Recuperar dados de Pessoa!
		// TODO Recuperar configurção de favorito aqui!
		// configuracaoServico.leConfiguracaoPadraoModalidade(idPessoa,
		// modalidade)

		request.setAttribute("token", request.getParameter("token"));

		rd.forward(request, response);
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

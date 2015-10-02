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
import br.com.fitrank.modelo.fb.PostFitness.PostFitnessFB;
import br.com.fitrank.service.AplicativoServico;
import br.com.fitrank.service.ConfiguracaoServico;
import br.com.fitrank.util.ConstantesFitRank;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

public class CarregaEscolhaRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AplicativoServico aplicativoServico = new AplicativoServico();
	
	ConfiguracaoServico configuracaoServico = new ConfiguracaoServico();
	
	public CarregaEscolhaRanking() {

	}

	protected void inicia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Aplicativo> aplicativos = new ArrayList<Aplicativo>();
		
		List<PostFitnessFB> postsFit = new ArrayList<PostFitnessFB>();
		
		ArrayList<Aplicativo> aplicativosNaoInserir = new ArrayList<Aplicativo>();
		
		String modalidade = defineModalidade((String) request.getParameter("modalidade"));
		
		FacebookClient facebookClient = new DefaultFacebookClient(
				request.getParameter("token"));
		
		User facebookUser = facebookClient.fetchObject("me", User.class);
		
		String fav = (String) request.getParameter("fav");
		
		Configuracao configuracao = null;
				
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

		RequestDispatcher rd = null;

		if (postsFit.size() == 0) {
			request.setAttribute("erro",
					"Você não tem nenhum registro nesta categoria.");
			request.setAttribute("token", (String) request.getParameter("token"));
			request.getRequestDispatcher("/escolheModalidade.jsp").forward(request, response);;
			return;
		} else {
			rd = request.getRequestDispatcher("/escolhaRanking.jsp");
		}
		// TODO gvsribeiro Recuperar dados de Pessoa!
		// TODO Recuperar configurção de favorito aqui!
		
		if (fav == null) {
			configuracao = configuracaoServico.leConfiguracaoPadraoModalidade( facebookUser.getId(), (String) request.getParameter("modalidade"));
		} else if (fav.equals("S")){
			configuracao = configuracaoServico.leConfiguracaoFavorita(facebookUser.getId());
		}
		
		if (configuracao != null) {
			request.setAttribute("modalidade", configuracao.getModalidade());
			request.setAttribute("modo", configuracao.getModo());
			request.setAttribute("turno", configuracao.getDiaNoite());
			request.setAttribute("favorito", configuracao.isFavorito() ? "S" : "N");
			request.setAttribute("default", configuracao.isPadraoModalidade() ? "S" : "N");
			request.setAttribute("periodo", configuracao.getIntervaloData());
			
		} else {
			request.setAttribute("modalidade", (String) request.getParameter("modalidade"));
		}
		
		
		request.setAttribute("token", (String) request.getParameter("token"));
		
		rd.forward(request, response);
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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}

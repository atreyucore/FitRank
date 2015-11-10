package br.com.fitrank.service;

import java.util.ArrayList;
import java.util.List;

import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.modelo.RankingPessoa;
import br.com.fitrank.persistencia.RankingPessoaDAO;
import br.com.fitrank.util.ConstantesFitRank;

public class RankingPessoaServico {
	
	private RankingPessoaDAO rankingPessoaDAO;
	
	public List<RankingPessoa> geraRankingDistancia(Configuracao configuracao){
		ArrayList<RankingPessoa> rankingDistancia = new ArrayList<RankingPessoa>();
		rankingPessoaDAO = new RankingPessoaDAO();
		
		try{
			rankingDistancia = (ArrayList<RankingPessoa>) rankingPessoaDAO.geraRankingDistancia(configuracao);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		List<RankingPessoa> listaRanking = retornaRankingTop(rankingDistancia, configuracao);
		
		return listaRanking;
	}
	
	public List<RankingPessoa> geraRankingVelocidadeMedia(Configuracao configuracao){
		ArrayList<RankingPessoa> rankingVelocidadeMedia = new ArrayList<RankingPessoa>();
		rankingPessoaDAO = new RankingPessoaDAO();
		try{
			rankingVelocidadeMedia = (ArrayList<RankingPessoa>) rankingPessoaDAO.geraRankingVelocidadeMedia(configuracao);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		List<RankingPessoa> listaRanking = retornaRankingTop(rankingVelocidadeMedia, configuracao);
		
		return listaRanking;
	}
	
	private RankingPessoa recuperaUsuarioNoRanking(List<RankingPessoa> listaRankingCompleta, Configuracao configuracao){
		for(RankingPessoa rankingPessoa : listaRankingCompleta){
			if(rankingPessoa.getId_pessoa().equalsIgnoreCase(configuracao.getIdPessoa())){
				return rankingPessoa;
			}
		}
		//Não vai acontecer, usuário necessita de dados para chegar até aqui e obrigatoriamente aparece no ranking
		return new RankingPessoa();
	}
	
	private List<RankingPessoa> retornaRankingTop(List<RankingPessoa> listaRankingCompleta, Configuracao configuracao){
		if(listaRankingCompleta.size() > ConstantesFitRank.TAMANHO_PADRAO_RANKING){
			RankingPessoa rankingPessoaUsuario = recuperaUsuarioNoRanking(listaRankingCompleta, configuracao);
			List<RankingPessoa> listaRankingTop = new ArrayList<RankingPessoa>();
			
			boolean usuarioTopoRanking = rankingPessoaUsuario.getColocacao() <= ConstantesFitRank.TAMANHO_PADRAO_RANKING;
					
			if(usuarioTopoRanking){
				for(int i=0; i<ConstantesFitRank.TAMANHO_PADRAO_RANKING; i++){
					listaRankingTop.add(listaRankingCompleta.get(i));
				}
			} else {
				for(int i=0; i<listaRankingCompleta.size(); i++){
					if(i<(ConstantesFitRank.TAMANHO_PADRAO_RANKING-1) || (listaRankingCompleta.get(i).getColocacao() == rankingPessoaUsuario.getColocacao())){
						listaRankingTop.add(listaRankingCompleta.get(i));
						if(i>=ConstantesFitRank.TAMANHO_PADRAO_RANKING){
							break;
						}
					}
					
				}
			}
			
			return listaRankingTop;
			
		} else {
			return listaRankingCompleta;
		}
	}

}

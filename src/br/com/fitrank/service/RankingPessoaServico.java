package br.com.fitrank.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.modelo.RankingPessoa;
import br.com.fitrank.persistencia.RankingPessoaDAO;
import br.com.fitrank.util.ConstantesFitRank;
import br.com.fitrank.util.Logger;

public class RankingPessoaServico {
	
	private RankingPessoaDAO rankingPessoaDAO;
	
	public List<RankingPessoa> geraRanking(Configuracao configuracao){
		ArrayList<RankingPessoa> rankingDistancia = new ArrayList<RankingPessoa>();
		rankingPessoaDAO = new RankingPessoaDAO();
		
		try{
			rankingDistancia = (ArrayList<RankingPessoa>) rankingPessoaDAO.geraRanking(configuracao);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		List<RankingPessoa> listaRanking = retornaRankingTop(rankingDistancia, configuracao);
		
		return listaRanking;
	}
	
	private RankingPessoa recuperaUsuarioNoRanking(List<RankingPessoa> listaRankingCompleta, Configuracao configuracao){
		for(RankingPessoa rankingPessoa : listaRankingCompleta){
			if(rankingPessoa.getId_pessoa().equalsIgnoreCase(configuracao.getIdPessoa())){
				return rankingPessoa;
			}
		}
		//Caso usuario nao tenha publicacoes dentro da configuracao utilizada, ele e colocado no ranking ocupando a ultima posicao 
		RankingPessoa usuarioSemCorrida = new RankingPessoa();
		usuarioSemCorrida.setId_pessoa(configuracao.getIdPessoa());
		usuarioSemCorrida.setId_ranking(configuracao.getIdConfiguracao());
		usuarioSemCorrida.setColocacao(listaRankingCompleta.size()+1);
		listaRankingCompleta.add(usuarioSemCorrida);
		return usuarioSemCorrida;
	}
	
	private List<RankingPessoa> retornaRankingTop(List<RankingPessoa> listaRankingCompleta, Configuracao configuracao){
		RankingPessoa rankingPessoaUsuario = recuperaUsuarioNoRanking(listaRankingCompleta, configuracao);
		if(listaRankingCompleta.size() > ConstantesFitRank.TAMANHO_PADRAO_RANKING){
			
			List<RankingPessoa> listaRankingTop = new ArrayList<RankingPessoa>();
			boolean usuarioTopoRanking = rankingPessoaUsuario.getColocacao() < ConstantesFitRank.TAMANHO_PADRAO_RANKING;
					
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
	
	public void gravaRankingPessoa(List<RankingPessoa> listaRankingPessoa, int idRanking){
		for (RankingPessoa rankingPessoa : listaRankingPessoa) {
			try {
				rankingPessoaDAO = new RankingPessoaDAO();
				rankingPessoa.setId_ranking(idRanking);
				rankingPessoaDAO.adicionaRankingPessoa(rankingPessoa);
			} catch (SQLException e) {
				Logger.insertLog("Erro ao salvar rankingPessoa.");
				Logger.insertLog("id_ranking = " + idRanking);
				Logger.insertLog("id_pessoa = " + rankingPessoa.getId_pessoa());
				e.printStackTrace();
			}
		}
	}
	
	public List<RankingPessoa> listaRankingPessoaPorIdRanking(int idRanking){
		try {
			rankingPessoaDAO = new RankingPessoaDAO();
			return rankingPessoaDAO.listaRankingPessoaPorIdRanking(idRanking);
			
		} catch (SQLException e) {
			Logger.insertLog("Erro ao consukltar rankingPessoa.");
			Logger.insertLog("id_ranking = " + idRanking);
			e.printStackTrace();
			return new ArrayList<RankingPessoa>();
		}
	}

}

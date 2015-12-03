package br.com.fitrank.service;

import java.sql.SQLException;
import java.util.Date;

import br.com.fitrank.modelo.Ranking;
import br.com.fitrank.persistencia.RankingDAO;
import br.com.fitrank.util.DateConversor;

public class RankingServico {
	
	private RankingDAO rankingDAO;
	
	public Ranking adicionaRanking(Ranking ranking) {
		rankingDAO = new RankingDAO();
		ranking.setData_ranking(DateConversor.DateToString(new Date()));
		
		try {
			ranking = rankingDAO.adicionaRanking(ranking);
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Ranking.");
			e.printStackTrace();
		}
		
		return ranking;
	}
	
	public Ranking leRanking(int idRanking) {
		rankingDAO = new RankingDAO();
		Ranking ranking;
		
		try {
			ranking = rankingDAO.leRanking(idRanking);
		} catch (SQLException e) {
			System.out.println("Erro ao ler Ranking.");
			System.out.println("id_ranking = " + idRanking);
			e.printStackTrace();
			ranking = new Ranking();
		}
		
		return ranking;
	}

}

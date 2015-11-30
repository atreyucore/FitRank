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

}

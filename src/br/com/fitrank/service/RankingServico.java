package br.com.fitrank.service;

import java.sql.SQLException;
import java.util.Date;

import br.com.fitrank.modelo.Ranking;
import br.com.fitrank.persistencia.RankingDAO;
import br.com.fitrank.util.DateConversor;

public class RankingServico {
	
	private RankingDAO rankingDAO;
	
	public Ranking adicionaRanking(Ranking ranking) throws SQLException {
		rankingDAO = new RankingDAO();
		ranking.setData_ranking(DateConversor.DateToString(new Date()));
		
		return rankingDAO.adicionaRanking(ranking);
	}

}

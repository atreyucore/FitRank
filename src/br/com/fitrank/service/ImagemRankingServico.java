package br.com.fitrank.service;

import java.sql.SQLException;

import br.com.fitrank.modelo.ImagemRanking;
import br.com.fitrank.persistencia.ImagemRankingDAO;
import br.com.fitrank.util.Logger;

public class ImagemRankingServico {
	
	private ImagemRankingDAO imagemRankingDAO;
	
	public ImagemRanking adicionaRanking(ImagemRanking imagemRanking) {
		imagemRankingDAO = new ImagemRankingDAO();
		
		try {
			imagemRanking = imagemRankingDAO.adicionaImagemRanking(imagemRanking);
		} catch (SQLException e) {
			Logger.insertLog("Erro ao salvar Ranking.");
			e.printStackTrace();
		}
		
		return imagemRanking;
	}
	
	public ImagemRanking leRanking(int idRanking) {
		imagemRankingDAO = new ImagemRankingDAO();
		ImagemRanking imagemRanking;
		
		try {
			imagemRanking = imagemRankingDAO.leImagemRanking(idRanking);
		} catch (SQLException e) {
			Logger.insertLog("Erro ao ler Ranking.");
			Logger.insertLog("id_ranking = " + idRanking);
			e.printStackTrace();
			imagemRanking = new ImagemRanking();
		}
		
		return imagemRanking;
	}

}
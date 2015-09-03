package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fitrank.modelo.Ranking;
import br.com.fitrank.util.JDBCFactory;


public class RankingDAO {
	
	private Connection conexao;

	public RankingDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}
	
	public Ranking adicionaConfiguracao(Ranking ranking) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO ranking (id_ranking, titulo, "
				+ "id_configuracao, data_ranking) "
				+ "VALUES (?, ?, ?, ?)";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, ranking.getId_ranking());
			preparedStatement.setString(2, ranking.getTitulo());
			preparedStatement.setInt(3, ranking.getId_configuracao());
			preparedStatement.setString(4, ranking.getData_ranking()); 
			

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return ranking;
	}
}

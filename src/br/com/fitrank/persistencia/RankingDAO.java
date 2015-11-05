package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fitrank.modelo.Ranking;
import br.com.fitrank.util.JDBCFactory;


public class RankingDAO {
	
	private Connection conexao;

	public RankingDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}
	
	public Ranking adicionaRanking(Ranking ranking) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO ranking ("
				+ "titulo, "
				+ "id_configuracao, "
				+ "data_ranking"
				+ ") VALUES (?, ?, ?)";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			int i = 0;
			
			preparedStatement.setString(++i, ranking.getTitulo());
			preparedStatement.setInt(++i, ranking.getId_configuracao());
			preparedStatement.setString(++i, ranking.getData_ranking()); 

			// execute insert SQL stetement
			//TODO gabriel: Verificar possibilidade de recuperar id do ranking após inclusao (possivelmente com o metodo getGeneratedKeys())
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

	public Ranking atualizaRanking(Ranking ranking) throws SQLException {
	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
	
		String insertTableSQL = "UPDATE ranking set "
				+ "titulo = ?, "
				+ "id_configuracao= ?, "
				+ "data_ranking= ?"
				+ "where id_ranking = ?";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
	
			int i = 0;
			
			preparedStatement.setString(++i, ranking.getTitulo());
			preparedStatement.setInt(++i, ranking.getId_configuracao());
			preparedStatement.setString(++i, ranking.getData_ranking());
			preparedStatement.setInt(++i, ranking.getId_ranking());
			
	
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
	
	public Ranking leRanking(Ranking ranking) throws SQLException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
	
		String selectTableSQL = "SELECT  "
				+ "id_ranking, "
				+ "titulo, "
				+ "id_configuracao, "
				+ "data_ranking,"
				+ "FROM ranking"
				+ "where id_ranking = ?";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			preparedStatement.setInt(1, ranking.getId_ranking());
			
			if ( rs.next() ) {
				
				ranking.setId_ranking(rs.getInt("id_ranking"));
				ranking.setTitulo(rs.getString("titulo"));
				ranking.setId_configuracao(rs.getInt("id_configuracao"));
				ranking.setData_ranking(rs.getString("data_ranking"));
				
			}
	
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

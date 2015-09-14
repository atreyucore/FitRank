package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fitrank.modelo.RankingPessoa;
import br.com.fitrank.util.JDBCFactory;

public class RankingPessoaDAO {
	private Connection conexao;

	public RankingPessoaDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}

	public RankingPessoa adicionaRankingPessoa(RankingPessoa rankingPessoa)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO ranking_pessoa("
				+ "id_ranking, "
				+ "id_pessoa, "
				+ "colocacao" 
				+ ") VALUES (?, ?, ?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setInt(++i, rankingPessoa.getId_ranking());
			preparedStatement.setString(++i, rankingPessoa.getId_pessoa());
			preparedStatement.setInt(++i, rankingPessoa.getColocacao());

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
		return rankingPessoa;
	}

	public RankingPessoa atualizaRankingPessoa(RankingPessoa rankingPessoa)
			throws SQLException {
	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
	
		String insertTableSQL = "UPDATE ranking_pessoa set "
				+ "id_pessoa = ?,"
				+ "colocacao = ? " 
				+ "where id_ranking = ?";
	
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setInt(++i, rankingPessoa.getColocacao());
			preparedStatement.setInt(++i, rankingPessoa.getId_ranking());
			preparedStatement.setString(++i, rankingPessoa.getId_pessoa());
	
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
		return rankingPessoa;
	}
	
	public RankingPessoa leRankingPessoa(RankingPessoa rankingPessoa)
			throws SQLException {
	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
	
		String selectTableSQL = "SELECT "
				+ "id_ranking, "
				+ "id_pessoa,"
				+ "colocacao "
				+ "FROM ranking_pessoa "
				+ "where id_ranking = ?";
	
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			int i = 0;
			
			preparedStatement.setInt(++i, rankingPessoa.getId_ranking());

			if ( rs.next() ) {
				rankingPessoa.setId_ranking(rs.getInt("id_ranking"));
				rankingPessoa.setId_pessoa(rs.getString("id_pessoa"));
				rankingPessoa.setColocacao(rs.getInt("colocacao"));
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
		return rankingPessoa;
	}
}

package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fitrank.modelo.PostFitness;
import br.com.fitrank.util.JDBCFactory;


public class PostFitnessDAO {
	
	private Connection conexao;

	public PostFitnessDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}
	
	public PostFitness adicionaPostFitness(PostFitness postFitness) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO post_fitness (id_publicacao, id_pessoa, "
				+ "data_inicio_corrida, data_fim_corrida, modalidade, id_app, "
				+ "distancia_percorrida, duracao, data_publicacao, url) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			preparedStatement.setString(1, postFitness.getId_publicacao());
			preparedStatement.setString(2, postFitness.getId_pessoa());
			preparedStatement.setString(3, postFitness.getData_inicio_corrida());
			preparedStatement.setString(4, postFitness.getData_fim_corrida());
			preparedStatement.setInt(5, postFitness.getId_app());
			preparedStatement.setFloat(6, postFitness.getDistancia_percorrida());
			preparedStatement.setFloat(7, postFitness.getDuracao());
			preparedStatement.setString(8, postFitness.getData_publicacao());
			preparedStatement.setString(9, postFitness.getUrl());
			preparedStatement.setString(10, postFitness.getModalidade());

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
		return postFitness;
	}
}

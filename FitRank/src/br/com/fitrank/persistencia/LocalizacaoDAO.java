package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fitrank.modelo.Localizacao;
import br.com.fitrank.util.JDBCFactory;

public class LocalizacaoDAO {

	private Connection conexao;

	public LocalizacaoDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}
	
	public Localizacao adicionaLocalizacao(Localizacao localizacao) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO localizacao (latitude, longitude, altitude, id_course) "
				+ "VALUES (?, ?, ?, ?)";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setDouble(1, localizacao.getLatitude());
			preparedStatement.setDouble(2, localizacao.getLongitude());
			preparedStatement.setDouble(3, localizacao.getAltitude());
			preparedStatement.setString(4, localizacao.getId_course()); 

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
		return localizacao;
	}
}

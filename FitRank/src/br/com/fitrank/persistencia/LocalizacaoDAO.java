package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fitrank.modelo.Localizacao;
import br.com.fitrank.util.JDBCFactory;

//TODO Verificar classe no banco, classe de modelo e UML
public class LocalizacaoDAO {

	private Connection conexao;

	public LocalizacaoDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}
	
	public Localizacao adicionaConfiguracao(Localizacao localizacao) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "";//"INSERT INTO localizacao (latitude, longitude, "
//				+ "altitude, fk_course) "
//				+ "VALUES (?, ?, ?, ?)";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

//			preparedStatement.setString(1, localizacao.getLatitude());
//			preparedStatement.setFloat(2, localizacao.getLongitude());
//			preparedStatement.setFloat(3, localizacao.getAltitude());
//			preparedStatement.setFloat(4, localizacao.getFk_course()); 

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

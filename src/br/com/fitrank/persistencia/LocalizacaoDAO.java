package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fitrank.modelo.Localizacao;
import br.com.fitrank.util.JDBCFactory;
import br.com.fitrank.util.Logger;

public class LocalizacaoDAO {

	private Connection conexao;

	public LocalizacaoDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}
	
	public Localizacao adicionaLocalizacao(Localizacao localizacao) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		//Auto increment (id_localizacao)
		String insertTableSQL = "INSERT INTO localizacao ("
				+ "latitude, "
				+ "longitude, "
				+ "altitude, "
				+ "id_course"
				+ ") VALUES (?, ?, ?, ?)";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setDouble(++i, localizacao.getLatitude());
			preparedStatement.setDouble(++i, localizacao.getLongitude());
			preparedStatement.setDouble(++i, localizacao.getAltitude());
			preparedStatement.setString(++i, localizacao.getId_course()); 

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			Logger.insertLog(e.getMessage());

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
	
	public Localizacao atualizaLocalizacao(Localizacao localizacao) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "update localizacao set "
				+ "latitude = ?, "
				+ "longitude  = ?, "
				+ "altitude = ?, "
				+ "id_course = ? "
				+ "where id_localizacao = ?";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setDouble(++i, localizacao.getLatitude());
			preparedStatement.setDouble(++i, localizacao.getLongitude());
			preparedStatement.setDouble(++i, localizacao.getAltitude());
			preparedStatement.setString(++i, localizacao.getId_course());
			preparedStatement.setInt(++i, localizacao.getId_localizacao());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			Logger.insertLog(e.getMessage());

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
	
	public Localizacao leLocalizacao(Localizacao localizacao) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "select "
				+ "id_localizacao,"
				+ "latitude, "
				+ "longitude, "
				+ "altitude, "
				+ "id_course "
				+ "FROM localizacao "
				+ "where id_localizacao = ?";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			preparedStatement.setInt(1, localizacao.getId_localizacao());
			
			while (rs.next()) {
				localizacao.setId_localizacao(rs.getInt("id_localizacao"));
				localizacao.setLatitude(rs.getDouble("latitude"));
				localizacao.setLongitude(rs.getDouble("longitude"));
				localizacao.setAltitude(rs.getDouble("altitude"));
				localizacao.setId_course(rs.getString("id_course"));
			}
			
			// execute insert SQL stetement
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			Logger.insertLog(e.getMessage());

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

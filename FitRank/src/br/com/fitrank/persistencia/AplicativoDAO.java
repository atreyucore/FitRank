package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fitrank.modelo.Aplicativo;
import br.com.fitrank.util.JDBCFactory;


public class AplicativoDAO {
	
	private Connection conexao;

	public AplicativoDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}

	public Aplicativo adicionaAplicativo(Aplicativo aplicativo) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO aplicativo"
				+ "(id_aplicativo, nome) VALUES"
				+ "(?,?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, aplicativo.getIdAplicativo());
			preparedStatement.setString(2, aplicativo.getNome());

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
		return aplicativo;
	}
	
	public boolean removeAplicativoFromId(Aplicativo aplicativo) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String deleteSQL = "DELETE from aplicativo WHERE id_aplicativo = ?";
 
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, aplicativo.getIdAplicativo());
 
			// execute delete SQL stetement
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
			return false;
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
	}

	public Aplicativo leAplicativo(Aplicativo aplicativo) throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT id_aplicativo, nome from aplicativo limit 1";
		
		try {
			
			dbConnection = conexao;
			statement = dbConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(selectTableSQL);
			
			rs.next();
			
			aplicativo.setIdAplicativo(rs.getInt("id_aplicativo"));
			aplicativo.setNome(rs.getString("nome"));
			
		} catch (SQLException e) {
			 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (statement != null) {
				statement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
			
		}
		
		return aplicativo;
	}
	
	public Aplicativo atualizaAplicativo(Aplicativo aplicativo) throws SQLException {

		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL  = "update aplicativo set nome = ? where id_aplicativo = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, aplicativo.getNome());
			preparedStatement.setInt(2, aplicativo.getIdAplicativo());
			

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
		
		return aplicativo;
	}
}

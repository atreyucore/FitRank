package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fitrank.modelo.Amizade;
import br.com.fitrank.util.JDBCFactory;

public class AmizadeDAO {
	
	private Connection conexao;

	public AmizadeDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}

	public Amizade adicionaAmizade(Amizade amizade) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO amizade (id_pessoa, id_amigo, data_amizade) VALUES (?, ?, ?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, amizade.getId_pessoa());
			preparedStatement.setString(2, amizade.getId_amigo());
			preparedStatement.setString(2, amizade.getData_amizade());

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
		return amizade;
	}
	
	public Amizade atualizaAmizade(Amizade amizade) throws SQLException {

		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL  = "update amizade set id_amigo = ?, data_amizade = ? where id_pessoa = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, amizade.getId_amigo());
			preparedStatement.setString(2, amizade.getData_amizade());
			preparedStatement.setString(3, amizade.getId_pessoa());

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
		
		return amizade;
	}
//	
//	public boolean removeAmizadeFromId(Amizade amizade) throws SQLException {
//
//		Connection dbConnection = null;
//		PreparedStatement preparedStatement = null;
// 
//		String deleteSQL = "DELETE from amizade WHERE id_pessoa = ? and id_amigo = ?";
// 
//		try {
//			dbConnection = conexao;
//			preparedStatement = dbConnection.prepareStatement(deleteSQL);
//			
//			preparedStatement.setString(1, amizade.getIdPessoa());
//			preparedStatement.setString(2, amizade.getIdAmigo());
// 
//			// execute delete SQL stetement
//			preparedStatement.executeUpdate();
//			return true;
//		} catch (SQLException e) {
// 
//			System.out.println(e.getMessage());
//			return false;
//		} finally {
// 
//			if (preparedStatement != null) {
//				preparedStatement.close();
//			}
// 
//			if (dbConnection != null) {
//				dbConnection.close();
//			}
// 
//		}
//	}
//
//	public Amizade leAmizade(Amizade amizade) throws SQLException {
//		
//		Connection dbConnection = null;
//		Statement statement = null;
//
//		String selectTableSQL = "SELECT id_pessoa, id_amigo from amizade limit 1";
//		
//		try {
//			
//			dbConnection = conexao;
//			statement = dbConnection.createStatement();
//			
//			ResultSet rs = statement.executeQuery(selectTableSQL);
//			
//			rs.next();
//			
//			amizade.setIdPessoa(rs.getString("id_pessoa"));
//			amizade.setIdAmigo(rs.getString("id_amigo"));
//			
//		} catch (SQLException e) {
//			 
//			System.out.println(e.getMessage());
// 
//		} finally {
// 
//			if (statement != null) {
//				statement.close();
//			}
// 
//			if (dbConnection != null) {
//				dbConnection.close();
//			}
//			
//		}
//		
//		return amizade;
//	}
}


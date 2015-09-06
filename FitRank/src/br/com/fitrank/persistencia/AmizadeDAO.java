package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

		String insertTableSQL = "INSERT INTO amizade ("
				+ "id_pessoa, "
				+ "id_amigo, "
				+ "data_amizade"
				+ ") VALUES (?, ?, ?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setString(++i, amizade.getId_pessoa());
			preparedStatement.setString(++i, amizade.getId_amigo());
			preparedStatement.setString(++i, amizade.getData_amizade());

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

		String updateTableSQL  = "update amizade set "
				+ "id_amigo = ?, "
				+ "data_amizade = ? "
				+ "where id_pessoa = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			
			int i = 0;
			
			preparedStatement.setString(++i, amizade.getId_amigo());
			preparedStatement.setString(++i, amizade.getData_amizade());
			preparedStatement.setString(++i, amizade.getId_pessoa());

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

	public Amizade leAmizade(Amizade amizade) throws SQLException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT "
				+ "id_pessoa, "
				+ "id_amigo, "
				+ "data_amizade "
				+ "from amizade "
				+ "where id_pessoa = ?";
		
		try {
			
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			preparedStatement.setString(1, amizade.getId_pessoa());

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				amizade.setId_pessoa(rs.getString("id_pessoa"));
				amizade.setId_amigo(rs.getString("id_amigo"));
				amizade.setData_amizade(rs.getString("data_amizade"));

			}
			
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
//			preparedStatement.setString(++i, amizade.getIdPessoa());
//			preparedStatement.setString(++i, amizade.getIdAmigo());
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

}


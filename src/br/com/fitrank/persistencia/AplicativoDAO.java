package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fitrank.modelo.Aplicativo;
import br.com.fitrank.util.JDBCFactory;

public class AplicativoDAO {

	private Connection conexao;

	public AplicativoDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}

	public Aplicativo adicionaAplicativo(Aplicativo aplicativo)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO aplicativo (" 
				+ "id_aplicativo, "
				+ "nome" 
				+ ") VALUES (?,?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			int i = 0;

			preparedStatement.setString(++i, aplicativo.getId_aplicativo());
			preparedStatement.setString(++i, aplicativo.getNome());

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
	
	public ArrayList<Aplicativo> adicionaAplicativos(ArrayList<Aplicativo> aplicativos)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO aplicativo (" 
				+ "id_aplicativo, "
				+ "nome" 
				+ ") VALUES (?,?)";
		
		for (int i = 0; i < (aplicativos.size() - 1); i++) {
			insertTableSQL += ", (?,?)";
		}

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			int i = 0;
			
			for (Aplicativo aplicativo : aplicativos) {
				preparedStatement.setString(++i, aplicativo.getId_aplicativo());
				preparedStatement.setString(++i, aplicativo.getNome());
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
		return aplicativos;
	}
	
	public Aplicativo atualizaAplicativo(Aplicativo aplicativo)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "update aplicativo set " 
				+ "nome = ? "
				+ "where id_aplicativo = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			int i = 0;

			preparedStatement.setString(++i, aplicativo.getNome());
			preparedStatement.setString(++i, aplicativo.getId_aplicativo());

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

	public Aplicativo leAplicativo(String id_aplicativo) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		Aplicativo aplicativo = null;

		String selectTableSQL = "SELECT "
				+ "id_aplicativo, "
				+ "nome "
				+ "FROM aplicativo "
				+ "where id_aplicativo = ?";

		try {

			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			int i = 0;
			
			preparedStatement.setString(++i, id_aplicativo);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				aplicativo = new Aplicativo();
				aplicativo.setId_aplicativo(rs.getString("id_aplicativo"));
				aplicativo.setNome(rs.getString("nome"));

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

		return aplicativo;
	}
	
	public ArrayList<Aplicativo> leListaAplicativos(ArrayList<Aplicativo> aplicativos)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Aplicativo> aplicativosReturn = null; 

		String selectTableSQL = "SELECT "
				+ "id_aplicativo, "
				+ "nome "
				+ "FROM aplicativo "
				+ "where id_aplicativo IN ( ?";
		
		for (int i = 0; i < (aplicativos.size() - 1); i++) {
			selectTableSQL += ", ?";
		}		
		
		selectTableSQL += ");";
		
		try {

			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			int i = 0;

			for (Aplicativo aplicativo : aplicativos) {
				preparedStatement.setString(++i, aplicativo.getId_aplicativo());
			}

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			aplicativosReturn = new ArrayList<Aplicativo>();
			
			while (rs.next()) {
				Aplicativo aplicativo = new Aplicativo();
				
				aplicativo.setId_aplicativo(rs.getString("id_aplicativo"));
				aplicativo.setNome(rs.getString("nome"));
				
				aplicativosReturn.add(aplicativo);

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

		return aplicativosReturn;
	}
	// public boolean removeAplicativoFromId(Aplicativo aplicativo)
	// throws SQLException {
	//
	// Connection dbConnection = null;
	// PreparedStatement preparedStatement = null;
	//
	// String deleteSQL = "DELETE from aplicativo WHERE id_aplicativo = ?";
	//
	// try {
	// dbConnection = conexao;
	// preparedStatement = dbConnection.prepareStatement(deleteSQL);
	// preparedStatement.setInt(++i, aplicativo.getIdAplicativo());
	//
	// // execute delete SQL stetement
	// preparedStatement.executeUpdate();
	// return true;
	// } catch (SQLException e) {
	//
	// System.out.println(e.getMessage());
	// return false;
	// } finally {
	//
	// if (preparedStatement != null) {
	// preparedStatement.close();
	// }
	//
	// if (dbConnection != null) {
	// dbConnection.close();
	// }
	//
	// }
	// }

}

package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

			preparedStatement.setInt(++i, aplicativo.getIdAplicativo());
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
			preparedStatement.setInt(++i, aplicativo.getIdAplicativo());

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

	public Aplicativo leAplicativo(Aplicativo aplicativo) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT "
				+ "id_aplicativo, "
				+ "nome "
				+ "FROM aplicativo "
				+ "where id_aplicativo = ?";

		try {

			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setInt(1, aplicativo.getIdAplicativo());

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				aplicativo.setIdAplicativo(rs.getInt("id_aplicativo"));
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

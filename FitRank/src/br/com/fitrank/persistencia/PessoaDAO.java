package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.util.JDBCFactory;



public class PessoaDAO {

	private Connection conexao;

	public PessoaDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}

	public Pessoa persistePessoa(Pessoa pessoa) throws SQLException {

		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO pessoa"
				+ "(id_usuario, nome) VALUES"
				+ "(?,?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, pessoa.getIdUsuario());
			preparedStatement.setString(2, pessoa.getNome());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into DBUSER table!");

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
		return pessoa;
	}

}

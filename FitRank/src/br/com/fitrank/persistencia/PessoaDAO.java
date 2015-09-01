package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.util.JDBCFactory;



public class PessoaDAO {

	private Connection conexao;

	public PessoaDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}

	public Pessoa adicionaPessoa(Pessoa pessoa) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO pessoa"
				+ "(id_usuario, data_cadastro, nome) VALUES"
				+ "(?,?,?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, pessoa.getId_usuario());
			preparedStatement.setString(2, pessoa.getData_cadastro());
			preparedStatement.setString(3, pessoa.getNome());

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
		return pessoa;
	}
	
	public boolean removePessoaFromId(Pessoa pessoa) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String deleteSQL = "DELETE from pessoa WHERE id_usuario = ?";
 
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, pessoa.getId_usuario());
 
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

	public Pessoa lePessoa(Pessoa pessoa) throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT id_usuario, nome from pessoa limit 1";
		
		try {
			
			dbConnection = conexao;
			statement = dbConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(selectTableSQL);
			
			if ( rs.next() == true ) {
				pessoa.setId_usuario(rs.getString("id_usuario"));
				pessoa.setNome(rs.getString("nome"));
			} else {
				pessoa = null;
			}
				
			
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
		
		return pessoa;
	}
	
	public Pessoa atualizaPessoa(Pessoa pessoa) throws SQLException {

		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL  = "update pessoa set nome = ? where id_usuario = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getId_usuario());
			

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
		
		return pessoa;
	}
}

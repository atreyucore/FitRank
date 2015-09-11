package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

		String insertTableSQL = "INSERT INTO pessoa ("
				+ "id_usuario, "
				+ "data_cadastro, "
				+ "nome"
				+ ") VALUES (?,?,?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setString(++i, pessoa.getId_usuario());
			preparedStatement.setString(++i, pessoa.getData_cadastro());
			preparedStatement.setString(++i, pessoa.getNome());

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
	
	public Pessoa atualizaPessoa(Pessoa pessoa) throws SQLException {
	
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
	
		String updateTableSQL  = "update pessoa set "
				+ "nome = ? "
				+ "where id_usuario = ?";
	
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			
			int i = 0;
			
			preparedStatement.setString(++i, pessoa.getNome());
			preparedStatement.setString(++i, pessoa.getId_usuario());
			
	
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
	

	public Pessoa lePessoa(String idPessoa) throws SQLException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		Pessoa pessoa = null;

		String selectTableSQL = "SELECT "
				+ "id_usuario, "
				+ "data_cadastro, "
				+ "nome "
				+ "from pessoa "
				+ "where id_usuario = ?";
		
		try {
			
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			preparedStatement.setString(1, idPessoa);
			ResultSet rs = preparedStatement.executeQuery();
			
			if ( rs.next() ) {
				pessoa = new Pessoa();
				pessoa.setId_usuario(rs.getString("id_usuario"));
				pessoa.setData_cadastro(rs.getString("data_cadastro"));
				pessoa.setNome(rs.getString("nome"));	
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
		
		return pessoa;
	}
//	public boolean removePessoaFromId(Pessoa pessoa) throws SQLException {
//
//		Connection dbConnection = null;
//		PreparedStatement preparedStatement = null;
// 
//		String deleteSQL = "DELETE from pessoa WHERE id_usuario = ?";
// 
//		try {
//			dbConnection = conexao;
//			preparedStatement = dbConnection.prepareStatement(deleteSQL);
//			
//			int i = 0;
//			
//			preparedStatement.setString(++i, pessoa.getId_usuario());
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

package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.util.DateConversor;
import br.com.fitrank.util.JDBCFactory;
import br.com.fitrank.util.Logger;



public class PessoaDAO {

	private Connection conexao;

	public PessoaDAO() {
		
	}

	public Pessoa adicionaPessoa(Pessoa pessoa) throws SQLException {

		conexao = new JDBCFactory().getConnection();
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO pessoa ("
				+ "id_usuario, "
				+ "data_cadastro, "
				+ "nome,"
				+ "data_ultimo_login, "
				+ "data_ultima_atualizacao_runs, "
				+ "data_ultima_atualizacao_walks, "
				+ "data_ultima_atualizacao_bikes, "
				+ "rank_anual, "
				+ "genero, "
				+ "data_nascimento, "
				+ "url_foto "
				+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		try {
			preparedStatement = conexao.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			
			preparedStatement.setString(++i, pessoa.getId_usuario());
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_cadastro()));
			preparedStatement.setString(++i, pessoa.getNome());
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_ultimo_login()));
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_ultima_atualizacao_runs()));
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_ultima_atualizacao_walks()));
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_ultima_atualizacao_bikes()));
			preparedStatement.setString(++i, pessoa.getRank_anual());
			preparedStatement.setString(++i, pessoa.getGenero());
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_nascimento()));
			preparedStatement.setString(++i, pessoa.getUrl_foto());

			// execute insert SQL statement
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			Logger.insertLog(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (conexao != null) {
				conexao.close();
			}

		}
		return pessoa;
	}
	
	public Pessoa atualizaPessoa(Pessoa pessoa, boolean proprioUsuario) throws SQLException {
	
		
		conexao = new JDBCFactory().getConnection();
		PreparedStatement preparedStatement = null;
	
		String updateTableSQL  	= "UPDATE pessoa set "
								+ "nome = ?, ";
			if(proprioUsuario){
				updateTableSQL += "data_ultimo_login = ?, ";
			}
		updateTableSQL 		   += "data_ultima_atualizacao_runs = ?, "
								+ "data_ultima_atualizacao_walks = ?, "
								+ "data_ultima_atualizacao_bikes = ?, "
								+ "rank_anual = ?, "
								+ "genero = ?, "
								+ "data_nascimento = ?, "
								+ "url_foto = ? "
								+ "WHERE id_usuario = ? ";
	
		try {
			preparedStatement = conexao.prepareStatement(updateTableSQL);
			
			int i = 0;
			
			preparedStatement.setString(++i, pessoa.getNome());
			if(proprioUsuario){
				preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_ultimo_login()));
			}
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_ultima_atualizacao_runs()));
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_ultima_atualizacao_walks()));
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_ultima_atualizacao_bikes()));
			preparedStatement.setString(++i, pessoa.getRank_anual());
			preparedStatement.setString(++i, pessoa.getGenero());
			preparedStatement.setString(++i, DateConversor.DateToString(pessoa.getData_nascimento()));
			preparedStatement.setString(++i, pessoa.getUrl_foto());
			
			preparedStatement.setString(++i, pessoa.getId_usuario());
	
			// execute insert SQL statement
			preparedStatement.executeUpdate();
	
		} catch (SQLException e) {
	
			Logger.insertLog(e.getMessage());
	
		} finally {
	
			if (preparedStatement != null) {
				preparedStatement.close();
			}
	
			if (conexao != null) {
				conexao.close();
			}
	
		}
		
		return pessoa;
	}

	public Pessoa lePessoa(String idPessoa) throws SQLException {
		
		conexao = new JDBCFactory().getConnection();
		PreparedStatement preparedStatement = null;
		Pessoa pessoa = null;

		String selectTableSQL = "SELECT "
				+ "id_usuario, "
				+ "data_cadastro, "
				+ "nome,"
				+ "data_ultimo_login, "
				+ "data_ultima_atualizacao_runs, "
				+ "data_ultima_atualizacao_walks, "
				+ "data_ultima_atualizacao_bikes, "
				+ "rank_anual, "
				+ "genero, "
				+ "data_nascimento, "
				+ "url_foto "
				+ "from pessoa "
				+ "where id_usuario = ?";
		
		try {
			preparedStatement = conexao.prepareStatement(selectTableSQL);
			
			preparedStatement.setString(1, idPessoa);
			ResultSet rs = preparedStatement.executeQuery();
			
			if ( rs.next() ) {
				pessoa = new Pessoa();
				pessoa.setId_usuario(rs.getString("id_usuario"));
				pessoa.setData_cadastro(DateConversor.StringToDate(rs.getString("data_cadastro")));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setData_ultimo_login(DateConversor.StringToDate(rs.getString("data_ultimo_login")));
				pessoa.setData_ultima_atualizacao_runs(DateConversor.StringToDate( rs.getString("data_ultima_atualizacao_runs") ) );
				pessoa.setData_ultima_atualizacao_walks(DateConversor.StringToDate( rs.getString("data_ultima_atualizacao_walks") ) );
				pessoa.setData_ultima_atualizacao_bikes(DateConversor.StringToDate( rs.getString("data_ultima_atualizacao_bikes") ) );
				pessoa.setRank_anual(rs.getString("rank_anual"));
				pessoa.setGenero(rs.getString("genero"));
				pessoa.setData_nascimento(DateConversor.StringToDate( rs.getString("data_nascimento") ) );
				pessoa.setUrl_foto(rs.getString("url_foto"));
			}
			
		} catch (SQLException e) {
			 
			Logger.insertLog(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (conexao != null) {
				conexao.close();
			}
			
		}
		
		return pessoa;
	}
	
	public boolean removePessoaFromId(String userId) throws SQLException {
		conexao = new JDBCFactory().getConnection();

		PreparedStatement preparedStatement = null;
 
		String deleteSQL = "DELETE from pessoa WHERE id_usuario = ?";
 
		try {

			preparedStatement = conexao.prepareStatement(deleteSQL);
			
			int i = 0;
			
			preparedStatement.setString(++i, userId);
 
			// execute delete SQL stetement
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
 
			Logger.insertLog(e.getMessage());
			return false;
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (conexao != null) {
				conexao.close();
			}
 
		}
	}
	
	public List<Pessoa> leTodasPessoas() throws SQLException {
		
		conexao = new JDBCFactory().getConnection();
		PreparedStatement preparedStatement = null;
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		String selectTableSQL = "SELECT "
				+ "id_usuario, "
				+ "data_cadastro, "
				+ "nome,"
				+ "data_ultimo_login, "
				+ "data_ultima_atualizacao_runs, "
				+ "data_ultima_atualizacao_walks, "
				+ "data_ultima_atualizacao_bikes, "
				+ "rank_anual, "
				+ "genero, "
				+ "data_nascimento, "
				+ "url_foto "
				+ "from pessoa";
		
		try {
			preparedStatement = conexao.prepareStatement(selectTableSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while ( rs.next() ) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId_usuario(rs.getString("id_usuario"));
				pessoa.setData_cadastro(DateConversor.StringToDate(rs.getString("data_cadastro")));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setData_ultimo_login(DateConversor.StringToDate(rs.getString("data_ultimo_login")));
				pessoa.setData_ultima_atualizacao_runs(DateConversor.StringToDate( rs.getString("data_ultima_atualizacao_runs") ) );
				pessoa.setData_ultima_atualizacao_walks(DateConversor.StringToDate( rs.getString("data_ultima_atualizacao_walks") ) );
				pessoa.setData_ultima_atualizacao_bikes(DateConversor.StringToDate( rs.getString("data_ultima_atualizacao_bikes") ) );
				pessoa.setRank_anual(rs.getString("rank_anual"));
				pessoa.setGenero(rs.getString("genero"));
				pessoa.setData_nascimento(DateConversor.StringToDate( rs.getString("data_nascimento") ) );
				pessoa.setUrl_foto(rs.getString("url_foto"));
				
				pessoas.add(pessoa);
			}
			
		} catch (SQLException e) {
			 
			Logger.insertLog(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (conexao != null) {
				conexao.close();
			}
			
		}
		
		return pessoas;
	}

}

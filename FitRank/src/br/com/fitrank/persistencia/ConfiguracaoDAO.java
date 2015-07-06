package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.util.JDBCFactory;


public class ConfiguracaoDAO {
	
	private Connection conexao;

	public ConfiguracaoDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}

	public Configuracao adicionaConfiguracao(Configuracao configuracao) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO configuracao"
				+ "(id_configuracao, modalidade, dia_noite, intervalo_data, favorito, padrao_modalidade, id_configuracao) VALUES"
				+ "(?,?,?,?,?,?,?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, configuracao.getIdConfiguracao());
			preparedStatement.setString(2, configuracao.getModalidade());
			preparedStatement.setString(3, configuracao.getDiaNoite());
			preparedStatement.setString(4, configuracao.getIntervaloData());
			preparedStatement.setInt(5, configuracao.getFavorito());
			preparedStatement.setInt(6, configuracao.getPadraoModalidade());
			preparedStatement.setString(7, configuracao.getIdPessoa());

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
		return configuracao;
	}
	
	public boolean removeConfiguracaoFromId(Configuracao configuracao) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String deleteSQL = "DELETE from configuracao WHERE id_configuracao = ?";
 
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, configuracao.getIdConfiguracao());
 
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

	public Configuracao leConfiguracao(Configuracao configuracao) throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT id_configuracao, modalidade, "
				+ "dia_noite, intervalo_data, "
				+ "favorito, padrao_modalidade, "
				+ "id_pessoa from configuracao limit 1";
		
		try {
			
			dbConnection = conexao;
			statement = dbConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(selectTableSQL);
			
			rs.next();
			
			
			configuracao.setIdConfiguracao(rs.getInt("id_configuracao"));
			configuracao.setModalidade(rs.getString("modalidade"));
			configuracao.setDiaNoite(rs.getString("dia_noite"));
			configuracao.setIntervaloData(rs.getString("intervalo_data"));
			configuracao.setFavorito(rs.getInt("favorito"));
			configuracao.setPadraoModalidade(rs.getInt("padrao_modalidade"));
			configuracao.setIdPessoa(rs.getString("id_pessoa"));
			
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
		
		return configuracao;
	}
	
	public Configuracao atualizaConfiguracao(Configuracao configuracao) throws SQLException {

		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL  = "update configuracao set "
				+ "modalidade = ?, "
				+ "dia_noite = ?, "
				+ "intervalo_data = ?, "
				+ "favorito = ?, "
				+ "padrao_modalidade = ?, "
				+ "id_pessoa = ? where id_configuracao = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			
			preparedStatement.setString(1, configuracao.getModalidade());
			preparedStatement.setString(2, configuracao.getDiaNoite());
			preparedStatement.setString(3, configuracao.getIntervaloData());
			preparedStatement.setInt(4, configuracao.getFavorito());
			preparedStatement.setInt(5, configuracao.getPadraoModalidade());
			preparedStatement.setString(6, configuracao.getIdPessoa());
			preparedStatement.setInt(7, configuracao.getIdConfiguracao());
			
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
		
		return configuracao;
	}
}

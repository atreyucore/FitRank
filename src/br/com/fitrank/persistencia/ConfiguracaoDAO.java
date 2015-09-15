package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.util.ConstantesFitRank;
import br.com.fitrank.util.JDBCFactory;


public class ConfiguracaoDAO {
	
	private Connection conexao;

	public ConfiguracaoDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}

	public Configuracao adicionaConfiguracao(Configuracao configuracao) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO configuracao ("
				+ "modalidade, "
				+ "dia_noite, "
				+ "intervalo_data, "
				+ "favorito, "
				+ "padrao_modalidade, "
				+ "id_pessoa"
				+ ") VALUES (?, ?, ?, ?, ?, ?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			int i = 0;
			
			preparedStatement.setString(++i, configuracao.getModalidade());
			preparedStatement.setString(++i, configuracao.getDiaNoite());
			preparedStatement.setString(++i, configuracao.getIntervaloData());
			preparedStatement.setInt(++i, configuracao.getFavorito());
			preparedStatement.setInt(++i, configuracao.getPadraoModalidade());
			preparedStatement.setString(++i, configuracao.getIdPessoa());

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
	
public Configuracao atualizaConfiguracao(Configuracao configuracao) throws SQLException {

		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL  = "update configuracao set "
				+ "modalidade = ?, "
				+ "dia_noite = ?, "
				+ "intervalo_data = ?, "
				+ "favorito = ?, "
				+ "padrao_modalidade = ?, "
				+ "id_pessoa = ? "
				+ "where id_configuracao = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			int i = 0;
			
			preparedStatement.setString(++i, configuracao.getModalidade());
			preparedStatement.setString(++i, configuracao.getDiaNoite());
			preparedStatement.setString(++i, configuracao.getIntervaloData());
			preparedStatement.setInt(++i, configuracao.getFavorito());
			preparedStatement.setInt(++i, configuracao.getPadraoModalidade());
			preparedStatement.setString(++i, configuracao.getIdPessoa());
			preparedStatement.setInt(++i, configuracao.getIdConfiguracao());
			
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
	

	public Configuracao leConfiguracaoPorId(Configuracao configuracao) throws SQLException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
	
		String selectTableSQL = "SELECT "
				+ "id_configuracao, "
				+ "modalidade, "
				+ "dia_noite, "
				+ "intervalo_data, "
				+ "favorito, "
				+ "padrao_modalidade, "
				+ "id_pessoa "
				+ "FROM configuracao "
				+ "WHERE id_configuracao = ?";
		
		try {
			
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			
			preparedStatement.setInt(1, configuracao.getIdConfiguracao());
			
			
			while (rs.next()) {
				
				configuracao.setIdConfiguracao(rs.getInt("id_configuracao"));
				configuracao.setModalidade(rs.getString("modalidade"));
				configuracao.setDiaNoite(rs.getString("dia_noite"));
				configuracao.setIntervaloData(rs.getString("intervalo_data"));
				configuracao.setFavorito(rs.getInt("favorito"));
				configuracao.setPadraoModalidade(rs.getInt("padrao_modalidade"));
				configuracao.setIdPessoa(rs.getString("id_pessoa"));

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
		
		return configuracao;
	}
	
	public Configuracao leConfiguracaoPorPessoa(Configuracao configuracao, Boolean isFavorito, Boolean isPadraoModalidade) throws SQLException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
	
		String selectTableSQL = "SELECT "
				+ "id_configuracao, "
				+ "modalidade, "
				+ "dia_noite, "
				+ "intervalo_data, "
				+ "favorito, "
				+ "padrao_modalidade, "
				+ "id_pessoa "
				+ "FROM configuracao "
				+ "WHERE id_pessoa = ? ";
		
		if(isFavorito){
			selectTableSQL += " AND favorito = ? ";
		}
		if(isPadraoModalidade){
			selectTableSQL += " AND padrao_modalidade = ? ";
		}
		
		try {
			
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			int i = 1;
			preparedStatement.setString(i++, configuracao.getIdPessoa());
			if(isFavorito){
				preparedStatement.setString(i++, ConstantesFitRank.CHAR_SIM);
			}
			if(isPadraoModalidade){
				preparedStatement.setString(i++, ConstantesFitRank.CHAR_SIM);
			}
			
			while (rs.next()) {
				
				configuracao.setIdConfiguracao(rs.getInt("id_configuracao"));
				configuracao.setModalidade(rs.getString("modalidade"));
				configuracao.setDiaNoite(rs.getString("dia_noite"));
				configuracao.setIntervaloData(rs.getString("intervalo_data"));
				configuracao.setFavorito(rs.getInt("favorito"));
				configuracao.setPadraoModalidade(rs.getInt("padrao_modalidade"));
				configuracao.setIdPessoa(rs.getString("id_pessoa"));

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
		
		return configuracao;
	}

//	public boolean removeConfiguracaoFromId(Configuracao configuracao) throws SQLException {
//
//		Connection dbConnection = null;
//		PreparedStatement preparedStatement = null;
// 
//		String deleteSQL = "DELETE from configuracao WHERE id_configuracao = ?";
// 
//		try {
//			dbConnection = conexao;
//			preparedStatement = dbConnection.prepareStatement(deleteSQL);
//			preparedStatement.setInt(++i, configuracao.getIdConfiguracao());
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

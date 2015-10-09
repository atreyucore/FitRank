package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fitrank.modelo.RankingPessoa;
import br.com.fitrank.util.JDBCFactory;

public class RankingPessoaDAO {
	private Connection conexao;

	public RankingPessoaDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}

	public RankingPessoa adicionaRankingPessoa(RankingPessoa rankingPessoa)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO ranking_pessoa("
				+ "id_ranking, "
				+ "id_pessoa, "
				+ "colocacao" 
				+ ") VALUES (?, ?, ?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setInt(++i, rankingPessoa.getId_ranking());
			preparedStatement.setString(++i, rankingPessoa.getId_pessoa());
			preparedStatement.setInt(++i, rankingPessoa.getColocacao());

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
		return rankingPessoa;
	}

	public RankingPessoa atualizaRankingPessoa(RankingPessoa rankingPessoa)
			throws SQLException {
	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
	
		String insertTableSQL = "UPDATE ranking_pessoa set "
				+ "id_pessoa = ?,"
				+ "colocacao = ? " 
				+ "where id_ranking = ?";
	
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setInt(++i, rankingPessoa.getColocacao());
			preparedStatement.setInt(++i, rankingPessoa.getId_ranking());
			preparedStatement.setString(++i, rankingPessoa.getId_pessoa());
	
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
		return rankingPessoa;
	}
	
	public RankingPessoa leRankingPessoa(RankingPessoa rankingPessoa)
			throws SQLException {
	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
	
		String selectTableSQL = "SELECT "
				+ "id_ranking, "
				+ "id_pessoa,"
				+ "colocacao "
				+ "FROM ranking_pessoa "
				+ "where id_ranking = ?";
	
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			int i = 0;
			
			preparedStatement.setInt(++i, rankingPessoa.getId_ranking());

			if ( rs.next() ) {
				rankingPessoa.setId_ranking(rs.getInt("id_ranking"));
				rankingPessoa.setId_pessoa(rs.getString("id_pessoa"));
				rankingPessoa.setColocacao(rs.getInt("colocacao"));
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
		return rankingPessoa;
	}
	
	public List<RankingPessoa> geraRankingDistancia(Configuracao configuracao)
			throws SQLException {
	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		List<RankingPessoa> listaRanking = new ArrayList<RankingPessoa>();
	
		String selectTableSQL = "SELECT @rownum := @rownum + 1 AS colocacao,							"
		 					+	"		consulta.id_usuario id_pessoa,									"
		 					+	"		consulta.resultado resultado									"
							+	"  FROM (SELECT @rownum := 0) r,										"
							+	"		(SELECT p.id_usuario,											"
		 					+	"				SUM(pf.distancia_percorrida) resultado					"
							+	"				FROM post_fitness pf,									"
  		 					+	"					 pessoa p											"
							+	"		  WHERE (p.id_usuario IN (SELECT a.id_amigo						"
							+	"									FROM amizade a						"
							+	"								   WHERE a.id_pessoa = p.id_usuario)	"
							+	"				 OR p.id_usuario = ?)									"
							+	"			AND p.id_usuario = pf.id_pessoa								"
							+	"			AND (str_to_date(pf.data_publicacao, '%d/%m/%Y') 			"
	     					+	"					BETWEEN str_to_date(?, '%d/%m/%Y') 					"
		      				+	"						AND str_to_date(?, '%d/%m/%Y'))					"
							+	"		 GROUP BY p.id_usuario											"
							+	"		 ORDER BY SUM(pf.distancia_percorrida) DESC ) consulta 			";
	
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			int i = 1;
			
			preparedStatement.setString(i++, configuracao.getIdPessoa());
			preparedStatement.setString(i++, DateConversor.DateToString(new Date()));
			preparedStatement.setString(i++, DateConversor.getPreviousWeekString());
			
			if(ConstantesFitRank.DIA.equalsIgnoreCase(configuracao.getIntervaloData())){
				preparedStatement.setString(i++, DateConversor.getPreviousDayString());
				
			} else if(ConstantesFitRank.SEMANA.equalsIgnoreCase(configuracao.getIntervaloData())){
				preparedStatement.setString(i++, DateConversor.getPreviousWeekString());
				
			} else if(ConstantesFitRank.MES.equalsIgnoreCase(configuracao.getIntervaloData())){
				preparedStatement.setString(i++, DateConversor.getPreviousMonthString());
				
			} else if(ConstantesFitRank.ANO.equalsIgnoreCase(configuracao.getIntervaloData())){
				preparedStatement.setString(i++, DateConversor.getPreviousYearString());
			}
			
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			RankingPessoa rankingPessoa;

			while ( rs.next() ) {
				rankingPessoa = new RankingPessoa();
				rankingPessoa.setId_pessoa(rs.getString("id_pessoa"));
				rankingPessoa.setColocacao(rs.getInt("colocacao"));
				rankingPessoa.setResultado(rs.getFloat("resultado"));
				
				listaRanking.add(rankingPessoa);
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
		return listaRanking;
	}
	
	public List<RankingPessoa> geraRankingVelocidadeMedia(Configuracao configuracao)
			throws SQLException {
	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		List<RankingPessoa> listaRanking = new ArrayList<RankingPessoa>();
	
		String selectTableSQL = "SELECT @rownum := @rownum + 1 AS colocacao,							"
		 					+	"		consulta.id_usuario id_pessoa,									"
		 					+	"		consulta.resultado resultado									"
							+	"  FROM (SELECT @rownum := 0) r,										"
							+	"		(SELECT p.id_usuario,											"
		 					+	"				SUM(pf.distancia_percorrida/pf.duracao) resultado		"
							+	"				FROM post_fitness pf,									"
  		 					+	"					 pessoa p											"
							+	"		  WHERE (p.id_usuario IN (SELECT a.id_amigo						"
							+	"									FROM amizade a						"
							+	"								   WHERE a.id_pessoa = p.id_usuario)	"
							+	"				 OR p.id_usuario = ?)									"
							+	"			AND p.id_usuario = pf.id_pessoa								"
							+	"			AND (str_to_date(pf.data_publicacao, '%d/%m/%Y') 			"
	     					+	"					BETWEEN str_to_date(?, '%d/%m/%Y') 					"
		      				+	"						AND str_to_date(?, '%d/%m/%Y'))					"
							+	"		 GROUP BY p.id_usuario											"
							+	"		 ORDER BY SUM(pf.distancia_percorrida) DESC ) consulta 			";
	
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			int i = 1;
			
			preparedStatement.setString(i++, configuracao.getIdPessoa());
			preparedStatement.setString(i++, DateConversor.DateToString(new Date()));
			
			if(ConstantesFitRank.DIA.equalsIgnoreCase(configuracao.getIntervaloData())){
				preparedStatement.setString(i++, DateConversor.getPreviousDayString());
				
			} else if(ConstantesFitRank.SEMANA.equalsIgnoreCase(configuracao.getIntervaloData())){
				preparedStatement.setString(i++, DateConversor.getPreviousWeekString());
				
			} else if(ConstantesFitRank.MES.equalsIgnoreCase(configuracao.getIntervaloData())){
				preparedStatement.setString(i++, DateConversor.getPreviousMonthString());
				
			} else if(ConstantesFitRank.ANO.equalsIgnoreCase(configuracao.getIntervaloData())){
				preparedStatement.setString(i++, DateConversor.getPreviousYearString());
			}
			
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			RankingPessoa rankingPessoa;

			while ( rs.next() ) {
				rankingPessoa = new RankingPessoa();
				rankingPessoa.setId_pessoa(rs.getString("id_pessoa"));
				rankingPessoa.setColocacao(rs.getInt("colocacao"));
				rankingPessoa.setResultado(rs.getFloat("resultado"));
				
				listaRanking.add(rankingPessoa);
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
		return listaRanking;
	}
}

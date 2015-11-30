package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.modelo.RankingPessoa;
import br.com.fitrank.util.ConstantesFitRank;
import br.com.fitrank.util.DateConversor;
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
				+ "colocacao, "
				+ "resultado "
				+ ") VALUES (?, ?, ?, ?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setInt(++i, rankingPessoa.getId_ranking());
			preparedStatement.setString(++i, rankingPessoa.getId_pessoa());
			preparedStatement.setInt(++i, rankingPessoa.getColocacao());
			preparedStatement.setFloat(++i, rankingPessoa.getResultado());

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
				+ "resultado = ?"
				+ "where id_ranking = ?";
	
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setInt(++i, rankingPessoa.getColocacao());
			preparedStatement.setInt(++i, rankingPessoa.getId_ranking());
			preparedStatement.setFloat(++i, rankingPessoa.getResultado());
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
				+ "colocacao, "
				+ "resultado "
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
				rankingPessoa.setResultado(rs.getFloat("resultado"));
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
		
		String dataInicial = "";
		
		if(ConstantesFitRank.DIA.equalsIgnoreCase(configuracao.getIntervaloData())){
			dataInicial = DateConversor.DateToString(new Date());
			
		} else if(ConstantesFitRank.SEMANA.equalsIgnoreCase(configuracao.getIntervaloData())){
			dataInicial =  DateConversor.getPreviousWeekString();
			
		} else if(ConstantesFitRank.MES.equalsIgnoreCase(configuracao.getIntervaloData())){
			dataInicial = DateConversor.getPreviousMonthString();
			
		} else if(ConstantesFitRank.ANO.equalsIgnoreCase(configuracao.getIntervaloData())){
			dataInicial = DateConversor.getPreviousYearString();
		}
	
		String selectTableSQL = "SELECT @rownum := @rownum + 1 AS colocacao,							\n"
							+	"		consulta.id_pessoa id_pessoa,									\n"
							+	"		consulta.resultado resultado									\n"
							+	"  FROM (SELECT @rownum := 0) r,										\n"
							+	"		(SELECT pf.id_pessoa,											\n"
							+	"				SUM(pf.distancia_percorrida) resultado		\n"
							+	"				FROM post_fitness pf,									\n"
							+	"					 pessoa p											\n"
							+	"		  WHERE (p.id_usuario IN (SELECT a.id_amigo						\n"
							+	"									FROM amizade a						\n"
							+	"								   WHERE a.id_pessoa = p.id_usuario)	\n"
							+	"				 OR p.id_usuario = '"+configuracao.getIdPessoa()+"')									\n"
							+   "			AND pf.modalidade = '"+configuracao.getModalidade()+"'										\n"							
							+	"			AND (str_to_date(pf.data_publicacao, '%d/%m/%Y') 			\n"
							+	"					BETWEEN str_to_date('"+dataInicial+"', '%d/%m/%Y') 					\n"
			  				+	"						AND str_to_date('"+DateConversor.DateToString(new Date())+"', '%d/%m/%Y'))					\n"
							+	"		 GROUP BY pf.id_pessoa											\n"
							+	"		 ) consulta 													\n"
							+	" ORDER BY resultado DESC												\n";
	
		try {
			dbConnection = conexao;
			selectTableSQL = selectTableSQL.replace("\t", "");
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
//			int i = 1;
//			
//			preparedStatement.setString(i++, configuracao.getIdPessoa());
//			preparedStatement.setString(i++, configuracao.getModalidade());
//			
//			
//			if(ConstantesFitRank.DIA.equalsIgnoreCase(configuracao.getIntervaloData())){
//				preparedStatement.setString(i++, DateConversor.getPreviousDayString());
//				
//			} else if(ConstantesFitRank.SEMANA.equalsIgnoreCase(configuracao.getIntervaloData())){
//				preparedStatement.setString(i++, DateConversor.getPreviousWeekString());
//				
//			} else if(ConstantesFitRank.MES.equalsIgnoreCase(configuracao.getIntervaloData())){
//				preparedStatement.setString(i++, DateConversor.getPreviousMonthString());
//				
//			} else if(ConstantesFitRank.ANO.equalsIgnoreCase(configuracao.getIntervaloData())){
//				preparedStatement.setString(i++, DateConversor.getPreviousYearString());
//			}
//			
//			preparedStatement.setString(i++, DateConversor.DateToString(new Date()));
			
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
		
		String dataInicial = "";
		
		if(ConstantesFitRank.DIA.equalsIgnoreCase(configuracao.getIntervaloData())){
			dataInicial = DateConversor.getPreviousDayString();
			
		} else if(ConstantesFitRank.SEMANA.equalsIgnoreCase(configuracao.getIntervaloData())){
			dataInicial =  DateConversor.getPreviousWeekString();
			
		} else if(ConstantesFitRank.MES.equalsIgnoreCase(configuracao.getIntervaloData())){
			dataInicial = DateConversor.getPreviousMonthString();
			
		} else if(ConstantesFitRank.ANO.equalsIgnoreCase(configuracao.getIntervaloData())){
			dataInicial = DateConversor.getPreviousYearString();
		}
	
		String selectTableSQL = "SELECT @rownum := @rownum + 1 AS colocacao,							\n"
		 					+	"		consulta.id_pessoa id_pessoa,									\n"
		 					+	"		(consulta.resultado/consulta.corridas) velocidade_media			\n"
							+	"  FROM (SELECT @rownum := 0) r,										\n"
							+	"		(SELECT pf.id_pessoa,											\n"
		 					+	"				SUM(pf.distancia_percorrida/pf.duracao) resultado,		\n"
		 					+	"				COUNT(pf.id_publicacao) corridas						\n"
							+	"				FROM post_fitness pf,									\n"
  		 					+	"					 pessoa p											\n"
							+	"		  WHERE (p.id_usuario IN (SELECT a.id_amigo						\n"
							+	"									FROM amizade a						\n"
							+	"								   WHERE a.id_pessoa = p.id_usuario)	\n"
							+	"				 OR p.id_usuario = '"+configuracao.getIdPessoa()+"')									\n"
							+   "			AND pf.modalidade = '"+configuracao.getModalidade()+"'										\n"							
							+	"			AND (str_to_date(pf.data_publicacao, '%d/%m/%Y') 			\n"
	     					+	"					BETWEEN str_to_date('"+dataInicial+"', '%d/%m/%Y') 					\n"
		      				+	"						AND str_to_date('"+DateConversor.DateToString(new Date())+"', '%d/%m/%Y'))					\n"
							+	"		 GROUP BY pf.id_pessoa											\n"
							+	"		 ) consulta 													\n"
							+	" ORDER BY velocidade_media DESC										\n";
	
		try {
			dbConnection = conexao;
			selectTableSQL = selectTableSQL.replace("\t", "");
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
//			int i = 1;
			
//			preparedStatement.setString(i++, configuracao.getIdPessoa());
//			preparedStatement.setString(i++, configuracao.getModalidade());
			
//			if(ConstantesFitRank.DIA.equalsIgnoreCase(configuracao.getIntervaloData())){
//				preparedStatement.setString(i++, DateConversor.getPreviousDayString());
//				
//			} else if(ConstantesFitRank.SEMANA.equalsIgnoreCase(configuracao.getIntervaloData())){
//				preparedStatement.setString(i++, DateConversor.getPreviousWeekString());
//				
//			} else if(ConstantesFitRank.MES.equalsIgnoreCase(configuracao.getIntervaloData())){
//				preparedStatement.setString(i++, DateConversor.getPreviousMonthString());
//				
//			} else if(ConstantesFitRank.ANO.equalsIgnoreCase(configuracao.getIntervaloData())){
//				preparedStatement.setString(i++, DateConversor.getPreviousYearString());
//			}
			
//			preparedStatement.setString(i++, DateConversor.DateToString(new Date()));
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			RankingPessoa rankingPessoa;

			while ( rs.next() ) {
				rankingPessoa = new RankingPessoa();
				rankingPessoa.setId_pessoa(rs.getString("id_pessoa"));
				rankingPessoa.setColocacao(rs.getInt("colocacao"));
				rankingPessoa.setResultado(rs.getFloat("velocidade_media"));
				
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

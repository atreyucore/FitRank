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
				+ "resultado, "
				+ "distancia_percorrida, "
				+ "velocidade_media, "
				+ "quantidade_corridas "
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			int i = 0;
			
			preparedStatement.setInt(++i, rankingPessoa.getId_ranking());
			preparedStatement.setString(++i, rankingPessoa.getId_pessoa());
			preparedStatement.setInt(++i, rankingPessoa.getColocacao());
			preparedStatement.setFloat(++i, rankingPessoa.getResultado());
			preparedStatement.setFloat(++i, rankingPessoa.getDistancia_percorrida());
			preparedStatement.setFloat(++i, rankingPessoa.getVelocidade_media());
			preparedStatement.setInt(++i, rankingPessoa.getQuantidade_corridas());

			// execute insert SQL statement
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
	
	public List<RankingPessoa> listaRankingPessoaPorIdRanking(int idRanking)
			throws SQLException {
	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		List<RankingPessoa> listaRanking = new ArrayList<RankingPessoa>();
	
		String selectTableSQL = "SELECT "
				+ "id_ranking, "
				+ "id_pessoa,"
				+ "colocacao, "
				+ "resultado, "
				+ "distancia_percorrida, "
				+ "velocidade_media, "
				+ "quantidade_corridas "
				+ "FROM ranking_pessoa "
				+ "WHERE id_ranking = ? "
				+ "ORDER BY colocacao ";
	
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			int i = 0;
			preparedStatement.setInt(++i, idRanking);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			RankingPessoa rankingPessoa;

			while ( rs.next() ) {
				rankingPessoa = new RankingPessoa();
				rankingPessoa.setId_ranking(rs.getInt("id_ranking"));
				rankingPessoa.setId_pessoa(rs.getString("id_pessoa"));
				rankingPessoa.setColocacao(rs.getInt("colocacao"));
				rankingPessoa.setResultado(rs.getFloat("resultado"));
				rankingPessoa.setDistancia_percorrida(rs.getFloat("distancia_percorrida"));
				rankingPessoa.setVelocidade_media(rs.getFloat("velocidade_media"));
				rankingPessoa.setQuantidade_corridas(rs.getInt("quantidade_corridas"));
				listaRanking.add(rankingPessoa);
			}
			
			// execute select SQL statement
			preparedStatement.executeQuery();
	
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
							+	"		(consulta.distancia/consulta.duracao) velocidade_media,			\n"
							+	"		consulta.distancia distancia_percorrida,						\n"
							+	"		consulta.corridas quantidade_corrida							\n"
							+	"  FROM (SELECT @rownum := 0) r,										\n"
							+	"		(SELECT pf.id_pessoa,											\n"
							+	"				SUM(pf.distancia_percorrida) distancia,					\n"
							+	"				SUM(pf.duracao) duracao,								\n"
							+	"				COUNT(pf.id_publicacao) corridas,						\n"
							+	"				SUM(pf.distancia_percorrida) resultado					\n"
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
				rankingPessoa.setResultado(rs.getFloat("distancia_percorrida"));
				rankingPessoa.setDistancia_percorrida(rs.getFloat("distancia_percorrida"));
				rankingPessoa.setVelocidade_media(rs.getFloat("velocidade_media"));
				rankingPessoa.setQuantidade_corridas(rs.getInt("quantidade_corrida"));
				
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
		 					+	"		(consulta.distancia/consulta.duracao) velocidade_media,			\n"
		 					+	"		consulta.distancia distancia_percorrida,						\n"
		 					+	"		consulta.corridas quantidade_corrida							\n"
							+	"  FROM (SELECT @rownum := 0) r,										\n"
							+	"		(SELECT pf.id_pessoa,											\n"
		 					+	"				SUM(pf.distancia_percorrida) distancia,					\n"
		 					+	"				SUM(pf.duracao) duracao,								\n"
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
				rankingPessoa.setVelocidade_media(rs.getFloat("velocidade_media"));
				rankingPessoa.setDistancia_percorrida(rs.getFloat("distancia_percorrida"));
				rankingPessoa.setQuantidade_corridas(rs.getInt("quantidade_corrida"));
				
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

package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fitrank.modelo.JSoup;
import br.com.fitrank.util.JDBCFactory;


public class JSoupDAO {
	
	private Connection conexao;

	public JSoupDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}
	
	public JSoup adicionaJSoup(JSoup jsoup) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO jsoup (distance, duration, avg_pace, "
				+ "elevation_gain, calories, heart_rate, max_heart_rate, weather, "
				+ "celsius_degrees, place_kind, evaluation, avg_speed, "
				+ "red_line_heart_rate, red_line_heart_rate_duration, anaerobic_heart_rate, "
				+ "anaerobic_heart_rate_duration, aerobic_heart_rate, aerobic_heart_rate_duration, "
				+ "fat_burning_heart_rate, fat_burning_heart_rate_duration, easy_heart_rate, "
				+ "easy_heart_rate_duration, no_zone_heart_rate, no_zone_heart_rate_duration, "
				+ "json_course) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1,jsoup.getId_course());
			preparedStatement.setFloat(2,jsoup.getDuration());
			preparedStatement.setFloat(3,jsoup.getAvg_pace());
			preparedStatement.setFloat(4,jsoup.getElevation_gain());
			preparedStatement.setFloat(5,jsoup.getCalories());
			preparedStatement.setFloat(6,jsoup.getHeart_rate());
			preparedStatement.setFloat(7,jsoup.getMax_heart_rate());
			preparedStatement.setString(8,jsoup.getWeather());
			preparedStatement.setFloat(9,jsoup.getCelsius_degrees());
			preparedStatement.setString(10,jsoup.getPlace_kind());
			preparedStatement.setString(11,jsoup.getEvaluation());
			preparedStatement.setFloat(12,jsoup.getAvg_speed());
			preparedStatement.setFloat(13,jsoup.getRed_line_heart_rate());
			preparedStatement.setFloat(14,jsoup.getRed_line_heart_rate_duration());
			preparedStatement.setFloat(15,jsoup.getAnaerobic_heart_rate());
			preparedStatement.setFloat(16,jsoup.getAnaerobic_heart_rate_duration());
			preparedStatement.setFloat(17,jsoup.getAerobic_heart_rate());
			preparedStatement.setFloat(18,jsoup.getAerobic_heart_rate_duration());
			preparedStatement.setFloat(19,jsoup.getFat_burning_heart_rate());
			preparedStatement.setFloat(20,jsoup.getFat_burning_heart_rate_duration());
			preparedStatement.setFloat(21,jsoup.getEasy_heart_rate());
			preparedStatement.setFloat(22,jsoup.getEasy_heart_rate_duration());
			preparedStatement.setFloat(23,jsoup.getNo_zone_heart_rate());
			preparedStatement.setFloat(24,jsoup.getNo_zone_heart_rate_duration());
			preparedStatement.setBytes(25,jsoup.getJson_couse());
			preparedStatement.setString(26,jsoup.getJson_url());
			
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
		return jsoup;
	}
	
	public JSoup atualizaJSoup(JSoup jsoup) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "update jsoup set distance = ?, duration = ?, avg_pace = ?, "
				+ "elevation_gain = ?, calories = ?, heart_rate = ?, max_heart_rate = ?, weather = ?, "
				+ "celsius_degrees = ?, place_kindv, evaluation = ?, avg_speed = ?, "
				+ "red_line_heart_rate = ?, red_line_heart_rate_duration = ?, anaerobic_heart_rate = ?, "
				+ "anaerobic_heart_rate_duration = ?, aerobic_heart_rate = ?, aerobic_heart_rate_duration = ?, "
				+ "fat_burning_heart_rate = ?, fat_burning_heart_rate_duration = ?, easy_heart_rate = ?, "
				+ "easy_heart_rate_duration = ?, no_zone_heart_rate = ?, no_zone_heart_rate_duration = ?, "
				+ "json_course = ?) "
				+ "where id_ ";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1,jsoup.getId_course());
			preparedStatement.setFloat(2,jsoup.getDuration());
			preparedStatement.setFloat(3,jsoup.getAvg_pace());
			preparedStatement.setFloat(4,jsoup.getElevation_gain());
			preparedStatement.setFloat(5,jsoup.getCalories());
			preparedStatement.setFloat(6,jsoup.getHeart_rate());
			preparedStatement.setFloat(7,jsoup.getMax_heart_rate());
			preparedStatement.setString(8,jsoup.getWeather());
			preparedStatement.setFloat(9,jsoup.getCelsius_degrees());
			preparedStatement.setString(10,jsoup.getPlace_kind());
			preparedStatement.setString(11,jsoup.getEvaluation());
			preparedStatement.setFloat(12,jsoup.getAvg_speed());
			preparedStatement.setFloat(13,jsoup.getRed_line_heart_rate());
			preparedStatement.setFloat(14,jsoup.getRed_line_heart_rate_duration());
			preparedStatement.setFloat(15,jsoup.getAnaerobic_heart_rate());
			preparedStatement.setFloat(16,jsoup.getAnaerobic_heart_rate_duration());
			preparedStatement.setFloat(17,jsoup.getAerobic_heart_rate());
			preparedStatement.setFloat(18,jsoup.getAerobic_heart_rate_duration());
			preparedStatement.setFloat(19,jsoup.getFat_burning_heart_rate());
			preparedStatement.setFloat(20,jsoup.getFat_burning_heart_rate_duration());
			preparedStatement.setFloat(21,jsoup.getEasy_heart_rate());
			preparedStatement.setFloat(22,jsoup.getEasy_heart_rate_duration());
			preparedStatement.setFloat(23,jsoup.getNo_zone_heart_rate());
			preparedStatement.setFloat(24,jsoup.getNo_zone_heart_rate_duration());
			preparedStatement.setBytes(25,jsoup.getJson_couse());
			preparedStatement.setString(26,jsoup.getJson_url());
			
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
		return jsoup;
	}
}

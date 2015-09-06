package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

		String insertTableSQL = "INSERT INTO jsoup ("
			    + "distance, "
			    + "duration, "
			    + "avg_pace, "
			    + "elevation_gain, "
			    + "calories, "
			    + "heart_rate, "
			    + "max_heart_rate, "
			    + "weather, "
			    + "celsius_degrees, "
			    + "place_kind, "
			    + "evaluation, "
			    + "avg_speed, "
			    + "red_line_heart_rate, "
			    + "red_line_heart_rate_duration, "
			    + "anaerobic_heart_rate, "
			    + "anaerobic_heart_rate_duration, "
			    + "aerobic_heart_rate, "
			    + "aerobic_heart_rate_duration, "
			    + "fat_burning_heart_rate, "
			    + "fat_burning_heart_rate_duration, "
			    + "easy_heart_rate, "
			    + "easy_heart_rate_duration, "
			    + "no_zone_heart_rate, "
			    + "no_zone_heart_rate_duration, "
			    + "json_course, "
			    + "url, "
			    + "id_course"
				+ ") VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			int i = 0;
			
			preparedStatement.setFloat(++i, jsoup.getDistance());
			preparedStatement.setFloat(++i, jsoup.getDuration());
			preparedStatement.setFloat(++i, jsoup.getAvg_pace());
			preparedStatement.setFloat(++i, jsoup.getElevation_gain());
			preparedStatement.setFloat(++i, jsoup.getCalories());
			preparedStatement.setFloat(++i, jsoup.getHeart_rate());
			preparedStatement.setFloat(++i, jsoup.getMax_heart_rate());
			preparedStatement.setString(++i, jsoup.getWeather());
			preparedStatement.setFloat(++i, jsoup.getCelsius_degrees());
			preparedStatement.setString(++i, jsoup.getPlace_kind());
			preparedStatement.setString(++i, jsoup.getEvaluation());
			preparedStatement.setFloat(++i, jsoup.getAvg_speed());
			preparedStatement.setFloat(++i, jsoup.getRed_line_heart_rate());
			preparedStatement.setFloat(++i,	jsoup.getRed_line_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getAnaerobic_heart_rate());
			preparedStatement.setFloat(++i, jsoup.getAnaerobic_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getAerobic_heart_rate());
			preparedStatement.setFloat(++i, jsoup.getAerobic_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getFat_burning_heart_rate());
			preparedStatement.setFloat(++i,	jsoup.getFat_burning_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getEasy_heart_rate());
			preparedStatement.setFloat(++i, jsoup.getEasy_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getNo_zone_heart_rate());
			preparedStatement.setFloat(++i,	jsoup.getNo_zone_heart_rate_duration());
			preparedStatement.setBytes(++i, jsoup.getJson_course());
			preparedStatement.setString(++i, jsoup.getJson_url());

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

		String insertTableSQL = "UPDATE jsoup SET "
				+ "distance = ?, "
				+ "duration = ?, "
				+ "avg_pace = ?, "
				+ "elevation_gain = ?, "
				+ "calories = ?, "
				+ "heart_rate = ?, "
				+ "max_heart_rate = ?, "
				+ "weather = ?, "
				+ "celsius_degrees = ?, "
				+ "place_kind = ?, "
				+ "evaluation = ?, "
				+ "avg_speed = ?, "
				+ "red_line_heart_rate = ?, "
				+ "red_line_heart_rate_duration = ?, "
				+ "anaerobic_heart_rate = ?, "
				+ "anaerobic_heart_rate_duration = ?, "
				+ "aerobic_heart_rate = ?, "
				+ "aerobic_heart_rate_duration = ?, "
				+ "fat_burning_heart_rate = ?, "
				+ "fat_burning_heart_rate_duration = ?, "
				+ "easy_heart_rate = ?, "
				+ "easy_heart_rate_duration = ?, "
				+ "no_zone_heart_rate = ?, "
				+ "no_zone_heart_rate_duration = ?, "
				+ "json_course = ?, "
				+ "url = ?" 
				+ "id_course = ?, "
					+ "WHERE id_jsoup = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			int i = 0;

			preparedStatement.setFloat(++i, jsoup.getDistance());
			preparedStatement.setFloat(++i, jsoup.getDuration());
			preparedStatement.setFloat(++i, jsoup.getAvg_pace());
			preparedStatement.setFloat(++i, jsoup.getElevation_gain());
			preparedStatement.setFloat(++i, jsoup.getCalories());
			preparedStatement.setFloat(++i, jsoup.getHeart_rate());
			preparedStatement.setFloat(++i, jsoup.getMax_heart_rate());
			preparedStatement.setString(++i, jsoup.getWeather());
			preparedStatement.setFloat(++i, jsoup.getCelsius_degrees());
			preparedStatement.setString(++i, jsoup.getPlace_kind());
			preparedStatement.setString(++i, jsoup.getEvaluation());
			preparedStatement.setFloat(++i, jsoup.getAvg_speed());
			preparedStatement.setFloat(++i, jsoup.getRed_line_heart_rate());
			preparedStatement.setFloat(++i,	jsoup.getRed_line_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getAnaerobic_heart_rate());
			preparedStatement.setFloat(++i,	jsoup.getAnaerobic_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getAerobic_heart_rate());
			preparedStatement.setFloat(++i,	jsoup.getAerobic_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getFat_burning_heart_rate());
			preparedStatement.setFloat(++i,	jsoup.getFat_burning_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getEasy_heart_rate());
			preparedStatement.setFloat(++i, jsoup.getEasy_heart_rate_duration());
			preparedStatement.setFloat(++i, jsoup.getNo_zone_heart_rate());
			preparedStatement.setFloat(++i,	jsoup.getNo_zone_heart_rate_duration());
			preparedStatement.setBytes(++i, jsoup.getJson_course());
			preparedStatement.setString(++i, jsoup.getJson_url());
			preparedStatement.setString(++i, jsoup.getId_course());
			preparedStatement.setInt(++i, jsoup.getId_jsoup());

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
	
	
	public JSoup leJSoup(JSoup jsoup) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT "
			    + "id_jsoup, "
			    + "distance, "
			    + "duration, "
			    + "avg_pace, "
			    + "elevation_gain, "
			    + "calories, "
			    + "heart_rate, "
			    + "max_heart_rate, "
			    + "weather, "
			    + "celsius_degrees, "
			    + "place_kind, "
			    + "evaluation, "
			    + "avg_speed, "
			    + "red_line_heart_rate, "
			    + "red_line_heart_rate_duration, "
			    + "anaerobic_heart_rate, "
			    + "anaerobic_heart_rate_duration, "
			    + "aerobic_heart_rate, "
			    + "aerobic_heart_rate_duration, "
			    + "fat_burning_heart_rate, "
			    + "fat_burning_heart_rate_duration, "
			    + "easy_heart_rate, "
			    + "easy_heart_rate_duration, "
			    + "no_zone_heart_rate, "
			    + "no_zone_heart_rate_duration, "
			    + "json_course, "
			    + "url, "
			    + "id_course"
				+ "from jsoup"
				+ "where id_jsoup = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			
			
			ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
			
			
			preparedStatement.setInt(1, jsoup.getId_jsoup());
			
			
			while (rs.next()) {
				
				jsoup.setId_jsoup(rs.getInt("id_jsoup"));
				jsoup.setDistance(rs.getFloat("distance"));
				jsoup.setDuration(rs.getFloat("duration"));
				jsoup.setAvg_pace(rs.getFloat("avg_pace"));
				jsoup.setElevation_gain(rs.getFloat("elevation_gain"));
				jsoup.setCalories(rs.getFloat("calories"));
				jsoup.setHeart_rate(rs.getFloat("heart_rate"));
				jsoup.setMax_heart_rate(rs.getFloat("max_heart_rate"));
				jsoup.setWeather(rs.getString("weather"));
				jsoup.setCelsius_degrees(rs.getFloat("celsius_degrees"));
				jsoup.setPlace_kind(rs.getString("place_kind"));
				jsoup.setEvaluation(rs.getString("evaluation"));
				jsoup.setAvg_speed(rs.getFloat("avg_speed"));
				jsoup.setRed_line_heart_rate(rs.getFloat("red_line_heart_rate"));
				jsoup.setRed_line_heart_rate_duration(rs.getFloat("red_line_heart_rate_duration"));
				jsoup.setAnaerobic_heart_rate(rs.getFloat("anaerobic_heart_rate"));
				jsoup.setAnaerobic_heart_rate_duration(rs.getFloat("anaerobic_heart_rate_duration"));
				jsoup.setAerobic_heart_rate(rs.getFloat("aerobic_heart_rate"));
				jsoup.setAerobic_heart_rate_duration(rs.getFloat("aerobic_heart_rate_duration"));
				jsoup.setFat_burning_heart_rate(rs.getFloat("fat_burning_heart_rate"));
				jsoup.setFat_burning_heart_rate_duration(rs.getFloat("fat_burning_heart_rate_duration"));
				jsoup.setEasy_heart_rate(rs.getFloat("easy_heart_rate"));
				jsoup.setEasy_heart_rate_duration(rs.getFloat("easy_heart_rate_duration"));
				jsoup.setNo_zone_heart_rate(rs.getFloat("no_zone_heart_rate"));
				jsoup.setNo_zone_heart_rate_duration(rs.getFloat("no_zone_heart_rate_duration"));
				jsoup.setJson_course(rs.getBytes("json_course"));
				jsoup.setJson_url(rs.getString("json_url"));

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
		return jsoup;
	}
}

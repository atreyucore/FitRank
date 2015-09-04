package br.com.fitrank.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fitrank.modelo.Aplicativo;
import br.com.fitrank.modelo.Course;
import br.com.fitrank.util.JDBCFactory;


public class CourseDAO {
	
	private Connection conexao;

	public CourseDAO() {
		this.conexao = new JDBCFactory().getConnection();
	}
	
	public Course adicionaCourse(Course course) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO course (id_course, distancia, "
				+ "calorias, ritmo, id_post) "
				+ "VALUES (?, ?, ?, ?, ?)";
				
		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, course.getId_course());
			preparedStatement.setFloat(2, course.getDistancia());
			preparedStatement.setFloat(3, course.getCalorias());
			preparedStatement.setFloat(4, course.getRitmo()); 
			preparedStatement.setString(5, course.getId_post());

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
		return course;
	}
	
	public Course atualizaCourse(Course course)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "update aplicativo set distancia = ? "
				+ "calorias = ?, ritmo = ?, id_post = ? where id_aplicativo = ?";

		try {
			dbConnection = conexao;
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			
			preparedStatement.setFloat(1, course.getDistancia());
			preparedStatement.setFloat(2, course.getCalorias());
			preparedStatement.setFloat(3, course.getRitmo()); 
			preparedStatement.setString(4, course.getId_post());
			preparedStatement.setString(5, course.getId_course());

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

		return course;
	}
}

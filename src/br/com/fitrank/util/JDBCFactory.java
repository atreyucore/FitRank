package br.com.fitrank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCFactory {

	// private static EntityManagerFactory entityManagerFactory=
	// Persistence.createEntityManagerFactory("fitrank");

	// public EntityManager getEntityManager(){

	// return entityManagerFactory.createEntityManager();

	// }

	public Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			return DriverManager.getConnection(
					"jdbc:mysql://eic.cefet-rj.br:8101/fitrank", "fitrank", "fitrank2015!");
			
//			return DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/fitrank", "fitrank", "fitrank2015!");
			
		} catch (SQLException | ClassNotFoundException e) {

			throw new RuntimeException(e);
		}
	}

}

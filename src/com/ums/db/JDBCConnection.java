package com.ums.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	private static JDBCConnection instance = new JDBCConnection();
	private Connection connection;

	private JDBCConnection() {
	}

	public Connection open() throws UMSDBException {
		if (connection != null)
			return connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/umsjdbc?serverTimezone=UTC", "root",
					"");
			return connection;
		} catch (ClassNotFoundException e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":Le driver JDBC est introuvable !");
		} catch (SQLException sqle) {
			throw new UMSDBException("ERROR:" + sqle.getClass() + ":" + sqle.getMessage());
		}
	}

	public void close() throws UMSDBException {
		try {
			if (connection != null)
				connection.close();
			
			    connection = null;
		} catch (SQLException e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}
	}

	public static JDBCConnection getInstance() {
		return instance;
	}
}

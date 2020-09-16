package DBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	/* Statement and Connection */
	protected Statement statement;
	protected Connection connection;

	/* creation of the DB manager using SQLite */
	public DBManager () throws ClassNotFoundException, SQLException {
			Class.forName("org.sqlite.JDBC");
			connection =  DriverManager.getConnection("jdbc:sqlite:Void_Gazer.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}

	public int executeUpdate(String query) throws SQLException {
		return statement.executeUpdate(query);
	}

	public void close() throws SQLException {
		if (connection != null) {
			statement.close();
			connection.close();
		}
	}
}

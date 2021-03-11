package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;
@Component
public class DatabaseConnect {
	Connection dbCon;
	String queryToExecute;
	Statement theStatement;
	ResultSet resultSet;
	
	public Statement getTheStatement() {
		return theStatement;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	
	public DatabaseConnect(){
		String urlToConnect = "jdbc:mysql://localhost:3306/xyz_bank";
    	String userName = "root";
    	String userPassword = "";
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbCon = DriverManager.getConnection(urlToConnect, userName, userPassword);
			try {
				theStatement = dbCon.createStatement();
			} catch (SQLException e) {
				System.out.println("Some issues while getting Statement : " + e.getMessage());
			}
    	} catch (ClassNotFoundException e) {
			System.out.println("Can't load the Driver : " + e.getMessage());
    	} catch (SQLException e) {
    		System.out.println("Can't connect to db : " + e.getMessage());
    	}
	}
	
}


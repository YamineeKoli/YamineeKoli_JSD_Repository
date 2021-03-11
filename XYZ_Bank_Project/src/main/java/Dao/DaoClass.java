package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.Customer_Details;
import bean.Customer_Transaction_Log;

public class DaoClass {
	public void createAccount(Customer_Details customer_Details,Customer_Transaction_Log customer_Transaction_Log) {
		String queryToExecute,queryToExecute2;
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationConfig.xml");
		//DatabaseConnect dbConnect = new DatabaseConnect();
		DatabaseConnect databaseConnect = context.getBean(DatabaseConnect.class);
		Statement theStatement = databaseConnect.getTheStatement();
		queryToExecute = "insert into customer_details(Phone_No,Name,Address,Balance,password) values ('"+ customer_Details.getPhone_no() + "', '"+ customer_Details.getName() + "', '" + customer_Details.getAddress() +  "', '" + customer_Details.getBalance() +"','" + customer_Details.getPassword()+"')";
		queryToExecute2 = "insert into customer_trasaction_log(Phone_No,Transaction_type,Transaction_Amount,Total_balance) values ('"+ customer_Transaction_Log.getPhone_No() + "', '"+ customer_Transaction_Log.getTransaction_type() + "', '" + customer_Transaction_Log.getTransaction_Amount() +  "', '" + customer_Transaction_Log.getTotal_balance() +"')";
    	try {
			if(theStatement.executeUpdate(queryToExecute) > 0) {
				System.out.println("New Customer details added successfully.");
				if(theStatement.executeUpdate(queryToExecute2) > 0)
					System.out.println("New transaction log details added successfully.");
				else
					System.out.println("Issues while inserting a new record...");
			}
			else {
				System.out.println("Issues while inserting a new record...");
			}
		} catch (SQLException e) {
			System.out.println("Some issues while inserting : " + e.getMessage());
		}
    }
	public boolean logintoAccount(String phone_no, String password) {
		String queryToExecute, queryToExecute2;
		boolean result=false;
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationConfig.xml");
		DatabaseConnect databaseConnect = context.getBean(DatabaseConnect.class);
		Statement theStatement = databaseConnect.getTheStatement();
		ResultSet resultSet = databaseConnect.getResultSet();
    	queryToExecute = "select * from customer_details where Phone_No =" + "'"+phone_no+"'";
    	try {
	  		resultSet = theStatement.executeQuery(queryToExecute);
	  		while(resultSet.next()) {
		  		if (resultSet.getString("Phone_No").equals(phone_no) && resultSet.getString("password").equals(password)){
		  			System.out.println("Login successful");
		  			result = true;
		  		}
		  		else {
		  			System.out.println("Login unsuccessful, password incorrect");
		  			result = false;
		  		}
	  		}
    	}catch (SQLException e) {
			System.out.println("Unable to execute query : " + e.getMessage());
		}
    	return result;
	}
	
	public void checkLowBalance(String phoneno) {
		String queryToExecute;
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationConfig.xml");
		DatabaseConnect databaseConnect = context.getBean(DatabaseConnect.class);
		Statement theStatement = databaseConnect.getTheStatement();
		ResultSet resultSet = databaseConnect.getResultSet();
    	queryToExecute = "select * from customer_details where Phone_No =" + "'" + phoneno + "'";
    	try {
	  		resultSet = theStatement.executeQuery(queryToExecute);
	  		if(resultSet.next()) {
		  		if (resultSet.getString("Phone_No").equals(phoneno) && resultSet.getInt("balance")<100){
		  			System.out.println("You have low balance");
		  		}
	  		}
    	}catch (SQLException e) {
			System.out.println("Unable to execute query : " + e.getMessage());
		}
		
	}

	public void deposit(String phoneno, int depositAmount) {
		String queryToExecute;
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationConfig.xml");
		DatabaseConnect databaseConnect = context.getBean(DatabaseConnect.class);
		Statement theStatement = databaseConnect.getTheStatement();
		ResultSet resultSet = databaseConnect.getResultSet();
		String queryToExecute2,queryToExecute3;
		queryToExecute = "select * from customer_details where Phone_No ="  + "'" + phoneno + "'";
		try {
	  		resultSet = theStatement.executeQuery(queryToExecute);
	  		if(resultSet.next()) {
	  			int totalBalance=resultSet.getInt("balance") + depositAmount;
	  			queryToExecute2 = "UPDATE customer_details SET balance =" + totalBalance + " WHERE Phone_No="  + "'" + phoneno + "'";
	  			queryToExecute3 = "insert into customer_trasaction_log(Phone_No,Transaction_type,Transaction_Amount,Total_balance) values ('"+ phoneno + "', '"+ "deposit" + "', '" + depositAmount +  "', '" + totalBalance +"')";
	  		  //	System.out.println(queryToExecute2);
	  			if(theStatement.executeUpdate(queryToExecute2) > 0) {
	  				System.out.println("Customer balance updated successfully.");
	  				if(theStatement.executeUpdate(queryToExecute3) > 0)
		  				System.out.println("Record added successfully.");
		  			else
		  				System.out.println("Issues while Updating record...");
	  			}
	  			else {
		  				System.out.println("Issues while Updating record...");
	  			}
	  		}
		}catch(SQLException e) {
			System.out.println("Some issues while updating : " + e.getMessage());
		}
	}

	public void withdraw(String phoneno, int withdrawAmount) {
		String queryToExecute;
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationConfig.xml");
		DatabaseConnect databaseConnect = context.getBean(DatabaseConnect.class);
		Statement theStatement = databaseConnect.getTheStatement();
		ResultSet resultSet = databaseConnect.getResultSet();
		String queryToExecute2,queryToExecute3;
		queryToExecute = "select * from customer_details where Phone_No =" + "'" + phoneno + "'";
    	try {
	  		resultSet = theStatement.executeQuery(queryToExecute);
	  		if(resultSet.next()) {
	  			int totalBalance=resultSet.getInt("balance") - withdrawAmount;
	  			if(totalBalance>=0) {
		  			queryToExecute2 = "UPDATE customer_details SET balance =" + totalBalance + " WHERE Phone_No="  + "'" + phoneno + "'";
		  			queryToExecute3 = "insert into customer_trasaction_log(Phone_No,Transaction_type,Transaction_Amount,Total_balance) values ('"+ phoneno + "', '"+ "withdraw" + "', '" + withdrawAmount +  "', '" + totalBalance +"')";
		  		//	System.out.println(queryToExecute2);
		  			if(theStatement.executeUpdate(queryToExecute2) > 0) {
		  				System.out.println("Customer balance updated successfully.");
		  				if(theStatement.executeUpdate(queryToExecute3) > 0)
				  			System.out.println("Record added successfully.");
				  		else
				  			System.out.println("Issues while Updating record...");
			  			}
			  			else {
				  			System.out.println("Issues while Updating record...");
			  			}
	  			}
	  		}
		}catch(SQLException e) {
			System.out.println("Some issues while updating : " + e.getMessage());
		}
	}

	public void print_transaction(String phoneno){
		String queryToExecute;
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationConfig.xml");
		DatabaseConnect databaseConnect = context.getBean(DatabaseConnect.class);
		Statement theStatement = databaseConnect.getTheStatement();
		ResultSet resultSet = databaseConnect.getResultSet();
    	queryToExecute = "select * from customer_trasaction_log where Phone_No ="  + "'" + phoneno + "'";
    	try {
	  		resultSet = theStatement.executeQuery(queryToExecute);
	  		System.out.println("\n\nBelow is Transaction history");
	  		while(resultSet.next())
	  			System.out.println("Transaction Type :" + resultSet.getString("Transaction_type") + 
	  					", \t Transaction Amount : " + resultSet.getInt("Transaction_Amount") + 
	  					", \t Total Balance : " + resultSet.getInt("Total_balance"));
    	}catch (SQLException e) {
			System.out.println("Unable to execute query : " + e.getMessage());
		}
	}

}

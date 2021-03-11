package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Dao.DaoClass;
import Dao.DatabaseConnect;
import bean.Customer_Details;
import bean.Customer_Transaction_Log;

public class ServiceClass implements service_interface {
	@Override
	public boolean search(String phoneno) {
		String queryToExecute;
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationConfig.xml");
		DatabaseConnect databaseConnect = context.getBean(DatabaseConnect.class);
		
		Statement theStatement = databaseConnect.getTheStatement();
		ResultSet resultSet = databaseConnect.getResultSet();
    	queryToExecute = "select * from customer_details where Phone_No =" + phoneno;
    	try {
	  		resultSet = theStatement.executeQuery(queryToExecute);
	  		if(resultSet.next()) 
	  			return true;
    	}catch (SQLException e) {
			System.out.println("Unable to execute query : " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public void createAccount(Customer_Details customer,Customer_Transaction_Log customer_Log) {
		DaoClass dao = new DaoClass();
		dao.createAccount(customer,customer_Log);
	}

	@Override
	public boolean logintoAccount(String phoneno, String password) {
		DaoClass dao = new DaoClass();
		boolean result = dao.logintoAccount(phoneno, password);
		if(result)
			new ServiceClass().checkLowBalance(phoneno);
		return result;
	}


	@Override
	public void checkLowBalance(String phoneno) {
		DaoClass dao = new DaoClass();
		dao.checkLowBalance(phoneno);
	}
	
	@Override
	public void deposit(String phoneno, int depositAmount) {
		DaoClass dao = new DaoClass();
		dao.deposit(phoneno,depositAmount);
	}

	@Override
	public void withdraw(String phoneno, int withdrawAmount) {
		DaoClass dao = new DaoClass();
		dao.withdraw(phoneno,withdrawAmount);
	}

	@Override
	public void fundTransfer(String phoneno1,int fund, String phoneno2) {
		DaoClass dao = new DaoClass();
		dao.withdraw(phoneno1,fund);
		dao.deposit(phoneno2,fund);
	}

	@Override
	public void print_transaction(String phoneno) {
		DaoClass dao = new DaoClass();
		dao.print_transaction(phoneno);
	}

	
}

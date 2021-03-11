package com.JavaFSD.Spring.XYZ_Bank_Project;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Dao.DatabaseConnect;
import Service.ServiceClass;
import Service.service_interface;
import bean.Customer_Details;
import bean.Customer_Transaction_Log;

public class App 
{
    public static void main( String[] args )
    {
    	int choice=0,balance;
    	String phoneno;
    	int transactionchoice=0;
    	String name,address,password;
    	boolean searchresult;
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationConfig.xml");
    	ServiceClass serviceClass = context.getBean(ServiceClass.class);
    	Customer_Details customer_Details = context.getBean(Customer_Details.class);
    	Customer_Transaction_Log customer_Transaction_Log = context.getBean(Customer_Transaction_Log.class);
    	Scanner scan=new Scanner(System.in);
        System.out.println("Welcometo XYZ Bank");
        while(choice!=3) {
        	System.out.println("Enter your chooice:\n1: Create New Account\n2: Login to Account\n3: Exit");
        	choice = scan.nextInt();
        	scan.nextLine();
        	switch(choice) {
	        	case 1:// Create new Account
	        		System.out.println("Please enter Phone Numebre:");
	        		phoneno = scan.nextLine();
	        		searchresult = serviceClass.search(phoneno);
	        		if(searchresult) {
	        			System.out.println("Phone Number already exist");
	        		}	
	        		else {
	        			System.out.println("Please Enter Name:");
	        			name = scan.nextLine();
	        			System.out.println("Please Enter Address:");
	        			address = scan.nextLine();
	        			System.out.println("Enter Password");
	        			password = scan.nextLine();
	        			customer_Details.setPhone_no(phoneno);
	        			customer_Details.setName(name);
	        			customer_Details.setAddress(address);
	        			customer_Details.setPassword(password);
	        			customer_Details.setBalance(0);
	        			customer_Transaction_Log.setPhone_No(phoneno);
	        			customer_Transaction_Log.setTransaction_Amount(0);
	        			customer_Transaction_Log.setTotal_balance(0);
	        			customer_Transaction_Log.setTransaction_type("Deposit");
	        			serviceClass.createAccount(customer_Details,customer_Transaction_Log);
	        		}
	        		break;
	        	case 2:
	        		System.out.println("Please enter Phone Numebre:");
	        		phoneno = scan.nextLine();
	        		searchresult = serviceClass.search(phoneno);	        		
	        		if(searchresult) {
	        			System.out.println("Enter Password");
	        			password = scan.nextLine();
	        			serviceClass.logintoAccount(phoneno, password);
	        			while(transactionchoice!=5) {
		        			System.out.println("Enter your chooice:\n1: Deposit Money\n2: Withdraw Money\n3: Fund Transfer\n4: Print transaction\n5: Exit");
		                	transactionchoice = scan.nextInt();
		                	scan.nextLine();
		                	switch(transactionchoice) {
		                	case 1:
		                		System.out.println("Enter amount to be deposited in account:");
		                		int depositAmount = scan.nextInt();
		                		scan.nextLine();
		                		serviceClass.deposit(phoneno,depositAmount);
		                		break;
		                	case 2:
		                		System.out.println("Enter amount to be withdraw from account:");
		                		int withdrawAmount = scan.nextInt();
		                		scan.nextLine();
		                		serviceClass.withdraw(phoneno,withdrawAmount);
		                		break;
		                	case 3:
		                		System.out.println("Enter phone no to which fund to be transferred");
		                		String phone2 = scan.nextLine();
		                		System.out.println("Enter fund to be transferred");
		                		int fund = scan.nextInt();		                		
		                		serviceClass.fundTransfer(phoneno, fund, phone2);
		                		break;
		                	case 4:
		                		serviceClass.print_transaction(phoneno);
		                		break;
		                	case 5:
		                		break;
		                	default:
		                	}
	        			}
	        		}
	        		else {
	        			System.out.println("Phone Number does not exist");
	        		}
	        		
	        		break;
	        	default:
        	}
        }
    } 
}

package Service;

import bean.Customer_Details;
import bean.Customer_Transaction_Log;

public interface service_interface {
	boolean search(String phoneno);
	public void createAccount(Customer_Details customer,Customer_Transaction_Log customer_Log);
	boolean logintoAccount(String phone_no,String password);
	void checkLowBalance(String phoneno);
	public void deposit(String phoneno, int depositAmount);
	public void withdraw(String phoneno, int withdrawAmount);
	public void fundTransfer(String phoneno1,int fund, String phoneno2);
	public void print_transaction(String phoneno);
}

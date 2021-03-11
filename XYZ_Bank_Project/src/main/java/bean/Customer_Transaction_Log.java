package bean;

public class Customer_Transaction_Log {
	int Transaction_Amount,Total_balance;
	String Transaction_type,Phone_No;
	public String getPhone_No() {
		return Phone_No;
	}
	public void setPhone_No(String phone_No) {
		Phone_No = phone_No;
	}
	public int getTransaction_Amount() {
		return Transaction_Amount;
	}
	public void setTransaction_Amount(int transaction_Amount) {
		Transaction_Amount = transaction_Amount;
	}
	public int getTotal_balance() {
		return Total_balance;
	}
	public void setTotal_balance(int total_balance) {
		Total_balance = total_balance;
	}
	public String getTransaction_type() {
		return Transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		Transaction_type = transaction_type;
	}

}

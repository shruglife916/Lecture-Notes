package bankapp.service;

import bankapp.models.Transaction;
import bankapp.models.User;

public interface CustomerService {

	public void applyNewAccountWithBalance(User customer, double balance) throws Exception;
	
	//public boolean transferMoney(String email, double amount);
	public boolean acceptMoneyTransfer(Transaction transaction);
}

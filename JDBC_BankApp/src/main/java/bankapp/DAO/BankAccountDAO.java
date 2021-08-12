package bankapp.DAO;

import bankapp.models.BankAccount;
import bankapp.models.User;

public interface BankAccountDAO {
	public BankAccount saveOne(BankAccount newAccount);
	   public BankAccount findOne(User userId);
	   public boolean updateBalance(int bankId,BankAccount existingAccount);
	   public void updateAccount(BankAccount account);
}

package bankapp.DAO;

import java.util.List;

import bankapp.models.Transaction;

public interface TransactionDAO {
public Transaction saveOne(Transaction transaction);	
	
	public boolean updateOne(Transaction transaction);
	
	public List<Transaction> findRecipient(int userId);
}

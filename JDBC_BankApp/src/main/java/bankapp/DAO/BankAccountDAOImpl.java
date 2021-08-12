package bankapp.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import bankapp.connection.PostgresConnection;
import bankapp.models.BankAccount;
import bankapp.models.CheckingAccount;
import bankapp.models.SavingAccount;
import bankapp.models.User;


public class BankAccountDAOImpl implements BankAccountDAO{
	private static Logger log = Logger.getLogger(BankAccountDAOImpl.class);
	
	@Override
	public BankAccount saveOne(BankAccount newAccount) {
		
		return null;
	}

	@Override
	public BankAccount findOne(User userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBalance(int bankId, BankAccount existingAccount) {
		String ca_account_number = null,sa_account_number=null;
		String sql =null;
		double newBalance = 0;
		
		try (Connection connection = PostgresConnection.getConnection()) {
			if(existingAccount instanceof CheckingAccount) {
				sql = "update checking_account set ca_balance = ? "
						+ "where ca_account_number = ? and bank_id = ?;";
				ca_account_number = ((CheckingAccount) existingAccount).getAccountNumber();
				newBalance = ((CheckingAccount) existingAccount).getBalance();
			} else if (existingAccount instanceof SavingAccount){
				sql = "update saving_account set sa_balance = ? "
						+ "where sa_account_number = ? "
						+ "and bank_id = ?;";
				sa_account_number = ((SavingAccount) existingAccount).getAccountNumber();
				newBalance = ((SavingAccount) existingAccount).getBalance();
			}
			if(ca_account_number != null ) {
				PreparedStatement updateBalance = connection.prepareStatement(sql);
				updateBalance.setDouble(1, newBalance);
				updateBalance.setString(2, ca_account_number);
				updateBalance.setInt(3, bankId);
								
				updateBalance.execute();
				return true;
			} else if(sa_account_number != null) {
				PreparedStatement updateBalance = connection.prepareStatement(sql);
				updateBalance.setDouble(1, newBalance);
				updateBalance.setString(2, sa_account_number);
				updateBalance.setInt(3, bankId);
								
				updateBalance.execute();
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
		}
		return false;
	}

	@Override
	public void updateAccount(BankAccount account) {
		// TODO Auto-generated method stub
		
	}

}

package bankapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import bankapp.connection.PostgresConnection;
import bankapp.exceptions.InternalErrorException;
import bankapp.exceptions.UserNotFoundException;
import bankapp.models.BankAccount;
import bankapp.models.CheckingAccount;
import bankapp.models.Customer;
import bankapp.models.Employee;
import bankapp.models.SavingAccount;
import bankapp.models.User;

public class UserDAOImpl implements UserDAO{
	private static Logger log = Logger.getLogger(UserDAO.class);
	@Override
	public User createCustomerAccount(User user, double balance) throws Exception{
		try (Connection connection = PostgresConnection.getConnection()){
			String sql = "insert into \"user\" (\"first_name\",\"last_name\",\"ssn\",\"username\",\"password\",\"isCustomer\",\"user_status\")\r\n"
					+ "values(?,?,?,?,true,'PENDING') returning \"user_id\";";
			PreparedStatement createCustomerAccount = connection.prepareStatement(sql);
			createCustomerAccount.setString(1, user.getFirstName());
			createCustomerAccount.setString(2, user.getLastName());
			createCustomerAccount.setInt(3, user.getSsn());
			createCustomerAccount.setString(4, user.getUsername());
			createCustomerAccount.setString(5, user.getPassword());
			
			
			ResultSet res = createCustomerAccount.executeQuery();
			
			int newCustomerId;
			if(res.next()) {
				newCustomerId = res.getInt("user_id");
				
			} else {
				throw new SQLException();
			}
			String bankingSql = "insert into \"banking_account\" (customer_id,pending_transaction,banking_status,initial_deposit)\r\n"
					+ "values(?,false,'Not Available','PENDING',?);";
			
			PreparedStatement applyNewBanking = connection.prepareStatement(bankingSql);
			applyNewBanking.setInt(1, newCustomerId);
			applyNewBanking.setDouble(2, balance);
			
			applyNewBanking.execute();
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
			throw new Exception("Internal error occured...Please contact SYSSADMIN");
		}
		return null;
	}
	@Override
	public boolean acceptOne(User user, CheckingAccount ca, SavingAccount sa) throws Exception {
		try(Connection connection = PostgresConnection.getConnection()) {
			String bankIdSql = "select ba.bank_id from \"user\" u join banking_account ba \r\n"
					+ "on u.user_id = ba.customer_id \r\n"
					+ "where user_id = ?;";
			PreparedStatement getBankId = connection.prepareStatement(bankIdSql);
			getBankId.setInt(1, user.getUserId());
			ResultSet res = getBankId.executeQuery();
			int bankId;
			
			if(res.next()) {
				bankId = res.getInt("bank_id");
				
			} else {
				throw new SQLException();
			}
			
			//update user and banking_account table
			String userSql = "update \"user\" set user_status = 'ACTIVE' where user_id =?;";
			PreparedStatement updateUser = connection.prepareStatement(userSql);
			updateUser.setInt(1, user.getUserId());
			updateUser.executeUpdate();
			
			String bankAccountSql = "update \"banking_account\" set banking_status = 'ACTIVE' where bank_id =?;";
			PreparedStatement updateBankingAccount = connection.prepareStatement(bankAccountSql);
			updateBankingAccount.setInt(1, bankId);
			updateBankingAccount.executeUpdate();
			
			//insert checking account
			String insertCheckingAccountSql = "insert into checking_account (bank_id,ca_account_number,ca_balance) \r\n"
					+ "values(?,?,?);";
			PreparedStatement insertCheckingAccount = connection.prepareStatement(insertCheckingAccountSql);
			insertCheckingAccount.setInt(1, bankId);
			insertCheckingAccount.setString(2, ca.getAccountNumber());
			insertCheckingAccount.setDouble(3, ca.getBalance());
			insertCheckingAccount.execute();
			
			//insert savingaccount
			String insertSavingAccountSql = "insert into saving_account (bank_id,sa_account_number,sa_balance) \r\n"
					+ "values(?,?,?);";
			PreparedStatement insertSavingAccount = connection.prepareStatement(insertSavingAccountSql);
			insertSavingAccount.setInt(1, bankId);
			insertSavingAccount.setString(2, sa.getAccountNumber());
			insertSavingAccount.setDouble(3, sa.getBalance());
			insertSavingAccount.execute();
		
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
			throw new Exception("Internal error occurred... Please contact SYSSADMIN");
		}
	
		return false;
	}
	@Override
	public boolean rejectOne(User user) {
		try (Connection connection = PostgresConnection.getConnection()) {
			String bankIdSql = "select ba.bank_id from \"user\" u join banking_account ba \r\n"
					+ "on u.user_id = ba.customer_id \r\n"
					+ "where user_id = ?;";
			PreparedStatement getBankId = connection.prepareStatement(bankIdSql);
			getBankId.setInt(1, user.getUserId());
			
			ResultSet res = getBankId.executeQuery();
			int bankId;
			if(res.next()) {
				bankId = res.getInt("bank_id");
				
			} else {
				throw new SQLException();
			}
			
			//update user and banking_account table
			String userSql = "update \"user\" set user_status = 'CLOSED' where user_id =?;";
			PreparedStatement updateUser = connection.prepareStatement(userSql);
			updateUser.setInt(1, user.getUserId());
			updateUser.executeUpdate();
			
			String bankAccountSql = "update \"banking_account\" set banking_status = 'CLOSED' where bank_id =?;";
			PreparedStatement updateBankingAccount = connection.prepareStatement(bankAccountSql);
			updateBankingAccount.setInt(1, bankId);
			updateBankingAccount.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
		}
		return false;
	}

	@Override
	public User findOne(String ssn, String password, boolean isCustomer)
			throws UserNotFoundException, InternalErrorException, SQLException, Exception {
		try(Connection connection = PostgresConnection.getConnection()) {
			String sql = "select * from \"user\" where ssn = ? and password = ?;";
			PreparedStatement getUser = connection.prepareStatement(sql);
			getUser.setString(1,ssn);
			getUser.setString(2,password);
			ResultSet res = getUser.executeQuery();
			
			if(res.next()) {
				User u;
				if(isCustomer) {
					u = new Customer(res.getString("user_status"));
				} else {
					u = new Employee();
				}
				u.setUserId(res.getInt("user_id"));
				u.setFirstName(res.getString("first_name"));
				u.setLastName(res.getString("last_name"));
				u.setSsn(res.getInt("ssn"));
				
				return u;
			}
			else {
				throw new UserNotFoundException();
			}
		
		} catch (SQLException e) {
			log.info(e);
			throw new InternalErrorException();
		}
	
	}
	
	@Override
	public List<User> findPendingCustomer() throws InternalErrorException, SQLException {
		List<User> listPendingUser = new ArrayList<User>();
		try (Connection connection =PostgresConnection.getConnection()) {
			String sql = "select u.user_id,u.first_name,u.last_name,u.ssn,u.user_status,\r\n"
					+ "ba.bank_id,ba.banking_status ,ba.initial_deposit\r\n"
					+ "from \"user\" u \r\n"
					+ "join banking_account ba \r\n"
					+ "on u.user_id  = ba.customer_id\r\n"
					+ "where u.\"user_status\" = 'PENDING' and u.\"isCustomer\" = true;";
			PreparedStatement getPendingUser = connection.prepareStatement(sql);
			ResultSet res = getPendingUser.executeQuery();
			
			while(res.next()) {
				User u = new Customer();
				u.setUserId(res.getInt("user_id"));
				u.setFirstName(res.getString("first_name"));
				u.setLastName(res.getString("last_name"));
				u.setSsn(res.getInt("ssn"));
				u.setInitialDeposit(res.getDouble("initial_deposit"));
				
				listPendingUser.add(u);
			} 
		} catch ( ClassNotFoundException | SQLException e) {
			log.info(e);
			throw new InternalErrorException();
		}
		return listPendingUser;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	
	@Override
	public List<Object> findCustomerInfoBySSN(User user) throws InternalErrorException {
		List<Object> listCustomerInfo = new ArrayList<Object>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select u.user_id, u.first_name ,u.last_name,u.ssn, u.user_status,\r\n"
					+ "ba.banking_status,ba.pending_transaction, ba.bank_id,ba.customer_id,\r\n"
					+ "ca.ca_account_number,ca.ca_balance,\r\n"
					+ "sa.sa_account_number,sa.sa_balance\r\n"
					+ "\r\n"
					+ "from \"user\" u \r\n"
					+ "inner join banking_account ba on u.user_id = ba.customer_id \r\n"
					+ "inner join checkng_account ca on ba.bank_id = ca.bank_id \r\n"
					+ "inner join saving_account sa on ba.bank_id = sa.bank_id \r\n"
					+ "where u.\"ssn\" = ? \r\n"
					+ "and u.user_status = 'ACTIVE' \r\n"
					+ "and ba.banking_status = 'ACTIVE';";
		
			PreparedStatement getCustomerInfo = connection.prepareStatement(sql);
			
			getCustomerInfo.setInt(1,user.getSsn());
			
			ResultSet res = getCustomerInfo.executeQuery();
			
			while(res.next()) {
				User customer = new Customer();
				BankAccount bankingAccount = new BankAccount();
				CheckingAccount checkingAccount =new CheckingAccount();
				SavingAccount savingAccount = new SavingAccount();
				customer.setUserId(res.getInt("user_id"));
				customer.setFirstName(res.getString("first_name"));
				customer.setLastName(res.getString("last_name"));
				customer.setSsn(res.getInt("ssn"));
			    
				
				listCustomerInfo.add(customer);
				bankingAccount.setBankId(res.getInt("bank_id"));
				bankingAccount.setCustomerId(res.getInt("customer_id"));
				bankingAccount.setBankingStatus(res.getString("banking_status"));
				bankingAccount.setPendingTransaction(res.getBoolean("pending_transaction"));
				
				listCustomerInfo.add(bankingAccount);
				checkingAccount.setAccountNumber(res.getString("ca_account_number"));
				checkingAccount.setBalance(res.getDouble("ca_balance"));
				
				listCustomerInfo.add(checkingAccount);
				savingAccount.setAccountNumber(res.getString("sa_account_number"));
				savingAccount.setBalance(res.getDouble("sa_balance"));
				listCustomerInfo.add(savingAccount);
				
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
			throw new InternalErrorException();
		}
		
		return listCustomerInfo;
	}

}

package bankapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import bankapp.connection.PostgresConnection;
import bankapp.models.Transaction;


public class TransactionDAOImpl implements TransactionDAO {
	private static Logger log = Logger.getLogger(TransactionDAOImpl.class);
	

	@Override
	public Transaction saveOne(Transaction transaction) {
		try (Connection connection = PostgresConnection.getConnection()){
			 String sqlGetUser = "select \"user_id\" from bank_schema.\"user\" u where ssn = ?;";
			    PreparedStatement getUser = connection.prepareStatement(sqlGetUser);
			    
			    getUser.setString(1, transaction.getRecipientSSN());
			    
			    ResultSet res = getUser.executeQuery();
			    int userId;
			    
			    if(res.next()) {
			    	userId = res.getInt("user_id");
					
				} else {
					throw new SQLException();
				}
			    
			  //insert to transaction table
			    String transactionSql = "insert into \"transaction\" \r\n"
			    		+ "(repicient_id,sender_account_number,sender_id,transaction_amount,transaction_status)\r\n"
			    		+ "values(?,?,?,?,'PENDING');";
			    PreparedStatement insertTransaction = connection.prepareStatement(transactionSql);
			    insertTransaction.setInt(1, userId);
			    insertTransaction.setString(2, transaction.getSenderAccountNumber());
			    insertTransaction.setInt(3, transaction.getSenderId());
			    insertTransaction.setDouble(4, transaction.getTransactionAmount());
			    
			    insertTransaction.execute();
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
		}
		return null;
	}

	@Override
	public boolean updateOne(Transaction transaction) {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "update \"transaction\" set transaction_status = 'ACCEPTED' where transaction_id = ?;";
			PreparedStatement acceptTransaction = connection.prepareStatement(sql);
			acceptTransaction.setInt(1, transaction.getTransactionId());
			
			acceptTransaction.executeUpdate();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
		}
		return false;
	}

	@Override
	public List<Transaction> findRecipient(int userId) {
		List<Transaction> list = new ArrayList<Transaction>();
		try (Connection connection = PostgresConnection.getConnection()){
			String sql = "select t.transaction_id,t.repicient_id ,t.transaction_amount,"
					+ "t.transaction_status, u.user_id, u.first_name,u.last_name,u.ssn\r\n"
					+ "from \"transaction\" t \r\n"
					+ "inner join \"user\" u \r\n"
					+ "on t.sender_id = u.user_id "
					+ "where t.transaction_status = 'PENDING' and t.recipient_id = ? ";
			PreparedStatement getTransaction = connection.prepareStatement(sql);
			getTransaction.setInt(1, userId);
			
			ResultSet res = getTransaction.executeQuery();
			
			while(res.next()) {
				Transaction transaction = new Transaction();
				transaction.setSenderSSN(res.getString("ssn"));
				transaction.setTransactionId(res.getInt("transaction_id"));
				transaction.setSenderId(res.getInt("user_id"));
				transaction.setSenderFirstName(res.getString("first_name"));
				transaction.setSenderLastName(res.getString("last_name"));
				transaction.setRecipientId(res.getInt("recipient_id"));
				transaction.setTransactionAmount(res.getDouble("transaction_amount"));
				transaction.setTransactionStatus(res.getString("transaction_status"));
				list.add(transaction);							
				
				
			} 
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		return null;
	}

}

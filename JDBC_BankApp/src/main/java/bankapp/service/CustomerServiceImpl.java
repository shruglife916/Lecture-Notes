package bankapp.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import bankapp.DAO.BankAccountDAO;
import bankapp.DAO.TransactionDAOImpl;
import bankapp.DAO.UserDAOImpl;
import bankapp.exceptions.InternalErrorException;
import bankapp.exceptions.UserNotFoundException;
import bankapp.launcher.BankLauncher;
import bankapp.models.BankAccount;
import bankapp.models.CheckingAccount;
import bankapp.models.SavingAccount;
import bankapp.models.Transaction;
import bankapp.models.User;

public class CustomerServiceImpl implements CustomerService,UserService {
	private static Logger log = Logger.getLogger(CustomerServiceImpl.class);
	//private UserImplementationDAO uid;
    private UserDAOImpl upd;
    private BankAccountDAO bad;
    private TransactionDAOImpl tpd;
    
    public CustomerServiceImpl(UserDAOImpl upd, BankAccountDAO bad, TransactionDAOImpl tpd) {
		super();
		this.upd = upd;
		this.bad = bad;
		this.tpd = tpd;
	}
	
    @Override
	public User userLogIn(String username, String password, boolean isCustomer) throws UserNotFoundException, InternalErrorException, SQLException, Exception {
    	User user = upd.findOne(username, password, isCustomer);	
		BankLauncher.log.info(user.getFirstName()+" "+user.getLastName()+" logged in!");
		if(user!= null) {
		   //log.info("Welcome, "+ user.getFirstName()+" "+user.getLastName());
		   return user;
		}

		return null;
	}

	

	@Override
	public List<Object> viewCustomerInfo(User customer) {
		List<Object> listInfo;
		try {
			listInfo = upd.findCustomerInfoBySSN(customer);	
			if(listInfo.size() != 0) {
				return listInfo;
			}
		} catch (InternalErrorException e) {
			e.printStackTrace();
			log.info(e);
		} 
		return null;
	}


	@Override
	public void applyNewAccountWithBalance(User customer, double balance) throws Exception{
		 User user = upd.createCustomerAccount(customer, balance);
		  if(user!= null) {
			   log.info("Welcome, "+ user.getFirstName()+" "+user.getLastName());
			   log.info("Thank you for registering new account! Your account is reviewed!");
			}
			else {
				log.info("Creation is not successful!");
			}
		  
		
	}
	public boolean deposit(int bankId,BankAccount existingAccount,double amount) {
		if(existingAccount instanceof CheckingAccount) {
			((CheckingAccount)existingAccount).setBalance(amount);
		} else if (existingAccount instanceof SavingAccount){
			((SavingAccount)existingAccount).setBalance(amount);
		}
		BankLauncher.log.info("Banking Account with Id: "+bankId+" just got deposit: "+amount+"$!");
		return bad.updateBalance(bankId ,existingAccount);
		
	}
	public boolean withdraw(int bankId,BankAccount existingAccount,double amount) {
		if(existingAccount instanceof CheckingAccount) {
			((CheckingAccount)existingAccount).withDraw(amount);
		} else if (existingAccount instanceof SavingAccount){
			((SavingAccount)existingAccount).withDraw(amount);
		}
		return bad.updateBalance(bankId ,existingAccount);
	}
	public boolean transferMoney(String ssn, int userId,BankAccount existingAccount,double amount) {
		Transaction t = new Transaction();
		t.setRecipientSSN(ssn);
		
		if(existingAccount instanceof CheckingAccount) {
			t.setSenderAccountNumber(((CheckingAccount)existingAccount).getAccountNumber());
		} else if (existingAccount instanceof SavingAccount){
			t.setSenderAccountNumber(((SavingAccount)existingAccount).getAccountNumber());			
		}
		t.setSenderId(userId);
		t.setTransactionAmount(amount);
		Transaction newTransaction = tpd.saveOne(t);
		BankLauncher.log.info("User with Id: "+userId+"just send "+amount+"$!");
		return newTransaction != null ? true : false;
	}
	
	public List<Transaction> findRecipient(int repicientId) {
		return tpd.findRecipient(repicientId);

	}

	@Override
	public boolean acceptMoneyTransfer(Transaction transaction) {
		return tpd.updateOne(transaction);
	
	}

}

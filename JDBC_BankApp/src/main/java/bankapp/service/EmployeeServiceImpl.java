package bankapp.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import bankapp.DAO.UserDAOImpl;
import bankapp.exceptions.InternalErrorException;
import bankapp.exceptions.UserNotFoundException;
import bankapp.models.CheckingAccount;
import bankapp.models.SavingAccount;
import bankapp.models.User;

public class EmployeeServiceImpl implements EmployeeService,UserService {
	private static Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	private UserDAOImpl upd;
	
	
	public EmployeeServiceImpl(UserDAOImpl upd) {
		   this.upd = upd;
	}
	
	
	@Override
	public User userLogIn(String email, String password, boolean isCustomer)
			throws UserNotFoundException, InternalErrorException, SQLException, Exception {
		User user = upd.findOne(email, password, isCustomer);	
		if(user!= null) {
		   log.info("Welcome, "+ user.getFirstName()+" "+user.getLastName());
		   return user;
		}
		else {
			log.info(" User is not found");
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
			System.out.println(e);
		} 
		return null;
	}

	@Override
	public List<User> viewListPendingUser() throws InternalErrorException, SQLException {
		List<User> list = upd.findPendingCustomer();
		if(list.size() != 0) {
			return list;
		}
		return null;
	}
	public boolean approveCustomer(User user) throws Exception{
		
		  CheckingAccount newCheckingAccount = new CheckingAccount();
		  SavingAccount	newSavingAccount = new SavingAccount();
		  newCheckingAccount.setAccountNumber(generateAccountNumber());
		  newCheckingAccount.setBalance(user.getInitialDeposit());
		  newSavingAccount.setAccountNumber(generateAccountNumber());
		  	  
		  return upd.acceptOne(user,newCheckingAccount,newSavingAccount);
	}
	
	public boolean rejectCustomer(User user) {
		  return upd.rejectOne(user);
	}
	
	public String generateAccountNumber() {
		int i =1;
		StringBuffer accountNumber = new StringBuffer("");
		while (true) {
			if(i==7) {
				break;
			}		
			int a = (int) (Math.random() * 10);
			
			accountNumber.append(a);
			i++;
		}
		return accountNumber.toString();
	}


	@Override
	public boolean setCustomerStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void viewCustomerAccount(User customer) {
		// TODO Auto-generated method stub
		
	}

}

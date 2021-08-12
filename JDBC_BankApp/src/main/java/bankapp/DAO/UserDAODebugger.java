package bankapp.DAO;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import bankapp.exceptions.UserNotFoundException;
import bankapp.models.CheckingAccount;
import bankapp.models.Customer;
import bankapp.models.SavingAccount;
import bankapp.models.User;

public class UserDAODebugger {
	private static Logger log = Logger.getLogger(UserDAODebugger.class);
	public static void main(String[] args) throws UserNotFoundException, SQLException{
		UserDAO ud = new UserDAOImpl();
		User customer = new Customer();
		customer.setUserId(5);
		
		//customer.setInitialDeposit(174);
		  CheckingAccount newCheckingAccount = new CheckingAccount();
		  SavingAccount	newSavingAccount = new SavingAccount();
		  newCheckingAccount.setAccountNumber(generateAccountNumber());
		  newCheckingAccount.setBalance(customer.getInitialDeposit());
		  newSavingAccount.setAccountNumber(generateAccountNumber());
		  
		//ud.acceptOne(customer,newChequingAccount,newSavingAccount);
		  try {
			log.info(ud.rejectOne(customer));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//User customer = new Customer(,"12345","Bob","Frac","5678");
		//System.out.println(ud.createCustomerAccount(customer,120));
	}
	public static String generateAccountNumber() {
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
}

package bankapp.launcher;
import java.sql.SQLException;
import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import bankapp.DAO.BankAccountDAOImpl;
import bankapp.DAO.TransactionDAOImpl;
import bankapp.DAO.UserDAOImpl;
import bankapp.exceptions.InternalErrorException;
import bankapp.exceptions.UserNotFoundException;
import bankapp.main.UserMenu;
import bankapp.service.CustomerServiceImpl;
import bankapp.service.EmployeeServiceImpl;

public class BankLauncher {
	public static Logger log = Logger.getLogger(BankLauncher.class);
	public static void main(String[] args) throws UserNotFoundException, InternalErrorException, 
	SQLException, InputMismatchException, Exception {
		UserDAOImpl uid = new UserDAOImpl();
		BankAccountDAOImpl bad = new BankAccountDAOImpl();
		TransactionDAOImpl tpd = new TransactionDAOImpl();
		CustomerServiceImpl csi = new CustomerServiceImpl(uid,bad,tpd);
		EmployeeServiceImpl esi = new EmployeeServiceImpl(uid);
		UserMenu userMenu = new UserMenu(csi,esi);
		
		log.info("Server has started!");
		log.info("Welcome to my bank\n");
		
		while(true) {
			
			userMenu.manageUserAccountInput();
			log.info("\n");
		}
	}
}

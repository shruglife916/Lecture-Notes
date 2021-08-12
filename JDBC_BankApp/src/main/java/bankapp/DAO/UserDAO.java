package bankapp.DAO;

import java.sql.SQLException;
import java.util.List;

import bankapp.exceptions.InternalErrorException;
import bankapp.exceptions.UserNotFoundException;
import bankapp.models.CheckingAccount;
import bankapp.models.SavingAccount;
import bankapp.models.User;

public interface UserDAO {
	public User createCustomerAccount(User user, double balance) throws Exception;
	public User findOne(String email, String password, boolean isCustomer) throws UserNotFoundException, InternalErrorException, SQLException, Exception;
	public List<User> findPendingCustomer() throws InternalErrorException, SQLException;
	public List<User> findAll();
    public boolean acceptOne(User user,CheckingAccount ca,SavingAccount sa) throws Exception;
    public boolean rejectOne(User user) throws Exception; 
	List<Object> findCustomerInfoBySSN(User user) throws InternalErrorException;
}

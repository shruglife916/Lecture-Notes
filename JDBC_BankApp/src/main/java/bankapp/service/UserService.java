package bankapp.service;

import java.sql.SQLException;
import java.util.List;

import bankapp.exceptions.InternalErrorException;
import bankapp.exceptions.UserNotFoundException;
import bankapp.models.User;

public interface UserService {
	public User userLogIn(String email,String password, boolean isCustomer) throws UserNotFoundException,InternalErrorException,SQLException, Exception;
		      
		      public List<Object> viewCustomerInfo(User customer);
}

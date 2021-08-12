package bankapp.service;

import java.sql.SQLException;
import java.util.List;

import bankapp.exceptions.InternalErrorException;
import bankapp.models.User;

public interface EmployeeService {
	public List<User> viewListPendingUser() throws InternalErrorException, SQLException;
	public boolean setCustomerStatus();
	public void viewCustomerAccount(User customer);
}

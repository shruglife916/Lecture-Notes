package bankapp.models;

public class Employee extends User {

	public Employee(String firstName, String lastName, int ssn, String username, String password) {
		super(firstName,lastName,ssn,username,password);
		super.setCustomer(false);
	}
	public Employee () {
		super.setCustomer(false);
	}
}

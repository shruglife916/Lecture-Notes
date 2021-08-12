package bankapp.models;

public class Customer extends User {
	private CustomerStatus customerStatus;
	
	public Customer(String firstName, String lastName, int ssn, String username, String password) {
		super(firstName,lastName,ssn,username,password);
	}

	public Customer() {
		super.setCustomer(true);
	}
	public Customer(String customerStatus) {
		super.setCustomer(true);
		for(CustomerStatus cs : CustomerStatus.values()) {
			if(cs.toString().equals(customerStatus)) {
				this.customerStatus = cs;
			}
		}
	}
	public String getCustomerStatus() {
		return customerStatus.toString();
	}
	public void setCustomerStatus(String customerStatus) {
		
		for(CustomerStatus cs : CustomerStatus.values()) {
			if(cs.toString().equals(customerStatus)) {
				this.customerStatus = cs;
			}
		}
		
		
	}
	
}

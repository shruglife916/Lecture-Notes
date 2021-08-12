package bankapp.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import bankapp.exceptions.InternalErrorException;
import bankapp.exceptions.UserNotFoundException;
import bankapp.models.BankAccount;
import bankapp.models.CheckingAccount;
import bankapp.models.Customer;
import bankapp.models.SavingAccount;
import bankapp.models.Transaction;
import bankapp.models.User;
import bankapp.service.CustomerServiceImpl;
import bankapp.service.EmployeeServiceImpl;

public class UserMenu {
	private static Logger log = Logger.getLogger(UserMenu.class);
	private CustomerServiceImpl csi;
	  private EmployeeServiceImpl esi;
	  Scanner Input;
	
	  public UserMenu(CustomerServiceImpl csi, EmployeeServiceImpl esi) {
		  this.csi = csi;
		  this.esi = esi;
		  Input = new Scanner(System.in);
	  }
	 
	  public void manageUserAccountInput() throws UserNotFoundException, InternalErrorException, SQLException,InputMismatchException, Exception {
		String username, password;
		int accountType;
		log.info("Welcome\n 1. New customer \n 2. Existing customer  \n 3. Employee \n");
	    log.info("Please enter to start!");
		this.Input.nextLine();
		log.info("Your choice is: ");
		
		try {
			accountType = this.Input.nextInt();
	    	if(accountType < 0 || accountType > 3) {
	    		log.warn("Please select the account 1, 2, or 3");
	    	}
	    	
	    	else {
	    		if(accountType == 1) {
	    			this.Input.nextLine();
	    			//1
	    			manageNewCustomer();
	    		} if(accountType == 2) {
	    			this.Input.nextLine();
	    			log.info("Welcome back to our bank!\n ");
	    			log.info("Please enter your username: ");
	    			username = this.Input.nextLine();
	    			log.info("Please enter your password: ");
	    			password = this.Input.nextLine();
	    			User customer = csi.userLogIn(username,password, true);
	    			if(customer.isCustomer()) {
	    				//Enter customer menu
	    				//this.userIn.nextLine();
	    				manageCustomerAccount(customer);
	    			};
	    		}
	    		else if(accountType == 3){
	    			log.info("Welcome back to our bank!\n ");
	    			this.Input.nextLine();
	    			log.info("Please enter your username: ");
	    			username = this.Input.nextLine();
	    			log.info("Please enter your password: ");
	    			password = this.Input.nextLine();
	    			User employee = esi.userLogIn(username,password, false);
	    			if(!employee.isCustomer()) {
	    				 
	    				manageEmployeeAccount(employee);
	    			}
	    		}
	    			    			    		
	    	}
		} catch (InputMismatchException e) {
			log.warn("Please select the options based on number!\n");
		} catch (UserNotFoundException u) {
			log.info("Wrong user or password! \n");
		}
	 }

	private void manageEmployeeAccount(User employee) throws Exception {
		while(true) {
			   log.info("Please choose the option below!\n");
			   log.info("1. View pending customer account\n"
			   		+ "2. View a customer's bank account\n"
			   		+ "3. View a log of all transactions\n"
			   		+ "4. Return to the main menu\n");
			  
				log.info("Your choice is: ");
				int option = this.Input.nextInt();
			   if(option == 4)
				   break;
			   switch(option) {
			   		case 1:
			   			viewPendingAccount();
			   			break;
			   		case 2:
			   			viewCustomerBankAccount();
			   			break;
			   		case 3:
			   			break;
			   		default: {
			   		 this.Input.nextLine();
						log.warn("Please enter option 1, 2, or 3 ! \n");
			   			break;
			   		}
			   }
			   log.info("Would you like to come to the main menu! \n "
						+ "1. Yes\n 2. No\n ");
				int type = this.Input.nextInt();
				if(type == 1) {
					break;
				}
		 }
		
	}

	private void manageCustomerAccount(User user) throws Exception{
		while(true)  {
			List<Object> list = csi.viewCustomerInfo(user);
			if(list == null) {
				log.info("Welcome "+ user.getFirstName()+" "+user.getLastName()+"!\n"
						+ "Your account is under review! Please wait!\n");
				log.info("Press enter to come back to the main menu!\n");
				this.Input.nextLine();
				break;
			}
			BankAccount bankAccount = new BankAccount();
			CheckingAccount checkingAccount = new CheckingAccount();
			SavingAccount savingAccount = new SavingAccount();
			for(Object o: list) {
				 if(o instanceof CheckingAccount) {
					checkingAccount = (CheckingAccount) o;
				} else if(o instanceof SavingAccount) {
					savingAccount = (SavingAccount) o;
				} else if(o instanceof BankAccount) {
					bankAccount = (BankAccount)o;
				} 
			}
			log.info("Welcome "+ user.getFirstName()+" "+user.getLastName()+"!\n");
			
			log.info("Select the options below for your account:\n");
			log.info("1. View your account details\n"
					         + "2. Deposit money to your account\n"
					         + "3. Withdraw money from your account\n"
					         + "4. View pending transaction\n"
					         + "5. Send money to another account\n"
					         + "6. Return to the main menu\n");
			log.info("Your choice is: ");
			int option = this.Input.nextInt();
			switch(option) {
			    case 1: 
			    	viewCustomerAccountDetail(checkingAccount,savingAccount);
			    	break;
			    case 2:
			    	depositMoney(bankAccount,checkingAccount,savingAccount);
			    	break;
			    case 3: 
			    	withdrawMoney(bankAccount,checkingAccount,savingAccount);
			    	break;
			    case 4:
			    	viewPendingTransaction(user.getUserId(),bankAccount,checkingAccount,savingAccount);
			    	break;
			    case 5:
			    	sendMoney(user.getUserId(),bankAccount,checkingAccount,savingAccount);
			    	break;
			    case 6:
			    	log.info("Back to main menu");
			    	break;
			    default:
			    	log.warn("Invalid choice");
			    	break;
			}
			
			if(option==6) {
				break;
			}
			
			
		} 			
		
	}

	private void viewCustomerAccountDetail(CheckingAccount ca, SavingAccount sa) {
		this.Input.nextLine();  
		  log.info("Your accounts:\n");
		  log.info(ca);
		  log.info(sa);
		  log.info("Please enter to come bank the main menu!\n");
		  this.Input.nextLine();

		
	}

	private void depositMoney(BankAccount ba, CheckingAccount ca, SavingAccount sa) {
		log.info("Which account do you need to deposit?\n"
				+ "1. Checking Account\n"
				+ "2. Saving Account\n");
		int option = this.Input.nextInt();
		double amount;
		while(true) {
			log.info("Please enter your amount:\n");
			amount =this.Input.nextDouble();
			if(amount>0)
				break;
			else {
				log.warn("Please enter positive amount!\n");
			}
		}
		this.Input.nextLine();
		if(option ==1) {
			if(csi.deposit(ba.getBankId(),ca, amount))
				log.info("You successfully deposit to your account!");		
		} else if(option == 2) {
			if(csi.deposit(ba.getBankId(), sa, amount))
				log.info("You successfully deposit to your account!");
		} else {
			log.info("Please try again and enter 1 or 2\n");
		}
	  log.info("Please enter to come bank the main menu!\n");
	  this.Input.nextLine();
	}
	

	private void withdrawMoney(BankAccount ba, CheckingAccount ca, SavingAccount sa) {
		log.info("Which account do you need to withdraw?\n"
				+ "1. Checking Account\n"
				+ "2. Saving Account\n");
		int option = this.Input.nextInt();
		double amount;
		while(true) {
			log.info("Please enter your amount:\n");
			amount =this.Input.nextDouble();
			if(option == 1 && (amount > ca.getBalance()) || amount <0) {
				log.warn("Your amount cannot withdraw amount bigger than the Checking Account balance or less than 0!\n");
			} else if(option == 2 && (amount > sa.getBalance() || amount < 0)){
				log.warn("Your amount cannot withdraw amount bigger than the Saving Account balance or less than 0!\n");
			} else {
				break;
			}
			this.Input.nextLine();
			if(option ==1) {
				if(csi.withdraw(ba.getBankId(),ca, amount))
					log.info("You successfully withdrawed to your account!");		
			} else if(option == 2) {
				if(csi.withdraw(ba.getBankId(), sa, amount))
					log.info("You successfully withdrawed to your account!");
			} else {
				log.warn("Please try again and enter 1 or 2\n");
			}
		  log.info("Please enter to come bank the main menu!\n");
		  this.Input.nextLine();
		}
	}

	private void viewPendingTransaction(int recipientId, BankAccount ba, CheckingAccount ca, SavingAccount sa) {
		while(true) {
			log.info("These below are your pending transactions: \n");
			this.Input.nextLine();
			List<Transaction> list = csi.findRecipient(recipientId);
			if(list.size()>0) {
				log.info(list);
				log.info("Please enter the transaction ID to accept: ");
				int option = this.Input.nextInt();
				log.info(option);
				for(Transaction t : list) {
					if(t.getTransactionId() == option) {
						log.info("Which account do you need to deposit?\n"
								+ "1. Checking Account\n"
								+ "2. Saving Account\n");
						int choice = this.Input.nextInt();
						this.Input.nextLine();
						if(choice ==1) {
							if(csi.deposit(ba.getBankId(),ca, t.getTransactionAmount())) {
								csi.acceptMoneyTransfer(t);
								log.info("You successfully deposited to your account!");	
							}
									
						} else if(choice == 2) {
							if(csi.deposit(ba.getBankId(), sa, t.getTransactionAmount())) {
								csi.acceptMoneyTransfer(t);
								log.info("You successfully deposited to your account!");
							}
								
						} else {
							log.warn("Invalid choice.\n");
						}
					}
				}
			
					
					} else {
							log.info("You don't have any pending transaction!");
						}
						log.info("Would you like to try another transaction! \n "
								+ "1. Yes\n 2. No\n ");
						int type = this.Input.nextInt();
						if(type == 2) {
							break;
						}	
					}
			}
				
	private void sendMoney(int userId, BankAccount ba, CheckingAccount ca, SavingAccount sa) {
		this.Input.nextLine();   
		log.info("Please enter the ssn of the recipient:\n");
		   String ssn = this.Input.nextLine();
		   log.info("Which account do you need to withdraw for sending money?\n"
					+ "1. Checking Account\n"
					+ "2. Saving Account\n");
			int option = this.Input.nextInt();
			log.info("Please enter your amount:\n");
			double amount =this.Input.nextDouble();
			this.Input.nextLine();
			if(option ==1) {
				if(csi.withdraw(ba.getBankId(),ca, amount)) {
					
					if(csi.transferMoney(ssn, userId, ca, amount))
						log.info("You have successfully transferred the money!");
				}
							
			} else if(option == 2) {
				if(csi.withdraw(ba.getBankId(), sa, amount))
				{
					if(csi.transferMoney(ssn, userId, sa, amount))
						log.info("You have successfully transferred the money!");
				}
			} else {
				log.warn("Invalid Choice\n");
			}
			log.info("Please enter to come bank the main menu!\n");
			  this.Input.nextLine();
	}

	private void viewCustomerBankAccount() {
		this.Input.nextLine();
		   log.info("Please enter the customer's last 4 digits of SSN:\n");
		   int ssn = this.Input.nextInt();
		   User customer = new Customer();
		   customer.setSsn(ssn);
		   List<Object> list = esi.viewCustomerInfo(customer);
		   if(list.size()>0)
			   log.info(list); 
		   else {
			  log.info("Customer is not found!\n");
		   }
		
	}

	private void viewPendingAccount() throws Exception{
		while(true) {
			 this.Input.nextLine();
			 log.info("This is the list of pending customers:\n");
			 List<User> list=null;
			 try {
				 list = esi.viewListPendingUser();
				 if(list != null) {
					log.info(list); 
				 }
				 else {
					 log.info("There are no pending users!\n");
				 }
				
			} catch (InternalErrorException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			 	if(list!=null) {
				 
				 log.info("Please enter the customer id!\n");
					int customerId = this.Input.nextInt();
					log.info("Which option do you choose:\n"
							+ "1. Accept\n"
							+ "2. Reject\n");
					int option = this.Input.nextInt();
					User customer=null;
					if(option == 1) {
						
						for(User u :list) {
							if(u.getUserId()==customerId)
								  customer = u;
						}
						if(esi.approveCustomer(customer))
						   log.info("Customer is accepted!");
						else {
							log.warn("There is something wrong");
						}
					}
					else if(option ==2) {
						for(User u :list) {
							if(u.getUserId()==customerId)
								  customer = u;
						}
						if(esi.rejectCustomer(customer)) {
							log.info("Customer is rejected!");
						} else {
							log.warn("There is something wrong");
						}
					} else {
						log.info("Please enter 1 or 2!\n");
					}
			 }
			 	log.info("Would you like to try another customer! \n "
						+ "1. Yes\n 2. No\n ");
				int type = this.Input.nextInt();
				if(type == 2) {
					break;
				}
		}
	}

	private void manageNewCustomer() throws Exception{
		while(true) {
			String username,password,firstName,lastName;
			int ssn;	
			log.info("Choose the best options for you: \n");
			log.info("1. Register new account without initial deposit\n"
					+ "2. Register new account with deposit \n"
					+ "3. Return to main menu");
			log.info("Your choice is: ");
			int option = this.Input.nextInt();
			if(option ==3)
				break;
			User customer = new Customer();
			if(option == 1 || option  == 2) {
				this.Input.nextLine();
				log.info("Please enter your username: ");
				username = this.Input.nextLine();
				log.info(username);
				log.info("Please enter your password: ");
				password = this.Input.nextLine();
				customer.setPassword(password);
				log.info("Please enter your first name: ");
				firstName = this.Input.nextLine();
				customer.setFirstName(firstName);
				log.info("Please enter your last name: ");
				lastName = this.Input.nextLine();
				customer.setLastName(lastName);
				//System.out.println(customer);
				log.info("Enter the last 4 digits of your SSN");
				ssn = this.Input.nextInt();
				customer.setSsn(ssn);
			} 
	
			if(option == 1) {
		
				this.csi.applyNewAccountWithBalance(customer, 0);

			}
			else if (option == 2) {
				log.info("Please enter your first deposit: ");
				double balance = this.Input.nextDouble();
				this.csi.applyNewAccountWithBalance(customer, balance);
			} else {
				this.Input.nextLine();
				log.info("Please enter option 1 or 2! \n");
				log.info("Would you like to try again! \n "
						+ "Type 'y' to continue 'n' to exit this menu:\n ");
				String type = this.Input.nextLine();
				if(type.equals("n")) {
					break;
				}
			}
		}
			
	
	
	}
	
	
}

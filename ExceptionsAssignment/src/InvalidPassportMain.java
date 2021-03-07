
public class InvalidPassportMain {

	public static void main(String[] args) {
		
		InvalidPassport i= new InvalidPassport();
		try {
			if (i.isValidPassport("456876901")) {
				System.out.println("Passport Validated");
			}
		}

			catch (InvalidPassportException e) {
				System.out.println(e.getMessage());
	}
	
		}
	} 
	

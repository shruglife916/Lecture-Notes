
public class CreditCardValidation {

	public static void main(String[] args) {
		String s = "1256-3478-5690-7821";
			
		if (s.contains("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}")) {
			System.out.println("Valid");
		} else 
			System.out.println("Invalid");
		
		

	}

}

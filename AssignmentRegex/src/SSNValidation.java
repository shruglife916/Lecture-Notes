
public class SSNValidation {

	public static void main(String[] args) {
		String s ="123-45-6789";
		
		if(s.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
			System.out.println("Valid");
	} else {
			System.out.println("Invalid");

	
	}
}
}
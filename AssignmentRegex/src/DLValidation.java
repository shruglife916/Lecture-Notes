
public class DLValidation {

	public static void main(String[] args) {
		String s = "53007388";
				
		if(s.matches("[0-9]{8}")) {
			System.out.println("Valid");
		} else {
			System.out.println("Invalid");
		}

	}

}

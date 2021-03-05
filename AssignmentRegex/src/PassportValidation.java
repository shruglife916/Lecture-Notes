
public class PassportValidation {

	public static void main(String[] args) {
		String s = "3247888511";

		if (s.contains("[0-9]{10}")) {
			System.out.println("Valid");
		} else {
			System.out.println("Invalid");
		}
	}
}

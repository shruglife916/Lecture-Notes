
public class ValidatorLogicMain {

	public static void main(String[]args) {
		
			ValidatorLogic v=new ValidatorLogic();
			try{
				if (v.isValidage(-1)) {
			}
				System.out.println("Age Validated");
			} catch (InvalidAgeException e) {
				System.out.println(e.getMessage());
			}
	}

}

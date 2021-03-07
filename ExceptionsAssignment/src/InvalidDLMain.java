
public class InvalidDLMain {

	public static void main(String[] args) {
		
		InvalidDL i=new InvalidDL();
		try {
			if (i.isValidDL("32218567")) {
				
				System.out.println("DL Validated");
			} 
		}
			catch (InvalidDLException e) {
				System.out.println(e.getMessage());
	}

	}

}

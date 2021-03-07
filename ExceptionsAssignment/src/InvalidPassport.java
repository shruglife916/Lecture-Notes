
public class InvalidPassport {

public boolean isValidPassport (String Passport) throws InvalidPassportException  {
	
	if (!Passport.matches("\\n [0-9]{9}")) {
		throw new InvalidPassportException ("Invalid Passport");
	}
	return true;
}
}


public class ValidatorLogic {

	public boolean isValidage(int age) throws InvalidAgeException{
		if ( age <=0 ) {
			throw new InvalidAgeException("Age can't be zero or negative. ");
		
		}
		if ( age < 18 || age > 35) {
			throw new InvalidAgeException("Users are ages between 18 and 35. ");
		}
		
		return true;	
			
		}

}

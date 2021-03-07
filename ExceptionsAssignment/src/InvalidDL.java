
public class InvalidDL {


	public String isValidDL;

	public boolean isValidDL (String DL) throws InvalidDLException {

		if (!DL.matches("\\n [F-I] [0-9]")) {
			throw new InvalidDLException ("Invalid DL Number");
		}
	return true;
	}
}
package bankapp.exceptions;

public class InvalidTransaction extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidTransaction() {
		super("Invalid Transaction");
	}
}

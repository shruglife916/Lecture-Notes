package bankapp.exceptions;

public class InternalErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public InternalErrorException() {
		  super("Something went wrong");
	}
}


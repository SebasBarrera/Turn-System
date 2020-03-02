package customExceptions;

@SuppressWarnings("serial")
public class InvalidFormatException extends Exception {

	public InvalidFormatException() {
		super("the date formar is incorrect");
	}


}

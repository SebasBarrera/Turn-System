package customExceptions;

@SuppressWarnings("serial")
public class DoingNothingException extends Exception {

	public DoingNothingException() {
		super("you are not advancing the time");
	}


}

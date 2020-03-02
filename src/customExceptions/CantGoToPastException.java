package customExceptions;

@SuppressWarnings("serial")
public class CantGoToPastException extends Exception {

	public CantGoToPastException(String what) {
		super("It was not possible to change the date because the "+what+" that you write is not in the future");
	}

	

}

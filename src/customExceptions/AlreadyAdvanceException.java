package customExceptions;

@SuppressWarnings("serial")
public class AlreadyAdvanceException extends Exception {

	public AlreadyAdvanceException() {
		super("You only can advance once at time, please advance de time to take this option again");
	}
}

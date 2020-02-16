package customExceptions;

@SuppressWarnings("serial")
public class TurnNotFountException extends Exception {
	
	public TurnNotFountException() {
		super("You can't attend a turn if nobody have take a turn");
	}
}

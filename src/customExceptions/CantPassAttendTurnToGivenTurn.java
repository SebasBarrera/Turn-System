package customExceptions;

@SuppressWarnings("serial")
public class CantPassAttendTurnToGivenTurn extends Exception {

	public CantPassAttendTurnToGivenTurn() {
		super("The attend turn can not pass de last given turn");
	}

}

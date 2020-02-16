package customExceptions;

@SuppressWarnings("serial")
public class InvalidSelectionException extends Exception{
	private int option;
	/**
	 * @param option the option to set
	 */
	public void setOption(int option) {
		this.option = option;
	}
	public InvalidSelectionException(int option) {
		super(option+" is not a valid selection");
		this.option = option;
	}
	public int getOption() {
		return this.option;
	}
}

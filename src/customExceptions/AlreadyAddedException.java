package customExceptions;

@SuppressWarnings("serial")
public class AlreadyAddedException extends Exception {
	private String id;
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	public AlreadyAddedException(String documentNumber) {
		super("The document number"+documentNumber+"it's already register in owr system");
		id = documentNumber;
	}
	public String getId() {
		return id;
	}

	

}

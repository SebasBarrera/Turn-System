package customExceptions;

@SuppressWarnings("serial")
public class InvalidDocumentTypeException extends Exception {
	private String documentType;
	
	public InvalidDocumentTypeException(String dt) {
		super("The document type you try to register "+dt+" is not a valid type.");
		setDocumentType(dt);
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

}

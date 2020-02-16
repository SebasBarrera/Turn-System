package customExceptions;

import model.DocumentType;

@SuppressWarnings("serial")
public class WithOutRegisterException extends Exception {
	private String documentNumber;
	public WithOutRegisterException(String documentNumber2, DocumentType documentType) {
		super("The document number "+documentNumber2+" with document type "+documentType +" is not register in our base date, please do it.");
		setDocumentNumber(documentNumber2);
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

}

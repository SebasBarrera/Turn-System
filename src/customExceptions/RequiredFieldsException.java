/**
 * 
 */
package customExceptions;


@SuppressWarnings("serial")
public class RequiredFieldsException extends Exception {

	private String field;

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @param first: represents the personal information missing.
	 */
	public RequiredFieldsException(String first) {
		super("To register a new user, also you need to put "+first);
	}

	public String getField() {
		return field;
	}


}

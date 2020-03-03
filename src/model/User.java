package model;

import java.util.ArrayList;

public class User implements Comparable<User>{
	
	private DocumentType documentType;
	private String documentNumber;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private Turn personalTurn;
	private int counterTurns;
	private ArrayList<String> turns;
	private boolean active;
	private ArrayList<Boolean> presents;

	public User(DocumentType dt, String dn, String fn, String ln) {
		documentType = dt;
		documentNumber = dn;
		firstName = fn;
		lastName = ln;
		address = null;
		phoneNumber = null;
		personalTurn = null;
		setCounterTurns(0);
		turns = new ArrayList<String>();
		active = false;
		presents = new ArrayList<Boolean>();
	}	
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the actives
	 */
	public ArrayList<Boolean> getPresents() {
		return presents;
	}

	/**
	 * @param actives the actives to set
	 */
	public void setPresents(ArrayList<Boolean> actives) {
		this.presents = actives;
	}
	
	public void addToPresentList(boolean isActive) {
		presents.add(isActive);
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	public void addToTurnList() {
		turns.add(personalTurn.getTurn());
	}

	/**
	 * @return the turns
	 */
	public ArrayList<String> getTurns() {
		return turns;
	}

	/**
	 * @param turns the turns to set
	 */
	public void setTurns(ArrayList<String> turns) {
		this.turns = turns;
	}

	/**
	 * @return the documentType
	 */
	public DocumentType getDocumentType() {
		return documentType;
	}
	
	public String getTurn() {
		return personalTurn.getTurn();
	}
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return the documentNumber
	 */
	public String getDocumentNumber() {
		return documentNumber;
	}

	/**
	 * @param documentNumber the documentNumber to set
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getlastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the personalTurn
	 */
	public Turn getPersonalTurn() {
		return personalTurn;
	}

	/**
	 * @param personalTurn the personalTurn to set
	 */
	public void setPersonalTurn(Turn personalTurn) {
		this.personalTurn = personalTurn;
	}

	@Override
	public String toString() {
		String msg  = "";
		String turn = "";
		
		msg += "DocumentType:    "+documentType+"\nComplete name:   "+firstName+" "+lastName + "\n";
		if (phoneNumber == "") {
			msg +="\nPhoneNumber:   "+phoneNumber;
		}
		if (personalTurn != null || (active && counterTurns>0)) {
			turn = "\nTurn:            "+getTurn();
		}
		if (counterTurns!=0 && !active) {
			for (int i = 0; i < counterTurns; i++) {
				msg+= "\nThe user ";
				if (presents.get(i)) {
					msg += "was attended in the turn: " + turns.get(i);
				} else {
					msg += "was not present when we called him in the turn: "+ turns.get(i);
				} 
			}
		}
		msg+= turn+"\n";
		return msg;
	}
	
	public int getCounterTurns() {
		return counterTurns;
	}

	public void setCounterTurns(int counterTurns) {
		this.counterTurns = counterTurns;
	}

	public void advanceTurn() {
		counterTurns++;
		
	}

	public int compareById(User arg0) {
		int ret = 0;		
		ret = this.documentNumber.compareTo(arg0.getFirstName());	
		return ret;
	}
	
	public int compareByFirstName(User arg0) {
		return this.firstName.compareTo(arg0.getFirstName());
	}

	@Override
	public int compareTo(User o) {
		

		return 0;
	}
	
}

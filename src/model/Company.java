package model;

import java.util.*;

import customExceptions.AlreadyAddedException;
import customExceptions.CantPassAttendTurnToGivenTurn;
import customExceptions.InvalidDocumentTypeException;
import customExceptions.RequiredFieldsException;
import customExceptions.TurnNotFountException;
import customExceptions.WithOutRegisterException;

/**
 * 
 * @author sebasbarrera
 * This is the principal class of the model
 */
public class Company {
	
	private Turn previusTurnGiven;
	private Turn actualTurnAttend;
	private ArrayList<User> users;
	private int counterGiven;
	private int counterAttend;
	/**
	 * The constructor of the class, it will be the initial state of the program
	 */
	public Company() {
		actualTurnAttend = new Turn("A00");
		previusTurnGiven = new Turn("A0/");
		users = new ArrayList<User>();
		counterAttend = 0;
		counterGiven = 0;
	}
	
	/**
	 * This constructor is for the test
	 * @param given: is the previus turn given to a user
	 */
	public Company(String given) {
		actualTurnAttend = new Turn("A00");
		previusTurnGiven = new Turn(given);
		users = new ArrayList<User>();
		counterAttend = 0;
		counterGiven = 0;
	}

	/**
	 * @return the counterGiven
	 */
	public int getCounterGiven() {
		return counterGiven;
	}

	/**
	 * @param counterGiven the counterGiven to set
	 */
	public void setCounterGiven(int counterGiven) {
		this.counterGiven = counterGiven;
	}

	/**
	 * @return the counterAttend
	 */
	public int getCounterAttend() {
		return counterAttend;
	}

	/**
	 * @param counterAttend the counterAttend to set
	 */
	public void setCounterAttend(int counterAttend) {
		this.counterAttend = counterAttend;
	}

	/**
	 * @return the previusTurngiven
	 */
	public Turn getPreviusTurnGiven() {
		return previusTurnGiven;
	}
	
	

	/**
	 * @param previusTurngiven the previusTurngiven to set
	 */
	public void setPreviusTurnGiven(Turn previusTurngiven) {
		this.previusTurnGiven = previusTurngiven;
	}
	

	
	public Turn getActualTurnAttend() {
		return actualTurnAttend;
	}
	

	/**
	 * @param previusTurngiven the previusTurngiven to set
	 */
	public void setActualTurnAttend(Turn actualTurnAttend) {
		this.actualTurnAttend = actualTurnAttend;
	}
	
	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public boolean addUser(DocumentType documentType, String documentNumber, String firstName, String secondName, String dt)
			throws RequiredFieldsException, InvalidDocumentTypeException, AlreadyAddedException {
		boolean added = false;
		
		if (dt.equals("")) 
			throw new RequiredFieldsException("document type");
		if (documentType == DocumentType.NO) 
			throw new InvalidDocumentTypeException(dt);
		if (documentNumber.equals("")) 
			throw new RequiredFieldsException("document number");
		if (firstName.equals("")) 
			throw new RequiredFieldsException("First name");
		if (secondName.equals("")) 
			throw new RequiredFieldsException("last name");
		searchUserRegistered(documentNumber, documentType);
		users.add(new User(documentType,documentNumber,firstName,secondName));
		return added; 
	}
		
	public void advanceGivenTurn() {
		String newpreviusTurngiven = advance(previusTurnGiven.getTurn());
		counterGiven++;
		previusTurnGiven = new Turn(newpreviusTurngiven);
	}
	
	public void advanceActualTurn() 
			throws CantPassAttendTurnToGivenTurn {
		if (counterAttend==counterGiven) {
			throw new CantPassAttendTurnToGivenTurn();	
		}
		String newActualTurnAttend = advance(actualTurnAttend.getTurn());
		counterAttend++;
		actualTurnAttend = new Turn(newActualTurnAttend);
		
	}
	
	public String consultNextAttendTurn() {
		String msg = "";
		if (counterAttend < counterGiven) {
			msg = "The next turn to attend is: "+actualTurnAttend.getTurn();
		} else {
			msg = "We don't have any user to attend";
		}
		msg += "\n";
		return msg;
	}
	
	public String isPresent(int present, String turn) 
			throws TurnNotFountException, CantPassAttendTurnToGivenTurn {
		String msg = "";
		if (turn.equals("A0/")) {
			throw new TurnNotFountException();
		}
		advanceActualTurn();
		boolean ward = false;
		for (int i = 0; i < users.size() && !ward; i++) {
			if (present == 1) {
				if (users.get(i).getTurn().equals(turn)) {
					users.get(i).addToPresentList(true);
					msg = "The user has been attended.\n";
					ward = true;
				}
			}else {
				msg = "The user has not been attended, but you can attend the next turn.\n";
			}
			
			users.get(i).setActive(false);
			users.get(i).addToPresentList(false);
			users.get(i).setPersonalTurn(null);
		}
		return msg;
	}
	
	public User searchUser(String documentNumber, DocumentType documentType) 
			throws WithOutRegisterException {
		User user = null;
		boolean ward = true;
		for (int i = 0; i < users.size() && ward; i++) {
			if (users.get(i).getDocumentNumber().equals(documentNumber) 
					&& users.get(i).getDocumentType().equals(documentType)) {
				user = users.get(i);
				ward = false;
			}
		}
		if (user == null) {
			throw new WithOutRegisterException(documentNumber, documentType);
		}
		return user;
	}
	
	public User searchUserRegistered(String documentNumber, DocumentType documentType) 
			throws AlreadyAddedException {
		User user = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getDocumentNumber().equals(documentNumber)
					&& users.get(i).getDocumentType().equals(documentType)) {
				throw new AlreadyAddedException(documentNumber);
			}
		}
		return user;
	}

	public String userToString(User user) {
		String msg = "";
		msg = user.toString();
		return msg;
	}
	
	public String userGetName(User user) {
		String msg = "";
		msg += user.getFirstName();
		msg += " ";
		msg += user.getlastName();
		return msg;
	}
	
	public void setAddress(String address, String documentNumber, DocumentType documentType) 
			throws WithOutRegisterException {
		User user = searchUser(documentNumber, documentType);
		user.setAddress(address);
	}
	
	public void setphone(String phone, String dn, DocumentType dt) 
			throws WithOutRegisterException {
		User user = searchUser(dn, dt);
		user.setPhoneNumber(phone);
	}

	public DocumentType chooseType(String dt) {
		DocumentType documentType = DocumentType.NO;
		switch (dt) {
			case "CC":
				 documentType = DocumentType.CC;
			break;
			case "TI":
				 documentType = DocumentType.TI;
			break;
			case "RC":
				 documentType = DocumentType.RC;
			break;
			case "PA":
				 documentType = DocumentType.PA;
			break;
			case "CE":
				 documentType = DocumentType.CE;
			break;
		}
		return documentType;
	}

	
	public String alreadyRegist(String dn, DocumentType dt) 
			throws WithOutRegisterException {
		User user = searchUser(dn, dt);
		String msg = "";
		msg += "Search result\n";
		msg += user.toString(); 
		if (user.getPersonalTurn() == null) {
			msg += "The assigned turn for "+userGetName(user)+" is: ";
			advanceGivenTurn();
			msg += getActualTurnAttend().getTurn();
			user.setPersonalTurn(getPreviusTurnGiven());
			user.advanceTurn();
			user.addToTurnList();
			user.setActive(true);
		}
		return msg;
	}
	

	public String showTurns() {
		String msg = "\n";
		if (previusTurnGiven.getTurn().equals("A0/")) {
			msg += "Welcome to the turn program\n";
		} else if (counterAttend == counterGiven) {
			msg += "Actually, we don't have users to attend\n";
		} else {
			msg+= "The next turn to attend is: "+getActualTurnAttend().getTurn()+"\n";
		}
		if (getPreviusTurnGiven().getTurn().charAt(2)!='/') {
			msg+= "The previus turn given is: "+getPreviusTurnGiven().getTurn();
		} else {
			msg += "At the moment we have not give any turns";
		}
		msg += "\n";
		return msg;
	}

	public String showNextTurn() 
			throws TurnNotFountException, CantPassAttendTurnToGivenTurn { 
		String msg = "";
		if (previusTurnGiven.getTurn().charAt(2)=='/') {
			throw new TurnNotFountException();
		}
		msg += "The next turn to attend is: "+actualTurnAttend.getTurn()+"\n";
		verifyCountersToAtend();
		return msg;
	}
	
	public String intToString(int num) {
		String nums = "";
		if (num <= 9) {
			nums = "0" + num;
		} else {
			nums = Integer.toString(num);
		}
		return nums;
	}
	
	public String advance(String turn) {
		String newTurn = "";
		String numbers = turn.substring(1);
		boolean ward = true;
		int num;
		if (numbers.charAt(1) == '/') {
			num = 0;
			ward = false;
		} else { 
			num = Integer.parseInt(numbers);
			if (num!=99) {
				num++;
			} else {
				num = 00;
			}
		} 
		char letter = turn.charAt(0);
		if (num==00 && ward) {
			if (letter!='Z') {
				letter++;
			} else {
				letter = 'A';
			}
		}
		String nums = intToString(num);
		
		newTurn = letter+nums+"";
		return newTurn;
	}

	public void verifyCountersToAtend()
			throws CantPassAttendTurnToGivenTurn {
		if (counterAttend == counterGiven) {
			throw new CantPassAttendTurnToGivenTurn();
		}	
	}
	
}
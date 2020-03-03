package model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import customExceptions.*;

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
	private ArrayList<TurnType> types;
	private DateAnHour date;
	private long difference;
	/**
	 * The constructor of the class, it will be the initial state of the program
	 */
	public Company() {
		TurnType initial = new TurnType("initial", 0);
		actualTurnAttend = new Turn("A00", initial, null);
		previusTurnGiven = new Turn("A0/", initial, null);
		users = new ArrayList<User>();
		types = new ArrayList<TurnType>();
		counterAttend = 0;
		counterGiven = 0;
		LocalDateTime now= LocalDateTime.now();
	    int year = now.getYear();
	    int month = now.getMonthValue();
	    int day = now.getDayOfMonth();
	    int hour = now.getHour();
	    int minute = now.getMinute();
	    int second = now.getSecond();
	    difference = 0;
		date = new DateAnHour(year, month, day, hour, minute, second);
	}
	
	/**
	 * This constructor is for the test
	 * @param given: is the previus turn given to a user
	 */
	public Company(String given, TurnType type) {TurnType initial = new TurnType("initial", 0);
	actualTurnAttend = new Turn("A00", initial, null);
	previusTurnGiven = new Turn(given, type, null);
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
		
	public void advanceGivenTurn(TurnType type) {
		String newpreviusTurngiven = advance(previusTurnGiven.getTurn());
		counterGiven++; 
		
	    
		previusTurnGiven = new Turn(newpreviusTurngiven, type, date);
	}
	
	public void advanceActualTurn() 
			throws CantPassAttendTurnToGivenTurn {
		if (counterAttend==counterGiven) {
			throw new CantPassAttendTurnToGivenTurn();	
		}
		String newActualTurnAttend = advance(actualTurnAttend.getTurn());
		counterAttend++;
		TurnType type = null;
		DateAnHour date = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getPersonalTurn().getTurn().equals(newActualTurnAttend)) {
				type = users.get(i).getPersonalTurn().getType();
				date = users.get(i).getPersonalTurn().getDate();
			}
		}
		actualTurnAttend = new Turn(newActualTurnAttend, type, date);
		
	}
	
	public int whatMonth() {
		int days = 0;
		switch (date.getMonth()) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
			break;
			case 2:
				days = 28;
			break;
			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;
		}
		return days;
	}
	
	public void passTime(int year, int month, int day, int hour, int minute, int second) throws CantGoToPastException, DoingNothingException, InvalidFormatException {
		int days = whatMonth();
		if (year == 0) 
			year = date.getYear();
		if (month == 0) 
			month = date.getMonth();
		if (day == 0) 
			day = date.getDay();
		if (hour == 0)
			hour = date.getHour();		
		if (second > 60 || minute > 60 || hour > 24 || day > days || month > 12) 
			throw new InvalidFormatException();
		if (year > date.getYear()) {
			throw new CantGoToPastException("Year");
		} else if(year == date.getYear()) {
			if (month > date.getMonth()) {
				throw new CantGoToPastException("Month");
			} else if (month == date.getMonth()) {
				if (day > date.getDay()) {
					throw new CantGoToPastException("Day");
				} else  if (day == date.getDay()) {
					if (hour > date.getHour()) {
						throw new CantGoToPastException("Hour");
					} else if (hour == date.getHour()) {
						if (minute > date.getMinute()) {
							throw new CantGoToPastException("Minute");
						} else if (minute == date.getMinute()) {
							if (second > date.getSecond()) {
								throw new CantGoToPastException("Second");
							} else if (second == date.getSecond()) {
								throw new DoingNothingException();
							}
						}
					}
				}
			}
		}	
		difference += date.compareDatesInMillis(new DateAnHour(year, month, day, hour, minute, second));
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

	
	public String alreadyRegist(String dn, DocumentType dt, String type) 
			throws WithOutRegisterException {
		User user = searchUser(dn, dt);
		String msg = "";
		msg += "Search result\n";
		msg += user.toString(); 
		TurnType typeOf = null; 
		for (int i = 0; i < types.size(); i++) {
			if (types.get(i).getName().equalsIgnoreCase(type)) {
				typeOf = types.get(i);
			}
		}
		if (user.getPersonalTurn() == null) {
			msg += "The assigned turn for "+userGetName(user)+" is: ";
			advanceGivenTurn(typeOf);
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

	public void aleatoryUsers(int many, String fn, String index) throws IOException {
		boolean contiene = false;
		File archivo = new File(fn);
		FileReader reader = new FileReader(archivo);
		BufferedReader lector = new BufferedReader(reader);
		String line = lector.readLine();
		int i = 0;
		while (line != null && !contiene && i<many) {
			int aleatoryN =  (int) (Math.random()*5);
			DocumentType documentType = null;
			switch (aleatoryN) {
			case 0:
				documentType = DocumentType.CC;
			break;
			case 1:
				documentType = DocumentType.CE;
			break;
			case 2:
				documentType = DocumentType.TI;
			break;
			case 3:
				documentType = DocumentType.PA;
			break;
			case 4:
				documentType = DocumentType.RC;
			}
			String dn = 10000000 + (int) (Math.random()*990000000) + "";
			String[] datos = line.split(index);
			String firstName = datos[0];
			String lastName = datos[1];
			User newUser = new User(documentType, dn, firstName, lastName);
			users.add(newUser);
			i++;
		}
		lector.close();
		reader.close();
	}	
		
	

	public String showTime() {
		String msg = "The actual date is:\n";
		msg += date.toString();
		return msg;
	}
	

	public void actualizeTime() {
		LocalDateTime now = LocalDateTime.now();
		date.setSecond(now.getSecond());
		date.setMinute(now.getMinute());
		date.setHour(now.getHour());
		date.setDay(now.getDayOfMonth());
		date.setMonth(now.getMonthValue());
		date.setYear(now.getYear());
		date = date.millisToDate(difference);
	}

	@SuppressWarnings("unchecked")
	public void loadUsers() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("nombre del archivo"));
		users = (ArrayList<User>)ois.readObject();
		ois.close();
		
	}

	public void saveRooms() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("nombre del archivo"));
		oos.writeObject(users);
		oos.close();
		
	}
	
	public void orderByIdBubbleSort() {
		for (int i = users.size(); i > 0; i--) {
			for (int j = 0; j < i-1; j++) {
				if (users.get(j).compareById(users.get(j+1))>0) {
					User temp = users.get(j);
					users.set(j, users.get(j+1));
					users.set(j+1, temp);
				}
			}
		}
	}
	
	public void orderByFirstNameInsertionSort() {
		for (int i = 1; i < users.size(); i++) {
			User toInsert = users.get(i);
			boolean finish = false;
			for (int j = i; j > 0 && !finish; j--) {
				User actual = users.get(j-1);
				if (actual.compareByFirstName(toInsert) > 0) {
					users.set(j, actual);
					users.set(j-1, toInsert);
				} else {
					finish = true;
				}
			}
		}
	}

	public long getDifference() {
		return difference;
	}

	public void setDifference(long difference) {
		this.difference = difference;
	}

	public String showTurnTypes() {
		String msg = "";
		for (int i = 0; i < types.size(); i++) {
			msg += types.get(i).getName() + "\n";
		}
		return msg;
	}

	public void createNewType(String name, double time) {
		types.add(new TurnType(name, time));
		
	}
	
	
	
	
}
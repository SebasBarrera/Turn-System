package ui;

import java.io.IOException;
import java.util.*;
import customExceptions.*;
import model.*;

public class Main {
	
	private Scanner sc;
	private Company comp;
	
	public Main() {
		sc = new Scanner(System.in);
		comp = new Company();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.options();	
	}
	
	public void menu() {
		System.out.println(comp.showTurns());
		System.out.println();
		System.out.println("Choose an option");
		System.out.println();
		System.out.println("1. Add a new user");
		System.out.println("2. Take a turn");
		System.out.println("3. Attend a turn");
		System.out.println("4. Create a new turn type");
		System.out.println("5. Generate a new report");
		System.out.println("6. Generate aleatory users");
		System.out.println("7. Actualize time");
		System.out.println("8. Show Date an Hour");
		System.out.println("9. Load");
		System.out.println("10.Save");
		System.out.println();
		System.out.println("0. Get out");
	}
	
	public void options() {
		boolean back = false;
		while (!back) {
			System.out.println(comp.showTime());
			menu();
			System.out.println();
			try {
				int option = sc.nextInt();
				sc.nextLine();
				switch (option) {
					case 2:
						registerTurn();
					break;
					case 1:
						addUser();
					break;
					case 3:
						attendTurns();
					break;
					case 4:
						createNewTurnType();
					break;
					case 5:
						reportTypes();
					break;
					case 6:
						aleatoryUsers();
					break;
					case 7:
						actualizeTime();
					break;
					case 8:
						comp.showTime();
					break;
					case 9:
						load();
					break;
					case 10:
						save();
					break;
					case 0:
    					System.out.flush(); 
    					for (int i=0; i < 30; i++) {
    					System.out.println("");
    					}
						System.out.println("Thanks for using the turn system");  
						back = true;
					default:
						throw new InvalidSelectionException(option);
				}
			} catch (InputMismatchException e) {
            	System.out.println("You have to write a number smaller than 2*10^9 ");
            	sc.nextLine();
			} catch (InvalidSelectionException e) {
				System.out.println("Only numbers between 1 and 3 or 9 to get out");
			} finally {
				
				comp.actualizeTime();
			}
		}
	}

	

	private void load() {
		// TODO Auto-generated method stub
		
	}

	private void save() {
		try {
			long initialTime = System.currentTimeMillis();
			comp.saveRooms();
			long finalTime = System.currentTimeMillis();
			long time = finalTime - initialTime;
			System.out.println("This action takes "+time+" milliseconds");
			System.out.println();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	private void actualizeTime() {
		System.out.println("How do you want to actualizate it");
		System.out.println("1. manuality");
		System.out.println("2. using the local hour");
		int option = sc.nextInt();
		if (option == 1) {
			manual();
		} else {
			comp.actualizeTime();
		}
		
	}

	private void manual() {
		
		System.out.println("If you want the same year, month, day or hour that the system have, pls only digit number 0 in the correspondient space");
		System.out.print("Year: ");
		int year = sc.nextInt();sc.nextLine();
		System.out.println("From 1 to 12");
		System.out.print("Month: ");
		int month = sc.nextInt();sc.nextLine();
		System.out.print("Day: ");
		int day = sc.nextInt();sc.nextLine();
		System.out.println("From 0 to 24");
		System.out.print("Hour: ");
		int hour = sc.nextInt();sc.nextLine();
		System.out.print("Minute: ");
		int minute = sc.nextInt();sc.nextLine();
		System.out.print("Second: ");
		int second = sc.nextInt();sc.nextLine();
		long initialTime = System.currentTimeMillis();
		try {
			comp.passTime(year, month, day, hour, minute, second);
		} catch (CantGoToPastException e) {
			e.getMessage();
		} catch (DoingNothingException e) {
			e.getMessage();
		} catch (InvalidFormatException e) {
			e.getMessage();
		}
		long finalTime = System.currentTimeMillis();
		long time = finalTime - initialTime;
		System.out.println("This action takes "+time+" milliseconds");
		System.out.println();
	}


	private void aleatoryUsers() {
		System.out.println("Write de file name");
		String fn = sc.nextLine();
		System.out.println("how is the index that separate the words in the file");
		String index = sc.nextLine();
		System.out.println("How many user do you want to create?");
		int many = sc.nextInt();
		long initialTime = System.currentTimeMillis();
		try {
			comp.aleatoryUsers(many, fn, index);
			long finalTime = System.currentTimeMillis();
			long time = finalTime - initialTime;
			System.out.println("This action takes "+time+" milliseconds");
			System.out.println();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	private void reportTypes() throws InvalidSelectionException, InputMismatchException {
		System.out.println("what type of report do you want to generate?");
		System.out.println("1. With all turn that a user have been taked");
		System.out.println("2. With all user that have the same code");
		int option = sc.nextInt();
		System.out.println("Do you want to see de report in:");
		System.out.println("1. Console");
		System.out.println("2. text archive");
		int type = sc.nextInt();
		if (option == 2) {
			turnUsers(type);
		}else if (option == 1) {
			userTurns(type);
		}else {
			throw new InvalidSelectionException(option);
		}
		
	}

	private void turnUsers(int type) {
		// TODO Auto-generated method stub
		long initialTime = System.currentTimeMillis();
		
		long finalTime = System.currentTimeMillis();
		long time = finalTime - initialTime;
		System.out.println("This action takes "+time+" milliseconds");
		System.out.println();
		// DEBO MOSTRAR TODOS LOS USUARIOS CON CIERTO TURNO
	}

	private void userTurns(int type) {
		// TODO Auto-generated method stub
		long initialTime = System.currentTimeMillis();
		
		long finalTime = System.currentTimeMillis();
		long time = finalTime - initialTime;
		System.out.println("This action takes "+time+" milliseconds");
		System.out.println();
		// DEBO MOSTRAR TODOS LOS TURNOS DE CIERTA PERSONA
	}

	private void createNewTurnType() {
		long initialTime = System.currentTimeMillis();
		System.out.println("Write the name of the turn type");
		String name = sc.nextLine();
		System.out.println("Write the time that will be take to attend this turn. ");
		System.out.println("example, 2,5 will be taked like 2 minutes and 30 seconds");
		double time = sc.nextDouble();
		comp.createNewType(name, time);
		long finalTime = System.currentTimeMillis();
		long times = finalTime - initialTime;
		System.out.println("The turn type have been added");
		System.out.println("This action takes "+times+" milliseconds");
		System.out.println();
	}

	public void attendTurns() {
		System.out.println();
		
	}

	public void addUser() {
		System.out.println("Remember that we only admit CC, TI, RC, PA, CE (in caps)");
		System.out.println("A * means the field is obligatory");
		String dt = "";DocumentType documentType;String documentNumber = "";
		String firstName = "";String lastName = "";String address = "";String phone = "";
		try {
			System.out.print("*Document type: ");dt = sc.nextLine();
			documentType = comp.chooseType(dt);
			System.out.print("*Document number: ");documentNumber = sc.nextLine();
			System.out.print("*First name: ");firstName = sc.nextLine();
			System.out.print("*Last name: ");lastName = sc.nextLine();
			System.out.print("Address: ");address = sc.nextLine();
			System.out.print("Phone number: ");phone = sc.nextLine();
			long initialTime = System.currentTimeMillis();
			comp.addUser(documentType, documentNumber, firstName, lastName, dt);
			comp.setAddress(address, documentNumber, documentType);comp.setphone(phone, documentNumber, documentType);
			System.out.println("The user has beed added to the data base\n");
			long finalTime = System.currentTimeMillis();
			long time = finalTime - initialTime;
			System.out.println("This action takes "+time+" milliseconds");
			System.out.println();
		} catch (AlreadyAddedException e) {
			System.out.println("We can't add the user");
			System.out.println(e.getMessage()); 
		} catch (InvalidDocumentTypeException e) {
			System.out.println("It was no possible to add a user with document type: "+dt);
			System.out.println(e.getMessage());
		} catch (RequiredFieldsException e) {
			System.out.println("We can't add the user");
			System.out.println(e.getMessage());
		} catch (WithOutRegisterException e) {
			System.out.println("It was no possible to find the user");
			System.out.println(e.getMessage());
		}	
	}
	
	public void registerTurn() {
		System.out.println("Type of turn, we only admit already created turns type");
		System.out.println(comp.showTurnTypes());
		String type = sc.nextLine();
		System.out.println("Document Type: ");
		String dt = sc.nextLine();DocumentType documentType;
		System.out.print("Document Number: ");
		String dn = sc.nextLine();
		try {
			long initialTime = System.currentTimeMillis();
			documentType = comp.chooseType(dt);
			System.out.println(comp.alreadyRegist(dn,  documentType, type));
			long finalTime = System.currentTimeMillis();
			long time = finalTime - initialTime;
			System.out.println("This action takes "+time+" milliseconds");
			System.out.println();
		} catch (WithOutRegisterException e) {
			System.out.println("It was no possible to find the user");
			System.out.println(e.getMessage());
		}
	}
	
}

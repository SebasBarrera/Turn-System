package ui;

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
		System.out.println();
		System.out.println("9. Get out");
	}
	
	public void options() {
		boolean back = false;
		while (!back) {
			menu();
			System.out.println(comp.showTime());
			try {
				int option = sc.nextInt();
				sc.nextLine();
				long initialTime = System.currentTimeMillis();
				long finalTime;
				long time;
				switch (option) {
					case 2:
						registerTurn();
						finalTime = System.currentTimeMillis();
						time = initialTime - finalTime;
						System.out.println(time);
					break;
					case 1:
						addUser();
						finalTime = System.currentTimeMillis();
						time = initialTime - finalTime;
						System.out.println(time);
					break;
					case 3:
						attendTurn();
						finalTime = System.currentTimeMillis();
						time = initialTime - finalTime;
						System.out.println(time);
					break;
					case 4:
						createNewTurnType();
						finalTime = System.currentTimeMillis();
						time = initialTime - finalTime;
						System.out.println(time);
					break;
					case 5:
						reportTypes();
						finalTime = System.currentTimeMillis();
						time = initialTime - finalTime;
						System.out.println(time);
					break;
					case 6:
						aleatoryUsers();
						finalTime = System.currentTimeMillis();
						time = initialTime - finalTime;
						System.out.println(time);
					case 9:
    					System.out.flush(); 
    					for (int i=0; i < 30; i++) {
    					System.out.println("");
    					}
						System.out.println("Thanks for using the turn system");  
						back = true;
					break;
					default:
						throw new InvalidSelectionException(option);
				}
			} catch (InputMismatchException e) {
            	System.out.println("You have to write a number smaller than 2*10^9 ");
            	sc.nextLine();
			} catch (InvalidSelectionException e) {
				System.out.println("Only numbers between 1 and 3 or 9 to get out");
			}
		}
	}

	private void aleatoryUsers() {
		System.out.println("How many user do you want to create?");
		int many = sc.nextInt();
		comp.aleatoryUsers(many);
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
		
		
		// DEBO MOSTRAR TODOS LOS USUARIOS CON CIERTO TURNO
	}

	private void userTurns(int type) {
		// TODO Auto-generated method stub
		
		
		// DEBO MOSTRAR TODOS LOS TURNOS DE CIERTA PERSONA
	}

	private void createNewTurnType() {
		// TODO Auto-generated method stub
		
	}

	public void attendTurn() {
		try {
			System.out.println(comp.showNextTurn());
			System.out.println("please choose an option: \n1. You attend the user.\n2. The user isn't present");
			int present = sc.nextInt();sc.nextLine();
			System.out.println(comp.isPresent(present, comp.getPreviusTurnGiven().getTurn()));
		} catch (TurnNotFountException e) {
			System.out.println(e.getMessage());
		} catch (CantPassAttendTurnToGivenTurn e) {
			System.out.println(e.getMessage());
		}
		
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
			comp.addUser(documentType, documentNumber, firstName, lastName, dt);
			comp.setAddress(address, documentNumber, documentType);comp.setphone(phone, documentNumber, documentType);
			System.out.println("The user has beed added to the data base\n");
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
	
	public	 void registerTurn() {
		System.out.println("Type of turn, we only admit already created turns type");
		String type = sc.nextLine();
		System.out.println("Document Type: ");
		String dt = sc.nextLine();DocumentType documentType;
		System.out.print("Document Number: ");
		String dn = sc.nextLine();
		try {
			documentType = comp.chooseType(dt);
			System.out.println(comp.alreadyRegist(dn,  documentType, type));
		} catch (WithOutRegisterException e) {
			System.out.println("It was no possible to find the user");
			System.out.println(e.getMessage());
		}
	}
	
}

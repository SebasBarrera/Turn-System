package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import customExceptions.*;

class CompanyTest {

	private Company comp;
	
	public void setUp1() {
		comp = new Company();
	}
//	
//	public void setUp2() {
//		comp = new Company("D99");
//	}
//	
//	public void setUp3() {
//		comp = new Company("Z99");
//	}
//	
//	public void setUp4() 
//			throws RequiredFieldsException, InvalidDocumentTypeException, AlreadyAddedException {
//		comp = new Company();
//		comp.addUser(DocumentType.CC, "1144105003", "Sebastián", "Barrera", "CC");
//		comp.advanceGivenTurn();
//		comp.getUsers().get(0).setPersonalTurn(comp.getPreviusTurnGiven());
//		comp.getUsers().get(0).setActive(true);
//	}
//	
//	public void setUp5() 
//			throws RequiredFieldsException, InvalidDocumentTypeException, AlreadyAddedException {
//		comp = new Company();
//		comp.addUser(DocumentType.CC, "1144105003", "Sebastián", "Barrera", "CC");
//		comp.addUser(DocumentType.CC, "19493420", "Edilberto", "Barrera", "CC");
//		comp.advanceGivenTurn();
//		comp.getUsers().get(0).setPersonalTurn(comp.getPreviusTurnGiven());
//		comp.getUsers().get(0).setActive(true);
//	}
//	
//	@Test 
//	public void testCompany() {
//		setUp1();
//		assertEquals("A00", comp.getActualTurnAttend().getTurn(), "The actual Turn to attend is created incorrectly");
//		assertEquals("A0/", comp.getPreviusTurnGiven().getTurn(), "The previus Turn given is created incorrectly");
//		assertEquals(true, comp.getUsers().isEmpty());
//		assertEquals(0, comp.getCounterGiven(), "The system didn't give any turn");
//		assertEquals(0, comp.getCounterAttend(), "The system didn't attend any turn");
//		setUp2();
//		assertEquals("D99", comp.getPreviusTurnGiven().getTurn());
//	}
//	
//	@Test
//	public void testSetters() 
//			throws RequiredFieldsException, InvalidDocumentTypeException, AlreadyAddedException, WithOutRegisterException {
//		setUp4();
//		Turn a = new Turn("B99");
//		comp.setActualTurnAttend(a);
//		assertEquals(a, comp.getActualTurnAttend(), "The actual turn attend setter is not working");
//		comp.setAddress("cll 186 #55b - 51", comp.getUsers().get(0).getDocumentNumber(), comp.getUsers().get(0).getDocumentType());
//		assertEquals("cll 186 #55b - 51", comp.getUsers().get(0).getAddress(), "The user address setter is not working");
//		int ca = 938;
//		comp.setCounterAttend(ca);
//		assertEquals(ca, comp.getCounterAttend(), "The counter of actual attend turn setter is not working");
//		int cg = 4328;
//		comp.setCounterAttend(cg);
//		comp.setCounterGiven(cg);
//		assertEquals(cg, comp.getCounterGiven(), "The counter of previus turn given setter is not working");
//		comp.setphone("3214650140", comp.getUsers().get(0).getDocumentNumber(), comp.getUsers().get(0).getDocumentType());
//		assertEquals("3214650140", comp.getUsers().get(0).getPhoneNumber(), "The phone number setter is not working");
//		comp.setPreviusTurnGiven(a);
//		assertEquals(a, comp.getPreviusTurnGiven(), "The previus turn given setter is not working");
//	}
//	
//	@Test
//	public void testAdvanceAttendTurns() 
//			throws CantPassAttendTurnToGivenTurn {
//		setUp1();
//		assertThrows(CantPassAttendTurnToGivenTurn.class, ()->{comp.advanceActualTurn();});
//		comp.advanceGivenTurn();
//		comp.advanceActualTurn();
//		assertEquals(1, comp.getCounterAttend());
//	}
//	
//	@Test
//	public void testAlreadyRegist() 
//			throws RequiredFieldsException, InvalidDocumentTypeException, WithOutRegisterException, TurnNotFountException, CantPassAttendTurnToGivenTurn, AlreadyAddedException {
//		setUp4();
//		comp.alreadyRegist(comp.getUsers().get(0).getDocumentNumber(), comp.getUsers().get(0).getDocumentType());
//		assertEquals("A00", comp.getUsers().get(0).getTurn(), "The turn have not to advance, because the user already have an active turn");	
//		comp.isPresent(1, "A00");
//		comp.alreadyRegist(comp.getUsers().get(0).getDocumentNumber(), comp.getUsers().get(0).getDocumentType());
//		assertEquals("A01", comp.getUsers().get(0).getTurn(), "The turn have to advance, because the user doesn't have an active turn");
//	}
//	
//	@Test
//	public void testAdvanceGivenTurn() 
//			throws CantPassAttendTurnToGivenTurn {
//		setUp1();
//		comp.advanceGivenTurn();
//		assertEquals("A00", comp.getPreviusTurnGiven().getTurn(), "The turn is not start correctly, it start in A00");
//		setUp2();
//		comp.advanceGivenTurn();
//		assertEquals("E00", comp.getPreviusTurnGiven().getTurn(), "The turn is not advanced conrrectly, it supose to be E00");
//		setUp3();
//		comp.advanceGivenTurn();
//		assertEquals("A00", comp.getPreviusTurnGiven().getTurn(), "The turn is not advanced conrrectly, it supose to be A00");
//	}
//	
//	@Test 
//	public void testSearchUser() 
//			throws WithOutRegisterException, RequiredFieldsException, InvalidDocumentTypeException, AlreadyAddedException {
//		setUp1();
//		assertThrows(WithOutRegisterException.class, ()->{comp.searchUser("0", DocumentType.CC);}, "The user shouldn't be found");
//		setUp5();
//		assertEquals(comp.searchUser(comp.getUsers().get(0).getDocumentNumber(), comp.getUsers().get(0).getDocumentType()), comp.getUsers().get(0), "The user have to be find");
//	}
//
//	@Test
//	public void testAddUser() 
//			throws RequiredFieldsException, InvalidDocumentTypeException, AlreadyAddedException {
//		setUp1();
//		String dt = "CC";
//		DocumentType documentType = comp.chooseType(dt);
//		String documentNumber = "1144105003";
//		String firstName = "Sebastian";
//		String previusName = "Barrera";
//		comp.addUser(documentType, documentNumber, firstName, previusName, dt);
//		assertEquals(documentNumber, comp.getUsers().get(0).getDocumentNumber(), "The user was not added");
//		assertThrows(AlreadyAddedException.class, ()->{comp.addUser(documentType, documentNumber, firstName, previusName, dt);}, "The user was added but it shouldn't have been");
//		String dt1 = "CC";
//		DocumentType documentType1 = DocumentType.CC;
//		String documentNumber1 = "19493420";
//		String firstName1 = "Edilberto";
//		String previusName1 = "Barrera";
//		comp.addUser(documentType1, documentNumber1, firstName1, previusName1, dt1);
//		assertEquals(documentNumber1, comp.getUsers().get(1).getDocumentNumber(), "The user was not created");
//		setUp1();
//		String dt2 = "";
//		DocumentType documentType2 = comp.chooseType(dt2);
//		String documentNumber2 = "1144105003";
//		String firstName2 = "Sebastian";
//		String previusName2 = "Barrera";
//		assertThrows(RequiredFieldsException.class, ()-> {comp.addUser(documentType2, documentNumber2, firstName2, previusName2, dt2);});
//		String dt3 = "cc";
//		DocumentType documentType3 = comp.chooseType(dt3);
//		String documentNumber3 = "1144105003";
//		String firstName3 = "Sebastian";
//		String previusName3 = "Barrera";
//		assertThrows(InvalidDocumentTypeException.class, ()-> {comp.addUser(documentType3, documentNumber3, firstName3, previusName3, dt3);});
//		String dt4 = "CC";
//		DocumentType documentType4 = comp.chooseType(dt4);
//		String documentNumber4 = "";
//		String firstName4 = "Sebastian";
//		String previusName4 = "Barrera";
//		assertThrows(RequiredFieldsException.class, ()-> {comp.addUser(documentType4, documentNumber4, firstName4, previusName4, dt4);});
//		String dt5 = "CC";
//		DocumentType documentType5 = comp.chooseType(dt5);
//		String documentNumber5 = "1144105003";
//		String firstName5 = "";
//		String previusName5 = "Barrera";
//		assertThrows(RequiredFieldsException.class, ()-> {comp.addUser(documentType5, documentNumber5, firstName5, previusName5, dt5);});
//		String dt6 = "CC";
//		DocumentType documentType6 = comp.chooseType(dt6);
//		String documentNumber6 = "1144105003";
//		String firstName6 = "Sebastian";
//		String previusName6 = "";
//		assertThrows(RequiredFieldsException.class, ()-> {comp.addUser(documentType6, documentNumber6, firstName6, previusName6, dt6);});
//	}
//	
//	@Test
//	public void testIsPresent() 
//			throws RequiredFieldsException, InvalidDocumentTypeException, AlreadyAddedException, WithOutRegisterException, TurnNotFountException, CantPassAttendTurnToGivenTurn {
//		setUp5();
//		comp.alreadyRegist(comp.getUsers().get(0).getDocumentNumber(), comp.getUsers().get(0).getDocumentType());
//		comp.alreadyRegist(comp.getUsers().get(1).getDocumentNumber(), comp.getUsers().get(1).getDocumentType());
//		assertEquals("A00", comp.getActualTurnAttend().getTurn(), "It should be A00, because the first turn have no been attend");
//		comp.isPresent(1, comp.getPreviusTurnGiven().getTurn());
//		comp.alreadyRegist(comp.getUsers().get(0).getDocumentNumber(), comp.getUsers().get(0).getDocumentType());
//		assertEquals("A01", comp.getActualTurnAttend().getTurn(), "The turn is not assignated correctly, it should be A01");
//		comp.isPresent(2, comp.getPreviusTurnGiven().getTurn());
//		assertEquals("A02", comp.getActualTurnAttend().getTurn(), "The turn is not assignated correctly, it should be A02");
//		comp.alreadyRegist(comp.getUsers().get(1).getDocumentNumber(), comp.getUsers().get(1).getDocumentType());
//		comp.isPresent(2, comp.getPreviusTurnGiven().getTurn());
//		assertEquals("A03", comp.getActualTurnAttend().getTurn(), "The turn is not assignated correctly, it should be A03");
//	}
//	
//	@Test
//	public void testIntToString() {
//		setUp1();
//		String nums;
//		nums = comp.intToString(0);
//		assertEquals("00", nums, "The change is not doing well");
//		nums = comp.intToString(7);
//		assertEquals("07", nums, "The change is not doing well");
//		nums = comp.intToString(45);
//		assertEquals("45", nums, "The change is not doing well");
//	}
//	
//	@Test
//	public void testAdvance() {
//		setUp1();
//		String nums;
//		nums = comp.advance("A0/");
//		assertEquals("A00", nums);
//	}
//	
//	@Test
//	public void testShowTurns() {
//		setUp1();
//		assertEquals("\nWelcome to the turn program\nAt the moment we have not give any turns\n", comp.showTurns());
//	}
//
//	
	
	
	
}
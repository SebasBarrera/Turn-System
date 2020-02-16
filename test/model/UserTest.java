package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	private User user;
	
	public void setUp1(){
		DocumentType dt = DocumentType.CC;
		String dn = "1144105003";
		String fn = "Sebastián";
		String ln = "Barrera";
		user = new User(dt, dn, fn, ln);
	}
	
	@Test
	public void testUser() {
		setUp1();
		assertEquals(DocumentType.CC, user.getDocumentType(), "The document type of the user is created incorrectly");
		assertEquals("1144105003", user.getDocumentNumber(), "The document numnber of the user is created incorrectly");
		assertEquals("Sebastián", user.getFirstName(), "The first name of the user is created incorrectly");
		assertEquals("Barrera", user.getlastName(), "The last name of the user is created incorrectly");
		assertEquals(null, user.getAddress(), "The address of the user is created incorrectly");
		assertEquals(null, user.getPhoneNumber(), "The phone number of the user is created incorrectly");
		assertEquals(null, user.getPersonalTurn(), "The user turn of the user is created incorrectly");
		assertEquals(0, user.getCounterTurns(), "The counter of turns of the user is created incorrectly");
		assertEquals(true, user.getTurns().isEmpty(), "The turns of the user is created incorrectly");
		assertEquals(false, user.isActive(), "The activity of the user is created incorrectly");
		assertEquals(true, user.getPresents().isEmpty(), "The presents of the user is created incorrectly");
	}
	
	@Test
	public void testAdvanceTurn() {
		setUp1();
		user.advanceTurn();
		assertEquals(1, user.getCounterTurns());
	}
	
	
}

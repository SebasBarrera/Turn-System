package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TurnTest {
	
	Turn turn;
	
	public void setUp1() {
		turn = new Turn("A00");
	}
	

	@Test
	void testTurn() {
		setUp1();
		assertEquals("A00", turn.getTurn());
	}

}

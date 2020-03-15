package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Turn implements Serializable {
	
	private String turn;
	private TurnType type;
	private DateAnHour date;
	
	public Turn(String position, TurnType type, DateAnHour date) {
		turn = position;
		this.type = type;
		this.setDate(date);
	}	

	public String getTurn() {
		return turn;
	}

	public TurnType getType() {
		return type;
	}

	public void setType(TurnType type) {
		this.type = type;
	}

	public DateAnHour getDate() {
		return date;
	}

	public void setDate(DateAnHour date) {
		this.date = date;
	}
	
	
 
}

package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class DateAnHour implements Serializable{
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	/**
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public DateAnHour(int year, int month, int day, int hour, int minute, int second) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}
	/**
	 * @param minute the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}
	/**
	 * @return the second
	 */
	public int getSecond() {
		return second;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(int second) {
		this.second = second;
	}
	@Override
	public String toString() {
		return year+"/"+month+"/"+day+"    "+hour+":"+minute+":"+second;
	}
	
	public long compareDatesInMillis(DateAnHour arg0) {
		GregorianCalendar real = new GregorianCalendar();
		GregorianCalendar plus = new GregorianCalendar();
		real.set(year, month, day, hour, minute, second);
		plus.set(arg0.getYear(), arg0.getMonth(), arg0.getDay(), arg0.getHour(), arg0.getMinute(), arg0.getSecond());
		long currentMillis = plus.getTimeInMillis() - real.getTimeInMillis();
		return currentMillis;	
	}
	
	public DateAnHour millisToDate(long time) {
		DateAnHour date;
		GregorianCalendar dt = new GregorianCalendar();
		dt.set(year, month, day, hour, minute, second);
		dt.roll(Calendar.MILLISECOND, (int)time);
		date = new DateAnHour(dt.get(Calendar.YEAR), dt.get(Calendar.MONTH), dt.get(Calendar.DAY_OF_MONTH), 
				dt.get(Calendar.HOUR_OF_DAY), dt.get(Calendar.MINUTE), dt.get(Calendar.SECOND));
		return date;
	}

	public DateAnHour doubleToDate(double time) {
		DateAnHour date;
		GregorianCalendar dt = new GregorianCalendar();
		dt.set(year, month, day, hour, minute, second);
		time = time*60;
		int tm =  (int)time;
		dt.roll(Calendar.MILLISECOND, tm);
		date = new DateAnHour(dt.get(Calendar.YEAR), dt.get(Calendar.MONTH), dt.get(Calendar.DAY_OF_MONTH), 
				dt.get(Calendar.HOUR_OF_DAY), dt.get(Calendar.MINUTE), dt.get(Calendar.SECOND));
		return date;
	}
	
	public long millis(DateAnHour arg0) {
		long millis = 0;
		 GregorianCalendar actual = new GregorianCalendar();
		 actual.set(year, month, day, hour, minute, second);
		 
		return millis;
	}
	

	
}

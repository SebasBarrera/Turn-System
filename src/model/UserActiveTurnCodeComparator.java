package model;

import java.util.Comparator;

public class UserActiveTurnCodeComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		int x = 0;
		if (o1.isActive() && o2.isActive()) {
			x = (int) o1.getPersonalTurn().getDate().compareDatesInMillis(o2.getPersonalTurn().getDate());
		}else {
			if (o1.isActive() && !o2.isActive()) {
				x = -1;
			} else {
				x = 1;
			}
		}
		return x;
	}

}

package ZTestPackage;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalenderTest {

	public static void main(String[] args) {

		GregorianCalendar dates = new GregorianCalendar();
		int day, month, year;

		day = dates.get(Calendar.DAY_OF_MONTH);
		month = dates.get(Calendar.MONTH) + 1;
		year = dates.get(Calendar.YEAR);

		System.out.println(day + "_" + month + "_" + year);

		GregorianCalendar date = new GregorianCalendar();
		day = date.get(Calendar.DAY_OF_MONTH);
		month = date.get(Calendar.MONTH) + 1;
		year = date.get(Calendar.YEAR);
		String today = year + "_" + month + "_" + day;
		System.out.println(today);
	}
}

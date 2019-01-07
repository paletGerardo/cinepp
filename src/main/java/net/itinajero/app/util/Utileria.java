package net.itinajero.app.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Utileria {

	public static List<String> getNextDays(int count)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
		System.out.println(sdf);

		Date start = new Date();
		System.out.println(start);

		Calendar cal = Calendar.getInstance();
		System.out.println(cal);
		cal.add(Calendar.DAY_OF_MONTH, count);
		Date endDate = cal.getTime();

		GregorianCalendar gcal = new GregorianCalendar();
		System.out.println(gcal);
		gcal.setTime(start);
		List<String> nextDays = new ArrayList<String>();
		System.out.println(nextDays);
		while(!gcal.getTime().after(endDate))
		{
			Date d = gcal.getTime();
			gcal.add(Calendar.DATE, 1);
			nextDays.add(sdf.format(d));
		}
		System.out.println(nextDays);
		return nextDays;

	}
}

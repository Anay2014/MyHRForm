package Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeHelper {
public static String getCurrentDateTime() {
	 DateFormat dateFormat = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");
	 Calendar calander = Calendar.getInstance();
	 String time =""+dateFormat.format(calander.getTime());
	 return time;
}

public static String getCurrentDate() {
	
	return getCurrentDateTime().substring(0, 11);
}

}

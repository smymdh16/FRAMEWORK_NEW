package genericUtility;

import java.util.Date;
import java.util.Random;
/**
 * This class provides method to generate random numbers and system date and time
 */
public class JavaUtility {

	/**
	 * This method is used to generate random numbers
	 * @return
	 */
	public int  toGetRandomNo()
	{ Random r=new Random();
	  int value=r.nextInt(1000);
	  return value;
		
	}
	
	/**
	 * This methods is used to provide get system date and time in format
	 */
	public String toGetSystemDateAndTime()
	{
		Date d=new Date();
		String data[]=d.toString().split(" ");
		String day=data[0];
		String month=data[1];
		String date=data[2];
		String time=data[3].replace(":","-");
		String year=data[5];
		String finalDate=day+" "+month+" "+date+" "+time+" "+year;
		return finalDate;
	}
}

package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consists of methods related to Property File
 */

public class PropertyFileUtility {

	/**
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	
	public String toReadDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream pfis=new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop=new Properties();
		prop.load(pfis);
		String value = prop.getProperty(key);
		return value;
		
	}
}

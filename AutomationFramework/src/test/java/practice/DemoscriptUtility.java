package practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;

public class DemoscriptUtility {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// To read data from property file
		PropertyFileUtility putil = new PropertyFileUtility();
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		// To read data from excel
		ExcelFileUtility eutil = new ExcelFileUtility();
		String lastname = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
		//Step-1:Launch Browser
		WebDriver driver=null;
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
			
		}
		else if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

	}

}

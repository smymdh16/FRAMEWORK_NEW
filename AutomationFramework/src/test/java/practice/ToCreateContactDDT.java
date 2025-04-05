package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateContactDDT {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//To read common  data from Properties file
		FileInputStream pfis=new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop=new Properties();
		prop.load(pfis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD=prop.getProperty("password");
		
		
		//TO read test data from excel file
		FileInputStream efis=new FileInputStream(".\\src\\test\\resources\\TestDataAd.xlsx");
		Workbook wb=WorkbookFactory.create(efis);
		String LASTNAME = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
		
		//Step-1:Launch Browser
		WebDriver driver=null;
		if(BROWSER.contains("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.contains("edge"))
		{
			driver=new EdgeDriver();
		
		}
		else if(BROWSER.contains("firefox"))
		{
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step-2:-To create contacts
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Step-3:-Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname=driver.findElement(By.className("dvHeaderText")).getText();
		if(lastname.contains(LASTNAME))
		{
			System.out.println(lastname+"contact info verified-----------passed ");
			
		}
		else 
		{
			System.out.println(lastname+"contact info -----------------failed");
		}

		//Step-7:Navigate to signout
		WebElement signOut=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(signOut).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step-8:Close browser
		driver.quit();
		
		
		
		
		
		
		
	}

}

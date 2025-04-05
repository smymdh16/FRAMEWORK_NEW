package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateOrganizationDDT {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//To read Common data from properties file
		FileInputStream pfis=new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop=new Properties();
		prop.load(pfis);
		String BROWSER=prop.getProperty("browser");
		String URL=prop.getProperty("url");
		String USERNAME=prop.getProperty("username");
		String PASSWORD=prop.getProperty("password");
		
		//to read Test Data from excel file
		FileInputStream efis=new FileInputStream(".\\src\\test\\resources\\TestDataAd.xlsx");
		Workbook wb=WorkbookFactory.create(efis);
		String Org=wb.getSheet("Organization").getRow(1).getCell(2).toString();
		Random r=new Random();
		int rand=r.nextInt(1000);
		
		
		//Step-1:To launch browser
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
		
		//Step-2:To create organization
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(Org+rand);
		//Step-3:-Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String orgname=driver.findElement(By.className("dvHeaderText")).getText();
		if(orgname.contains(Org))
		{
			System.out.println(orgname+"contact info verified-----------passed ");
			
		}
		else 
		{
			System.out.println(orgname+"contact info -----------------failed");
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

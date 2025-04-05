package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoscriptWithDDTandGU {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
PropertyFileUtility putil=new PropertyFileUtility();
ExcelFileUtility eutil=new ExcelFileUtility();
WebDriverUtility wutil= new WebDriverUtility();

//Read data from propertyFile
String URL=putil.toReadDataFromPropertyFile("url");
String BROWSER=putil.toReadDataFromPropertyFile("browser");
String USERNAME=putil.toReadDataFromPropertyFile("username");
String PASSWORD=putil.toReadDataFromPropertyFile("password");

//read data from Excel file
String LASTNAME=eutil.toReadDataFromExcelFile("Contacts", 1, 2);

//Step-1:-Launch Browser
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

driver.get(URL);
wutil.toMaximize(driver);
wutil.toWait(driver);

//Step-2:Login with valid credentials
driver.findElement(By.name("user_name")).sendKeys(USERNAME);
driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
driver.findElement(By.id("submitButton")).click();

//Step-3:Create  a contact
driver.findElement(By.linkText("Contacts")).click();
driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

//Step-4:Save and verify
driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
String name=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
if(name.contains(LASTNAME))
{
	System.out.println(name+"---Passed");
}
else
{
	System.out.println(name+"----Failed");
}

//Step-5:click on Logout 
WebElement signOut=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
wutil.toMouseHover(driver, signOut);
driver.findElement(By.linkText("Sign Out")).click();

//Step-8:Close browser
driver.quit();

	}

}

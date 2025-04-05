package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class DemoscriptWithDDLandPOM {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
  PropertyFileUtility putil=new PropertyFileUtility();
  ExcelFileUtility eutil=new ExcelFileUtility();
  WebDriverUtility wutil=new WebDriverUtility();
  
  //Read data from property file
  String URL=putil.toReadDataFromPropertyFile("url");
  String BROWSER=putil.toReadDataFromPropertyFile("browser");
  String USERNAME=putil.toReadDataFromPropertyFile("username");
  String PASSWORD=putil.toReadDataFromPropertyFile("password");
  
  //To read data from excel file
  String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
  
  //Step-1: Launch the browser
  WebDriver driver=null;
  if(BROWSER.equals("chrome"))
  {
	  driver=new ChromeDriver();
	  
  }
  else if(BROWSER.equals("firefox"))
  {
	  driver=new FirefoxDriver();
	  
  }
  else if(BROWSER.equals("edge"))
  {
	  driver=new EdgeDriver();
  }
  wutil.toMaximize(driver);
  wutil.toWait(driver);
  
//Step 2:Login to application with valid credentials
  driver.get(URL);
  LoginPage lp=new LoginPage(driver);
  lp.getUsernameTextfield().sendKeys(USERNAME);
  lp.getPasswordTextfield().sendKeys(PASSWORD);
  lp.getLoginButton().click();
  
  //Step 3:Click on contacts link
  HomePage hp=new HomePage(driver);
  hp.getContactLink().click();
  
  
  //Step 4:Click on create contact lookup image
  ContactsPage cp=new ContactsPage(driver);
  cp.getCreateContactlink().click();
  
  //Step 5:Fill contact info
  CreateContactPage ccp=new CreateContactPage(driver);
  ccp.getLastnameTextfield().sendKeys(LASTNAME);
  
  
  //Step 6:Save and Verify
  ccp.getSaveButton().click();
  ContactsInfoPage cip=new ContactsInfoPage(driver);
  String name=cip.getContactInfo().getText();
  
  if(name.contains(LASTNAME))
  {
	  System.out.println(name + "-------Passed--------------");
  }
  else
  {
	  System.out.println(name+"----------Failed--------------");
  }
  
  //Step 7:Logout 
  wutil.toMouseHover(driver, hp.getAdminLink());
  hp.getSignoutLink().click();
  
  //Step 8:Close browser
  driver.quit();
  
  }

}

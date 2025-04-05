package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass {
PropertyFileUtility putil=new PropertyFileUtility();
public WebDriverUtility wutil=new WebDriverUtility();
public WebDriver driver=null;
public static WebDriver sDriver;//Listeners

@BeforeSuite(groups={"smoke","Regression"})
public void beforSuiteConfiugration()
{
	Reporter.log("----------------Database connection established---------",true);
}

//@Parameters("browser")//Cross browser Testing
//@BeforeTest //Cross browser testing
@BeforeClass(groups={"smoke","Regression"})
public void beforeClassConfiguration(/*String BROWSER*/) throws IOException
{
	String BROWSER=putil.toReadDataFromPropertyFile("browser");
	String URL=putil.toReadDataFromPropertyFile("url");
	if(BROWSER.contains("chrome"))
	{
		driver=new ChromeDriver();
	}else if(BROWSER.contains("edge"))
	{
		driver=new EdgeDriver();
	}
	else if(BROWSER.contains("firefox"))
	{
		driver=new FirefoxDriver();
	}
	sDriver=driver;//Listeners
	Reporter.log("-----------------Browser got launched---------------------------",true);
	wutil.toMaximize(driver);
	wutil.toWait(driver);
	driver.get(URL);
	
}

@BeforeMethod(groups={"smoke","Regression"})
public void beforeMethodConfiguration() throws IOException
{
	String USERNAME=putil.toReadDataFromPropertyFile("username");
	String PASSWORD=putil.toReadDataFromPropertyFile("password");
	LoginPage lp=new LoginPage(driver);
	lp.getUsernameTextfield().sendKeys(USERNAME);
	lp.getPasswordTextfield().sendKeys(PASSWORD);
	lp.getLoginButton().click();
	Reporter.log("-----------------Logged in successfully-------------------------",true);
}

@AfterMethod(groups={"smoke","Regression"})
public void afterMethodConfiguration()
{
	HomePage hp=new HomePage(driver);
	wutil.toMouseHover(driver,hp.getAdminLink());
	hp.getContactLink().click();
	Reporter.log("------------------Logged out successfully-----------------------",true);
			
}

@AfterClass(groups={"smoke","Regression"})
public void afterClassConfiguration()
{
	Reporter.log("------------------Browser got closed successfully---------------",true);
	driver.quit();
}

@AfterSuite(groups={"smoke","Regression"})
public void afterSuiteConfiguration()
{
	Reporter.log("------------------Database connection disabled------------------",true);
}


}
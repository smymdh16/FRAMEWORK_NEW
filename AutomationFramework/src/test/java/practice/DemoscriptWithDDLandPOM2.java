package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganizationPage;

public class DemoscriptWithDDLandPOM2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PropertyFileUtility putil=new PropertyFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		//To read data from property file
		String URL=putil.toReadDataFromPropertyFile("url");
		String BROWSER=putil.toReadDataFromPropertyFile("browser");
		String USERNAME=putil.toReadDataFromPropertyFile("username");
		String PASSWORD=putil.toReadDataFromPropertyFile("password");
		
		//To read data from excel file
		String ORGNAME=eutil.toReadDataFromExcelFile("Organization", 1, 2);
		
//		String ORGNAME=NAME+rand;
		
		WebDriver driver=null;
		if(BROWSER.equals("chrome"))
		{ driver=new ChromeDriver();
			
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
		
		//Step-1:Login to application with valid credential
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		
		//Step-2:Click on Organization link
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		
		//Step-3:clicl create organization look up image
		OrganizationPage  op=new OrganizationPage(driver);
		op.getCreateOrgLink().click();
		
		//Step-4 :Fill organization details
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		Random r=new Random();
		int rand=r.nextInt(1000);
		cop.getOrgTextfield().sendKeys(ORGNAME+rand);
		
		//Step-5:Save and Verify
		cop.getSaveButton().click();
		OrgInfoPage oip=new OrgInfoPage(driver);
		String orgInfo=oip.getOrgHeader().getText();
		if(orgInfo.contains(ORGNAME+rand))
		{
			System.out.println(orgInfo+"-------------Passed------------");
			
		}
		else
		{
			System.out.println(orgInfo+"---------------Failed------------");
		}
		
		//Step-6:Logout
		wutil.toMouseHover(driver,hp.getAdminLink());
		hp.getSignoutLink().click();
		
		//Step-7:close
		driver.close();
	}

}


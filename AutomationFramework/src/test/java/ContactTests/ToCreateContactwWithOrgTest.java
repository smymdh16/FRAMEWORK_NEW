package ContactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateContactPage;
import objectRepository.HomePage;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactwWithOrgTest extends BaseClass {
	@Test(groups="smoke")
	public void toCreateContactWithOrg_005() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactlink().click();
		CreateContactPage ccp = new CreateContactPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 4, 2);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getOrgSelect().click();
		wutil.toSwitchWindowID(driver, "Accounts");
		driver.findElement(By.xpath("//a[text()='TCS544']")).click();
		wutil.toSwitchWindowID(driver,"Contacts");
		ccp.getSaveButton().click();
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String name=cip.getContactInfo().getText();
		/*
		 * if(name.contains(LASTNAME)) { Reporter.log(name+" added successfully ",true);
		 * } else { Reporter.log(name+" not added ",true); }
		 */
		
		Assert.assertTrue(name.contains(LASTNAME));
		Reporter.log(name+"ADDED SUCCESSFULLY");
		

	}

}

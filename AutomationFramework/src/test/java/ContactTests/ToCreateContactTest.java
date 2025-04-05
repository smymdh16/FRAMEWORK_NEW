package ContactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
public class ToCreateContactTest extends BaseClass {

	@Test(groups = "smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactlink().click();
		CreateContactPage ccp = new CreateContactPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String name = cip.getContactInfo().getText();
		/*
		 * if(name.contains(LASTNAME)) {
		 * System.out.println(name+"-------passed successfully--------------"); } else {
		 * System.out.println(name+"----------Failed-------------------------"); }
		 */
		Assert.fail();
		Assert.assertTrue(name.contains(LASTNAME));
		Reporter.log(name + "Contact created successfully");

	}
}

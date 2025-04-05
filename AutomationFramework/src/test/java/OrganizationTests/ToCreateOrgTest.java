package OrganizationTests;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganizationPage;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateOrgTest extends BaseClass{

	@Test(groups="Regression")
	public void toCreateOrg_002() throws EncryptedDocumentException, IOException
	{
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateOrgLink().click();
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		ExcelFileUtility eutil=new ExcelFileUtility();
		String org=eutil.toReadDataFromExcelFile("Organization", 1, 2);
		Random rand=new Random();
		int r=rand.nextInt(1000);
		cop.getOrgTextfield().sendKeys(org+r);
		cop.getSaveButton().click();
		OrgInfoPage oip=new OrgInfoPage(driver);
		String name=oip.getOrgHeader().getText();
		
		/*
		 * if(name.contains(org+r)) {
		 * System.out.println(name+"-------------PASSED---------"); } else {
		 * System.out.println(name+"-----------FAILED------------"); }
		 */
		Assert.assertTrue(name.contains(org+r));
	Reporter.log(name+"Successfully passed");
	}
	
}

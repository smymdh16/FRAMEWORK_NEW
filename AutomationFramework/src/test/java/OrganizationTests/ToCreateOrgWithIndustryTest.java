package OrganizationTests;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationPage;

@Listeners
public class ToCreateOrgWithIndustryTest extends BaseClass{
@Test(groups="Regression")
	public void tocreateOrgWithIndustry_003() throws EncryptedDocumentException, IOException
	{
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateOrgLink().click();
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		ExcelFileUtility eutil=new ExcelFileUtility();
		String org=eutil.toReadDataFromExcelFile("Organization",4,2);
		String industry=eutil.toReadDataFromExcelFile("Organization",4,3);
		Random rand=new Random();
		int r=rand.nextInt(1000);
		cop.getOrgTextfield().sendKeys(org+r);
		wutil.toHandleDropdown(industry,cop.getIndustryDrp());
		
		
	}
}

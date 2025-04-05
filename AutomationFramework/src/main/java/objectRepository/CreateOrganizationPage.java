package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {

	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@name='accountname']")
	private WebElement orgTextfield;
	
	@FindBy(name="industry")
	private WebElement industryDrp;
	
	@FindBy(name="accounttype")
	private WebElement industryType;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public WebElement getOrgTextfield() {
		return orgTextfield;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getIndustryDrp() {
		return industryDrp;
	}

	public WebElement getIndustryType() {
		return industryType;
	}
	
	
	
	
}

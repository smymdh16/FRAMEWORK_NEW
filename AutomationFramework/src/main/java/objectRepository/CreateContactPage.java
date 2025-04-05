package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {

	public CreateContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnameTextfield;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	@FindBy(xpath="//input[@name='account_name']/..//img[@src='themes/softed/images/select.gif']")
	private WebElement orgSelect;
	
	
	public WebElement getLastnameTextfield() {
		return lastnameTextfield;
	}

	
	
	public WebElement getSaveButton() {
		return saveButton;
	}



	public WebElement getOrgSelect() {
		return orgSelect;
	}
	
	
	
}

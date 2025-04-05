package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminLink;
	
	@FindBy(linkText="Sign Out")
	private WebElement signoutLink;
	
	@FindBy(linkText="Organizations")
	private WebElement organizationLink;

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getAdminLink() {
		return adminLink;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}
	
	
	
}

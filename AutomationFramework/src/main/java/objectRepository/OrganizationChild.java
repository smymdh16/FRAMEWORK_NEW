package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationChild {
public OrganizationChild(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}
@FindBy(xpath="//td[@class='moduleName']")
private WebElement header;

@FindBy(xpath="//a[@id='2']")
private WebElement orgnameSelect;

public WebElement getHeader() {
	return header;
}

public WebElement getOrgnameSelect() {
	return orgnameSelect;
}



}

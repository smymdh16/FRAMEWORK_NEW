package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * This class consists of methods to handle dropdown,methods for mousehover ,takescrrenshot etc
 */
public class WebDriverUtility {
	/**
	 * This method is used to maximize the browser
	 * 
	 * @param driver
	 */
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to minimize the browser
	 * 
	 * @param driver
	 */
	public void toMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * This method is used to waits till the element is loaded in webpage
	 * 
	 * @param driver
	 */
	public void toWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	/**
	 * This method will wait until element is clickable provided driver and element
	 * 
	 * @param driver
	 * @param ele
	 */

	public void toWaitUntilElementClickable(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(ele));

	}

	/**
	 * This method will wait until element is visible provided driver and element
	 * 
	 * @param driver
	 * @param ele
	 */

	public void toWaitUntilTheElementIsVisible(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	/**
	 * This method is used to handle dropdown by using selectByIndex()
	 * 
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);

	}

	/**
	 * This method is used to handle dropdown by using selectByValue()
	 * 
	 * @param element
	 * @param value
	 */
	public void toHandleDropdown(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}

	/**
	 * This method is used to handle dropdown by using selectByVisibleText()
	 * 
	 * @param text
	 * @param element
	 */
	public void toHandleDropdown(String text, WebElement element) {
		Select s = new Select(element);
		s.selectByVisibleText(text);

	}

	/**
	 * This method is used to perform mousehover over the element provided driver
	 * and element
	 * 
	 * @param driver
	 * @param ele
	 */
	public void toMouseHover(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();

	}

	/**
	 * This method is used to perform right click over the ele
	 * 
	 * @param driver
	 * @param ele
	 */
	public void toRightClick(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.contextClick(ele).perform();

	}

	/**
	 * THis method is used to perform double click provided driver and element
	 * 
	 * @param driver
	 * @param ele
	 */
	public void toDoubleClick(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.doubleClick(ele).perform();

	}

	/**
	 * This method is used to perform draganddrop provided driver and element
	 * 
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void ToDragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}

	/**
	 * This method is used to handle frame using index
	 * 
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to handle frame using id/name
	 * 
	 * @param driver
	 * @param id_name
	 */
	public void toHandleFrame(WebDriver driver, String id_name) {
		driver.switchTo().frame(id_name);

	}

	/**
	 * This method is used to handle frame using Webelement
	 * 
	 * @param driver
	 * @param ele
	 */
	public void toHandleFrame(WebDriver driver, WebElement ele) {
		driver.switchTo().frame(ele);

	}

	/**
	 * This method is used to switch back the driver control from frame to main
	 * frame
	 * 
	 * @param driver
	 */

	public void toSwitchBack(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to switch to alert popup and accept
	 * 
	 * @param driver
	 */
	public void toSwitchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used to switch to alert popup and dismiss
	 * 
	 * @param driver
	 */
	public void toSwitchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * 
	 * This method is used to switch to alert and capture the text from alert popup
	 * 
	 * @param driver
	 * @return
	 */
	public String toSwitchToALertAndCaptureText(WebDriver driver) {
		Alert popup = driver.switchTo().alert();
		String message = popup.getText();
		popup.accept();
		return message;
	}

	/**
	 * This method is used to take screenshot provide driver and screenshotname
	 * 
	 * @param driver
	 * @param screenshotname
	 * @throws IOException
	 */
	public String toTakeScreenshot(WebDriver driver, String screenshotname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./errorShots/" + screenshotname + ".png");
		FileHandler.copy(temp, dest);
		String path=dest.getAbsolutePath();
		return path;
	}

	/**
	 * This method is used to switch the driver control to other windows
	 * 
	 * @param driver
	 * @param partialTitle
	 */
	public void toSwitchWindowID(WebDriver driver, String partialTitle) {
		Set<String> windowId = driver.getWindowHandles();
		for (String id : windowId) {
			String title = driver.switchTo().window(id).getTitle();
			if (title.contains(partialTitle)) {
				break;

			}
		}
	}

}

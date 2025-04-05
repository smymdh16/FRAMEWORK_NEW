package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ToSelectIndustryChemical {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// Step -1:Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step-2:Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// Step-3:Navigate to organizations link
		driver.findElement(By.linkText("Organizations")).click();

		// Click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step-5:Create organization with mandatory fields
		Random r = new Random();
		int rand = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys("Larsen toubra" + rand);

		WebElement dropdown = driver.findElement(By.name("industry"));
		Select s = new Select(dropdown);
		s.selectByValue("Chemicals");

		// Step-6:Save and Verify

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String organization = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (organization.contains("Larsen toubra" + rand)) {
			System.out.println("organization info verified-----------passed ");
		} else {
			System.out.println("organization info -----------------failed");
		}

		// Step-7:Navigate to signout
		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(signOut).perform();

		driver.findElement(By.linkText("Sign Out")).click();

		// Step-8:Close browser
		driver.quit();

	}

}

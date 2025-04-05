package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateContact {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Step-1: Launch Browser
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		//Step-2:Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Step-3:Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		
		//Step-4:Click on create contact look up page
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		//Step-5:Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Shreya M");
		
		//Step-6:Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(lastname.contains("Shreya M"))
		{
			System.out.println("Shreya M contact info verified-----------passed ");
		}
		else
		{
			System.out.println("Shreya M contact info -----------------failed");  
		}
		
		//Step-7:Navigate to signout
		WebElement signOut=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(signOut).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step-8:Close browser
		driver.quit();
		}

}

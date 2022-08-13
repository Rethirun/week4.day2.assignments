package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class CustomerServiceOptions {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();//Verify the version,download and set the chrome driver

		ChromeDriver driver=new ChromeDriver();//Launch the chrome browser

		driver.get("https://login.salesforce.com/");//load the URL

		driver.manage().window().maximize();//maximize the browser

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		// Enter the username as "ramkumar.ramaiah@testleaf.com -- id username
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");

		//Enter the passsword as Password#123--id password
		driver.findElement(By.id("password")).sendKeys("Password#123");

		//Click the login -- id Login
		driver.findElement(By.id("Login")).click();

		//Click on learn more option in the Mobile publisher.-- //span[text()='Learn More']
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		//Switch to the next window using windowHandles
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindows=new ArrayList<String>(windowHandles);
		String firstWindowHandle = lstWindows.get(0);
		String secondWindowHandle = lstWindows.get(1);

		//click on the confirm button in the redirecting page
		//button[text()='Confirm']
		driver.switchTo().window(secondWindowHandle);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//Click on Products
		Shadow dom=new Shadow(driver);
		Thread.sleep(2000);
		WebElement prodEle = dom.findElementByXPath("//span[text()='Products']");
		prodEle.click();
		
		//Mouse Hover on Service
		Actions builder=new Actions(driver);
		WebElement serEle = dom.findElementByXPath("//span[text()='Service']");
		builder.moveToElement(serEle).perform();
		
		//Click on Customer Service
		WebElement cusServEle = dom.findElementByXPath("//a[text()='Customer Service']");
		cusServEle.click();
		
		//Get the list of Customer Services
		List<WebElement> lstCusServ = driver.findElements(By.xpath("//ul[@class='page-list page-list-level-2']//a"));
		int size = lstCusServ.size();
		for(int i=0;i<size;i++) {
			System.out.println(lstCusServ.get(i).getText());
		}
		
		//Verify the title
		String title = driver.getTitle();
		System.out.println(title);

		
		
	}

}

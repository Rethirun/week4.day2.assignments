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

public class AdminCertifications {

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
		
		//Click Resources
		driver.findElement(By.linkText("Resources")).click();
		
		//Switch to new Window
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> lstWindowHandles2=new ArrayList<String>(windowHandles2);
		String thirdWindow = lstWindowHandles2.get(1);
		
		driver.switchTo().window(thirdWindow);
	
		//Click on Learning Shadow DOM object Learning
		Shadow dom=new Shadow(driver);
		Thread.sleep(2000);
		WebElement learnEle = dom.findElementByXPath("//span[text()='Learning']");
		learnEle.click();
		
		//Move to Actions for Mouser hover on Learning Trailhead
		Actions builder=new Actions(driver);
		WebElement learnTrailEle = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		builder.moveToElement(learnTrailEle).perform();
		
		//Click on Salesforce Certifications
		WebElement salesforceCert = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		builder.scrollToElement(salesforceCert).perform();
		salesforceCert.click();
		
		//Navigate to Certification - Administrator Overview Window
		Set<String> windowHandles3 = driver.getWindowHandles();
		List<String> lstWindows3=new ArrayList<String>(windowHandles3);
		String fourthWindow = lstWindows3.get(1);
		driver.switchTo().window(fourthWindow);
		
		//Verify the certifications available for Administrator Credentials
		String certText = driver.findElement(By.xpath("//h1[text()='Administrator Credentials']/following::div[@class='credentials-card_type'][1]")).getText();
		String AdminText = driver.findElement(By.xpath("//h1[text()='Administrator Credentials']/following::div[@class='credentials-card_title'][1]/a")).getText();
		
		if(certText.equals("Certification")&&AdminText.equalsIgnoreCase("Administrator")) {
			System.out.println("Yes Administrator Certifications are Available!!");
		}else {
			System.out.println("Error faced!!");
		}
		
		
		
				
		

	}

}

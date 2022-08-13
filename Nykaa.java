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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();//Verify the version,download and set the chrome driver

		ChromeDriver driver=new ChromeDriver();//Launch the chrome browser

		driver.get("https://www.nykaa.com/");//load the URL

		driver.manage().window().maximize();//maximize the browser

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		//2) Mouseover on Brands and Search L'Oreal Paris
		//brandSearchBox--id
		
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(brands).perform();
		
		WebElement searchbox=driver.findElement(By.id("brandSearchBox"));
		searchbox.sendKeys("L'oreal Paris");
		
		//3) Click L'Oreal Paris
		//(//a[contains(text(),'Paris')])[1]
		driver.findElement(By.xpath("(//a[contains(text(),'Paris')])[1]")).click();
		
		//4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title=driver.getTitle();
		if(title.contains("L'Oreal")) {
			System.out.println("Yes!! the title contains L'Oreal Paris");
		}
		
		//5) Click sort By and select customer top rated
		//span[@class='sort-name']
		//span[text()='customer top rated']
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']/following::div[1]")).click();
		
		//6) Click Category and click Hair->Click haircare->Shampoo
		//span[text()='Category']
		//(//div[@class=' css-b5p5ep'])[2]/span[1]
		//span[text()='Shampoo']/following::div[1]
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']/following::div[1]")).click();
		
		//7) Click->Concern->Color Protection
		//span[text()='Concern']
		//span[text()='Color Protection']/following::div[1]
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']/following::div[1]")).click();
		
		//8)check whether the Filter is applied with Shampoo
		//(//span[@class='filter-value'])[1]
		String filterText=driver.findElement(By.xpath("(//span[@class='filter-value'])[1]")).getText();
		System.out.println("Yes!! the filter is applied with "+filterText);
		
		//9) Click on L'Oreal Paris Colour Protect Shampoo
		//(//div[@class='css-wkluxr'])[1]/following::a[1]
		driver.findElement(By.xpath("(//div[@class='css-wkluxr'])[1]/following::a[1]")).click();

		//10) GO to the new window and select size as 175ml
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindows=new ArrayList<String>(windowHandles);
		String secondWindow = lstWindows.get(1);
		driver.switchTo().window(secondWindow);
		WebElement dropdown = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select select=new Select(dropdown);
		select.selectByIndex(1);
		
		//11) Print the MRP of the product
		String price = driver.findElement(By.xpath("(//span[text()='MRP:'])[1]/following::span[1]")).getText();
		System.out.println("MRP of the product: " +price.replace("?", "Rs."));
		
		//12) Click on ADD to BAG
		driver.findElement(By.xpath("(//span[text()='Add to Bag'])[1]")).click();
		
		//13) Go to Shopping Bag 
		//(//button[@type='button'])[1]
		driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
		
		//14) Print the Grand Total amount
		//span[@class='css-n-12gwxi e171rb9k3']
		driver.switchTo().frame(0);
		String grandTot = driver.findElement(By.xpath("(//div[@class='value'])[4]")).getText();
		System.out.println("Total of the product: "+grandTot.replace("?", ""));
		
		//15) Click Proceed
		//span[text()='Proceed']
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
		//16) Click on Continue as Guest
		//button[@class='btn full big']
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		
		//17) Check if this grand total is the same in step 14
		//(//div[@class='value'])[2]/span
		String totAmt=driver.findElement(By.xpath("(//div[@class='value'])[2]/span")).getText();
		System.out.println("Total Amount "+totAmt);
		if(grandTot.contains(totAmt)) {
			System.out.println("Yes!! Both the amt are same");
		}
		
		//18) Close all windows

		
				
				
		

	}

}

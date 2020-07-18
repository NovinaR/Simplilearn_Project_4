package Searching;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class Search {
	
	WebDriver driver=new ChromeDriver();
	String URL="http://automationpractice.com";
	String expected=null;
	String actual=null;
	
	@BeforeTest
	public void openBrowser() {
		driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
		driver.get(URL);
		driver.manage().window().maximize();
		expected="My Store";
		actual=driver.getTitle();
		AssertJUnit.assertEquals(actual, expected);
	}
	
	@Test
	public void searchTest() {
		driver.findElement(By.id("search_query_top")).sendKeys("dressdress");
		driver.findElement(By.id("searchbox")).submit();
		try {		
			WebDriverWait wait=new WebDriverWait(driver, 14);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"grid\"]/a/i")));
		}
		catch(Exception e) {
			System.out.println("Error "+driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText());
		}
	}
		
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}

}

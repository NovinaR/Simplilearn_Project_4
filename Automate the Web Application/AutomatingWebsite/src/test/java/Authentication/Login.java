package Authentication;

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

public class Login {
	
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
	public void login() {
		driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
		driver.findElement(By.className("login")).click();
		
		driver.findElement(By.id("email")).sendKeys("abccxyzc@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("abcxyz");
		
		driver.findElement(By.id("SubmitLogin")).click();
		try 
		{
			WebDriverWait wait=new WebDriverWait(driver, 14);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]")));
			System.out.println("Login Successfull");
	
			driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")));
			System.out.println("Signed Out");
		}
		catch(Exception e) 
		{
			System.out.println("Error "+driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]")).getText());
		}
	
		}
		
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}

}


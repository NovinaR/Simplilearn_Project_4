package Authentication;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class Registration {
	
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
	public void openSignInPage() {
		driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
		driver.findElement(By.className("login")).click();
		
		expected="Login - My Store";
		actual=driver.getTitle();
		AssertJUnit.assertEquals(actual, expected);
		
	}
	
	@Test
	public void validEmail() {
		driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
		driver.findElement(By.id("email_create")).sendKeys("helloabcxyz@gmail.com");
		driver.findElement(By.id("SubmitCreate")).submit();

		try {
			WebDriverWait wait=new WebDriverWait(driver, 14);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2")));
		}
		catch(Exception e) {
			System.out.println("Error "+driver.findElement(By.id("create_account_error")).getText());
		}

		try 
		{
			driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
			driver.findElement(By.id("id_gender2")).click();
			driver.findElement(By.id("customer_firstname")).sendKeys("Hello First");
			driver.findElement(By.id("customer_lastname")).sendKeys("Hello Last");
			driver.findElement(By.id("passwd")).sendKeys("Password");
			
			Select date=new Select(driver.findElement(By.id("days")));
			date.selectByIndex(2);
			Select month=new Select(driver.findElement(By.id("months")));
			month.selectByIndex(4);
			Select year=new Select(driver.findElement(By.id("years")));
			year.selectByIndex(6);
			
			driver.findElement(By.id("newsletter")).click();
			driver.findElement(By.id("optin")).click();
			
			driver.findElement(By.id("company")).sendKeys("Hello Company");
			driver.findElement(By.id("address1")).sendKeys("Address 1");
			driver.findElement(By.id("address2")).sendKeys("Address 2");
			driver.findElement(By.id("city")).sendKeys("Hello City");
			Select state=new Select(driver.findElement(By.id("id_state")));
			state.selectByIndex(15);
			driver.findElement(By.id("postcode")).sendKeys("12345");
			
			Select country=new Select(driver.findElement(By.id("id_country")));
			country.selectByIndex(1);
			
			driver.findElement(By.id("other")).sendKeys("Hello Other");
			driver.findElement(By.id("phone")).sendKeys("0987654321");
			driver.findElement(By.id("phone_mobile")).sendKeys("9087654321");
			
			try 
			{
				driver.findElement(By.id("submitAccount")).click();
				WebDriverWait wait=new WebDriverWait(driver, 14);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")));
				System.out.println("\nRegistration Successfull!!");
			}
			catch(Exception e) 
			{
				System.out.println(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div")).getText());
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) 
		{
			System.out.println("Website has thrown an Error!! \nPlease solve that Error first to proceed further");
		}
	}
		
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}
	
}

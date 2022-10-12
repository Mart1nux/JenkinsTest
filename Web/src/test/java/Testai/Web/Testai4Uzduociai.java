package Testai.Web;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testai4Uzduociai
{
	public static String mail = null;
	static ChromeDriver driver = null;
	int i = 1;
	
	@BeforeClass
    public static void setUp()
    {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Martinas\\Downloads\\chromedriver_win32\\chromedriver.exe");
    	ChromeDriver driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.get("https://demowebshop.tricentis.com/");
    	
    	driver.findElement(By.xpath("//a[@href='/login']")).click();
    	
    	driver.findElement(By.xpath("//div[@class='new-wrapper register-block']//input[@value='Register']")).click();
    	
    	driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Martinas");
    	
    	driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Mačernius");
    	
    	mail = generateRandomEmail();
    	
    	driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(mail);
    	
    	driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

    	driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
    	
    	driver.findElement(By.xpath("//input[@id='register-button']")).click();
    	
    	driver.quit();
    }
	
	public static String getUniqueId()
    {
        return String.format("%s_%s", UUID.randomUUID().toString().substring(0, 5), System.currentTimeMillis() / 1000);
    }
    
    public static String generateRandomEmail()
    {
        return String.format("%s@%s", getUniqueId(), "mif.stud.vu.com");
    }
    
    @Test
    public void test1()
    {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Martinas\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	    WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
	    int i = 1;
	    
		driver.get("https://demowebshop.tricentis.com/");
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(mail);
		driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		driver.findElement(By.xpath("//div[@class='master-wrapper-main']//a[@href=\"/digital-downloads\"]")).click();
		
		try
		{
		    File input = new File("C:\\Users\\Martinas\\Desktop\\VU\\Testavimas\\data1.txt");
		    Scanner myReader = new Scanner(input);
		    while (myReader.hasNextLine())
		    {
		    	String data = myReader.nextLine();
		        driver.findElement(By.xpath("//a[text()='" + data + "']/following::input[@value='Add to cart']")).click();
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='(" + i + ")']")));
		        i++;		
		    }
		    myReader.close();
		}
		catch (FileNotFoundException e)
		{
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
		driver.findElement(By.xpath("//*[@id='termsofservice']")).click();
		driver.findElement(By.xpath("//*[@id='checkout']")).click();
		driver.findElement(By.xpath("//option[text()='Lithuania']")).click();
		driver.findElement(By.xpath("//*[@id='BillingNewAddress_City']")).sendKeys("Vilnius");
		driver.findElement(By.xpath("//*[@id='BillingNewAddress_Address1']")).sendKeys("Didlaukio g. 47");
		driver.findElement(By.xpath("//*[@id='BillingNewAddress_ZipPostalCode']")).sendKeys("08303");
		driver.findElement(By.xpath("//*[@id='BillingNewAddress_PhoneNumber']")).sendKeys("(8-5) 219 3050");
		
		
		driver.findElement(By.xpath("//input[@class='button-1 new-address-next-step-button']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 payment-method-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 payment-method-next-step-button']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 payment-info-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page checkout-page']//strong[text()='Your order has been successfully processed!']")));
		
		assertEquals("Your order has been successfully processed!", driver.findElement(By.xpath("//div[@class='page checkout-page']//strong[text()='Your order has been successfully processed!']")).getText());
		
	    driver.quit();
    }
    
    @Test
    public void test2()
    {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Martinas\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	    WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
	    int i = 1;
	    
		driver.get("https://demowebshop.tricentis.com/");
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(mail);
		driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		driver.findElement(By.xpath("//div[@class='master-wrapper-main']//a[@href=\"/digital-downloads\"]")).click();
		
		try
		{
		    File input = new File("C:\\Users\\Martinas\\Desktop\\VU\\Testavimas\\data2.txt");
		    Scanner myReader = new Scanner(input);
		    while (myReader.hasNextLine())
		    {
		    	String data = myReader.nextLine();
		        driver.findElement(By.xpath("//a[text()='" + data + "']/following::input[@value='Add to cart']")).click();
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='(" + i + ")']")));
		        i++;
		    }
		    myReader.close();
		}
		catch (FileNotFoundException e)
		{
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
		driver.findElement(By.xpath("//*[@id='termsofservice']")).click();
		driver.findElement(By.xpath("//*[@id='checkout']")).click();
		
		
		driver.findElement(By.xpath("//input[@class='button-1 new-address-next-step-button']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 payment-method-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 payment-method-next-step-button']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 payment-info-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page checkout-page']//strong[text()='Your order has been successfully processed!']")));
		
		assertEquals("Your order has been successfully processed!", driver.findElement(By.xpath("//div[@class='page checkout-page']//strong[text()='Your order has been successfully processed!']")).getText());
		
		driver.quit();
    }
    
    /*@Before
    public void newWebdriverSession()
    {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Martinas\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
    }

	@Test
	public void test1()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Martinas\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
		driver.get("https://demowebshop.tricentis.com/");
		prisijungimas();
		pridetiPagalFaila("D:\\Users\\Martinas\\eclipse-workspace\\Web\\src\\main\\java\\Testai\\Web\\data1.txt");
		pradetiAtsiskaityma();
		adresoRegistravimas();
		nextSpam();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page checkout-page']//strong[text()='Your order has been successfully processed!']")));
		System.out.println("True");
	}
	
	@Test
	public void test2()
	{
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
		driver.get("https://demowebshop.tricentis.com/");
		prisijungimas();
		pridetiPagalFaila("D:\\Users\\Martinas\\eclipse-workspace\\Web\\src\\main\\java\\Testai\\Web\\data2.txt");
		pradetiAtsiskaityma();
		nextSpam();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page checkout-page']//strong[text()='Your order has been successfully processed!']")));
		System.out.println("True");
	}
	
	public void prisijungimas()
	{
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(mail);
		driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
	}
	
	public void pridetiPagalFaila(String failas)
	{
	WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));	
	driver.findElement(By.xpath("//div[@class='master-wrapper-main']//a[@href=\"/digital-downloads\"]")).click();
			try
			{
			    File input = new File("failas");
			    Scanner myReader = new Scanner(input);
			    while (myReader.hasNextLine())
			    {
			    	String data = myReader.nextLine();
			        driver.findElement(By.xpath("//a[text()='" + data + "']/following::input[@value='Add to cart']")).click();
			        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='(" + i + ")']")));
			        i++;
			    }
			    myReader.close();
			}
			catch (FileNotFoundException e)
			{
			    System.out.println("An error occurred.");
			    e.printStackTrace();
			}
	}
	
	public void pradetiAtsiskaityma()
	{
		driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
		driver.findElement(By.xpath("//*[@id='termsofservice']")).click();
		driver.findElement(By.xpath("//*[@id='checkout']")).click();
	}
	
	public void adresoRegistravimas()
	{
		driver.findElement(By.xpath("//option[text()='Lithuania']")).click();
		driver.findElement(By.xpath("//*[@id='BillingNewAddress_City']")).sendKeys("Vilnius");
		driver.findElement(By.xpath("//*[@id='BillingNewAddress_Address1']")).sendKeys("Didlaukio g. 47");
		driver.findElement(By.xpath("//*[@id='BillingNewAddress_ZipPostalCode']")).sendKeys("08303");
		driver.findElement(By.xpath("//*[@id='BillingNewAddress_PhoneNumber']")).sendKeys("(8-5) 219 3050");
	}
	
	public void nextSpam()
	{
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));	
		driver.findElement(By.xpath("//input[@class='button-1 new-address-next-step-button']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 payment-method-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 payment-method-next-step-button']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 payment-info-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")));
		driver.findElement(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")).click();
	}
	
	@After
	public void cleanUp()
	{
		driver.quit();
		i = 1;
	}*/

}

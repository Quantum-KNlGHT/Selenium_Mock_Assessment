package practice_Assessment;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task1 {
	
	WebDriver driver;
	
	@BeforeMethod
	void setup() {
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("browserVersion", "latest");
		capabilities.setCapability("os", "Windows");
		capabilities.setCapability("osVersion", "10");
		
		URL url=null;
		
		try {
			url=new URL("https://shlokachowdhury_FDi13X:z3Qfzpp7JeNj7KAEXxp1@hub-cloud.browserstack.com/wd/hub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver=new RemoteWebDriver(url, capabilities);
		driver.get("http://137.184.76.209/orangehrm-4.9");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	
	@Test
	void test1(){

		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("ASDFwhuhpp3lhdz3k47iw%");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Dashboard']")).getText().equalsIgnoreCase("dashboard"));
		
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='logInPanelHeading']")).getText().equalsIgnoreCase("LOGIN Panel"));
	}
	
	@Test
	void Test2() {
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin1");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("ASDFwhuhpp3lhdz3%");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='spanMessage']")).getText().equalsIgnoreCase("Invalid credentials"));
		
		
	}
	
	@AfterMethod
	void teardown() {
		driver.quit();
	}
	

}

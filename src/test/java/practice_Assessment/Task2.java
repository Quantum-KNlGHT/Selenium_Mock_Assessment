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

public class Task2 {
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
	
	@AfterMethod
	void teardown() {
		driver.quit();
	}

	@Test
	void test() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("ASDFwhuhpp3lhdz3k47iw%");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Dashboard']")).getText().equalsIgnoreCase("dashboard"));
		
		driver.findElement(By.xpath("//b[normalize-space()='PIM']")).click();
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		Assert.assertTrue(driver.getCurrentUrl().contains("addEmployee"));
		
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Shloka");
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Chowdhury");
		//driver.findElement(By.xpath("//input[@id='photofile']")).sendKeys("C:\\Users\\shlchowd\\Downloads\\mvn clean.png");
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='personal_txtEmployeeId']")).isEnabled());
	
		driver.findElement(By.xpath("//b[normalize-space()='PIM']")).click();
		driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).sendKeys("Shloka Chowdhury");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='chkSelectRow[]']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='btnDelete']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='dialogDeleteBtn']")).click();		
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[normalize-space()='No Records Found']")).getText().equalsIgnoreCase("No Records Found"));

		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='logInPanelHeading']")).getText().equalsIgnoreCase("LOGIN Panel"));
	}
}

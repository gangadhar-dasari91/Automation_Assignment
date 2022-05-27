package com.techgenie.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techgenie.pages.GoogleMapPage;
import com.techgenie.utils.ScreenShotUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDataTables {
	WebDriver driver;
	 Wait<WebDriver> wait;
	 @BeforeTest
		public void setup() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			wait = new FluentWait<WebDriver>(driver)
				        .withTimeout(Duration.ofSeconds(30))
				        .pollingEvery(Duration.ofSeconds(5))
				        .ignoring(NoSuchElementException.class);
			
		}
	 
	 @Test
	 public void testDataTables() {
//		 Test NO : 5
//		 5.) Open https://datatables.net/ website.
		 	driver.get("https://datatables.net");
//			 a. Fetch 1st 25 entries from the table.
		 	
		 	Select selectEntries = new Select(driver.findElement(By.xpath("//select[@name='example_length']"))); 
		 	selectEntries.selectByValue("25");
		 	
//			 b. Do sort on Age column young to old.
		 	driver.findElement(By.xpath("//th[contains(@class,'sorting') and contains(text(),'Age')]")).click();
		 	
//			 c. Fetch name, position, office, age and salary details of the Software Engineers of below 30 years.
		 	List<WebElement> rowCount = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
		 	System.out.println("Name       |    Position     | Office          | Age    |  Salary    | ");
		 	for(int i=1;i<=rowCount.size();i++) {
//		 		Name
		 		String name = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+i+"]/td[1]")).getText();
//		 		Position
		 		String position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+i+"]/td[2]")).getText();
//		 		Office
		 		String office = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+i+"]/td[3]")).getText();
//		 		Age
		 		String ageStr = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+i+"]/td[4]")).getText();
		 		int age = Integer.parseInt(ageStr);
//		 		StartDate
		 		String startDate = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+i+"]/td[5]")).getText();
//		 		Salary
		 		String salary = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+i+"]/td[6]")).getAttribute("innerText");
		 		
		 		if(position.equalsIgnoreCase("Software Engineer") && (age<30)) {
		 			System.out.print(name+" | ");
		 			System.out.print(position+" | ");
		 			System.out.print(office+" | ");
		 			System.out.print(age+" | ");
		 			System.out.print(startDate+" | ");
		 			System.out.print(salary+" | ");
		 			System.out.println();
		 			
		 		}else {
		 			continue;
		 		}
		 	}
		 	
		 	
	 }
	 
	@AfterTest
		public void tearDown() {
			driver.quit();
		}
}

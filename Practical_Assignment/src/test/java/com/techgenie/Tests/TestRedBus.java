package com.techgenie.Tests;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techgenie.pages.RedBusHomePage;
import com.techgenie.pages.RedBusSearchResultPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestRedBus {
	WebDriver driver;
	Wait<WebDriver> wait;
	RedBusHomePage redbusHomePage;
	RedBusSearchResultPage redbusSearchResult;

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
			redbusHomePage = new RedBusHomePage(driver);
			redbusSearchResult = new RedBusSearchResultPage(driver);
			
		}
	
	
	@Test
	public void testRedBus() throws Exception {
//		Test 6: RedBus
//		6.) Open https://redbus.in/ website.
//			a. Search buses from Mumbai to Pune for current date (Today).
//			b. Capture all bus details having ratings > 4.0 in excel (sheet1) and ratings < 4.0 in same excel (sheet2)
//			c. save the excel in same project
		driver.get("https://www.redbus.in/");
		redbusHomePage.enterSource("Mumbai");
		redbusHomePage.enterDestination("Pune");
		redbusHomePage.selectCurrentDate();
		redbusHomePage.clickOnSearch();
		redbusSearchResult.scrollTillAllItemsLoaded();
		
		
		Thread.sleep(5000);
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
//			softAssert.assertAll();
	}

}

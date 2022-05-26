package com.techgenie.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techgenie.pages.GoogleMapPage;
import com.techgenie.pages.GoogleSearchHome;
import com.techgenie.pages.GoogleSearchResult;
import com.techgenie.utils.ScreenShotUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestGoogleSearchMaps {
	WebDriver driver;
	 Wait<WebDriver> wait;
	GoogleSearchHome gshome;
	GoogleSearchResult gsresult;
	GoogleMapPage gmaps;
	ScreenShotUtil screenshot;
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
		gshome = new GoogleSearchHome(driver);
		gsresult = new GoogleSearchResult(driver);
		gmaps = new GoogleMapPage(driver);
		screenshot = new ScreenShotUtil(driver);
		
	}
	
	@Test
	public void googleSearhTest() throws Exception {		
//		TestNO : - 1
//		a. Go to the below URL: https://www.google.com/maps/
			driver.get("https://www.google.com");
//			b. Search for “Wankhede Stadium” and then click on Maps
			gshome.searchSomething("Wankhede Stadium");
			screenshot.takeScreenShot("Test_1","snap1");
			gsresult.searchWithMapMode();
//			c. Save the screen shot image at this test execution instant
//			d. Verify the Text Present “Stadium” in the left frame
			Assert.assertTrue(StringUtils.containsIgnoreCase(gmaps.getHeadline(), "Stadium") ,"Does not contain the expected string");
//			e. Verify the Title “Wankhede Stadium - Google Maps”
			Assert.assertEquals(gmaps.getPageTitle(), "Wankhede Stadium - Google Maps","Page Title Did not match");
//			f. Print the ratings point and number of reviews in the console.
			System.out.println("Ratings : "+gmaps.getRating());
			System.out.println("Reviews : "+gmaps.getReviewCount());
//			g. Verify that link is Present “mumbaicricket.com” on the left frame.
			Assert.assertTrue(gmaps.getWebAddress().contains("mumbaicricket.com"),"Specified Link is not present");
//			System.out.println(gmaps.getWebAddress());
//			h. Print the address appearing above the “mumbaicricket.com”
			System.out.println("Address : "+gmaps.getAddress());
//			i. Verify that the phone number “022 2279 5500” is present
			Assert.assertTrue(gmaps.getPhoneNumber().equals("022 2279 5500"));
//			System.out.println(gmaps.getPhoneNumber());
//			j. Save the screen shot image at this test execution instant
			screenshot.takeScreenShot("Test_1","snap2");
	}
	

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

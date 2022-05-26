package com.techgenie.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.NoSuchElementException;

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

public class TestGoogleMapsAddress {
	WebDriver driver;
	 Wait<WebDriver> wait;
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
		gmaps = new GoogleMapPage(driver);
		screenshot = new ScreenShotUtil(driver);
		
	}
	
	@Test
	public void googleMapsAddressTest() throws Exception {
//			TestNo : 4
//			Open http://maps.google.com and write TestNG / WebDriver test for the following using Google Chrome: 
			driver.get("http://maps.google.com");
//			Search for your home address
			String homeAddress = "902 Building No 1 , Atlanta Edenworld , Bhiwandi";
			gmaps.searchOnMap(homeAddress);
//			Print the address text that is displayed in the left frame 
			System.out.println(gmaps.getAddress());
//			Take screenshot of the page at this instant and save the image 
			screenshot.takeScreenShot("Test_4", "snap1");
//			Click on direction and get direction from your office address to your home
			gmaps.clickOnDirections();
			String officeAddress = "Here Solutions Unit No1 3rd Floor Airoli 400078";
			gmaps.setStartLocation(officeAddress);
//			Display the first option distance and time suggested in eclipse console
			System.out.println("Distance : "+gmaps.getFirstOptionDistance());
			System.out.println("Estimated Time : "+gmaps.getFirstOptionTime());
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

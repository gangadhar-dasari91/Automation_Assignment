package com.techgenie.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.techgenie.utils.ExcelDataProvider;
import com.techgenie.pages.PepperFryHomePage;
import com.techgenie.pages.PepperFrySearchResultsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestPepperFry {
	WebDriver driver;
	Wait<WebDriver> wait;
	PepperFryHomePage pepperHome;
	PepperFrySearchResultsPage pepperSearchResult;
	SoftAssert softAssert = new SoftAssert();
	ExcelDataProvider dataProvider = new ExcelDataProvider();
	
	@Parameters("browser")
	@BeforeTest
	public void setup(String browser) {
		WebDriverManager.firefoxdriver().setup();
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();			
		}else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		else {// default chrome
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		pepperHome = new PepperFryHomePage(driver);
		pepperSearchResult = new PepperFrySearchResultsPage(driver);

	}
	
	
	@Test(dataProvider = "test1data")
	public void testPepperFryPriceOrder(String searchTerm) throws Exception {
//		On http://www.pepperfry.com/, search for the items related to the search keyword.
//		Rearrange the search results in	Ascending order of the price. 
//		Test #1: Use keyword “Bedsheets”, Test #2: Use keyword “Clocks” and Test #3: Use keyword “Padlocks”. 
//		Save these keywords in an Excel (.xls) file, read the search keywords from this file and then
//		execute your test. Write script in TestNG using WebDriver to test that the results are indeed in Ascending order in Google Chrome, FireFox and IE.
		driver.get("http://www.pepperfry.com");
//		String searchTerm = "PadLocks";
		pepperHome.searchProduct(searchTerm);
		pepperSearchResult.selectLowToHigh();
//		System.out.println("Number of products : "+pepperSearchResult.getProductsPriceList().length);
		softAssert.assertTrue(ArrayUtils.isSorted(pepperSearchResult.getProductsPriceList()),"The result is not in Ascending Order");
		Thread.sleep(3000);
		
	}
	
	@DataProvider(name = "test1data")
	public Object[][] getData() {
		String excelPath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "TestData.xlsx").toString();
		Object data[][] = dataProvider.testData(excelPath, "Sheet1");
		return data;		
	}
	
	@AfterMethod
	@AfterTest
	public void tearDown() {
		driver.quit();
		softAssert.assertAll();
	}
}

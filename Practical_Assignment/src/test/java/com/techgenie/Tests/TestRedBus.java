package com.techgenie.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		System.out.println(redbusSearchResult.getBusDataItems().size());
//		redbusSearchResult.getBusDataItems();
		List<WebElement> busDataItems = redbusSearchResult.getBusDataItems();
		
		 //// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// create Blank Sheet
		XSSFSheet sheet1 = workbook.createSheet("Sheet1");
		XSSFSheet sheet2 = workbook.createSheet("Sheet2");
		
		// This Data Need to be written Object[]
		Map<String, Object[]> data1 = new TreeMap<String, Object[]>();
		data1.put("1", new Object[] { "Travels", "BusType", "DepartTime" ,"DepartLocation","ArrivalTime","ArrivalLocation","JourneyDuration","StartingFare","Seats Left","Windows Left","Rating"});
		
		Map<String, Object[]> data2 = new TreeMap<String, Object[]>();
		data2.put("1", new Object[] { "Travels", "BusType", "DepartTime" ,"DepartLocation","ArrivalTime","ArrivalLocation","JourneyDuration","StartingFare","Seats Left","Windows Left","Rating"});
		
		
		int idnum = 2;
		for(WebElement e: busDataItems) {
			String travels = e.findElement(By.cssSelector("div[class*='travels']")).getText();
			String busType = e.findElement(By.cssSelector("div[class*='bus-type']")).getText();
			String depTime = e.findElement(By.cssSelector("div[class*='dp-time']")).getText();
			String depLoc = e.findElement(By.cssSelector("div[class*='dp-loc']")).getText();
			String arrTime = e.findElement(By.cssSelector("div[class*='bp-time']")).getText();
			String arrLoc = e.findElement(By.cssSelector("div[class*='bp-loc']")).getText();
			String journeyDur = e.findElement(By.cssSelector("div[class*='dur']")).getText();
			String fare =e.findElement(By.cssSelector("div[class*='seat-fare'] span")).getText();
			String seatsLeft = (!e.findElements(By.cssSelector("div[class*='seat-left']")).isEmpty())? e.findElement(By.cssSelector("div[class*='seat-left']")).getText().replaceAll("\\D",""): "0";
			String windowLeft =(!e.findElements(By.cssSelector("div[class*='window-left']")).isEmpty())? e.findElement(By.cssSelector("div[class*='window-left']")).getText().replaceAll("\\D",""): "0";
			String rating =(!e.findElements(By.cssSelector("div[class*='rating-sec']")).isEmpty())? e.findElement(By.cssSelector("div[class*='rating-sec']")).getText(): "0";
			
			float ratingVal = Float.parseFloat(rating);
			
			if(ratingVal > 4.0) {
				data1.put(String.valueOf(idnum++), new Object[] { travels, busType, depTime ,depLoc,arrTime,arrLoc,journeyDur,fare,seatsLeft,windowLeft,rating});
			}else if(ratingVal < 4.0){
				data2.put(String.valueOf(idnum++), new Object[] { travels, busType, depTime ,depLoc,arrTime,arrLoc,journeyDur,fare,seatsLeft,windowLeft,rating});
			}
			
			
//			System.out.println(travels+","+busType+","+depTime+","+depLoc+","+arrTime+","+arrLoc+","+journeyDur+","+fare+","+seatsLeft+","+windowLeft+","+rating);
			
		}
		
		// Iterate Over data and Write to Sheet

		Set<String> keySet1 = data1.keySet();
		Set<String> keySet2 = data2.keySet();

		// Write to Sheet 1
		writeDataToCell(keySet1, data1, sheet1);

		// Write to Sheet 2
		writeDataToCell(keySet2, data2, sheet2);
		
		try {
			// Write the workbook in the fs
			FileOutputStream out = new FileOutputStream(new File("RedBusData.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("Write data to Excel Sucessful");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Thread.sleep(5000);
		
	}

	@AfterMethod
	@AfterTest
	public void tearDown() {
		driver.quit();
//			softAssert.assertAll();
	}
	
	public static void writeDataToCell(Set<String> keyset,Map<String, Object[]> data,XSSFSheet sheet) {
		int rownum = 0;
		for(String key: keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum =0;
			
			for(Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String) {
					cell.setCellValue((String)obj);
				}else if(obj instanceof Integer) {
					cell.setCellValue((Integer)obj);
				}
			}
			
		}
	}

}

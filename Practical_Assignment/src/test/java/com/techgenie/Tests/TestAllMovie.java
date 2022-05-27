package com.techgenie.Tests;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techgenie.pages.AllMovieHomePage;
import com.techgenie.pages.AllMovieSearchResultPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAllMovie {
	
	WebDriver driver;
	 Wait<WebDriver> wait;
	 AllMovieHomePage allMovieHome ;
	 AllMovieSearchResultPage allMovieSearchResult;
	 @BeforeTest
		public void setup() {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			wait = new FluentWait<WebDriver>(driver)
				        .withTimeout(Duration.ofSeconds(30))
				        .pollingEvery(Duration.ofSeconds(5))
				        .ignoring(NoSuchElementException.class);
			allMovieHome = new AllMovieHomePage(driver);
			allMovieSearchResult = new AllMovieSearchResultPage(driver);
			
		}
	@Test
	public void testMovieDetails() throws Exception {
//		2.) Using TestNG, Page Factory and WebDriver script, open http://www.allmovie.com/ in Firefox and search for movie “The Godfather” and do the below: 
			driver.get("http://www.allmovie.com/");
			allMovieHome.searchMovie("The Godfather");
//			a. Print number of search results items found in eclipse console
			allMovieSearchResult.getTitles();
//			b. Click on the movie link page with release year “1972”
//			c. Verify that the movie’s genre is “Crime” and MPAA rating is “A”.
//			d. Verify on Cast & Crew page that the movie’s director’s name is “Francis Ford Coppola”
//			e. Verify on Cast & Crew page that the Al Pacino’s character’s name is Michael Corleone
			
			//during dev of testcase
			Thread.sleep(3000);
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

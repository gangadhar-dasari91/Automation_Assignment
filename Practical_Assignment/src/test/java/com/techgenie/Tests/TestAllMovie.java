package com.techgenie.Tests;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.techgenie.pages.AllMovieDetailsPage;
import com.techgenie.pages.AllMovieHomePage;
import com.techgenie.pages.AllMovieSearchResultPage;
import com.techgenie.pages.MovieCastAndCrewPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAllMovie {
	
	WebDriver driver;
	 Wait<WebDriver> wait;
	 AllMovieHomePage allMovieHome ;
	 AllMovieSearchResultPage allMovieSearchResult;
	 AllMovieDetailsPage movieDetailPage;
	 MovieCastAndCrewPage movieCastAndCrew;
	 SoftAssert softAssert = new SoftAssert();
	 @BeforeTest
		public void setup() {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
			wait = new FluentWait<WebDriver>(driver)
				        .withTimeout(Duration.ofSeconds(30))
				        .pollingEvery(Duration.ofSeconds(5))
				        .ignoring(NoSuchElementException.class);
			allMovieHome = new AllMovieHomePage(driver);
			allMovieSearchResult = new AllMovieSearchResultPage(driver);
			movieDetailPage = new AllMovieDetailsPage(driver);
			movieCastAndCrew = new MovieCastAndCrewPage(driver);
			
		}
	@Test
	public void testMovieDetails() throws Exception {
//		Test No2
//		Using TestNG, Page Factory and WebDriver script, open http://www.allmovie.com/ in Firefox and search for movie “The Godfather” and do the below: 
			driver.get("http://www.allmovie.com/");
			allMovieHome.searchMovie("The Godfather");
//			a. Print number of search results items found in eclipse console
			System.out.println("Search Results count : "+allMovieSearchResult.getResultCount());
//			b. Click on the movie link page with release year “1972”
			allMovieSearchResult.selectByRealeaseYear("1972");
//			c. Verify that the movie’s genre is “Crime” and MPAA rating is “A”.
			softAssert.assertEquals(movieDetailPage.getGenre(),"Crime","Actual and Expected Genre doesn't match");  
			softAssert.assertEquals(movieDetailPage.getMpaaRating(),"A","MPAA Rating not matched with the expected rating");
//			d. Verify on Cast & Crew page that the movie’s director’s name is “Francis Ford Coppola”
			movieDetailPage.clickCastAndCrewTab();
			softAssert.assertTrue(movieCastAndCrew.getDirectors().contains("Francis Ford Coppola"), "Expected Director's name not matched");
//			e. Verify on Cast & Crew page that the Al Pacino’s character’s name is Michael Corleone
			softAssert.assertEquals(movieCastAndCrew.getCharacterNameByActorName("Al Pacino"), "Michael Corleone");			
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
		softAssert.assertAll();
	}
}

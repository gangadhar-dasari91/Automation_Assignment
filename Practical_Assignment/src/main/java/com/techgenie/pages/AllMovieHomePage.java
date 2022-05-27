package com.techgenie.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllMovieHomePage {
	WebDriver driver;
	
	public AllMovieHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@class='site-search-input']")
	WebElement itxt_search;
	
	public void searchMovie(String movieName) {
		itxt_search.sendKeys(movieName,Keys.ENTER);
	}
}

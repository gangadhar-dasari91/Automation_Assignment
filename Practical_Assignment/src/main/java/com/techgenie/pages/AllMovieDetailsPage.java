package com.techgenie.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AllMovieDetailsPage {
	WebDriver driver;
	JavascriptExecutor jse;
	public AllMovieDetailsPage(WebDriver driver) {
		this.driver = driver;	
		jse = (JavascriptExecutor)driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
	}
	
	
	@FindBy(xpath="//span[@class='header-movie-genres']/child::*[1]")
	WebElement txt_genre;
	
	@FindBy(xpath="//li[@class='tab cast-crew']/a[@href]")
	WebElement btn_castAndCrew;
	
	@FindBy(xpath="//span[contains(text(),'MPAA Rating')]/child::*[1]")
	WebElement txt_mpaaRating;
	
	public String getGenre() {
		return txt_genre.getText();
	}
	
	public String getMpaaRating() {
		return txt_mpaaRating.getText();
	}
	
	public void clickCastAndCrewTab() {
		btn_castAndCrew.click();
	}
}

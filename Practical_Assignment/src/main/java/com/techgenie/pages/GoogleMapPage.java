package com.techgenie.pages;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class GoogleMapPage {
	WebDriver driver;
	 Wait<WebDriver> wait;
	public GoogleMapPage(WebDriver driver) {
		this.driver = driver;
		wait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofSeconds(30))
		        .pollingEvery(Duration.ofSeconds(5))
		        .ignoring(NoSuchElementException.class);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//div[contains(@aria-label,'Results')]//a[contains(@jsaction,'contextmenu:pane')]")
	WebElement btn_firstResult;
	
	@FindBy(xpath="//button[@id='searchbox-searchbutton']")
	WebElement btn_searchButton;
	
	@FindBy(xpath="//input[@id='searchboxinput']")
	WebElement itxt_searchOnMap;
	
	@FindBy(xpath="(//h1[contains(@class,'fontHeadlineLarge')]/span)[position()=1]")
	WebElement txt_headline;
	
	@FindBy(xpath="//div[@jsaction='pane.rating.moreReviews']/span/span/span[1]")
	WebElement txt_rating;
	
	@FindBy(xpath="//button[contains(@aria-label,'reviews') and @jsaction='pane.rating.moreReviews' ]")
	WebElement txt_reviews;
	
	@FindBy(xpath="//button[contains(@aria-label,'Website:')]//div[contains(@class,'fontBodyMedium')]")
	WebElement txt_website;
	
	@FindBy(xpath="//button[contains(@aria-label,'Address:')]//div[contains(@class,'fontBodyMedium')]")
	WebElement txt_address;
	
	@FindBy(xpath="//button[contains(@aria-label,'Phone:')]//div[contains(@class,'fontBodyMedium')]")
	WebElement txt_phoneNumber;
	
	@FindBy(xpath="//button[@jsaction='pane.placeActions.directions;keydown:pane.placeActions.directions']")
	WebElement btn_direction;
	
	@FindBy(xpath="//div[@id='directions-searchbox-0']//input")
	WebElement itxt_fromLocation;
	
	@FindBy(xpath="//div[@id='directions-searchbox-1']//input")
	WebElement itxt_toLocation;
	
	@FindBy(xpath="//img[@aria-label='Driving']/parent::button")
	WebElement btn_driveMode;
	
	@FindBy(xpath="//img[@aria-label='Transit']/parent::button")
	WebElement btn_transitMode;
	
	@FindBy(xpath="//img[@aria-label='Walking']/parent::button")
	WebElement btn_walkMode;
	
	@FindBy(xpath="//div[@id='section-directions-trip-0']//div[contains(@class,'delay')]")
	WebElement txt_firstOptTime;
	
	@FindBy(xpath="//div[@id='section-directions-trip-0']//div[contains(@class,'delay')]//following-sibling::div")
	WebElement txt_firstOptDistance;
		
	public String getHeadline() {
		String headline = "";
		if(txt_headline != null) {
			headline = txt_headline.getText();
		}
		return headline;
	}
	
	public Float getRating() {
		float rating = 0;
		if(txt_rating != null) {
			rating = Float.parseFloat(txt_rating.getText());
		}
		return rating;
	}
	
	public int getReviewCount() {
		int review = 0;
		if(txt_reviews != null) {
		review = Integer.parseInt(txt_reviews.getText().replaceAll("\\D",""));
		}
		return review;
	}
	
	public String getWebAddress() {
		String website = "";
		if(txt_website != null) {
			website = txt_website.getText();
		}
		return website;
	}
	
	public String getAddress() {
		String address ="";
		if(txt_address != null) {
			address = txt_address.getText();
		}
		return address;
	}
	
	public String getPhoneNumber() {
		String phoneNumber = "";
		if(txt_phoneNumber != null) {
			phoneNumber = txt_phoneNumber.getText();
		}
		return phoneNumber;
	}
	
	public void searchOnMap(String searchTerm) {
		itxt_searchOnMap.sendKeys(searchTerm);
		btn_searchButton.click();
		// clicking on first option if there are multiple result
		try {
			btn_firstResult.click();
		}catch(Exception e){
			System.out.println("No context menu found");
		}
	}
	
	public void clickOnDirections() {
		btn_direction.click();
	}
	
	public void setStartLocation(String startLocation) {
		wait.until(ExpectedConditions.visibilityOf(itxt_fromLocation));
		itxt_fromLocation.sendKeys(startLocation,Keys.ENTER);
	}
	
	public void setDestinationLocation(String destination) {
		itxt_toLocation.sendKeys(destination,Keys.ENTER);
	}
	
	public String getFirstOptionDistance() {
		wait.until(ExpectedConditions.visibilityOf(txt_firstOptDistance));
		String fdistance = "";
		if(txt_firstOptDistance != null) {
			fdistance = txt_firstOptDistance.getText();
		}
		return fdistance;
	}
	
	public String getFirstOptionTime() {
		wait.until(ExpectedConditions.visibilityOf(txt_firstOptTime));
		String fTime = "";
		if(txt_firstOptTime != null) {
			fTime =  txt_firstOptTime.getText();
		}
		return fTime;
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
}

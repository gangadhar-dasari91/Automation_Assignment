package com.techgenie.pages;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class RedBusHomePage {
WebDriver driver;
JavascriptExecutor jse;
Wait<WebDriver> wait;
	public RedBusHomePage(WebDriver driver) {
		this.driver = driver;
		jse = (JavascriptExecutor)driver;
		wait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofSeconds(30))
		        .pollingEvery(Duration.ofSeconds(5))
		        .ignoring(NoSuchElementException.class);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='src']")
	WebElement itxt_source;
	
	@FindBy(xpath="//input[@id='dest']")
	WebElement itxt_destination;
	
	@FindBy(xpath="//div[contains(@class,'search-box date-box gtm-onwardCalendar')]")
	WebElement btn_journeyDate;
	
	@FindBy(xpath="//td[@class='current day']")
	WebElement btn_currentDate;
	
	@FindBy(xpath="//ul[contains(@class,'homeSearch')]")
	WebElement txt_locationSuggestion;
	
	@FindBy(xpath="//button[@id='search_btn']")
	WebElement btn_Search;
	
	public void enterSource(String source) {
		itxt_source.clear();
		itxt_source.sendKeys(source);
		wait.until(ExpectedConditions.visibilityOf(txt_locationSuggestion));
		List<WebElement> sl= (List<WebElement>) jse.executeScript("return document.querySelectorAll(\"ul[class*='homeSearch']>li\");");
		sl.get(0).click();
//		itxt_source.sendKeys(Keys.TAB);
	}
	
	public void enterDestination(String destination) {
		itxt_destination.clear();
		itxt_destination.sendKeys(destination);
		wait.until(ExpectedConditions.visibilityOf(txt_locationSuggestion));
		List<WebElement> dl= (List<WebElement>) jse.executeScript("return document.querySelectorAll(\"ul[class*='homeSearch']>li\");");
		dl.get(0).click();
		
	}
	
	public void selectCurrentDate() {
		btn_journeyDate.click();
		btn_currentDate.click();
	}
	
	public void clickOnSearch() {
		jse.executeScript("arguments[0].click()",btn_Search);
//		btn_Search.click();
	}
}

package com.techgenie.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RedBusHomePage {
WebDriver driver;
	
	public RedBusHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='src']")
	WebElement itxt_source;
	
	@FindBy(xpath="//input[@id='dest']")
	WebElement itxt_destination;
	
	public void enterSource(String source) {
		itxt_source.clear();
		itxt_source.sendKeys(source);
	}
	
	public void enterDestination(String destination) {
		itxt_destination.clear();
		itxt_destination.sendKeys(destination);
	}
}

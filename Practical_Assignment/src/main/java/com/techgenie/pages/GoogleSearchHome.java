package com.techgenie.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchHome {
	
	WebDriver driver;
	
	public GoogleSearchHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@name='q']")
	WebElement itxt_Search;
	
	public void searchSomething(String searchTerm) {
		itxt_Search.sendKeys(searchTerm,Keys.ENTER); 
	} 

}

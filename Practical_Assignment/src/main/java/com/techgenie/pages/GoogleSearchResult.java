package com.techgenie.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchResult {
	WebDriver driver;
	
	public GoogleSearchResult(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//div[@id='hdtb']//a[contains(text(),'Maps')]")
	WebElement btn_maps;
	
	@FindBy(xpath="//div[@id='hdtb']//a[contains(text(),'Images')]")
	WebElement btn_images;
	
	public void searchWithMapMode() {
		btn_maps.click();
	}
	
}

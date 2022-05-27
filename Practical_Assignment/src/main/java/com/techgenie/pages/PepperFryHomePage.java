package com.techgenie.pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class PepperFryHomePage {
	WebDriver driver;
	JavascriptExecutor jse;
	Wait<WebDriver> wait;
	public PepperFryHomePage(WebDriver driver) {
		this.driver = driver;	
		jse = (JavascriptExecutor)driver;
		wait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofSeconds(30))
		        .pollingEvery(Duration.ofSeconds(5))
		        .ignoring(NoSuchElementException.class);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	@FindBy(xpath="//input[@id='search']")
	WebElement itxt_search;
	
	@FindBy(xpath="//div[@id='reg_login_box']/div[@id='regPopUp']/a[@class='popup-close']")
	WebElement btn_popUpClose;
	
	
	public void waitAndClosePopup() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btn_popUpClose));
			btn_popUpClose.click();
		}catch(Exception e) {
			System.out.println("Login pop up was not visible");
		}
	}
	
	public void searchProduct(String searchTerm) {
		waitAndClosePopup();
		itxt_search.sendKeys(searchTerm,Keys.ENTER);
	}
}

package com.techgenie.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class PepperFrySearchResultsPage {
	WebDriver driver;
	JavascriptExecutor jse;
	Wait<WebDriver> wait;
	public PepperFrySearchResultsPage(WebDriver driver) {
		this.driver = driver;	
		jse = (JavascriptExecutor)driver;
		wait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofSeconds(30))
		        .pollingEvery(Duration.ofSeconds(5))
		        .ignoring(NoSuchElementException.class);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
	}
	
	@FindBy(xpath="//input[@id='price-asc']")
	WebElement rad_lowToHighPrice;
	
	@FindBy(xpath="//input[@id='price-desc']")
	WebElement rad_highToLow;
	
	@FindBy(xpath="//div[@class='clipCard__wrapper']")
	List<WebElement>  listProducts;
	
	@FindBy(xpath="(//div[@class='clipCard__wrapper'])")
	WebElement productCard;
	
	@FindBy(xpath="//div[@id='webklipper-publisher-widget-container-notification-close-div']")
	WebElement btn_closeNotification;
	
	@FindBy(xpath="//span[contains(text(),'No results found for')]")
	WebElement noResults;
//	div[@class="clipCard__wrapper"]//span[@class="clipCard__price-offer"]
	
	public void waitAndCloseNotification() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btn_closeNotification));
			btn_closeNotification.click();
		}catch(Exception e) {
			System.out.println("notification was not visible");
		}
	}
	
	public void selectLowToHigh() {
		try {
			if(noResults.isDisplayed()) {
				return;
			}
		} catch (Exception e) {
			System.out.println("Results found");
		}
		jse.executeScript("arguments[0].click()",rad_lowToHighPrice);
	}
	
	public void selectHighToLow() {
		try {
			if(noResults.isDisplayed()) {
				return;
			}
		} catch (Exception e) {
			System.out.println("Results found");
		}
		jse.executeScript("arguments[0].click()",rad_highToLow);
	}
	
	public int[]  getProductsPriceList() {
		try {
			if(noResults.isDisplayed()) {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Results found");
		}
	wait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.readyState == 'complete'"));
		List<WebElement> l= (List<WebElement>) jse.executeScript("return document.querySelectorAll(\"span[class='clipCard__price-offer']\")");
		int pricelist[] = new int[l.size()];
		for(int i =0;i<pricelist.length;i++) {
			pricelist[i] = Integer.parseInt(l.get(i).getText().replaceAll("\\D", ""));
//			System.out.println(pricelist[i]);
		}
		return pricelist;
	}
	
	
}

package com.techgenie.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class RedBusSearchResultPage {
	WebDriver driver;
	JavascriptExecutor jse;
	Wait<WebDriver> wait;
		public RedBusSearchResultPage(WebDriver driver) {
			this.driver = driver;
			jse = (JavascriptExecutor)driver;
			wait = new FluentWait<WebDriver>(driver)
			        .withTimeout(Duration.ofSeconds(30))
			        .pollingEvery(Duration.ofSeconds(5))
			        .ignoring(NoSuchElementException.class);
			PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
		}
		
		@FindBy(xpath="//div[@class='clearfix bus-item']")
		WebElement busItem;	
		
		@FindBy(xpath="//div[@class='button' and contains(text(),'View Buses')]")
		WebElement btn_viewHiddenBusses;
		
		@FindBy(xpath="//div[contains(@class,'clearfix onward')]//span[contains(@class,'busFound')]")
		WebElement txt_numOfBusesFound;
		
		public void scrollTillAllItemsLoaded() throws Exception {
			wait.until(ExpectedConditions.visibilityOf(busItem));
			int numOfBusesfound = Integer.parseInt(txt_numOfBusesFound.getText().replaceAll("\\D", ""));
			System.out.println("Number of Buses : "+numOfBusesfound);
			if(btn_viewHiddenBusses.isDisplayed()) {
				btn_viewHiddenBusses.click();
			}
			
			
			
			List<WebElement> l =(List<WebElement>) jse.executeScript("return document.querySelectorAll(\"div[class='clearfix bus-item']\")");
			int currentItemSize = l.size();
			while(!(currentItemSize == numOfBusesfound)) {
				
				jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				// ToDO we have to find a way to replace Thread.sleep();  this is a temporary workaround
//				Thread.sleep(2000);
				jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				l =(List<WebElement>) jse.executeScript("return document.querySelectorAll(\"div[class='clearfix bus-item']\")");
				currentItemSize = l.size();
			}
			System.out.println("loaded num of buses : "+currentItemSize);
		}
}

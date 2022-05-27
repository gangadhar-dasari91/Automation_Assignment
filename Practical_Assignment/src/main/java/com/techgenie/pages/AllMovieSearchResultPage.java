package com.techgenie.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

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

public class AllMovieSearchResultPage {
	WebDriver driver;
	JavascriptExecutor jse;
	public AllMovieSearchResultPage(WebDriver driver) {
		this.driver = driver;	
		jse = (JavascriptExecutor)driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
	}

	@FindBy(xpath="//div[@class='results']/h1")
	WebElement results;
	
	@FindBy(xpath="//div[@class='no-results']")
	WebElement noResults;
	
	@FindBy(xpath = "//div[@class='stripe-container footer-container']")
	WebElement footer;

	@FindBy(xpath = "//li[@class='movie']//div[@class='title']")
	List<WebElement> listTitle;

	public void scrollTillAllItemsLoaded() throws Exception {
		boolean noresults = false;
		try {
			if(noResults.isDisplayed()) {
				noresults = true;
			}
		}catch(Exception exp){
			System.out.println("No Results : "+noresults);
		}
		
		if(!noresults) {
			while(true) {
				jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				Long scrollHeightA = (Long) jse.executeScript("return document.body.scrollHeight");
//				System.out.println(scrollHeightA);
				// ToDO we have to find a way to replace Thread.sleep();  this is a temporary workaround
				Thread.sleep(3000);
				jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				Long scrollHeightB = (Long) jse.executeScript("return document.body.scrollHeight");
//				System.out.println(scrollHeightB);
				if(scrollHeightA.equals(scrollHeightB)) {
					break;
				}
			}
			
		}
		
		
		
	}

	public int getResultCount() throws Exception {
		scrollTillAllItemsLoaded();
		int results = 0;
		if(!listTitle.isEmpty()) {
			results = listTitle.size();			
		}
		return results;
	}
	
	public void selectByRealeaseYear(String releaseYear) {
		for(WebElement e: listTitle) {
			String title = e.getAttribute("innerText");
			if(title.contains(releaseYear)) {
				e.findElement(By.cssSelector("a[href]")).click();
				return;
			}else {continue;}
		}
	}
}

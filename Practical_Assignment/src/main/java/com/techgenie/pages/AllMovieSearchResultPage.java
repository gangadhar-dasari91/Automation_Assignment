package com.techgenie.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllMovieSearchResultPage {
WebDriver driver;
	
	public AllMovieSearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//li[@class='movie']//div[@class='title']")
	List<WebElement> listTitle;
	
	public void getTitles() {
		for(WebElement ele : listTitle) {
			System.out.println(ele.getText());
		}
	}
}

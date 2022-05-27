package com.techgenie.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MovieCastAndCrewPage {
	WebDriver driver;
	JavascriptExecutor jse;
	public MovieCastAndCrewPage(WebDriver driver) {
		this.driver = driver;	
		jse = (JavascriptExecutor)driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
	}
	
	@FindBy(xpath="//div[@class='director_container']//dt[contains(@class,'artist')]")
	List<WebElement> listDirectors;
	
	@FindBy(xpath="//div[@class='cast_container']")
	List<WebElement> listCast;
	
	public List<String> getDirectors() {
		List<String> dirList = new ArrayList<String>();
		for(WebElement e: listDirectors) {
			dirList.add(e.getText());
		}
		return dirList;	
	}
	
	public String getCharacterNameByActorName(String actorName) {
		HashMap<String,String> actorCharactror = new HashMap<String,String>();
		
		for(WebElement e: listCast) {
			String actor = e.findElement(By.xpath("//div[@class='cast_name artist-name']")).getText();
			String character = e.findElement(By.xpath("//div[@class='cast_role']")).getText();
			actorCharactror.put(actor, character);
		}
		
		return actorCharactror.get(actorName);
		
	}
}

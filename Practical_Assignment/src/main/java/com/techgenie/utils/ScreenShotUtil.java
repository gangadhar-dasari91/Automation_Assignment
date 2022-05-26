package com.techgenie.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil {
	WebDriver driver;
	
	
	public ScreenShotUtil(WebDriver driver) {
		this.driver = driver;
	}


	public void takeScreenShot(String dirUnderScreen,String fileName) throws IOException {
		String filePath = Paths.get(System.getProperty("user.dir"),"screenshots",dirUnderScreen ,fileName+".jpg").toString();
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(filePath));
	}
}

package org.course.selenium.webdriver.exercise1.chrome;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EntryApplicationTest {
	
	private static final String APP_TITLE = "TODO Web Application";
	
	private static WebDriver webDriver;
	
	@BeforeClass
	public static void start(){
		System.setProperty("webdriver.chrome.driver", "C:\\DeveloperApps\\selenum-drivers\\chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.get("http://localhost:3000");
	}

	@Test
	public void testEntryWebApp(){
		WebElement title = webDriver.findElement(By.tagName("h1"));
		
		assertTrue(
				"La aplicación se ha cargado correctamente.", 
				(APP_TITLE.equals(title.getText()))
				);
	}
	
	@AfterClass
	public static void finish(){
		webDriver.quit();
	}	
	
}

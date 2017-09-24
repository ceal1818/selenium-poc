package org.course.selenium.webdriver.exercise1.firefox;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EntryApplicationTest {
	
	private static final String APP_TITLE = "TODO Web Application";
	
	private static WebDriver webDriver;
	
	@BeforeClass
	public static void start(){
		System.setProperty("webdriver.gecko.driver", "C:\\DeveloperApps\\selenum-drivers\\geckodriver.exe");
		webDriver = new FirefoxDriver();
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

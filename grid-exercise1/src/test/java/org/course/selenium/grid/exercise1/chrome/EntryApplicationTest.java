package org.course.selenium.grid.exercise1.chrome;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class EntryApplicationTest {
	
	private static final String APP_TITLE = "TODO Web Application";
	
	private static WebDriver webDriver;
	
	@BeforeClass
	public static void start() throws MalformedURLException{
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		webDriver.get("http://localhost:3000");
	}

	@Test
	public void testEntryWebApp(){
		WebElement title = webDriver.findElement(By.tagName("h1"));
		
		assertTrue(
				"La aplicaci�n se ha cargado correctamente.", 
				(APP_TITLE.equals(title.getText()))
				);
	}
	
	@AfterClass
	public static void finish(){
		webDriver.quit();
	}	
	
}

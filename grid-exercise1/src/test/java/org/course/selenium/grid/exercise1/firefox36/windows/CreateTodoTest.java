package org.course.selenium.grid.exercise1.firefox36.windows;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateTodoTest {

	private static final String TODO_TITLE = "Test Todo";
	private static final String TODO_DESCRIPTION = "Test Todo";
	
	private static WebDriver webDriver;
	
	@BeforeClass
	public static void start() throws MalformedURLException{
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setPlatform(Platform.WINDOWS);
		capabilities.setVersion("3.6");
		
		webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		webDriver.get("http://localhost:3000");
	}
	
	@Test
	public void testCreateEntity(){
		WebElement titleInput = null, descriptionInput = null, createButton = null, titleTodoCreated = null;
		WebDriverWait wait = new WebDriverWait(webDriver, 5);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
		
		titleInput = webDriver.findElement(By.id("title"));
		descriptionInput = webDriver.findElement(By.id("description"));
		createButton = webDriver.findElement(By.id("create"));
		
		titleInput.sendKeys(TODO_TITLE);
		descriptionInput.sendKeys(TODO_DESCRIPTION);
		createButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='todo-list']/tr[last()]/td")));
		
		titleTodoCreated = webDriver.findElement(By.xpath("//tbody[@id='todo-list']/tr[last()]/td"));
		
		Assert.assertTrue("El todo "+TODO_TITLE+" ha sido creado con �xito...", (TODO_TITLE.equals(titleTodoCreated.getText())));
	}
	
	@AfterClass
	public static void finish(){
		webDriver.quit();
	}		
	
}

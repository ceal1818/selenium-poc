package org.course.selenium.grid.exercise1.firefox36.windows;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

public class DeleteTodoTest {
	
	private static final String TODO_TITLE = "Test Todo";
	private static final String TODO_DESCRIPTION = "Test Todo";
	
	private static WebDriver webDriver;
	private static WebDriverWait wait;
	
	@BeforeClass
	public static void start() throws MalformedURLException{
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setPlatform(Platform.WINDOWS);
		capabilities.setVersion("3.6");
		
		webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		
		WebElement titleInput = null, descriptionInput = null, createButton = null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
		
		titleInput = webDriver.findElement(By.id("title"));
		descriptionInput = webDriver.findElement(By.id("description"));
		createButton = webDriver.findElement(By.id("create"));
		
		titleInput.sendKeys(TODO_TITLE);
		descriptionInput.sendKeys(TODO_DESCRIPTION);
		createButton.click();		
	}
	
	@Test
	public void testDeleteTodo(){
		WebElement deleteButton = null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//td[contains(@class, 'todo-title') and text() = '"+TODO_TITLE+"']")
				));
		
		deleteButton = webDriver.findElement(By.xpath("//input[@id='removeBtn']"));
		deleteButton.click();
		
		List<WebElement> trs = webDriver.findElements(By.xpath("//tbody[@id='todo-list']/tr"));
		
		Assert.assertTrue(trs.isEmpty());
	}
	
	@AfterClass
	public static void finish() {
		webDriver.quit();
	}
	
}

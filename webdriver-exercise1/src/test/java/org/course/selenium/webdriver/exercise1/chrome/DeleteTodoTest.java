package org.course.selenium.webdriver.exercise1.chrome;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteTodoTest {
	
	private static final String TODO_TITLE = "Test Todo";
	private static final String TODO_DESCRIPTION = "Test Todo";
	
	private static WebDriver webDriver;
	private static WebDriverWait wait;
	
	@BeforeClass
	public static void start(){
		System.setProperty("webdriver.chrome.driver", "C:\\DeveloperApps\\selenum-drivers\\chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.get("http://localhost:3000");
		wait = new WebDriverWait(webDriver, 5);
		
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

package org.course.selenium.webdriver.exercise1.chrome;

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

public class CreateTodoTest {

	private static final String TODO_TITLE = "Test Todo";
	private static final String TODO_DESCRIPTION = "Test Todo";
	
	private static WebDriver webDriver;
	
	@BeforeClass
	public static void start(){
		System.setProperty("webdriver.chrome.driver", "C:\\DeveloperApps\\selenum-drivers\\chromedriver.exe");
		webDriver = new ChromeDriver();
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
		
		Assert.assertTrue("El todo "+TODO_TITLE+" ha sido creado con éxito...", (TODO_TITLE.equals(titleTodoCreated.getText())));
	}
	
	@AfterClass
	public static void finish(){
		webDriver.quit();
	}		
	
}

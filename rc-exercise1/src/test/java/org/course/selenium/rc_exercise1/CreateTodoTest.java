package org.course.selenium.rc_exercise1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

@SuppressWarnings("deprecation")
public class CreateTodoTest {
	
	private static final String TITLE = "Todo Test";
	private static final String DESCRIPTION = "Todo Test";
	private static Selenium selenium;
	
	@BeforeClass
	public static void init(){
		selenium = new DefaultSelenium("localhost", 4444, 
				"*firefox", "http://localhost:3000");
		
		selenium.start();
	}
	
	@Test
	public void testCreateTodo() throws Exception{
		selenium.open("/");
		selenium.windowFocus();
		selenium.windowMaximize();
		selenium.setSpeed("1000");
		
		selenium.type("xpath=//input[@id='title']", TITLE);
		selenium.type("xpath=//textarea[@id='description']", DESCRIPTION);
		
		selenium.click("id=create");
		
		String titleResult = selenium.getText("xpath=//tbody[@id='todo-list']/tr[last()]/td");
		Assert.assertTrue(TITLE.equals(titleResult));
	}
	
	@AfterClass
	public static void finish(){
		selenium.stop();
	}	
}

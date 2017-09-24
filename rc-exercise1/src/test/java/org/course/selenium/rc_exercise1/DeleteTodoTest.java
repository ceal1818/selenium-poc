package org.course.selenium.rc_exercise1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

@SuppressWarnings("deprecation")
public class DeleteTodoTest {

	private static final String TITLE = "Todo Test";
	private static final String DESCRIPTION = "Todo Test";
	private static Selenium selenium;
	
	@BeforeClass
	public static void init(){
		selenium = new DefaultSelenium("localhost", 4444, 
				"*firefox", "http://localhost:3000");
		
		selenium.start();
	}
	
	@Before
	public void setUp(){
		selenium.open("/");
		selenium.setSpeed("1000");
		
		selenium.type("xpath=//input[@id='title']", TITLE);
		selenium.type("xpath=//textarea[@id='description']", DESCRIPTION);
		
		selenium.click("id=create");
	}
	
	@Test
	public void testDeleteTodo(){
		selenium.windowFocus();
		selenium.windowMaximize();
		
		selenium.click("xpath=//tbody[@id='todo-list']/tr[last()]/td[last()]/input[@id='removeBtn']");
		
		Number count = selenium.getXpathCount("xpath=//tbody[@id='todo-list']/tr");
		
		Assert.assertTrue("No se ha eliminado el todo creado.",	(count.intValue() == 0));
	}
	
	@AfterClass
	public static void finish(){
		selenium.stop();
	}		
}

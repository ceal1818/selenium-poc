package org.course.selenium.rc_exercise1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

@SuppressWarnings("deprecation")
public class UpdateTodoTest {

	private static final String TITLE = "Todo Test";
	private static final String DESCRIPTION = "Todo Test";
	private static final String TEXT = " UPDATED!";
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
	public void testUpdateTodo(){
		selenium.windowFocus();
		selenium.windowMaximize();
		
		selenium.click("xpath=//tbody[@id='todo-list']/tr[last()]/td[last()]/input[@id='editBtn']");
		
		String title = selenium.getValue("xpath=//input[@id='title']");
		String description = selenium.getValue("xpath=//textarea[@id='description']");
		
		Assert.assertTrue("El titulo no es igual que el esperado.", TITLE.equals(title));
		Assert.assertTrue("El descripcion no es igual que el esperado.", DESCRIPTION.equals(description));
		
		selenium.type("xpath=//input[@id='title']", TITLE+TEXT);
		selenium.type("xpath=//textarea[@id='description']", DESCRIPTION+TEXT);
		
		selenium.click("id=update");
		
		String titleResult = selenium.getText("xpath=//tbody[@id='todo-list']/tr[last()]/td");
		String updatedDateResult = selenium.getText("xpath=//tbody[@id='todo-list']/tr[last()]/td[4]");
		
		Assert.assertTrue("El titulo no es igual a "+TITLE+TEXT, (TITLE+TEXT).equals(titleResult));
		Assert.assertTrue("El fecha de modificación esta vacía.", !("".equals(updatedDateResult)));
	}
	
	@AfterClass
	public static void finish(){
		selenium.stop();
	}		
}

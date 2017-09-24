package org.course.selenium.rc_exercise1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

@SuppressWarnings("deprecation")
public class EntryApplicationTest {

	private final static String TITLE = "TODO Web Application";
	private static Selenium selenium;
	
	@BeforeClass
	public static void init(){
		selenium = new DefaultSelenium("localhost", 4444, 
				"*firefox", "http://localhost:3000");
		
		selenium.start();
	}
	
	@Test
	public void testEntryApplication() throws Exception{
		selenium.open("/");
		selenium.windowFocus();
		selenium.windowMaximize();
		selenium.setSpeed("1000");
		
		String title = selenium.getTitle();
		String h1 = selenium.getText("xpath=//h1");
		
		Assert.assertTrue(TITLE.equals(h1));
		Assert.assertTrue(TITLE.equals(title));
	}
	
	@AfterClass
	public static void finish(){
		selenium.stop();
	}
}

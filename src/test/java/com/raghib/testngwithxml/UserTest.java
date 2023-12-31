package com.raghib.testngwithxml;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.raghib.selenium.BaseClass;

public class UserTest extends BaseClass {

	public static String browserName = "chrome";
	public static String browserVersion = "116";
	
	public static String url = "https://www.google.com/";
	
	WebDriver webDriverObj = null;
	WebDriverWait webDriverWaitObj = null;
	WebElement webElementObj = null;
	String expectedTitle = "Google";

	@Test
	public void f() {
		String currentTitle = webDriverObj.getTitle();
		System.out.println("currentTitle : " + currentTitle);
		Assert.assertEquals(currentTitle, expectedTitle);
	}

	@BeforeTest
	public void beforeTest() {
		// Chrome Browser
		webDriverObj = BaseClass.getDriver(browserName, browserVersion);
		webDriverObj.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		webDriverObj.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		webDriverObj.manage().window().maximize();
		webDriverObj.manage().deleteAllCookies();
		webDriverObj.get(url);

		// Refresh the browser.
		webDriverObj.navigate().refresh();
	}

	@AfterTest
	public void afterTest() {
		try {
			if (webDriverObj != null) {
				System.out.println("Driver Need to Close");
			} else {
				System.out.println("Driver Still Open");
			}
		} catch (Exception e) {
			System.out.println("Nothing to do with it");
		} finally {
			System.out.println("Finally Block - To close the driver");
			BaseClass.quitDriver();
		}
	}
}
package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo extends TestBase {
	/**
	 * WebDriver can be set by all kinds of browsers. Wait ten seconds for the
	 * page to load.
	 */
	@BeforeClass
	public void setUp() {
		driver = getDriver("chrome");
		driver.get("https://react-redux-registration-login-example.stackblitz.io/login");// 打开指定的网站
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * Implement tests for registration
	 */
	@Test
	public void testRegistration() {
		driver.findElement(By.linkText("Register")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.name("firstName")).sendKeys("Penny");
		driver.findElement(By.name("lastName")).sendKeys("Liu");
		driver.findElement(By.name("username")).sendKeys("pennyliu");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(
				By.xpath("/html/body/div[1]/div/div/div/div/div/form/div[5]/button"))
				.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/**
	 * 1.login using the correct credentials 
	 * 2.check if the user’s first name is correctly displayed once logged in (i.e. Hi FirstName!) and the 
	 * 3.check if their full name appears under the “All registered users” list.
	 */
	@Test(dependsOnMethods = "testRegistration")
	public void testLogin() {
		driver.findElement(
				By.xpath("/html/body/div[1]/div/div/div/div[2]/div/form/div[1]/input"))
				.sendKeys("pennyliu");
		driver.findElement(
				By.xpath("/html/body/div[1]/div/div/div/div[2]/div/form/div[2]/input"))
				.sendKeys("123456");
		driver.findElement(
				By.xpath("/html/body/div[1]/div/div/div/div[2]/div/form/div[3]/button"))
				.click();
		String strHello = driver.findElement(
				By.xpath("/html/body/div[1]/div/div/div/div/div/h1")).getText();
		String strResUser = driver.findElement(
				By.xpath("/html/body/div[1]/div/div/div/div/div/ul/li[1]"))
				.getText();

		Assert.assertEquals(strHello, "Hi Penny!");// check if the user’s first name is correctly displayed
		Assert.assertTrue(strResUser.contains("Penny Liu"));// check if their full name appears
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
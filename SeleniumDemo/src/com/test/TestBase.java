package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase {
	protected WebDriver driver = null;

	/**
	 * get webDriver from Native browser
	 * 
	 * @param browserType
	 * @return
	 */
	public WebDriver getDriver(String browserType) {
		System.out.println("getDriver");
		WebDriver driver = null;
		if (browserType.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					".\\config\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserType.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserType.equals("iexplorer")) {

			System.setProperty("webdriver.ie.driver",
					".\\config\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;
	}

}

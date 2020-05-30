package com.ParametersBrowsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenBrowsers {
	public WebDriver driver;

	@BeforeTest
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.operadriver().setup();
		WebDriverManager.phantomjs().setup();
		WebDriverManager.edgedriver().setup();
		WebDriverManager.iedriver().setup();
	}

	@BeforeMethod
	public void setupTest() {
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver = new ChromeDriver();
	}

	@Test
	@Parameters({ "browsers" })
	public void openBrowsers(@Optional("default") String Browsers) {
		if (Browsers.equals("FireFox")) {
			driver.get("https://www.google.com/");
			System.out.println(driver.getTitle());
		}
	}

}

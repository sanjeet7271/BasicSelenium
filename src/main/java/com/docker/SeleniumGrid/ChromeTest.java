package com.docker.SeleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeTest {
	@Test
	public void Testcase() throws MalformedURLException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		DesiredCapabilities cap=new DesiredCapabilities();
		//cap.setBrowserName(BrowserType.FIREFOX);
		cap.setBrowserName(BrowserType.CHROME);
		WebDriver driver=new RemoteWebDriver(new URL("http://localhost:4546/wd/hub"),cap);
		
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("toolsQa");
		Thread.sleep(5000);
		driver.quit();
	}
}


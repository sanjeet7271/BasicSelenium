package com.basicSelenium;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


public class ChromeBrowser {
	@Test
	public void  openChromeBrowser() throws InterruptedException{
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("safebrowsing.enabled", "false"); 
        options.setExperimentalOption("prefs", prefs); 
		options.addArguments("--disable-extensions");
		options.addArguments("--incognito");
		options.addArguments("--disable-infobars");
		options.addArguments("--test-type");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-plugins");
		options.addArguments("--no-sandbox");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		@SuppressWarnings("deprecation")
		WebDriver driver=new ChromeDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.hotstar.com/in");
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		int last_height = (int) js.executeScript("return document.body.scrollHeight");
		System.out.println(last_height);
		//js.executeScript("window.scrollBy(0,document.body.scrollheight)");
		while(true) {
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(1000);
		}
		
		
	}
}

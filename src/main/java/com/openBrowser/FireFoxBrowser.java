package com.openBrowser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


public class FireFoxBrowser {
	WebDriver driver;
	@Test
	public void openFireFox() {
	
		SetLocal();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");

	}
	
	@SuppressWarnings("deprecation")
	public void SetLocal() {
		DesiredCapabilities firefoxcapability=new DesiredCapabilities();
		firefoxcapability=getFireFoxCapabilities(firefoxcapability);
		driver = new FirefoxDriver(firefoxcapability);
	}
	
	public DesiredCapabilities getFireFoxCapabilities(DesiredCapabilities fireFoxcapabilities) {
		System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/geckodriver.exe");
		FirefoxProfile profile=new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		fireFoxcapabilities.setBrowserName("firefox");
		fireFoxcapabilities.setCapability("marionette", true);
		fireFoxcapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		profile.setPreference("browser.private.browsing.autostart", true);
		fireFoxcapabilities.setCapability(FirefoxDriver.PROFILE, profile);
		return fireFoxcapabilities;
		
	}
}

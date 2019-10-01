package com.openBrowser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class IEBrowser {
	WebDriver driver;
	@Test
	public void openIEBrowser() {
		setLocal();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");

	}
	
	public void setLocal() {
		DesiredCapabilities capability=new DesiredCapabilities();
		capability=getIECapability(capability);
		driver=new InternetExplorerDriver();
	}

	private DesiredCapabilities getIECapability(DesiredCapabilities ieCapability) {
		System.setProperty("webdriver.ie.driver", "./src/main/resources/drivers/IEDriverServer.exe");
		
		ieCapability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCapability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		ieCapability.setCapability("ignoreProtectedModeSettings", true);
		ieCapability.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());

		ieCapability.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);

		ieCapability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		// Accept unexpected alerts
		ieCapability.setCapability("requireWindowFocus", true);
		ieCapability.setCapability("enablePersistentHover", false);
		ieCapability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		ieCapability.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		ieCapability.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
		ieCapability.setCapability("disable-popup-blocking", true);
		ieCapability.setJavascriptEnabled(true);
		return ieCapability;
	}

}

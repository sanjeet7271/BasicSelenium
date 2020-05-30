package com.basicSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import io.github.bonigarcia.wdm.WebDriverManager;


public class FirefoxProfile1 {

	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/geckodriver.exe");
		 //WebDriverManager.chromedriver().setup();
		ProfilesIni prof=new ProfilesIni();
		FirefoxProfile fire=prof.getProfile("abc");
		//WebDriver driver=new ChromeDriver(fire);
		 FirefoxOptions options=new FirefoxOptions().setProfile(fire);
		FirefoxDriver driver=new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		
		
	}

}

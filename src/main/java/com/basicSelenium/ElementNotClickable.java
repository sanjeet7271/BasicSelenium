package com.basicSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementNotClickable {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");

		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://ksrtc.in/oprs-web/");
		
		driver.findElement(By.xpath(".//button[@class='btn btn-primary btn-lg btn-block btn-booking']")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[@class='btn btn-primary btn-lg btn-block btn-booking")));
		
	}

}

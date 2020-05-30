package com.ninetex;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

class Amazon {

	private WebDriver driver;

	@BeforeTest
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();

	}

	@BeforeMethod
	public void setupTest() {
		driver = new ChromeDriver();
	}

	@Test
	public void test() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		
		System.out.println("switch back to main page");
		Thread.sleep(500);
		Actions act=new Actions(driver);
		WebElement element=driver.findElement(By.xpath(".//div[@id='nav-tools']//a[@id='nav-link-accountList']"));
		act.moveToElement(element).build().perform();
		driver.findElement(By.xpath(".//div[@id='nav-flyout-ya-signin']//span[@class='nav-action-inner']")).click();;
		Thread.sleep(500);
		driver.findElement(By.xpath(".//input[@id='ap_email']")).sendKeys("abc@gmail.com");
		Thread.sleep(500);
		driver.findElement(By.xpath(".//input[@id='continue']")).click();;
	}

	@AfterTest
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

class TestDriverManager3 {

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
		driver.get("https://www.nintex.com");
		// System.out.println(driver.getTitle());
		/*driver.switchTo().frame("drift-widget");
		System.out.println("switched to frame");
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/div/span/div/div[1]/button/img")).click();
		System.out.println("closed pop up");
		// Thread.sleep(500);
		driver.switchTo().defaultContent();*/
		System.out.println("switch back to main page");
		Thread.sleep(500);
		driver.findElement(By.xpath(".//div[@class='nintex-search-toggle']")).click();

		driver.findElement(By.xpath(
				".//div[@class='nintex-search nintex-search--knockout nintex-search--on-dark nintex-search--large']//input[@id='keyword-input']"))
				.sendKeys("automation");
		Thread.sleep(500);
		driver.findElement(By.xpath(".//div[@class='nintex-search nintex-search--knockout nintex-search--on-dark nintex-search--large nintex-search--active']//button[@class='nintex-search__button']")).click();
		Thread.sleep(500);
	}

	@AfterTest
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
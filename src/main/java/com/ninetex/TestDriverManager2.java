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

class TestDriverManager2 {

	private WebDriver driver;

	@BeforeTest
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
		/*
		 * WebDriverManager.firefoxdriver().setup();
		 * WebDriverManager.operadriver().setup(); WebDriverManager.phantomjs().setup();
		 * WebDriverManager.edgedriver().setup(); WebDriverManager.iedriver().setup();
		 */
	}

	@BeforeMethod
	public void setupTest() {
		driver = new ChromeDriver();
		// driver=new FirefoxDriver();
	}

	@Test
	public void test() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.nintex.com");
		System.out.println(driver.getTitle());
		Thread.sleep(500);
		driver.findElement(By.xpath(".//div[@class='nintex-search-toggle']//img[@id='search-mag-glass']")).click();
		//element.click();
		/*driver.switchTo().frame("drift-widget");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/span/div/div[1]/button/img")).click();
		//Thread.sleep(500);
		driver.switchTo().defaultContent();*/
		/*WebElement element = driver.findElement(By.xpath(".//div[@class='nintex-search-toggle']//img[@id='search-mag-glass']"));
		element.click();*/
		/*Thread.sleep(500);
		 Set<String> windows=driver.getWindowHandles();
	       List<String> tabs=new ArrayList<>(windows);
	       System.out.println(tabs.size());   */  
		/*WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				".//div[@class='nintex-search nintex-search--knockout nintex-search--on-dark nintex-search--large']//label[@class='screen-reader-text']")));
		driver.findElement(By.xpath(
				".//div[@class='nintex-search nintex-search--knockout nintex-search--on-dark nintex-search--large']//label[@class='screen-reader-text']"))
				.sendKeys("automation");*/
		Thread.sleep(500);
	}

	@AfterTest
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
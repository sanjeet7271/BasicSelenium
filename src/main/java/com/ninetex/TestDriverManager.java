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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

class TestDriverManager {

	 private WebDriver driver;

	    @BeforeTest
	    public static void setupClass() {
	        WebDriverManager.chromedriver().setup();
	        /*WebDriverManager.firefoxdriver().setup();
	        WebDriverManager.operadriver().setup();
	        WebDriverManager.phantomjs().setup();
	        WebDriverManager.edgedriver().setup();
	        WebDriverManager.iedriver().setup();*/
	    }

	    @BeforeMethod
	    public void setupTest() {
	        driver = new ChromeDriver();
	    	//driver=new FirefoxDriver();
	    }
	    @Test
	    public void test() throws InterruptedException {
	    	driver.manage().window().maximize();
	    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        driver.get("https://www.nintex.com");
	        System.out.println(driver.getTitle());
	        Actions act=new Actions(driver);
	        WebElement element=driver.findElement(By.xpath(".//div[@class='menu-language-selector-container']//ul[@id='language-menu']//li"));
	        act.moveToElement(element).build().perform();
	        Thread.sleep(5000);
	        
	        act.sendKeys(driver.findElement(By.xpath(".//ul[@class='sub-menu']/li//a[contains(@title,'Japanese')]")), Keys.CONTROL).sendKeys(driver.findElement(By.xpath(".//ul[@class='sub-menu']/li//a[contains(@title,'Japanese')]")),Keys.NUMPAD2).build().perform();
	        
	        //driver.switchTo().window(tabs.get(1)); 
	        Set<String> windows=driver.getWindowHandles();
	        List<String> tabs=new ArrayList<>(windows);
	        driver.switchTo().window(tabs.get(1)); 
	        System.out.println(tabs.size());
	        String title=driver.getTitle();
	        //driver.close();
	        System.out.println(title);
	        Thread.sleep(5000);
	        Actions act1=new Actions(driver);
	        WebElement element1=driver.findElement(By.xpath(".//div[@class='menu-language-selector-japanese-container']//ul[@id='language-menu']//li"));
	        act.moveToElement(element1).build().perform();
	        Thread.sleep(5000);
	        driver.close();
	        driver.switchTo().window(tabs.get(0));
	        Thread.sleep(5000);
	        driver.close();
	       /* act1.sendKeys(driver.findElement(By.xpath(".//ul[@class='sub-menu']/li//a[contains(@title,'Japanese')]")), Keys.CONTROL).sendKeys(driver.findElement(By.xpath(".//ul[@class='sub-menu']/li//a[contains(@title,'Japanese')]")),Keys.NUMPAD2).build().perform();
	        String title1=driver.getTitle();*/

	        //act.sendKeys(driver.findElement(By.tagName(".//ul[@class='sub-menu']/li//a[contains(@title,'Japanese')]")), Keys.CONTROL).sendKeys(driver.findElement(By.tagName(".//ul[@class='sub-menu']/li//a[contains(@title,'Japanese')]")),Keys.NUMPAD1).build().perform();
	        
	        
	    }
	    @AfterTest
	    public void teardown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	  

	}
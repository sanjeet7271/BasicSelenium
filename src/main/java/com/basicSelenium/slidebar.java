package com.basicSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class slidebar {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		/*	driver.get("https://jqueryui.com/slider/");

		driver.switchTo().frame(0); // need to switch to this frame before clicking the slider
		WebElement slider = driver.findElement(By.xpath("//div[@id='slider']/span"));
		Actions builder = new Actions(driver);
		int iCount = 0;
		int iRange=20;
		if (iRange > 0) {
			for (iCount = 0; iCount < iRange; iCount++) {
				builder.moveToElement(slider).click(slider).sendKeys(Keys.ARROW_UP).perform();
			}
		} else {
			for (iCount = 0; iCount > iRange; iCount--) {
				builder.click(slider).sendKeys(Keys.ARROW_DOWN).perform();
			}
		}*/
		
		
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a[@class='sf-with-ul']"));
		/*Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
		Thread.sleep(5000);	
		WebElement ele1=driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a[contains(text(),'T-shirts')]"));
		action.moveToElement(ele1).perform();*/
		ele.click();
		
		WebElement slider = driver.findElement(By.xpath(".//a[@class='ui-slider-handle ui-state-default ui-corner-all'][1]"));
		Actions builder = new Actions(driver);
		int iCount = 0;
		int iRange=100;
		if (iRange > 0) {
			for (iCount = 0; iCount < iRange; iCount++) {
				builder.moveToElement(slider).click(slider).sendKeys(Keys.ARROW_UP).perform();
			}
		} else {
			for (iCount = 0; iCount > iRange; iCount--) {
				builder.click(slider).sendKeys(Keys.ARROW_DOWN).perform();
			}
		}
		
		
	}

}

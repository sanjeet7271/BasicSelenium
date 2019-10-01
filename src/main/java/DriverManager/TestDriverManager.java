package DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
	        WebDriverManager.firefoxdriver().setup();
	        WebDriverManager.operadriver().setup();
	        WebDriverManager.phantomjs().setup();
	        WebDriverManager.edgedriver().setup();
	        WebDriverManager.iedriver().setup();
	    }

	    @BeforeMethod
	    public void setupTest() {
	       // driver = new ChromeDriver();
	    	driver=new FirefoxDriver();
	    }
	    @Test
	    public void test() {
	        driver.get("https://www.google.com/");
	        System.out.println(driver.getTitle());
	    }
	    @AfterTest
	    public void teardown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	  

	}
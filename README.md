# Basic Selenium and Its terms 
Leaning Basic Selenium using Java , WebDriver, TestNG

# Starting Selenium
   ### Interfaces in selenium:
                i)  SearchContext Interface
                ii) WebDriver
            => SearchContext is the parent interface of WebDriver    
            => only FindElement() and FindElements() methods are present in SearchContext
 
   ### Locators in Selenium
             i) ID
             ii) name
             iii) className
             iv) tagName
             v) LinkText
             vi) PartialLinkText
             vii) Css Selectors
             viii) Xpath
     
   ### About FindElement() and FindElements() Methods
            i) FindElement() : 
                  -> Use this method to access any single element on the web page.
                  -> It returns the first matching element of the specified locator.
                  -> It throws a NoSuchElementException exception when it fails to locate the element( when an element is not there).
                  -> You cannot get other than first element using findElement()
                  -> Implicitly Wait applicable on findElement()
                  -> If you are in page level, findElement() will look for the element in page level; it will not look for the element which is present inside a Frame and vice versa
                  Syntax :
                        WebElement example = driver.findElement(By.locator("locator"));
            ii) FindElements() : 
               ->It gives back the whole list of all(not all but all the elements which are loaded by that time) the elements matching the specified locator.
               -> If the element doesn’t exist or not available on the page, then the return value will be an empty list.
               -> It will not throw 'NoSuchElementFoundException' in case element is not present
               -> User will be allowed to perform only list operations on the return value; we have to iterate through each item to perform webelement operations
               -> implicitlyWait applicable on findElements on a special occasion, please visit implicitlyWait for more details
               -> If you are in page level, findElements() will look for the element in page level; it will not look for the element which is present inside a Frame and vice versa
               
               Syntax :
                        List<WebElement> elems = driver.findElements(By.locator("locator"));

### Waits in Selenium
      i) Timeouts interface manages all the waits of the driver instances. This is the inner Interface of Webdriver Interface
      ii) Timeouts interface has three abstract methods, which are :

            1) implicitlyWait
            2) setScriptTimeout
            3) pageLoadTimeout
 ### Page Load Timeout in Selenium Webdriver
         -> Page load timeout in selenium requests/set the time limit for a page to load, If the page is not loaded within the given time frame selenium throws TimeOutException.
         
         Syntax :
                  driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                  
         * Page Load timeout is applicable only to driver.get() and driver.navigate().to() methods in selenium
         * Setting Negative time limit makes the selenium to wait for the page load infinitely
               driver.manage().timeouts().pageLoadTimeout(-10, TimeUnit.SECONDS);
         * Page load timeout is not applicable when the user clicks a link to open a page.
### Set Script Load timeout
      -> setScriptTimeout sets the time limit for the asynchronous script to finish execution, if the process is not completed before the time limit, selenium throws TimeOutException
      Question : why do you want to set a timeout for executeAsyncScript?
      Answer : The default timeout for setScriptTimeout method is 0 (zero) if we do not set any time our executeAsyncScript method may fail because the JavaScript code may take more than zero seconds. So to avoid unnecessary failures, we have to set the setScriptTimeout.
   
      Syntax : 
         driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
         ((JavascriptExecutor) driver).executeAsyncScript("document.getElementById('dummy')");

   ### Implicitly Wait
         * Implicitly Wait is applicable only to findElement and findElements no other statement in selenium.
         * By default Implicitly Wait is Zero.
         
         Syntax :
                  WebDriver driver = new ChromeDriver();
                  // set implicit wait tme as 30 Seconds
                  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                  
                  
  ### Explicit Wait
         * The explicit wait is used to tell the Web Driver to wait for certain conditions or the maximum time limit before throwing an Exception.
         * 
         
         Syntax : 
                     WebDriverWait wait=new WebDriverWait( driver, timeoutInSeconds);
                     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("xpath")));
 ### Fluent Wait
         
         Syntax :
                  FluentWait fw = new FluentWait(driver);
                  // max time limit is 30 seconds
                  fw.withTimeout(30, TimeUnit.SECONDS);
                  fw.pollingEvery(5, TimeUnit.SECONDS);
                  fw.ignoring(NoSuchElementException.class);
                  
                  
                  
 ### Take Screenshot in Selenium
         * here is an interface called TakesScreenshot which provides the getScreenshotAs() method and which selenium uses to take a screenshot.
         
         
         Syntax :
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("D:pageScreenshot.png"), true); 
   
        
 ### Kill browser session ID for different browser
         * Run following t\command to kill current seesion   ie. browser id 
             for Internet Explorer --    taskkill /f /fi "pid gt 0" /im iexplore.exe
             for chrome driver ----  taskkill /f /fi "pid gt 0" /im chromedriver.exe
        
        
** Q. How to use Local jar in maven dependency from system?**<br>
**Answer:** <br>
         ![image](https://user-images.githubusercontent.com/45819133/133749002-03206cd7-2d1f-4341-8aa4-594c6f1e0619.png)

Group id: will identify your project across all projects(com.test.selenium)
Artifact id: project name
Project:  Its root node of pom.xml
Scope: defines scope for this maven project. It can be compiled, provided, runtime, test and system
Packaging: defines packaging such as jar, war etc.
### **Q. Where all jar file downloads in the local system?** <br>
Ans: 	.m2 folder
       
**Q. How does WebDriver Internally work?** <br>
**A.**  There are four basic components of WebDriver Architecture: <br>
      - Selenium client Libraries <br>
      - JSON Wire Protocol over http <br>
      - Browser Drivers <br>
      - Browsers <br>

![Selenium-WebDriver-Architecture](https://user-images.githubusercontent.com/45819133/197811998-8e32e2f7-99f1-4c45-8bfb-012be78311df.jpg)

JSON Wire Protocol over http

	- JSON Wire Protocol does  all the communication between the browser and the code. 
	- JSON is an open standard for exchanging data on web and It supports data structures like object and array. so it become easy to write and read data from JSON.
	- Data is transferred using a RESTful (Representational State Transfer)  API  using  JSON over HTTP.
	- GET and POST APIs call happens throught JSON wire protocol.

        
        
### SOME POINTS ABOUT SEARCHCONTEXT: <br>
		=> SearchContext is the super interface of the Webdriver.
		=> Two subinterface Webdriver and webelement
		=> SearchContext is having only 2 methods like FindElement() and FinsElements()


### Selenium Code Snippet: <br>
 **1. Scroll Down by javascriptExecutor by defining number of Pixels:**<br>
				 @Test
				 public void scrollDown()
				         {
				          System.setProperty("webdriver.gecko.driver","D://Selenium    Environment//Drivers//geckodriver.exe");
				            WebDriver driver = new FirefoxDriver();
				            driver.navigate().to("Website URL");
				
				            //to perform Scroll on application using Selenium
				            JavascriptExecutor js = (JavascriptExecutor) driver;
				            js.executeScript("window.scrollBy(0,350)", "");
				         }
				}
**2. Scroll Up by javascriptExecutor by defining number of Pixels: **<br>

			    @Test
			     public void scrollDown()  {
			      System.setProperty("webdriver.gecko.driver","D://Selenium Environment//Drivers//geckodriver.exe");
			
			      WebDriver driver = new FirefoxDriver();
			      driver.navigate().to("Website URL"); // Specify the website URL
			
			       //to perform Scroll on application using Selenium
			       JavascriptExecutor js = (JavascriptExecutor) driver;
			       js.executeScript("window.scrollBy(0,-350)", "");
			   }
**3. Scroll down to an element in Selenium until it is visible**<br>
   
			public class ScrollByVisibleElement {
				    WebDriver driver;
				    @Test
				    public void ByVisibleElement() {
				        System.setProperty("webdriver.gecko.driver","D://Selenium Environment//Drivers//geckodriver.exe"); 
				        WebDriver driver = new FirefoxDriver();
				        JavascriptExecutor js = (JavascriptExecutor) driver;
				
				        //Launch the application		
				        driver.get("https://www.browserstack.com/guide/selenium-scroll-tutorial");
				
				        //Locating element by link text and store in variable "Element"        		
				        WebElement Element = driver.findElement(By.linkText("Try Selenium Testing For Free"));
				
				        // Scrolling down the page till the element is found		
				        js.executeScript("arguments[0].scrollIntoView();", Element);
				    }
			}
**4. Scroll down to the bottom of the webpage**<br>
   
			public class HandleScroll {
					  @Test
					  public void scrollDown()  {
					
					   System.setProperty("webdriver.gecko.driver","D://Selenium Environment//Drivers//geckodriver.exe");
					
					   WebDriver driver = new FirefoxDriver();
					   driver.navigate().to("Website URL"); // Specify the Website URL
					
					   //to perform scroll on an application using Selenium
					
					   JavascriptExecutor js = (JavascriptExecutor) driver;
					   js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
					
					  }
			}




**How to input text in the text box using Selenium WebDriver without sendKeys?**<br>

			// To initialize js object
			JavascriptExecutor JS = (JavascriptExecutor)webdriver;
			// To enter username
			JS.executeScript("document.getElementById('User').value='SoftwareTestingMaterial.com'");
			// To enter password
			JS.executeScript("document.getElementById('Pass').value='tester'");

## Difference between selenium 3 and selenium 4
		Architecture: Selenium 3 uses JSON Wire Protocol for communication, while Selenium 4 uses WebDriver W3C protocol for direct communication between client and server.
		Chrome Driver: In Selenium 3, Chrome Driver extends Remote WebDriver, whereas in Selenium 4, it extends Chrome Driver itself.
		Communication: Selenium 3 uses JSON Wire Protocol for communication, whereas Selenium 4 uses WebDriver W3C protocol.
		Hub & Node Jars: Selenium 3 requires starting Hub & Node Jars before running tests, whereas Selenium 4 doesn't.
		Features: Selenium 4 offers new features like Relative Locators, improved DevTools API support, and Reactive WebDriver.
![image](https://github.com/sanjeet7271/BasicSelenium/assets/45819133/9b2d71e0-4e6b-4ffe-a3d2-fedcc0e488fd)

## Alerts in Selenium: Alert is an interface
	1. Dismiss the Alert: driver.switchTo().alert().dismiss();
 	2. Accept the Alert: driver.switchTo().alert().dismiss();
  	3. Send text into the alert box: driver.switchTo().alert().sendKeys(""hello world);
   	4. Get text from alert: driver.switchTo().alert().getText();
  
## iFrames
	iframes: we can switch frames using 3 ways
 		1. using Webelement : 
   			WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));
      			driver.switchTo().frame(iframe);
	 	2. using name or ID:
   				driver.switchTo().frame("buttonframe");
       		3. Using an index
	 		driver.switchTo().frame(1);
    Switch back to parent iframe: driver.switchTo().defaultContent();
       

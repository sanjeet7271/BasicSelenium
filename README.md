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

            
        

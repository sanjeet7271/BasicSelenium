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
            i) FindElement() : property
                  -> Use this method to access any single element on the web page.
                  -> It returns the first matching element of the specified locator.
                  -> It throws a NoSuchElementException exception when it fails to locate the element( when an element is not there).
                  -> You cannot get other than first element using findElement()
                  -> Implicitly Wait applicable on findElement()
                  -> If you are in page level, findElement() will look for the element in page level; it will not look for the element which is present inside a Frame and vice versa
                  Syntax :
                        WebElement example = driver.findElement(By.locator("locator"));
            ii) FindElements() : Property
               ->It gives back the whole list of all(not all but all the elements which are loaded by that time) the elements matching the specified locator.
               -> If the element doesn’t exist or not available on the page, then the return value will be an empty list.
               -> It will not throw 'NoSuchElementFoundException' in case element is not present
               -> User will be allowed to perform only list operations on the return value; we have to iterate through each item to perform webelement operations
               -> implicitlyWait applicable on findElements on a special occasion, please visit implicitlyWait for more details
               -> If you are in page level, findElements() will look for the element in page level; it will not look for the element which is present inside a Frame and vice versa
               
               Syntax :
                        List<WebElement> elems = driver.findElements(By.locator("locator"));

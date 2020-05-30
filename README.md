# BasicSelenium
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
                  

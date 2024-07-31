## Types of Framework in Selenium
  Selenium frameworks can be classified into three main types based on the functional approach:
    1. Data-driven framework
    2. Keyword-driven framework
    3. Hybrid framework -  Combines elements of both the keyword-driven and data-driven frameworks.

## Thread Guard Class in Selenium
  1.) When we run our test cases in parallel using TestNg in selenium, you might have faced issues like some environmental, or some unknown issues halting the parallel execution.<br>
  2.) Parallel execution is nothing but running the selenium in multiple threads, when one thread interrupts the other thread then JVM throws exceptions.<br>
  3.) Like webdriver listener we have to wrap the webdriver instance using the ThreadGuard class, for that purpose we a method called protect()in ThreadGuard class.<br>
  Example :
    `WebDriver driver = ThreadGuard.protect(new FirefoxDriver());`
    
## Switch tabs using Selenium with Java

    #Switch tabs between only 2 tabs
          String parentTab=driver.getWindowHandle();
          ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
          if(parentTab.equals(tabs.get(0)){
            driver.SwitchTo().Window(tabs.get(1));
          }else{
              driver.SwitchTo().Window(tabs.get(0));
          }
    #Switch tabs between more than 2 tabs
        String parentTab=driver.getWindowHandle();
        ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
        for(String tab:tabs){
            driver.SwitchTo().window(tab);
            if(driver.getTitle().equals("topics")){
               breaks;
            }
            
        }

## Thread Guard Class in Selenium
  1.) When we run our test cases in parallel using TestNg in selenium, you might have faced issues like some environmental, or some unknown issues halting the parallel execution.
  2.) Parallel execution is nothing but running the selenium in multiple threads, when one thread interrupts the other thread then JVM throws exceptions.
  3.) Like webdriver listener we have to wrap the webdriver instance using the ThreadGuard class, for that purpose we a method called protect()in ThreadGuard class.
  Example :
  'WebDriver driver = ThreadGuard.protect(new FirefoxDriver());'
  

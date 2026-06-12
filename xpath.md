# Type of xpath
  1. Absolute XPath: An Absolute XPath specifies the complete path from the root element (html) to the target element.<br>
       Example:<br>
             /html/body/div/form/input
  2. Relative XPath: A Relative XPath starts searching from anywhere in the DOM instead of from the root (html). It usually begins with // and is the most commonly used XPath type in Selenium automation.<br>
       Example:<br>
               //input[@id='username']

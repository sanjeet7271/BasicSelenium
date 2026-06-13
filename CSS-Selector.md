# CSS-Selectors
## In Selenium, CSS selectors are commonly used with findElement() and findElements() to locate web elements.
    - By ID: Represented by #
          <input id="username">
          findElement(By.CssSelector("#username"))
          
    - By Class Name: Represented by .
          <input class="input-field">
          findElement(By.CssSelector(".input-field"));
      
    - By Tag Name
          <button>Login</button>
          findElelemt(By.Selector("button"));
      
    - By Attribute
          <input type="text" name="email">
          findElement(By.CSSSeector("input[type='text']"));
      or  findElemet(By.CSSSelector(input[name='email']))

    - Multiple Attributes
          <input type="text" name="email" placeholder="Enter Email">
          driver.findElement(
              By.cssSelector("input[type='text'][name='email']")
          );
    - Parent → Child (>)
        <div class="form">
            <input id="username">
        </div>
        findElement(By.CSSSelector("div.form > input.username")

    - Descendant (space)
          <div class="container">
              <form>
                  <input id="username">
              </form>
          </div>
          findElement(By.CSSSelector("div.container input"));

    - Contains Attribute Value
          <input id="user_123">
          driver.findElement(By.cssSelector("input[id*='user']"));

## Operators
        | Operator | Meaning     |
        | -------- | ----------- |
        | `*=`     | Contains    |
        | `^=`     | Starts with |
        | `$=`     | Ends with   |

        driver.findElement(By.cssSelector("input[id*='user']"));
        By.cssSelector("input[id^='user']")
        By.cssSelector("input[id$='123']")

  ## nth-child
          <ul>
             <li>One</li>
             <li>Two</li>
             <li>Three</li>
          </ul>
          Selects "Two": driver.findElement(By.cssSelector("li:nth-child(2)"));

  ## Combining Selectors
        <input id="username" class="form-control">
        driver.findElement(
            By.cssSelector("input.form-control#username")
        );

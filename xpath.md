# Type of xpath
  1. Absolute XPath: An Absolute XPath specifies the complete path from the root element (html) to the target element.<br>
       Example:<br>
                   /html/body/div/form/input
  2. Relative XPath: A Relative XPath starts searching from anywhere in the DOM instead of from the root (html). It usually begins with // and is the most commonly used XPath type in Selenium automation.

# Common Relative XPath Patterns
    1. Using Attribute
    //button[@id='loginBtn']
    2. Using Text
    //button[text()='Login']
    3. Using Contains
    //input[contains(@id,'user')]
    4. Using Starts-With
    //input[starts-with(@id,'user')]
    5. Using Multiple Conditions
    //input[@type='text' and @name='username']

# XPaths Relations
  - Parent Relation
  - Child Relation
  - Ancestor Relation
  - Descendant Relation
  - Following-Sibling Relation
  - Preceding-Sibling Relation
  - Following Relation
  - Preceding Relation
  - Self Relation
  - Attribute Relation

# DOM Relationship Summary
                            Grandparent (Ancestor)
                                    |
                                 Parent
                                    |
                                 Current (Self)
                                  /    \
                             Child   Following-Sibling
                                        |
                                 Preceding-Sibling
                            
                            Ancestor ← Parent ← Self → Child → Descendant
                            
                            Preceding ← Self → Following

# HTML
                    <div class="container">
                        <form>
                            <label>Email</label>
                            <input type="text" id="email">
                    
                            <label>Password</label>
                            <input type="password" id="pwd">
                    
                            <button>Login</button>
                        </form>
                    </div>
## 1. Parent Relation: With the above HTML DOM, find the parent
              //input[@id='email']/parent::form
## 2. Child Relation: With the above HTML DOM, find the child
            //form/child::input[@id='pwd']
## 3. Ancestor Relation: Find any parent, grandparent, great-grandparent, etc.
            //input[@id='email']/ancestor::div
## 4. Descendant Relation: Find any child, grandchild, etc.
          //div[@class='container']/descendant::input[@id='pwd']
## 5. Following-Sibling Relation: Find siblings after the current element.
          //input[@id='email']/following-sibling::label[text()='password']
## 6. Preceding-Sibling Relation: Find siblings before the current element.
          //input[@id='email']/preceeding-sibling:label
## 7. Following Relation: Find any element appearing after the current element in the document.
          //label[text()='Email']/following::button
## 8. Preceding Relation: Find any element before the current element.
          //button[text()='Login']/preceding::input
## 9. Self Relation: Refers to the current element itself.
          //input[@id='email']/self::input

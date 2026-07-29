# Selenium Grid 4 – Async Architecture

## 1. Overview

Selenium Grid 4 uses a **distributed, event-driven architecture**.

The major components are:

1. Router
2. New Session Queue
3. Distributor
4. Node
5. Session Map
6. Event Bus

A simplified architecture:

```text
                 Test / Selenium Client
                         |
                         | HTTP
                         v
                 +----------------+
                 |     ROUTER     |
                 +----------------+
                    |          |
           New Session       Existing Session
                    |          |
                    v          v
             +-----------+  +-------------+
             |  Session  |  |   Session   |
             |   Queue   |  |     Map     |
             +-----------+  +-------------+
                    |
                    | HTTP
                    v
             +-------------+
             | DISTRIBUTOR |
             +-------------+
                    |
                    | HTTP
                    v
          +----------------------+
          |        NODE          |
          | Chrome / Firefox     |
          | Edge / etc.          |
          +----------------------+

       <------ ASYNC EVENT BUS ------>
       Node <-> Distributor <-> Queue
                  Session Map
```

---

## 2. What Does "Async Architecture" Mean?

Selenium Grid 4 uses a **hybrid communication model**.

### Synchronous Communication

```text
A ---> HTTP Request ---> B
A <--- HTTP Response --- B
```

The caller waits for a response.

Used for operations such as:

- WebDriver commands
- Creating a session
- Router → Node communication
- Distributor → Node communication

### Asynchronous Communication

```text
A ---> Event ---> Event Bus

                 |
          +------+------+
          |             |
          v             v
          B             C
```

The sender publishes an event and does not need to wait for every listener.

The Event Bus is used for asynchronous communication between Grid components.

---

# 3. Selenium Grid 4 Components

## 3.1 Router

The **Router** is the entry point to Selenium Grid.

Example:

```java
WebDriver driver =
    new RemoteWebDriver(
        new URL("http://grid:4444"),
        new ChromeOptions());
```

The request reaches the Router.

```text
Test
 |
 | POST /session
 |
 v
Router
```

The Router determines where the request should go.

---

## 3.2 New Session Queue

The New Session Queue stores session requests when an appropriate browser slot is not immediately available.

For example:

```text
New Session Queue

Test 1
Test 2
Test 3
Test 4
Test 5
...
```

If there are:

```text
100 tests
10 Chrome slots
```

the remaining requests can wait in the queue.

The Distributor processes requests from this queue when suitable slots become available.

---

## 3.3 Distributor

The **Distributor is one of the most important components in Grid 4**.

It maintains the Grid's view of available Nodes and slots.

Example:

```text
Node 1
 ├── Chrome
 ├── Chrome
 └── Firefox

Node 2
 ├── Chrome
 ├── Edge
 └── Firefox
```

If the test requests:

```json
{
  "browserName": "chrome"
}
```

the Distributor looks for a matching available slot.

Conceptually:

```text
Chrome available?

Node 1 → YES
Node 2 → YES
```

The Distributor selects an appropriate slot and sends the session request to the Node.

---

## 3.4 Node

A **Node** is where the actual browser session runs.

For example:

```text
Node 1
 ├── Chrome
 ├── Chrome
 └── Firefox
```

The Node communicates with the actual browser:

```text
Node
 |
 v
Chrome / Firefox / Edge
```

---

## 3.5 Session Map

Once a session is created, Grid needs to know which Node owns that session.

The Session Map maintains this relationship.

Example:

```text
Session Map

abc-123 → Node 2
xyz-456 → Node 1
pqr-789 → Node 3
```

This becomes important for subsequent WebDriver commands.

---

## 3.6 Event Bus

The Event Bus provides asynchronous communication between Grid components.

Conceptually:

```text
                  Event Bus
                     |
       +-------------+-------------+
       |             |             |
       v             v             v
     Node       Distributor     Session Map
```

Nodes can publish events such as:

- Node registration
- Node heartbeat
- Session-related events

Interested Grid components can consume those events.

---

# 4. Complete New Session Flow

Suppose the test executes:

```java
WebDriver driver =
    new RemoteWebDriver(
        new URL("http://grid:4444"),
        new ChromeOptions());
```

The requested capability is:

```text
browserName = chrome
```

### Step 1 – Request reaches Router

```text
Test
 |
 | POST /session
 v
Router
```

---

### Step 2 – Router sends request to Session Queue

```text
Router
   |
   v
New Session Queue
```

The request waits if no matching browser slot is currently available.

---

### Step 3 – Distributor processes request

```text
New Session Queue
        |
        v
Distributor
```

Distributor checks available Nodes:

```text
Node 1 → Chrome available
Node 2 → Chrome unavailable
Node 3 → Chrome available
```

It selects a suitable Node/slot.

---

### Step 4 – Distributor sends request to Node

```text
Distributor
     |
     | HTTP
     v
Node 1
     |
     v
Chrome
```

Chrome starts and the Node creates the WebDriver session.

---

### Step 5 – Session ID is created

Example:

```text
Session ID = abc-123
```

Grid stores the mapping:

```text
abc-123 → Node 1
```

inside the Session Map.

---

# 5. What Happens During Test Execution?

Suppose the test executes:

```java
driver.findElement(By.id("username"))
      .sendKeys("Sanjeet");
```

The request flows approximately like this:

```text
Test
 |
 v
Router
 |
 v
Session Map
 |
 | abc-123 → Node 1
 v
Node 1
 |
 v
Chrome
```

### Important Point

The **Distributor is not involved in every WebDriver command**.

The Router uses the Session Map to route commands to the Node that owns the session.

This avoids unnecessary coordination through the Distributor.

---

# 6. Where Does the Async Event Bus Come In?

Suppose a Node starts.

The Node can publish an event through the Event Bus.

Conceptually:

```text
Node
 |
 | NODE_REGISTERED
 v
Event Bus
 |
 +-----------> Distributor
 |
 +-----------> Other interested components
```

The Distributor can learn about the Node and update its Grid model.

---

# 7. Node Heartbeat

Nodes periodically communicate their health/status through Grid's event-driven mechanisms.

Conceptually:

```text
Node
 |
 | "I am alive"
 v
Event Bus
 |
 v
Distributor
```

The Distributor can then check the Node's status and maintain an accurate view of the Grid.

---

# 8. Why Use an Async Event Bus?

Imagine a Grid containing 100 Nodes.

A polling-based approach could require repeated checks:

```text
Distributor → Node 1: Are you alive?
Distributor → Node 2: Are you alive?
Distributor → Node 3: Are you alive?
...
Distributor → Node 100: Are you alive?
```

With an event-driven approach:

```text
Node 1 ──┐
Node 2 ──┤
Node 3 ──┼──> Event Bus
Node 4 ──┤
Node 5 ──┘
```

This helps make the architecture more:

- Decoupled
- Scalable
- Event-driven
- Efficient

---

# 9. Very Important Interview Point

Do **not** say:

> "Everything in Selenium Grid 4 is asynchronous."

That is incorrect.

A better answer is:

> **"Selenium Grid 4 uses a hybrid communication architecture. Synchronous HTTP/REST communication is used when a response is required, such as WebDriver commands and certain component interactions. The Event Bus is used for asynchronous communication such as Node registration, heartbeats, and other Grid events."**

This distinction is important in an SDET/Senior QA interview.

---

# 10. Old Grid vs Selenium Grid 4

## Selenium Grid 3 – Simplified View

```text
              HUB
               |
       +-------+-------+
       |       |       |
     Node    Node    Node
```

The Hub handled much of the coordination.

---

## Selenium Grid 4 – Distributed Architecture

```text
                  Router
                    |
             Session Queue
                    |
               Distributor
                    |
            +-------+-------+
            |               |
          Node            Node

       Session Map
            |
        Event Bus
```

Grid 4 separates responsibilities into different components.

---

# 11. Standalone Mode

In Standalone mode, the Grid components run together in one process.

```text
+--------------------------------+
|       Selenium Server          |
|                                |
| Router                         |
| Distributor                    |
| Session Map                    |
| New Session Queue              |
| Event Bus                      |
| Node                           |
+--------------------------------+
```

This is convenient for local execution.

---

# 12. Hub/Node Mode

The Hub runs the central Grid components while Nodes run separately.

```text
                 HUB
                  |
       +----------+----------+
       |          |          |
     Node       Node       Node
```

---

# 13. Fully Distributed Mode

Grid components can be distributed across multiple machines.

For example:

```text
Machine 1 → Router

Machine 2 → Distributor

Machine 3 → Session Queue

Machine 4 → Session Map

Machine 5 → Event Bus

Machine 6 → Chrome Node

Machine 7 → Firefox Node
```

This is useful for large-scale infrastructure.

---

# 14. Key Difference: Distributor vs Router

This is a common interview question.

### Router

Responsible for:

- Receiving client requests
- Routing requests
- Using Session Map for existing sessions

Think:

> **Router = Traffic Controller**

### Distributor

Responsible for:

- Finding suitable browser slots
- Matching session capabilities
- Assigning new sessions to Nodes

Think:

> **Distributor = Resource Allocator**

---

# 15. Key Difference: Distributor vs Session Map

### Distributor

Answers:

> "Which Node should receive this NEW session?"

### Session Map

Answers:

> "Which Node owns this EXISTING session?"

Example:

```text
NEW SESSION
     |
     v
Distributor
     |
     v
Node 2

EXISTING SESSION abc-123
     |
     v
Session Map
     |
     v
Node 2
```

---

# 16. Complete Architecture

A useful interview diagram:

```text
                 Selenium Test
                       |
                       | HTTP
                       v
                 +-----------+
                 |  ROUTER   |
                 +-----------+
                    |       |
          New Session       Existing Session
                    |       |
                    v       v
             +-----------+ +-------------+
             |  Session  | |   Session   |
             |   Queue   | |     Map     |
             +-----------+ +-------------+
                    |
                    v
             +-------------+
             | DISTRIBUTOR |
             +-------------+
                    |
              Finds matching
                 browser
                    |
                    v
             +-------------+
             |    NODE     |
             +-------------+
                    |
                    v
             Chrome/Firefox

                    ^
                    |
             +-------------+
             |  EVENT BUS  |
             +-------------+
                    ^
                    |
              Node / Grid
              Components
```

---

# 17. 60-Second Interview Answer

If the interviewer asks:

**"How does Selenium Grid 4 work internally?"**

You can answer:

> "Selenium Grid 4 uses a distributed, event-driven architecture consisting of Router, New Session Queue, Distributor, Session Map, Event Bus, and Nodes. The Router is the entry point for client requests. A new session request goes to the Session Queue, and the Distributor looks for a matching available slot based on capabilities and sends the request to the appropriate Node. Once the session is created, the session ID is mapped to the Node in the Session Map. Subsequent WebDriver commands go through the Router, which uses the Session Map to route them directly to that Node rather than involving the Distributor.
>
> Grid 4 uses a hybrid communication model: synchronous HTTP is used where a response is required, while the Event Bus provides asynchronous communication for things like Node registration and heartbeat events. This decoupling improves scalability and allows Grid components to be distributed independently."

---

# 18. Interview Questions to Prepare

### Basic

1. What is Selenium Grid?
2. What is the difference between Selenium Grid 3 and Grid 4?
3. What is a Grid Node?
4. What is a Grid Router?
5. What is the Distributor?
6. What is the New Session Queue?
7. What is the Session Map?
8. What is the Event Bus?

### Advanced

9. Explain Selenium Grid 4 architecture.
10. Explain the Grid 4 async architecture.
11. What is the difference between synchronous and asynchronous communication?
12. How does Grid decide which Node receives a test?
13. Why isn't the Distributor involved in every WebDriver command?
14. How does Grid route an existing session?
15. How does Node registration work?
16. What is a Node heartbeat?
17. How does Selenium Grid scale horizontally?
18. How would you design Grid for 1,000+ parallel tests?
19. How would you run Selenium Grid using Docker?
20. How would you run Selenium Grid on Kubernetes?
21. How do you troubleshoot a Grid session allocation problem?
22. How do you handle browser crashes in Grid?
23. How do you control parallel execution?
24. How do you avoid test interference between parallel sessions?
25. Selenium Grid vs cloud providers such as BrowserStack/Sauce Labs?

---

# 19. Key Points to Memorize

For an SDET/Lead interview, remember these six points:

```text
Router
   ↓
New Session Queue
   ↓
Distributor
   ↓
Node
```

And:

```text
Existing Session
   ↓
Router
   ↓
Session Map
   ↓
Node
```

And separately:

```text
Grid Components
       ↕
   Event Bus
       ↕
 Asynchronous Events
```

### One-line summary

> **Selenium Grid 4 is a distributed, event-driven system where the Router handles client traffic, the Distributor allocates new sessions, the Session Map tracks existing sessions, Nodes execute browser sessions, and the Event Bus enables asynchronous communication between components.**

# Summative Prep - Java Systems & Networking Summative

## Let's Assess
* Object-Oriented Design (Abstract classes, Polymorphism, Encapsulation)
* Collections and Data Manipulation
* String Parsing and Rule-Based Logic
* Concurrency and the `Runnable` interface
* Unit Testing

## Instructions
1. **Coding Questions:** Complete the logic within the provided `.java` skeleton files.
2. **External Libraries:** Do **not** use external libraries (e.g., Jackson, Gson, Apache Commons) for string parsing or core logic. Rely strictly on built-in Java packages.
3. **Comprehension Questions:** Write your answers in the `answers.txt` file.
4. **Testing:** A full JUnit 5 test suite is provided. Ensure all tests pass.

---

## Part 1: Coding Questions

### Question 1 — `parseCommandArgs(String rawInput)`
The server for your robotics simulation receives raw string commands over a network socket. Before the server can act, it needs to parse this string into an ordered list of arguments.

Apply your logic to the `parseCommandArgs()` method. It receives a single `rawInput` string (e.g., `"launch sniper 5 3"`) and must return a `List<String>` containing the individual components.

**Rules:**
* Separate the string by spaces.
* If the input is `null` or entirely whitespace, return an empty list.
* Ignore multiple consecutive spaces (e.g., `"forward   10"` should yield `["forward", "10"]`).

### Question 2 — Robot Hierarchy & Encapsulation
You are tasked with designing the foundation for different robot types in the simulation. Complete the classes in `Robot.java`:

1. Complete the **abstract base class** `Robot`. It must have:
    * A private `name` (String) and `shieldStrength` (int).
    * A constructor to initialize these fields.
    * Getters for both fields.
    * A method `public void takeDamage(int amount)` that reduces the shield strength but never lets it drop below `0`.
    * An abstract method `public abstract String fireWeapon();`.

2. Complete the **concrete class** `SniperRobot` that extends `Robot`.
    * Its `fireWeapon()` method must return: `"Sniper [name] fires a high-velocity piercing shot!"`

3. Complete the **concrete class** `TankRobot` that extends `Robot`.
    * Its `fireWeapon()` method must return: `"Tank [name] fires an explosive shell!"`

### Question 3 — `ShieldRepairTask` (Concurrency)
When a robot issues a `repair` command, it takes time. To prevent the entire server from freezing while one robot repairs, this action must be handled in a background thread.

Implement the `ShieldRepairTask` class so that it implements the `Runnable` interface.
* It requires a `Robot` object and a `repairTimeMs` (in milliseconds) in its constructor.
* When executed by a thread, it must pause execution for the specified `repairTimeMs` (using `Thread.sleep()`), and then restore the robot's shield strength by calling `robot.repairShields()`.
* Handle any potential `InterruptedException` gracefully by printing `"Repair interrupted"` to standard error.

### Question 4 — `filterActiveConnections(List<ClientConnection> connections)`
The server maintains a list of all client connections, but sometimes connections drop or are intentionally closed.

Apply your logic to the `filterActiveConnections()` method. You will receive a list of `ClientConnection` objects. You must return a **new list** containing only the connections where the state is exactly `"CONNECTED"`.

If the input list is `null` or empty, return an empty list. Do not modify the original list.

---

## Part 2: Comprehension Questions

Please answer these in `answers.txt`.

### Comprehension Question 1 — Sockets & Network IO
Your team is reviewing the server code, and a junior developer asks why the server uses `ServerSocket.accept()` inside an infinite `while(true)` loop. They are concerned that an infinite loop will crash the program or cause it to freeze indefinitely.

Explain how network sockets and `accept()` actually work in Java. Cover the concept of "blocking" methods, the roles of `InputStream` and `OutputStream`, and explain why the infinite loop is both safe and necessary for a server expecting multiple clients.

### Comprehension Question 2 — Concurrency & Race Conditions
Imagine two different threads in your server are simultaneously trying to execute `robot.takeDamage(1)` on the exact same `Robot` instance.

Explain to a teammate what a "race condition" is and why this scenario might result in the robot's shield strength being incorrect. How would you modify the `takeDamage` method (or the way threads interact with it) in Java to ensure the state remains perfectly accurate?

### Comprehension Question 3 — Client/Server Architecture
A non-technical product manager wants to understand exactly what happens when a player types `fire` in the client terminal. They've heard about "JSON" and "Protocols" but don't understand how the pieces connect.

Walk them through the lifecycle of that single command. Explain what the client does with the input, how it travels to the server, how the server understands it using your custom protocol, and how the result eventually gets displayed back on the player's screen.

### Comprehension Question 4 — Defensive Copying & Encapsulation
During a code review, you notice a teammate wrote a getter method in the `World` class that simply returns the internal `List<Robot> activeRobots`.

Explain why returning this raw internal list is a massive vulnerability in your Object-Oriented design. What could a malicious (or clumsy) client code do with that reference, and how should you alter the getter to protect the internal state of the `World`?
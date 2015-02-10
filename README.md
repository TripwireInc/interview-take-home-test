interview-take-home-test
========================
## First Things First
Hello and welcome! This 'take home' is a way for us to evaluate your skills as a developer. 

Please follow
[these instructions](https://help.github.com/articles/duplicating-a-repository)
for creating a place to work on this test. More explicitly please do not use the built-in GitHub fork functionality. Make your repo private in the github settings, and add users @whodevil
and @DavidWhitlock as contributors, so we can see your results. If you don't have a GitHub account that allows you to create private repositories, consider [this](https://education.github.com/discount_requests/new). If none of these are options, please feel free to contact us, and we can make other arangements.

### Running
From the command line start the server by running this command:
```bash
./gradlew runServer
```
Once the server us running, in another terminal window, you can start the client with this command and the client will begin accepting user input from the keyboard:
```bash
./gradlew run
```
You can run the unit tests in the system by running this command:
```bash
./gradlew test
```
If you'd prefer to use Intellij IDEA, you could generate the project files using this command line:
```bash
./gradlew idea
```
### The problem
The server accepts arbitrary strings from the client. Currently the strings are just echoed back. I'd like you to add a feature to this system: When the server recieves a string it should return the starting location and length of the longest contigous repeated character. Please add more unit tests for the feature you are writing. 

Example ```aaabbbbbbcccc``` will output ```(3, 6)```.

Bonus points! Make the server multi-threaded.

### Metrics for evaluation
We will be evaluating this project on:
* comprehension (how well you filled out the below questions) 
* unit tests are all passing
* code review of design, comments, maintainability.

### Describe the System
Please edit this README.md to provide a detailed description of what this system does, and how it's moving parts work together.

### Test Plan
Please edit this README.md to provide a detailed description of additional testing (manual and/or automated) that you would perform for this codebase.

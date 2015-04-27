interview-take-home-test
========================
## First Things First
Hello and welcome! This 'take home' is a way for us to evaluate your skills as a developer. 

Please make a clone of this repo. Please do not fork. When you have completed your work, please use [git-diff](http://stackoverflow.com/questions/1800783/compare-local-git-branch-with-remote-branch) to create a diff based off of ```origin/master``` and submit it to the manager you recieved this link from.

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
Please edit this README.md to provide a detailed description of what this system does, and how its moving parts work together.

### Test Plan
Please edit this README.md to provide a detailed description of additional testing (manual and/or automated) that you would perform for this codebase.

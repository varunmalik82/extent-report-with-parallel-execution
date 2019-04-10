### System Requirement
- Safari & ChromeDriver should be installed on your machine, if not please update the test for the browsers present on your machine

### Tests
- This solution contains 2 tests
- 'ReportingProblemWithParallelExecution' is created to show the issue caused due to unawareness of the role of static variable
in test creation for a threaded solution
-  'ReportingSolution' is another test created keeping the correct variable usage for a threaded application

### How to run the tests
- Update the Testng.xml for one the above test you are planning to run

### Notes -

`Static variable is a shared resource`
- which can be used to exchange some information among different threads.
And we need to be careful while accessing such a shared resource. Hence, we need to make sure that the access to static variables in multi-threaded environment is synchronized.

`Every thread has its own stack`
- Each thread has its own stack but they share the process heap.
Stack holds only the local variables and not the variables on the heap. Static variables are stored in the PermGen section of the heap and hence the access to them should be well guarded.

- This Solution is using Extent Report version '4.0.9' which has synchronized 'ExtentTest' methods so there is no need to keep Test and node creation steps in synchronized blocks and methods
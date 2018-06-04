# Google Search UI Test Suite

## Test cases
<br/>This web automated test suite contains 2 test cases
1.  Test if the auto suggest feature of the search box is working fine.
2.  Test if the search query is having correct results on the search result page.

## Solution includes the following:

* Logging
    - Implemented using Log4J to log at 2 destinations:
        1. Log messages on output stream
        2. Log in a file "automation.out" (residing at project level)
* Taking screenshot on failed tests
    - Available in the "/src/test/resources/failure_screencaptures" directory.
* Generation human readable report
    - HTML Reports are available in the "/test-output" directory having details of each test case execution.
* WebDriver factory
    - WebDriverFactory class is added as part of base package to enable WebDriver initialization for cross browser and platform test.
* Encapsulation layers like test data, logic of tests, actions on web pages and so on
    - PageFactory design pattern is used to have a clean separation of layers consisting of test data, logic
    and actions on web pages.
* Configurator(via testng.xml file):
  * run tests in parallel mode;
    - Test cases executed in parallel with maximum thread count of 3.
  * ability to run tests for different browsers/OS by configuring;
    - Test can run on windows/mac/linux OS for chrome/firefox browsers using parameters in testng.xml file.
  * ability to run tests for different environments(urls) by configuring/by command-line.
    - Website url can be passed via command line for test on various environment wiz. staging, test or production.
    - If not passed it will default back to "https://www.google.com".


## Steps to execute the project:

* Method 1: Command Line:
    * Execute via command line by entering following command.
    <br/>clean test -Durl="https://www.google.com"
* Method 2: TestNG file:
    * Execute via the testng xml and the test cases will run and produce the report.
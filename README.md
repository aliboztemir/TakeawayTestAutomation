#  Project Structure
* Programming Language: JAVA [JavaSE - 1.7]
* IDE: Eclipse
* OS : macOs x
* Create a Maven project to build the program;
* Add all dependences in pom.xml regarding some frameworks that I used such as Cucumber to manage BDD, Rest-assured to call the web-service REST and added a json-schema-validator to validate JSON response format;

##  BDD requires a feature file to invoke the step definitions:

* Create the scenarios in feature file as per the requirements, so each step in feature file has to match a step definition in class file;
* Following the BDD practices for coding;
* Different annotations have been defined for test types.
	1) Functional tests cases: @Smoke
	2) Stress test cases: @Stress
	3) Negative test cases: @Negative, @InvalidData, @NoAuth, @InvalidEndPoint

# Manual Tests:
	1) Postman tool was used for Manual Tests. At the same time, scenarios were written in features files with the gherkin programming language.;
	2) You can view the test cases from the following file path. --> src/test/java/features
	3) The bugs found are stated in the "PROBLEM" tab below

# Automated Functional Testing:
	1) Both valid and invalid scenarios were written for functional tests. The scenarios specified with the @Smoke tag in the feature files are for functional testing.
	2) You can view valid test cases from the following file path. --> src/test/java/features/ValidCasesTakeawayApi.feature
	3) You can view invalid test cases from the following file path. --> src/test/java/features/NegativeCasesTakeAwayApi.feature
	4) Note: Test steps were written using only gherkin syntax for negative scenarios. But their stepdefinitions were not developed. In general, a dynamic and generic structure was designed. If desired, step definitions of negative scenarios can also be developed.

# Stress Testing:
	* Scenarios written for stress testing are specified with @Smoke tag in feature files.;
	* Actually, stress testing within a framework designed for functional testing is not very efficient. Because in the OOP structure, the data passes through many scopes. In this case, when calling the api, the real-like response time does not return for the pure state of the api.
	* It is more possible to perform lifelike tests using Gatling vs Jmeter tools for stress tests.
	* Response times are calculated in stress test scenarios. In general, a threshold value of 1000 ms was defined.
	* If you want to run stress tests in multiple threads add the following code file to pom.xml. You can run it with the following command.
	
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-failsafe-plugin</artifactId>
    <version>2.22.0</version>
    <executions>
        <execution>
            <goals>
                <goal>integration-test</goal>
                <goal>verify</goal>
            </goals>
            <configuration>
                <parallel>methods</parallel>
                <useUnlimitedThreads>true</useUnlimitedThreads>
            </configuration>
        </execution>
    </executions>
</plugin>
To set the thread count to a specific number instead of useUnlimitedThreads use the below setting.

<configuration>
    <parallel>methods</parallel>
    <threadCount>4</threadCount>
</configuration>
```

### Test Automation Framework Guidelines:

	1) Created pojo classes for APIs with bodies --> /src/main/java/pojo;
	2) Endpoints of APIs are defined as enums --> src/main/java/java_resources/APIResources.java;
	3) Different annotations have been defined for test types. --> src/test/java/features
	4) Stepdefinition classes have been developed for feature files --> src/test/java/step_definitions
	5) You can view the classes that manage helper, response and request in the following file path. --> src/test/java/test_resources
	6- You can view the application's logs in the following file path. --> RestAssuredBDDTests-master/logging.txt

### Test Execution
* Execute from IDE by right clicking on features folder.
* run `mvn clean test` from command line.
* Can also be run from Maven using the following command: mvn test -Dcucumber.options="--tags @Smoke or @Stress"
* You can also right click and run with junit.

### Execution screenshots
Execution screenshots are placed in screenshots folder (screenshots/TestExecution-Cucumber.mp4)
* Reports are generated and placed in target folder `target/cucumber-html-report.html`
* Sample reports look like this ! "target/TestReportScreenShot.png"

![Test Report Screenshot](https://github.com/aliboztemir/TakeawayTestAutomation/blob/main/screenshots/TestReportScreenShot.png)

### Tools / libraries used :
* Cucumber
* Gherkin
* Rest Assured
* Maven
* Junit

### [PROBLEM] During the tests, the following problems were seen.

* Minimum length check can be added for "api/v1/tasks" api.

* Bad character check can be added for "api/v1/tasks" api. For example: "'+&/)&++"

* For PutTaskAPI, when we send a "null" value to the title field, it should give an error, but it acts as if it is updating.

* Min length and bad character control can be added for PutTaskAPI.

* There is a general problem for all api's. If we set the endpoints as invalid and call them, the .php extension files are displayed for the error message. A safer and more understandable error message may be displayed. Displaying all php file paths can also cause a security vulnerability.

* Likewise, when the "id" field is sent invalid at the endpoints of getTaskAPI, putTaskAPI and getTaskAPI, the response message appears to be problematic. Again, the line numbers of the code file with the .php extension error are displayed. The error level must be changed.

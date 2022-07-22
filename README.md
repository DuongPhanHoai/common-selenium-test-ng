# common-selenium-test-ng

This is the common test API for training purpose

There are 2 projects which apply different framework to Automation test
 - auto-awb-bdd: Apply cucumber
 - auto-awb-testng: Apply testNG framework

WEB Testing:
 - The test case inherits from the BaseWebTest which will cover all the driver for execution
 - The inside page objects inherits from Page which will init the element on the construction

API Testing
 - The test case inherits from the BaseAPITest which will keep the specification for re-use with the relevant login-token
 - The API which wrap the endpoint to actions (simulate the POM on API testing)

****** ****** EXECUTE TESTS ****** ******
- Cucumber: mvn clean test -pl auto-awb-bdd
- testNG: mvn clean test -pl auto-awb-testng -Dtest=*

- after execution there is surefire report
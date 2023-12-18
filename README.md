# accu-weather-auto-test

This is the common test project for training purpose

WEB Testing:
 - The test case inherits from the BaseWebTest which will cover help to engage with driver for execution
 - The inside page objects inherits from Page which will init the element on the construction

API Testing
 - The test case inherits from the BaseAPITest which will keep the specification for re-use with the relevant login-token
 - The API which wrap the endpoint to actions (simulate the POM on API testing)

>> REBUILD FOR DEMO AUTOMATION TEST
> MAIN TEST MODULE IS ****** auto-accu-weather ******
1 - Set env = dev / staging / production by set env variable (Add "-ea -Denv=staging" to VM Options of edit test run Configuration in IntelliJ)
> Also can set as tamplate to auto gen the template configuration
2 - Server info is store in resource/[env]/config.properties (env=dev/staging/production)

Execution command line: mvn clean test -pl auto-accu-weather -Dtest=*
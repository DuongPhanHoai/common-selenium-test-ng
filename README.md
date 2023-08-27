# common-selenium-test-ng

This is the common test API for training purpose

WEB Testing:
 - The test case inherits from the BaseWebTest which will cover help to engage with driver for execution
 - The inside page objects inherits from Page which will init the element on the construction

API Testing
 - The test case inherits from the BaseAPITest which will keep the specification for re-use with the relevant login-token
 - The API which wrap the endpoint to actions (simulate the POM on API testing)

Hope you can understand well the ideas,
GOOD LUCK

>> REBUILD FOR DEMO AUTOMATION TEST
1 - Set env = dev / staging / production by set env variable (Add "-ea -Denv=staging" to VM Options of edit test run Configuration in IntelliJ)
> Also can set as tamplate to auto gen the template configuration
2 - Server info is store in resource/[env]/config.properties of auto-sauce-demo (env=dev/staging/production)

- testNG: mvn clean test -pl auto-sauce-demo -Dtest=*

**UI tests:**
1. Login successful
2. Login failed
3. Add To Cart successful






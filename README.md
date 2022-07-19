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

****** This is the implementation branch specific for pantry for good ******
Pantry For Good is a good sample site which can contains some errors/bugs which simulates very the real testing circumstances
Following is the steps which guide you to in stall pantry for good

****** ****** INSTALLATION NOTE ****** ******
 - Follow the README of https://github.com/freeCodeCamp/pantry-for-good
   - But install NodeJS 6 (Newer may not work): https://nodejs.org/fr/blog/release/v6.11.1/
 - Configure data is stored under file config.properties of the test project for the correct reading

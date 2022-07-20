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

****** ****** ADDED CODE ****** ******
 - Configure data is stored under file config.properties of the auto-pantry-for-good / test / resources
   - Read in class ConfigReader
 - Excel is stored under file EditFoodIem.xlsx of the project auto-pantry-for-good / test / resources
   - Read in class ExcelReader, provide data to FoodTests
 - GoogleSheet report https://docs.google.com/spreadsheets/d/1eaAz6HwGiZxJjpTIgasOzVegRFK2UnDUC2H6r0-SI2Q
   - Meet the error "Unable to set permissions for" "because you are running on a non-POSIX file system."
   - In Windows fir by Grand permission for the executor or everyone(less secured)

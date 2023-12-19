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

I- To run the test by IDE
1- plz open this project with IntelliJ
2- open the module auto-accu-weather
3- open the class com.david.aw.test.web.DayTests
4- trigger the test scanAllDays

II- Execute by command lines
- Clean before run: mvn clean install -nsu -DskipTests
- Command to run regression
Linux, MACOS: mvn test -pl auto-accu-weather -Denv=production -Dsurefire.suiteXmlFiles=target/classes/suites/regression.xml
Windows: mvn test -pl auto-accu-weather -Denv=production -D"surefire.suiteXmlFiles"="target/classes/suites/regression.xml"
- Quick test to check
mvn test -pl auto-accu-weather -Denv=production -D"surefire.suiteXmlFiles"="target/classes/suites/alluretest.xml"

III- Report
Report store at auto-accu-weather\allure-results
Run command to generate report: mvn -pl auto-accu-weather allure:report
Run command to open: mvn -pl auto-accu-weather allure:serve

File detail save in auto-accu-weather/scanAllDays.json


// For history report I am configuring the allure report to have history



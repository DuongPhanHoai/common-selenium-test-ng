# accu-weather-auto-test

This is the common test project for training purpose

WEB Testing:
 - The test case inherits from the BaseWebTest which will cover help to engage with driver for execution
 - The inside page objects inherits from Page which will init the element on the construction

This project is build on Java 1.8 (quite old version)
Maven version 3.8.6
IDE: IntelliJ 2022.2.3

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

File detail save in auto-accu-weather/scanAllDays.json

III- Report
Report store at auto-accu-weather\allure-results
Run command to generate report: mvn -pl auto-accu-weather allure:report
Run command to open: mvn -pl auto-accu-weather allure:serve



// For history report I am configuring the allure report to have history



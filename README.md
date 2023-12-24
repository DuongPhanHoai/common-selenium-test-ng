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
- Quick test to check history report
mvn test -pl auto-accu-weather -Denv=production -D"surefire.suiteXmlFiles"="target/classes/suites/history.xml"

File detail save in auto-accu-weather/scanAllDays.json
Using gson to save file, the limitation appear with special character. Beside the consequence, gson is good lib can merge to object by reflection (mean no need setter methods for the target Class)

III- Report
1. EXCEL Report
Store at auto-accu-weather\TestNGResult.xlsx

2. ALLURE (not recommend)
Report store at auto-accu-weather\allure-results
Run command to generate report: mvn -pl auto-accu-weather allure:report
Before open the report, need to generate
Run command to open: mvn -pl auto-accu-weather allure:serve

3. Google sheet (recommend online solution)
Note: Because I need approval from google on account permission asking windows so has to skip this kind of report
Will try to have in the future after renew my Consent Security with google to have permission to modify the google sheet
However with the limitation of free account so it limit to some access per minutes so we cannot use this kins of report fpr API test
Sample canbe found at: https://docs.google.com/spreadsheets/d/1zKJ9lYqvJeNOdu2UX9ZOC3-vnn51sddkeJUs7Mvs-5s/edit#gid=282550025

VI- Note
For faster I put MAX_GET_DAYS = 5 to limit getting max 5 first to save running time demo
Increase that number can help to get more data

Recommendation
- This is UI automation but cover the lopping steps to get info so long, which increase risk of failing
- To increase the stability, for the test like this I suggest using API which can be re-try easier on fail



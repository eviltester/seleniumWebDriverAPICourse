# Selenium WebDriver API Course

[![build status](https://github.com/eviltester/seleniumWebDriverAPICourse/actions/workflows/build.yml/badge.svg)](https://github.com/eviltester/seleniumWebDriverAPICourse/actions)

The source code and slides for my online training course on Selenium WebDriver.

The course was originally written for Selenium 2, then updated to Selenium 3.

The course was removed from sale in 2019/2020.

The course videos are available to Patreon supporters:

- [Selenium WebDriver With Java Course](https://www.testerhq.com/member2/index.php?course=selenium_webdriver_3_api&page=coursecontents&preview=true)
- [Patreon](https://patreon.com/eviltester)

The code and slides material can still add value for self study.

The code has been updated and works on Selenium 3 and Selenium 4.

The Selenium 4 tests are also running on Chrome headless as a Github Action:

- [execution reports](https://github.com/eviltester/seleniumWebDriverAPICourse/actions/workflows/build.yml)

The application under test is available online at:

- [testpages.herokuapps.com](https://testpages.herokuapp.com/styled/index.html)
- [source code](https://github.com/eviltester/TestingApp/tree/master/java/testingapps/seleniumtestpages)

## Running

It should be possible to open the root directory in IntelliJ and it will find the pom.xml in the lower level folders and tests can be run from the IDE.

To run from the command line then change directory into the appropriate source directory.

- e.g. for Selenium 3 `cd code/selenium_3/code`
- e.g. for Selenium 4 `cd code/selenium_4/code`

At which point:

`mvn test` will run all the test packs


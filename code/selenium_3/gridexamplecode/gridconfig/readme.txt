In order to use the batch files here, you need to download the grid.

e.g. listed on:

- https://selenium.dev/downloads/

https://selenium-release.storage.googleapis.com/3.141/selenium-server-standalone-3.141.59.jar

If the version changes from 3.141.59 then download the new selenium-server-standalone-x.xx.x.jar and amend the .bat files to reference the correct version of the jar.

The /win folder has the files I use on windows

The /mac folder has the files I use on mac

I only really configure nodes on the mac, so it is just a node_mac.json file.

Remember:

* You need to change the version of selenium to match the one you downloaded.
* You need to set the path to the chrome driver you are using
* You need to change the ip addresses to those you are using

Examples:

* `java -jar selenium-server-standalone-3.141.59.jar -role hub`
  * will start a hub with default config
* `java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.1.139:4444/grid/register`
  * will start a node with default config, and register it on to the hub at 192.168.1.139
* `java -jar selenium-server-standalone-3.141.59.jar -role node -nodeConfig node_mac.json -Dwebdriver.chrome.driver=./chromedriver`
  * will start a node, configured by the file `node_mac.json` and where the ChromeDriver is located in the same folder and called `chromedriver`


To run the projects tests I would, from the project folder with the pom.xml, use:

mvn test -Dselenium2basics.webdriver=GRID -DWEBDRIVER_GRID_URL=http://localhost:4444/wd/hub -DWEBDRIVER_GRID_BROWSER=chrome

or

mvn test -Dselenium2basics.webdriver=GRID -DWEBDRIVER_GRID_URL=http://localhost:4444/wd/hub -DWEBDRIVER_GRID_BROWSER=firefox


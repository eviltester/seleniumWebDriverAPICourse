REM start a node configured by the node.json
REM http://code.google.com/p/selenium/source/browse/trunk/java/server/src/org/openqa/grid/common/defaults/DefaultNode.json
REM java -jar selenium-server-standalone-3.141.59.jar -role node -nodeConfig node.json -Dwebdriver.chrome.driver=../chromedriver/chromedriver.exe

REM to start one locally it all goes on the command line
java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register

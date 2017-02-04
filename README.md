# Page Refresher

**Page Refresher** refreshes a webpage as long as a particular word is displayed. When the word to be searched for disappears, then Page Refresher plays an alarm sound and stops refreshing.

![Page Refresher](https://cloud.githubusercontent.com/assets/469989/22622169/c2f8cff4-eb33-11e6-85fa-91de582b6760.png)

## Technology

Page Refresher launches an instance of Google Chrome using [Selenium](http://www.seleniumhq.org/) in connection with [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads). The UI, shown to configure Page Refresher, is built with JavaFX 8.

## Build Instructions

Page Refresher can be packed as an executable JAR file using `mvn package`. The ChromeDriver for Windows is bundled into the JAR file.

## Compatibility

Page Refresher has been successfully tested on Windows 10 with Java 1.8 using Chrome v56 (64-bit), ChromeDriver v2.27 and Selenium 3.0.1.

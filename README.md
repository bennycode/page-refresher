# Page Refresher

**Page Refresher** refreshes a webpage as long as a particular word is displayed. When the word to be searched for disappears, then Page Refresher plays an alarm sound and stops refreshing.

![Page Refresher](https://cloud.githubusercontent.com/assets/469989/22622169/c2f8cff4-eb33-11e6-85fa-91de582b6760.png)

## Technology

Page Refresher launches an instance of Google Chrome using [Selenium](http://www.seleniumhq.org/) in connection with [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads). The UI, shown to configure Page Refresher, is built with JavaFX 8.

## Build Instructions

Page Refresher can be packed as an executable JAR file using `mvn package`. The ChromeDriver for Windows is bundled into the JAR file.

## Compatibility

Page Refresher has been successfully tested on Windows 10 with Java 1.8 using Chrome v56 (64-bit), ChromeDriver v2.27 and Selenium 3.0.1.

## Scenario

You want to get alarmed when it's friday, so you configure Page Refresher to lookup [http://isitfridayyet.net/](http://isitfridayyet.net/):

1. Start **Page Refresher**
2. Set URL to "http://isitfridayyet.net/"
3. Set text to refresh to "You just missed it."
4. Set update interval to "1000" (ms)
5. Page Refresher will now play a test sound (so you know how it will sound like when there is an alarm)
6. Afterwards, Page Refresher will visit "http://isitfridayyet.net/" in a separate Google Chrome instance
7. As long as the text "You just missed it." is shown, Page Refresher will refresh the website once per second
8. As soon as Page Refresher cannot find the text "You just missed it." anymore, it will stop refreshing the browser window and play an alarm sound. The alarm will stop shortly after.

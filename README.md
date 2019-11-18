## Getting started with Selenium Appium BDD and applure report
### Setup:
* Install [Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
* Install Maven [Maven](https://maven.apache.org/)
* Set JAVA_HOME and MAVEN_HOME
* Install Cucumber eclipse plugin- http://cucumber.github.com/cucumber-eclipse/update-site

### Setting up appium on windows
* Go to https://developer.android.com/studio/index.html and Download android studio
* To Install Android Studio, Click Next button
* Check Android virtual Device check if not checked and click Next
* Again Click Next button and Click Install
* Choose do not “import import my settings” and Click OK
* Click Next and select Standard
* Again Click Next & Next
* On Verify Setting page, Click Finish
* Wait for Libraries to download
* Click Configure and Select SDK Manager
* Select your device’s or emulator’s Android API level (Version) from SDK Platforms Tab
* Select latest and Latest-1 API libraries for Install
* Click SDK Tools tab and Check below checkboxes
  I. Android SDK Build-Tools
  II. Android Emulator
  III. Android SDK Platform-tools
  IV. Documentation for Android SDK
  V. Intel X86 Emulator Accelerator(HAXM Installer)
  VI. Support Repository
* Click OK and Finish

### Setting ANDROID_HOME 
* Set  ANDROID_HOME environment variable to AndroidSDK home location where tools and build-tools folders are located 
* Now set path variable to %ANDROID_HOME% and %ANDROID_HOME%\tools
* Now create sample project in Android SDK
* And install all the missing Libraries (They are displaying as error with Install button)
* Click Device Icon on Top right in android studio
* Click on “Create Virtual Device”
* Select Hardware window will be displayed
* Select Any device Let say Pixel XL and Click Next
* Click Download API level 27 and Click Next & Finish
* Your virtual device screen will be displayed
* Now click on Launch your device

### Setting Up appium
* Go to http://appium.io/downloads.html and click “Appium-Desktop for OSX, Windows and Linux” link
* Click “appium-desktop-Setup-1.3.1.exe” file or latest one 
* When the installation file downloaded, click run and start to install appium desktop
* When installation finished, double-click the appium icon and open the appium server as shown below
* OR
* "npm install -g" to install appium
* "appium --allow-insecure chromedriver_autodownload" start appium with auto chrome download

* Let’s click the “Advanced” tab and change the Server Address as “127.0.0.1” and click Allow Session Override for override session when there will be problems and click “Start Server”.
* Click Allow access if window with Allow Access appear
* Appium server running message should be displayed

### Exceute Test
* mvn clean test -DbrowserName=chrome -DplatformName=android
* mvn clean test -DbrowserName=chrome -DplatformName=windows
* mvn allure:serve  to generate HTML report



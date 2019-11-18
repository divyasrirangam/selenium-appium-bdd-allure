package com.amegy.driverutils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.amegybank.enums.BrowserTypes;
import com.amegybank.utility.Constants;
import com.amegybank.utility.PropertyFileReader;

import io.appium.java_client.remote.MobileCapabilityType;

public class BrowserCapabilityUtils
{
   private static final Logger LOG = LogManager.getLogger(BrowserCapabilityUtils.class);

   public static String HUB_URL = PropertyFileReader.getProperty(Constants.HUB_URL);

   /**
    * Based on the platform requirement, this provides the suitable remote driver URL. If RUN_ONBS is true then it will always return the BrowserStack API URL
    * If in case false, it will update the HUB_URL as per the platform requirement and return the URL of Appium Server or HUB
    *
    * @param platformName
    * @return URL of Remote driver
    */
   public static String getPlatformBasedDriverURL(String platformName)
   {
      LOG.info("Getting platform URL details for: " + platformName);
      if (platformName.trim().equalsIgnoreCase(Platform.ANDROID.toString()) || platformName.trim().equalsIgnoreCase(Platform.IOS.toString()))
      {

         HUB_URL = PropertyFileReader.getProperty(Constants.APPIUM_SERVER_URL);
      }
      LOG.info("Got platform URL details for: " + platformName);
      return HUB_URL;
   }

   /**
    * This method is being used internally to set up the desired browser environment. This has to be called from the method responsible for initializing browser
    * as requirement of Test
    *
    * @param platformName
    * @param browserName
    * @return DesiredCapability object based on the platform specified
    */
   @SuppressWarnings("deprecation")
   public static DesiredCapabilities getDriverCapabilities(String platformName, String browserName)

   {
      DesiredCapabilities capability = null;
      LOG.info("Get driver capabilities for platform: " + platformName + ", browserName: " + browserName);

      if (platformName.toLowerCase().contains(Platform.ANDROID.toString()))
      {
         capability = DesiredCapabilities.android();

         capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
         capability.setCapability(MobileCapabilityType.UDID, PropertyFileReader.getProperty(Constants.ANDROID_DEVICE_NAME));
         capability.setCapability(MobileCapabilityType.DEVICE_NAME.toString(), PropertyFileReader.getProperty(Constants.ANDROID_DEVICE_NAME));
         capability.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
         capability.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
         capability.setCapability(MobileCapabilityType.VERSION, PropertyFileReader.getProperty(Constants.ANDROID_VERSION));
         capability.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
      }
      else if (platformName.toLowerCase().contains(Platform.IOS.toString()))
      {
         capability = DesiredCapabilities.iphone();
         capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
         capability.setCapability(MobileCapabilityType.UDID, PropertyFileReader.getProperty(Constants.iOS_DEVICE_NAME));
         capability.setCapability(MobileCapabilityType.DEVICE_NAME.toString(), PropertyFileReader.getProperty(Constants.iOS_DEVICE_NAME));
         capability.setCapability(MobileCapabilityType.BROWSER_NAME.toString(), browserName);
         capability.setCapability(MobileCapabilityType.PLATFORM_VERSION.toString(), PropertyFileReader.getProperty(Constants.iOS_VERSION));
         capability.setCapability(MobileCapabilityType.PLATFORM.toString(), Platform.IOS);
         capability.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
      }

      // Validating windows options
      else if (platformName.toLowerCase().contains(Platform.WINDOWS.toString()) && browserName.toLowerCase().contains(BrowserTypes.CHROME.toString()))
      {
         capability = DesiredCapabilities.chrome();
         capability.setCapability(MobileCapabilityType.BROWSER_NAME.toString(), BrowserType.CHROME);
         capability.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
      }
      else if (platformName.toLowerCase().contains(Platform.WINDOWS.toString()) && browserName.toLowerCase().contains(BrowserTypes.FIREFOX.toString()))
      {
         capability = DesiredCapabilities.firefox();
         capability.setCapability(MobileCapabilityType.BROWSER_NAME.toString(), BrowserType.FIREFOX);
         capability.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
      }
      else if (platformName.toLowerCase().contains(Platform.WINDOWS.toString()) && browserName.toLowerCase().contains(BrowserTypes.IE.toString()))
      {
         capability = DesiredCapabilities.internetExplorer();
         capability.setCapability(MobileCapabilityType.BROWSER_NAME.toString(), BrowserType.IE);
         capability.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
      }

      // Validating OS X options
      else if (platformName.toLowerCase().contains(Platform.IOS.toString()) && browserName.toLowerCase().contains(BrowserTypes.CHROME.toString()))
      {
         capability = DesiredCapabilities.chrome();
         capability.setCapability(MobileCapabilityType.BROWSER_NAME.toString(), BrowserType.CHROME);
         capability.setCapability(CapabilityType.PLATFORM, Platform.IOS);
      }
      else if (platformName.toLowerCase().contains(Platform.IOS.toString()) && browserName.toLowerCase().contains(BrowserTypes.SAFARI.toString()))
      {
         capability = DesiredCapabilities.safari();
         capability.setCapability(MobileCapabilityType.BROWSER_NAME.toString(), BrowserType.SAFARI);
         capability.setCapability(CapabilityType.PLATFORM, Platform.IOS);;
      }

      LOG.info("Created driver capabilities for platform: " + platformName + ", browserName: " + browserName);
      return capability;
   }
}
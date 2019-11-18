
package com.amegy.driverutils;

import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.amegybank.enums.BrowserTypes;
import com.amegybank.utility.SeleniumUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager
{
   public static WebDriver driver = null;
   public String vastURL;

   public static final Logger LOG = LogManager.getLogger(DriverManager.class);

   private static DriverManager driverManager;

   private DriverManager()
   {
      
   }

   /**
    * 
    */
   public static void initlizeDriver()
   {
      if (!(System.getProperty("browserName") == null) && (!System.getProperty("browserName").isEmpty()) && !(System.getProperty("platformName") == null)
         && !System.getProperty("platformName").isEmpty())
      {
         LOG.info("Starting creating driver manager instance: " + System.getProperty("platformName") + " , " + System.getProperty("browserName"));
         driver = driverManager.getDriver(System.getProperty("platformName"), System.getProperty("browserName"));
      }
      else
      {
         driver = driverManager.getDriver(Platform.WINDOWS.toString(), BrowserTypes.CHROME.toString());
      }
   }

   public static DriverManager getInstance()
   {
      if (driverManager == null)
      {
         driverManager = new DriverManager();
         initlizeDriver();
      }
      return driverManager;
   }

   public WebDriver getDriver()
   {
      return driver;
   }

   public IOSDriver<MobileElement> getiOSDriver()
   {
      return (IOSDriver<MobileElement>) driver;
   }

   /**
    * 
    * @param platformName
    * @param browserName
    * @param bsBuildName
    * @return
    */
   public IOSDriver<MobileElement> getiOSDriver(String platformName, String browserName)
   {
      LOG.info("==================Creating Driver instance==============================");
      LOG.info("platformName: " + platformName);
      LOG.info("browserName: " + browserName);
      try
      {
         // check if the Driver is for desktop and local driver configuration
         // in properties is true
         // then launch the local driver
         if ((platformName.toLowerCase().contains(Platform.WINDOWS.toString()) || platformName.toLowerCase().contains(Platform.IOS.toString())))
         {
            driver = new ChromeDriver();
         }
         else
         {
            String RemoteDriverURL = BrowserCapabilityUtils.getPlatformBasedDriverURL(platformName);
            LOG.info("RemoteDriverURL: " + RemoteDriverURL);
            driver = new IOSDriver<>(new URL(RemoteDriverURL), BrowserCapabilityUtils.getDriverCapabilities(platformName, browserName));
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         LOG.error(e.getMessage());
      }

      if (platformName.toLowerCase().contains("windows") || platformName.toLowerCase().contains("os x"))
      {
         driver.manage().window().maximize();
      }
      LOG.info("==================Driver instance creation done============================");
      return (IOSDriver<MobileElement>) driver;
   }

   /**
    * 
    * @param platformName
    * @param browserName
    * @param bsBuildName
    * @return
    */
   public WebDriver getDriver(String platformName, String browserName)
   {
      LOG.info("==================Creating Driver instance==============================");
      LOG.info("platformName: " + platformName);
      LOG.info("browserName: " + browserName);
      try
      {
         // check if the Driver is for desktop and local driver configuration
         // in properties is true
         // then launch the local driver
         if ((platformName.toLowerCase().contains(Platform.WINDOWS.toString().toLowerCase())
            || platformName.toLowerCase().contains(Platform.IOS.toString().toLowerCase())))
         {
            if (browserName.equals("chrome"))
            {
               WebDriverManager.chromedriver().setup();
               driver = new ChromeDriver();
            }
            else if (browserName.equals("firefox"))
            {
               WebDriverManager.firefoxdriver().setup();
               driver = new FirefoxDriver();
            }
            else if (browserName.equals("internetexplorer"))
            {
               WebDriverManager.iedriver().setup();
               driver = new InternetExplorerDriver();
            }
            else if (browserName.equals("phantom"))
            {
               WebDriverManager.phantomjs();
            }
            else
            {
               throw new RuntimeException("Does not support browser + " + browserName);
            }
         }
         else
         {
            String RemoteDriverURL = BrowserCapabilityUtils.getPlatformBasedDriverURL(platformName);
            LOG.info("RemoteDriverURL: " + RemoteDriverURL);
            driver = new RemoteWebDriver(new URL(RemoteDriverURL), BrowserCapabilityUtils.getDriverCapabilities(platformName, browserName));
         }
      }

      catch (Exception e)
      {
         e.printStackTrace();
         LOG.error(e.getMessage());
      }

      if (platformName.toLowerCase().contains("windows") || platformName.toLowerCase().contains("os x"))
      {
         driver.manage().window().maximize();
      }
      LOG.info("==================Driver instance creation done============================");
      return driver;
   }

   /**
    * Used to quit driver
    *
    */
   public void quitDriver(WebDriver driver)
   {
      LOG.info("Quiting driver instance");
      if (driver != null)
      {
         driver.quit();
         SeleniumUtils.waitForSeconds(3);
         driverManager = null;
         LOG.info("===========Driver instance closed===================");
      }
   }
}

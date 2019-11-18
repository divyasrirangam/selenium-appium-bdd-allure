
package com.amegybank.utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 * Copyright (C) 2017 Clearstream.TV, Inc. All Rights Reserved.
 * Proprietary and confidential.
 * 
*/
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.List;

public class SeleniumUtils
{

   private static int maxTimeout = Integer.parseInt(PropertyFileReader.getProperty(Constants.COMMON_WAIT_IN_SECONDS));
   private static final Logger LOG = LogManager.getLogger(PropertyFileReader.class);

   /**
    * waiting for seconds
    *
    * @param timeoutInSeconds
    *           timeout in seconds for wait
    */
   public static void waitForSeconds(int timeoutInSeconds)
   {
      try
      {
         Thread.sleep(timeoutInSeconds * 1000);
      }
      catch (InterruptedException e)
      {
         LOG.error("Thread interrupted error while timeout: " + e.getMessage());
         e.printStackTrace();
      }
   }

   public static List<String> getListFromWebElementsAsString(List<WebElement> columnListData)
   {
      List<String> elementList = new ArrayList<>();
      for (WebElement element : columnListData)
      {
         elementList.add(element.getText());
      }
      return elementList;

   }

   public static void waitForElementPresent(WebDriver driver, WebElement element)
   {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(maxTimeout, SECONDS).pollingEvery(500, TimeUnit.MICROSECONDS).ignoring(
         NoSuchElementException.class,
         StaleElementReferenceException.class);
      wait.until(ExpectedConditions.visibilityOf(element));
   }

   public static void waitForElementToBeClickable(WebDriver driver, WebElement element)
   {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
         .withTimeout(maxTimeout, SECONDS)
         .pollingEvery(500, TimeUnit.MICROSECONDS)
         .ignoring(StaleElementReferenceException.class)
         .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
      wait.until(ExpectedConditions.elementToBeClickable(element));
   }

   public static void waitForElementToBeSelected(WebDriver driver, WebElement element)
   {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(maxTimeout, SECONDS).pollingEvery(500, TimeUnit.MICROSECONDS).ignoring(
         NoSuchElementException.class,
         StaleElementReferenceException.class);
      wait.until(ExpectedConditions.elementToBeSelected(element));
   }

   public static void waitForElementToBeVisible(WebDriver driver, WebElement element)
   {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(maxTimeout, SECONDS).pollingEvery(500, TimeUnit.MICROSECONDS).ignoring(
         NoSuchElementException.class,
         StaleElementReferenceException.class);
      wait.until(ExpectedConditions.visibilityOf(element));
   }

   public static void waitForURLContains(WebDriver driver, String text)
   {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(maxTimeout, SECONDS).pollingEvery(500, TimeUnit.MICROSECONDS).ignoring(
         NoSuchElementException.class,
         StaleElementReferenceException.class);
      wait.until(ExpectedConditions.urlContains(text));
   }

   public static void waitForTextToBePresentInElement(WebDriver driver, WebElement element, String text)
   {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(maxTimeout, SECONDS).pollingEvery(500, TimeUnit.MICROSECONDS).ignoring(
         NoSuchElementException.class,
         StaleElementReferenceException.class);
      wait.until(ExpectedConditions.textToBePresentInElement(element, text));
   }
}

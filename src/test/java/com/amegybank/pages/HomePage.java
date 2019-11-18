/**
 * 
 */
package com.amegybank.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amegybank.utility.SeleniumUtils;

/**
 * 
 * @version $Id$
 */
public class HomePage
{

   /**
    * @param driver
    */
   static WebDriver driver;

   public HomePage(WebDriver driver)
   {
      this.driver = driver;
      PageFactory.initElements(driver, this);
   }

   @FindBy(css = ".nav li a")
   private List<WebElement> homePageSecondryNavigationMenus;

   @FindBy(css = "img.logo")
   private WebElement homePageLogo;

   @FindBy(css = "[data-segmentpath=\"personal\"], #main-menu > ul.main-menu__user-segment.active > li > a")
   private WebElement personalMenuLink;

   @FindBy(css = "button.main-nav__menu-button")
   private WebElement mainMenuOptions;

   public String getHomePageTitle()
   {
      // SeleniumUtils.waitForElementToBeVisible(driver, homePageLogo);
      return driver.getTitle();
   }

   public List<String> getHomeMenuOptionAsList()
   {
      return SeleniumUtils.getListFromWebElementsAsString(homePageSecondryNavigationMenus);
   }

   public boolean isHomePageLogoDisplayed()
   {
      return homePageLogo.isDisplayed();
   }

   public void clickPersonalMenu()
   {
      try
      {
         mainMenuOptions.click();
      }
      catch (Exception e)
      {

      }
      SeleniumUtils.waitForSeconds(2);
      personalMenuLink.click();
   }

}

/**
 * 
 */
package com.amegybank.steps;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.amegy.driverutils.DriverManager;
import com.amegybank.pages.HomePage;
import com.amegybank.utility.SeleniumUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

/**
 * 
 * @version $Id$
 */
public class HomePageSteps
{

   static WebDriver driver;
   static HomePage homePage;
   public static final Logger LOG = LogManager.getLogger(HomePageSteps.class);

   @Given("^I navigaed to \"([^\"]*)\" home page$")
   public void i_navigaed_to_home_page(String url) throws Throwable
   {
      LOG.info("Navigating amegy bank home Page");
      driver = DriverManager.getInstance().getDriver();
      SeleniumUtils.waitForSeconds(3);
      driver.get(url);
      LOG.info("Opened amegy bank home Page URL");
      homePage = new HomePage(driver);
   }

   @Then("^I should see amegybank home page$")
   public void i_should_see_amegybank_home_page() throws Throwable
   {
      System.out.println(homePage.getHomePageTitle());
      Assert.assertTrue(homePage.getHomePageTitle().contains("Home | Amegy Bank of Texas"));
      LOG.info("HomePage Navigation done ");
   }

   @Then("^I should see home page menus$")
   public void i_should_see_home_page_menus(DataTable dataTable) throws Throwable
   {
      List<String> menus = dataTable.asList(String.class);
      Assert.assertEquals(menus, homePage.getHomeMenuOptionAsList());
   }

   @Given("^I am on amegybank home page$")
   public void i_am_on_amegybank_home_page() throws Throwable
   {
     Assert.assertTrue(homePage.isHomePageLogoDisplayed());
   }

   @When("^I click on personal menu$")
   public void i_click_on_personal_menu() throws Throwable
   {
      homePage.clickPersonalMenu();
   }

   @Then("^I should see sub menus for personal menu$")
   public void i_should_see_sub_menus_for_personal_menu() throws Throwable
   {
      //driverManager.quitDriver(driver);
   }
   
   @Then("I close the browser for amegybank")
   public void i_close_the_browser_for_amegybank()
   {
      DriverManager.getInstance().quitDriver(driver);

   }
}

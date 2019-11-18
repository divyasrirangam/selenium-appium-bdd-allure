Feature: Home page navigation for https://www.amegybank.com/

  Scenario: Verify home page navigation1
    Given I navigaed to "https://www.amegybank.com/" home page
    Then I should see amegybank home page
    And I should see home page menus
      | About Us                 |
      | Contact Us               |
      | FAQs                     |
      | Agreement Center         |
      | Careers                  |
      | Privacy Policy           |
      | Online Privacy Statement |
      | Login to Desktop Banking |

  #About Us, Contact Us, FAQs, Agreement Center, Careers, Privacy Policy, Online Privacy Statement, Login to Desktop Banking
  Scenario: Verify home page sub menus for personal menu2
    Given I am on amegybank home page
    When I click on personal menu
    Then I should see sub menus for personal menu
    Then I close the browser for amegybank

@Regression
Feature: E-1: Login-Logout

  @Start
  Scenario Outline: 1: Successful Login
    Given the user is on the login page
    When the user logs in with the credentials <username> and <password>
    Then the user <user> will be logged in successfully

    Examples: 
      | username               | password | user                   |
      | EmployeeAutomationTest | test     | EmployeeAutomationTest |

  Scenario: 2: Logout
    Given the user is logged in
    When the user logs out
    Then the user will be back on the login page

  @End
  Scenario Outline: 3: Invalid Login
    Given the user is on the login page
    When the user logs in with the credentials <username> and <password>
    Then the feedback message "Invalid username or password." will be displayed

    Examples: 
      | username               | password      |
      | EmployeeAutomationTest | wrongPassword |

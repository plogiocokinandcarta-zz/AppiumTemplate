Feature: Open google maps emulating a disconected device
  Scenario: Using Devtools from selenium
    Given emulated google maps in selenium
    And emulated network conection
    Then the app works in offline mode

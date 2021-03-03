Feature: Open google maps and serach a place
  Scenario: IA Smoke test on google maps
    Given the google maps app on appium
    When search "Quilmes"
    Then the maps open in the correct place

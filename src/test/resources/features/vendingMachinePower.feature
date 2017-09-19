Feature: VendingMachinePower
  As a user
  I want to be able to power the machine on and off 
  So that I can carry out maintenance
Scenario: Power machine on
    Given The machine is powered off
    When I power it on
    Then The machine sets itself to the initial state
    And The machine notifies the user that it is ready to start vending
Scenario: Power machine off
    Given The machine is powered on
    When I power it off
    Then The machine sets itself to the off state
    And The machine no longer accepts messages
	
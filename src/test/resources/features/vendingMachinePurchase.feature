Feature: VendingMachinePurchase 
	As a user
  I want to use the vending machine to buy things 
  So that I can obtain goods when no-one is around
Scenario Outline: Return coins when there are sufficient coins to make up the balance - one coin needed
	Given The machine is powered on with customer balance <balance> 
	And There are sufficient coins to return the balance 
	When I request coin return 
	Then The machine returns <balance> 
	Examples: 
		|balance|
		|0|
		|10|
		

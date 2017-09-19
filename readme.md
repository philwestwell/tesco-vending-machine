# Tesco Vending Machine

A private Github repo to model a vending machine's state during operation.
 
1. The machine accepts the following coins

* 10p
* 20p
* 50p
* £1

2. The machine vends three types of item

* Item A costing £0.60
* Item B costing £1.00
* Item C costing £1.70

3. The machine responds to the following messages

* `insertCoin`

takes a parameter of single coin (see above for valid coins) and updates the balance

* `returnCoins`

returns coins representing the current balance (does not need to be the same coins)

* `purchaseItem`

takes a parameter of a single item (see above for valid items) and returns coins representing the unused balance

* `turnOn`

loads the current persisted state for the number of coins and products of each type

accepts new transactions    
 
* `turnOff`

no longer accepts requests
 
# Assumptions

1. The coin slot hardware has responsibility to filter out unsupported tokens (Euros, washers etc) without notifying the software
2. At present it's not clear whether the hardware would take care of coins and requests when powered off (arguably if there is no power then there is no software either) so need to double check with the stakeholders how this all works

# To get the code

1. Clone this repo from Github
2. Download the Maven dependencies
* command line
``` mvn clean install ```
* Eclipse
``` Project, Clean ```
* Intellij
Should download dependencies automatically. If not then open the IDE's terminal command window, then 
``` mvn clean install ```

# Features of the code
1. If infinitest is installed in your IDE to support faster TDD, then filters can be applied using file 
` infinitest.filters `
2. Mockito is included in the dependencies. Mockito is useful in TDD particularly for the following scenarios
* checking that dependent objects were called in the expected way
* setting up a specific response from a dependant object
When developing this project, take care not to mock inadvertently the code you are trying to execute in the test!

3. Some sample BDD Cucumber tests are included. If this code was being developed as an agile project, the BDD tests for the next show and tell would be added at the start of the sprint. 

4. The project includes the PITest mutation test tool, a very powerful approach to assessing the effectiveness of the unit tests (refer http://pitest.org), notably more effective than simple code coverage.
PITest can be run directly from the commandline
 	`mvn org.pitest:pitest-maven:mutationCoverage`
This outputs html reports to target/pit-reports/YYYYMMDDHHMI.

# todo
* Update BDD features 
* Add shortcomings in tests identified by PITest
* Review responsibilities of classes and refactor as appropriate so that the code is easy to change. Design principles to consider
..* Separate what changes from what stays the same
..* Classes should be open for extension but closed for modification
..* Classes should have only one reason to change
..* Encapsulate what varies
..* Depend on abstraction, not concrete classes
* Add BDD tests
* Run static code analysis tool
* Incorporate as part of CI Build
* The CashManager sets up hardcoded values for the initial coin balances. Initial balance could be passed in to the constructor after retrieving from local storage
* Deal with situation where insufficient coins to provide change - needs design 
* Think through how the actor is going to interact with the vening machine in terms of feedback e.g. successful transaction, error transaction (insufficient balance)
